package com.aurorascm.service.myzone;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.aurorascm.entity.OrderManage;
import com.aurorascm.entity.OrderPayInfo;
import com.aurorascm.entity.Page;
import com.aurorascm.entity.Result;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;

/**
 * @Title: OrderService.java 
 * @Package com.aurorascm.service.myzone 
 * @Description: 订单中心
 * @author SSY  
 * @date 2018年5月23日 上午10:32:12 
 * @version V1.0
 */
public interface OrderService {
	
	/**
	 * @Title: getMyOrderList 
	 * @Description:   订单中心--查询我的个人订单列表;
	 * @param    Page<OrderManage> page, String keyword, String beginTimeFront,String orderStateFront1(逗号拼接字符串),String orderStateFront2
	 * @return List<OrderManage>  
	 * @author SSY
	 * @date 2018年5月23日 上午10:28:56
	 */
	public List<OrderManage> getMyOrderList(Page<OrderManage> page, String keyword, String beginTimeFront, String orderStateFront1,String orderStateFront2)throws Exception;
	
	/**
	 * @Title: getPurchaseOrderList 
	 * @Description:   订单中心--查询我的微仓采购订单列表;
	 * @param    Page<OrderManage> page, String keyword, String beginTimeFront,String orderStateFront1(逗号拼接字符串),String orderStateFront2
	 * @return List<OrderManage>  
	 * @author SSY
	 * @date 2018年5月23日 上午10:28:56
	 */
	public List<OrderManage> getPurchaseOrderList(Page<OrderManage> page, String keyword, String beginTimeFront, String orderStateFront1,String orderStateFront2)throws Exception;
		
		
	/**
	 * @Title: getPurchaseOrderList 
	 * @Description:   订单中心--查询我的微仓销售订单列表;
	 * @param    Page<OrderManage> page, String keyword, String beginTimeFront, String orderStateFront1(逗号拼接字符串),String orderStateFront2
	 * @return List<OrderManage>  
	 * @author SSY
	 * @date 2018年5月23日 上午10:28:56
	 */
	public List<OrderManage> getSellOrderList(Page<OrderManage> page, String keyword, String beginTimeFront,String orderStateFront1,String orderStateFront2)throws Exception;
			
		 
	/**
	 * @Title: getOrderNum 
	 * @Description: 查询订单数量
	 * @param    Integer orderType, String orderStateFront(逗号拼接字符串)
	 * @return int  
	 * @author SSY
	 * @date 2018年5月23日 上午11:56:21
	 */
	public int getOrderNum(Integer orderType, String orderStateFront) throws Exception;
	
	/**
	 * @Title: getOrder 
	 * @Description: 查询订单详情
	 * @param    
	 * @return OrderManage  
	 * @author SSY
	 * @date 2018年5月23日 下午2:20:49
	 */
	public OrderManage getOrder(String orderID) throws Exception;

	/**
	 * @Title: updateCancelOrder 
	 * @Description: 取消订单
	 * @param    String orderID
	 * @return Result<Object>  
	 * @author SSY
	 * @date 2018年5月23日 下午2:59:32
	 */
	public Result<Object> updateCancelOrder(String orderID) throws Exception;
	

	/**
	 * @Title: updateFinishOrder 
	 * @Description:  确认收货;
	 * @param    String orderID
	 * @return Result<Object>  
	 * @author SSY
	 * @date 2018年5月23日 下午6:27:23
	 */
	public Result<Object> updateFinishOrder(String orderID) throws Exception;
	
	/**
	 * @Title: getLogisticInfo 
	 * @Description:  查询订单的第一条物流信息;
	 * @param    
	 * @return Result<Object>  
	 * @author SSY
	 * @date 2018年5月24日 上午11:14:42
	 */
	public Result<Object> getLogisticInfo(String orderID) throws Exception;
	
	/**
	 * @Title: getLogisticInfo 
	 * @Description:  根据订单id和用户id去查询订单中的商品信息
	 * 				根据物流单号查询物流信息;只查询前2个商品的物流信息,返回该订单所有的运单号;
	 * @param    
	 * @return Result<Object>  
	 * @author SSY
	 * @date 2018年5月24日 上午11:14:42
	 */
	public Result<Object> getMoreLogisticInfo(String orderID) throws Exception;
	
	/**根据订单ID获取订单应付金额
	 * @param orderID
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getShouldPayByOID(String orderID) throws Exception;

	/**
	 * @Title: getOrderPayInfo 
	 * @Description: 订单付款信息查询
	 * @param    
	 * @return OrderPayInfo  
	 * @author SSY
	 * @date 2018年5月29日 下午3:25:17
	 */
	public OrderPayInfo getOrderPayInfo(String orderID) throws Exception;

	
	//TODO:
	
	
	/**-------------------------------------------------------------------------------------------------------------------
	 * ----------------------------------------------------分割线---------------------------------------------------------
	 * ------------------------------------------------------------------------------------------------------------
	 */
	
	
	/**客户个人中心/我的订单 --根据条件获取订单ID
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getOrderIDByS3(Page page) throws Exception;
	
	/**客户个人中心/我的订单 --根据条件获取订单数量
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public int getOrderIDNumByS3(Page page) throws Exception;
	
	/**根据订单单号获取订单包含商品
	 * @param orderID
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getOrderGoodsByOID(String orderID) throws Exception;
	
	/**根据订单状态获取订单数量
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public int getONumByState(PageData pd) throws Exception;
	
	/**客户个人中心/我的订单 --根据条件获取订单状态
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public int getOrderState(PageData pd) throws Exception;
	
	/**根据订单ID获取订单状态
	 * @param orderID
	 * @return
	 * @throws Exception
	 */
	public int getOrderStateByOID(String orderID) throws Exception;
	
 
	
	/**根据订单ID更新单个订单支付信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public void updateOrderPayInfo(PageData pd)throws Exception;
	
 
 
	
	/**根据订单单号获取订单信息（可能有多个订单号）
	 * @param ArrayOrderID
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getOrderByOID(String[] ArrayOrderID) throws Exception;

	
	/**根据订单ID获取单个订单管理信息
	 * @param orderID
	 * @return
	 * @throws Exception
	 */
	public PageData getOrderMByOID(String orderID) throws Exception;

	

	
	 

	
}
