package com.aurorascm.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.GoodsCommon;
import com.aurorascm.entity.Page;
import com.aurorascm.service.GoodsService;
import com.aurorascm.util.PageData;

/**
 * 描述:客户登录注册ServiceImpl
 * 创建:BYG 2017/5/25
 * 修改:
 * @version 1.0
 */
@Service("goodsServiceImpl")
public class GoodsServiceImpl implements GoodsService{
	
	@Resource(name = "daoSupport")
	private DAO daoSupport;

	/**根据品牌ID,邮寄方式得到品牌所有商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getGoodsListByBrandID(Page page) throws Exception {
		return (List<PageData>)daoSupport.findForList("GoodsReadMapper.getGoodsListByBrandID", page);
	}
	
	/**根据品牌ID,邮寄方式得到品牌所有商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getGNumByBrandID(Page page) throws Exception {
		return (int)daoSupport.findForObject("GoodsReadMapper.getGNumByBrandID", page);
	}
	
	/**搜索页根据综合条件S5得到品牌所有商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getGoodsListByS5(Page page) throws Exception {
		return (List<PageData>)daoSupport.findForList("GoodsReadMapper.getGoodsListByS5", page);
	}
	
	/**获取销量最高的4个商品
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getRand4Goods(PageData pd) throws Exception {
		return (List<PageData>)daoSupport.findForList("GoodsReadMapper.getRand4Goods",pd);
	}

	/**首页根据关键词等值搜索商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GoodsCommon> getGoodsListByKeyword(Page page) throws Exception {
		return (List<GoodsCommon>)daoSupport.findForList("GoodsReadMapper.getGoodsListByKeyword", page);
	}

	/**搜索页根据综合条件S5等值搜索商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GoodsCommon> getGoodsList2ByS5(Page page) throws Exception {
		return (List<GoodsCommon>)daoSupport.findForList("GoodsReadMapper.getGoodsList2ByS5", page);
	}
	
	/**根据关键词模糊查询商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GoodsCommon> getGoodsLikeKeyword(Page page) throws Exception {
		
		//模糊满足所有关键字
		return (List<GoodsCommon>)daoSupport.findForList("GoodsReadMapper.getGoodsLikeKeyword", page);//模糊满足单个关键字
	}
	
	/**根据关键词模糊查询商品总数
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getGNumLikeKeyword(Page page) throws Exception {
		
		
		return (int)daoSupport.findForObject("GoodsReadMapper.getGNumLikeKeyword", page);
	}
	
	/**根据关键词模糊查询商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GoodsCommon> getGoodsLikeS5(Page page) throws Exception {
		return (List<GoodsCommon>)daoSupport.findForList("GoodsReadMapper.getGoodsLikeS5", page);
	}

	/**根据类目等值搜索商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GoodsCommon> getGoodsListByCagegory(Page page) throws Exception {
		return (List<GoodsCommon>)daoSupport.findForList("GoodsReadMapper.getGoodsListByCagegory", page);
	}

	/**根据邮寄方式等值搜索商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GoodsCommon> getGoodsByshipType(Page page) throws Exception {
		return (List<GoodsCommon>)daoSupport.findForList("GoodsReadMapper.getGoodsByshipType", page);
	}
	
	/**根据邮寄方式等值搜索商品总数
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getGNumByshipType(Page page) throws Exception {
		return (int)daoSupport.findForObject("GoodsReadMapper.getGNumByshipType", page);
	}
	
	
	/**根据二级类目ID得到相关推荐商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getLikeGoods(PageData pd) throws Exception {
		return (List<PageData>)daoSupport.findForList("GoodsReadMapper.getLikeGoods", pd);
	}
	
}
