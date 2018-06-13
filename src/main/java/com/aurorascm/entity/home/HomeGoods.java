package com.aurorascm.entity.home;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Title: HomeGoods.java 
 * @Package com.aurorascm.entity.home 
 * @Description: 首页展示商品
 * @author SSY  
 * @date 2018年5月7日 上午11:51:51 
 * @version V1.0
 */
public class HomeGoods implements Serializable{
 
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 47920210738694345L;
	
	/**
	 *  id
	 */
	private Integer id;
	/**
	 * 商品id
	 */
	private String goodsID;
	/**
	 * 首页宣传名称
	 */
	private String goodsNewName;
	/**
	 * 首页商品展示价格
	 */
	private BigDecimal goodsPrice2;
	/**
	 * 位置
	 */
	private Integer location;
	/**
	 * 白底图,首页白底图
	 */
	private String homeMap;
	 
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 更新者
	 */
	private String updator;
	
	public BigDecimal getGoodsPrice2() {
		return goodsPrice2;
	}
	public void setGoodsPrice2(BigDecimal goodsPrice2) {
		this.goodsPrice2 = goodsPrice2;
	}
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
	public String getGoodsNewName() {
		return goodsNewName;
	}
	public void setGoodsNewName(String goodsNewName) {
		this.goodsNewName = goodsNewName;
	}
	public Integer getLocation() {
		return location;
	}
	public void setLocation(Integer location) {
		this.location = location;
	}
	public String getHomeMap() {
		return homeMap;
	}
	public void setHomeMap(String homeMap) {
		this.homeMap = homeMap;
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
	@Override
	public String toString() {
		return "HomeBonded [id=" + id + ", goodsID=" + goodsID + ", goodsNewName=" + goodsNewName + ", location="
				+ location + ", homeMap=" + homeMap + ", updateTime=" + updateTime + ", updator=" + updator + "]";
	}
	 
	
}
