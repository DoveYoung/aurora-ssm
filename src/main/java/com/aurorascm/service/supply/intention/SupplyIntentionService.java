package com.aurorascm.service.supply.intention;

import com.alibaba.fastjson.JSON;
import com.aurorascm.entity.Result;
import com.aurorascm.entity.supply.intention.SupplyIntention;
import com.aurorascm.util.DateUtil;

/**
 * @Title: SupplyIntentionService.java 
 * @Package com.aurorascm.service.supply.intention 
 * @Description:  供货意向 
 * @author SSY  
 * @date 2018年5月14日 上午11:30:34 
 * @version V1.0
 */
public interface SupplyIntentionService {
	
	/**
	 * @Title: addSupplyIntention 
	 * @Description: 新增全站供货意向
	 * @param    
	 * @return Result<Object>  
	 * @author SSY
	 * @date 2018年5月14日 上午11:46:59
	 */
	Result<Object> addSupplyIntention(String supplyIntentionJson) throws Exception;

	/**
	 * @Title: addSupplyGoodsIntention 
	 * @Description: 新增单个商品供货意向
	 * @param    
	 * @return Result<Object>  
	 * @author SSY
	 * @date 2018年5月14日 上午11:46:59
	 */
	public Result<Object> addSupplyGoodsIntention(String supplyGoodsIntentionJson) throws Exception;
	
	
	
	
}
