package com.aurorascm.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 合同实体类
 * @author SSY 2018-1-9
 */
public class ContractManage {

	/**
	 * 合同单id
	 */
	private String contractID;
	/**
	 * 上一份合同id,客户采购创建的则为空
	 */
	private String sourceID;
	/**
	 * 询价源id
	 */
	private String inquiryID;
	/**
	 * 合同专属销售id
	 */
	private Integer salesManager;
	/**
	 * 合同有效期
	 */
	private String validDate;
	/**
	 *  合同状态:1.合同待上传;2待付款；3已线上付款；4已线下付款；5.作废;11实付金额与应付金额不等值)
	 */
	private Integer contractState;
	/**
	 * 合同总金额包含邮费
	 */
	private BigDecimal contractMoney;
	/**
	 * 合同总邮费
	 */
	private BigDecimal totalPostage;
	/**
	 * 第三方支付交易流水号
	 */
	private String tradeNo;
	/**
	 * 付款路径：1支付宝；2微信；3快钱;4线下
	 */
	private Integer payPath;
	/**
	 * 合同付款时间
	 */
	private String payTime;
	/**
	 * 实际付款金额
	 */
	private BigDecimal payMoney;
	/**
	 * 合同文件url
	 */
	private String contractFile;
	/**
	 * 合同生成时间
	 */
	private String createTime;
	/**
	 * 客户ID
	 */
	private Integer customerID;
	/**
	 * 用户名称
	 */
	private String customerName;
	/**
	 * 用户邮箱
	 */
	private String customerEmail;
	/**
	 * 联系电话
	 */
	private String customerMobile;
	/**
	 * 合同公司名称
	 */
	private String company;
	/**
	 *  公司地址
	 */
	private String address;
	
	/**
	 * 合同中商品列表;
	 */
	private List<ContractGoods> contractGoodsList;

	/**
	 * 获取: contractID
	 * @return the contractID
	 */
	public String getContractID() {
		return contractID;
	}

	/**
	 * 设置: contractID
	 * @param contractID the contractID to set
	 */
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}

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
	 * 获取: contractState
	 * @return the contractState
	 */
	public Integer getContractState() {
		return contractState;
	}

	/**
	 * 设置: contractState
	 * @param contractState the contractState to set
	 */
	public void setContractState(Integer contractState) {
		this.contractState = contractState;
	}

	/**
	 * 获取: contractMoney
	 * @return the contractMoney
	 */
	public BigDecimal getContractMoney() {
		return contractMoney;
	}

	/**
	 * 设置: contractMoney
	 * @param contractMoney the contractMoney to set
	 */
	public void setContractMoney(BigDecimal contractMoney) {
		this.contractMoney = contractMoney;
	}

	/**
	 * 获取: totalPostage
	 * @return the totalPostage
	 */
	public BigDecimal getTotalPostage() {
		return totalPostage;
	}

	/**
	 * 设置: totalPostage
	 * @param totalPostage the totalPostage to set
	 */
	public void setTotalPostage(BigDecimal totalPostage) {
		this.totalPostage = totalPostage;
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
	 * 获取: payPath
	 * @return the payPath
	 */
	public Integer getPayPath() {
		return payPath;
	}

	/**
	 * 设置: payPath
	 * @param payPath the payPath to set
	 */
	public void setPayPath(Integer payPath) {
		this.payPath = payPath;
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
	 * 获取: contractFile
	 * @return the contractFile
	 */
	public String getContractFile() {
		return contractFile;
	}

	/**
	 * 设置: contractFile
	 * @param contractFile the contractFile to set
	 */
	public void setContractFile(String contractFile) {
		this.contractFile = contractFile;
	}
 
	/**
	 * 获取: createTime
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置: createTime
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
	 * 获取: company
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * 设置: company
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * 获取: address
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置: address
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取: contractGoodsList
	 * @return the contractGoodsList
	 */
	public List<ContractGoods> getContractGoodsList() {
		return contractGoodsList;
	}

	/**
	 * 设置: contractGoodsList
	 * @param contractGoodsList the contractGoodsList to set
	 */
	public void setContractGoodsList(List<ContractGoods> contractGoodsList) {
		this.contractGoodsList = contractGoodsList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ContractManager [contractID=" + contractID + ", inquiryID=" + inquiryID + ", salesManager="
				+ salesManager + ", contractState=" + contractState + ", contractMoney=" + contractMoney
				+ ", totalPostage=" + totalPostage + ", tradeNo=" + tradeNo + ", payPath=" + payPath + ", payTime="
				+ payTime + ", payMoney=" + payMoney + ", contractFile=" + contractFile + ", createTime="
				+ createTime + ", customerID=" + customerID + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", customerMobile=" + customerMobile + ", company=" + company + ", address=" + address
				+ ", contractGoodsList=" + contractGoodsList + ", getContractID()=" + getContractID()
				+ ", getInquiryID()=" + getInquiryID() + ", getSalesManager()=" + getSalesManager()
				+ ", getContractState()=" + getContractState() + ", getContractMoney()=" + getContractMoney()
				+ ", getTotalPostage()=" + getTotalPostage() + ", getTradeNo()=" + getTradeNo() + ", getPayPath()="
				+ getPayPath() + ", getPayTime()=" + getPayTime() + ", getPayMoney()=" + getPayMoney()
				+ ", getContractFile()=" + getContractFile() + ", getCreateTime()=" + getCreateTime() + ", getCustomerID()=" + getCustomerID()
				+ ", getCustomerName()=" + getCustomerName() + ", getCustomerEmail()=" + getCustomerEmail()
				+ ", getCustomerMobile()=" + getCustomerMobile() + ", getCompany()=" + getCompany() + ", getAddress()="
				+ getAddress() + ", getContractGoodsList()=" + getContractGoodsList() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	/**
	 * 获取: validDate
	 * @return the validDate
	 */
	public String getValidDate() {
		return validDate;
	}

	/**
	 * 设置: validDate
	 * @param validDate the validDate to set
	 */
	public void setValidDate(String validDate) {
		this.validDate = validDate;
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

	 
	
}