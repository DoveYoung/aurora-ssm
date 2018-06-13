package com.aurorascm.controller.shop.home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.aurorascm.controller.BaseController;
import com.aurorascm.entity.Brand;
import com.aurorascm.entity.Page;
import com.aurorascm.entity.home.GoodsSolr;
import com.aurorascm.service.BrandService;
import com.aurorascm.service.GoodsService;
import com.aurorascm.service.StatisticsService;
import com.aurorascm.service.myzone.AttentionService;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;
import com.aurorascm.util.SolrUtil;
import com.aurorascm.util.Tools;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;

/**搜索
 * @author BYG 2017-12-16
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController extends BaseController {

	@Autowired SolrUtil solrUtil;
	@Autowired BrandService brandServiceImpl;
	@Autowired GoodsService goodsServiceImpl;
	@Autowired AttentionService attentionServiceImpl;
	@Autowired StatisticsService statisticsServiceImpl;
	
	/**搜索词自动补全
	 * @author BYG
	 * @param 关键词--keyword
	 * @throws Exception
	 * @return 补全集合--autoComplete
	 */
	@RequestMapping(value="/autoComplete", produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<String> likeSearch(String keyword) throws Exception {
		List<String> autoComplete = solrUtil.autoComplete(keyword);
		return autoComplete;
	}
	
	/**搜索
	 * @param 关键词--keyword；贸易方式--shipType；排序方式--orderBY(1销量,2新货,3价格,4综合)；
	 * 		      排序规则--orderAD（DESC降序，ASC升序）；页码--pageNum
	 * @throws Exception
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/search")
	public ModelAndView likeSearch(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String keyword = Tools.notEmptys(pd.getString("keyword")) ? pd.getString("keyword") : null;// 关键词检索条件
		String tradeType = Tools.notEmptys(pd.getString("shipType")) ? pd.getString("shipType") : "1";// 关键词检索条件（若贸易方式条件为空，则默认德国自提）
		String orderBY = Tools.notEmptys(pd.getString("orderBY")) ? pd.getString("orderBY").replace(" ", "") : "4";
		String orderAD = Tools.notEmptys(pd.getString("orderAD")) ? pd.getString("orderAD").replace(" ", "") : "DESC";
		String pageNum = Tools.notEmptys(pd.getString("pageNum")) ? pd.getString("pageNum").replace(" ", "") : "1";//页码默认第一页
		pd.put("keyword", keyword);
		pd.put("tradeType", tradeType);
		pd.put("orderBY", orderBY);
		pd.put("orderAD", orderAD);
		pd.put("pageNum", pageNum);
		pd.put("pageSize", 20);
		page.setPd(pd);
		page.setPageSize(20);
		try {
			Brand brand = null;
			if (Tools.notEmptys(keyword)) {
//				JiebaSegmenter segmenter = new JiebaSegmenter();//jieba中文分词
//				List<String> keyword1 = segmenter.sentenceProcess(keyword);
//				for (int i = 0; i < keyword1.size(); i++) {
//					if (keyword1.get(i) == " ") {
//						keyword1.remove(i);
//					}
//				}
				List<String> keyword1 = new ArrayList<String>();
				keyword1 = new ArrayList<String>(Arrays.asList(keyword.split(" ")));
				String s1 = "";
				for (int i = 0; i < keyword1.size(); i++) {
		            if (!"".equals(keyword1.get(i))) {
		            	s1 += keyword1.get(i) + ",";
		            }
		        }
				pd.put("keyword1", keyword1);
				brand = brandServiceImpl.getBrandByKeyword(pd);// 优先进行品牌判断，有等值品牌搜索显示品牌信息及品牌所属商品
			}
			if (brand != null ) {//品牌搜索结果不空			
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
			}else{//没有搜索到品牌，搜商品
				Map<String, Object> map2 = solrUtil.queryByKeyword(pd);
				List<GoodsSolr> goodsSolr2 = new ArrayList<GoodsSolr>();
				List<GoodsSolr> likeGoodsSolr2 = new ArrayList<GoodsSolr>();
				goodsSolr2 = (List<GoodsSolr>) map2.get("goodsSolr");
				if(goodsSolr2.size() >= 1){
					Object totalRecord2 = map2.get("totalRecord");
					pd.put("totalRecord2", totalRecord2);
					List<String> brandNames2 = new ArrayList<String>();
					List<String> brandIDs = new ArrayList<String>();
					for(GoodsSolr gs : goodsSolr2){
						String brandName = gs.getBrand_cname() + gs.getBrand_ename();
						if (!brandNames2.contains(brandName)) {
							brandNames2.add(brandName);
						}
						String brandID = gs.getBrand_id();
						if (!brandIDs.contains(brandID)) {
							brandIDs.add(brandID);
						}
					}
					List<PageData> likeBrand2 = brandServiceImpl.get5BrandByBrandID(brandIDs);// 根据品牌ID集合得到5个类似品牌
					Map<String, Object> likeMap2 = solrUtil.queryRandom4(pd);//搜索随机四个商品
					likeGoodsSolr2 = (List<GoodsSolr>) likeMap2.get("goodsSolr");
					mv.addObject("category2", goodsSolr2.get(0).getCategory2_name());
					mv.addObject("brandNames2", JSON.toJSON(brandNames2));
					mv.addObject("goodsSolr2", JSON.toJSON(goodsSolr2));
					mv.addObject("likeBrand2", JSON.toJSON(likeBrand2));
					mv.addObject("likeGoodsSolr2", JSON.toJSON(likeGoodsSolr2));
					mv.addObject("state", 2);
					mv.addObject("pd", JSON.toJSON(pd));
				}else {//没有搜索到任何结果
					Map<String, Object> map3 = solrUtil.queryRandom4(pd);
					List<GoodsSolr> goodsSolr3 = (List<GoodsSolr>) map3.get("goodsSolr");
					mv.addObject("goodsSolr3", JSON.toJSON(goodsSolr3));
					mv.addObject("state", 3);
					mv.addObject("pd", JSON.toJSON(pd));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("system/search/searchPage");
		return mv;
	}



}
