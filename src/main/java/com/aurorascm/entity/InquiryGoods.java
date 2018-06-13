package com.aurorascm.entity;

import java.math.BigDecimal;

/**
 * 询价商品实体类
 * @author SSY 2018-1-4
 */
public class InquiryGoods {

	/**
	 * 询价单id
	 */
	private String inquiryID;
	/**
	 * 询价商品id
	 */
	private Integer inquiryGoodsID;
	/**
	 * 询价商品名称
	 */
	private String goodsName;
	/**
	 * 询价商品主图
	 */
	private String goodsMap;
	/**
	 * 询价商品品牌
	 */
	private String goodsBrand;
	/**
	 * 询价商品规格
	 */
	private String goodsNorm;
	/**
	 * 询价商品条码
	 */
	private String goodsCode;
	/**
	 * 客户询价商品数量
	 */
	private BigDecimal buyNum;
	/**
	 * 修改后的询价商品数量
	 */
	private BigDecimal updateNum;
	/**
	 * 交货国家
	 */
	private String deliverCountry;
	/**
	 * 交货城市
	 */
	private String deliverCity;
	/**
	 * 物流方式1.海运;2.陆运;3.空运
	 */
	private Integer logisticsType;
	/**
	 * 贸易方式：1CIF；2FCA；3EXW ; 4.FOB;
	 */
	private Integer tradeType;
	/**
	 * 备货天数
	 */
	private Integer readyTime;
	/**
	 * 有效期
	 */
	private String validTime;
	/**
	 * 客户备注
	 */
	private String customerRemark;
	 
	/**
	 * 货币符号
	 */
	private String currencySign;
	/**
	 * 货币名称
	 */
	private String currencyName;
	/**
	 * 一托盘体积
	 */
	private BigDecimal palletVolume;
	
	/**
	 * 该商品总体积
	 */
	private BigDecimal volume;
	
	/**
	 * 一坨运费
	 */
	private BigDecimal palletPrice;
	/**
	 * 商品单价
	 */
	private BigDecimal goodsPrice;
	/**
	 * 总供货价
	 */
	private BigDecimal supplyPrice;
	/**
	 * 商品邮费
	 */
	private BigDecimal postage;
	/**
	 * 询价商品状态
	 */
	private Integer inquiryGoodsState;
	/**
	 * 修改报价次数
	 */
	private Integer record;
	/**
	 * 保存报价人
	 */
	private String inputor;
	/**
	 * 保存报价时间
	 */
	private String inputTime;
	/**
	 * 获取: inquiryID
	 * @return the inquiryID
	 */
	public String getInquiryID() {
		return inquiryID;
	}
	/**
	 * 设置: inquiryID
	 * @param inquiryID the inquiryID to set
	 */
	public void setInquiryID(String inquiryID) {
		this.inquiryID = inquiryID;
	}
	/**
	 * 获取: inquiryGoodsID
	 * @return the inquiryGoodsID
	 */
	public Integer getInquiryGoodsID() {
		return inquiryGoodsID;
	}
	/**
	 * 设置: inquiryGoodsID
	 * @param inquiryGoodsID the inquiryGoodsID to set
	 */
	public void setInquiryGoodsID(Integer inquiryGoodsID) {
		this.inquiryGoodsID = inquiryGoodsID;
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
	 * 获取: goodsBrand
	 * @return the goodsBrand
	 */
	public String getGoodsBrand() {
		return goodsBrand;
	}
	/**
	 * 设置: goodsBrand
	 * @param goodsBrand the goodsBrand to set
	 */
	public void setGoodsBrand(String goodsBrand) {
		this.goodsBrand = goodsBrand;
	}
	/**
	 * 获取: goodsNorm
	 * @return the goodsNorm
	 */
	public String getGoodsNorm() {
		return goodsNorm;
	}
	/**
	 * 设置: goodsNorm
	 * @param goodsNorm the goodsNorm to set
	 */
	public void setGoodsNorm(String goodsNorm) {
		this.goodsNorm = goodsNorm;
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
	 * 获取: buyNum
	 * @return the buyNum
	 */
	public BigDecimal getBuyNum() {
		return buyNum;
	}
	/**
	 * 设置: buyNum
	 * @param buyNum the buyNum to set
	 */
	public void setBuyNum(BigDecimal buyNum) {
		this.buyNum = buyNum;
	}
	/**
	 * 获取: updateNum
	 * @return the updateNum
	 */
	public BigDecimal getUpdateNum() {
		return updateNum;
	}
	/**
	 * 设置: updateNum
	 * @param updateNum the updateNum to set
	 */
	public void setUpdateNum(BigDecimal updateNum) {
		this.updateNum = updateNum;
	}
	/**
	 * 获取: deliverCountry
	 * @return the deliverCountry
	 */
	public String getDeliverCountry() {
		return deliverCountry;
	}
	/**
	 * 设置: deliverCountry
	 * @param deliverCountry the deliverCountry to set
	 */
	public void setDeliverCountry(String deliverCountry) {
		this.deliverCountry = deliverCountry;
	}
	/**
	 * 获取: deliverCity
	 * @return the deliverCity
	 */
	public String getDeliverCity() {
		return deliverCity;
	}
	/**
	 * 设置: deliverCity
	 * @param deliverCity the deliverCity to set
	 */
	public void setDeliverCity(String deliverCity) {
		this.deliverCity = deliverCity;
	}
	/**
	 * 获取: logisticsType
	 * @return the logisticsType
	 */
	public Integer getLogisticsType() {
		return logisticsType;
	}
	/**
	 * 设置: logisticsType
	 * @param logisticsType the logisticsType to set
	 */
	public void setLogisticsType(Integer logisticsType) {
		this.logisticsType = logisticsType;
	}
	/**
	 * 获取: tradeType
	 * @return the tradeType
	 */
	public Integer getTradeType() {
		return tradeType;
	}
	/**
	 * 设置: tradeType
	 * @param tradeType the tradeType to set
	 */
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}
	/**
	 * 获取: readyTime
	 * @return the readyTime
	 */
	public Integer getReadyTime() {
		return readyTime;
	}
	/**
	 * 设置: readyTime
	 * @param readyTime the readyTime to set
	 */
	public void setReadyTime(Integer readyTime) {
		this.readyTime = readyTime;
	}
	 
	/**
	 * 获取: currencySign
	 * @return the currencySign
	 */
	public String getCurrencySign() {
		return currencySign;
	}
	/**
	 * 设置: currencySign
	 * @param currencySign the currencySign to set
	 */
	public void setCurrencySign(String currencySign) {
		this.currencySign = currencySign;
	}
	/**
	 * 获取: currencyName
	 * @return the currencyName
	 */
	public String getCurrencyName() {
		return currencyName;
	}
	/**
	 * 设置: currencyName
	 * @param currencyName the currencyName to set
	 */
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	/**
	 * 获取: palletVolume
	 * @return the palletVolume
	 */
	public BigDecimal getPalletVolume() {
		return palletVolume;
	}
	/**
	 * 设置: palletVolume
	 * @param palletVolume the palletVolume to set
	 */
	public void setPalletVolume(BigDecimal palletVolume) {
		this.palletVolume = palletVolume;
	}
	/**
	 * 获取: volume
	 * @return the volume
	 */
	public BigDecimal getVolume() {
		return volume;
	}
	/**
	 * 设置: volume
	 * @param volume the volume to set
	 */
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	/**
	 * 获取: palletPrice
	 * @return the palletPrice
	 */
	public BigDecimal getPalletPrice() {
		return palletPrice;
	}
	/**
	 * 设置: palletPrice
	 * @param palletPrice the palletPrice to set
	 */
	public void setPalletPrice(BigDecimal palletPrice) {
		this.palletPrice = palletPrice;
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
	 * 获取: supplyPrice
	 * @return the supplyPrice
	 */
	public BigDecimal getSupplyPrice() {
		return supplyPrice;
	}
	/**
	 * 设置: supplyPrice
	 * @param supplyPrice the supplyPrice to set
	 */
	public void setSupplyPrice(BigDecimal supplyPrice) {
		this.supplyPrice = supplyPrice;
	}
	/**
	 * 获取: postage
	 * @return the postage
	 */
	public BigDecimal getPostage() {
		return postage;
	}
	/**
	 * 设置: postage
	 * @param postage the postage to set
	 */
	public void setPostage(BigDecimal postage) {
		this.postage = postage;
	}
	/**
	 * 获取: inquiryGoodsState
	 * @return the inquiryGoodsState
	 */
	public Integer getInquiryGoodsState() {
		return inquiryGoodsState;
	}
	/**
	 * 设置: inquiryGoodsState
	 * @param inquiryGoodsState the inquiryGoodsState to set
	 */
	public void setInquiryGoodsState(Integer inquiryGoodsState) {
		this.inquiryGoodsState = inquiryGoodsState;
	}
	/**
	 * 获取: record
	 * @return the record
	 */
	public Integer getRecord() {
		return record;
	}
	/**
	 * 设置: record
	 * @param record the record to set
	 */
	public void setRecord(Integer record) {
		this.record = record;
	}
	/**
	 * 获取: inputor
	 * @return the inputor
	 */
	public String getInputor() {
		return inputor;
	}
	/**
	 * 设置: inputor
	 * @param inputor the inputor to set
	 */
	public void setInputor(String inputor) {
		this.inputor = inputor;
	}
	 
	/**
	 * 获取: validTime
	 * @return the validTime
	 */
	public String getValidTime() {
		return validTime;
	}
	/**
	 * 设置: validTime
	 * @param validTime the validTime to set
	 */
	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}
	/**
	 * 获取: customerRemark
	 * @return the customerRemark
	 */
	public String getCustomerRemark() {
		return customerRemark;
	}
	/**
	 * 设置: customerRemark
	 * @param customerRemark the customerRemark to set
	 */
	public void setCustomerRemark(String customerRemark) {
		this.customerRemark = customerRemark;
	}
	/**
	 * 获取: inputTime
	 * @return the inputTime
	 */
	public String getInputTime() {
		return inputTime;
	}
	/**
	 * 设置: inputTime
	 * @param inputTime the inputTime to set
	 */
	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InquiryGoods [inquiryID=" + inquiryID + ", inquiryGoodsID=" + inquiryGoodsID + ", goodsName="
				+ goodsName + ", goodsMap=" + goodsMap + ", goodsBrand=" + goodsBrand + ", goodsNorm=" + goodsNorm
				+ ", goodsCode=" + goodsCode + ", buyNum=" + buyNum + ", updateNum=" + updateNum + ", deliverCountry="
				+ deliverCountry + ", deliverCity=" + deliverCity + ", logisticsType=" + logisticsType + ", tradeType="
				+ tradeType + ", readyTime=" + readyTime + ", validTime=" + validTime + ", customerRemark="
				+ customerRemark + ", currencySign=" + currencySign + ", currencyName=" + currencyName
				+ ", palletVolume=" + palletVolume + ", volume=" + volume + ", palletPrice=" + palletPrice
				+ ", goodsPrice=" + goodsPrice + ", supplyPrice=" + supplyPrice + ", postage=" + postage
				+ ", inquiryGoodsState=" + inquiryGoodsState + ", record=" + record + ", inputor=" + inputor
				+ ", inputTime=" + inputTime + "]";
	}
	 
	
}
