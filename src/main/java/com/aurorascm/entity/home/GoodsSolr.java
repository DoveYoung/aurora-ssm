package com.aurorascm.entity.home;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

public class GoodsSolr implements Serializable{
	
	/** 商品信息类，用于solr搜索
	 * @author SSY 2017-12-5
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *   ID
	 */
	@Field("id")
	private String id;	
	/**
	 *   商品ID
	 */
	@Field("goods_id")
	private String goods_id;	
	/**
	 *   商品条码
	 */
	@Field("goods_code")
	private String goods_code;	
	/**
	 *   商品名称
	 */
	@Field("goods_name")
	private String goods_name;
	/**
	 *   商品描述,副标题
	 */ 
	@Field("goods_describe")
	private String goods_describe;
	/**
	 *  商品品牌id
	 */ 
	@Field("brand_id")
	private String  brand_id;	
	/**
	 *  商品品牌名中文
	 */ 
	@Field("brand_cname")
	private String  brand_cname;
	/**
	 *  商品品牌名英文
	 */ 
	@Field("brand_ename")
	private String  brand_ename;
	/**
	 *  贸易方式:1德国自提；2德国代发；3中国现货；4保税仓现货
	 */ 
	@Field("trade_type")
	private String  trade_type;
	/**
	 *  商品贸易类型中文名称
	 */ 
	@Field("trade_type_name")
	private String  trade_type_name;
	/**
	 *  1包邮；2不包邮
	 */  
	@Field("postage_style")
	private String postage_style;
	/**
	 *  邮寄方式中文名称（包邮/不包邮）
	 */  
	@Field("postage_style_name")
	private String postage_style_name;
	/**
	 * 定金：整数（100表示不支持定金）
	 */ 
	@Field("deposit")
	private String deposit;
	/**
	 * 商品若支持定金，值：定金
	 */ 
	@Field("deposit_name")
	private String deposit_name;	
	/**
	 *  商品国家名称
	 */  
	@Field("product_area")
	private String product_area;
	/**
	 *  商品所属一级类目名
	 */ 
	@Field("category1_name")
	private String category1_name;
	/**
	 *  商品所属二级类目id
	 */  
	@Field("category2_id")
	private String category2_id;
	/**
	 *  商品所属二级类目名
	 */ 
	@Field("category2_name")
	private String category2_name;
	/**
	 *  商品所属三级类目名
	 */  
	@Field("category3_name")
	private String category3_name;
	/**
	 *  商品关键字1
	 */ 
	@Field("keyword1")
	private String keyword1;
	/**
	 *  商品关键字2
	 */ 
	@Field("keyword2")
	private String keyword2;
	/**
	 *  商品关键字3
	 */  
	@Field("keyword3")
	private String keyword3;
	/**
	 *  商品关键字4
	 */  
	@Field("keyword4")
	private String keyword4;
	/**
	 *  商品关键字5
	 */  
	@Field("keyword5")
	private String keyword5;
	/**
	 *  商品属性
	 */  
	@Field("property")
	private String property;	
	/**
	 *  商品主图
	 */  
	@Field("main_map")
	private String main_map;	
	/**
	 *  商品阶段2价格
	 */ 
	@Field("goods_price2")
	private String goods_price2;
	/**
	 *   价格单位
	 */ 
	@Field("currency_unit")
	private String currency_unit;
	/**
	 *  商品库存
	 */ 
	@Field("fake_stock")
	private String fake_stock;
	/**
	 *   商品市场售价
	 */ 
	@Field("market_price")
	private String market_price;
	/**
	 *   商品销量
	 */ 
	@Field("goods_sales")
	private String goods_sales;				 
	/**
	 *  商品上架时间时间
	 */ 
	@Field("up_time")
	private String up_time;
	/**
	 *  商品往solr更新时间
	 */
	@Field("update_time")
	private String update_time;
	
	/*************************************************************************/
	
	/**
	 * 返回 id
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置 id
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 返回 goods_id
	 * @return the goods_id
	 */
	public String getGoods_id() {
		return goods_id;
	}
	/**
	 * 设置 goods_id
	 * @param goods_id the goods_id to set
	 */
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	/**
	 * 返回 goods_code
	 * @return the goods_code
	 */
	public String getGoods_code() {
		return goods_code;
	}
	/**
	 * 设置 goods_code
	 * @param goods_code the goods_code to set
	 */
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	/**
	 * 返回 goods_name
	 * @return the goods_name
	 */
	public String getGoods_name() {
		return goods_name;
	}
	/**
	 * 设置 goods_name
	 * @param goods_name the goods_name to set
	 */
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	/**
	 * 返回 goods_describe
	 * @return the goods_describe
	 */
	public String getGoods_describe() {
		return goods_describe;
	}
	/**
	 * 设置 goods_describe
	 * @param goods_describe the goods_describe to set
	 */
	public void setGoods_describe(String goods_describe) {
		this.goods_describe = goods_describe;
	}
	/**
	 * 返回 brand_id
	 * @return the brand_id
	 */
	public String getBrand_id() {
		return brand_id;
	}
	/**
	 * 设置 brand_id
	 * @param brand_id the brand_id to set
	 */
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	/**
	 * 返回 brand_cname
	 * @return the brand_cname
	 */
	public String getBrand_cname() {
		return brand_cname;
	}
	/**
	 * 设置 brand_cname
	 * @param brand_cname the brand_cname to set
	 */
	public void setBrand_cname(String brand_cname) {
		this.brand_cname = brand_cname;
	}
	/**
	 * 返回 brand_ename
	 * @return the brand_ename
	 */
	public String getBrand_ename() {
		return brand_ename;
	}
	/**
	 * 设置 brand_ename
	 * @param brand_ename the brand_ename to set
	 */
	public void setBrand_ename(String brand_ename) {
		this.brand_ename = brand_ename;
	}
	/**
	 * 返回 trade_type
	 * @return the trade_type
	 */
	public String getTrade_type() {
		return trade_type;
	}
	/**
	 * 设置 trade_type
	 * @param trade_type the trade_type to set
	 */
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	/**
	 * 返回 trade_type_name
	 * @return the trade_type_name
	 */
	public String getTrade_type_name() {
		return trade_type_name;
	}
	/**
	 * 设置 trade_type_name
	 * @param trade_type_name the trade_type_name to set
	 */
	public void setTrade_type_name(String trade_type_name) {
		this.trade_type_name = trade_type_name;
	}
	/**
	 * 返回 postage_style
	 * @return the postage_style
	 */
	public String getPostage_style() {
		return postage_style;
	}
	/**
	 * 设置 postage_style
	 * @param postage_style the postage_style to set
	 */
	public void setPostage_style(String postage_style) {
		this.postage_style = postage_style;
	}
	/**
	 * 返回 postage_style_name
	 * @return the postage_style_name
	 */
	public String getPostage_style_name() {
		return postage_style_name;
	}
	/**
	 * 设置 postage_style_name
	 * @param postage_style_name the postage_style_name to set
	 */
	public void setPostage_style_name(String postage_style_name) {
		this.postage_style_name = postage_style_name;
	}
	/**
	 * 返回 deposit
	 * @return the deposit
	 */
	public String getDeposit() {
		return deposit;
	}
	/**
	 * 设置 deposit
	 * @param deposit the deposit to set
	 */
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	/**
	 * 返回 deposit_name
	 * @return the deposit_name
	 */
	public String getDeposit_name() {
		return deposit_name;
	}
	/**
	 * 设置 deposit_name
	 * @param deposit_name the deposit_name to set
	 */
	public void setDeposit_name(String deposit_name) {
		this.deposit_name = deposit_name;
	}
	/**
	 * 返回 product_area
	 * @return the product_area
	 */
	public String getProduct_area() {
		return product_area;
	}
	/**
	 * 设置 product_area
	 * @param product_area the product_area to set
	 */
	public void setProduct_area(String product_area) {
		this.product_area = product_area;
	}
	/**
	 * 返回 category1_name
	 * @return the category1_name
	 */
	public String getCategory1_name() {
		return category1_name;
	}
	/**
	 * 设置 category1_name
	 * @param category1_name the category1_name to set
	 */
	public void setCategory1_name(String category1_name) {
		this.category1_name = category1_name;
	}
	/**
	 * 返回 category2_id
	 * @return the category2_id
	 */
	public String getCategory2_id() {
		return category2_id;
	}
	/**
	 * 设置 category2_id
	 * @param category2_id the category2_id to set
	 */
	public void setCategory2_id(String category2_id) {
		this.category2_id = category2_id;
	}
	/**
	 * 返回 category2_name
	 * @return the category2_name
	 */
	public String getCategory2_name() {
		return category2_name;
	}
	/**
	 * 设置 category2_name
	 * @param category2_name the category2_name to set
	 */
	public void setCategory2_name(String category2_name) {
		this.category2_name = category2_name;
	}
	/**
	 * 返回 category3_name
	 * @return the category3_name
	 */
	public String getCategory3_name() {
		return category3_name;
	}
	/**
	 * 设置 category3_name
	 * @param category3_name the category3_name to set
	 */
	public void setCategory3_name(String category3_name) {
		this.category3_name = category3_name;
	}
	/**
	 * 返回 keyword1
	 * @return the keyword1
	 */
	public String getKeyword1() {
		return keyword1;
	}
	/**
	 * 设置 keyword1
	 * @param keyword1 the keyword1 to set
	 */
	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}
	/**
	 * 返回 keyword2
	 * @return the keyword2
	 */
	public String getKeyword2() {
		return keyword2;
	}
	/**
	 * 设置 keyword2
	 * @param keyword2 the keyword2 to set
	 */
	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}
	/**
	 * 返回 keyword3
	 * @return the keyword3
	 */
	public String getKeyword3() {
		return keyword3;
	}
	/**
	 * 设置 keyword3
	 * @param keyword3 the keyword3 to set
	 */
	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}
	/**
	 * 返回 keyword4
	 * @return the keyword4
	 */
	public String getKeyword4() {
		return keyword4;
	}
	/**
	 * 设置 keyword4
	 * @param keyword4 the keyword4 to set
	 */
	public void setKeyword4(String keyword4) {
		this.keyword4 = keyword4;
	}
	/**
	 * 返回 keyword5
	 * @return the keyword5
	 */
	public String getKeyword5() {
		return keyword5;
	}
	/**
	 * 设置 keyword5
	 * @param keyword5 the keyword5 to set
	 */
	public void setKeyword5(String keyword5) {
		this.keyword5 = keyword5;
	}
	/**
	 * 返回 property
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}
	/**
	 * 设置 property
	 * @param property the property to set
	 */
	public void setProperty(String property) {
		this.property = property;
	}
	/**
	 * 返回 main_map
	 * @return the main_map
	 */
	public String getMain_map() {
		return main_map;
	}
	/**
	 * 设置 main_map
	 * @param main_map the main_map to set
	 */
	public void setMain_map(String main_map) {
		this.main_map = main_map;
	}
	/**
	 * 返回 goods_price2
	 * @return the goods_price2
	 */
	public String getGoods_price2() {
		return goods_price2;
	}
	/**
	 * 设置 goods_price2
	 * @param goods_price2 the goods_price2 to set
	 */
	public void setGoods_price2(String goods_price2) {
		this.goods_price2 = goods_price2;
	}
	/**
	 * 返回 currency_unit
	 * @return the currency_unit
	 */
	public String getCurrency_unit() {
		return currency_unit;
	}
	/**
	 * 设置 currency_unit
	 * @param currency_unit the currency_unit to set
	 */
	public void setCurrency_unit(String currency_unit) {
		this.currency_unit = currency_unit;
	}
	/**
	 * 返回 fake_stock
	 * @return the fake_stock
	 */
	public String getFake_stock() {
		return fake_stock;
	}
	/**
	 * 设置 fake_stock
	 * @param fake_stock the fake_stock to set
	 */
	public void setFake_stock(String fake_stock) {
		this.fake_stock = fake_stock;
	}
	/**
	 * 返回 market_price
	 * @return the market_price
	 */
	public String getMarket_price() {
		return market_price;
	}
	/**
	 * 设置 market_price
	 * @param market_price the market_price to set
	 */
	public void setMarket_price(String market_price) {
		this.market_price = market_price;
	}
	/**
	 * 返回 goods_sales
	 * @return the goods_sales
	 */
	public String getGoods_sales() {
		return goods_sales;
	}
	/**
	 * 设置 goods_sales
	 * @param goods_sales the goods_sales to set
	 */
	public void setGoods_sales(String goods_sales) {
		this.goods_sales = goods_sales;
	}
	/**
	 * 返回 up_time
	 * @return the up_time
	 */
	public String getUp_time() {
		return up_time;
	}
	/**
	 * 设置 up_time
	 * @param up_time the up_time to set
	 */
	public void setUp_time(String up_time) {
		this.up_time = up_time;
	}
	/**
	 * 返回 update_time
	 * @return the update_time
	 */
	public String getUpdate_time() {
		return update_time;
	}
	/**
	 * 设置 update_time
	 * @param update_time the update_time to set
	 */
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	/**
	 * 返回 serialversionuid
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GoodsSolr [id=" + id + ", goods_id=" + goods_id + ", goods_code=" + goods_code + ", goods_name="
				+ goods_name + ", goods_describe=" + goods_describe + ", brand_id=" + brand_id + ", brand_cname="
				+ brand_cname + ", brand_ename=" + brand_ename + ", trade_type=" + trade_type + ", trade_type_name="
				+ trade_type_name + ", postage_style=" + postage_style + ", postage_style_name=" + postage_style_name
				+ ", deposit=" + deposit + ", deposit_name=" + deposit_name + ", product_area=" + product_area
				+ ", category1_name=" + category1_name + ", category2_id=" + category2_id + ", category2_name="
				+ category2_name + ", category3_name=" + category3_name + ", keyword1=" + keyword1 + ", keyword2="
				+ keyword2 + ", keyword3=" + keyword3 + ", keyword4=" + keyword4 + ", keyword5=" + keyword5
				+ ", property=" + property + ", main_map=" + main_map + ", goods_price2=" + goods_price2
				+ ", currency_unit=" + currency_unit + ", fake_stock=" + fake_stock + ", market_price=" + market_price
				+ ", goods_sales=" + goods_sales + ", up_time=" + up_time + ", update_time=" + update_time
				+ ", getId()=" + getId() + ", getGoods_id()=" + getGoods_id() + ", getGoods_code()=" + getGoods_code()
				+ ", getGoods_name()=" + getGoods_name() + ", getGoods_describe()=" + getGoods_describe()
				+ ", getBrand_id()=" + getBrand_id() + ", getBrand_cname()=" + getBrand_cname() + ", getBrand_ename()="
				+ getBrand_ename() + ", getTrade_type()=" + getTrade_type() + ", getTrade_type_name()="
				+ getTrade_type_name() + ", getPostage_style()=" + getPostage_style() + ", getPostage_style_name()="
				+ getPostage_style_name() + ", getDeposit()=" + getDeposit() + ", getDeposit_name()="
				+ getDeposit_name() + ", getProduct_area()=" + getProduct_area() + ", getCategory1_name()="
				+ getCategory1_name() + ", getCategory2_id()=" + getCategory2_id() + ", getCategory2_name()="
				+ getCategory2_name() + ", getCategory3_name()=" + getCategory3_name() + ", getKeyword1()="
				+ getKeyword1() + ", getKeyword2()=" + getKeyword2() + ", getKeyword3()=" + getKeyword3()
				+ ", getKeyword4()=" + getKeyword4() + ", getKeyword5()=" + getKeyword5() + ", getProperty()="
				+ getProperty() + ", getMain_map()=" + getMain_map() + ", getGoods_price2()=" + getGoods_price2()
				+ ", getCurrency_unit()=" + getCurrency_unit() + ", getFake_stock()=" + getFake_stock()
				+ ", getMarket_price()=" + getMarket_price() + ", getGoods_sales()=" + getGoods_sales()
				+ ", getUp_time()=" + getUp_time() + ", getUpdate_time()=" + getUpdate_time() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
}
