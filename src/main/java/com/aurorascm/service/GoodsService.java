package com.aurorascm.service;

import java.util.List;

import com.aurorascm.entity.GoodsCommon;
import com.aurorascm.entity.Page;
import com.aurorascm.util.PageData;

public interface GoodsService {
	
	/**默认根据品牌ID,邮寄方式得到品牌所有商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getGoodsListByBrandID(Page page)throws Exception;	
	
	/**根据品牌ID,邮寄方式得到品牌所有商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public int getGNumByBrandID(Page page) throws Exception;
	
	/**搜索页根据综合条件S5得到品牌所有商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getGoodsListByS5(Page page)throws Exception;
	
	/**根据二级类目ID得到相关推荐商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getLikeGoods(PageData pd)throws Exception;
	
	/**获取销量最高的4个商品
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getRand4Goods(PageData pd)throws Exception;
	
	/**首页根据关键词等值搜索商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<GoodsCommon> getGoodsListByKeyword(Page page)throws Exception;
	
	/**搜索页根据综合条件S5等值搜索商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<GoodsCommon> getGoodsList2ByS5(Page page)throws Exception;
	
	/**首页根据关键词模糊查询商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<GoodsCommon> getGoodsLikeKeyword(Page page)throws Exception;
	
	/**根据关键词模糊查询商品总数
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public int getGNumLikeKeyword(Page page) throws Exception;
	
	
	/**搜索页根据关键词模糊及S4查询商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<GoodsCommon> getGoodsLikeS5(Page page)throws Exception;
	
	/**根据类目等值搜索商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<GoodsCommon> getGoodsListByCagegory(Page page)throws Exception;
	
	/**根据邮寄方式等值搜索商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<GoodsCommon> getGoodsByshipType(Page page)throws Exception;

	/**根据邮寄方式等值搜索商品总数
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public int getGNumByshipType(Page page) throws Exception;
 

		
}
