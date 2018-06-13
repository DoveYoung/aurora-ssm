package com.aurorascm.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.aurorascm.util.Const;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Tools;

public class OrderManage {
	/**
	 * 订单id
	 */
	private String orderID;
	/**
	 * 订单等级;1北极光订单;2北极光客户订单
	 */
	private int orderLvel;
	/**
	 *  订单类型,1收货地址是微仓的订单2个人订单; 3微仓销售订单;
	 */
	private int orderType;
	
	/**
	 * 所属客户名称
	 */
	private String customerName;
	/**
	 * 所属客户id
	 */
	private int customerID;
	/**
	 * 所属销售id
	 */
	private int salesManager;
	/**
	 * 收货人名称
	 */
	private String consignee;
	/**
	 * 邮寄地址;
	 */
	private String shipAddress;
	/**
	 * 收货人联系电话;
	 */
	private String consigneeMobile;
	/**
	 * 收货人身份证号码;
	 */
	private String consigneeCard;

	/**
	 * 全款1定金2
	 */
	private int payType;
	/**
	 * 货币单位
	 */
	private String currencyUnit;
	/**
	 * 实际付款金额;
	 */
	private BigDecimal payMoney;
	/**
	 * 应付金额(按定金比例计算)
	 */
	private BigDecimal shouldPayment;
	/**
	 * 总金额
	 */
	private BigDecimal totalMoney;
	/**
	 * 退款金额
	 */
	private BigDecimal refundMoney;
	/**
	 * 订单总邮费
	 */
	private BigDecimal postage;
	/**
	 * 付款时间
	 */
	private String payTime;
	/**
	 * 付款的方式 1支付宝2微信3快钱;
	 */
	private int payPath;
	/**
	 * 支付流水号
	 */
	private String tradeNo;
	/**
	 * 支付时提交的订单号
	 */
	private String outTradeNo;
	/**
	 * 下单时间
	 */
	private String orderTime;
	/**
	 * 订单状态
	 */
	private int orderState;
	
	/**
	 * 订单状态条件查询
	 */
	private String[] orderStates;
	
	/**
	 * 订单状态回显,前端显示Tab1
	 */
	private String orderStateFront1;
	
	/**
	 * 订单状态回显,前端显示Tab2
	 */
	private String orderStateFront2;
	
	/**
	 * 下单时间前端显示,29表示一个月前···
	 */
	private String beginTimeFront;
	
	
	/**
	 * 用户备注
	 */
	private String customerRemark;
	/**
	 * 发货时间;
	 */
	private String sendTime;
	/**
	 * 退款时间
	 */
	private String refundTime;
	/**
	 * 取消订单时间
	 */
	private String cancelTime;
	/**
	 * 取消订单人
	 */
	private String cancelOperator;
	/**
	 * 完成时间
	 */
	private String finishTime;
	/**
	 * 确认收货人;
	 */
	private String finishOperator;
	
	/**
	 * 贸易类型;
	 */
	private Integer tradeType;
	/**
	 * 账单号;
	 */
	private String billNum;
	 
	/**
	 * 税费;
	 */
	private BigDecimal tax;
	
	/**
	 * 订单查询关键词
	 */
	private String searchWord;
	
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	/**
	 * 订单商品;一对多;
	 */
	private List<OrderGoods> orderGoods;
	/**
	 * 获取: orderID
	 * @return the orderID
	 */
	public String getOrderID() {
		return orderID;
	}
	/**
	 * 设置: orderID
	 * @param orderID the orderID to set
	 */
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	/**
	 * 获取: orderLvel
	 * @return the orderLvel
	 */
	public int getOrderLvel() {
		return orderLvel;
	}
	/**
	 * 设置: orderLvel
	 * @param orderLvel the orderLvel to set
	 */
	public void setOrderLvel(int orderLvel) {
		this.orderLvel = orderLvel;
	}
	/**
	 * 获取: customerName
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * 设置: customerName
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * 获取: customerID
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}
	/**
	 * 设置: customerID
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	/**
	 * 获取: salesManager
	 * @return the salesManager
	 */
	public int getSalesManager() {
		return salesManager;
	}
	/**
	 * 设置: salesManager
	 * @param salesManager the salesManager to set
	 */
	public void setSalesManager(int salesManager) {
		this.salesManager = salesManager;
	}
	/**
	 * 获取: consignee
	 * @return the consignee
	 */
	public String getConsignee() {
		return consignee;
	}
	/**
	 * 设置: consignee
	 * @param consignee the consignee to set
	 */
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	/**
	 * 获取: shipAddress
	 * @return the shipAddress
	 */
	public String getShipAddress() {
		return shipAddress;
	}
	/**
	 * 设置: shipAddress
	 * @param shipAddress the shipAddress to set
	 */
	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}
	/**
	 * 获取: consigneeMobile
	 * @return the consigneeMobile
	 */
	public String getConsigneeMobile() {
		return consigneeMobile;
	}
	/**
	 * 设置: consigneeMobile
	 * @param consigneeMobile the consigneeMobile to set
	 */
	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}
	/**
	 * 获取: consigneeCard
	 * @return the consigneeCard
	 */
	public String getConsigneeCard() {
		return consigneeCard;
	}
	/**
	 * 设置: consigneeCard
	 * @param consigneeCard the consigneeCard to set
	 */
	public void setConsigneeCard(String consigneeCard) {
		this.consigneeCard = consigneeCard;
	}
	/**
	 * 获取: orderType
	 * @return the orderType
	 */
	public int getOrderType() {
		return orderType;
	}
	/**
	 * 设置: orderType
	 * @param orderType the orderType to set
	 */
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	/**
	 * 获取: payType
	 * @return the payType
	 */
	public int getPayType() {
		return payType;
	}
	/**
	 * 设置: payType
	 * @param payType the payType to set
	 */
	public void setPayType(int payType) {
		this.payType = payType;
	}
	/**
	 * 获取: currencyUnit
	 * @return the currencyUnit
	 */
	public String getCurrencyUnit() {
		return currencyUnit;
	}
	/**
	 * 设置: currencyUnit
	 * @param currencyUnit the currencyUnit to set
	 */
	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit;
	}
	/**
	 * 获取: payMoney
	 * @return the payMoney
	 */
	public BigDecimal getPayMoney() {
		return payMoney;
	}
	/**
	 * 设置: payMoney
	 * @param payMoney the payMoney to set
	 */
	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}
	/**
	 * 获取: shouldPayment
	 * @return the shouldPayment
	 */
	public BigDecimal getShouldPayment() {
		return shouldPayment;
	}
	/**
	 * 设置: shouldPayment
	 * @param shouldPayment the shouldPayment to set
	 */
	public void setShouldPayment(BigDecimal shouldPayment) {
		this.shouldPayment = shouldPayment;
	}
	/**
	 * 获取: postage
	 * @return the postage
	 */
	public BigDecimal getPostage() {
		return postage;
	}
	/**
	 * 设置: postage
	 * @param postage the postage to set
	 */
	public void setPostage(BigDecimal postage) {
		this.postage = postage;
	}
	/**
	 * 获取: payTime
	 * @return the payTime
	 */
	public String getPayTime() {
		return payTime;
	}
	/**
	 * 设置: payTime
	 * @param payTime the payTime to set
	 */
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	/**
	 * 获取: payPath
	 * @return the payPath
	 */
	public int getPayPath() {
		return payPath;
	}
	/**
	 * 设置: payPath
	 * @param payPath the payPath to set
	 */
	public void setPayPath(int payPath) {
		this.payPath = payPath;
	}
	/**
	 * 获取: tradeNo
	 * @return the tradeNo
	 */
	public String getTradeNo() {
		return tradeNo;
	}
	/**
	 * 设置: tradeNo
	 * @param tradeNo the tradeNo to set
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	/**
	 * 获取: outTradeNo
	 * @return the outTradeNo
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}
	/**
	 * 设置: outTradeNo
	 * @param outTradeNo the outTradeNo to set
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	/**
	 * 获取: orderTime
	 * @return the orderTime
	 */
	public String getOrderTime() {
		return orderTime;
	}
	/**
	 * 设置: orderTime
	 * @param orderTime the orderTime to set
	 */
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	/**
	 * 获取: orderState
	 * @return the orderState
	 */
	public int getOrderState() {
		return orderState;
	}
	/**
	 * 设置: orderState
	 * @param orderState the orderState to set
	 */
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	/**
	 * 获取: customerRemark
	 * @return the customerRemark
	 */
	public String getCustomerRemark() {
		return customerRemark;
	}
	/**
	 * 设置: customerRemark
	 * @param customerRemark the customerRemark to set
	 */
	public void setCustomerRemark(String customerRemark) {
		this.customerRemark = customerRemark;
	}
	/**
	 * 获取: totalMoney
	 * @return the totalMoney
	 */
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	/**
	 * 设置: totalMoney
	 * @param totalMoney the totalMoney to set
	 */
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	/**
	 * 获取: refundMoney
	 * @return the refundMoney
	 */
	public BigDecimal getRefundMoney() {
		return refundMoney;
	}
	/**
	 * 设置: refundMoney
	 * @param refundMoney the refundMoney to set
	 */
	public void setRefundMoney(BigDecimal refundMoney) {
		this.refundMoney = refundMoney;
	}
	/**
	 * 获取: refundTime
	 * @return the refundTime
	 */
	public String getRefundTime() {
		return refundTime;
	}
	/**
	 * 设置: refundTime
	 * @param refundTime the refundTime to set
	 */
	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}
	/**
	 * 获取: finishTime
	 * @return the finishTime
	 */
	public String getFinishTime() {
		return finishTime;
	}
	/**
	 * 设置: finishTime
	 * @param finishTime the finishTime to set
	 */
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	/**
	 * 获取: sendTime
	 * @return the sendTime
	 */
	public String getSendTime() {
		return sendTime;
	}
	/**
	 * 设置: sendTime
	 * @param sendTime the sendTime to set
	 */
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	/**
	 * 获取: orderGoods
	 * @return the orderGoods
	 */
	public List<OrderGoods> getOrderGoods() {
		return orderGoods;
	}
	/**
	 * 设置: orderGoods
	 * @param orderGoods the orderGoods to set
	 */
	public void setOrderGoods(List<OrderGoods> orderGoods) {
		this.orderGoods = orderGoods;
	}
 
	/**
	 * 获取: cancelTime
	 * @return the cancelTime
	 */
	public String getCancelTime() {
		return cancelTime;
	}
	/**
	 * 设置: cancelTime
	 * @param cancelTime the cancelTime to set
	 */
	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}
	/**
	 * 获取: cancelOperator
	 * @return the cancelOperator
	 */
	public String getCancelOperator() {
		return cancelOperator;
	}
	/**
	 * 设置: cancelOperator
	 * @param cancelOperator the cancelOperator to set
	 */
	public void setCancelOperator(String cancelOperator) {
		this.cancelOperator = cancelOperator;
	}
	/**
	 * 获取: finishOperator
	 * @return the finishOperator
	 */
	public String getFinishOperator() {
		return finishOperator;
	}
	/**
	 * 设置: finishOperator
	 * @param finishOperator the finishOperator to set
	 */
	public void setFinishOperator(String finishOperator) {
		this.finishOperator = finishOperator;
	}
	/**
	 * 获取: tradeType
	 * @return the tradeType
	 */
	public Integer getTradeType() {
		return tradeType;
	}
	/**
	 * 设置: tradeType
	 * @param tradeType the tradeType to set
	 */
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}
	public String getBillNum() {
		return billNum;
	}
	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}
	public BigDecimal getTax() {
		if (shouldPayment!=null&&postage!=null) {
			tax = shouldPayment.subtract(postage).multiply(Const.TAX_RATE).setScale(2, BigDecimal.ROUND_HALF_UP);//四舍五入,保留两位
		}
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public String[] getOrderStates() {
		return orderStates;
	}
	public void setOrderStates(String[] orderStates) {
		this.orderStates = orderStates;
	}
	
	public String getOrderStateFront1() {
		return orderStateFront1;
	}
	public void setOrderStateFront1(String orderStateFront1) {
		this.orderStateFront1 = orderStateFront1;
	}
	
	public String getOrderStateFront2() {
		return orderStateFront2;
	}
	public void setOrderStateFront2(String orderStateFront2) {
		this.orderStateFront2 = orderStateFront2;
	}
	
	public String getBeginTimeFront() {
		return beginTimeFront;
	}
	public void setBeginTimeFront(String beginTimeFront) {
		this.beginTimeFront = beginTimeFront;
	}
	
	@Override
	public String toString() {
		return "OrderManage [orderID=" + orderID + ", orderLvel=" + orderLvel + ", orderType=" + orderType
				+ ", customerName=" + customerName + ", customerID=" + customerID + ", salesManager=" + salesManager
				+ ", consignee=" + consignee + ", shipAddress=" + shipAddress + ", consigneeMobile=" + consigneeMobile
				+ ", consigneeCard=" + consigneeCard + ", payType=" + payType + ", currencyUnit=" + currencyUnit
				+ ", payMoney=" + payMoney + ", shouldPayment=" + shouldPayment + ", totalMoney=" + totalMoney
				+ ", refundMoney=" + refundMoney + ", postage=" + postage + ", payTime=" + payTime + ", payPath="
				+ payPath + ", tradeNo=" + tradeNo + ", outTradeNo=" + outTradeNo + ", orderTime=" + orderTime
				+ ", orderState=" + orderState + ", orderStates=" + Arrays.toString(orderStates) + ", customerRemark="
				+ customerRemark + ", sendTime=" + sendTime + ", refundTime=" + refundTime + ", cancelTime="
				+ cancelTime + ", cancelOperator=" + cancelOperator + ", finishTime=" + finishTime + ", finishOperator="
				+ finishOperator + ", tradeType=" + tradeType + ", billNum=" + billNum + ", tax=" + tax
				+ ", searchWord=" + searchWord + ", orderGoods=" + orderGoods + "]";
	}
	
	
}
