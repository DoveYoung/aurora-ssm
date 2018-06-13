package com.aurorascm.entity;

import java.io.Serializable;

public class GoodsCommon implements Serializable{
	
	/** 商品共通属性
	 * @author BYG 2017-7-21
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;
		private int commonID;						//商品共通属性表ID
		private String goodsID;						//商品ID
		private String goodsName;					//商品中文名
		private int brandID;						//商品品牌ID
		private String brandName;					//商品品牌中文名
		private int shipType;						//邮寄方式/1德国直邮；2保税仓直邮；3国内现货
		private String shipTypeN;					//邮寄方式名称/1德国直邮；2保税仓直邮；3国内现货
		private String mainMap;						//商品主图
		private String goodsColour;					//商品颜色
		private String productArea;					//商品产地
		private String goodsPurpose;				//商品用途
		private String goodsSign;					//商品标记：热销等
		private int category1ID;					//商品一级类目ID
		private String category1;					//商品一级类目
		private int category2ID;					//商品二级类目ID
		private String category2;					//商品二级类目
		private int category3ID;					//商品二级类目IDַ
		private String category3;					//̬商品三级类目
		private String keyword1;					//商品关键词1
		private String keyword2;					//商品关键词2
		private String keyword3;					//商品关键词3
		private String keyword4;					//商品关键词4
		private String keyword5;					//商品关键词5
		private GoodsManage gManage;				//商品管理信息
		public int getCommonID() {
			return commonID;
		}
		public void setCommonID(int commonID) {
			this.commonID = commonID;
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
		public int getShipType() {
			return shipType;
		}
		public void setShipType(int shipType) {
			this.shipType = shipType;
		}
		public String getShipTypeN() {
			return shipTypeN;
		}
		public void setShipTypeN(String shipTypeN) {
			this.shipTypeN = shipTypeN;
		}
		public String getMainMap() {
			return mainMap;
		}
		public void setMainMap(String mainMap) {
			this.mainMap = mainMap;
		}
		public String getGoodsColour() {
			return goodsColour;
		}
		public void setGoodsColour(String goodsColour) {
			this.goodsColour = goodsColour;
		}
		public String getProductArea() {
			return productArea;
		}
		public void setProductArea(String productArea) {
			this.productArea = productArea;
		}
		public String getGoodsPurpose() {
			return goodsPurpose;
		}
		public void setGoodsPurpose(String goodsPurpose) {
			this.goodsPurpose = goodsPurpose;
		}
		public String getGoodsSign() {
			return goodsSign;
		}
		public void setGoodsSign(String goodsSign) {
			this.goodsSign = goodsSign;
		}
		public int getCategory1ID() {
			return category1ID;
		}
		public void setCategory1ID(int category1id) {
			category1ID = category1id;
		}
		public String getCategory1() {
			return category1;
		}
		public void setCategory1(String category1) {
			this.category1 = category1;
		}
		public int getCategory2ID() {
			return category2ID;
		}
		public void setCategory2ID(int category2id) {
			category2ID = category2id;
		}
		public String getCategory2() {
			return category2;
		}
		public void setCategory2(String category2) {
			this.category2 = category2;
		}
		public int getCategory3ID() {
			return category3ID;
		}
		public void setCategory3ID(int category3id) {
			category3ID = category3id;
		}
		public String getCategory3() {
			return category3;
		}
		public void setCategory3(String category3) {
			this.category3 = category3;
		}
		public String getKeyword1() {
			return keyword1;
		}
		public void setKeyword1(String keyword1) {
			this.keyword1 = keyword1;
		}
		public String getKeyword2() {
			return keyword2;
		}
		public void setKeyword2(String keyword2) {
			this.keyword2 = keyword2;
		}
		public String getKeyword3() {
			return keyword3;
		}
		public void setKeyword3(String keyword3) {
			this.keyword3 = keyword3;
		}
		public String getKeyword4() {
			return keyword4;
		}
		public void setKeyword4(String keyword4) {
			this.keyword4 = keyword4;
		}
		public String getKeyword5() {
			return keyword5;
		}
		public void setKeyword5(String keyword5) {
			this.keyword5 = keyword5;
		}
		public GoodsManage getgManage() {
			return gManage;
		}
		public void setgManage(GoodsManage gManage) {
			this.gManage = gManage;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		@Override
		public String toString() {
			return "GoodsCommon [commonID=" + commonID + ", goodsID=" + goodsID + ", goodsName=" + goodsName
					+ ", brandID=" + brandID + ", brandName=" + brandName + ", shipType=" + shipType + ", shipTypeN="
					+ shipTypeN + ", mainMap=" + mainMap + ", goodsColour=" + goodsColour + ", productArea="
					+ productArea + ", goodsPurpose=" + goodsPurpose + ", goodsSign=" + goodsSign + ", category1ID="
					+ category1ID + ", category1=" + category1 + ", category2ID=" + category2ID + ", category2="
					+ category2 + ", category3ID=" + category3ID + ", category3=" + category3 + ", keyword1=" + keyword1
					+ ", keyword2=" + keyword2 + ", keyword3=" + keyword3 + ", keyword4=" + keyword4 + ", keyword5="
					+ keyword5 + ", gManage=" + gManage + ", getCommonID()=" + getCommonID() + ", getGoodsID()="
					+ getGoodsID() + ", getGoodsName()=" + getGoodsName() + ", getBrandID()=" + getBrandID()
					+ ", getBrandName()=" + getBrandName() + ", getShipType()=" + getShipType() + ", getShipTypeN()="
					+ getShipTypeN() + ", getMainMap()=" + getMainMap() + ", getGoodsColour()=" + getGoodsColour()
					+ ", getProductArea()=" + getProductArea() + ", getGoodsPurpose()=" + getGoodsPurpose()
					+ ", getGoodsSign()=" + getGoodsSign() + ", getCategory1ID()=" + getCategory1ID()
					+ ", getCategory1()=" + getCategory1() + ", getCategory2ID()=" + getCategory2ID()
					+ ", getCategory2()=" + getCategory2() + ", getCategory3ID()=" + getCategory3ID()
					+ ", getCategory3()=" + getCategory3() + ", getKeyword1()=" + getKeyword1() + ", getKeyword2()="
					+ getKeyword2() + ", getKeyword3()=" + getKeyword3() + ", getKeyword4()=" + getKeyword4()
					+ ", getKeyword5()=" + getKeyword5() + ", getgManage()=" + getgManage() + ", getClass()="
					+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}

}
