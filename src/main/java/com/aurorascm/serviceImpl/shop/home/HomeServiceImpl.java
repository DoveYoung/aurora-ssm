package com.aurorascm.serviceImpl.shop.home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.Result;
import com.aurorascm.entity.home.Category;
import com.aurorascm.redis.RedisUtil;
import com.aurorascm.service.shop.home.HomeService;
import com.aurorascm.util.PageData;
import com.aurorascm.util.RedisConst;

/**
 * @Title: HomeServiceImpl 
 * @Package com.aurorascm.serviceImpl.shop.home; 
 * @Description:  商城首页维护接口实现类
 * @author BYG
 * @date 2018年5月7日 
 * @version V2.0
 */
@Service("homeServiceImpl")
public class HomeServiceImpl implements HomeService{
	
	@Autowired
	private DAO daoSupport;
	@Autowired 
	private RedisUtil redisUtil;
	/**
	 * @Title: getAllCategory
	 * @Description: 获取所有目录（一二三级递归嵌套）
	 * @param    
	 * @return  List<Category>
	 * @author BYG
	 * @date 2018年5月7日
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategory() throws Exception {
		List<Category> category = new ArrayList<Category>();
		int categoryLevel = 1;
		int categoryParentID = 0;
		List<Category> category1 = (List<Category>) daoSupport.findForList("HomeReadMapper.getCategoryListByLevel", categoryLevel);
		if (category1.size() > 0) {
			for (Category c1 : category1) {
				categoryParentID = c1.getCategoryID();
				List<Category> category2 = (List<Category>) daoSupport.findForList("HomeReadMapper.getCategoryListByPID", categoryParentID);
				if (category2.size() > 0) {
					for (Category c2 : category2) {
						categoryParentID = c2.getCategoryID();
						List<Category> category3 = (List<Category>) daoSupport.findForList("HomeReadMapper.getCategoryListByPID", categoryParentID);
						c2.setSubcategory(category3);
					}
				}
				c1.setSubcategory(category2);
			}
		}
		category = category1;
		return category;
	}

	/**
	 * @Title: getHotSell
	 * @Description: 获取首页热卖商品信息
	 * @param    
	 * @return  List<PageData>
	 * @author BYG
	 * @date 2018年5月7日
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getHotSell() throws Exception {
		List<PageData> hotSell = (List<PageData>) daoSupport.findForList("HomeReadMapper.getHotSellTitle");
		if (hotSell.size() > 0) {
			for (PageData t : hotSell) {
				Object titleID = t.get("title_id");
				List<PageData> goods = (List<PageData>) daoSupport.findForList("HomeReadMapper.getHotSellGoodsByTitle",titleID);
				t.put("goods", goods);
			}
		}
		return hotSell;
	}

	/**
	 * @Title: getLargeCargo
	 * @Description: 获取大货集散商品
	 * @param    
	 * @return  List<PageData>
	 * @author BYG
	 * @date 2018年5月7日
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getLargeCargo() throws Exception {
		List<PageData> largeCargo = (List<PageData>) daoSupport.findForList("HomeReadMapper.getLargeCargoCategory");
		if (largeCargo.size() > 0) {
			for (PageData c : largeCargo) {
				Object categoryID = c.get("category_id");
				List<PageData> goods = (List<PageData>) daoSupport.findForList("HomeReadMapper.getLargeCargoGoods",categoryID);
				c.put("goods", goods);
			}
		}
		return largeCargo;
	}

	/**
	 * @Title: getDirectPost
	 * @Description: 获取海外直邮
	 * @param    
	 * @return  List<PageData>
	 * @author BYG
	 * @date 2018年5月7日
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getDirectPost() throws Exception {
		List<PageData> directPost = (List<PageData>) daoSupport.findForList("HomeReadMapper.getDirectPostTitle");
		if (directPost.size() > 0) {
			for (PageData d : directPost) {
				Object titleID = d.get("title_id");
				PageData bannerAndKeywords = (PageData) daoSupport.findForObject("HomeReadMapper.getBannerAndKeywords",titleID);
				//前台太笨只好拆分下
				Object banner = "";
				Object keywords = "";
				if (bannerAndKeywords != null) {
					banner =  bannerAndKeywords.get("banner");
					keywords = bannerAndKeywords.get("keywords");
				}
				d.put("banner", banner);
				d.put("keywords", keywords);
				List<PageData> goods = (List<PageData>) daoSupport.findForList("HomeReadMapper.getDirectPostGoods",titleID);
				d.put("goods", goods);
			}
		}
		return directPost;
	}

	/**
	 * @Title: getCountriesGoods
	 * @Description: 获取各国好货
	 * @param    
	 * @return  List<PageData>
	 * @author BYG
	 * @date 2018年5月7日
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getCountriesGoods() throws Exception {
		List<PageData> countriesGoods = (List<PageData>) daoSupport.findForList("HomeReadMapper.getCountriesGoodsTitle");
		if (countriesGoods.size() > 0) {
			for (PageData c : countriesGoods) {
				Object titleID = c.get("title_id");
				Object banner = daoSupport.findForObject("HomeReadMapper.getCountriesGoodsBanner",titleID);
				List<PageData> goods = (List<PageData>) daoSupport.findForList("HomeReadMapper.getCountriesGoods",titleID);
				c.put("banner", banner);
				c.put("goods", goods);
			}
		}
		return countriesGoods;
	}

	/**
	 * @Title: getTopBrand
	 * @Description: 获取热门品牌
	 * @param    
	 * @return  List<PageData>
	 * @author BYG
	 * @date 2018年5月7日
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getTopBrand(int pageNumber) throws Exception {
		List<PageData> topBrand = (List<PageData>) daoSupport.findForList("HomeReadMapper.getTopBrand",pageNumber);
		if (topBrand.size() > 0) {
			for (PageData b : topBrand) {
				if (b.get("seat").equals(1)) {//第一个图跟其它图不一样
					b.remove("recommend_map");
				} else {
					b.put("brand_show_map", b.get("recommend_map"));
					b.remove("recommend_map");
				}
			}
		}
		return topBrand;
	}

	/**
	 * @Title: getTopBrandMaxPage
	 * @Description: 获取热门品牌最大页
	 * @param    
	 * @return  int
	 * @author BYG
	 * @date 2018年5月7日
	 */
	@Override
	public int getTopBrandMaxPage() throws Exception {
		return (int) daoSupport.findForObject("HomeReadMapper.getTopBrandMaxPage");
	}
	
	/**
	 * @Title: getSearchKeyword 
	 * @Description:查询搜索框热搜词
	 * @param    
	 * @return Result<String>  
	 * @author SSY
	 * @date 2018年5月7日 下午4:15:22
	 */
	@Override
	public Result<String> getSearchKeyword(int keywordType) throws Exception{
		Result<String> result = new Result<String>();	
		String searchKeyword = null;
		Object object = redisUtil.get(RedisConst.HOME_SEARCH);
		if (null==object) {
			searchKeyword = (String)daoSupport.findForObject("HomeKeywordReadMapper.getHomeKeyword",keywordType);
			redisUtil.set(RedisConst.HOME_SEARCH, searchKeyword);
		}else{
			searchKeyword = (String)object;
		}
		result.setResult(searchKeyword);
		return result;
	}

	
}
