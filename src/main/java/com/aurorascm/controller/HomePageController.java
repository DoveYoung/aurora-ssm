package com.aurorascm.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aurorascm.entity.GoodsCategory;
import com.aurorascm.redis.RedisUtil;
import com.aurorascm.service.HomePageService;
import com.aurorascm.service.StatisticsService;
import com.aurorascm.util.AppUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/** 系统首页
 * @author BYG 2017-7-21
 * @version 1.0
 */
@Controller
@RequestMapping(value="/homePage")
public class HomePageController extends BaseController {
	
	@Resource(name="homePageServiceImpl")
	private HomePageService homePageServiceImpl;
	@Resource(name="redisUtil")
	private RedisUtil redisUtil;
	@Resource(name="statisticsServiceImpl")
	private StatisticsService statisticsServiceImpl;
	
	/**
	 * 访问首页  
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goHomePage")
	public ModelAndView goHomePage()throws Exception{
		ModelAndView mv = this.getModelAndView();
		Object spread = this.getRequest().getAttribute("spread");
		statisticsServiceImpl.updateSpreadVisitNum(spread);//统计推广源访问次数
		this.getHomeHotWord();//搜索框下热搜词保存到session中homeHotWordList
		this.getCatalog();//目录放入session
		ArrayList<List<PageData>> IndustryData = this.getHomeIndustryData();//首页行业数据
		List<PageData>	tHotSell = this.getTJHotSell("t");//淘宝热卖
		List<PageData>	jHotSell = this.getTJHotSell("j");//京东热卖
		List<PageData> homeSpecial = this.getHomeSpecial();//首页四个活动专题板块;
		mv.addObject("IndustryData",IndustryData);//行业数据
		mv.addObject("tHotSell",tHotSell);//淘宝热卖
		mv.addObject("jHotSell",jHotSell);//京东热卖
		mv.addObject("homeSpecial",homeSpecial);// 首页四个活动专题板块;
		mv.setViewName("system/index/homePage");
		return mv;
	}
	
	/**首页目录请求
	 * @param pageNum
	 * @return List<PageData>	hotBrand
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getCatalog", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getCatalog()throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		Session session = Jurisdiction.getSession();//类目信息放到session中
		//热搜词
		List<String> homeHotWordList = new ArrayList<String>();
		Object redisHomeHotWordList =  redisUtil.get("shHomeHotWordList");
		if (redisHomeHotWordList != null) {
			homeHotWordList = (List<String>) redisHomeHotWordList;
			session.setAttribute("homeHotWordList", homeHotWordList);
		}else {
			try {
				String homeHotWord = homePageServiceImpl.getHomeHotWord();
				if (Tools.notEmptys(homeHotWord)) {
					String[] homeHotWordArry = homeHotWord.split(",");
					homeHotWordList = new ArrayList<String>(Arrays.asList(homeHotWordArry));
					session.setAttribute("homeHotWordList", homeHotWordList);
					redisUtil.set("shHomeHotWordList", homeHotWordList);
				}
			} catch (Exception e) {
				logger.info("搜索框下搜索词获取执行异常！");
			}
		}
		//目录
		List<PageData>	category1 = new ArrayList<PageData>();
		ArrayList<List<GoodsCategory>> category23 = new ArrayList<List<GoodsCategory>>();
		Object redisCategory1 =  redisUtil.get("shCategory1");
		Object redisCategory23 =  redisUtil.get("shCategory23");
		if (redisCategory1 != null && redisCategory23 != null) {
			session.setAttribute("category1ALL", redisCategory1);
			session.setAttribute("categoryALL", redisCategory23);
			result = "success";
		} else {
			try {
				category1 = homePageServiceImpl.getCategory1();		//得到全部一级类目ID/名称
				Object[] aCategory1ID = new Object[]{category1.get(0).get("category_id"),category1.get(1).get("category_id"),category1.get(2).get("category_id"),
						category1.get(3).get("category_id"),category1.get(4).get("category_id"),category1.get(5).get("category_id"),category1.get(6).get("category_id")};
				for (int i = 0; i < aCategory1ID.length; i++) {		//二三级类目
					List<GoodsCategory>	iCategory = homePageServiceImpl.getCategoryByC1ID(aCategory1ID[i]);
					category23.add(iCategory);
				}
				session.setAttribute("category1ALL", category1);
				session.setAttribute("categoryALL", category23);
				redisUtil.set("shCategory1", category1);
				redisUtil.set("shCategory23", category23);
				result = "success";
			} catch (Exception e) {
				result = "failed";
				msg = "类目获取异常";
				logger.info("首页类目获取异常！");
			}
		}
		map.put("result", result);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
	}
	
	/**异步获取首页--本站热卖
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getHSiteHS", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getHSiteHS()throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		ArrayList<List<PageData>> hotSellList = new ArrayList<List<PageData>>();
		List<PageData>	category1 = new ArrayList<PageData>();
		Object redisHotSellList =  redisUtil.get("shHotSellList");
		Object redisCategory1 =  redisUtil.get("shHotSellCategory1");
		if (redisHotSellList != null && redisCategory1 != null) {
			hotSellList = (ArrayList<List<PageData>>) redisHotSellList;
			category1 = (List<PageData>) redisCategory1;
			result = "success";
		} else {
			try {
				category1 = homePageServiceImpl.getCategory145();//得到全部一级类目ID/名称【增加爱他美+喜宝/不包含--食品药品、五金卫浴】
				Object[] aCategory1ID = new Object[]{category1.get(0).get("category_id"),category1.get(1).get("category_id"),category1.get(2).get("category_id"),
					category1.get(3).get("category_id"),category1.get(4).get("category_id"),category1.get(5).get("category_id"),category1.get(6).get("category_id")};
				for (int i = 0; i < aCategory1ID.length; i++) {
					List<PageData>	iHotSellList = homePageServiceImpl.getHomeHotSell(aCategory1ID[i]);
					hotSellList.add(iHotSellList);
				}
				redisUtil.set("shHotSellCategory1", category1);
				redisUtil.set("shHotSellList", hotSellList);
				result = "success";
			} catch (Exception e) {
				result = "failed";
				msg = "网络异常！请稍后再试！";
				logger.info("CHPC:本站热卖获取异常!");
			}
		}
		map.put("category1", category1);
		map.put("hotSellList", hotSellList);
		map.put("result", result);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
	}
		
	/**异步获取首页--保税仓/海外直邮热卖+获取大/小额采购商品
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getHBHLL", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getHBHLL()throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "success";
		String msg = "";
		List<PageData>	homeBHotSell = new ArrayList<PageData>();
		Object redisHomeBHotSell =  redisUtil.get("shHomeBHotSell");
		if (redisHomeBHotSell != null ) {
			homeBHotSell = (List<PageData>) redisHomeBHotSell;
		} else {
			try {
				homeBHotSell = homePageServiceImpl.getHomeHBHotSell(1);//1保税仓热卖/2海外直邮热卖
				redisUtil.set("shHomeBHotSell", homeBHotSell);
			} catch (Exception e) {
				result = "failed";
				msg = "数据异常";
				logger.info("首页保税仓热卖数据获取异常！");
			}
		}
		List<PageData>	homeHHotSell = new ArrayList<PageData>();
		Object redisHomeHHotSell =  redisUtil.get("shHomeHHotSell");
		if (redisHomeHHotSell != null ) {
			homeHHotSell = (List<PageData>) redisHomeHHotSell;
		} else {
			try {
				homeHHotSell = homePageServiceImpl.getHomeHBHotSell(2);//1保税仓热卖/2海外直邮热卖
				redisUtil.set("shHomeHHotSell", homeHHotSell);
			} catch (Exception e) {
				result = "failed";
				msg = "数据异常";
				logger.info("首页海外直邮热卖数据获取异常！");
			}
		}
		List<PageData>	category1 = new ArrayList<PageData>();
		ArrayList<Object> homePurchase = new ArrayList<Object>();
		ArrayList<List<PageData>> lessBuyBrand = new ArrayList<List<PageData>>();
		Object redisHomePurchase =  redisUtil.get("shHomePurchase");
		Object redisLessBuyBrand =  redisUtil.get("shLessBuyBrand");
		Object redisCategory1=  redisUtil.get("shPurchaseCategory1");
		if (redisHomePurchase != null && redisLessBuyBrand != null && redisCategory1 != null) {
			category1 = (List<PageData>) redisCategory1;
			homePurchase = (ArrayList<Object>) redisHomePurchase;
			lessBuyBrand = (ArrayList<List<PageData>>) redisLessBuyBrand;
		} else {
			try {
				category1 = homePageServiceImpl.getCategory15();//得到全部一级类目ID/名称【不包含--食品药品、五金卫浴】
				Object[] aCategory1ID = new Object[]{category1.get(0).get("category_id"),category1.get(1).get("category_id"),category1.get(2).get("category_id"),
						category1.get(3).get("category_id"),category1.get(4).get("category_id")};
				lessBuyBrand = this.getLessBuyBrand(aCategory1ID);//小额采购品牌
				//小额采购一级类目下对应品牌
				Object[] a1BrandID = new Object[]{lessBuyBrand.get(0).get(0).get("brand_id"),lessBuyBrand.get(0).get(1).get("brand_id"),lessBuyBrand.get(0).get(2).get("brand_id")};
				Object[] a2BrandID = new Object[]{lessBuyBrand.get(1).get(0).get("brand_id"),lessBuyBrand.get(1).get(1).get("brand_id"),lessBuyBrand.get(1).get(2).get("brand_id")};
				Object[] a3BrandID = new Object[]{lessBuyBrand.get(2).get(0).get("brand_id"),lessBuyBrand.get(2).get(1).get("brand_id"),lessBuyBrand.get(2).get(2).get("brand_id")};
				Object[] a4BrandID = new Object[]{lessBuyBrand.get(3).get(0).get("brand_id"),lessBuyBrand.get(3).get(1).get("brand_id"),lessBuyBrand.get(3).get(2).get("brand_id")};
				Object[] a5BrandID = new Object[]{lessBuyBrand.get(4).get(0).get("brand_id"),lessBuyBrand.get(4).get(1).get("brand_id"),lessBuyBrand.get(4).get(2).get("brand_id")};
				Object[] aBrandID = new Object[]{a1BrandID,a2BrandID,a3BrandID,a4BrandID,a5BrandID};
				homePurchase = this.getHomePurchase(aCategory1ID, aBrandID);//获取大/小额采购商品
				redisUtil.set("shPurchaseCategory1", category1);
				redisUtil.set("shHomePurchase", homePurchase);
				redisUtil.set("shLessBuyBrand", lessBuyBrand);
			} catch (Exception e) {
				e.printStackTrace();
				result = "failed";
				msg = "网络异常";
				logger.info("CHPC:首页数据查询执行异常！");
			}
		}
		map.put("homeBHotSell", homeBHotSell);
		map.put("homeHHotSell", homeHHotSell);
		map.put("homePurchase", homePurchase);
		map.put("lessBuyBrand", lessBuyBrand);
		map.put("category1", category1);
		map.put("result", result);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
	}
	
	/**首页热门品牌页码切换
	 * @param pageNum
	 * @return List<PageData>	hotBrand
	 * @throws Exception
	 */
	@RequestMapping(value="/changeHotBrand", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object changeHotBrand(int pageNum)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		try {
			List<PageData>	hotBrand = homePageServiceImpl.getHotBrand(pageNum);
			String hbMaxPN = homePageServiceImpl.getHBMaxPN();//获取热卖品牌的最大页码
			if (Tools.isEmpty(hbMaxPN)) {
				hbMaxPN = "0";
			}
			map.put("hotBrand", hotBrand);
			map.put("hbMaxPN", hbMaxPN);
			result = "success";
		} catch (Exception e) {
			result = "failed";
			msg = "网络异常！请稍后再试！";
			logger.info("CHPC:系统错误！热门品牌获取异常！");
		}
		map.put("result", result);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
	}
	
	/**首页新货推荐切换
	 * @param pageNum
	 * @return List<PageData>	hotBrand
	 * @throws Exception
	 */
	@RequestMapping(value="/changeHomeNewGoods", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object changeHomeNewGoods(int pageNum)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		try {
			List<PageData>	homeNewGoods = homePageServiceImpl.getHomeNewGoods(pageNum);
			String ngMaxPN = homePageServiceImpl.getNGMaxPN();//获取新货推荐的最大页码	
			if (Tools.isEmpty(ngMaxPN)) {
				ngMaxPN = "0";
			}
			map.put("homeNewGoods", homeNewGoods);
			map.put("ngMaxPN", ngMaxPN);
			result = "success";
		} catch (Exception e) {
			result = "failed";
			msg = "网络异常！请稍后再试！";
			logger.info("CHPC:系统错误！新货推荐获取异常！");
		}
		map.put("result", result);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
	}
	
	/**首页获取活动专题数据
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getHomeSpecial()throws Exception{
		List<PageData> homeSpecial = new ArrayList<PageData>();
		Object redisHomeSpecial =  redisUtil.get("shHomeSpecial");
		if (redisHomeSpecial != null) {
			homeSpecial = (List<PageData>) redisHomeSpecial;
		}else {
			try {
				homeSpecial = homePageServiceImpl.getHomeSpecial();//首页四个活动专题板块;
				redisUtil.set("shHomeSpecial", homeSpecial);
			} catch (Exception e) {
				logger.info("首页获取活动专题数据执行异常！");
			}
		}
		return homeSpecial;
	}
		
	/**获取首页淘宝/京东热卖
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getTJHotSell(String type)throws Exception{
		List<PageData>	tjHotSell = new ArrayList<PageData>();
		String key = "sh" + type + "HotSell";
		Object redisTJHotSell =  redisUtil.get(key);
		if (redisTJHotSell != null) {
			tjHotSell = (List<PageData>) redisTJHotSell;
		}else {
			try {
				tjHotSell = homePageServiceImpl.getTJHotSellList(type);//淘宝热卖
				redisUtil.set(key, tjHotSell);
			} catch (Exception e) {
				logger.info("首页获取京东/淘宝热卖数据执行异常！");
			}
		}
		return tjHotSell;
	}
	
	/**获取首页搜索框下面搜索词
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void getHomeHotWord()throws Exception{
		List<String> homeHotWordList = new ArrayList<String>();
		Session session = Jurisdiction.getSession();
		Object redisHomeHotWordList =  redisUtil.get("shHomeHotWordList");
		if (redisHomeHotWordList != null) {
			homeHotWordList = (List<String>) redisHomeHotWordList;
			session.setAttribute("homeHotWordList", homeHotWordList);
		}else {
			try {
				String homeHotWord = homePageServiceImpl.getHomeHotWord();
				if (Tools.notEmptys(homeHotWord)) {
					String[] homeHotWordArry = homeHotWord.split(",");
					homeHotWordList = new ArrayList<String>(Arrays.asList(homeHotWordArry));
					session.setAttribute("homeHotWordList", homeHotWordList);
					redisUtil.set("shHomeHotWordList", homeHotWordList);
				}
			} catch (Exception e) {
				logger.info("搜索框下搜索词获取执行异常！");
			}
		}
	}
	
	/**首页默认显示一级类目下小额采购的品牌
	 * @param int category1ID【】
	 * @return 
	 * @throws Exception
	 */
	public ArrayList<List<PageData>> getLessBuyBrand(Object[] aCategory1ID)throws Exception{
		ArrayList<List<PageData>> lessBuyBrand = new ArrayList<List<PageData>>();
		for (int i = 0; i < aCategory1ID.length; i++) {
			List<PageData>	iHotSellList = homePageServiceImpl.getLessBuyBrand(aCategory1ID[i]);
			lessBuyBrand.add(iHotSellList);
		}
		return lessBuyBrand;
	}
			
	/**首页小额采购模块一级类目/品牌对应下的商品
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public ArrayList<Object> getHomePurchase(Object[] aCategory1ID,Object[] aBrandID)throws Exception{
		ArrayList<Object> homePurchase = new ArrayList<Object>();
		try {
			for (int i = 0; i < aCategory1ID.length; i++) {
				ArrayList<Object> iHomePurchase = new ArrayList<Object>();
				ArrayList<List<PageData>> iHomeLessBuy = new ArrayList<List<PageData>>();
				PageData pd = new PageData();
				pd.put("category1ID", aCategory1ID[i]);
				List<PageData>	iLessBuyMore = homePageServiceImpl.getLessBuyMore(pd);
				iHomeLessBuy.add(iLessBuyMore);
				Object[] aiBrandID = (Object[]) aBrandID[i];
				for (int j = 0; j < aiBrandID.length; j++) {
					PageData pdb = new PageData();
					pdb.put("category1ID", aCategory1ID[i]);
					pdb.put("brandID", aiBrandID[j]);
					pdb.put("blocationSort", j+1);
					List<PageData>	jLessBuy = homePageServiceImpl.getLessBuy(pdb);
					iHomeLessBuy.add(jLessBuy);
				}
				List<PageData>	iHomeLargeBuy = homePageServiceImpl.getHomeLargeBuy(aCategory1ID[i]);
				iHomePurchase.add(iHomeLargeBuy);
				iHomePurchase.add(iHomeLessBuy);
				homePurchase.add(iHomePurchase);
			}
		} catch (Exception e) {
			logger.info("CHPC:获取首页小额采购商品系统执行异常!");
			throw new Exception("获取首页小额采购商品异常");
		}
		return homePurchase;
	}
		
	/**首页行业数据
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<List<PageData>> getHomeIndustryData()throws Exception{
		ArrayList<List<PageData>> IndustryData = new ArrayList<List<PageData>>();
		Object redisIndustryData =  redisUtil.get("shIndustryData");
		if (redisIndustryData != null) {
			IndustryData = (ArrayList<List<PageData>>) redisIndustryData;
		}else {
			try {
				for (int i = 1; i < 6; i++) {
					List<PageData>	iIndustryData = homePageServiceImpl.getHomeIndustryData(i);
					IndustryData.add(iIndustryData);
				}
				redisUtil.set("shIndustryData", IndustryData);
			} catch (Exception e) {
				throw new Exception("获取首页行业数据异常");
			}
		}
		return IndustryData;
	}
	
	
	
}
