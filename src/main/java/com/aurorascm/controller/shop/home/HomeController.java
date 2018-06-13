package com.aurorascm.controller.shop.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.aurorascm.controller.BaseController;
import com.aurorascm.entity.Result;
import com.aurorascm.entity.home.HomeBonded;
import com.aurorascm.entity.home.HomeFloor;
import com.aurorascm.entity.home.HomeSpecial;
import com.aurorascm.entity.home.TimedActivity;
import com.aurorascm.redis.RedisUtil;
import com.aurorascm.service.shop.home.HomeService;
import com.aurorascm.util.Const;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.PageData;
import com.aurorascm.util.RedisConst;

/**
 * @Title: HomePageController.java 
 * @Package com.aurorascm.controller.shop.home 
 * @Description: 首页
 * @author SSY  
 * @date 2018年5月7日 下午4:02:34 
 * @version V1.0
 */
@Controller
@RequestMapping(value="/home")
public class HomeController extends BaseController {
	
	@Autowired HomeService HomeServiceImpl;
	@Autowired RedisUtil redisUtil;
	/**
	 * @Title: goHome 
	 * @Description: 去商城首页
	 * @param    
	 * @return homeHotSell(热卖商品);homeLargeCargo(大货集散);homeFloorCategoryIDs(品类商品所有类目id),homeBanner,homeTimedActivity
	 * @author SSY
	 * @date 2018年5月7日 下午4:03:03
	 */
	@RequestMapping 
	public String goHome(ModelMap modelMap)throws Exception{ 
		modelMap.put("homeFloorCategoryIDs", JSON.toJSON(Const.CATEGORYID_FLOORS.split(",")));
		modelMap.put(RedisConst.HOME_BANNER, this.getHomeBannerList());
		modelMap.put(RedisConst.HOME_TIMED_ACTIVITY, this.getActivityList());
		modelMap.put(RedisConst.HOME_HOT_SELL, JSON.toJSON(this.getHotSell()));
		modelMap.put(RedisConst.HOME_LARGE_CARGO, JSON.toJSON(this.getLargeCargo()));
		return "system/index/home"; 
	}
	
	/**
	 * @Title: getHotSell 
	 * @Description: 获取热卖商品
	 * @param    
	 * @return homeHotSell(热卖商品);  
	 * @author BYG
	 * @date 2018年5月7日 下午4:03:03
	 */
	public Object getHotSell()throws Exception{
		Object hotSell = redisUtil.get(RedisConst.HOME_HOT_SELL);
		if (hotSell == null) {
			hotSell = HomeServiceImpl.getHotSell();
			redisUtil.set(RedisConst.HOME_HOT_SELL, hotSell);
		}
		return hotSell;
	}
 
	/**
	 * @Title: getLargeCargo
	 * @Description: 获取大货集散商品
	 * @param    
	 * @return largeCargo(大货集散商品);  
	 * @author BYG
	 * @date 2018年5月7日 下午4:03:03
	 */
	public Object getLargeCargo()throws Exception{
		Object largeCargo = redisUtil.get(RedisConst.HOME_LARGE_CARGO);
		if (largeCargo == null) {
			largeCargo = HomeServiceImpl.getLargeCargo();
			redisUtil.set(RedisConst.HOME_LARGE_CARGO, largeCargo);
		}
		return largeCargo;
	}
	
	/**
	 * @Title: getDirectPostAndCountriesGoods
	 * @Description: 获取海外直邮和各国好货
	 * @param    
	 * @return Result<Object>  
	 * @author BYG
	 * @date 2018年5月7日 下午4:03:03
	 */
	@RequestMapping(value="/getDirectPostAndCountriesGoods",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> getDirectPostAndCountriesGoods()throws Exception{
		Result<Object> result = new Result<Object>();
		PageData directPostAndCountriesGoods = new PageData();
		Object directPost = redisUtil.get(RedisConst.HOME_DIRECT_POST);
		Object countriesGoods = redisUtil.get(RedisConst.HOME_COUNTRIES_GOODS);
		if (directPost == null) {
			directPost = HomeServiceImpl.getDirectPost();
			redisUtil.set(RedisConst.HOME_DIRECT_POST, directPost);
		}
		if (countriesGoods == null) {
			countriesGoods = HomeServiceImpl.getCountriesGoods();
			redisUtil.set(RedisConst.HOME_COUNTRIES_GOODS, countriesGoods);
		}
		directPostAndCountriesGoods.put("directPost", directPost);
		directPostAndCountriesGoods.put("countriesGoods", countriesGoods);
		result.setResult(directPostAndCountriesGoods);
		result.setState(Result.STATE_SUCCESS);
		return result;
	}
	
	/**
	 * @Title: getTopBrand
	 * @Description: 获取热门品牌
	 * @param    int pageNumber
	 * @return   pageNumber,maxPage,topBrand
	 * @author BYG
	 * @date 2018年5月7日 下午4:03:03
	 */
	@RequestMapping(value="/getTopBrand",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> getTopBrand(int pageNumber)throws Exception{
		Result<Object> result = new Result<Object>();
		Object topBrand;
		if (pageNumber ==1) {
			topBrand = redisUtil.get(RedisConst.HOME_TOP_BRAND);//redis中只缓存第一页
			if (topBrand == null) {
				topBrand = HomeServiceImpl.getTopBrand(pageNumber);
				redisUtil.set(RedisConst.HOME_TOP_BRAND, topBrand);
			}
		}else {
			topBrand = HomeServiceImpl.getTopBrand(pageNumber);
		}
		int maxPage = HomeServiceImpl.getTopBrandMaxPage();
		PageData pd = new PageData();
		pd.put("pageNumber", pageNumber);
		pd.put("maxPage", maxPage);
		pd.put("topBrand", topBrand);
		result.setResult(pd);
		result.setState(Result.STATE_SUCCESS);
		return result;
	}
	
	
	/**
	 * @Title: getHomeBannerList
	 * @Description: 查询首页banner
	 * @param    
	 * @return Object  
	 * @author SSY
	 * @date 2018年5月8日 下午3:33:49
	 */
	public  Object  getHomeBannerList() throws Exception {
		Object homeBannerList = null ;
		try {
			Object object = redisUtil.get(RedisConst.HOME_BANNER);
			if (null==object) {
				Result<List<HomeSpecial>> result = homeSpecialServiceImpl.getHomeSpecialList(Const.SPECIAL_BANNER_MODULE);//banner专题
				homeBannerList = JSON.toJSON(result.getResult());
				redisUtil.set(RedisConst.HOME_BANNER, homeBannerList);
			}else{
				homeBannerList = object;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("【ERROR】"+DateUtil.getTime()+"查询首页banner执行异常! ");
		}
		return homeBannerList;
	}
	
	
	/**
	 * @Title: getActivityList 
	 * @Description: 查询活动列表
	 * @param    
	 * @return    Result<List<TimedActivity>> 
	 * @author SSY
	 * @date 2018年5月9日 下午6:58:38
	 */
	public Object getActivityList() throws Exception {
		Object activityList = null;
		try {
			Result<List<TimedActivity>> result  = limitedTimeServiceImpl.getTimedActivityList();
			activityList = JSON.toJSON(result.getResult());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("【ERROR】"+DateUtil.getTime()+"限时折扣活动列表查询执行异常! ");
		}
		return activityList;
	}
	
	/**
	 * @Title: getHomeBonded
	 * @Description: 查询首页Bonded
	 * @param    
	 * @return Result<HomeBonded>
	 * @author SSY
	 * @date 2018年5月7日 下午3:33:49
	 */
	@RequestMapping(value = "/getHomeBonded", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result<HomeBonded> getHomeBonded() throws Exception {
		Result<HomeBonded> result = new Result<HomeBonded>();
		try {
			result = homeBondedServiceImpl.getHomeBonded();//保税仓热卖商品以及专题列表;
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("系统异常! ");
			result.setState(Result.STATE_ERROR);
			logger.info("【ERROR】"+DateUtil.getTime()+"查询首页保税仓执行异常! ");
		}
		return result;
	}
	
	/**
	 * @Title: getHomeFloorList
	 * @Description: 查询首页品类HomeFloorList
	 * @param    
	 * @return Object  
	 * @author SSY
	 * @date 2018年5月5日 下午3:33:49
	 */
	@RequestMapping(value = "/getHomeFloorList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result<List<HomeFloor>> getHomeFloorList() throws Exception {
		Result<List<HomeFloor>> result = new Result<List<HomeFloor>>();
		try {
			result = homeFloorServiceImpl.getHomeFloorList(); 
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("系统异常! ");
			result.setState(Result.STATE_ERROR);
			logger.info("【ERROR】"+DateUtil.getTime()+"查询首页品类商品执行异常! ");
		}
		return result;
	}
	
}
