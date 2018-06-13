package com.aurorascm.serviceImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.service.StatisticsService;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/**
 * 数据统计--
 * @author SSY 2017-11-14
 * @version 1.0
 */
@Service("statisticsServiceImpl")
public class StatisticsServiceImpl implements StatisticsService{
	
	@Resource(name = "daoSupport")
	private DAO daoSupport;
	
	 
	
	/**
	 * 推广源访问次数统计
	 * @param int num
	 */
	@Override
	public void updateSpreadVisitNum(Object spread) throws Exception{
		daoSupport.update("StatisticsWebsiteWriteMapper.updateSpreadVisitNum", spread);//统计该模块的点击次数
	}
	
	 
	/**
	 * 搜索关键字统计
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public void saveStatisticKeyWord(List<String> keyWords) throws Exception{
		PageData pd = new PageData();
		pd.put("monthNum", "month"+DateUtil.getMonth()+"_num");
		try {
			for (Iterator iterator = keyWords.iterator(); iterator.hasNext();) {
				String keyWord = (String) iterator.next();
				pd.put("keyWord", keyWord);
				daoSupport.save("StatisticsWebsiteWriteMapper.saveStatisticKeyWord", pd);//统计关键字搜索次数;
			}
		} catch (Exception e) {
			e.printStackTrace();
			//什么也不做
		}
	}
	
	
}
