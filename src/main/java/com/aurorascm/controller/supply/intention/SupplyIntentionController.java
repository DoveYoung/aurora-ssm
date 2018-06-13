package com.aurorascm.controller.supply.intention;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aurorascm.controller.BaseController;
import com.aurorascm.entity.Result;
import com.aurorascm.util.DateUtil;

/**
 * @Title: SupplyIntentionController.java 
 * @Package com.aurora.controller.shop.home 
 * @Description: 供货商信息登记管理
 * @author SSY  
 * @date 2018年5月3日 下午7:56:58 
 * @version V1.0
 */
@RestController
@RequestMapping(value = "/supplyIntention")
public class SupplyIntentionController extends BaseController {

	 

	/**
	 * @Title:  
	 * @Description: 保存全站供货意向
	 * @param    supplyIntentionJson :{String convenientTime, String companyName, Integer chainPath, String contacts, String phone, String advantageBrand}
	 * 
	 * @return Object  
	 * @author SSY
	 * @date 2018年5月4日 下午3:33:49
	 */
	@RequestMapping(value = "/addSupplyIntention", produces = "application/json;charset=UTF-8")
	public Object addSupplyIntention(String supplyIntentionJson) throws Exception {
		Result<Object> result = new Result<Object>();
		try {
			result = supplyIntentionServiceImpl.addSupplyIntention(supplyIntentionJson);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("系统异常! ");
			result.setState(Result.STATE_ERROR);
			logger.info("【ERROR】"+DateUtil.getTime()+"新增全站供货意向执行异常! ");
		}
		return result;
	}

	/**
	 * @Title:  
	 * @Description: 保存单个商品供货意向
	 * @param    supplyGoodsIntentionJson :{String goodsID,goodsName,contacts,phone,deliveryType,price,convenientTime,inputTime}
	 * 
	 * @return Object  
	 * @author SSY
	 * @date 2018年5月4日 下午3:33:49
	 */
	@RequestMapping(value = "/addSupplyGoodsIntention", produces = "application/json;charset=UTF-8")
	public Object addSupplyGoodsIntention(String supplyGoodsIntentionJson) throws Exception {
		Result<Object> result = new Result<Object>();
		try {
			result = supplyIntentionServiceImpl.addSupplyGoodsIntention(supplyGoodsIntentionJson);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("系统异常! ");
			result.setState(Result.STATE_ERROR);
			logger.info("【ERROR】"+DateUtil.getTime()+"新增单个商品供货意向执行异常! ");
		}
		return result;
	}
	
	
}
