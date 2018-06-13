package com.aurorascm.controller.shop.home;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.aurorascm.controller.BaseController;
import com.aurorascm.entity.OrderPayInfo;
import com.aurorascm.entity.Result;
import com.aurorascm.service.AreaAddrService;
import com.aurorascm.service.BuySettleFCartService;
import com.aurorascm.service.ShopCartService;
import com.aurorascm.service.myzone.OrderService;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.MyDataException;
import com.aurorascm.util.MyParamException;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/** 购物车结算
 * @author BYG 2018.5.23
 * @version 2.0
 */
@Controller
@RequestMapping(value="/cartSettle")
public class CartSettleController extends BaseController {

	@Resource(name="shopCartServiceImpl")
	private ShopCartService shopCartServiceImpl;
	@Resource(name="areaAddrServiceImpl")
	private AreaAddrService areaAddrServiceImpl;
	@Resource(name="orderServiceImpl")
	private OrderService orderServiceImpl;
	@Resource(name="buySettleFCartServiceImpl")
	private BuySettleFCartService buySettleFCartServiceImpl;
	
	/**
	 * 
	 * @Title: goFSettleFC1 
	 * @Description: 购物车【全款--立即购买】
	 * @param    bCartIDs,  hCartIDs,  gCartIDs
	 * @return ModelAndView  
	 * @author BYG
	 * @date 2018年5月25日 下午4:08:18
	 */
	@RequestMapping(value="/goFSettleFC1", produces="application/json;charset=UTF-8")
	public ModelAndView goFSettleFC1()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String msg="";
		String customerID =  Jurisdiction.getCustomerID();
		String bCartIDs = Tools.notEmptys(pd.getString("bCartIDs")) ? pd.getString("bCartIDs").replace(" ", "") : null;//保税仓
		String hCartIDs = Tools.notEmptys(pd.getString("hCartIDs")) ? pd.getString("hCartIDs").replace(" ", "") : null;//海外直邮
		String gCartIDs = Tools.notEmptys(pd.getString("gCartIDs")) ? pd.getString("gCartIDs").replace(" ", "") : null;//国内现货
		String saID = null;			//邮寄地址ID
		if (customerID != null) {
			if(bCartIDs != null || hCartIDs != null || gCartIDs != null){
				try{
					int type = 1;//1为立即购买；2为立即采购
					Map<String, Object> map = buySettleFCartServiceImpl.getFSettleFSC(customerID,bCartIDs,hCartIDs,gCartIDs,saID,type);
					mv.addObject("map", JSON.toJSON(map));
					mv.setViewName("system/order/confirmCar1");
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
	 * @Title: goFSettleFC2 
	 * @Description: 购物车【全款--立即采购 】
	 * @param    bCartIDs hCartIDs gCartIDs 
	 * @return ModelAndView  
	 * @author SSY
	 * @date 2018年6月2日 下午4:10:56
	 */
	@RequestMapping(value="/goFSettleFC2", produces="application/json;charset=UTF-8")
	public ModelAndView goFSettleFC2()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String msg="";
		String customerID =  Jurisdiction.getCustomerID();
		String bCartIDs = Tools.notEmptys(pd.getString("bCartIDs")) ? pd.getString("bCartIDs").replace(" ", "") : null;//保税仓
		String hCartIDs = Tools.notEmptys(pd.getString("hCartIDs")) ? pd.getString("hCartIDs").replace(" ", "") : null;//海外直邮
		String gCartIDs = Tools.notEmptys(pd.getString("gCartIDs")) ? pd.getString("gCartIDs").replace(" ", "") : null;//国内现货
		String saID = null;			//邮寄地址ID
		if (customerID != null) {
			if(bCartIDs != null || hCartIDs != null || gCartIDs != null){
				try{
					int type = 2;//1为立即购买；2为立即采购
					Map<String, Object> map = buySettleFCartServiceImpl.getFSettleFSC(customerID,bCartIDs,hCartIDs,gCartIDs,saID,type);
					mv.addObject("map", JSON.toJSON(map));
					mv.setViewName("system/order/confirmCar2");
				}catch(Exception e){
					e.printStackTrace();
					msg="网络异常，请稍后重试或联系客服！";
					logger.info("购物车全款结算执行异常】");
	 				throw new Exception(msg);
				}
			}else{
				msg="网络异常，请稍后重试或联系客服！";
				logger.info("购物车全款结算结算商品参数有误!】");
				throw new Exception(msg);
			}	
		}else{
			msg = "登录超时，请登录！";
			logger.info("购物车全款结算客户登录超时！】");
			throw new Exception(msg);
		}
		return mv;
	}
	
	/**
	 * @Title: getFSettleInfoFC 
	 * @Description: 全款立即购买结算页 ——地址变更结算信息异步请求更新
	 * @param    
	 * @return Result<Object>  
	 * @author SSY
	 * @date 2018年6月2日 下午4:07:30
	 */
	@RequestMapping(value="/getFSettleInfoFC", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> getFSettleInfoFC()throws Exception{
		Result<Object> result = new Result<>();
		PageData pd = this.getPageData();
		String customerID =  Jurisdiction.getCustomerID();
		String bCartIDs = Tools.notEmptys(pd.getString("bCartIDs")) ? pd.getString("bCartIDs").replace(" ", "") : null;//保税仓
		String hCartIDs = Tools.notEmptys(pd.getString("hCartIDs")) ? pd.getString("hCartIDs").replace(" ", "") : null;//海外直邮
		String gCartIDs = Tools.notEmptys(pd.getString("gCartIDs")) ? pd.getString("gCartIDs").replace(" ", "") : null;//国内现货
		String saID = Tools.notEmptys(pd.getString("saID")) ? pd.getString("saID").replace(" ", "") : null;			//邮寄地址ID
		if (customerID != null) {
			if(bCartIDs != null || hCartIDs != null || gCartIDs != null){
				try{
					int type = 1;//1为立即购买；2为立即采购
					Map<String, Object> map = buySettleFCartServiceImpl.getFSettleFSC(customerID,bCartIDs,hCartIDs,gCartIDs,saID,type);
					result.setResult(map);
					result.setState(Result.STATE_SUCCESS);
				}catch(Exception e){
					e.printStackTrace();
					result.setMsg("网络异常，请稍后重试或联系客服!");
					result.setState(Result.STATE_ERROR);
					logger.info("【购物车全款结算执行异常!】");
				}
			}else{
				result.setMsg("参数异常!请稍后重试或联系客服!");
				result.setState(Result.STATE_ERROR);
				logger.info("【购物车全款结算商品ID参数异常!】");
			}	
		}else{
			result.setMsg("登录超时，请登录！");
			result.setState(Result.STATE_ERROR);
			logger.info("【购物车全款结算客户登录超时！】");
		}
		return result;
	}
	
	/**
	 * 结算页面【全款】提交订单--ajax预请求判断购买金额是否超额+购买数量是否在三段、库存范围内checkAmountFP
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/checkAmountFP", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> checkAmountFP()throws Exception{
		Result<Object> result = new Result<>();
		PageData pd = this.getPageData();
		String customerID = Jurisdiction.getCustomerID();
		String bCartIDs = Tools.notEmptys(pd.getString("bCartIDs")) ? pd.getString("bCartIDs").replace(" ", "") : null;
		String hCartIDs = Tools.notEmptys(pd.getString("hCartIDs")) ? pd.getString("hCartIDs").replace(" ", "") : null;
		String gCartIDs = Tools.notEmptys(pd.getString("gCartIDs")) ? pd.getString("gCartIDs").replace(" ", "") : null;
		String saID = Tools.notEmptys(pd.getString("saID")) ? pd.getString("saID").replace(" ", "") : null;			//邮寄地址ID
		if (customerID != null) {
			if(bCartIDs != null || hCartIDs != null || gCartIDs!=null){
				try{
					Map<String, String> map = buySettleFCartServiceImpl.checkAmountFP(bCartIDs,hCartIDs,gCartIDs,saID);
					result.setMsg(map.get("msg"));
					result.setState(map.get("result"));
				}catch(MyDataException e){
					result.setMsg("购买数量不在规定购买范围，或超出库存！");
					result.setState(Result.STATE_ERROR);
					logger.info("【CBSFCC:购买数量不在规定购买范围，或超出库存！】");
				}catch(Exception e){
					result.setMsg("网络异常，请稍后重试或联系客服!");
					result.setState(Result.STATE_ERROR);
				}
			}else{
				result.setMsg("参数异常！请稍后重试或联系客服!");
				result.setState(Result.STATE_ERROR);
				logger.info("【CBSFCC:订单提交CHECK参数有误！】");
			}
		}else {
			result.setMsg("登陆超时，请登录！");
			result.setState(Result.STATE_ERROR);
			logger.info("【CBSFCC:订单提交CHECK登陆超时！】");
		}
		return result;
	}
	
	
	
	/**
	 * @Title: goDSettleFC 
	 * @Description: 购物车【定金--立即采购】
	 * @param    
	 * @return ModelAndView  
	 * @author SSY
	 * @date 2018年6月2日 下午4:29:42
	 */
	@RequestMapping(value="/goDSettleFC", produces="application/json;charset=UTF-8")
	public ModelAndView goDSettleFC()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String msg="";
		String customerID =  Jurisdiction.getCustomerID();
		String bCartIDs = pd.getString("bCartIDs") != null && !"".equals(pd.getString("bCartIDs").replace(" ", "") ) ? pd.getString("bCartIDs").replace(" ", "") : null;
		String hCartIDs = pd.getString("hCartIDs") != null && !"".equals(pd.getString("hCartIDs").replace(" ", "") ) ? pd.getString("hCartIDs").replace(" ", "") : null;
		String gCartIDs = pd.getString("gCartIDs") != null && !"".equals(pd.getString("gCartIDs").replace(" ", "") ) ? pd.getString("gCartIDs").replace(" ", "") : null;
		if (customerID != null) {
			if(bCartIDs != null || hCartIDs != null || gCartIDs != null){
				try{
					Map<String, Object> map = buySettleFCartServiceImpl.getDSettleFSC(customerID,bCartIDs,hCartIDs,gCartIDs);
					mv.addObject("map", JSON.toJSON(map));
//					mv.addObject("bCartDPMath", map.get("bCartDPMath"));
//					mv.addObject("hCartDPMath", map.get("hCartDPMath"));
//					mv.addObject("gCartDPMath", map.get("gCartDPMath"));
//					mv.addObject("tFMoney", map.get("tFMoney"));//总的全款费用
//					mv.addObject("pDMoney", map.get("pDMoney"));//总的定金
//					mv.addObject("wc1Address", map.get("wc1Address"));
//					mv.addObject("sType", "dsfc");
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
	
	/**结算页【定金】提交订单--ajax预请求判断check购买数量是否在三段、库存范围内checkAmountDP
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/checkAmountDP", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> checkAmountDP()throws Exception{
		Result<Object> result = new Result<>();
		PageData pd =  this.getPageData();
		String customerID =  Jurisdiction.getCustomerID();
		String bCartIDs = Tools.notEmptys(pd.getString("bCartIDs")) ? pd.getString("bCartIDs").replace(" ", "") : null;
		String hCartIDs = Tools.notEmptys(pd.getString("hCartIDs")) ? pd.getString("hCartIDs").replace(" ", "") : null;
		String gCartIDs = Tools.notEmptys(pd.getString("gCartIDs")) ? pd.getString("gCartIDs").replace(" ", "") : null;
		if (customerID != null) {
			if(bCartIDs != null || hCartIDs != null || gCartIDs!=null){
				try{
					Map<String, String> map  = buySettleFCartServiceImpl.checkAmountDP(bCartIDs,hCartIDs,gCartIDs);
					result.setState(map.get("result"));
				}catch(MyDataException e){
					result.setMsg("订单购买数量不在规定购买范围，或超出库存！");
					result.setState(Result.STATE_ERROR);
					logger.info("【CBSFCC:【定金】订单购买数量不在规定购买范围，或超出库存！】");
				}catch(Exception e){
					result.setMsg("网络异常,请稍后重试或联系客服!");
					result.setState(Result.STATE_ERROR);
					logger.info("【CBSFCC:【定金】提交订单CHECK执行异常！】");
				}
			}else{
				result.setMsg("参数异常！请稍后重试或联系客服!");
				result.setState(Result.STATE_ERROR);
				logger.info("【【定金】提交订单CHECK参数有误！】");
			}
		}else {
			result.setMsg("登陆超时，请重新登录！");
			result.setState(Result.STATE_ERROR);
			logger.info("【【定金】提交订单CHECK客户登录超时！】");
		}
		return result;
	}
	
	
	/**
	 * @Title: addDOrderFSC 
	 * @Description: 结算页面---提交全款订单
	 * @param    String saID, String bCartIDs, String hCartIDs, String gCartIDs, String customerRemark
	 * @return Result<Object>  
	 * @author SSY
	 * @date 2018年5月28日 下午4:23:17
	 */
	@RequestMapping(value="/addFOrderFSC", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> addFOrderFSC(String saID, String bCartIDs, String hCartIDs, String gCartIDs, String customerRemark) throws Exception{
		Result<Object> result = new Result<Object>();
		bCartIDs = Tools.notEmptys(bCartIDs) ? bCartIDs.replace(" ", "") : null;
		hCartIDs = Tools.notEmptys(hCartIDs) ? hCartIDs.replace(" ", "") : null;
		gCartIDs = Tools.notEmptys(gCartIDs) ? gCartIDs.replace(" ", "") : null;
		if (Tools.isEmpty(bCartIDs)&&Tools.isEmpty(hCartIDs)&&Tools.isEmpty(gCartIDs)) {
			result.setMsg("参数错误！");
			result.setState(Result.STATE_SUCCESS);
			return result;
		}
		try {
			String customerID =  Jurisdiction.getCustomerID();
			String customerName = Jurisdiction.getCustomerName();
			Map<String, Object> map = buySettleFCartServiceImpl.addFOrderFSC(customerID,customerName,bCartIDs,hCartIDs,gCartIDs,saID,customerRemark);
			result.setResult(map.get("orderID"));
			result.setState(Result.STATE_SUCCESS);
		} catch (MyParamException me) { 
			result.setMsg("参数错误！");
			result.setState(Result.STATE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("网络异常!请稍后重试或联系客服！");
			result.setState(Result.STATE_ERROR);
		}
		return result;
	}
	 
	
	
	/**
	 * @Title: addDOrderFSC 
	 * @Description: 结算页面提交定金订单
	 * @param    String bCartIDs, String hCartIDs, String gCartIDs, String customerRemark
	 * @return Result<Object>  
	 * @author SSY
	 * @date 2018年5月28日 下午4:23:17
	 */
	@RequestMapping(value="/addDOrderFSC", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> addDOrderFSC(String bCartIDs, String hCartIDs, String gCartIDs, String customerRemark) throws Exception{
		Result<Object> result = new Result<Object>();
		bCartIDs = Tools.notEmptys(bCartIDs) ? bCartIDs.replace(" ", "") : null;
		hCartIDs = Tools.notEmptys(hCartIDs) ? hCartIDs.replace(" ", "") : null;
		gCartIDs = Tools.notEmptys(gCartIDs) ? gCartIDs.replace(" ", "") : null;
		if (Tools.isEmpty(bCartIDs)&&Tools.isEmpty(hCartIDs)&&Tools.isEmpty(gCartIDs)) {
			result.setMsg("参数错误！");
			result.setState(Result.STATE_SUCCESS);
			return result;
		}
		try {
			String customerID =  Jurisdiction.getCustomerID();
			String customerName = Jurisdiction.getCustomerName();
			Map<String, Object> map = buySettleFCartServiceImpl.addDOrderFSC(customerID,customerName,bCartIDs,hCartIDs,gCartIDs,customerRemark);
			result.setResult(map.get("orderID"));
			result.setState(Result.STATE_SUCCESS);
		} catch (MyParamException me) { 
			result.setMsg("参数错误！");
			result.setState(Result.STATE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("网络异常!请稍后重试或联系客服！");
			result.setState(Result.STATE_ERROR);
		}
		return result;
	}
	
	 
	/**
	 * @Title: goPay 
	 * @Description: 付款页面
	 * @param    
	 * @return  orderPayInfo
	 * @author SSY
	 * @date 2018年5月28日 下午4:30:13
	 */
	@RequestMapping(value="/goPay", produces="application/json;charset=UTF-8")
	public String goPay(String orderID,ModelMap map)throws Exception{
		if (Tools.isEmpty(orderID)) {
			throw new Exception("订单不存在! ");
		}
		try {
			OrderPayInfo orderPayInfo = orderServiceImpl.getOrderPayInfo(orderID);
			map.put("orderPayInfo", JSON.toJSON(orderPayInfo));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return "system/order/payway";
	}
				
}
