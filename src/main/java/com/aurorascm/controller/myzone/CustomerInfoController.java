package com.aurorascm.controller.myzone;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aurorascm.controller.BaseController;
import com.aurorascm.service.myzone.CustomerService;
import com.aurorascm.util.AppUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;


/** 个人中心
 * 		---我的资料
 * @author SSY 2017/8/30
 * @version 1.0
 */
@Controller
@RequestMapping(value="/customerInfo")
public class CustomerInfoController extends BaseController{
	
	@Resource(name="customerServiceImpl")
	private CustomerService customerServiceImpl;
	
	/**
	  * 个人资料页面;
	  * @param
	  * 
	  */
	@RequestMapping(value="/MyInfo",produces="application/json;charset=UTF-8")
	public ModelAndView getMyInfo() throws Exception{
		ModelAndView mv = this.getModelAndView();
		String msg = "";
		Session session = Jurisdiction.getSession();
		String  customerEmail = (String)session.getAttribute("customerEmail");
		try {
			PageData customer = customerServiceImpl.getCustomerByEmail(customerEmail);
			mv.addObject("customer", customer);//个人资料;
		} catch (Exception e) {
			e.printStackTrace();
			msg = "网络异常,请稍后重试!";
			logger.info("【CCIC:查询用户个人资料执行异常！】");
			throw new Exception(msg);
		}
		mv.addObject("menuIndex", 7);
		mv.setViewName("system/myzone/customerInfo");
		return mv;
	}
	 
	
	/**
	 * 保存修改后的个人资料;
	 * @param  name mobile email birthday sex, smsPush(短信推送1.推送,2不推送) emailPush(邮箱推送1.推送,2不推送);
	 * @return result
	 * @throws Exception
	 */
	@RequestMapping(value="/updateMyInfo", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object updateMyInfo()throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		Session session = Jurisdiction.getSession();
		String customerID =  (String)session.getAttribute("customerID");
		String name = Tools.notEmptys(pd.getString("name")) ? pd.getString("name").replace(" ", "") : null;
		String mobile = Tools.notEmptys(pd.getString("mobile")) ? pd.getString("mobile").replace(" ", "") : null;
		String email =  Tools.notEmptys(pd.getString("email"))  ? pd.getString("email").replace(" ", "") : null;
		String birthday =  Tools.notEmptys(pd.getString("birthday")) ? pd.getString("birthday").replace(" ", "") : null;
		String sex =  Tools.notEmptys(pd.getString("sex")) ? pd.getString("sex").replace(" ", "") : null;
		String emailPush =  Tools.notEmptys(pd.getString("emailPush")) ? pd.getString("emailPush").replace(" ", "") : "1";
		String smsPush =  Tools.notEmptys(pd.getString("smsPush")) ? pd.getString("smsPush").replace(" ", "") : "1";
		if (name!=null&&mobile!=null&&email!=null&&sex!=null) {
			pd.put("customerID", customerID);
			pd.put("name", name);
			pd.put("mobile", mobile);
			pd.put("email", email);
			pd.put("birthday", birthday);
			pd.put("sex", sex);
			pd.put("emailPush", emailPush);
			pd.put("smsPush", smsPush);
			try{
				customerServiceImpl.updateMyInfo(pd);//修改用户资料同时同步至session
				result = "success";
			}catch(Exception e){
				result = "error";
				msg = "保存失败!请稍后重试!";
				logger.info("【CCIC:保存修改后的个人资料执行异常！】");
			}
		}else{
			result = "failed";
			msg = "保存失败!带*项为必填项!";
			logger.info("【CCIC:保存修改的个人资料参数错误！】");
		}
		map.put("result", result);
		map.put("pd", pd);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
	}
}
