package com.aurorascm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurorascm.entity.Result;
import com.aurorascm.service.AreaAddrService;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.KdniaoTrackQuery;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/**
 * 用户邮寄地址/省市区
 * 
 * @author BYG 2017-7-21
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/areaAddr")
public class AreaAddrController extends BaseController {

	
	@Resource(name = "areaAddrServiceImpl")
	private AreaAddrService areaAddrServiceImpl;
	/**
	 * 加入订单时选择收货地址--- 根据CustomerID和关键词搜索客户收获地址
	 * 
	 * @param customerID
	 *            keyword
	 * @return likeShipAddr
	 * @throws Exception
	 */
	@RequestMapping(value = "/likeShipAddr", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> likeShipAddr() throws Exception {
		Result<Object> result = new Result<Object>();
		PageData pd = this.getPageData();
		String customerID = Jurisdiction.getCustomerID();
		String keyword = Tools.notEmpty(pd.getString("keyword"))? pd.getString("keyword").replace(" ", "") : null;
		pd.put("customerID", customerID);
		pd.put("keyword", keyword);
		if (customerID != null && keyword != null) {
			try {
				List<PageData> likeShipAddr = areaAddrServiceImpl.likeShipAddr(pd);
				result.setResult(likeShipAddr);
				result.setState(Result.STATE_SUCCESS);
			} catch (Exception e) {
				result.setMsg("网络异常!请稍后重试");
				result.setState(Result.STATE_ERROR);
			}
		} else {
			result.setMsg("参数异常！");
			result.setState(Result.STATE_ERROR);
		}
		return result;
	}

	/**
	 * 新增收获地址请求
	 * @param
	 * @return result province
	 * @throws Exception
	 */
	@RequestMapping(value = "/goAddShipAddr", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object goAddShipAddr() throws Exception {
		Result<Object> result = new Result<Object>();
		try {
			List<PageData> province = areaAddrServiceImpl.getProvince();
			result.setResult(province);
			result.setState(Result.STATE_SUCCESS);
		} catch (Exception e) {
			result.setMsg("网络异常!省级区域加载失败!");
			result.setState(Result.STATE_ERROR);
			logger.info("【CAAC:系统异常,省级区域加载失败!】");
		}
		return result;
	}

	/**
	 * 新增收获地址保存
	 * 
	 * @param customerID
	 *            name mobile province city area detailAddr IDCard
	 * @return result
	 * @throws Exception
	 */
	@RequestMapping(value = "/addShipAddr", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object addShipAddr() throws Exception {
		Result<Object> result = new Result<Object>();
		PageData pd = this.getPageData();
		String customerID =Jurisdiction.getCustomerID();
		String name = Tools.notEmptys(pd.getString("name")) ? pd.getString("name").replace(" ", "") : null;
		String mobile = Tools.notEmptys(pd.getString("mobile")) ? pd.getString("mobile").replace(" ", "") : null;
		String telephone = Tools.notEmptys(pd.getString("telephone")) ? pd.getString("telephone").replace(" ", "") : null;
		String province = Tools.notEmptys(pd.getString("province")) ? pd.getString("province").replace(" ", "") : null;
		String provincePin = Tools.notEmptys(pd.getString("provincePin")) ? pd.getString("provincePin").replace(" ", "") : null;
		String city = Tools.notEmptys(pd.getString("city")) ? pd.getString("city").replace(" ", "") : null;
		String area = Tools.notEmptys(pd.getString("area")) ? pd.getString("area").replace(" ", "") : null;
		String detailAddr = Tools.notEmptys(pd.getString("detailAddr")) ? pd.getString("detailAddr").replace(" ", "") : null;
		String IDCard = Tools.notEmptys(pd.getString("IDCard")) ? pd.getString("IDCard").replace(" ", "") : null;
		if (name != null && provincePin != null && mobile != null && province != null
				&& IDCard != null && city != null && area != null && detailAddr != null) {
			try {
				pd.put("customerID", customerID);
				pd.put("name", name);
				pd.put("mobile", mobile);
				pd.put("telephone", telephone);
				pd.put("province", province);
				pd.put("provincePin", provincePin);
				pd.put("city", city);
				pd.put("area", area);
				pd.put("detailAddr", detailAddr);
				pd.put("IDCard", IDCard);
				pd.put("addressType", 4);
				pd.put("operateTime", DateUtil.getTime());
				areaAddrServiceImpl.addShipAddr(pd);
				PageData newShipAddr = areaAddrServiceImpl.getNewShipAddr(customerID);
				result.setResult(newShipAddr);
				result.setState(Result.STATE_SUCCESS);
			} catch (Exception e) {
				result.setMsg("网络异常!请稍后重试");
				result.setState(Result.STATE_ERROR);
				logger.info("【CAAC:系统异常,新增收获地址执行异常！】");
			}
		} else {
			result.setMsg("网络异常!请稍后重试");
			result.setState(Result.STATE_ERROR);
			logger.info("【CAAC:新增收获地址参数不正确！】");
		}
		return result;
	}

	/**
	 * 修改收获地址保存
	 * @author BYG 2018.5.23
	 * @param 
	 * @return result
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateShipAddr", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> updateShipAddr() throws Exception {
		Result<Object> result = new Result<Object>();
		PageData pd = this.getPageData();
		String customerID = Jurisdiction.getCustomerID();
		String saID = Tools.notEmptys(pd.getString("saID")) ? pd.getString("saID").replace(" ", "") : null;
		String addressType = Tools.notEmptys(pd.getString("addressType")) ? pd.getString("addressType").replace(" ", "") : null;
		String name = Tools.notEmptys(pd.getString("name")) ? pd.getString("name").replace(" ", "") : null;
		String mobile = Tools.notEmptys(pd.getString("mobile")) ? pd.getString("mobile").replace(" ", "") : null;
		String telephone = Tools.notEmptys(pd.getString("telephone")) ? pd.getString("telephone").replace(" ", "") : null;
		String province = Tools.notEmptys(pd.getString("province")) ? pd.getString("province").replace(" ", "") : null;
		String provincePin = Tools.notEmptys(pd.getString("provincePin")) ? pd.getString("provincePin").replace(" ", "") : null;
		String city = Tools.notEmptys(pd.getString("city")) ? pd.getString("city").replace(" ", "") : null;
		String area = Tools.notEmptys(pd.getString("area")) ? pd.getString("area").replace(" ", "") : null;
		String detailAddr = Tools.notEmptys(pd.getString("detailAddr")) ? pd.getString("detailAddr").replace(" ", "") : null;
		String IDCard = Tools.notEmptys(pd.getString("IDCard")) ? pd.getString("IDCard").replace(" ", "") : null;
		if (addressType != null && addressType.equals(4)) {//addressType=4时为普通地址可以修改，微仓地址等不可修改
			if (name != null && provincePin != null && mobile != null && province != null
					&& IDCard != null && city != null && area != null && detailAddr != null) {
				try {
					pd.put("customerID", customerID);
					pd.put("saID", saID);
					pd.put("name", name);
					pd.put("mobile", mobile);
					pd.put("telephone", telephone);
					pd.put("province", province);
					pd.put("provincePin", provincePin);
					pd.put("city", city);
					pd.put("area", area);
					pd.put("detailAddr", detailAddr);
					pd.put("IDCard", IDCard);
					pd.put("operateTime", DateUtil.getTime());
					areaAddrServiceImpl.updateShipAddr(pd);
					result.setState(Result.STATE_SUCCESS);
				} catch (Exception e) {
					e.printStackTrace();
					result.setState(Result.STATE_ERROR);
					result.setMsg("网络异常,请稍后再试！");
					logger.info("【CAAC:系统异常,新增收获地址执行异常！】");
				}
			} else {
				result.setState(Result.STATE_ERROR);
				result.setMsg("参数异常,请稍后再试！");
				logger.info("【CAAC:新增收获地址参数不正确！】");
			}
		} else {
			result.setState(Result.STATE_ERROR);
			result.setMsg("当前地址不可修改");
		}
		return result;
	}
	
	/**
	 * 根据父级区域ID搜索相应地区
	 * 
	 * @param areaID
	 * @return result
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUArea", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getUArea() throws Exception {
		Result<Object> result = new Result<Object>();
		PageData pd = this.getPageData();
		String areaID = Tools.notEmptys(pd.getString("areaID")) ? pd.getString("areaID").replace(" ", "") : null;
		if (areaID != null) {
			try {
				List<PageData> area = areaAddrServiceImpl.getUArea(areaID);
				result.setResult(area);
				result.setState(Result.STATE_SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				result.setState(Result.STATE_ERROR);
				result.setMsg("网络异常,请稍后再试！");
				logger.info("【CAAC:系统异常,区域级联查询失败!！】");
			}
		} else {
			result.setState(Result.STATE_ERROR);
			result.setMsg("网络异常,请稍后再试！");
			logger.info("【CAAC:参数异常,区域级联查询失败!！】");
		}
		return result;
	}

	/**
	 * 根据邮寄地址ID获取对应详细信息
	 * 
	 * @param saID
	 * @return result
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSAByID", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getSAByID() throws Exception {
		Result<Object> result = new Result<Object>();
		PageData pd = this.getPageData();
		String saID = Tools.notEmptys(pd.getString("saID")) ? pd.getString("saID").replace(" ", "") : null;
		if (saID != null) {
			try {
				PageData uShipAddr = areaAddrServiceImpl.getShipAddr(saID);
				result.setResult(uShipAddr);
				result.setState(Result.STATE_SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				result.setState(Result.STATE_ERROR);
				result.setMsg("网络异常,请稍后再试！");
				logger.info("【CAAC:根据地址ID获取详细信息出现异常！】");
			}
		} else {
			result.setState(Result.STATE_ERROR);
			result.setMsg("参数异常,请稍后再试！");
			logger.info("【CAAC:地址ID参数获取失败!】");
		}
		return result;
	}

	/**
	 * 物流查询接口
	 * 
	 * @param
	 * @return result province
	 * @throws Exception
	 */
	@RequestMapping(value = "/getLogisticInfo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> getLogisticInfo(String expCode, String expNo) throws Exception {
		Result<Object> result = new Result<Object>();
		String results = KdniaoTrackQuery.getOrderTracesByJson(expCode, expNo);
		result.setResult(results);
		return result;
	}
}
