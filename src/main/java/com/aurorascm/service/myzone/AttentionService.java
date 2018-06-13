package com.aurorascm.service.myzone;

import java.util.List;
import java.util.Map;

import com.aurorascm.entity.Page;
import com.aurorascm.util.PageData;

public interface AttentionService {
	  /**
	   * 根据关注的品牌查询销量最高的上架商品;
	   * @param careBrand
	   */
	public Map<String,List<PageData>> getAttention(Page page) throws Exception;
	
	/**
	 * 根据品牌id查询品牌信息
	 */
	public List<PageData> getBrandPbByID(String brandID) throws Exception;
	
	/**
	 * 根据品牌id查询销量最高的4个上架的商品;
	 * @param brandID
	 */
	public List<PageData> getGoodsBybrandID(String brandID) throws Exception;

	/**
	 * 根据用户id查询关注的拼接品牌id;
	 * @param customerID
	 */
	public String getAttentionBrand(String customerID) throws Exception;
	
	/**
	 * 添加品牌关注；
	 */
	public int addBrandCare(PageData pd) throws Exception;
	
	/**
	 * 取消品牌关注
	 * @param brandID ,customerID 
	 */
	public int updateBrandCare(PageData pd) throws Exception;
	
	/**
	 * 判断是否已经关注;YES true, NO false 
	 * @param brandID
	 */
	public String getJudgeBrandCared(PageData pd) throws Exception;

	/**
	 * 判断是否已经关注; 支持批量查询
	 * @param String brandIDs
	 * @return Boolean[] flag
	 * @author SSY 2017-12-29 
	 */
	public Boolean[] getJudgeBrandCared(String brandIDs) throws Exception;
	
}
