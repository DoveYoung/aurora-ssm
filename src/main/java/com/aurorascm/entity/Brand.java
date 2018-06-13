package com.aurorascm.entity;

import java.io.Serializable;
/** 品牌实体类
 * @author BYG 2017-5-21
 * @version 1.0
 */
public class Brand implements Serializable{
	
	 
	private static final long serialVersionUID = 1L;
		private int brandID;					//品牌ID
		private String brandName;				//品牌名
		private String brandMap;				//品牌主图
		private String advertiseMap;			//品牌广告图
		private String brandIcon;				//品牌标志
		private String nationalFlag;			//品牌所属国国旗
		private String countryEName;			//品牌国英文名
		private String countryCName;			//品牌国中文名
		private int sellStoreNum;				//卖品牌的商家数量
		private int careNum;					//品牌关注量
		private String brandDescribe1;			//品牌描述
		private String brandDescribe2;			//品牌描述
		public int getBrandID() {
			return brandID;
		}
		public void setBrandID(int brandID) {
			this.brandID = brandID;
		}
		public String getBrandName() {
			return brandName;
		}
		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}
		public String getBrandMap() {
			return brandMap;
		}
		public void setBrandMap(String brandMap) {
			this.brandMap = brandMap;
		}
		public String getAdvertiseMap() {
			return advertiseMap;
		}
		public void setAdvertiseMap(String advertiseMap) {
			this.advertiseMap = advertiseMap;
		}
		public String getBrandIcon() {
			return brandIcon;
		}
		public void setBrandIcon(String brandIcon) {
			this.brandIcon = brandIcon;
		}
		public String getNationalFlag() {
			return nationalFlag;
		}
		public void setNationalFlag(String nationalFlag) {
			this.nationalFlag = nationalFlag;
		}
		public String getCountryEName() {
			return countryEName;
		}
		public void setCountryEName(String countryEName) {
			this.countryEName = countryEName;
		}
		public String getCountryCName() {
			return countryCName;
		}
		public void setCountryCName(String countryCName) {
			this.countryCName = countryCName;
		}
		public int getSellStoreNum() {
			return sellStoreNum;
		}
		public void setSellStoreNum(int sellStoreNum) {
			this.sellStoreNum = sellStoreNum;
		}
		public int getCareNum() {
			return careNum;
		}
		public void setCareNum(int careNum) {
			this.careNum = careNum;
		}
		public String getBrandDescribe1() {
			return brandDescribe1;
		}
		public void setBrandDescribe1(String brandDescribe1) {
			this.brandDescribe1 = brandDescribe1;
		}
		public String getBrandDescribe2() {
			return brandDescribe2;
		}
		public void setBrandDescribe2(String brandDescribe2) {
			this.brandDescribe2 = brandDescribe2;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		@Override
		public String toString() {
			return "Brand [brandID=" + brandID + ", brandName=" + brandName + ", brandMap=" + brandMap
					+ ", advertiseMap=" + advertiseMap + ", brandIcon=" + brandIcon + ", nationalFlag=" + nationalFlag
					+ ", countryEName=" + countryEName + ", countryCName=" + countryCName + ", sellStoreNum="
					+ sellStoreNum + ", careNum=" + careNum + ", brandDescribe1=" + brandDescribe1 + ", brandDescribe2="
					+ brandDescribe2 + ", getBrandID()=" + getBrandID() + ", getBrandName()=" + getBrandName()
					+ ", getBrandMap()=" + getBrandMap() + ", getAdvertiseMap()=" + getAdvertiseMap()
					+ ", getBrandIcon()=" + getBrandIcon() + ", getNationalFlag()=" + getNationalFlag()
					+ ", getCountryEName()=" + getCountryEName() + ", getCountryCName()=" + getCountryCName()
					+ ", getSellStoreNum()=" + getSellStoreNum() + ", getCareNum()=" + getCareNum()
					+ ", getBrandDescribe1()=" + getBrandDescribe1() + ", getBrandDescribe2()=" + getBrandDescribe2()
					+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
					+ "]";
		}
		
	
	
}
