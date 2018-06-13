package com.aurorascm.controller.myzone;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.aurorascm.controller.BaseController;
import com.aurorascm.entity.Result;
import com.aurorascm.service.AreaAddrService;
import com.aurorascm.service.myzone.MicroWareHouseService;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/** 客户个人中心/微仓发货
 * @author BYG 2018-5-23
 * @version 2.0
 */
@Controller
@RequestMapping(value="/mwDeliverGoods")
public class MWDeliverGoodsController extends BaseController {
	
	@Resource(name="microWarehouseServiceImpl")
	private MicroWareHouseService microWarehouseServiceImpl;
	@Resource(name="areaAddrServiceImpl")
	private AreaAddrService areaAddrServiceImpl;
	

	/**
	 * 客户个人中心/跳转到微仓发货
	 * @author BYG 2018.5.23
	 * @param String mwgID 
	 * @return List<PageData> shipAddress5 ; PageData mwGoods
	 */
	@RequestMapping(value="/goDeliverGoods")
	public ModelAndView goMicroWarehouse()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String mwgID = Tools.notEmptys(pd.getString("mwgID")) ? pd.getString("mwgID").replace(" ", "") : null;
		String msg = "";
		String customerID = Jurisdiction.getCustomerID();
		if(customerID != null){
			try{
				List<PageData> shipAddress5 = areaAddrServiceImpl.get5ShipAddress(customerID);
				PageData mwGoods = null;
				if (mwgID != null) {
					mwGoods = microWarehouseServiceImpl.getGoodsByMwgID(mwgID);
				}
				mv.addObject("shipAddress5", JSON.toJSON(shipAddress5));
				mv.addObject("mwGoods", JSON.toJSON(mwGoods));
			} catch (Exception e) {
				e.printStackTrace();
				msg = "网络异常!请稍后重试!";
				logger.info("客户个人中心/我的微仓/微仓发货 初始化获取数据异常");
				throw new Exception(msg);
			}
		}else{
			msg = "登陆超时,请重新登陆!";
			throw new Exception(msg);
		}
		mv.addObject("msg", msg);
		mv.setViewName("system/myzone/wcfh");
		mv.addObject("menuIndex", 9);
		return mv;
	}
	
	
	/**
	 * 微仓发货页面---请求获取用户所有的微仓商品
	 * @author BYG 2018.5.23
	 * @param
	 * @return customerMWG
	 * @throws Exception
	 */
	@RequestMapping(value="/getCustomerMWG",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> getCustomerMWG() throws Exception {
		Result<Object> result = new Result<Object>();
		String customerID = Jurisdiction.getCustomerID();
		if(customerID != null){
			try {
				List<PageData> customerMWG = microWarehouseServiceImpl.getCustomerMWG(customerID);
				result.setResult(customerMWG);
				result.setState(Result.STATE_SUCCESS);
			} catch (Exception e) {
				result.setState(Result.STATE_ERROR);
				result.setMsg("网络异常,请稍后重试!");
				logger.info("请求获取用户所有的微仓商品系统执行异常!");
			}
		}else{
			result.setState(Result.STATE_ERROR);
			result.setMsg("登陆超时,请重新登陆!");
		}
		return result;
	}
	
	
	/**
	 * 客户个人中心/我的微仓/微仓发货 添加发货商品
	 * @author BYG 2018.5.23
	 * @param String mwgID
	 * @return mwGoods
	 */
	@RequestMapping(value="/getGoodsByMwgID",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> getGoodsByMwgID(String mwgID) throws Exception {
		Result<Object> result = new Result<Object>();
		String customerID = Jurisdiction.getCustomerID();
		if(customerID != null){
			try{
				PageData mwGoods = microWarehouseServiceImpl.getGoodsByMwgID(mwgID);
				result.setResult(mwGoods);
				result.setState(Result.STATE_SUCCESS);
			} catch (Exception e) {
				result.setState(Result.STATE_ERROR);
				result.setMsg("网络异常!请稍后重试!");
				logger.info("【客户个人中心/我的微仓/微仓发货 获取商品数据异常】");
			}
		}else{
			result.setState(Result.STATE_ERROR);
			result.setMsg("登陆超时,请重新登陆!");
			logger.info("登陆超时!客户个人中心/我的微仓/微仓发货!】");
		}
		return result;
	}
	
	
	/**
	 * 客户个人中心/我的微仓/微仓发货 ----生成订单，微仓中相应商品数量减少。
	 * @author BYG 2018.5.23
	 * @param String mwgIDAndNum, String saID, String customerRemark
	 * @return 
	 */
	@RequestMapping(value="/generateOrder",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> generateOrder(String mwgIDAndNum, String saID, String customerRemark) throws Exception {
		Result<Object> result = new Result<Object>();
		String customerID = Jurisdiction.getCustomerID();
		if(customerID != null){
			//mwgIDAndNum数据格式：[{"mwgID":"1","sendNum":"1"},{"mwgID":"2","sendNum":"2"}]
			if (Tools.notEmptys(mwgIDAndNum)) {
				@SuppressWarnings("unchecked")
				List<Map<String, String>> orderGoodsList = JSONArray.parseObject(mwgIDAndNum, List.class);
				try {
					//添加微仓订单及商品
					Map<String, String> resultMap = microWarehouseServiceImpl.addMWOrder(orderGoodsList, saID ,customerID, customerRemark);	
					result.setState(resultMap.get("result"));
					result.setMsg(resultMap.get("msg"));
				}catch (Exception e) {
					result.setState(Result.STATE_ERROR);
					result.setMsg("网络异常！请稍后重试。");
					logger.info("CCMWC:微仓发货 ----生成订单"+DateUtil.getSdfTimes() + ",客户:" + customerID + "生成订单异常");
					e.printStackTrace();
				}
			}else{
				result.setState(Result.STATE_ERROR);
				result.setMsg("参数错误,请稍后重试!");
				logger.info("【CCMWC:参数为空,微仓发货 ----生成订单】");
			}
		}else{
			result.setState(Result.STATE_ERROR);
			result.setMsg("登陆超时!请重新登陆");
			logger.info("【CCMWC:登陆超时,微仓发货 ----生成订单登陆超时!】");
		}
		return result;
	}
	
}
