package com.aurorascm.service.shop.home;

import java.util.List;

import com.aurorascm.entity.Result;
import com.aurorascm.entity.home.TimedActivity;

/**
 * @Title: LimitedTimeService.java 
 * @Package com.aurorascm.service.shop.home 
 * @Description: 限时活动service 
 * @author SSY  
 * @date 2018年5月5日 上午11:51:29 
 * @version V1.0
 */
public interface LimitedTimeService {

	/**
	 * @Title: getTimedActivityList 
	 * @Description: 查询限时活动
	 * @param    
	 * @return Result<List<TimedActivity>>  
	 * @author SSY
	 * @date 2018年5月5日 上午11:51:13
	 */
	Result<List<TimedActivity>> getTimedActivityList() throws Exception;
 
	
	
}
