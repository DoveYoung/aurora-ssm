package com.aurorascm.serviceImpl.shop.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.Result;
import com.aurorascm.entity.home.HomeSpecial;
import com.aurorascm.redis.RedisUtil;
import com.aurorascm.service.shop.home.HomeSpecialService;
import com.aurorascm.util.RedisConst;
@Service
public class HomeSpecialServiceImpl implements HomeSpecialService{
	
	@Autowired 
	private DAO daoSupport;
	@Autowired 
	private RedisUtil redisUtil;
	
	/**
	 * @Title: getHomeSpecialList 
	 * @Description:  查询首页专题
	 * @param    Integer module
	 * @return Result<List<HomeSpecial>>  
	 * @author SSY
	 * @date 2018年5月5日 上午11:15:22
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Result<List<HomeSpecial>> getHomeSpecialList(Integer module) throws Exception {
		Result<List<HomeSpecial>> result = new Result<List<HomeSpecial>>();
		if (null==module) {
			result.setMsg("参数错误! ");
			result.setState(Result.STATE_ERROR);
			return result;
		}
		List<HomeSpecial> homeSpecialList = (List<HomeSpecial>)daoSupport.findForList("HomeSpecialReadMapper.getHomeSpecialList",module);
		result.setResult(homeSpecialList);
		return result;
	}

}
