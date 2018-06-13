package com.aurorascm.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.aurorascm.util.Const;
import com.aurorascm.util.PostageMath;

public class CartFPMath implements Serializable{
	
	/** 购物车商品实体及结算支付计算【全款】
	 * @author BYG 2017-6-21
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;
		private int cartID;						//商品ID
		private String goodsID;						//商品ID
		private String goodsName;					//商品名称
		private String mainMap;			 			//商品主图
		private int category2ID;			 		//二级类目ID
		private String customerID;				 	//客户ID
		private int goodsStock;				 		//商品库存量
		private int goodsState;				 		//商品状态
		private int shipType;						//商品邮寄方式
		private int  postageStyle;					//邮费:0包邮；1不包邮
		private String province;			 		//邮寄目的地省份
		private int buyNum;				 			//商品购买数量
		private BigDecimal  goodsPrice1;                 //商品阶段1价格
		private BigDecimal  goodsPrice2;                 //商品阶段2价格
		private int rnum1;							//商品起买量
		private int rnum2;							//商品阶段量
		private int rnum3;			 				//商品阶段量
		private int exw;						//阶段二价格是否是exw价格，是1，不是2.
		private BigDecimal  weight;			 			//商品单个重量
		private BigDecimal  uWeight;			 			//单商品重量
		private BigDecimal  uPostage;			 		//单商品邮费
		private BigDecimal  goodsPrice;                  //商品显示价格
		private BigDecimal  uMoney;				 		//单商品购买金额
		private BigDecimal  costPrice;                  //单件商品成本价格
		private BigDecimal  uCost;				 		//商品成本
		private BigDecimal tax;
		public BigDecimal getTax() {
			if (getUMoney()!=null) {
				tax = getUMoney().multiply(Const.TAX_RATE).setScale(2, BigDecimal.ROUND_HALF_UP);//四舍五入,保留两位
			}
			return tax;
		}
		public void setTax(BigDecimal tax) {
			this.tax = tax;
		}
		public int getCartID() {
			return cartID;
		}
		public void setCartID(int cartID) {
			this.cartID = cartID;
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
		public String getMainMap() {
			return mainMap;
		}
		public void setMainMap(String mainMap) {
			this.mainMap = mainMap;
		}
		public int getCategory2ID() {
			return category2ID;
		}
		public void setCategory2ID(int category2id) {
			category2ID = category2id;
		}
		public String getCustomerID() {
			return customerID;
		}
		public void setCustomerID(String customerID) {
			this.customerID = customerID;
		}
		public int getShipType() {
			return shipType;
		}
		public void setShipType(int shipType) {
			this.shipType = shipType;
		}
		public int getPostageStyle() {
			return postageStyle;
		}
		public void setPostageStyle(int postageStyle) {
			this.postageStyle = postageStyle;
		}
		public String getProvince() {
			return province;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		public BigDecimal  getGoodsPrice1() {
			return goodsPrice1;
		}
		public void setGoodsPrice1(BigDecimal  goodsPrice1) {
			this.goodsPrice1 = goodsPrice1;
		}
		public BigDecimal  getGoodsPrice2() {
			return goodsPrice2;
		}
		public void setGoodsPrice2(BigDecimal  goodsPrice2) {
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
		public int getGoodsStock() {
			return goodsStock;
		}
		public void setGoodsStock(int goodsStock) {
			this.goodsStock = goodsStock;
		}
		public int getGoodsState() {
			return goodsState;
		}
		public void setGoodsState(int goodsState) {
			this.goodsState = goodsState;
		}
		public int getBuyNum() {
			return buyNum;
		}
		public void setBuyNum(int buyNum) {
			this.buyNum = buyNum;
		}
		public BigDecimal  getWeight() {
			return weight;
		}
		public void setWeight(BigDecimal  weight) {
			this.weight = weight;
		}
		public BigDecimal  getUWeight() {
			BigDecimal buyN = new BigDecimal(String.valueOf(buyNum));
			uWeight = buyN.multiply(weight);
			return uWeight;
		}
		public void setUWeight(BigDecimal  uWeight) {
			this.uWeight = uWeight;
		}
		public BigDecimal  getUPostage() {
			if (postageStyle == 1 || province == "weicang") {
				uPostage = new BigDecimal(0.00);
			}else if(postageStyle == 2){
				if (shipType == 2) {
					uPostage = PostageMath.getHPosttage(this.getUWeight(), buyNum);
				}else if (shipType == 1 || shipType == 3) {
					if(province != null){ 
						uPostage = PostageMath.getBGPosttage(province, weight, buyNum);
					}
				}
			}
			return uPostage;
		}
		public void setUPostage(BigDecimal  uPostage) {
			this.uPostage = uPostage;
		}
		public BigDecimal  getGoodsPrice() {
			if (rnum1 <= buyNum && buyNum <= rnum2){
				goodsPrice = goodsPrice1;
			}else if(exw == 1 && rnum2 < buyNum){//goodsPrice2为exw价时，购买数量规定不能大于rnum2，若异常大于价格为goodsPrice1
				goodsPrice = goodsPrice1;
			}else if (rnum2 < buyNum && buyNum <= rnum3) {
				goodsPrice = goodsPrice2;
			}else{
				goodsPrice = goodsPrice1;
			}
			return goodsPrice;
		}
		public void setGoodsPrice(BigDecimal  goodsPrice) {
			this.goodsPrice = goodsPrice;
		}
		public BigDecimal  getUMoney() {
			if (rnum1 <= buyNum && buyNum <= rnum2){
				goodsPrice = goodsPrice1;
			}else if(exw == 1 && rnum2 < buyNum){//goodsPrice2为exw价时，购买数量规定不能大于rnum2，若异常大于价格为goodsPrice1
				goodsPrice = goodsPrice1;
			}else if (rnum2 < buyNum && buyNum <= rnum3) {
				goodsPrice = goodsPrice2;
			}else{
				goodsPrice = goodsPrice1;
			}
			BigDecimal buyN = new BigDecimal(String.valueOf(buyNum));
			uMoney = buyN.multiply(goodsPrice);
			return uMoney;
		}
		public void setUMoney(BigDecimal  uMoney) {
			this.uMoney = uMoney;
		}
		public BigDecimal getCostPrice() {
			return costPrice;
		}
		public void setCostPrice(BigDecimal costPrice) {
			this.costPrice = costPrice;
		}
		public BigDecimal getUCost() {
			BigDecimal buyN = new BigDecimal(String.valueOf(buyNum));
			uCost = buyN.multiply(costPrice);
			return uCost;
		}
		public void setUCost(BigDecimal uCost) {
			this.uCost = uCost;
		}
		public int getExw() {
			return exw;
		}
		public void setExw(int exw) {
			this.exw = exw;
		}
		@Override
		public String toString() {
			return "CartFPMath [cartID=" + cartID + ", goodsID=" + goodsID + ", goodsName=" + goodsName + ", mainMap="
					+ mainMap + ", category2ID=" + category2ID + ", customerID=" + customerID + ", goodsStock="
					+ goodsStock + ", goodsState=" + goodsState + ", shipType=" + shipType + ", postageStyle="
					+ postageStyle + ", province=" + province + ", buyNum=" + buyNum + ", goodsPrice1=" + goodsPrice1
					+ ", goodsPrice2=" + goodsPrice2 + ", rnum1=" + rnum1 + ", rnum2=" + rnum2 + ", rnum3=" + rnum3
					+ ", exw=" + exw + ", weight=" + weight + ", uWeight=" + uWeight + ", uPostage=" + uPostage
					+ ", goodsPrice=" + goodsPrice + ", uMoney=" + uMoney + ", costPrice=" + costPrice + ", uCost="
					+ uCost + ", getCartID()=" + getCartID() + ", getGoodsID()=" + getGoodsID() + ", getGoodsName()="
					+ getGoodsName() + ", getMainMap()=" + getMainMap() + ", getCategory2ID()=" + getCategory2ID()
					+ ", getCustomerID()=" + getCustomerID() + ", getShipType()=" + getShipType()
					+ ", getPostageStyle()=" + getPostageStyle() + ", getProvince()=" + getProvince()
					+ ", getGoodsPrice1()=" + getGoodsPrice1() + ", getGoodsPrice2()=" + getGoodsPrice2()
					+ ", getRnum1()=" + getRnum1() + ", getRnum2()=" + getRnum2() + ", getRnum3()=" + getRnum3()
					+ ", getGoodsStock()=" + getGoodsStock() + ", getGoodsState()=" + getGoodsState() + ", getBuyNum()="
					+ getBuyNum() + ", getWeight()=" + getWeight() + ", getuWeight()=" + getUWeight()
					+ ", getuPostage()=" + getUPostage() + ", getGoodsPrice()=" + getGoodsPrice() + ", getUMoney()="
					+ getUMoney() + ", getCostPrice()=" + getCostPrice() + ", getuCost()=" + getUCost() + ", getExw()="
					+ getExw() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
					+ super.toString() + "]";
		}
		


}
