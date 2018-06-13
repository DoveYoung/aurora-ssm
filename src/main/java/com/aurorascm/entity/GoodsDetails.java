package com.aurorascm.entity;

import java.io.Serializable;

public class GoodsDetails implements Serializable{
	
	/** 商品详情
	 * @author BYG 2017-7-21
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;
		private String goodsID;						//商品ID
		private String goodsName;					//商品中文名加简单描述
		private String goodsCode;					//商品条形码
		private String weight;						//商品净重
		private String volume;						//商品体积
		private String productArea;					//商品原产地
		private String property;					//商品属性：字符串,拼接
		private String mainMap;						//商品主图
		private String map6;						//商品6个附图：URL，拼接
		private String advertiseMap;				//商品广告图：URL，拼接
		private String describe;					//商品描述
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
		public String getGoodsCode() {
			return goodsCode;
		}
		public void setGoodsCode(String goodsCode) {
			this.goodsCode = goodsCode;
		}
		public String getWeight() {
			return weight;
		}
		public void setWeight(String weight) {
			this.weight = weight;
		}
		public String getVolume() {
			return volume;
		}
		public void setVolume(String volume) {
			this.volume = volume;
		}
		public String getProductArea() {
			return productArea;
		}
		public void setProductArea(String productArea) {
			this.productArea = productArea;
		}
		public String getProperty() {
			return property;
		}
		public void setProperty(String property) {
			this.property = property;
		}
		public String getMainMap() {
			return mainMap;
		}
		public void setMainMap(String mainMap) {
			this.mainMap = mainMap;
		}
		public String getMap6() {
			return map6;
		}
		public void setMap6(String map6) {
			this.map6 = map6;
		}
		public String getAdvertiseMap() {
			return advertiseMap;
		}
		public void setAdvertiseMap(String advertiseMap) {
			this.advertiseMap = advertiseMap;
		}
		public String getDescribe() {
			return describe;
		}
		public void setDescribe(String describe) {
			this.describe = describe;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		@Override
		public String toString() {
			return "GoodsDetails [goodsID=" + goodsID + ", goodsName=" + goodsName + ", goodsCode=" + goodsCode
					+ ", weight=" + weight + ", volume=" + volume + ", productArea=" + productArea + ", property="
					+ property + ", mainMap=" + mainMap + ", map6=" + map6 + ", advertiseMap=" + advertiseMap
					+ ", describe=" + describe + ", getGoodsID()=" + getGoodsID() + ", getGoodsName()=" + getGoodsName()
					+ ", getGoodsCode()=" + getGoodsCode() + ", getWeight()=" + getWeight() + ", getVolume()="
					+ getVolume() + ", getProductArea()=" + getProductArea() + ", getProperty()=" + getProperty()
					+ ", getMainMap()=" + getMainMap() + ", getMap6()=" + getMap6() + ", getAdvertiseMap()="
					+ getAdvertiseMap() + ", getDescribe()=" + getDescribe() + ", getClass()=" + getClass()
					+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}
	
}
