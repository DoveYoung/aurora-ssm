package com.aurorascm.service;

import java.util.List;
import java.util.Map;

import com.aurorascm.util.PageData;

public interface SpecialService {

	/**
	 * 根据specialID查找该专题下的商品
	 * @param Integer specialID, String templateID
	 * @return
	 * @throws Exception
	 */
	public List<List<PageData>> getSpecialGoods(Integer templateID, Integer specialID) throws Exception;
	
	/**
	 * @Title: getSpecialMap 
	 * @Description:  查询专题背景图
	 * @param    Integer specialID
	 * @return String  
	 * @author SSY
	 * @date 2018年5月16日 上午9:21:43
	 */
	public String getSpecialBackground(Integer specialID) throws Exception;

}
