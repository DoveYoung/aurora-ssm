package com.aurorascm.serviceImpl.shop.home;

import java.util.ArrayList;
import java.util.List;

import org.omg.PortableInterceptor.HOLDING;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.Result;
import com.aurorascm.entity.home.Category;
import com.aurorascm.entity.home.HomeFloor;
import com.aurorascm.entity.home.HomeFloorBrand;
import com.aurorascm.entity.home.HomeFloorGoods;
import com.aurorascm.entity.home.HomeSpecial;
import com.aurorascm.entity.home.TimedActivity;
import com.aurorascm.redis.RedisUtil;
import com.aurorascm.service.shop.home.HomeFloorService;
import com.aurorascm.util.Const;
import com.aurorascm.util.RedisConst;

/**
 * @Title: HomeFloorServiceImpl.java 
 * @Package com.aurorascm.serviceImpl.shop.home 
 * @Description: 首页品类商品
 * @author SSY  
 * @date 2018年5月7日 下午4:54:07 
 * @version V1.0
 */
@Service
public class HomeFloorServiceImpl implements HomeFloorService, HOLDING {

	@Autowired 
	private DAO daoSupport;
	@Autowired 
	private RedisUtil redisUtil;
	
	/**
	 * @Title: getHomeFloorList 
	 * @Description:  查询首页品类
	 * @param    
	 * @return Result<List<HomeFloor>>  
	 * @author SSY
	 * @date 2018年5月7日 下午4:57:27
	 */
	@SuppressWarnings("unchecked")
	public Result<List<HomeFloor>> getHomeFloorList() throws Exception{
		Result<List<HomeFloor>> result = new Result<List<HomeFloor>>();
		List<HomeFloor> homeFloorList = new ArrayList<HomeFloor>();
		Object object = redisUtil.get(RedisConst.HOME_FLOOR);
		if (null==object) {
			List<Category> homeFloorCategory = (List<Category>)daoSupport.findForList("HomeFloorReadMapper.getHomeFloorCategory");
			for (Category category : homeFloorCategory) {
				Integer category1ID = category.getCategoryID();
				HomeFloor homeFloor = new HomeFloor();
				homeFloor.setCategoryName(category.getCategoryName());
				homeFloor.setCategory1ID(category1ID);
				List<HomeFloorGoods> floorGoodsList = (List<HomeFloorGoods>) daoSupport.findForList("HomeFloorReadMapper.getHomeFloorGoods", category1ID);
				homeFloor.setFloorGoodsList(floorGoodsList);
				List<HomeFloorBrand> floorBrandList = (List<HomeFloorBrand>) daoSupport.findForList("HomeFloorReadMapper.getHomeFloorBrand", category1ID);
				homeFloor.setFloorBrandList(floorBrandList);
				int keywordType = category1ID;
				String floorKeyword = (String) daoSupport.findForObject("HomeKeywordReadMapper.getHomeKeyword", keywordType);
				homeFloor.setKeyword(floorKeyword);
				//专题
				int module = category1ID;
				List<HomeSpecial> specialList = (List<HomeSpecial>)daoSupport.findForList("HomeSpecialReadMapper.getHomeSpecialList",module);
				homeFloor.setSpecialList(specialList);
				homeFloorList.add(homeFloor);
			}
			redisUtil.set(RedisConst.HOME_FLOOR, homeFloorList);
		}else{
			homeFloorList = (List<HomeFloor>)object;
		}
		result.setResult(homeFloorList);
		result.setState(Result.STATE_SUCCESS);
		return result;
	}
	
	
	
	
	
}
