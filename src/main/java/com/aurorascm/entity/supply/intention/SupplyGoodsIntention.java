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
public class SupplyGoodsIntention implements Serializable{
 
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 6173629307590555779L;
	
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 用户id,可为空
	 */
	private String goodsID;
	/**
	 * 公司名称
	 */
	private String goodsName;
	
	/**
	 * 联系人
	 */
	private String contacts;
	
	/**
	 * 联系方式
	 */
	private String phone;
	 
	/**
	 *  邮寄类型;
	 */
	private String deliveryType;
	/**
	 *  供货价格;
	 */
	private String price;
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
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
	@Override
	public String toString() {
		return "SupplyGoodsIntention [id=" + id + ", goodsID=" + goodsID + ", goodsName=" + goodsName + ", contacts="
				+ contacts + ", phone=" + phone + ", deliveryType=" + deliveryType + ", price=" + price
				+ ", convenientTime=" + convenientTime + ", inputTime=" + inputTime + ", beginInputTime="
				+ beginInputTime + ", endInputTime=" + endInputTime + "]";
	}
	
  
}
