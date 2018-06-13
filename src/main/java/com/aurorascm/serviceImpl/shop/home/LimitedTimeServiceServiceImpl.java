package com.aurorascm.serviceImpl.shop.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.Result;
import com.aurorascm.entity.home.TimedActivity;
import com.aurorascm.redis.RedisUtil;
import com.aurorascm.service.shop.home.LimitedTimeService;
import com.aurorascm.util.RedisConst;
@Service
public class LimitedTimeServiceServiceImpl implements LimitedTimeService{
	
	@Autowired 
	private DAO daoSupport;
	@Autowired 
	private RedisUtil redisUtil;
	
	
	
	/**
	 * @Title: getActivityList 
	 * @Description: 查询限时活动
	 * @param    
	 * @return Result<List<TimedActivity>>  
	 * @author SSY
	 * @date 2018年5月5日 上午11:51:13
	 */
	@SuppressWarnings("unchecked")
	public Result<List<TimedActivity>> getTimedActivityList() throws Exception{
		Result<List<TimedActivity>> result = new Result<List<TimedActivity>>();
		List<TimedActivity> timedActivityList = null;
		Object object = redisUtil.get(RedisConst.HOME_TIMED_ACTIVITY);
		if (null==object) {
			timedActivityList = (List<TimedActivity>)daoSupport.findForList("HomeTimedActivityReadMapper.getTimedActivityList");
			if (timedActivityList==null || timedActivityList.size()== 0) {
				timedActivityList = (List<TimedActivity>)daoSupport.findForList("HomeTimedActivityReadMapper.getLastTimedActivityList");
			}
			redisUtil.set(RedisConst.HOME_TIMED_ACTIVITY, timedActivityList);
		}else{
			timedActivityList = (List<TimedActivity>)object;
		}
		result.setResult(timedActivityList);
		return result;
	}
}
