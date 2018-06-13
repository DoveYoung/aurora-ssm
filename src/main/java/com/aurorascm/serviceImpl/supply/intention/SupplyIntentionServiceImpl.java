package com.aurorascm.serviceImpl.supply.intention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.aurorascm.dao.DAO;
import com.aurorascm.entity.Result;
import com.aurorascm.entity.supply.intention.SupplyGoodsIntention;
import com.aurorascm.entity.supply.intention.SupplyIntention;
import com.aurorascm.service.supply.intention.SupplyIntentionService;
import com.aurorascm.util.DateUtil;
/**
 * @Title: SupplyIntentionServiceImpl.java 
 * @Package com.aurorascm.serviceImpl.supply.intention 
 * @Description: 供货意向Impl
 * @author SSY  
 * @date 2018年5月14日 上午11:31:49 
 * @version V1.0
 */
@Service
public class SupplyIntentionServiceImpl implements SupplyIntentionService {
	@Autowired 
	private DAO daoSupport;


	/**
	 * @Title: addSupplyIntention 
	 * @Description: 新增全站供货意向
	 * @param    
	 * @return Result<Object>  
	 * @author SSY
	 * @date 2018年5月14日 上午11:46:59
	 */
	@Override
	public Result<Object> addSupplyIntention(String supplyIntentionJson) throws Exception {
		Result<Object> result = new Result<Object>();
		SupplyIntention supplyIntention = null;
		try {
			supplyIntention = JSON.parseObject(supplyIntentionJson, SupplyIntention.class);
		} catch (Exception e) {
			result.setMsg("参数错误!");
			result.setState(Result.STATE_ERROR);
			return result;
		}
		supplyIntention.setInputTime(DateUtil.getTime());
		int addNum = (int) daoSupport.save("SupplyIntentionWriteMapper.addSupplyIntention", supplyIntention);
		result.setMsg(addNum>=1 ? "提交成功!" : "提交失败!请联系客服");
		result.setState(addNum>=1 ? Result.STATE_SUCCESS : Result.STATE_ERROR);
		return result;
	}
	
	/**
	 * @Title: addSupplyGoodsIntention 
	 * @Description: 新增单个商品供货意向
	 * @param    
	 * @return Result<Object>  
	 * @author SSY
	 * @date 2018年5月14日 上午11:46:59
	 */
	@Override
	public Result<Object> addSupplyGoodsIntention(String supplyGoodsIntentionJson) throws Exception {
		Result<Object> result = new Result<Object>();
		SupplyGoodsIntention supplyGoodsIntention = null;
		try {
			supplyGoodsIntention = JSON.parseObject(supplyGoodsIntentionJson, SupplyGoodsIntention.class);
		} catch (Exception e) {
			result.setMsg("参数错误!");
			result.setState(Result.STATE_ERROR);
			return result;
		}
		supplyGoodsIntention.setInputTime(DateUtil.getTime());
		int addNum = (int) daoSupport.save("SupplyIntentionWriteMapper.addSupplyGoodsIntention", supplyGoodsIntention);
		result.setMsg(addNum>=1 ? "提交成功!" : "提交失败!请联系客服");
		result.setState(addNum>=1 ? Result.STATE_SUCCESS : Result.STATE_ERROR);
		return result;
	}
	
}
