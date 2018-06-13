package com.aurorascm.service.shop.home;

import java.util.List;

import com.aurorascm.entity.Result;
import com.aurorascm.entity.home.HomeSpecial;

public interface HomeSpecialService {

	/**
	 * @Title: getHomeSpecialList 
	 * @Description:  查询首页专题
	 * @param    Integer module
	 * @return Result<List<HomeSpecial>>  
	 * @author SSY
	 * @date 2018年5月5日 上午11:15:22
	 */
	Result<List<HomeSpecial>> getHomeSpecialList(Integer module) throws Exception;

 
	
	
}
