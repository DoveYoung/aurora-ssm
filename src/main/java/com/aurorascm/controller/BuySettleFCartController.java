package com.aurorascm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aurorascm.service.AreaAddrService;
import com.aurorascm.service.BuySettleFCartService;
import com.aurorascm.service.ShopCartService;
import com.aurorascm.service.myzone.OrderService;
import com.aurorascm.util.AppUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.MyDataException;
import com.aurorascm.util.MyParamException;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/** 购物车发起购买结算
 * @author BYG 2017-7-21
 * @version 1.0
 */
@Controller
@RequestMapping(value="/buySettleFCart")
public class BuySettleFCartController extends BaseController {

	@Resource(name="shopCartServiceImpl")
	private ShopCartService shopCartServiceImpl;
	@Resource(name="areaAddrServiceImpl")
	private AreaAddrService areaAddrServiceImpl;
	@Resource(name="orderServiceImpl")
	private OrderService orderServiceImpl;
	@Resource(name="buySettleFCartServiceImpl")
	private BuySettleFCartService buySettleFCartServiceImpl;
	
	/**购物车【全款】购买结算
	 * @param customerID bCartIDs hCartIDs gCartIDs province
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goFSettleFC", produces="application/json;charset=UTF-8")
	public ModelAndView goFSettleFC()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String msg="";
		Session session = Jurisdiction.getSession();
		String customerID =  (String)session.getAttribute("customerID");
		String bCartIDs = Tools.notEmptys(pd.getString("bCartIDs")) ? pd.getString("bCartIDs").replace(" ", "") : null;//保税仓
		String hCartIDs = Tools.notEmptys(pd.getString("hCartIDs")) ? pd.getString("hCartIDs").replace(" ", "") : null;//海外直邮
		String gCartIDs = Tools.notEmptys(pd.getString("gCartIDs")) ? pd.getString("gCartIDs").replace(" ", "") : null;//国内现货
		String saID = Tools.notEmptys(pd.getString("saID")) ? pd.getString("saID").replace(" ", "") : null;			//邮寄地址ID
		if (Tools.notEmptys(customerID)) {
			if(bCartIDs != null || hCartIDs != null || gCartIDs != null){
				try{
					int type = 1;//1为立即购买；2为立即采购
					Map<String, Object> map = buySettleFCartServiceImpl.getFSettleFSC(customerID,bCartIDs,hCartIDs,gCartIDs,saID,type);
					mv.addObject("bCartFPMath", map.get("bCartFPMath"));
					mv.addObject("hCartFPMath", map.get("hCartFPMath"));
					mv.addObject("gCartFPMath", map.get("gCartFPMath"));
					mv.addObject("tPostage", map.get("tPostage"));
					mv.addObject("pMoney", map.get("pMoney"));
					mv.addObject("tMoney", map.get("tMoney"));
					mv.addObject("shipAddr", map.get("shipAddr"));
					mv.addObject("wc1Address", map.get("wc1Address"));
					mv.addObject("shipAddress5", map.get("shipAddress5"));
					mv.addObject("newShipAddr", map.get("newShipAddr"));
					mv.addObject("sType", "fsfc");
					mv.addObject("bPostage", map.get("bPostage"));
					mv.addObject("hPostage", map.get("hPostage"));
					mv.addObject("gPostage", map.get("gPostage"));
					mv.addObject("btMoney", map.get("btMoney"));
					mv.addObject("htMoney", map.get("htMoney"));
					mv.setViewName("system/order/confirmCar");
				}catch(Exception e){
					e.printStackTrace();
					msg="网络异常，请稍后重试或联系客服！";
					logger.info("【CBSFCC:购物车全款结算执行异常】");
	 				throw new Exception(msg);
				}
			}else{
				msg="网络异常，请稍后重试或联系客服！";
				logger.info("【CBSFCC:购物车全款结算结算商品参数有误!】");
				throw new Exception(msg);
			}	
		}else{
			msg = "登录超时，请登录！";
			logger.info("【CBSFCC:购物车全款结算客户登录超时！】");
			throw new Exception(msg);
		}
		return mv;
	}
	
	/**
	 * 购物车【全款】结算画面——地址变更结算信息异步请求更新
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getFSettleInfoFC", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getFSettleInfoFC()throws Exception{
		Map<String, Object> rMap = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		Session session = Jurisdiction.getSession();
		String customerID =  (String)session.getAttribute("customerID");
		String bCartIDs = Tools.notEmptys(pd.getString("bCartIDs")) ? pd.getString("bCartIDs").replace(" ", "") : null;//保税仓
		String hCartIDs = Tools.notEmptys(pd.getString("hCartIDs")) ? pd.getString("hCartIDs").replace(" ", "") : null;//海外直邮
		String gCartIDs = Tools.notEmptys(pd.getString("gCartIDs")) ? pd.getString("gCartIDs").replace(" ", "") : null;//国内现货
		String saID = Tools.notEmptys(pd.getString("saID")) ? pd.getString("saID").replace(" ", "") : null;			//邮寄地址ID
		if (Tools.notEmptys(customerID)) {
			if(bCartIDs != null || hCartIDs != null || gCartIDs != null){
				try{
					int type = 1;//1为立即购买；2为立即采购
					Map<String, Object> map = buySettleFCartServiceImpl.getFSettleFSC(customerID,bCartIDs,hCartIDs,gCartIDs,saID,type);
					rMap.put("bCartFPMath", map.get("bCartFPMath"));
					rMap.put("hCartFPMath", map.get("hCartFPMath"));
					rMap.put("gCartFPMath", map.get("gCartFPMath"));
					rMap.put("tPostage", map.get("tPostage"));
					rMap.put("pMoney", map.get("pMoney"));
					rMap.put("tMoney", map.get("tMoney"));
					rMap.put("shipAddr", map.get("shipAddr"));
					rMap.put("wc1Address", map.get("wc1Address"));
					rMap.put("shipAddress5", map.get("shipAddress5"));
					rMap.put("newShipAddr", map.get("newShipAddr"));
					rMap.put("sType", "fsfc");
					rMap.put("bPostage", map.get("bPostage"));
					rMap.put("hPostage", map.get("hPostage"));
					rMap.put("gPostage", map.get("gPostage"));
					rMap.put("btMoney", map.get("btMoney"));
					rMap.put("htMoney", map.get("htMoney"));
					result = "success";
				}catch(Exception e){
					e.printStackTrace();
					msg="网络异常，请稍后重试或联系客服!";
					result = "error";
					logger.info("【CBSFCC:购物车全款结算执行异常!】");
				}
			}else{
				msg="商品参数有误!请稍后重试或联系客服!";
				result = "failed";
				logger.info("【CBSFCC:购物车全款结算商品ID参数异常!】");
			}	
		}else{
			msg = "登录超时，请登录！";
			result = "failed";
			logger.info("【CBSFCC:购物车全款结算客户登录超时！】");
		}
		rMap.put("result", result);
		rMap.put("msg", msg);
		rMap.put("pd", pd);
		return AppUtil.returnObject(pd, rMap);
	}
	
	/**购物车【全款】提交订单--ajax预请求判断购买金额是否超额+购买数量是否在三段、库存范围内checkAmountFP
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/checkAmountFP", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object checkAmountFP()throws Exception{
		Map<String, Object> rMap = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		Session session = Jurisdiction.getSession();
		String customerID =  (String)session.getAttribute("customerID");
		String bCartIDs = Tools.notEmptys(pd.getString("bCartIDs")) ? pd.getString("bCartIDs").replace(" ", "") : null;
		String hCartIDs = Tools.notEmptys(pd.getString("hCartIDs")) ? pd.getString("hCartIDs").replace(" ", "") : null;
		String gCartIDs = Tools.notEmptys(pd.getString("gCartIDs")) ? pd.getString("gCartIDs").replace(" ", "") : null;
		String saID = Tools.notEmptys(pd.getString("saID")) ? pd.getString("saID").replace(" ", "") : null;			//邮寄地址ID
		if (Tools.notEmptys(customerID)) {
			if(bCartIDs != null || hCartIDs != null || gCartIDs!=null){
				try{
					Map<String, String> map = buySettleFCartServiceImpl.checkAmountFP(bCartIDs,hCartIDs,gCartIDs,saID);
					msg = map.get("msg");
					result = map.get("result");
				}catch(MyDataException e){
					result = "failed";
					msg = e.getMessage();
					logger.info("【CBSFCC:购买数量不在规定购买范围，或超出库存！】");
				}catch(Exception e){
					result = "failed";
					msg="网络异常，请稍后重试或联系客服!！";
					logger.info("【CBSFCC:购买数量不在规定购买范围，或超出库存！】");
				}
			}else{
				result = "failed";
				msg="网络异常，请稍后重试或联系客服!";
				logger.info("【CBSFCC:订单提交CHECK参数有误！】");
			}
		}else {
			result = "failed";
			msg = "登陆超时，请登录！";
			logger.info("【CBSFCC:订单提交CHECK登陆超时！】");
		}
		rMap.put("result", result);
		rMap.put("msg", msg);
		return AppUtil.returnObject(pd, rMap);
	}
	
	/**购物车【全款】提交订单
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goFPayFC", produces="application/json;charset=UTF-8")
	public ModelAndView goFPayFC()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String msg= "";
		Session session = Jurisdiction.getSession();
		String customerID =  (String)session.getAttribute("customerID");
		String customerName = (String)session.getAttribute("customerName");
		String customerRemark = Tools.notEmptys(pd.getString("customerRemark")) ? pd.getString("customerRemark").replace(" ", "") : null;
		String bCartIDs = Tools.notEmptys(pd.getString("bCartIDs")) ? pd.getString("bCartIDs").replace(" ", "") : null;
		String hCartIDs = Tools.notEmptys(pd.getString("hCartIDs")) ? pd.getString("hCartIDs").replace(" ", "") : null;
		String gCartIDs = Tools.notEmptys(pd.getString("gCartIDs")) ? pd.getString("gCartIDs").replace(" ", "") : null;
		String saID = Tools.notEmptys(pd.getString("saID")) ? pd.getString("saID").replace(" ", "") : null;			//邮寄地址ID
		if (Tools.notEmptys(customerID)) {
			if(bCartIDs != null || hCartIDs != null || gCartIDs != null){
				try{
					Map<String, Object> map = buySettleFCartServiceImpl.addFOrderFSC(customerID,customerName,bCartIDs,hCartIDs,gCartIDs,saID,customerRemark);
					mv.addObject("bCartFPMath", map.get("bCartFPMath"));
					mv.addObject("hCartFPMath", map.get("hCartFPMath"));
					mv.addObject("gCartFPMath", map.get("gCartFPMath"));
					mv.addObject("tMoney", map.get("tMoney"));
					mv.addObject("tPostage", map.get("tPostage"));
					mv.addObject("pMoney", map.get("pMoney"));
					mv.addObject("shipAddr", map.get("shipAddr"));
					mv.addObject("orderID", map.get("orderID"));
					mv.addObject("pType", "fpfc");
					mv.setViewName("system/order/payway");
				}catch(Exception e){
					msg="网络异常，请稍后重试或联系客服!";
					logger.info("【CBSFCC:购物车页面全款提交订单执行异常!】");
					throw new Exception(msg);
				}
			}else{
				msg="提交订单商品参数有误!";
				logger.info("【CBSFCC:购物车页面全款提交订单商品id参数异常!】");
				throw new Exception(msg);
			}
		}else{
			msg = "登陆超时，请登录！";
			logger.info("【CBSFCC:订单提交CHECK登陆超时！】");
			throw new Exception(msg);
		}
		return mv;
	}
	
	/**购物车【定金】购买结算
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goDSettleFC", produces="application/json;charset=UTF-8")
	public ModelAndView goDSettleFC()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String msg="";
		Session session = Jurisdiction.getSession();
		String customerID =  (String)session.getAttribute("customerID");
		String bCartIDs = pd.getString("bCartIDs") != null && !"".equals(pd.getString("bCartIDs").replace(" ", "") ) ? pd.getString("bCartIDs").replace(" ", "") : null;
		String hCartIDs = pd.getString("hCartIDs") != null && !"".equals(pd.getString("hCartIDs").replace(" ", "") ) ? pd.getString("hCartIDs").replace(" ", "") : null;
		String gCartIDs = pd.getString("gCartIDs") != null && !"".equals(pd.getString("gCartIDs").replace(" ", "") ) ? pd.getString("gCartIDs").replace(" ", "") : null;
		if (Tools.notEmptys(customerID)) {
			if(bCartIDs != null || hCartIDs != null || gCartIDs != null){
				try{
					Map<String, Object> map = buySettleFCartServiceImpl.getDSettleFSC(customerID,bCartIDs,hCartIDs,gCartIDs);
					mv.addObject("bCartDPMath", map.get("bCartDPMath"));
					mv.addObject("hCartDPMath", map.get("hCartDPMath"));
					mv.addObject("gCartDPMath", map.get("gCartDPMath"));
					mv.addObject("tFMoney", map.get("tFMoney"));//总的全款费用
					mv.addObject("pDMoney", map.get("pDMoney"));//总的定金
					mv.addObject("wc1Address", map.get("wc1Address"));
					mv.addObject("sType", "dsfc");
					mv.setViewName("system/order/confirmCar");
				}catch(Exception e){
					msg="网络异常,请稍后重试或联系客服!";
	 				logger.info("【购物车定金结算执行异常!!】");
					throw new Exception(msg);
				}
			}else{
				msg="结算商品参数有误!";
				logger.info("【CBSFCC:购物车页面定金结算商品id参数有误!】");
				throw new Exception(msg);
			}
		}else{
			msg = "登陆超时，请登录";
			logger.info("【CBSFCC:购物车【定金】购买结算客户登陆超时！】");
			throw new Exception(msg);
		}
		mv.addObject("msg",msg);
		return mv;
	}
	
	/**购物车【定金】提交订单--ajax预请求判断check购买数量是否在三段、库存范围内checkAmountDP
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/checkAmountDP", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object checkAmountDP()throws Exception{
		Map<String, Object> rMap = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		Session session = Jurisdiction.getSession();
		String customerID =  (String)session.getAttribute("customerID");
		String bCartIDs = Tools.notEmptys(pd.getString("bCartIDs")) ? pd.getString("bCartIDs").replace(" ", "") : null;
		String hCartIDs = Tools.notEmptys(pd.getString("hCartIDs")) ? pd.getString("hCartIDs").replace(" ", "") : null;
		String gCartIDs = Tools.notEmptys(pd.getString("gCartIDs")) ? pd.getString("gCartIDs").replace(" ", "") : null;
		if (Tools.notEmptys(customerID)) {
			if(bCartIDs != null || hCartIDs != null || gCartIDs!=null){
				try{
					Map<String, String> map  = buySettleFCartServiceImpl.checkAmountDP(bCartIDs,hCartIDs,gCartIDs);
					result = map.get("result");
				}catch(MyDataException e){
					result = "failed";
					msg = e.getMessage();
					logger.info("【CBSFCC:【定金】订单购买数量不在规定购买范围，或超出库存！】");
				}catch(Exception e){
					result = "failed";
					msg="网络异常,请稍后重试或联系客服!";
					logger.info("【CBSFCC:【定金】提交订单CHECK执行异常！】");
				}
			}else{
				result = "failed";
				msg="参数错误,请稍后重试或联系客服!";
				logger.info("【CBSFCC:【定金】提交订单CHECK参数有误！】");
			}
		}else {
			result = "failed";
			msg = "登陆超时，请登录！";
			logger.info("【CBSFCC:【定金】提交订单CHECK客户登录超时！】");
		}
		rMap.put("result", result);
		rMap.put("msg", msg);
		return AppUtil.returnObject(pd, rMap);
	}
	
	/**购物车【定金】购买提交订单支付
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goDPayFC", produces="application/json;charset=UTF-8")
	public ModelAndView goDPayFC()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String msg="";
		Session session = Jurisdiction.getSession();
		String customerID =  (String)session.getAttribute("customerID");
		String customerName = (String)session.getAttribute("customerName");
		String customerRemark = pd.getString("customerRemark") != null && !"".equals(pd.getString("customerRemark").replace(" ", "") ) ? pd.getString("customerRemark").replace(" ", "") : null;
		String bCartIDs = pd.getString("bCartIDs") != null && !"".equals(pd.getString("bCartIDs").replace(" ", "") ) ? pd.getString("bCartIDs").replace(" ", "") : null;
		String hCartIDs = pd.getString("hCartIDs") != null && !"".equals(pd.getString("hCartIDs").replace(" ", "") ) ? pd.getString("hCartIDs").replace(" ", "") : null;
		String gCartIDs = pd.getString("gCartIDs") != null && !"".equals(pd.getString("gCartIDs").replace(" ", "") ) ? pd.getString("gCartIDs").replace(" ", "") : null;
		if (Tools.notEmptys(customerID)) {	
			if(bCartIDs != null || hCartIDs != null || gCartIDs != null){
				try{
					Map<String, Object> map = buySettleFCartServiceImpl.addDOrderFSC(customerID,customerName,bCartIDs,hCartIDs,gCartIDs,customerRemark);
					mv.addObject("bCartDPMath", map.get("bCartDPMath"));
					mv.addObject("hCartDPMath", map.get("hCartDPMath"));
					mv.addObject("gCartDPMath", map.get("gCartDPMath"));
					mv.addObject("wc1Address", map.get("wc1Address"));
					mv.addObject("tFMoney", map.get("tFMoney"));//总的全款费用
					mv.addObject("pDMoney", map.get("pDMoney"));//总的定金
					mv.addObject("orderID", map.get("orderID"));
					mv.addObject("pType", "dpfc");
					mv.setViewName("system/order/payway");
				}catch(MyParamException e){
					msg="订单已经提交了,请到个人中心--我的订单查看";
					logger.info("【CBSFCC:【定金】订单重复提交】");
					throw new Exception(msg);
				}catch(Exception e){
					msg="网络异常,请稍后重试或联系客服!";
					logger.info("【CBSFCC:购物车【定金】订单提交执行异常!】");
	 				throw new Exception(msg);
				}
			}else{
				msg="订单参数有误,请稍后重试或联系客服!";
				logger.info("【CBSFCC:购物车页面定金支付商品id参数异常!】");
				throw new Exception(msg);
			}	
		}else{
			msg = "登陆超时，请登录";
			logger.info("【CBSFCC:【定金】订单提交客户登陆超时！】");
			throw new Exception(msg);
		}
		return mv;
	}
					
}
