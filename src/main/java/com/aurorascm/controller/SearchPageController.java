package com.aurorascm.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aurorascm.entity.Brand;
import com.aurorascm.entity.GoodsCommon;
import com.aurorascm.entity.Page;
import com.aurorascm.service.BrandService;
import com.aurorascm.service.GoodsService;
import com.aurorascm.service.StatisticsService;
import com.aurorascm.service.myzone.AttentionService;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/**
 * 搜索页搜索
 * 
 * @author BYG 2017-7-21
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/searchPage")
public class SearchPageController extends BaseController {

	@Resource(name = "goodsServiceImpl")
	private GoodsService goodsServiceImpl;
	@Resource(name = "brandServiceImpl")
	private BrandService brandServiceImpl;
	@Resource(name = "attentionServiceImpl")
	private AttentionService attentionServiceImpl;
	@Resource(name = "statisticsServiceImpl")
	private StatisticsService statisticsServiceImpl;

	/**
	 * 首页搜索
	 * 
	 * @return
	 * @throws Exception
	 * @param orderBY(1销量,2新货,3价格,4综合)
	 *            orderAD(ASC升 DESC降)
	 */
	@RequestMapping(value = "/search")
	public ModelAndView likeSearch(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String sShipType = Tools.notEmptys(pd.getString("shipType")) ? pd.getString("shipType").replace(" ", "") : null;// 邮寄方式检索条件:1保；2海；3国。
		pd.put("sShipType", sShipType);
		String msg = "";
		List<String> keyword1 = new ArrayList<String>();
		String keyword = Tools.notEmptys(pd.getString("keyword")) ? pd.getString("keyword") : null;// 关键词检索条件
		
		//搜索参数去空格处理
		if(keyword != null){
			keyword1 = new ArrayList<String>(Arrays.asList(keyword.split(" ")));
			String s1 = "";
			for (int i = 0; i < keyword1.size(); i++) {
	            if (!"".equals(keyword1.get(i))) {
	            	s1 += keyword1.get(i) + ",";
	            }
	        }
			keyword1 = new ArrayList<String>(Arrays.asList(s1.split(",")));
			statisticsServiceImpl.saveStatisticKeyWord(keyword1);
		}
	
		String orderBY = Tools.notEmptys(pd.getString("orderBY")) ? pd.getString("orderBY").replace(" ", "") : "4";
		String orderAD = Tools.notEmptys(pd.getString("orderAD")) ? pd.getString("orderAD").replace(" ", "") : "DESC";
		try {
			pd.put("keyword", keyword);
			pd.put("keyword1", keyword1);
			pd.put("orderBY", orderBY);
			pd.put("orderAD", orderAD);
			page.setPd(pd);
			page.setPageSize(20);
			Brand brand = null;
			if (Tools.notEmptys(keyword)) {
				brand = brandServiceImpl.getBrandByKeyword(pd);// 优先进行品牌判断，有等值品牌搜索显示品牌信息及品牌所属商品
			}
			if (null != brand) {
				pd.put("brandID", brand.getBrandID());
				List<PageData> goodsList1 = goodsServiceImpl.getGoodsListByBrandID(page);// 根据品牌ID得到品牌所有商品,条件筛选
				int totalRecord = goodsServiceImpl.getGNumByBrandID(page);// 根据品牌id和邮寄方式搜索商品总数
				page.setTotalRecord(totalRecord);
				List<PageData> category = brandServiceImpl.getCategoryIDByBrandID(pd);// 根据品牌ID得到品牌类目名
				List<PageData> likeBrand1 = brandServiceImpl.getBrandByCategory(category);// 根据品牌类目名得到随机5个类似品牌
				List<Integer> category2ID = new ArrayList<Integer>();
				for (PageData gl1 : goodsList1) {
					if (gl1.get("category2_id") != null && !category2ID.contains(gl1.get("category2_id"))) {
						category2ID.add(Integer.valueOf(gl1.get("category2_id").toString()));// 二级类目id
					}
				}
				pd.put("category2ID", category2ID);
				List<PageData> hotGoods1 = goodsServiceImpl.getRand4Goods(pd);// 获取二级类目下随机的4个商品
				mv.addObject("category", category);
				mv.addObject("brand", brand);
				mv.addObject("goodsList1", goodsList1);
				mv.addObject("likeBrand1", likeBrand1);
				mv.addObject("hotGoods1", hotGoods1);
				mv.addObject("state", 1);
				mv.addObject("pd", pd);
				pd.put("customerID", Jurisdiction.getCustomerID());
				String hasAttention = attentionServiceImpl.getJudgeBrandCared(pd);
				mv.addObject("hasAttention", hasAttention);
			} else {// 没有等值品牌
				List<GoodsCommon> goodsList2 = goodsServiceImpl.getGoodsLikeKeyword(page);// 根据关键词等值搜索商品
				if (null != goodsList2 && !goodsList2.isEmpty()) {
					int totalRecord = goodsServiceImpl.getGNumLikeKeyword(page);// 根据关键词模糊查询商品总数;
					page.setTotalRecord(totalRecord);
					List<String> brands2 = new ArrayList<String>();
					List<String> categoryName = new ArrayList<String>();
					List<Integer> category2ID = new ArrayList<Integer>();
					for (GoodsCommon gl2 : goodsList2) {
						if (gl2.getCategory2() != null && !category2ID.contains(gl2.getCategory2ID())) {
							categoryName.add(gl2.getCategory2());// 二级类目名，用于分类显示
							category2ID.add(gl2.getCategory2ID());// 二级类目名，用于分类显示
						}
						if (!brands2.contains(gl2.getBrandName())) {
							brands2.add(gl2.getBrandName());// 所有商品的品牌，用于品牌显示
						}
					}
					List<PageData> likeBrand2 = brandServiceImpl.getBrandByCategory2ID(category2ID);// 根据商品二级类目ID得到随机5个类似品牌
					pd.put("category2ID", category2ID);
					List<PageData> hotGoods2 = goodsServiceImpl.getRand4Goods(pd);// 获取二级类目下随机的4个商品
					mv.addObject("categoryName", categoryName);
					mv.addObject("brands2", brands2);
					mv.addObject("goodsList2", goodsList2);
					mv.addObject("likeBrand2", likeBrand2);
					mv.addObject("hotGoods2", hotGoods2);
					mv.addObject("state", 2);
					mv.addObject("pd", pd);
				} else {// 啥也没有的话
					mv.addObject("state", 4);
					List<PageData> hotGoods4 = goodsServiceImpl.getRand4Goods(pd);// 得到随机的4个商品
					mv.addObject("hotGoods4", hotGoods4);
					mv.addObject("pd", pd);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "网络异常！请稍后再试！";
			logger.info("【CSPC: 搜索系统执行异常！】");
		}
		mv.setViewName("system/search/searchPage");
		mv.addObject("msg", msg);
		return mv;
	}

	/**
	 * 首页保税仓热卖/海外直邮热卖查看更多
	 * 
	 * @return
	 * @throws Exception
	 * @param orderBY(1销量,2新货,3价格,4综合)
	 *            orderAD(ASC升 DESC降)
	 */
	@RequestMapping(value = "/shipTypeSearch", produces = "application/json;charset=UTF-8")
	public ModelAndView shipTypeSearch(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String msg = "";
		String shipType = Tools.notEmptys(pd.getString("shipType")) ? pd.getString("shipType").replace(" ", "") : null;
		String orderBY = Tools.notEmptys(pd.getString("orderBY")) ? pd.getString("orderBY").replace(" ", "") : "4";
		String orderAD = Tools.notEmptys(pd.getString("orderAD")) ? pd.getString("orderAD").replace(" ", "") : "DESC";
		pd.put("shipType", shipType);
		pd.put("orderBY", orderBY);
		pd.put("orderAD", orderAD);
		page.setPd(pd);
		page.setPageSize(40);
		try {
			List<GoodsCommon> goodsList2 = goodsServiceImpl.getGoodsByshipType(page);// 根据邮寄方式 ,搜索商品  ,排序分页
			int totalRecord = goodsServiceImpl.getGNumByshipType(page);
			page.setTotalRecord(totalRecord);
			if (null != goodsList2 && !goodsList2.isEmpty()) {
				List<String> brands2 = new ArrayList<String>();
				List<String> categoryName = new ArrayList<String>();
				List<Integer> category2ID = new ArrayList<Integer>();
				for (GoodsCommon gl2 : goodsList2) {
					if (gl2.getCategory2() != null) {
						categoryName.add(gl2.getCategory2());// 二级类目名，用于分类显示
						category2ID.add(gl2.getCategory2ID());// 二级类目ID，用于分类显示
					}
					if (!brands2.contains(gl2.getBrandName())) {
						brands2.add(gl2.getBrandName());// 所有商品的品牌，用于品牌显示
					}
				}
				List<PageData> likeBrand2 = brandServiceImpl.getBrandByCategory2ID(category2ID);// 根据商品二级类目ID得到5个类似品牌
				pd.put("category2ID", category2ID);
				List<PageData> hotGoods2 = goodsServiceImpl.getRand4Goods(pd);// 得到随机的4个商品
				mv.addObject("categoryName", categoryName);
				mv.addObject("brands2", brands2);
				mv.addObject("goodsList2", goodsList2);
				mv.addObject("likeBrand2", likeBrand2);
				mv.addObject("hotGoods2", hotGoods2);
				mv.addObject("state", 2);
				mv.addObject("pd", pd);
				mv.setViewName("system/search/searchPage");
			} else {
				mv.addObject("state", 4);
				List<PageData> hotGoods4 = goodsServiceImpl.getRand4Goods(pd);// 得到随机的4个商品
				mv.addObject("hotGoods4", hotGoods4);
				mv.addObject("pd", pd);
				mv.setViewName("system/search/searchPage");
			}
		} catch (Exception e) {
			msg = "网络异常！请稍后再试！";
			logger.info("【CSPC: 首页保税仓热卖/海外直邮热卖查看更多异常！】");
		}
		mv.addObject("msg", msg);
		return mv;
	}

	/**
	 * 首页小额批发查看更多
	 * 
	 * @return
	 * @param orderBY(1销量,2新货,3价格,4综合)
	 *            orderAD(ASC升 DESC降)
	 * @throws Exception
	 */
	@RequestMapping(value = "/seeMoreByBID")
	public ModelAndView seeMoreByBID(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String msg = "";
		try {
			String sShipType = Tools.notEmptys(pd.getString("shipType")) ? pd.getString("shipType").replace(" ", "") : null;// 邮寄方式检索条件:1保；2海；3国。
			String brandID = Tools.notEmptys(pd.getString("brandID")) ? pd.getString("brandID").replace(" ", "") : null;// 关键词检索条件
			String orderBY = Tools.notEmptys(pd.getString("orderBY")) ? pd.getString("orderBY").replace(" ", "") : "4";
			String orderAD = Tools.notEmptys(pd.getString("orderAD")) ? pd.getString("orderAD").replace(" ", "") : "DESC";
			if (null != brandID) {
				pd.put("sShipType", sShipType);
				pd.put("brandID", brandID);
				pd.put("orderBY", orderBY);
				pd.put("orderAD", orderAD);
				page.setPd(pd);
				page.setPageSize(16);
				Brand brand = brandServiceImpl.getBrandByID(brandID);
				List<PageData> goodsList1 = goodsServiceImpl.getGoodsListByBrandID(page);// 根据品牌ID
																							// 邮寄方式得到品牌所有商品
				int totalRecord = goodsServiceImpl.getGNumByBrandID(page);// 根据品牌id和邮寄方式搜索商品总数
				page.setTotalRecord(totalRecord);
				List<PageData> category = brandServiceImpl.getCategoryIDByBrandID(pd);// 根据品牌ID得到品牌类目名
				List<PageData> likeBrand1 = brandServiceImpl.getBrandByCategory(category);// 根据品牌类目名得到5个类似品牌
				List<PageData> hotGoods1 = goodsServiceImpl.getRand4Goods(pd);// 得到随机的4个商品
				mv.addObject("category", category);
				mv.addObject("brand", brand);
				mv.addObject("goodsList1", goodsList1);
				mv.addObject("likeBrand1", likeBrand1);
				mv.addObject("hotGoods1", hotGoods1);
				mv.addObject("state", 1);
				mv.addObject("pd", pd);
				mv.setViewName("system/search/searchPage");
			} else {
				mv.setViewName("system/index/homePage");
			}
		} catch (Exception e) {
			msg = "网络异常！请稍后再试！";
			logger.info("【CSPC： 系统执行首页小额批发查看更多异常！】");
		}
		mv.addObject("msg", msg);
		return mv;
	}
	
	/**
	 * @Title: searchBrand
	 * @Description: 热门品牌查询品牌详情
	 * @param    
	 * @return Object  
	 * @author BYG
	 * @date 2018年5月15日 
	 */
	@RequestMapping(value = "/searchBrand")
	public ModelAndView searchBrand(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String msg = "";
		String sShipType = Tools.notEmptys(pd.getString("shipType")) ? pd.getString("shipType").replace(" ", "") : null;// 邮寄方式检索条件:1保；2海；3国。
		String brandID = Tools.notEmptys(pd.getString("brandID")) ? pd.getString("brandID") : null;// 关键词检索条件
		String orderBY = Tools.notEmptys(pd.getString("orderBY")) ? pd.getString("orderBY").replace(" ", "") : "4";
		String orderAD = Tools.notEmptys(pd.getString("orderAD")) ? pd.getString("orderAD").replace(" ", "") : "DESC";
		if (brandID != null) {
			try {
				pd.put("brandID", brandID);
				pd.put("sShipType", sShipType);
				pd.put("orderBY", orderBY);
				pd.put("orderAD", orderAD);
				page.setPd(pd);
				page.setPageSize(20);
				Brand brand = brandServiceImpl.getBrandByID(brandID);
				if (null != brand) {
					List<PageData> goodsList1 = goodsServiceImpl.getGoodsListByBrandID(page);// 根据品牌ID得到品牌所有商品,条件筛选
					int totalRecord = goodsServiceImpl.getGNumByBrandID(page);// 根据品牌id和邮寄方式搜索商品总数
					page.setTotalRecord(totalRecord);
					List<PageData> category = brandServiceImpl.getCategoryIDByBrandID(pd);// 根据品牌ID得到品牌类目名
					List<PageData> likeBrand1 = brandServiceImpl.getBrandByCategory(category);// 根据品牌类目名得到随机5个类似品牌
					List<Integer> category2ID = new ArrayList<Integer>();
					for (PageData gl1 : goodsList1) {
						if (gl1.get("category2_id") != null && !category2ID.contains(gl1.get("category2_id"))) {
							category2ID.add(Integer.valueOf(gl1.get("category2_id").toString()));// 二级类目id
						}
					}
					pd.put("category2ID", category2ID);
					List<PageData> hotGoods1 = goodsServiceImpl.getRand4Goods(pd);// 获取二级类目下随机的4个商品
					mv.addObject("category", category);
					mv.addObject("brand", brand);
					mv.addObject("goodsList1", goodsList1);
					mv.addObject("likeBrand1", likeBrand1);
					mv.addObject("hotGoods1", hotGoods1);
					mv.addObject("state", 1);
					mv.addObject("pd", pd);
					pd.put("customerID", Jurisdiction.getCustomerID());
					String hasAttention = attentionServiceImpl.getJudgeBrandCared(pd);
					mv.addObject("hasAttention", hasAttention);
				}
			} catch (Exception e) {
				e.printStackTrace();
				msg = "网络异常！请稍后再试！";
				logger.info("【CSPC: 搜索系统执行异常！】");
			}
		}else {
			msg = "参数异常！";
		}
		mv.setViewName("system/search/searchPage");
		mv.addObject("msg", msg);
		return mv;
	}

}
