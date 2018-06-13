package com.aurorascm.entity;

import java.io.Serializable;

/**
 * @Title: Customer.java 
 * @Package com.aurorascm.entity 
 * @Description: 客户实体类
 * @author SSY  
 * @date 2018年6月1日 上午10:03:14 
 * @version V1.0
 */
public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 客户ID 
	 */
	private String customerID;		
	
	/**
	 * 用户名
	 */
	private String customerName;	
	
	/**
	 * 专属销售经理
	 */
	private Integer salesManagerID;	
	
	/**
	 * 专属销售经理
	 */
	private SalesManager salesManager;	
	
	/**
	 * 性别
	 */
	private String customerSex;		
	
	/**
	 * 手机号
	 */
	private String customerMobile;				 
	
	/**
	 * 邮箱
	 */
	transient private String customerEmail;		
	
	/**
	 * 密码,不被序列化
	 */
	transient private String customerPassword;	 
	
	/**
	 * 生日
	 */
	private String customerBirthday;		 
	
	/**
	 * QQ
	 */
	private String customerQQ;					 
	
	/**
	 * 微信
	 */
	private String customerWeChat;		
	
	/**
	 * IP
	 */
	private String customerIP;					
	
	/**
	 * 1可用；4禁用
	 */
	private String customerStatus;		
	
	/**
	 * 最后登陆时间
	 */
	private String lastLoginTime;				

	/**
	 * 注册时间
	 */
	private String registerTime;	
	
	/**
	 * 关注的品牌
	 */
	private String careBrand;					//关注品牌
	
	/**
	 * 盐值
	 */
	private String salt;					 
	
	public Integer getSalesManagerID() {
		return salesManagerID;
	}
	public void setSalesManagerID(Integer salesManagerID) {
		this.salesManagerID = salesManagerID;
	}
	public SalesManager getSalesManager() {
		return salesManager;
	}
	public void setSalesManager(SalesManager salesManager) {
		this.salesManager = salesManager;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerSex() {
		return customerSex;
	}
	public void setCustomerSex(String customerSex) {
		this.customerSex = customerSex;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public String getCustomerBirthday() {
		return customerBirthday;
	}
	public void setCustomerBirthday(String customerBirthday) {
		this.customerBirthday = customerBirthday;
	}
	public String getCustomerQQ() {
		return customerQQ;
	}
	public void setCustomerQQ(String customerQQ) {
		this.customerQQ = customerQQ;
	}
	public String getCustomerWeChat() {
		return customerWeChat;
	}
	public void setCustomerWeChat(String customerWeChat) {
		this.customerWeChat = customerWeChat;
	}
	public String getCustomerIP() {
		return customerIP;
	}
	public void setCustomerIP(String customerIP) {
		this.customerIP = customerIP;
	}
	public String getCustomerStatus() {
		return customerStatus;
	}
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getCareBrand() {
		return careBrand;
	}
	public void setCareBrand(String careBrand) {
		this.careBrand = careBrand;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", customerName=" + customerName + ", salesManager="
				+ salesManager + ", customerSex=" + customerSex + ", customerMobile=" + customerMobile
				+ ", customerEmail=" + customerEmail + ", customerPassword=" + customerPassword + ", customerBirthday="
				+ customerBirthday + ", customerQQ=" + customerQQ + ", customerWeChat=" + customerWeChat
				+ ", customerIP=" + customerIP + ", customerStatus=" + customerStatus + ", lastLoginTime="
				+ lastLoginTime + ", registerTime=" + registerTime + ", careBrand=" + careBrand + ", salt=" + salt
				+ ", getSalesManager()=" + getSalesManager() + ", getCustomerID()=" + getCustomerID()
				+ ", getCustomerName()=" + getCustomerName() + ", getCustomerSex()=" + getCustomerSex()
				+ ", getCustomerMobile()=" + getCustomerMobile() + ", getCustomerEmail()=" + getCustomerEmail()
				+ ", getCustomerPassword()=" + getCustomerPassword() + ", getCustomerBirthday()="
				+ getCustomerBirthday() + ", getCustomerQQ()=" + getCustomerQQ() + ", getCustomerWeChat()="
				+ getCustomerWeChat() + ", getCustomerIP()=" + getCustomerIP() + ", getCustomerStatus()="
				+ getCustomerStatus() + ", getLastLoginTime()=" + getLastLoginTime() + ", getRegisterTime()="
				+ getRegisterTime() + ", getCareBrand()=" + getCareBrand() + ", getSalt()=" + getSalt()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	 

		
}
