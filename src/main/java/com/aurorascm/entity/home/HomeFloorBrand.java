package com.aurorascm.entity.home;

import java.io.Serializable;
/**
 * @Title: HomeFloorBrand.java 
 * @Package com.aurora.entity.home 
 * @Description: 首页各类目商品对应的热门品牌
 * @author SSY  
 * @date 2018年5月4日 下午4:39:50 
 * @version V1.0
 */
public class HomeFloorBrand implements Serializable{
 
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 5690242008353886296L;
	
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 品牌id
	 */
	private Integer brandID;
	/**
	 * 关注数量
	 */
	private Integer attentionNum;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 品牌热门图
	 */
	private String homeMap;
	/**
	 * 类目id位置楼层位置 ,品牌所在楼层位置 
	 */
	private Integer floor;
	/**
	 * 热门品牌在当前楼层中的位置 
	 */
	private Integer location;
 
	/**
	 * 更新时间
	 */
	private String operateTime;
	/**
	 * 更新者
	 */
	private String operator;
 
	
	public Integer getAttentionNum() {
		return attentionNum;
	}
	public void setAttentionNum(Integer attentionNum) {
		this.attentionNum = attentionNum;
	}
	public String getHomeMap() {
		return homeMap;
	}
	public void setHomeMap(String homeMap) {
		this.homeMap = homeMap;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBrandID() {
		return brandID;
	}
	public void setBrandID(Integer brandID) {
		this.brandID = brandID;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public Integer getLocation() {
		return location;
	}
	public void setLocation(Integer location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "HomeFloorBrand [id=" + id + ", brandID=" + brandID + ", brandName=" + brandName + ", floor=" + floor
				+ ", location=" + location + ", operateTime=" + operateTime + ", operator=" + operator + "]";
	}
}
