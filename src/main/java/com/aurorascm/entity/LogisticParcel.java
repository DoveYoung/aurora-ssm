package com.aurorascm.entity;

import java.io.Serializable;

public class LogisticParcel  implements Serializable{
	
	 
	private static final long serialVersionUID = 1L;
	
	/**
	 * 快递公司代码
	 */
	private String expCode;
	
	/**
	 * 快递公司名称
	 */
	private String expCompany;
	
	/**
	 * 快递公司官网
	 */
	private String expressUrl;
	
	

	/**
	 * 运单号
	 */
	private String expNo;
	
	/**
	 * 物流轨迹信息
	 */
	private String logisticTrack;

	 
	
	public String getExpCode() {
		return expCode;
	}

	public void setExpCode(String expCode) {
		this.expCode = expCode;
	}

	public String getExpCompany() {
		return expCompany;
	}

	public void setExpCompany(String expCompany) {
		this.expCompany = expCompany;
	}

	public String getExpressUrl() {
		return expressUrl;
	}

	public void setExpressUrl(String expressUrl) {
		this.expressUrl = expressUrl;
	}

	public String getExpNo() {
		return expNo;
	}

	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}

	public String getLogisticTrack() {
		return logisticTrack;
	}

	public void setLogisticTrack(String logisticTrack) {
		this.logisticTrack = logisticTrack;
	}
	
	@Override
	public String toString() {
		return "LogisticParcel [expCode=" + expCode + ", expCompany=" + expCompany + ", expressUrl=" + expressUrl
				+ ", expNo=" + expNo + ", logisticTrack=" + logisticTrack + "]";
	}
	
	
	
}
