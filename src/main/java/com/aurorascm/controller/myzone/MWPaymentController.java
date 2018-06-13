package com.aurorascm.controller.myzone;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.aurorascm.controller.BaseController;
import com.aurorascm.entity.Page;
import com.aurorascm.entity.Result;
import com.aurorascm.service.AreaAddrService;
import com.aurorascm.service.myzone.MicroWareHouseService;
import com.aurorascm.util.AppUtil;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/** 客户个人中心/微仓订单付款
 * @author BYG 2017-8-28
 * @version 1.0
 */
@Controller
@RequestMapping(value="/mwPayment")
public class MWPaymentController extends BaseController {
	
	@Resource(name="microWarehouseServiceImpl")
	private MicroWareHouseService microWarehouseServiceImpl;
	@Resource(name="areaAddrServiceImpl")
	private AreaAddrService areaAddrServiceImpl;

	
	/**客户个人中心/我的微仓/订单付款  页面
	 * @author BYG 2018.5.23
	 * @param Page page
	 * @return mwOrderMap
	 * @throws Exception
	 */
	@RequestMapping(value="/goMWPayment")
	public ModelAndView goMWOrder(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String msg = "";
		String customerID = Jurisdiction.getCustomerID();
		Map<String,Object> mwOrderMap = new LinkedHashMap<String,Object>();
		if (customerID != null) {
			pd.put("customerID", customerID);
			page.setPd(pd);
			page.setPageSize(10);
			try {
				List<PageData> mwOrderIDList = microWarehouseServiceImpl.getMWOrderID(page); 			//根据条件获取订单ID
				int mwOrderIDNum = microWarehouseServiceImpl.getMWOrderIDNum(page);
				page.setTotalRecord(mwOrderIDNum);
				for(PageData o : mwOrderIDList){
					List<PageData> orderGoods = microWarehouseServiceImpl.getGoodsByMWOrderID(o.getString("mw_order_id")); //根据微仓定单号获取定单包含的商品
					mwOrderMap.put(o.getString("mw_order_id"), orderGoods);
				}
			} catch (Exception e) {
				e.printStackTrace();
				msg = "网络异常!请稍后重试!";
				logger.info("CCMWC:系统错误!我的微仓/订单付款页面系统执行异常!");
				throw new Exception(msg);
			}
		}else{
			msg = "登陆超时,请重新登陆!";
			logger.info("【CCMWC:微仓删除商品--登陆超时】");
			throw new Exception(msg);
		}
		
		mv.addObject("mwOrderMap", JSON.toJSON(mwOrderMap));
		mv.addObject("pd", pd);
		mv.addObject("page", page);
		mv.addObject("msg", msg);
		mv.setViewName("system/myzone/wcfk");
		mv.addObject("menuIndex", 10);
		return mv;
	}
	
	/**
	 * 客户个人中心/我的微仓/订单付款- --删除微仓订单---微仓中相应商品数量增加。
	 */
	@RequestMapping(value="/deleteMWOrder",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> deleteMWOrder(String mwOrderID) throws Exception {
		Result<Object> result = new Result<Object>();
		String customerID = Jurisdiction.getCustomerID();
		if(customerID != null){
			if (Tools.notEmptys(mwOrderID)){
				try {
					microWarehouseServiceImpl.deleteMWOrder(mwOrderID);	//删除微仓订单及商品
					result.setState(Result.STATE_SUCCESS);
				} catch (Exception e) {
					result.setState(Result.STATE_ERROR);
					result.setMsg("网络异常!请稍后重试!");
					logger.info("CCMWC:"+DateUtil.getSdfTimes() + ",客户:" + customerID + "删除微仓订单异常");
				}
			}else{
				result.setState(Result.STATE_ERROR);
				result.setMsg("参数异常,删除失败!");
				logger.info("【CCMWC:参数为空,删除微仓订单参数错误!】");
			}
		}else{
			result.setState(Result.STATE_ERROR);
			result.setMsg("登陆超时,请重新登陆!");
			logger.info("【CCMWC:登陆超时,删除微仓订单!】");
		}
		return result;
	}
	
	/**客户个人中心/我的微仓/订单付款--去结算/尾款支付
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goMWOrderSettle")
	public ModelAndView goMWOrderSettle(String mwOrderID)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String msg = "";
		String customerID = Jurisdiction.getCustomerID();
		String viewName = null;
		if(customerID != null){
			try {
				Map<String, Object> m = microWarehouseServiceImpl.updateGetPayment(mwOrderID); 		//计算支付金额;同时生成一个合并订单ID
				int result = (int) m.get("result");
				if (result == 1) {
					Page page = new Page();
					pd.put("customerID", customerID);
					page.setPd(pd);
					page.setPageSize(10);
					Map<String,Object> mwOrderMap = new LinkedHashMap<String,Object>();		
					List<PageData> mwOrderIDList = microWarehouseServiceImpl.getMWOrderID(page); 			//根据条件获取订单ID
					int mwOrderIDNum = microWarehouseServiceImpl.getMWOrderIDNum(page);
					page.setTotalRecord(mwOrderIDNum);
					for(PageData o : mwOrderIDList){
						List<PageData> orderGoods = microWarehouseServiceImpl.getGoodsByMWOrderID(o.getString("mw_order_id")); //根据微仓定单号获取定单包含的商品
						mwOrderMap.put(o.getString("mw_order_id"), orderGoods);
					}
					mv.addObject("mwOrderMap", mwOrderMap);
					mv.addObject("pd", pd);
					mv.addObject("page", page);
					viewName = "system/myzone/wcfk";
					msg = "订单已提交！请到我的订单中查看！";
				} else {
					mv.addObject("mwOrderGoods", JSON.toJSON(m.get("mwOrderGoods")));
					mv.addObject("mwOrder", m.get("mwOrder"));
					mv.addObject("payment", m.get("payment"));
					mv.addObject("payOrderID", m.get("payOrderID"));
					mv.addObject("pType", "mwwk");
					viewName = "system/myzone/mwOrderSettle";
				}
			} catch (Exception e) {
				e.printStackTrace();
				msg = "网络异常!请稍后重试!";
				logger.info("【CCMWC:系统执行异常!我的微仓/订单付款--去结算/尾款支付】");
				throw new Exception(msg);
			}
		}else{
			msg = "登陆超时,请重新登陆!";
			logger.info("【CCMWC:登陆超时,我的微仓/订单付款--去结算/尾款支付】");
			throw new Exception(msg);
		}	
		mv.addObject("msg", msg);
		mv.setViewName(viewName);
		return mv;
	}
	
}
