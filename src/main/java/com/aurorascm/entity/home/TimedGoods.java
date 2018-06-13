package com.aurorascm.entity.home;
/**
 * @Title: TimedActivity.java 
 * @Package com.aurora.entity.home 
 * @Description: 限时抢购活动商品
 * @author SSY  
 * @date 2018年4月23日 下午7:18:48 
 * @version V1.0
 */

import java.io.Serializable;
import java.math.BigDecimal;

public class TimedGoods implements Serializable{

	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 5498378620689165402L;
	
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 活动id
	 */
	private Integer activityID;
	/**
	 * 活动商品id
	 */
	private String goodsID;
	/**
	 * 活动商品广告标题
	 */
	private String  goodsNewName;
	/**
	 * 商品1阶段原价
	 */
	private BigDecimal originalPrice1;
	/**
	 * 商品2阶段原价
	 */
	private BigDecimal originalPrice2;
	/**
	 * 商品折扣价,现价
	 */
	private BigDecimal discountPrice;
	/**
	 * 可售数量
	 */
	private Integer availableSellNum;
	/**
	 * 已售数量
	 */
	private Integer soldNum;
	/**
	 * 商品状态1.可购买;2.已售罄
	 */
	private Integer activityGoodsState;
	
	/**
	 * home展示图
	 */
	private String homeMap;
	

	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 创建者
	 */
	private String creator;
	/**
	 * 修改时间
	 */
	private String updateTime;
	/**
	 * 修改者
	 */
	private String updator;
	
	public Integer getActivityGoodsState() {
		return activityGoodsState;
	}
	public void setActivityGoodsState(Integer activityGoodsState) {
		this.activityGoodsState = activityGoodsState;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getActivityID() {
		return activityID;
	}
	public void setActivityID(Integer activityID) {
		this.activityID = activityID;
	}
	public String getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}
	public String getGoodsNewName() {
		return goodsNewName;
	}
	public void setGoodsNewName(String goodsNewName) {
		this.goodsNewName = goodsNewName;
	}
	public BigDecimal getOriginalPrice1() {
		return originalPrice1;
	}
	public void setOriginalPrice1(BigDecimal originalPrice1) {
		this.originalPrice1 = originalPrice1;
	}
	public BigDecimal getOriginalPrice2() {
		return originalPrice2;
	}
	public void setOriginalPrice2(BigDecimal originalPrice2) {
		this.originalPrice2 = originalPrice2;
	}
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Integer getAvailableSellNum() {
		return availableSellNum;
	}
	public void setAvailableSellNum(Integer availableSellNum) {
		this.availableSellNum = availableSellNum;
	}
	public Integer getSoldNum() {
		return soldNum;
	}
	public void setSoldNum(Integer soldNum) {
		this.soldNum = soldNum;
	}
	public String getHomeMap() {
		return homeMap;
	}
	public void setHomeMap(String homeMap) {
		this.homeMap = homeMap;
	}
	@Override
	public String toString() {
		return "TimedGoods [id=" + id + ", activityID=" + activityID + ", goodsID=" + goodsID + ", goodsNewName="
				+ goodsNewName + ", originalPrice1=" + originalPrice1 + ", originalPrice2=" + originalPrice2 + ", discountPrice=" + discountPrice
				+ ", availableSellNum=" + availableSellNum + ", soldNum=" + soldNum + "]";
	}
	
	
}
