package com.aurorascm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aurorascm.entity.Customer;
import com.aurorascm.entity.Result;
import com.aurorascm.service.AreaAddrService;
import com.aurorascm.service.myzone.CustomerService;
import com.aurorascm.util.AliyunSMS;
import com.aurorascm.util.AppUtil;
import com.aurorascm.util.Const;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/** 用户注册/登陆/退出/重置密码
 * @author BYG 2017-7-21
 * @version 1.0
 */
@Controller
@RequestMapping(value="/registerLogin")
public class CustomerRLController extends BaseController {
	
	@Resource(name="customerServiceImpl")
	private CustomerService customerServiceImpl;
	@Resource(name="areaAddrServiceImpl")
	private AreaAddrService areaAddrServiceImpl;
	
	/**去注册客户页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goRegister")
	public ModelAndView goRegister(String salasManager)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Session session = Jurisdiction.getSession();	
		String sSalasManager = Tools.notEmpty(salasManager) ? salasManager : "888";//销售经理用户id，为空则默认公池：888
		session.setAttribute("sSalasManager", sSalasManager);
		logger.info("【去用户注册页面】");
		mv.setViewName("system/index/register");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**注册短信验证码发送
	 * @param mobile
	 * @return Object
	 * @throws Exception
	 */
	@RequestMapping(value="/sendRSMS")
	@ResponseBody
	public Object sendRSMS()throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "";
		String msg = "";
		String mobile = Tools.notEmptys(pd.getString("mobile"))?pd.getString("mobile").replace(" ", ""):null;
		logger.info("【mobile：请求发送注册短信验证码】");
		if (mobile != null) {
			try {
				String templateCode = "SMS_86920192";//注册
				String templateParam= String.valueOf(Tools.getRandomNum());
				AliyunSMS.sendSMS(templateCode , mobile , templateParam);
				map.put("mCode", templateParam);
				result = "success";
			} catch (Exception e) {
				result = "error";
				msg = "网络异常，请稍后重试或联系客服！";
				logger.info("【CCRLC:系统执行注册短信验证码异常！】");
			}
		}else{
			msg = "参数错误，请稍后重试或联系客服！";
			result = "failed";
			logger.info("【CCRLC:参数错误，请稍后重试或联系客服！】");
		}
		map.put("result", result);
		map.put("msg", msg);
		map.put("pd", pd);
		return AppUtil.returnObject(pd, map);
	}
	
	/**
	 * 注册邮箱--即时验证是否存在
	 */
	@RequestMapping(value="/verifyEmail",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object verifyEmail(String email) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		email = email.replace(" ", "");
		if (Tools.checkEmail(email)) {
			try{
				PageData customerPD = customerServiceImpl.getCustomerByEmail(email);
				if (customerPD==null) {
					result = "success";
					msg = "该邮箱可以使用!";
				}else{
					result = "error";
					msg = "该邮箱已被注册!";
				}
			}catch (Exception e) {
				e.printStackTrace();
				result = "error";
				msg = "网络异常,稍后重试!";
				logger.info("CCRLC:注册邮箱即时验证执行异常！");
			}
		}
		map.put("result", result);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
	}
	
	
	/**注册提交
	 * @param customerEmail customerPassword customerMobile
	 * @return Object
	 * @throws Exception
	 */
	@RequestMapping(value="/saveRegister",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object saveRegister() throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		String customerEmail = Tools.notEmptys(pd.getString("customerEmail")) ? pd.getString("customerEmail").replace(" ", "") : null;
		String customerPassword = Tools.notEmptys(pd.getString("customerPassword")) ? pd.getString("customerPassword").replace(" ", "") : null;
		String customerMobile = Tools.notEmptys(pd.getString("customerMobile")) ? pd.getString("customerMobile").replace(" ", "") : null;
		if(customerEmail != null && customerPassword!= null){
			pd.put("customerEmail",customerEmail);
			Customer customer = customerServiceImpl.getCustomerByEmail(pd);
			if(customer == null){
				Session session = Jurisdiction.getSession();
				Object sSalasManager = session.getAttribute("sSalasManager");//销售经理
				pd.put("sSalasManager",null!=sSalasManager?sSalasManager:"888");
				pd.put("customerEmail",customerEmail);
				String randomNum = String.valueOf(Tools.getRandomNum());
				pd.put("salt", randomNum);
				pd.put("customerPassword",new SimpleHash("SHA-1", customerPassword, randomNum).toString());
				pd.put("customerMobile",customerMobile);
				pd.put("customerStatus",1);//1可用；2在线；3离线；4禁用
				pd.put("registerTime",DateUtil.getTime());
				try{
					customerServiceImpl.saveCustomerRegister(pd); 						//执行注册
					Customer newCustomer = customerServiceImpl.getCustomerByEmail(pd);
					String customerID = newCustomer.getCustomerID();
					this.addWC1Address(customerID);                                     //注册成功后默认为新用户添加一号微仓地址
					//注册成功后给客户发送一条成功短信
					AliyunSMS.sendSMS("SMS_129756496" , customerMobile , "");
					//注册成功后把用户信息放到session中
					session.setAttribute(Const.SESSION_CUSTOMER, newCustomer); 			
					session.setAttribute("customerID", customerID);
					session.setAttribute("customerEmail", customerEmail);
					String customerName = Tools.notEmptys(newCustomer.getCustomerName()) ? newCustomer.getCustomerName() : customerEmail;
					session.setAttribute("customerName", customerName);
					customerServiceImpl.addCustomerLoginDistribution();//数据统计--用户该时段登陆次数(当日,累计)
					result = "success";
				} catch (Exception e) {
					result = "error";
					msg = "网络异常,稍后重试!"; 
					logger.info("CCRLC:登陆执行系统异常!");
				}
			}else{
				result = "failed";
				msg = "注册邮箱已存在！";
				logger.info("CCRLC:"+msg);
			}
		}else{
			result = "failed";
			msg = "注册邮箱或密码为空"; 
			logger.info("CCRLC:"+msg);
		}
		map.put("result", result);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
	}
	
	/**拦截重定向到登陆页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goLoginPage")
	public ModelAndView goLoginPage()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/index/loginPage");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**拦截登录、验证用户，成功后转发到原请求。
	 * @param String customerEmail,String customerPassword,
	 * @return  result.goURL 
	 * @throws Exception
	 */
	@RequestMapping(value="/interceptLogin" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> interceptLogin(String customerEmail,String customerPassword,HttpServletRequest request)throws Exception{
		Result<Object> result = new Result<Object>();
		if (Tools.isEmpty(customerEmail)||Tools.isEmpty(customerPassword)) {
			result.setMsg("参数错误！");
			result.setState(Result.STATE_ERROR);
			return result;
		}
		try {  
			Subject subject = SecurityUtils.getSubject();											//shiro身份验证
			UsernamePasswordToken token = new UsernamePasswordToken(customerEmail, customerPassword); 
		   
	        subject.login(token);
			String customerID = Jurisdiction.getCustomerID();
			PageData pd = new PageData();
			pd.put("customerID", customerID);
			pd.put("customerStatus", 2);
			customerServiceImpl.updateCustomerStatus(pd);         								 //更新用户状态
			this.updateCustomerIP(customerID);                    								 //更新用户IP和登陆时间
			customerServiceImpl.addCustomerLoginDistribution();//数据统计--用户该时段登陆次数(当日,累计)
			logBefore(logger, customerEmail+"登录系统");
			token.clear();
			result.setState(Result.STATE_SUCCESS);
		} catch (UnknownAccountException uae) {
			result.setMsg("账号不存在！ ");
			result.setState(Result.STATE_ERROR);
		} catch (IncorrectCredentialsException iae) {
			result.setMsg("密码错误！ ");
			result.setState(Result.STATE_ERROR);
		} catch (LockedAccountException lae) {
			result.setMsg("账号被锁，请联系客服！ ");
			result.setState(Result.STATE_ERROR);
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			result.setMsg("网络异常，请联系客服！ ");
			result.setState(Result.STATE_ERROR);
			logger.info("【ERROR】"+DateUtil.getTime()+"登陆系统执行异常");
		} catch(Exception e){
			e.printStackTrace();
			result.setMsg("网络异常，请联系客服！ ");
			result.setState(Result.STATE_ERROR);
			logger.info("【ERROR】"+DateUtil.getTime()+"重定向登陆系统执行异常");
		}
		HttpSession session =  request.getSession();
		if( session.getAttribute("goURL") != null){
			String goURL = session.getAttribute("goURL").toString();
			result.setResult(goURL);
		}else{
			String goURL = "home";
			result.setResult(goURL);
		}
		return result;
	}
	
	/**请求登录，验证用户
	 * @param  String customerEmail,String customerPassword,
	 * @return Object
	 * @throws Exception
	 */
	@RequestMapping(value="/requestLogin" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> requestLogin(String customerEmail,String customerPassword)throws Exception{
		Result<Object> result = new Result<Object>();
		if (Tools.isEmpty(customerEmail)||Tools.isEmpty(customerPassword)) {
			result.setMsg("参数错误！");
			result.setState(Result.STATE_ERROR);
			return result;
		}				
	    try { 
	    	Subject subject = SecurityUtils.getSubject();
	    	UsernamePasswordToken token = new UsernamePasswordToken(customerEmail, customerPassword); 
	        subject.login(token);
			String customerID = Jurisdiction.getCustomerID();
			PageData pd = new PageData();
			pd.put("customerID", customerID);
			pd.put("customerStatus", 2);
			customerServiceImpl.updateCustomerStatus(pd);         								 //更新用户状态
			this.updateCustomerIP(customerID);                    								 //更新用户IP和登陆时间
			customerServiceImpl.addCustomerLoginDistribution();//数据统计--用户该时段登陆次数(当日,累计)
			token.clear();
			result.setState(Result.STATE_SUCCESS);
		} catch (UnknownAccountException uae) {
			result.setMsg("账号不存在！ ");
			result.setState(Result.STATE_ERROR);
		} catch (IncorrectCredentialsException iae) {
			result.setMsg("密码错误！ ");
			result.setState(Result.STATE_ERROR);
		} catch (LockedAccountException lae) {
			result.setMsg("账号被锁，请联系客服！ ");
			result.setState(Result.STATE_ERROR);
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			result.setMsg("网络异常，请联系客服！ ");
			result.setState(Result.STATE_ERROR);
			logger.info("【ERROR】"+DateUtil.getTime()+"登陆系统执行异常");
		} catch(Exception e){
			e.printStackTrace();
			result.setMsg("网络异常，请联系客服！ ");
			result.setState(Result.STATE_ERROR);
			logger.info("【ERROR】"+DateUtil.getTime()+"登陆系统执行异常");
		}
		return result;
	}
	
	/**
	 * @Title: getSession 
	 * @Description:  前端检查是否登陆
	 * @param    
	 * @return Object  
	 * @author SSY
	 * @date 2018年6月1日 下午2:49:45
	 */
	@RequestMapping(value="/getSession" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getSession() throws Exception{
		Result<Object> result = new Result<Object>();
		Session session = Jurisdiction.getSession();
		String customerID = (String)session.getAttribute("customerID");
		if (customerID != null) {
			result.setState(Result.STATE_SUCCESS);
		}else{
			result.setState(Result.STATE_ERROR);
		}
		return result;
	}
	
	/**
	 * 用户退出登陆
	 * @param session
	 * @return  result
	 * @throws Exception 
	 */
	@RequestMapping(value="/logout" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> logout() throws Exception{
		Result<Object> result = new Result<Object>();
		Subject subject = SecurityUtils.getSubject(); 		//shiro销毁登录
		subject.logout();
		result.setState(Result.STATE_SUCCESS);
		return result;
	}
			
	/**去重置密码页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goResetPwd")
	public ModelAndView goResetPwd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/index/resetPwd");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**重置密码步骤一：验证账号 
	 * @param  String customerEmail,String iCode
	 * @return Object
	 * @throws Exception
	 */
	@RequestMapping(value="/resetPW1")
	@ResponseBody
	public Object resetPW1()throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "";
		String msg = "";
		String customerEmail = Tools.notEmptys(pd.getString("customerEmail")) ? pd.getString("customerEmail").replace(" ", "") : null;
		logger.info("【customerEmail：重置密码步骤一：验证账号】");
		String iCode = Tools.notEmptys(pd.getString("iCode")) ? pd.getString("iCode").replace(" ", "") : null;
		if(customerEmail != null && iCode != null){
			try {
				Session session = Jurisdiction.getSession();
				String sessionCode = (String)session.getAttribute(Const.SESSION_VERIFICATION_CODE);		//获取session中的验证码
				if(Tools.notEmpty(sessionCode) && sessionCode.equalsIgnoreCase(iCode)){		//判断登录验证码
					session.setAttribute("resetPW1", Boolean.TRUE);
					pd.put("customerEmail",customerEmail);
					PageData customer = customerServiceImpl.getCustomerByEmail(customerEmail);
					if(customer!=null){
						result = "success";
						map.put("customer", customer);
						session.setAttribute("resetCustomerMobile", customer.getString("customer_mobile"));//锁定该手机号
						session.setAttribute("resetCustomerEmail", customer.getString("customer_email"));//锁定该邮箱
					}else{
						result = "failed";
						msg = "账号不存在！";
						logger.info("【CCRLC:重置密码步骤一账号不存在！】");
					}
				}else{
					result = "failed";
					msg = "验证码错误";//U20006
					logger.info("【CCRLC:重置密码步骤一验证码不正确！】");
				}
			} catch (Exception e) {
				result = "error";
				msg = "网络异常，请稍后重试！";
				logger.info("【CCRLC:系统执行重置密码步骤一异常！】");
			}
		}else{
			result = "failed";
			msg = "参数错误";
			logger.info("【CCRLC:重置密码步骤一邮箱或验证码为空！】");
		}
		map.put("result", result);
		map.put("msg", msg);
		map.put("pd", pd);
		return AppUtil.returnObject(pd, map);
	}


	/***
	 * @Title: resetPW2 
	 * @Description:重置密码步骤二：发送手机验证码
	 * @param  参数锁定第一步账户中绑定的手机号;
	 * @author SSY
	 * @date 2018年3月9日 上午10:37:36
	 */
	@RequestMapping(value="/resetPW2")
	@ResponseBody
	public Object resetPW2()throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "";
		String msg = "";
		
		Session session = Jurisdiction.getSession();
		Object attribute = session.getAttribute("resetPW1");
		Boolean resetPW1 = null!=attribute?(Boolean)attribute:Boolean.FALSE;
		if(resetPW1 ){//如果步奏1没走,不准过;
			session.removeAttribute("resetPW1");//防止恶意刷新频繁发送手机验证码;
			String customerEmail = (String)session.getAttribute("resetCustomerEmail");
			String customerMobile = (String)session.getAttribute("resetCustomerMobile");
			logger.info("【customerEmail：重置密码步骤二：请求短信验证】");
			if(customerEmail != null && customerMobile != null){
				try {
					String templateCode = "SMS_86920191";//密码修改
					String templateParam= String.valueOf(Tools.getRandomNum());
					AliyunSMS.sendSMS(templateCode , customerMobile , templateParam);
					session.setAttribute("mobileCode",templateParam);
					map.put("customerEmail", customerEmail);
					map.put("customerMobile", customerMobile);
					result = "success";
				} catch (Exception e) {
					result = "error";
					msg = "网络异常，请稍后重试！";
					logger.info("【CCRLC:系统执行重置密码步骤二：请求短信验证异常！】");
				}
			}else{
				result = "failed";
				msg = "参数异常，请稍后重试！";
				logger.info("【CCRLC:重置密码步骤二：请求短信验证邮箱或手机号为空！】");
			}
		}else{
			result = "failed";
			msg = "密码重置步奏错误!";
			logger.info("【CCRLC:重置密码步骤二：密码重置步奏错误!】");
		}	
		map.put("result", result);
		map.put("msg", msg);
		map.put("pd", pd);
		return AppUtil.returnObject(pd, map);
	}
	
	/**重置密码步骤三：短信验证提交
	 * @param String code
	 * @return Object
	 * @throws Exception
	 */
	@RequestMapping(value="/resetPW3")
	@ResponseBody
	public Object resetPW3()throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "";
		String msg = "";
		String code =  Tools.notEmptys(pd.getString("code")) ? pd.getString("code").replace(" ", "") : null;
		logger.info("【customerEmail：重置密码步骤三：短信验证提交】");
		if(code != null){
			Session session = Jurisdiction.getSession();
			Object attribute = session.getAttribute("mobileCode");
			String mailCode = attribute!=null?(String)attribute:null;
			if (mailCode!=null&&mailCode.equals(code)) {
				session.setAttribute("resetPW3", Boolean.TRUE);
				result="success";
			}else{
				result="failed";
				msg = "验证码错误!";
			}
		}else{
			result = "failed";
			msg = "验证码错误！";
			logger.info("【CCRLC:重置密码步骤三：验证码错误！】");
		}
		map.put("result", result);
		map.put("pd", pd);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
	}
	
	/**重置密码步骤四：设置新密码
	 * @param String customerPassword 
	 * @return Object
	 * @throws Exception
	 */
	@RequestMapping(value="/resetPW4")
	@ResponseBody
	public Object resetPW4()throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "";
		String msg = "";
		String customerPassword = Tools.notEmptys(pd.getString("customerPassword")) ? pd.getString("customerPassword").replace(" ", "") : null;
		logger.info("【customerEmail：重置密码步骤四：设置新密码】");
		Session session = Jurisdiction.getSession();
		Object attribute = session.getAttribute("resetPW3");
		Boolean resetPW3 = attribute!=null?(Boolean)attribute:Boolean.FALSE;
		if(customerPassword != null &&resetPW3){
			try {
				session.removeAttribute("resetPW3");//防止账号泄露,恶意更改他人密码;
				String customerEmail = (String)session.getAttribute("resetCustomerEmail");
				pd.put("customerEmail",customerEmail);
				pd.put("customerPassword",customerPassword);
				customerServiceImpl.resetPW(pd);
				result = "success";
			} catch (Exception e) {
				result = "failed";
				msg = "网络异常，请稍后重试！";
				logger.info("【CCRLC:系统执行重置密码步骤四：设置新密码异常！】");
			}
		}else{
			result = "failed";
			msg = "未通过手机验证！";
			logger.info("【CCRLC:重置密码步骤四：参数错误！】");
		}
		map.put("result", result);
		map.put("pd", pd);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
	}
				
	/** 更新登录用户的IP
	 * @param String customerID
	 * @throws Exception
	 */
	public void updateCustomerIP(String customerID) throws Exception {  
		PageData pd = new PageData();
		HttpServletRequest request = this.getRequest();
		String ip = "";
		if (request.getHeader("x-forwarded-for") == null) {  
			ip = request.getRemoteAddr();  
	    }else{
	    	ip = request.getHeader("x-forwarded-for");  
	    }
		pd.put("customerID", customerID);
		pd.put("time", DateUtil.getTime());
		pd.put("customerIP", ip);
		customerServiceImpl.updateCustomerIP(pd);
	}  
	
	/** 账户注册成功后默认添加一个北极光1号微仓地址。
	 * @param String customerID
	 * @throws Exception
	 */
	public void addWC1Address(String customerID) throws Exception {  
		PageData pd = new PageData();
		pd.put("customerID", customerID);
		pd.put("name", "北极光供应链");
		pd.put("mobile", "0571-87916936");
		pd.put("province", "浙江省");
		pd.put("provincePin", "");
		pd.put("city", "杭州市");
		pd.put("area", "");
		pd.put("detailAddr", "北极光1号微仓");
		pd.put("addressType", 1);
		pd.put("IDCard", "");
		pd.put("operateTime", DateUtil.getTime());
		areaAddrServiceImpl.addShipAddr(pd);

	}

}
