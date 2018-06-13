package com.aurorascm.service;

import java.util.List;

import com.aurorascm.util.PageData;

public interface DataService {
	
	/**根据商品ID获取商品详情页行业数据（淘宝售价  京东售价 本站售价 淘宝在售商家）
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getJPriceData(String goodsID)throws Exception;
	public List<PageData> getTPriceData(String goodsID)throws Exception;
	public List<PageData> getTStoreData(String goodsID)throws Exception;
	public List<PageData> getAPriceData(String goodsID)throws Exception;
	
	
	
//	TODO:↓↓↓↓↓↓↓↓↓↓↓↓下面注释时间SSY2017-11-9  数据统计重新做!↓↓↓↓↓↓↓↓↓↓↓
	
	/**根据session创建来累计每天网站浏览次数
	 * @param 
	 * @return
	 * @throws Exception
	 *//*
	public void updateWebBrowsingCount() throws Exception;
	
	*//**商品详情页每访问一次对应商品点击次数加1
	 * @param 
	 * @return
	 * @throws Exception
	 *//*
	public void updateGoodsVCD(String goodsID) throws Exception;
	
	*//**首页每天每登陆一次记录一次IP（若已有不记录）,相应更新访客数，ip每天定时清除。
	 * @param 
	 * @return
	 * @throws Exception
	 *//*
	public void updateVisitorCount() throws Exception;
	*/
	
}
