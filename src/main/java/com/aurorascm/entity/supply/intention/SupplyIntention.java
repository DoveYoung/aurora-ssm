package com.aurorascm.entity.supply.intention;

import java.io.Serializable;
/**
 * @Title: SupplyIntention.java 
 * @Package com.aurora.entity.dcc 
 * @Description: 全站供货意向
 * @author SSY  
 * @date 2018年5月3日 下午8:03:38 
 * @version V1.0
 */
public class SupplyIntention implements Serializable{
 
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -4591379472957497970L;
	
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 用户id,可为空
	 */
	private Integer customerID;
	/**
	 * 公司名称
	 */
	private String companyName;
	
	/**
	 * 联系人
	 */
	private String contacts;
	
	/**
	 * 联系方式
	 */
	private String phone;
	 
	/**
	 *  优势品牌
	 */
	private String advantageBrand;
	/**
	 * 供应链路
	 */
	private Integer chainPath;
	
	/**
	 * 方便联系时间
	 */
	private String convenientTime;
	/**
	 * 更新时间
	 */
	private String inputTime;
	
	/**
	 * 查询开始时间
	 */
	private String beginInputTime;
	/**
	 * 查询结束时间
	 */
	private String endInputTime;
	
	
	public String getBeginInputTime() {
		return beginInputTime;
	}
	public void setBeginInputTime(String beginInputTime) {
		this.beginInputTime = beginInputTime;
	}
	public String getEndInputTime() {
		return endInputTime;
	}
	public void setEndInputTime(String endInputTime) {
		this.endInputTime = endInputTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAdvantageBrand() {
		return advantageBrand;
	}
	public void setAdvantageBrand(String advantageBrand) {
		this.advantageBrand = advantageBrand;
	}
	public Integer getChainPath() {
		return chainPath;
	}
	public void setChainPath(Integer chainPath) {
		this.chainPath = chainPath;
	}
	public String getConvenientTime() {
		return convenientTime;
	}
	public void setConvenientTime(String convenientTime) {
		this.convenientTime = convenientTime;
	}
	public String getInputTime() {
		return inputTime;
	}
	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}
	
	@Override
	public String toString() {
		return "SupplyIntention [id=" + id + ", customerID=" + customerID + ", companyName=" + companyName
				+ ", contacts=" + contacts + ", phone=" + phone + ", advantageBrand=" + advantageBrand + ", chainPath="
				+ chainPath + ", convenientTime=" + convenientTime + ", inputTime=" + inputTime + "]";
	}
}
