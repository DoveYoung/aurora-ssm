package com.aurorascm.service;

import java.util.List;

public interface StatisticsService {
	 
	
	/**
	 * 搜索关键字统计
	 */
	public void saveStatisticKeyWord(List<String> keyWords) throws Exception;

	
	/**
	 * 推广源访问次数统计
	 * @param int num
	 */
	public void updateSpreadVisitNum(Object spread) throws Exception;
}
