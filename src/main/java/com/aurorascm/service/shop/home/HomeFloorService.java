package com.aurorascm.service.shop.home;

import java.util.List;

import com.aurorascm.entity.Result;
import com.aurorascm.entity.home.HomeFloor;

/**
 * @Title: HomeFloorService.java 
 * @Package com.aurorascm.service.shop.home 
 * @Description: 品类商品
 * @author SSY  
 * @date 2018年5月7日 下午4:53:12 
 * @version V1.0
 */
public interface HomeFloorService {

	/**
	 * @Title: getHomeFloorList 
	 * @Description:  查询首页品类
	 * @param    
	 * @return Result<List<HomeFloor>>  
	 * @author SSY
	 * @date 2018年5月7日 下午4:57:27
	 */
	Result<List<HomeFloor>> getHomeFloorList() throws Exception;

	
	
}
