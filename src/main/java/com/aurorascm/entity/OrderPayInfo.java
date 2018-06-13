package com.aurorascm.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Title: OrderPayInfo.java 
 * @Package com.aurorascm.entity 
 * @Description: 订单付款信息;
 * @author SSY  
 * @date 2018年5月29日 上午9:51:16 
 * @version V1.0
 */
public class OrderPayInfo implements Serializable{
	
	 
	private static final long serialVersionUID = 1L;
	
	/**
	 * 订单号
	 */
	private String orderID;
	
	/**
	 * 付款类型
	 */
	private Integer payType;
	
	
	/**
	 * 订单类型 ：1微仓采购订单 ；2个人订单 3.微仓销售订单
	 */
	private Integer orderType;
	
	/**
	 * 应付款
	 */
	private BigDecimal shouldPayment;
	
	/**
	 * 收货地址
	 */
	private String address;
	
	/**
	 * 收货人
	 */
	private String consignee;
	
	/**
	 * 收货人手机号
	 */
	private String consigneeMobile;
	
	/**
	 * 保税仓发货商品名称列表
	 */
	private List<OrderGoods> bondedGoods;
	
	/**
	 * 海外直邮商品名称列表
	 */
	private List<OrderGoods> directGoods;
	
	/**
	 * 一般贸易商品名称列表
	 */
	private List<OrderGoods> generalGoods;

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public BigDecimal getShouldPayment() {
		return shouldPayment;
	}

	public void setShouldPayment(BigDecimal shouldPayment) {
		this.shouldPayment = shouldPayment;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<OrderGoods> getBondedGoods() {
		return bondedGoods;
	}

	public void setBondedGoods(List<OrderGoods> bondedGoods) {
		this.bondedGoods = bondedGoods;
	}

	public List<OrderGoods> getDirectGoods() {
		return directGoods;
	}

	public void setDirectGoods(List<OrderGoods> directGoods) {
		this.directGoods = directGoods;
	}

	public List<OrderGoods> getGeneralGoods() {
		return generalGoods;
	}

	public void setGeneralGoods(List<OrderGoods> generalGoods) {
		this.generalGoods = generalGoods;
	}
	
	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsigneeMobile() {
		return consigneeMobile;
	}

	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	@Override
	public String toString() {
		return "OrderPayInfo [orderID=" + orderID + ", shouldPayment=" + shouldPayment + ", address=" + address
				+ ", bondedGoods=" + bondedGoods + ", directGoods=" + directGoods + ", generalGoods=" + generalGoods
				+ "]";
	}
	
	
	
	 
}
