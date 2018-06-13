package com.aurorascm.service;

import java.util.List;

import com.aurorascm.entity.GoodsManage;
import com.aurorascm.entity.home.TimedActivity;
import com.aurorascm.util.PageData;

public interface GManageService {
	
	/**通过商品ID获取商品管理信息
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getGManage(PageData pd)throws Exception;
	
	/**通过商品ID获取商品管理信息和详情（详情页）
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public GoodsManage getGoodsDM(String goodsID)throws Exception;
	
	/**通过商品ID获取商品信息.（所有  pd）
	 * @param  goodsID
	 * @return
	 * @throws Exception
	 */
	public PageData getPDGoodsByID(String goodsID) throws Exception;
	
	/**通过商品ID获取商品邮寄方式
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public int getShipType(String goodsID)throws Exception;
	
	/**获取同商品不同邮寄方式的商品详情信息（详情页）
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<GoodsManage> getRGoods(PageData pd)throws Exception;
	
	/**通过商品ID获取商品管理信息for购买
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public GoodsManage getGMDForLB(PageData pd)throws Exception;

	/**
	 * 根据商品信息,用户id,统计用户浏览该类目商品次数+1,商品被点击量+1
	 * @throws Exception
	 */
	public void addCustomerCategoryClickNum(GoodsManage goodsManage) throws Exception;

	/**
	 * 根据商品信息,统计商品点击次数+1;
	 * @throws Exception
	 */
	public void addGoodsClickNum(GoodsManage goodsManage) throws Exception;

	/**
	 * @Title: getActivityGoods 
	 * @Description:  查询该商品的促销价格活动信息;
	 * @param    
	 * @return void  
	 * @author SSY
	 * @date 2018年5月15日 上午10:53:39
	 */
	public TimedActivity getActivityGoods(String goodsID) throws Exception;
}
