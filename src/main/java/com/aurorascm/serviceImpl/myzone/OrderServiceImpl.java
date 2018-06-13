package com.aurorascm.serviceImpl.myzone;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.LogisticParcel;
import com.aurorascm.entity.OrderGoods;
import com.aurorascm.entity.OrderLogistic;
import com.aurorascm.entity.OrderManage;
import com.aurorascm.entity.OrderPayInfo;
import com.aurorascm.entity.Page;
import com.aurorascm.entity.Result;
import com.aurorascm.service.myzone.OrderService;
import com.aurorascm.util.Const;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.KdniaoTrackQuery;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/**
 * @Title: OrderService.java 
 * @Package com.aurorascm.service.myzone 
 * @Description: 订单中心
 * @author SSY  
 * @date 2018年5月23日 上午10:32:12 
 * @version V1.0
 */
@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private DAO daoSupport;

	/**
	 * @Title: getMyOrderList 
	 * @Description:   订单中心--查询我的个人订单列表;
	 * @param    Page<OrderManage> page, String keyword, String beginTimeFront,String orderStateFront1(逗号拼接字符串),String orderStateFront2
	 * @return List<OrderManage>  
	 * @author SSY
	 * @date 2018年5月23日 上午10:28:56
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderManage> getMyOrderList(Page<OrderManage> page, String keyword, String beginTimeFront, String orderStateFront1,
			String orderStateFront2)throws Exception{
		OrderManage orderManage = new OrderManage();
		orderManage.setOrderType(Const.MY_ORDER_TYPE);//个人订单
		if (Tools.notEmpty(keyword)) {
			orderManage.setSearchWord(keyword.replace(" ", ""));
		}
		if (Tools.notEmpty(beginTimeFront)) {
			orderManage.setBeginTimeFront(beginTimeFront);
			String orderTime = Tools.notEmptys(beginTimeFront) ? DateUtil.getBeforeDayDate(beginTimeFront.replace(" ", "")):null;
			orderManage.setOrderTime(orderTime);
		}
		if (Tools.notEmpty(orderStateFront1)) {
			orderManage.setOrderStateFront1(orderStateFront1);
			String[] orderStates = orderStateFront1.split(",");
			orderManage.setOrderStates(orderStates);
		}else if (Tools.notEmpty(orderStateFront2)) {
			orderManage.setOrderStateFront2(orderStateFront2);
			String[] orderStates = orderStateFront2.split(",");
			orderManage.setOrderStates(orderStates);
		}
		orderManage.setCustomerID(Integer.valueOf(Jurisdiction.getCustomerID()));
		page.setT(orderManage);
		page.setPageSize(Const.PAGE_SIZE_ORDER);
		//查询订单总数;
		int num = (int)daoSupport.findForObject("OrderReadMapper.getOrderNum", orderManage);
		page.setTotalRecord(num);
		List<OrderManage> myOrderIDs = (List<OrderManage>)daoSupport.findForList("OrderReadMapper.getOrderIDPage", page);
		List<OrderManage> myOrderList = null;
		if (null==myOrderIDs||myOrderIDs.size()<=0) {
			myOrderList = new ArrayList<>();
		} else{
			myOrderList = (List<OrderManage>)daoSupport.findForList("OrderReadMapper.getOrderListByIDs", myOrderIDs);
		}
		return myOrderList;
	}
	
	 
	/**
	 * @Title: getPurchaseOrderList 
	 * @Description:   订单中心--查询我的微仓采购订单列表;
	 * @param    Page<OrderManage> page, String keyword, String beginTimeFront,String orderStateFront1(逗号拼接字符串),String orderStateFront2
	 * @return List<OrderManage>  
	 * @author SSY
	 * @date 2018年5月23日 上午10:28:56
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderManage> getPurchaseOrderList(Page<OrderManage> page, String keyword, String beginTimeFront,String orderStateFront1,String orderStateFront2)throws Exception{
		OrderManage orderManage = new OrderManage();
		orderManage.setOrderType(Const.PURCHASE_ORDER_TYPE);//微仓采购订单
		if (Tools.notEmpty(keyword)) {
			orderManage.setSearchWord(keyword.replace(" ", ""));
		}
		if (Tools.notEmpty(beginTimeFront)) {
			orderManage.setBeginTimeFront(beginTimeFront);
			String orderTime = Tools.notEmptys(beginTimeFront) ? DateUtil.getBeforeDayDate(beginTimeFront.replace(" ", "")):null;
			orderManage.setOrderTime(orderTime);
		}
		if (Tools.notEmpty(orderStateFront1)) {
			orderManage.setOrderStateFront1(orderStateFront1);
			String[] orderStates = orderStateFront1.split(",");
			orderManage.setOrderStates(orderStates);
		} else if (Tools.notEmpty(orderStateFront2)) {
			orderManage.setOrderStateFront2(orderStateFront2);
			String[] orderStates = orderStateFront2.split(",");
			orderManage.setOrderStates(orderStates);
		}
		
		orderManage.setCustomerID(Integer.valueOf(Jurisdiction.getCustomerID()));
		page.setT(orderManage);
		page.setPageSize(Const.PAGE_SIZE_ORDER);
		//查询订单总数;
		int num = (int)daoSupport.findForObject("OrderReadMapper.getOrderNum", orderManage);
		page.setTotalRecord(num);
		List<OrderManage> purchaseOrderIDs = (List<OrderManage>)daoSupport.findForList("OrderReadMapper.getOrderIDPage", page);
		List<OrderManage> purchaseOrderList = null;
		if (null==purchaseOrderIDs||purchaseOrderIDs.size()<=0) {
			purchaseOrderList = new ArrayList<>();
		} else{
			purchaseOrderList = (List<OrderManage>)daoSupport.findForList("OrderReadMapper.getOrderListByIDs", purchaseOrderIDs);
		}
		return purchaseOrderList;
	}
	
	/**
	 * @Title: getPurchaseOrderList 
	 * @Description:   订单中心--查询我的微仓销售订单列表;
	 * @param    Page<OrderManage> page, String keyword, String beginTimeFront, String orderStateFront1(逗号拼接字符串),String orderStateFront2
	 * @return List<OrderManage>  
	 * @author SSY
	 * @date 2018年5月23日 上午10:28:56
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderManage> getSellOrderList(Page<OrderManage> page, String keyword, String beginTimeFront, String orderStateFront1,String orderStateFront2)throws Exception{
		OrderManage orderManage = new OrderManage();
		orderManage.setOrderType(Const.SELL_ORDER_TYPE);//微仓采购订单
		if (Tools.notEmpty(keyword)) {
			orderManage.setSearchWord(keyword.replace(" ", ""));
		}
		if (Tools.notEmpty(beginTimeFront)) {
			orderManage.setBeginTimeFront(beginTimeFront);
			String orderTime = Tools.notEmptys(beginTimeFront) ? DateUtil.getBeforeDayDate(beginTimeFront.replace(" ", "")):null;
			orderManage.setOrderTime(orderTime);
		}
		if (Tools.notEmpty(orderStateFront1)) {
			orderManage.setOrderStateFront1(orderStateFront1);
			String[] orderStates = orderStateFront1.split(",");
			orderManage.setOrderStates(orderStates);
		} else if (Tools.notEmpty(orderStateFront2)) {
			orderManage.setOrderStateFront2(orderStateFront2);
			String[] orderStates = orderStateFront2.split(",");
			orderManage.setOrderStates(orderStates);
		}
		
		orderManage.setCustomerID(Integer.valueOf(Jurisdiction.getCustomerID()));
		page.setT(orderManage);
		page.setPageSize(Const.PAGE_SIZE_ORDER);
		//查询订单总数;
		int num = (int)daoSupport.findForObject("OrderReadMapper.getOrderNum", orderManage);
		page.setTotalRecord(num);
		List<OrderManage> sellOrderIDs = (List<OrderManage>)daoSupport.findForList("OrderReadMapper.getOrderIDPage", page);
		List<OrderManage> sellOrderList = null;
		if (null==sellOrderIDs||sellOrderIDs.size()<=0) {
			sellOrderList = new ArrayList<>();
		} else{
			sellOrderList = (List<OrderManage>)daoSupport.findForList("OrderReadMapper.getOrderListByIDs", sellOrderIDs);
		}
		return sellOrderList;
	}
		
	/**
	 * @Title: getOrderNum 
	 * @Description: 查询订单数量
	 * @param     Integer orderType, String orderStateFront(逗号拼接字符串)
	 * @return int  
	 * @author SSY
	 * @date 2018年5月23日 上午11:56:21
	 */
	@Override
	public int getOrderNum(Integer orderType, String orderStateFront) throws Exception {
		OrderManage orderManage = new OrderManage();
		orderManage.setOrderType(orderType); 
		orderManage.setCustomerID(Integer.valueOf(Jurisdiction.getCustomerID()));
		if (Tools.notEmpty(orderStateFront)) {
			String[] orderStates = orderStateFront.split(",");
			orderManage.setOrderStates(orderStates);
		}
		int orderNum = (int) daoSupport.findForObject("OrderReadMapper.getOrderNum", orderManage);
		return orderNum;
	}
	
	
	/**
	 * @Title: getOrder 
	 * @Description: 查询订单详情,查询当前用户该订单号的订单详情
	 * @param    
	 * @return OrderManage  
	 * @author SSY
	 * @date 2018年5月23日 下午2:20:49
	 */
	@Override
	public OrderManage getOrder(String orderID) throws Exception{
		OrderManage orderManage = new OrderManage();
		orderManage.setOrderID(orderID.replace(" ", ""));
		orderManage.setCustomerID(Integer.valueOf(Jurisdiction.getCustomerID()));
		orderManage = (OrderManage) daoSupport.findForObject("OrderReadMapper.getOrder", orderManage);
		return orderManage;
	}
	
	/**
	 * @Title: updateCancelOrder 
	 * @Description: 取消订单
	 * @param    String orderID
	 * @return Result<Object>  
	 * @author SSY
	 * @date 2018年5月23日 下午2:59:32
	 */
	@Override
	public Result<Object> updateCancelOrder(String orderID) throws Exception{
		Result<Object> result = new Result<Object>();
		if (Tools.isEmpty(orderID)) {
			result.setMsg("参数错误！  ");
			result.setState(Result.STATE_ERROR);
			return result;
		}
		OrderManage order = this.getOrder(orderID);
		if (null==order) {
			result.setMsg("参数错误！  ");
			result.setState(Result.STATE_ERROR);
			return result;
		}
		if (order.getOrderType()==Const.SELL_ORDER_TYPE) {
			result.setMsg("微仓订单暂不支持退款!");
			result.setState(Result.STATE_ERROR);
			return result;
		}
		int orderState = order.getOrderState();
		if (orderState>=3) {//接单之后不能取消订单
			result.setMsg("订单状态错误！ 请联系您的专属客服！");
			result.setState(Result.STATE_ERROR);
			return result;
		}
		if (orderState == 1) {                                //1订单待付款状态
			order.setOrderState(3);//改为订单已取消状态
			order.setCancelTime(DateUtil.getTime());
			order.setCancelOperator(Jurisdiction.getCustomerEmail());
			daoSupport.update("OrderWriteMapper.updateOrderState", order);
			result.setState(Result.STATE_SUCCESS);
			return result;
		}else if (orderState == 2) {                          //2订单已付款状态
			order.setOrderState(4);//改为订单待退款状态
			order.setCancelTime(DateUtil.getTime());
			order.setCancelOperator(Jurisdiction.getCustomerEmail());
			daoSupport.update("OrderWriteMapper.updateOrderState", order);
			result.setState(Result.STATE_SUCCESS);
			return result;
		} else{
			result.setMsg("订单状态错误！ 请联系您的专属客服！");
			result.setState(Result.STATE_ERROR);
			return result;
		}
	}
	
	/**
	 * @Title: updateFinishOrder 
	 * @Description:  确认收货;
	 * @param    String orderID
	 * @return Result<Object>  
	 * @author SSY
	 * @date 2018年5月23日 下午6:27:23
	 */
	public Result<Object> updateFinishOrder(String orderID) throws Exception{
		Result<Object> result = new Result<Object>();
		if (Tools.isEmpty(orderID)) {
			result.setMsg("参数错误！  ");
			result.setState(Result.STATE_ERROR);
			return result;
		}
		OrderManage order = this.getOrder(orderID);
		if (null==order) {
			result.setMsg("参数错误！  ");
			result.setState(Result.STATE_ERROR);
			return result;
		}
		int orderState = order.getOrderState();
		if (orderState != 7) {  
			result.setMsg("订单状态错误！ 请联系您的专属客服！");
			result.setState(Result.STATE_ERROR);
			return result;
		}
		order.setOrderState(8);//改为订单待退款状态
		order.setFinishTime(DateUtil.getTime());
		order.setFinishOperator(Jurisdiction.getCustomerEmail());
		daoSupport.update("OrderWriteMapper.updateOrderState", order);
		result.setState(Result.STATE_SUCCESS);
		return result;
	}
	
	/**
	 * @Title: getLogisticInfo 
	 * @Description:  查询订单的第一条物流信息;
	 * @param    
	 * @return Result<Object>  
	 * @author SSY
	 * @date 2018年5月24日 上午11:14:42
	 */
	@Override
	public Result<Object> getLogisticInfo(String orderID) throws Exception {
		Result<Object> result = new Result<Object>();
		OrderLogistic orderLogistic = new OrderLogistic();
		orderLogistic.setOrderID(orderID);
		if (Tools.isEmpty(orderID)) {
			result.setMsg("参数错误！  ");
			result.setState(Result.STATE_ERROR);
			return result;
		}
		OrderManage order = this.getOrder(orderID);
		if (null==order) {
			result.setMsg("参数错误！  ");
			result.setState(Result.STATE_ERROR);
			return result;
		}
		if (order.getOrderState()<7) {
			result.setMsg("暂未发货！  ");
			result.setState(Result.STATE_ERROR);
			return result;
		}
		//1.查询订单包裹总数;
		List<OrderGoods> orderGoodsList = order.getOrderGoods();
		int parcelNum = 0;
		for (Iterator iterator = orderGoodsList.iterator(); iterator.hasNext();) {
			OrderGoods orderGoods = (OrderGoods) iterator.next();
			if (orderGoods.getLogisticsType()==1) {// 查找需要物流的
				String logisticsNumbers = orderGoods.getLogisticsNumber();
				if(Tools.notEmpty(logisticsNumbers)){
					String[] split = logisticsNumbers.split(",");
					parcelNum += split.length;
				}
			}else{
				orderGoodsList.remove(orderGoods);//目的便于下面取出第一个快递单号
			}
		}
		orderLogistic.setParcelNum(parcelNum);
		//2.查询第一个包裹的物流轨迹
		if (orderGoodsList.size()==0) {
			result.setMsg("无需物流!");
			result.setState(Result.STATE_ERROR);
			return result;
		}
		OrderGoods orderGoods = orderGoodsList.get(0);
		String logisticsCompany = orderGoods.getLogisticsCompany();
		String logisticsCompanyCode = orderGoods.getLogisticsCompanyCode();
		String logisticsNumber = orderGoods.getLogisticsNumber().split(",")[0];
		String logisticTrack = KdniaoTrackQuery.getOrderTracesByJson(logisticsCompanyCode, logisticsNumber);
		
		List<LogisticParcel> logisticParcels = new ArrayList<>();
		LogisticParcel logisticParcel = new LogisticParcel();
		logisticParcel.setExpCode(logisticsCompanyCode);
		logisticParcel.setExpCompany(logisticsCompany);
		logisticParcel.setExpNo(logisticsNumber);
		logisticParcel.setLogisticTrack(logisticTrack);
		logisticParcels.add(logisticParcel);
		orderLogistic.setLogisticParcels(logisticParcels);
		
		result.setResult(orderLogistic);
		result.setState(Result.STATE_SUCCESS);
		return result;
	}
	
	/**
	 * @Title: getLogisticInfo 
	 * @Description:  根据订单id和用户id去查询订单中的商品信息
	 * 				根据物流单号查询物流信息;只查询前2个商品的物流信息,返回该订单所有的运单号;
	 * @param    
	 * @return Result<Object>  
	 * @author SSY
	 * @date 2018年5月24日 上午11:14:42
	 */
	@Override
	public Result<Object> getMoreLogisticInfo(String orderID) throws Exception {
		Result<Object> result = new Result<Object>();
		List<Object> results = new ArrayList<>(2);
		OrderLogistic orderLogistic = new OrderLogistic();
		orderLogistic.setOrderID(orderID);
		if (Tools.isEmpty(orderID)) {
			result.setMsg("参数错误！  ");
			result.setState(Result.STATE_ERROR);
			return result;
		}
		OrderManage order = this.getOrder(orderID);
		if (null==order) {
			result.setMsg("参数错误！  ");
			result.setState(Result.STATE_ERROR);
			return result;
		}
		results.add(order);
		List<OrderGoods> orderGoodsList = order.getOrderGoods();
		List<LogisticParcel> logisticParcels = new ArrayList<>();
		for (Iterator iterator = orderGoodsList.iterator(); iterator.hasNext();) {
			OrderGoods orderGoods = (OrderGoods) iterator.next();
			if (orderGoods.getLogisticsType()==1) {// 查找需要物流的
				String logisticsNumbers = orderGoods.getLogisticsNumber();
				if(Tools.notEmpty(logisticsNumbers)){
					String logisticsNumber = logisticsNumbers.split(",")[0];
					String logisticsCompany = orderGoods.getLogisticsCompany();
					String logisticsCompanyCode = orderGoods.getLogisticsCompanyCode();
					
					LogisticParcel logisticParcel = new LogisticParcel();
					logisticParcel.setExpCode(logisticsCompanyCode);
					logisticParcel.setExpCompany(logisticsCompany);
					logisticParcel.setExpNo(logisticsNumber);
					
					if (logisticParcels.size()<2) {//只查询两个
						String logisticTrack = KdniaoTrackQuery.getOrderTracesByJson(logisticsCompanyCode, logisticsNumber);
						logisticParcel.setLogisticTrack(logisticTrack);
						break;
					}
					logisticParcels.add(logisticParcel);
				}
			} 
		}
		orderLogistic.setLogisticParcels(logisticParcels);
		results.add(orderLogistic);
		result.setResults(results);
		result.setState(Result.STATE_SUCCESS);
		return result;
	}
	 
	
	/**根据订单ID获取订单应付金额
	 * @param orderID
	 * @return
	 * @throws Exception
	 */
	@Override
	public BigDecimal getShouldPayByOID(String orderID) throws Exception {
		return (BigDecimal) daoSupport.findForObject("OrderReadMapper.getShouldPayByOID", orderID);
	}
	
	/**
	 * @Title: getOrderPayInfo 
	 * @Description: 订单付款页--商品分组
	 * @param    
	 * @return OrderManage  
	 * @author SSY
	 * @date 2018年5月23日 下午2:20:49
	 */
	@Override
	public OrderPayInfo getOrderPayInfo(String orderID) throws Exception{
		OrderManage orderManage = new OrderManage();
		orderManage.setOrderID(orderID.replace(" ", ""));
		orderManage.setCustomerID(Integer.valueOf(Jurisdiction.getCustomerID()));
		orderManage = (OrderManage) daoSupport.findForObject("OrderReadMapper.getOrder", orderManage);
		List<OrderGoods> orderGoodsList = orderManage.getOrderGoods();
		List<OrderGoods> bondedGoods = new ArrayList<OrderGoods>();//保税仓
		List<OrderGoods> generalGoods = new ArrayList<OrderGoods>();//一般贸易
		List<OrderGoods> directGoods = new ArrayList<OrderGoods>();//海外直邮
		for (Iterator iterator = orderGoodsList.iterator(); iterator.hasNext();) {
			OrderGoods orderGoods = (OrderGoods) iterator.next();
			int tradeType = orderGoods.getTradeType();
			switch (tradeType) {
			case Const.BONDED_TRADE_TYPE://保税仓
				bondedGoods.add(orderGoods);
				break;
			case Const.DIRECT_TRADE_TYPE://海外直邮
				directGoods.add(orderGoods);
				break;
			case Const.GENERAL_TRADE_TYPE://一般贸易
				generalGoods.add(orderGoods);
				break;
			default:
				break;
			}
			
		}
		OrderPayInfo orderPayInfo = new OrderPayInfo();
		orderPayInfo.setAddress(orderManage.getShipAddress());
		orderPayInfo.setConsignee(orderManage.getConsignee());
		orderPayInfo.setConsigneeMobile(orderManage.getConsigneeMobile());
		orderPayInfo.setOrderID(orderID);
		orderPayInfo.setPayType(orderManage.getPayType());
		orderPayInfo.setOrderType(orderManage.getOrderType());
		BigDecimal shouldPayment = orderManage.getShouldPayment();
		orderPayInfo.setShouldPayment(shouldPayment);
		orderPayInfo.setBondedGoods(bondedGoods);
		orderPayInfo.setDirectGoods(directGoods);
		orderPayInfo.setGeneralGoods(generalGoods);
		return orderPayInfo;
	}
	
	
	
	
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
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getOrderIDByS3(Page page) throws Exception {
		return (List<PageData>) daoSupport.findForList("OrderReadMapper.getOrderIDByS3", page);
	}

	/**客户个人中心/我的订单 --根据条件获取订单数量
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getOrderIDNumByS3(Page page) throws Exception {
		return (int) daoSupport.findForObject("OrderReadMapper.getOrderIDNumByS3", page);
	}

	/**根据订单单号获取订单包含商品
	 * @param orderID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getOrderGoodsByOID(String orderID) throws Exception {
		return (List<PageData>) daoSupport.findForList("OrderReadMapper.getOrderGoodsByOID", orderID);
	}
	
	/**根据订单单号获取订单信息（可能有多个订单号）
	 * @param ArrayOrderID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getOrderByOID(String[] ArrayOrderID) throws Exception {
		return (List<PageData>) daoSupport.findForList("OrderReadMapper.getOrderByOID", ArrayOrderID);
	}

	/**根据订单状态获取订单数量
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getONumByState(PageData pd) throws Exception {
		return (int) daoSupport.findForObject("OrderReadMapper.getONumByState", pd);
	}

	/**客户个人中心/我的订单 --根据条件（orderID/customerID）获取订单状态
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getOrderState(PageData pd) throws Exception {
		return (int) daoSupport.findForObject("OrderReadMapper.getOrderState", pd);
	}
	
	/**根据订单ID获取订单状态
	 * @param orderID
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getOrderStateByOID(String orderID) throws Exception {
		return (int) daoSupport.findForObject("OrderReadMapper.getOrderStateByOID", orderID);
	}



	/**根据订单ID更新单个订单支付信息;更新库存
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public void updateOrderPayInfo(PageData pd)throws Exception{
		//更新支付信息
		daoSupport.update("OrderWriteMapper.updateOrderPayInfo", pd);
		String orderID = (String) pd.get("orderID");
		@SuppressWarnings("unchecked")
		List<PageData> orderGoods = (List<PageData>)daoSupport.findForList("OrderReadMapper.getOrderGoodsForUStock", orderID);
		for(PageData og : orderGoods){
			int buyNum = (int) og.get("goods_num");
			int goodsStock1 = (int) og.get("goods_stock");
			int goodsStock = goodsStock1 - buyNum;
			if (goodsStock < 0) {
				goodsStock = 0;
			}
			String goodsID = og.getString("goods_id");
			PageData pdus = new  PageData();
			pdus.put("goodsStock", goodsStock);
			pdus.put("goodsID", goodsID);
			daoSupport.update("GoodsWriteMapper.updateGoodsStockAfterPay", pdus);//更新库存
		}
	}
 
	

	


	/**根据订单ID获取单个订单管理信息
	 * @param orderID
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageData getOrderMByOID(String orderID) throws Exception {
		return (PageData) daoSupport.findForObject("OrderReadMapper.getOrderMByOID", orderID);
	}

	

	
}
