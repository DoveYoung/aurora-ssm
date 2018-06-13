package com.aurorascm.entity;

import java.io.Serializable;

/**
 * @Title: SalesManager.java 
 * @Package com.aurorascm.entity 
 * @Description: 客户专属销售经理
 * @author SSY  
 * @date 2018年6月1日 上午10:15:10 
 * @version V1.0
 */
public class SalesManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 销售经理id
	 */
	private Integer userID;
	
	/**
	 * 销售经理姓名
	 */
	private String realName;
	
	/**
	 * 销售经理电话
	 */
	private String mobile;
	
	
	/**
	 * 销售经理邮箱
	 */
	private String email;
	
	/**
	 * 销售经理QQ
	 */
	private String qq;
	
	
	/**
	 * 销售经理微信
	 */
	private String weChat;


	public Integer getUserID() {
		return userID;
	}


	public void setUserID(Integer userID) {
		this.userID = userID;
	}


	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getQq() {
		return qq;
	}


	public void setQq(String qq) {
		this.qq = qq;
	}


	public String getWeChat() {
		return weChat;
	}


	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}


	@Override
	public String toString() {
		return "SalesManager [userID=" + userID + ", realName=" + realName + ", mobile=" + mobile + ", email=" + email
				+ ", qq=" + qq + ", weChat=" + weChat + "]";
	}
	
	
	
	
	
}
