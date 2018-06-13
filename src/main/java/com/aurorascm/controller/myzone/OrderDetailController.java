package com.aurorascm.controller.myzone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.aurorascm.controller.BaseController;
import com.aurorascm.entity.OrderManage;
import com.aurorascm.entity.OrderPayInfo;
import com.aurorascm.entity.Result;
import com.aurorascm.service.ShopCartService;
import com.aurorascm.service.myzone.OrderService;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Tools;

/**
 * @Title: CustomerOrderController.java 
 * @Package com.aurorascm.controller.myzone 
 * @Description: 订单中心 ---我的微仓销售订单
 * @author SSY  
 * @date 2018年5月23日   下午16:11:18 
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/personal/orderDetail")
public class OrderDetailController extends BaseController {

	@Autowired
	private OrderService orderServiceImpl;
	@Autowired
	private ShopCartService shopCartServiceImpl;

	
	/**
	 * @Title: getOrder 
	 * @Description: 查询订单详情
	 * @param    String orderID
	 * @return   order
	 * @author SSY
	 * @date 2018年5月23日  下午16:11:18 
	 */
	@RequestMapping
	public String getOrder(ModelMap map, String orderID) throws Exception {
		try {
			OrderManage order = orderServiceImpl.getOrder(orderID);
			map.put("order", JSON.toJSON(order));
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("【ERROR】"+DateUtil.getTime()+"订单详情查询系统执行异常! ");
			throw new Exception();
		}
		return "system/personal/orderDetail";
	}
 

	/**
	 * @Title: cancelOrder 
	 * @Description:  取消订单(未付款/已付款未接单)
	 * @param    
	 * @return Object  
	 * @author SSY
	 * @date 2018年5月23日 下午2:33:32
	 */
	@RequestMapping(value = "/cancelOrder", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> cancelOrder(String orderID) throws Exception {
		Result<Object> result = new Result<Object>();
		try {
			result = orderServiceImpl.updateCancelOrder(orderID);
		} catch (Exception e) {
			result.setMsg("网络异常,请稍后重试!"); 
			result.setState(Result.STATE_ERROR);
			logger.info("【ERROR】"+DateUtil.getTime()+"取消订单系统执行异常! ");
		}
		return result;
	}

	
	/**
	 * @Title: confirmReceipt 
	 * @Description:  确认收货; 
	 * @param    
	 * @return Object  
	 * @author SSY
	 * @date 2018年5月23日 下午5:33:32
	 */
	@RequestMapping(value = "/confirmReceipt", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> confirmReceipt(String orderID) throws Exception {
		Result<Object> result = new Result<Object>();
		try {
			result = orderServiceImpl.updateFinishOrder(orderID);
		} catch (Exception e) {
			result.setMsg("网络异常,请稍后重试!"); 
			result.setState(Result.STATE_ERROR);
			logger.info("【ERROR】"+DateUtil.getTime()+"订单确认收货系统执行异常! ");
		}
		return result;
	}
	
	/**
	 * @Title: getLogisticInfo 
	 * @Description:  查询用户的第一个物流单号信息;
	 * @param    
	 * @return   result.orderLogistic
	 * @author SSY
	 * @date 2018年5月23日 下午5:33:32
	 */
	@RequestMapping(value = "/getLogisticInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> getLogisticInfo(String orderID) throws Exception {
		Result<Object> result = new Result<Object>();
		try {
			result = orderServiceImpl.getLogisticInfo(orderID);
		} catch (Exception e) {
			result.setMsg("网络异常,请稍后重试!"); 
			result.setState(Result.STATE_ERROR);
			logger.info("【ERROR】"+DateUtil.getTime()+"订单物流查询系统执行异常! ");
		}
		return result;
	}
	

	/**
	 * @Title: getMoreLogisticInfo 
	 * @Description: 查询更多物流信息
	 * @param    
	 * @return   orderLogistic,这是一个集合
	 * @author SSY
	 * @date 2018年5月24日 下午3:13:23
	 */
	@RequestMapping(value = "/getMoreLogisticInfo", produces = "application/json;charset=UTF-8")
	public String getMoreLogisticInfo(ModelMap map, String orderID) throws Exception {
		try {
			Result<Object> moreLogisticInfo = orderServiceImpl.getMoreLogisticInfo(orderID);
			map.put("orderLogistic", JSON.toJSON(moreLogisticInfo.getResults()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("【ERROR】"+DateUtil.getTime()+"订单物流查询系统执行异常! ");
			throw new Exception();
		}  
		return "system/personal/moreLogisticInfo";
	}

	/**
	 * @Title: buyAgain 
	 * @Description: 再次购买----保存订单中商品到购物车;
	 * @param    
	 * @return Object  
	 * @author SSY
	 * @date 2018年5月24日 下午3:16:36
	 */
	@RequestMapping(value = "/buyAgain", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> buyAgain(String orderID) throws Exception {
		Result<Object> result = new Result<Object>();
		try {
			result = shopCartServiceImpl.buyAgain(orderID);// 加入购物车;
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("网络异常,请稍后重试!"); 
			result.setState(Result.STATE_ERROR);
			logger.info("【ERROR】"+DateUtil.getTime()+"订单中心--再次购买系统执行异常! ");
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
