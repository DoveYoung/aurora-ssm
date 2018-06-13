package com.aurorascm.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.aurorascm.entity.home.TimedActivity;

public class GoodsManage implements Serializable{
	
	/** 商品管理信息类
	 * @author BYG 2017-7-21
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;
		private int manageID;					//商品管理ID
		private String goodsID;					//商品ID
		private String relate1GID;				//关联商品ID
		private String relate2GID;				//关联商品ID
		private int shipType;					//商品邮寄方式
		private BigDecimal  costPrice;			//商品成本价格
		private BigDecimal  oldPrice1;			//商品一段老价格
		private BigDecimal  oldPrice2;			//商品二段老价格
		private BigDecimal  goodsPrice1;		//商品一段价格
		private BigDecimal  goodsPrice2;		//商品二段价格
		private int exw;						//阶段二价格是否是exw价格，是1，不是2.
		private int rnum1;						//商品1段数量
		private int rnum2;						//商品2段数量
		private int rnum3;						//商品3段数量
		private BigDecimal  marketPrice;		//市场价格
		private BigDecimal  jdPrice;			//京东价格
		private BigDecimal  tbPrice;			//淘宝价格
		private int sellStoreNum;				//淘宝在售商家
		private int goodsSales;					//商品真实累计销量
		private int fakeSales;					//商品假销量
		private BigDecimal  weight;				//商品重量
		private int  postageStyle;				//邮费:0包邮；1不包邮
		private Integer  isActivity;				//是否是活动中: 1.是活动价,2.不是活动价格
		private BigDecimal  originalPrice1;			//原价: 一段价格
		private BigDecimal  originalPrice2;			//原价: 二段价格
		private int goodsState;					//商品状态：0未完成录入（草稿）；1录入完成；2审核中；3审核通过；4上架；5下架
		private String inputer;					//商品录入者
		private String reviewer;				//商品审核者
		private int goodsStock;					//商品库存量
		private int stockEmergency;				//商品库存库存预警量
		private int deposit;					//定金支付
		private int fullPayment;				//全款支付ַ
		private BigDecimal goodsTaxes;			//̬商品税费
		private String inputTime;				//商品录入时间
		private String submitTime;				//商品提交审核时间
		private String reviewTime;				//商品审核通过时间
		private String upTime;					//商品上架时间
		private String downTime;				//商品下架时间
		private String norm;				//商品规格
		private String discount;	 		//商品折扣
		private GoodsDetails goodsDetails;		//商品详情
		private GoodsCommon goodsCommon;		//商品公共属性
		private TimedActivity timedActivity;//该商品限时折扣活动
		
		public TimedActivity getTimedActivity() {
			return timedActivity;
		}
		public void setTimedActivity(TimedActivity timedActivity) {
			this.timedActivity = timedActivity;
		}
		public Integer getIsActivity() {
			return isActivity;
		}
		public void setIsActivity(Integer isActivity) {
			this.isActivity = isActivity;
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
		public String getNorm() {
			return norm;
		}
		public void setNorm(String norm) {
			this.norm = norm;
		}
		public String getDiscount() {
			return discount;
		}
		public void setDiscount(String discount) {
			this.discount = discount;
		}
		public int getManageID() {
			return manageID;
		}
		public void setManageID(int manageID) {
			this.manageID = manageID;
		}
		public String getGoodsID() {
			return goodsID;
		}
		public void setGoodsID(String goodsID) {
			this.goodsID = goodsID;
		}
		public String getRelate1GID() {
			return relate1GID;
		}
		public void setRelate1GID(String relate1gid) {
			relate1GID = relate1gid;
		}
		public String getRelate2GID() {
			return relate2GID;
		}
		public void setRelate2GID(String relate2gid) {
			relate2GID = relate2gid;
		}
		public int getShipType() {
			return shipType;
		}
		public void setShipType(int shipType) {
			this.shipType = shipType;
		}
		public BigDecimal getCostPrice() {
			return costPrice;
		}
		public void setCostPrice(BigDecimal costPrice) {
			this.costPrice = costPrice;
		}
		public BigDecimal getOldPrice1() {
			return oldPrice1;
		}
		public void setOldPrice1(BigDecimal oldPrice1) {
			this.oldPrice1 = oldPrice1;
		}
		public BigDecimal getOldPrice2() {
			return oldPrice2;
		}
		public void setOldPrice2(BigDecimal oldPrice2) {
			this.oldPrice2 = oldPrice2;
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
		public int getExw() {
			return exw;
		}
		public void setExw(int exw) {
			this.exw = exw;
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
		public BigDecimal getJdPrice() {
			return jdPrice;
		}
		public void setJdPrice(BigDecimal jdPrice) {
			this.jdPrice = jdPrice;
		}
		public BigDecimal getTbPrice() {
			return tbPrice;
		}
		public void setTbPrice(BigDecimal tbPrice) {
			this.tbPrice = tbPrice;
		}
		public int getSellStoreNum() {
			return sellStoreNum;
		}
		public void setSellStoreNum(int sellStoreNum) {
			this.sellStoreNum = sellStoreNum;
		}
		public int getGoodsSales() {
			return goodsSales;
		}
		public void setGoodsSales(int goodsSales) {
			this.goodsSales = goodsSales;
		}
		public int getFakeSales() {
			return fakeSales;
		}
		public void setFakeSales(int fakeSales) {
			this.fakeSales = fakeSales;
		}
		public BigDecimal getWeight() {
			return weight;
		}
		public void setWeight(BigDecimal weight) {
			this.weight = weight;
		}
//	注释时间SSY 2017-11-9
//		public BigDecimal getPostage() {
//			return postage;
//		}
//		public void setPostage(BigDecimal postage) {
//			this.postage = postage;
//		}
		public int getPostageStyle() {
			return postageStyle;
		}
		public void setPostageStyle(int postageStyle) {
			this.postageStyle = postageStyle;
		}
		public int getGoodsState() {
			return goodsState;
		}
		public void setGoodsState(int goodsState) {
			this.goodsState = goodsState;
		}
		public String getInputer() {
			return inputer;
		}
		public void setInputer(String inputer) {
			this.inputer = inputer;
		}
		public String getReviewer() {
			return reviewer;
		}
		public void setReviewer(String reviewer) {
			this.reviewer = reviewer;
		}
		public int getGoodsStock() {
			return goodsStock;
		}
		public void setGoodsStock(int goodsStock) {
			this.goodsStock = goodsStock;
		}
		public int getStockEmergency() {
			return stockEmergency;
		}
		public void setStockEmergency(int stockEmergency) {
			this.stockEmergency = stockEmergency;
		}
		public int getDeposit() {
			return deposit;
		}
		public void setDeposit(int deposit) {
			this.deposit = deposit;
		}
		public int getFullPayment() {
			return fullPayment;
		}
		public void setFullPayment(int fullPayment) {
			this.fullPayment = fullPayment;
		}
		public BigDecimal getGoodsTaxes() {
			return goodsTaxes;
		}
		public void setGoodsTaxes(BigDecimal goodsTaxes) {
			this.goodsTaxes = goodsTaxes;
		}
		public String getInputTime() {
			return inputTime;
		}
		public void setInputTime(String inputTime) {
			this.inputTime = inputTime;
		}
		public String getSubmitTime() {
			return submitTime;
		}
		public void setSubmitTime(String submitTime) {
			this.submitTime = submitTime;
		}
		public String getReviewTime() {
			return reviewTime;
		}
		public void setReviewTime(String reviewTime) {
			this.reviewTime = reviewTime;
		}
		public String getUpTime() {
			return upTime;
		}
		public void setUpTime(String upTime) {
			this.upTime = upTime;
		}
		public String getDownTime() {
			return downTime;
		}
		public void setDownTime(String downTime) {
			this.downTime = downTime;
		}
		public GoodsDetails getGoodsDetails() {
			return goodsDetails;
		}
		public void setGoodsDetails(GoodsDetails goodsDetails) {
			this.goodsDetails = goodsDetails;
		}
		public GoodsCommon getGoodsCommon() {
			return goodsCommon;
		}
		public void setGoodsCommon(GoodsCommon goodsCommon) {
			this.goodsCommon = goodsCommon;
		}
		public BigDecimal getMarketPrice() {
			return marketPrice;
		}
		public void setMarketPrice(BigDecimal marketPrice) {
			this.marketPrice = marketPrice;
		}
		@Override
		public String toString() {
			return "GoodsManage [manageID=" + manageID + ", goodsID=" + goodsID + ", relate1GID=" + relate1GID
					+ ", relate2GID=" + relate2GID + ", shipType=" + shipType + ", costPrice=" + costPrice
					+ ", oldPrice1=" + oldPrice1 + ", oldPrice2=" + oldPrice2 + ", goodsPrice1=" + goodsPrice1
					+ ", goodsPrice2=" + goodsPrice2 + ", exw=" + exw + ", rnum1=" + rnum1 + ", rnum2=" + rnum2
					+ ", rnum3=" + rnum3 + ", marketPrice=" + marketPrice + ", jdPrice=" + jdPrice + ", tbPrice="
					+ tbPrice + ", sellStoreNum=" + sellStoreNum + ", fakeSales="
					+ fakeSales + ", weight=" + weight + ", postageStyle=" + postageStyle
					+ ", goodsState=" + goodsState + ", inputer=" + inputer + ", reviewer=" + reviewer + ", goodsStock="
					+ goodsStock + ", stockEmergency=" + stockEmergency + ", deposit=" + deposit + ", fullPayment="
					+ fullPayment + ", goodsTaxes=" + goodsTaxes + ", inputTime=" + inputTime + ", submitTime="
					+ submitTime + ", reviewTime=" + reviewTime + ", upTime=" + upTime + ", downTime=" + downTime
					+ ", goodsDetails=" + goodsDetails + ", goodsCommon=" + goodsCommon + ", getManageID()="
					+ getManageID() + ", getGoodsID()=" + getGoodsID() + ", getRelate1GID()=" + getRelate1GID()
					+ ", getRelate2GID()=" + getRelate2GID() + ", getShipType()=" + getShipType() + ", getCostPrice()="
					+ getCostPrice() + ", getOldPrice1()=" + getOldPrice1() + ", getOldPrice2()=" + getOldPrice2()
					+ ", getGoodsPrice1()=" + getGoodsPrice1() + ", getGoodsPrice2()=" + getGoodsPrice2()
					+ ", getExw()=" + getExw() + ", getRnum1()=" + getRnum1() + ", getRnum2()=" + getRnum2()
					+ ", getRnum3()=" + getRnum3() + ", getJdPrice()=" + getJdPrice() + ", getTbPrice()=" + getTbPrice()
					+ ", getSellStoreNum()=" + getSellStoreNum() + ", getFakeSales()=" + getFakeSales() + ", getWeight()=" + getWeight() +  ", getPostageStyle()=" + getPostageStyle() + ", getGoodsState()=" + getGoodsState()
					+ ", getInputer()=" + getInputer() + ", getReviewer()=" + getReviewer() + ", getGoodsStock()="
					+ getGoodsStock() + ", getStockEmergency()=" + getStockEmergency() + ", getDeposit()="
					+ getDeposit() + ", getFullPayment()=" + getFullPayment() + ", getGoodsTaxes()=" + getGoodsTaxes()
					+ ", getInputTime()=" + getInputTime() + ", getSubmitTime()=" + getSubmitTime()
					+ ", getReviewTime()=" + getReviewTime() + ", getUpTime()=" + getUpTime() + ", getDownTime()="
					+ getDownTime() + ", getGoodsDetails()=" + getGoodsDetails() + ", getGoodsCommon()="
					+ getGoodsCommon() + ", getMarketPrice()=" + getMarketPrice() + ", getClass()=" + getClass()
					+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}


		

}
