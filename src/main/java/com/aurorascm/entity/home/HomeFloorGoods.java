package com.aurorascm.entity.home;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * @Title: HomeFloorGoods.java 
 * @Package com.aurora.entity.home 
 * @Description: 首页楼层类目商品
 * @author SSY  
 * @date 2018年5月2日 下午4:39:50 
 * @version V1.0
 */
public class HomeFloorGoods implements Serializable{
 
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -8839600625377854028L;
	
	/**
	 * id
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
	 * 类目id位置楼层位置  
	 */
	private Integer category1ID;
	
	/**
	 * 白底图,首页白底图
	 */
	private String homeMap;
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
	 
	public String getHomeMap() {
		return homeMap;
	}
	public void setHomeMap(String homeMap) {
		this.homeMap = homeMap;
	}
	 
	public Integer getCategory1ID() {
		return category1ID;
	}
	public void setCategory1ID(Integer category1id) {
		category1ID = category1id;
	}

	@Override
	public String toString() {
		return "HomeFloorGoods [id=" + id + ", goodsID=" + goodsID + ", goodsNewName=" + goodsNewName + ", category1ID="
				+ category1ID + ", homeMap=" + homeMap + "]";
	}
	 
}
