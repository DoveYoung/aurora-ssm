package com.aurorascm.entity;

import java.io.Serializable;

public class CartGoods implements Serializable{
	
	/** 购物车商品实体类
	 * @author BYG 2017-7-21
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;
		private int cartID;							//购物车ID
		private String customerID;					//客户ID
		private String goodsID;			 			//商品ID
		private int category2ID;			 		//二级类目ID
		private int buyNum;				 			//购买商品数量
		private int paymentType;				 	//支付方式0全款；1定金
		private int shipType;				 		//邮寄方式1保税区直邮2海外直邮3国内现货
		private GoodsManage cartGM;                 //购物车商品的管理信息
		private GoodsCommon cartGC;                 //购物车商品的基本信息
		public int getCartID() {
			return cartID;
		}
		public void setCartID(int cartID) {
			this.cartID = cartID;
		}
		public String getCustomerID() {
			return customerID;
		}
		public void setCustomerID(String customerID) {
			this.customerID = customerID;
		}
		public String getGoodsID() {
			return goodsID;
		}
		public void setGoodsID(String goodsID) {
			this.goodsID = goodsID;
		}
		public int getCategory2ID() {
			return category2ID;
		}
		public void setCategory2ID(int category2id) {
			category2ID = category2id;
		}
		public int getBuyNum() {
			return buyNum;
		}
		public void setBuyNum(int buyNum) {
			this.buyNum = buyNum;
		}
		public int getPaymentType() {
			return paymentType;
		}
		public void setPaymentType(int paymentType) {
			this.paymentType = paymentType;
		}
		public int getShipType() {
			return shipType;
		}
		public void setShipType(int shipType) {
			this.shipType = shipType;
		}
		public GoodsManage getCartGM() {
			return cartGM;
		}
		public void setCartGM(GoodsManage cartGM) {
			this.cartGM = cartGM;
		}
		public GoodsCommon getCartGC() {
			return cartGC;
		}
		public void setCartGC(GoodsCommon cartGC) {
			this.cartGC = cartGC;
		}
		@Override
		public String toString() {
			return "CartGoods [cartID=" + cartID + ", customerID=" + customerID + ", goodsID=" + goodsID
					+ ", category2ID=" + category2ID + ", buyNum=" + buyNum + ", paymentType=" + paymentType
					+ ", shipType=" + shipType + ", cartGM=" + cartGM + ", cartGC=" + cartGC + ", getCartID()="
					+ getCartID() + ", getCustomerID()=" + getCustomerID() + ", getGoodsID()=" + getGoodsID()
					+ ", getCategory2ID()=" + getCategory2ID() + ", getBuyNum()=" + getBuyNum() + ", getPaymentType()="
					+ getPaymentType() + ", getShipType()=" + getShipType() + ", getCartGM()=" + getCartGM()
					+ ", getCartGC()=" + getCartGC() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
					+ ", toString()=" + super.toString() + "]";
		}

}
