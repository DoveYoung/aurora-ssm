package com.aurorascm.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartOrderGoods implements Serializable{
	
	/** 购物车商品实体及结算支付计算【定金】
	 * @author BYG 2017-6-21
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;
	
		private String goodsID;						//商品ID
		private String goodsName;					//商品名称
		private String goodsCode;					//商品条码
		private String goodsMap;			 		//商品主图
		private int tradeType;						//商品贸易方式
		private int goodsNum;				 		//商品购买数量
		private BigDecimal costPrice;               //商品成本价
		private BigDecimal goodsPrice1;             //商品阶段1价格
		private BigDecimal goodsPrice2;             //商品阶段2价格
		private BigDecimal goodsWeight;             //商品重量
		private int rnum1;							//商品起买量
		private int rnum2;							//商品阶段量
		private int rnum3;			 				//商品阶段量
		private int deposit;			 			//定金比例
		private BigDecimal goodsPrice;              //商品显示价格
		private int postageStyle;			 		//邮寄方式
		
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
		public String getGoodsMap() {
			return goodsMap;
		}
		public void setGoodsMap(String goodsMap) {
			this.goodsMap = goodsMap;
		}
		public int getTradeType() {
			return tradeType;
		}
		public void setTradeType(int tradeType) {
			this.tradeType = tradeType;
		}
		public int getGoodsNum() {
			return goodsNum;
		}
		public void setGoodsNum(int goodsNum) {
			this.goodsNum = goodsNum;
		}
		public BigDecimal getGoodsPrice1() {
			return goodsPrice1;
		}
		public void setGoodsPrice1(BigDecimal goodsPrice1) {
			this.goodsPrice1 = goodsPrice1;
		}
		public BigDecimal getGoodsPrice2() {
			return goodsPrice2;
		}
		public void setGoodsPrice2(BigDecimal goodsPrice2) {
			this.goodsPrice2 = goodsPrice2;
		}
		public int getRnum1() {
			return rnum1;
		}
		public void setRnum1(int rnum1) {
			this.rnum1 = rnum1;
		}
		public int getRnum2() {
			return rnum2;
		}
		public void setRnum2(int rnum2) {
			this.rnum2 = rnum2;
		}
		public int getRnum3() {
			return rnum3;
		}
		public void setRnum3(int rnum3) {
			this.rnum3 = rnum3;
		}
		public int getDeposit() {
			return deposit;
		}
		public void setDeposit(int deposit) {
			this.deposit = deposit;
		}
		
		public BigDecimal getGoodsPrice() {
			if (rnum1 <= goodsNum && goodsNum <= rnum2){
				goodsPrice = goodsPrice1;
			}else if (rnum2 < goodsNum && goodsNum <= rnum3) {
				goodsPrice = goodsPrice2;
			}
			return goodsPrice;
		}
		public void setGoodsPrice(BigDecimal goodsPrice) {
			this.goodsPrice = goodsPrice;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		public String getGoodsCode() {
			return goodsCode;
		}
		public void setGoodsCode(String goodsCode) {
			this.goodsCode = goodsCode;
		}
		public BigDecimal getCostPrice() {
			return costPrice;
		}
		public void setCostPrice(BigDecimal costPrice) {
			this.costPrice = costPrice;
		}
		
		public BigDecimal getGoodsWeight() {
			return goodsWeight;
		}
		public void setGoodsWeight(BigDecimal goodsWeight) {
			this.goodsWeight = goodsWeight;
		}
		public int getPostageStyle() {
			return postageStyle;
		}
		public void setPostageStyle(int postageStyle) {
			this.postageStyle = postageStyle;
		}
		@Override
		public String toString() {
			return "CartOrderGoods [goodsID=" + goodsID + ", goodsName=" + goodsName + ", goodsCode=" + goodsCode
					+ ", goodsMap=" + goodsMap + ", tradeType=" + tradeType + ", goodsNum=" + goodsNum + ", costPrice="
					+ costPrice + ", goodsPrice1=" + goodsPrice1 + ", goodsPrice2=" + goodsPrice2 + ", goodsWeight="
					+ goodsWeight + ", rnum1=" + rnum1 + ", rnum2=" + rnum2 + ", rnum3=" + rnum3 + ", deposit="
					+ deposit + ", goodsPrice=" + goodsPrice + ", postageStyle=" + postageStyle + ", getGoodsID()="
					+ getGoodsID() + ", getGoodsName()=" + getGoodsName() + ", getGoodsMap()=" + getGoodsMap()
					+ ", getTradeType()=" + getTradeType() + ", getGoodsNum()=" + getGoodsNum() + ", getGoodsPrice1()="
					+ getGoodsPrice1() + ", getGoodsPrice2()=" + getGoodsPrice2() + ", getRnum1()=" + getRnum1()
					+ ", getRnum2()=" + getRnum2() + ", getRnum3()=" + getRnum3() + ", getDeposit()=" + getDeposit()
					+ ", getGoodsPrice()=" + getGoodsPrice() + ", getGoodsCode()=" + getGoodsCode()
					+ ", getCostPrice()=" + getCostPrice() + ", getGoodsWeight()=" + getGoodsWeight()
					+ ", getPostageStyle()=" + getPostageStyle() + ", getClass()=" + getClass() + ", hashCode()="
					+ hashCode() + ", toString()=" + super.toString() + "]";
		}
		
}
