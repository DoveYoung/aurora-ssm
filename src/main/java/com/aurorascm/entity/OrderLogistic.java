package com.aurorascm.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: OrderLogistic.java 
 * @Package com.aurorascm.entity 
 * @Description: 订单物流信息详情
 * @author SSY  
 * @date 2018年5月24日 上午10:08:23 
 * @version V1.0
 */
public class OrderLogistic implements Serializable{
	
	 
	private static final long serialVersionUID = 1L;
	
	/**
	 * 订单号
	 */
	private String orderID;
	
	/**
	 * 包裹数量
	 */
	private Integer parcelNum;
	
	/**
	 * 包裹物流信息
	 */
	private List<LogisticParcel> logisticParcels;

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public Integer getParcelNum() {
		return parcelNum;
	}

	public void setParcelNum(Integer parcelNum) {
		this.parcelNum = parcelNum;
	}

	public List<LogisticParcel> getLogisticParcels() {
		return logisticParcels;
	}

	public void setLogisticParcels(List<LogisticParcel> logisticParcels) {
		this.logisticParcels = logisticParcels;
	}

	@Override
	public String toString() {
		return "OrderLogistic [orderID=" + orderID + ", parcelNum=" + parcelNum + ", logisticParcels=" + logisticParcels
				+ "]";
	}
	
	
	
	
}
