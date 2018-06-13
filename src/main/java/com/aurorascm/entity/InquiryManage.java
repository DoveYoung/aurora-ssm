package com.aurorascm.entity;

import java.util.List;

/**
 * 询价单实体类
 * @author SSY 2018-1-4
 */
public class InquiryManage {

	/**
	 * 询价单id
	 */
	private String inquiryID;
	/**
	 * 询价单状态
	 */
	private Integer inquiryState;
	/**
	 * 询价时间(询价单创建时间)
	 */
	private String inquiryTime;
	/**
	 * 询价专属销售id
	 */
	private Integer salesManager;
	/**
	 * 报价人
	 */
	private String offerer;
	/**
	 * 报价时间
	 */
	private String offerTime;
	/**
	 * 询价客户ID
	 */
	private Integer customerID;
	/**
	 * 询价客户名称;
	 */
	private String customerName;
	/**
	 * 询价客户邮箱账号
	 */
	private String customerEmail;
	/**
	 * 询价客户电话号码
	 */
	private String customerMobile;
	/**
	 * 询价源id
	 */
	private String sourceID;
	/**
	 * 询价次数
	 */
	private Integer inquiryTimes;
	/**
	 * 询价商品列表;
	 */
	private List<InquiryGoods> inquiryGoodsList;
	/**
	 * 获取: inquiryID
	 * @return the inquiryID
	 */
	public String getInquiryID() {
		return inquiryID;
	}
	/**
	 * 设置: inquiryID
	 * @param inquiryID the inquiryID to set
	 */
	public void setInquiryID(String inquiryID) {
		this.inquiryID = inquiryID;
	}
	 
	/**
	 * 获取: inquiryTime
	 * @return the inquiryTime
	 */
	public String getInquiryTime() {
		return inquiryTime;
	}
	/**
	 * 设置: inquiryTime
	 * @param inquiryTime the inquiryTime to set
	 */
	public void setInquiryTime(String inquiryTime) {
		this.inquiryTime = inquiryTime;
	}
	/**
	 * 获取: offerer
	 * @return the offerer
	 */
	public String getOfferer() {
		return offerer;
	}
	/**
	 * 设置: offerer
	 * @param offerer the offerer to set
	 */
	public void setOfferer(String offerer) {
		this.offerer = offerer;
	}
	/**
	 * 获取: offerTime
	 * @return the offerTime
	 */
	public String getOfferTime() {
		return offerTime;
	}
	/**
	 * 设置: offerTime
	 * @param offerTime the offerTime to set
	 */
	public void setOfferTime(String offerTime) {
		this.offerTime = offerTime;
	}
	 
	/**
	 * 获取: customerEmail
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}
	/**
	 * 设置: customerEmail
	 * @param customerEmail the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	/**
	 * 获取: customerMobile
	 * @return the customerMobile
	 */
	public String getCustomerMobile() {
		return customerMobile;
	}
	/**
	 * 设置: customerMobile
	 * @param customerMobile the customerMobile to set
	 */
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	/**
	 * 获取: sourceID
	 * @return the sourceID
	 */
	public String getSourceID() {
		return sourceID;
	}
	/**
	 * 设置: sourceID
	 * @param sourceID the sourceID to set
	 */
	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}
	/**
	 * 获取: inquiryState
	 * @return the inquiryState
	 */
	public Integer getInquiryState() {
		return inquiryState;
	}
	/**
	 * 设置: inquiryState
	 * @param inquiryState the inquiryState to set
	 */
	public void setInquiryState(Integer inquiryState) {
		this.inquiryState = inquiryState;
	}
	/**
	 * 获取: customerID
	 * @return the customerID
	 */
	public Integer getCustomerID() {
		return customerID;
	}
	/**
	 * 设置: customerID
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}
	/**
	 * 获取: inquiryTimes
	 * @return the inquiryTimes
	 */
	public Integer getInquiryTimes() {
		return inquiryTimes;
	}
	/**
	 * 设置: inquiryTimes
	 * @param inquiryTimes the inquiryTimes to set
	 */
	public void setInquiryTimes(Integer inquiryTimes) {
		this.inquiryTimes = inquiryTimes;
	}
	 
	/**
	 * 获取: inquiryGoodsList
	 * @return the inquiryGoodsList
	 */
	public List<InquiryGoods> getInquiryGoodsList() {
		return inquiryGoodsList;
	}
	/**
	 * 设置: inquiryGoodsList
	 * @param inquiryGoodsList the inquiryGoodsList to set
	 */
	public void setInquiryGoodsList(List<InquiryGoods> inquiryGoodsList) {
		this.inquiryGoodsList = inquiryGoodsList;
	}
	/**
	 * 获取: salesManager
	 * @return the salesManager
	 */
	public Integer getSalesManager() {
		return salesManager;
	}
	/**
	 * 设置: salesManager
	 * @param salesManager the salesManager to set
	 */
	public void setSalesManager(Integer salesManager) {
		this.salesManager = salesManager;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InquiryManage [inquiryID=" + inquiryID + ", inquiryState=" + inquiryState + ", inquiryTime="
				+ inquiryTime + ", salesManager=" + salesManager + ", offerer=" + offerer + ", offerTime=" + offerTime
				+ ", customerID=" + customerID + ", customerName=" + customerName + ", customerEmail=" + customerEmail
				+ ", customerMobile=" + customerMobile + ", sourceID=" + sourceID + ", inquiryTimes=" + inquiryTimes
				+ ", inquiryGoodsList=" + inquiryGoodsList + "]";
	}
	 
	 
	
	
	
}
