package com.aurorascm.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.GoodsCategory;
import com.aurorascm.entity.Page;
import com.aurorascm.service.HomePageService;
import com.aurorascm.util.PageData;

/**
 * 描述:客户登录注册ServiceImpl
 * 创建:BYG 2017/5/25
 * 修改:
 * @version 1.0
 */
@Service("homePageServiceImpl")
public class HomePageServiceImpl implements HomePageService{
	
	@Resource(name = "daoSupport")
	private DAO daoSupport;

	/**根据TJ类型获取首页淘京热卖数据
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getTJHotSellList(String type) throws Exception {
		return (List<PageData>)daoSupport.findForList("HomePageReadMapper.getTJHotSellList", type);
	}

	/**获取一级类目
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getCategory1() throws Exception {
		return (List<PageData>)daoSupport.findForList("HomePageReadMapper.getCategory1");
	}
	
	/**获取一级类目【不包含--食品药品、五金卫浴】
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getCategory15() throws Exception {
		return (List<PageData>)daoSupport.findForList("HomePageReadMapper.getCategory15");
	}
	
	/**获取一级类目【增加奶粉/不包含--食品药品、五金卫浴】
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getCategory145() throws Exception {
		return (List<PageData>)daoSupport.findForList("HomePageReadMapper.getCategory145");
	}
	
	/**根据一级类目ID得到其下二三级类目
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<GoodsCategory> getCategoryByC1ID(Object  categoryID) throws Exception {
		List<GoodsCategory> category = this.getCategoryByPID(categoryID);//所有二级类目;
		for(GoodsCategory gc : category){
			gc.setSubCategory(this.getCategoryByC1ID(gc.getCategoryID()));
		}
		return category;
	}
	
	/**根据父级类目搜索子类目
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GoodsCategory> getCategoryByPID(Object categoryID) throws Exception {
		return (List<GoodsCategory>)daoSupport.findForList("HomePageReadMapper.getCategoryByPID",categoryID);
	}
	
	/**首页本站热卖商品展示
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getHomeHotSell(Object category1ID) throws Exception {
		return (List<PageData>)daoSupport.findForList("HomePageReadMapper.getHomeHotSell", category1ID);
	}
	
	/**首页获取热门品牌最大页码
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getHBMaxPN() throws Exception {
		return (String)daoSupport.findForObject("HomePageReadMapper.getHBMaxPN");
	}

	/**首页热门品牌显示数据
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getHotBrand(int pageNum) throws Exception {
		return (List<PageData>)daoSupport.findForList("HomePageReadMapper.getHotBrand", pageNum);
	}
	
	/**获取新货推荐最大页码
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getNGMaxPN() throws Exception {
		return (String)daoSupport.findForObject("HomePageReadMapper.getNGMaxPN");
	}

	/**首页新货推荐显示数据
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getHomeNewGoods(int pageNum) throws Exception {
		return (List<PageData>)daoSupport.findForList("HomePageReadMapper.getHomeNewGoods", pageNum);
	}

	/**首页保税仓、海外直邮热卖显示数据
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getHomeHBHotSell(int shipType) throws Exception {
		return (List<PageData>)daoSupport.findForList("HomePageReadMapper.getHomeHBHotSell", shipType);
	}

	/**首页一级类目大额采购显示数据
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getHomeLargeBuy(Object category1ID) throws Exception {
		return (List<PageData>)daoSupport.findForList("HomePageReadMapper.getHomeLargeBuy", category1ID);
	}

	/**首页小额采购模块对应一级目录下的品牌
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getLessBuyBrand(Object category1ID) throws Exception {
		return (List<PageData>)daoSupport.findForList("HomePageReadMapper.getLessBuyBrand", category1ID);
	}

	/**首页小额采购模块品牌对应下的商品
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getLessBuy(PageData pd) throws Exception {
		return (List<PageData>)daoSupport.findForList("HomePageReadMapper.getLessBuy", pd);
	}
	
	/**首页小额采购模块其他品牌对应下的商品
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getLessBuyMore(PageData pd) throws Exception {
		return (List<PageData>)daoSupport.findForList("HomePageReadMapper.getLessBuyMore", pd);
	}

	/**获取首页搜索框下面搜索词
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getHomeHotWord() throws Exception {
		return (String)daoSupport.findForObject("HomePageReadMapper.getHomeHotWord");
	}

	/**首页获取行业数据
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getHomeIndustryData(int type) throws Exception {
		return (List<PageData>)daoSupport.findForList("HomePageReadMapper.getHomeIndustryData", type);
	}
	
	/**
	 * 获取首页四个活动专题
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getHomeSpecial() throws Exception{
		return (List<PageData>)daoSupport.findForList("HomePageReadMapper.getHomeSpecial");	
	}
	
	/**
	 * 条件查询大额采购页面商品数量
	 * @param page
	 * @return
	 */
	@Override
	public int getLargeBuyNum(Page page) throws Exception{
		return (int)daoSupport.findForObject("HomePageReadMapper.getLargeBuyNum",page);
	}
	
	/**
	 * 条件查询大额采购页面商品
	 * @param page
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> getLargeBuyList(Page page) throws Exception{
		return (List<PageData>)daoSupport.findForList("HomePageReadMapper.getLargeBuyList",page);	
	}
	
	/**
	 * 查询大额采购里所有品牌
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getBrandLargeBuy(Page page) throws Exception{
		return (List<PageData>)daoSupport.findForList("HomePageReadMapper.getBrandLargeBuy",page);	
	}
	
	
}
