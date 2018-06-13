package com.aurorascm.service;

import java.util.List;

import com.aurorascm.entity.GoodsCategory;
import com.aurorascm.entity.Page;
import com.aurorascm.util.PageData;

public interface HomePageService {
	
	/**获取首页默认淘京热卖数据
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getTJHotSellList(String type)throws Exception;
	
	/**获取一级类目
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getCategory1()throws Exception;
	
	/**获取一级类目【不包含--食品药品、五金卫浴】
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getCategory15()throws Exception;
	
	/**获取一级类目【增加奶粉/不包含--食品药品、五金卫浴】
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getCategory145()throws Exception;
	
	/**根据父级类目搜索子类目
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<GoodsCategory> getCategoryByPID(Object categoryID)throws Exception;
	
	/**根据一级类目ID得到其下二三级类目
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<GoodsCategory> getCategoryByC1ID(Object categoryID)throws Exception;
	
	/**首页本站热卖搜索商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getHomeHotSell(Object category1ID)throws Exception;
	
	/**热门品牌显示数据
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getHotBrand(int pageNum)throws Exception;
	
	/**获取热门品牌最大页码
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public String getHBMaxPN()throws Exception;
	
	/**首页新货推荐显示数据
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getHomeNewGoods(int pageNum)throws Exception;
	
	/**获取新货推荐最大页码
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public String getNGMaxPN()throws Exception;
	
	/**首页保税仓、海外直邮热卖显示数据
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getHomeHBHotSell(int shipType)throws Exception;
	
	/**首页一级类目大额采购显示数据
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getHomeLargeBuy(Object category1ID)throws Exception;
	
	/**首页小额采购模块对应一级目录下的品牌
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getLessBuyBrand(Object category1ID)throws Exception;
	
	/**首页小额采购模块品牌对应下的商品
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getLessBuy(PageData pd)throws Exception;
	
	/**首页小额采购模块其他品牌对应下的商品
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getLessBuyMore(PageData pd)throws Exception;
	
	/**获取首页搜索框下面搜索词
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public String getHomeHotWord()throws Exception;
	
	/**首页获取行业数据
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getHomeIndustryData(int type)throws Exception;

	
	/**
	 * 获取首页四个活动专题
	 * @return
	 */
	public List<PageData> getHomeSpecial() throws Exception;

	/**
	 * 条件查询大额采购页面商品数量
	 * @param page
	 * @return
	 */
	public int getLargeBuyNum(Page page) throws Exception;

	/**
	 * 条件查询大额采购页面商品
	 * @param page
	 * @return
	 */
	public List<PageData> getLargeBuyList(Page page) throws Exception;

	/**
	 * 查询大额采购里所有品牌
	 * @return
	 */
	public List<PageData> getBrandLargeBuy(Page page) throws Exception;
	
}
