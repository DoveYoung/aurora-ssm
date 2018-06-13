package com.aurorascm.entity;

import java.math.BigDecimal;
/**
 * 订单商品
 * @author SSY 2017-12-26
 */
public class OrderGoods {
	/**
	 * 订单商品id
	 */
	private int ogID;
	/**
	 * 该订单商品所属订单id
	 */
	private String orderID;
	/**
	 * 商品id
	 */
	private String goodsID;	
	/**
	 * 订单商品名称
	 */
	private String goodsName;	
	/**
	 * 订单商品条码 ;
	 */
	private String goodsCode;
	/**
	 * 订单商品数量
	 */
	private  int goodsNum;
	/**
	 * 订单商品货币结算单位;
	 */
	private String currencyUnit;
	/**
	 * 购买时商品价格
	 */
	private BigDecimal goodsPrice;
	/**
	 * 订单商品主图
	 */
	private String goodsMap;
	/**
	 * 订单商品重量
	 */
	private BigDecimal goodsWeight;
	
	/**
	 * 订单商品总重量
	 */
	private BigDecimal goodsTotalWeight;
	
	/**
	 * 订单商品定金
	 */
	private int deposit;
	/**
	 * 是否包邮  1包邮2不包邮
	 */
	private int postageStyle;
	/**
	 * 1全款2定金
	 */
	private int payType;
	/**
	 * 贸易的方式1德国自提；2德国代发；3中国现货; 4保税仓现货 ；
	 */
	private int tradeType;	
	/**
	 * 1物流发货；2无需物流
	 */
	private int logisticsType;
	/**
	 * 物流公司ID
	 */
	private String logisticsCompanyCode;
	/**
	 * 物流公司
	 */
	private String logisticsCompany;
	/**
	 * 物流单号
	 */
	private String logisticsNumber;
	/**
	 * 获取: ogID
	 * @return the ogID
	 */
	public int getOgID() {
		return ogID;
	}
	/**
	 * 设置: ogID
	 * @param ogID the ogID to set
	 */
	public void setOgID(int ogID) {
		this.ogID = ogID;
	}
	/**
	 * 获取: orderID
	 * @return the orderID
	 */
	public String getOrderID() {
		return orderID;
	}
	/**
	 * 设置: orderID
	 * @param orderID the orderID to set
	 */
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	/**
	 * 获取: goodsID
	 * @return the goodsID
	 */
	public String getGoodsID() {
		return goodsID;
	}
	/**
	 * 设置: goodsID
	 * @param goodsID the goodsID to set
	 */
	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}
	/**
	 * 获取: goodsCode
	 * @return the goodsCode
	 */
	public String getGoodsCode() {
		return goodsCode;
	}
	/**
	 * 设置: goodsCode
	 * @param goodsCode the goodsCode to set
	 */
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	/**
	 * 获取: goodsNum
	 * @return the goodsNum
	 */
	public int getGoodsNum() {
		return goodsNum;
	}
	/**
	 * 设置: goodsNum
	 * @param goodsNum the goodsNum to set
	 */
	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}
	/**
	 * 获取: currencyUnit
	 * @return the currencyUnit
	 */
	public String getCurrencyUnit() {
		return currencyUnit;
	}
	/**
	 * 设置: currencyUnit
	 * @param currencyUnit the currencyUnit to set
	 */
	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit;
	}
	/**
	 * 获取: goodsPrice
	 * @return the goodsPrice
	 */
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	/**
	 * 设置: goodsPrice
	 * @param goodsPrice the goodsPrice to set
	 */
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	/**
	 * 获取: goodsMap
	 * @return the goodsMap
	 */
	public String getGoodsMap() {
		return goodsMap;
	}
	/**
	 * 设置: goodsMap
	 * @param goodsMap the goodsMap to set
	 */
	public void setGoodsMap(String goodsMap) {
		this.goodsMap = goodsMap;
	}
	/**
	 * 获取: goodsWeight
	 * @return the goodsWeight
	 */
	public BigDecimal getGoodsWeight() {
		return goodsWeight;
	}
	/**
	 * 设置: goodsWeight
	 * @param goodsWeight the goodsWeight to set
	 */
	public void setGoodsWeight(BigDecimal goodsWeight) {
		this.goodsWeight = goodsWeight;
	}
	
	public BigDecimal getGoodsTotalWeight() {
		if (null!=goodsWeight) {
			goodsTotalWeight = goodsWeight.multiply(new BigDecimal(goodsNum)).setScale(2, BigDecimal.ROUND_HALF_UP);//四舍五入,保留两位
		}
		return goodsTotalWeight;
	}
	
	public void setGoodsTotalWeight(BigDecimal goodsTotalWeight) {
		this.goodsTotalWeight = goodsTotalWeight;
	}
	/**
	 * 获取: deposit
	 * @return the deposit
	 */
	public int getDeposit() {
		return deposit;
	}
	/**
	 * 获取: goodsName
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * 设置: goodsName
	 * @param goodsName the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * 设置: deposit
	 * @param deposit the deposit to set
	 */
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	/**
	 * 获取: postageStyle
	 * @return the postageStyle
	 */
	public int getPostageStyle() {
		return postageStyle;
	}
	/**
	 * 设置: postageStyle
	 * @param postageStyle the postageStyle to set
	 */
	public void setPostageStyle(int postageStyle) {
		this.postageStyle = postageStyle;
	}
	/**
	 * 获取: payType
	 * @return the payType
	 */
	public int getPayType() {
		return payType;
	}
	/**
	 * 设置: payType
	 * @param payType the payType to set
	 */
	public void setPayType(int payType) {
		this.payType = payType;
	}
	/**
	 * 获取: tradeType
	 * @return the tradeType
	 */
	public int getTradeType() {
		return tradeType;
	}
	/**
	 * 设置: tradeType
	 * @param tradeType the tradeType to set
	 */
	public void setTradeType(int tradeType) {
		this.tradeType = tradeType;
	}
	/**
	 * 获取: logisticsType
	 * @return the logisticsType
	 */
	public int getLogisticsType() {
		return logisticsType;
	}
	/**
	 * 设置: logisticsType
	 * @param logisticsType the logisticsType to set
	 */
	public void setLogisticsType(int logisticsType) {
		this.logisticsType = logisticsType;
	}
	/**
	 * 获取: logisticsCompanyCode
	 * @return the logisticsCompanyCode
	 */
	public String getLogisticsCompanyCode() {
		return logisticsCompanyCode;
	}
	/**
	 * 设置: logisticsCompanyCode
	 * @param logisticsCompanyCode the logisticsCompanyCode to set
	 */
	public void setLogisticsCompanyCode(String logisticsCompanyCode) {
		this.logisticsCompanyCode = logisticsCompanyCode;
	}
	/**
	 * 获取: logisticsCompany
	 * @return the logisticsCompany
	 */
	public String getLogisticsCompany() {
		return logisticsCompany;
	}
	/**
	 * 设置: logisticsCompany
	 * @param logisticsCompany the logisticsCompany to set
	 */
	public void setLogisticsCompany(String logisticsCompany) {
		this.logisticsCompany = logisticsCompany;
	}
	/**
	 * 获取: logisticsNumber
	 * @return the logisticsNumber
	 */
	public String getLogisticsNumber() {
		return logisticsNumber;
	}
	/**
	 * 设置: logisticsNumber
	 * @param logisticsNumber the logisticsNumber to set
	 */
	public void setLogisticsNumber(String logisticsNumber) {
		this.logisticsNumber = logisticsNumber;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderGoods [ogID=" + ogID + ", orderID=" + orderID + ", goodsID=" + goodsID + ", goods_name="
				+ goodsName + ", goodsCode=" + goodsCode + ", goodsNum=" + goodsNum + ", currencyUnit=" + currencyUnit
				+ ", goodsPrice=" + goodsPrice + ", goodsMap=" + goodsMap + ", goodsWeight=" + goodsWeight
				+ ", deposit=" + deposit + ", postageStyle=" + postageStyle + ", payType=" + payType + ", tradeType="
				+ tradeType + ", logisticsType=" + logisticsType + ", logisticsCompanyCode=" + logisticsCompanyCode
				+ ", logisticsCompany=" + logisticsCompany + ", logisticsNumber=" + logisticsNumber + "]";
	}
	
	
	
}
