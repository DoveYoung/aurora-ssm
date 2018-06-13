package com.aurorascm.controller.myzone;


import java.util.ArrayList;
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

import com.alibaba.fastjson.JSONArray;
import com.aurorascm.controller.BaseController;
import com.aurorascm.entity.Page;
import com.aurorascm.entity.Result;
import com.aurorascm.service.AreaAddrService;
import com.aurorascm.service.myzone.MicroWareHouseService;
import com.aurorascm.util.AppUtil;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.MyDataException;
import com.aurorascm.util.MyParamException;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/** 客户个人中心/我的微仓
 * @author BYG 2017-8-28
 * @version 1.0
 */
@Controller
@RequestMapping(value="/customerMW")
public class CustomerMWController extends BaseController {
	
	@Resource(name="microWarehouseServiceImpl")
	private MicroWareHouseService microWarehouseServiceImpl;
	@Resource(name="areaAddrServiceImpl")
	private AreaAddrService areaAddrServiceImpl;
	
	/**客户个人中心/我的微仓/微仓货物 页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goMicroWarehouse")
	public ModelAndView goMicroWarehouse(Page page,  String keyword, String beginTime, String endTime)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String msg = "";
		keyword = Tools.notEmptys(keyword) ? keyword.replace(" ", "") : null;
		beginTime = Tools.notEmptys(beginTime) ? beginTime.replace(" ", "") : null;
		endTime = Tools.notEmptys(endTime) ? endTime.replace(" ", "") : null;
		Session session = Jurisdiction.getSession();
		String customerID = (String)session.getAttribute("customerID");
		customerID = Tools.notEmptys(customerID) ? customerID.replace(" ", "") : null;
		pd.put("keyword", keyword);
		pd.put("beginTime", beginTime);
		pd.put("endTime", endTime);
		pd.put("customerID", customerID);
		page.setPd(pd);
		page.setPageSize(10);
		List<PageData> customerMWGoods = new ArrayList<PageData>();
		try{
			customerMWGoods = microWarehouseServiceImpl.getCustomerMWGoods(page);			//根据条件获取客户微仓商品
			int customerMWGNum = microWarehouseServiceImpl.getCustomerMWGNum(page);
			page.setTotalRecord(customerMWGNum);
		} catch (Exception e) {
			msg = "微仓页面跳转失败!请稍后重试!";
			logger.info("【CCMWC:客户个人中心/我的微仓页面请求执行异常】");
			throw new Exception(msg);
		}
		mv.addObject("customerMWGoods", customerMWGoods);
		mv.addObject("pd", pd);
		mv.addObject("page", page);
		mv.addObject("msg", msg);
		mv.addObject("rType", "wchw");
		mv.setViewName("system/myzone/myMicroWarehouse");
		mv.addObject("menuIndex", 4);
		return mv;
	}
	
	/**
	 * 客户个人中心/我的微仓 页面 删除商品
	 */
	@RequestMapping(value="/deleteMWGoods",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object deleteMWGoods(String mwgID) throws Exception {
		PageData pd = new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "";
		String msg = "";
		pd = this.getPageData();
		pd.put("mwgID", mwgID);
		Session session = Jurisdiction.getSession();
		String customerID = (String) session.getAttribute("customerID");
		if(Tools.notEmptys(customerID)){
			pd.put("customerID", customerID);
			result = microWarehouseServiceImpl.deleteMWGoods(pd);			//根据条件删除客户微仓商品
			if("failed1".equals(result)){                   //客户微仓中此商品数量>0
				result = "failed";
				msg = "删除失败!该商品微仓中数量不为0!";
				logger.info("【CCMWC:客户微仓中此商品数量>0】");
			}else if("failed2".equals(result)){
				result = "failed";
				msg = "删除失败!微仓订单中包含此商品!";
				logger.info("【CCMWC:微仓订单中包含此商品!】");
			}else if("error".equals(result)){
				msg = "删除失败!请稍后重试!";
				logger.info("【CCMWC:客户微仓中删除商品执行异常】");
			}else{
				logger.info(DateUtil.getSdfTimes() + ",客户:" + customerID + "删除微仓商品:" + mwgID);
			}
		}else{
			msg = "登陆超时,请重新登陆!";
			result = "failed"; 
			logger.info("【CCMWC:微仓删除商品--登陆超时】");
		}
		map.put("result", result);
		map.put("msg", msg);
		map.put("pd", pd);
		return AppUtil.returnObject(pd, map);
	}

	/**
	 * 客户个人中心/我的微仓/微仓发货
	 */
	@RequestMapping(value="/goDeliverGoods")
	public ModelAndView goMicroWarehouse()throws Exception{
		ModelAndView mv = this.getModelAndView();
		String msg = "";
		Session session = Jurisdiction.getSession();
		String customerID = (String)session.getAttribute("customerID");
		customerID = Tools.notEmptys(customerID) ? customerID.replace(" ", "") : null;
		if(Tools.notEmptys(customerID)){
			try{
				Map<String, Object> m = microWarehouseServiceImpl.getShipAddress(customerID);
				mv.addObject("wc1Address", m.get("wc1Address"));
				mv.addObject("shipAddress5", m.get("shipAddress5"));
				mv.addObject("newShipAddr", m.get("newShipAddr"));
			} catch (Exception e) {
				e.printStackTrace();
				msg = "网络异常!请稍后重试!";
				logger.info("【CCMWC:客户个人中心/我的微仓/微仓发货 初始化数据异常】");
				throw new Exception(msg);
			}
		}else{
			msg = "登陆超时,请重新登陆!";
			logger.info("【CCMWC:客户个人中心/我的微仓/微仓发货,登陆超时!】");
			throw new Exception(msg);
		}
		mv.addObject("msg", msg);
		mv.addObject("rType", "wcfh");
		mv.setViewName("system/myzone/myMicroWarehouse");
		mv.addObject("menuIndex", 4);
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
	 */
	@RequestMapping(value="/getGoodsByMwgID",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getGoodsByMwgID(String mwgID) throws Exception {
		PageData pd = new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "";
		String msg = "";
		pd = this.getPageData();
		pd.put("mwgID", mwgID);
		Session session = Jurisdiction.getSession();
		String customerID = (String)session.getAttribute("customerID");
		customerID = Tools.notEmptys(customerID) ? customerID.replace(" ", "") : null;
		if(Tools.notEmptys(customerID)){
			try{
				PageData mwGoods = microWarehouseServiceImpl.getGoodsByMwgID(mwgID);
				map.put("mwGoods", mwGoods);
				result = "success";
			} catch (Exception e) {
				result = "error";
				msg = "网络异常!请稍后重试!";
				logger.info("【CCMWC: 客户个人中心/我的微仓/微仓发货 获取商品数据异常】");
			}
		}else{
			result = "failed"; 
			msg = "登陆超时,请重新登陆!";
			logger.info("【CCMWC: 登陆超时!客户个人中心/我的微仓/微仓发货!】");
		}
		map.put("result", result);
		map.put("msg", msg);
		map.put("pd", pd);
		return AppUtil.returnObject(pd, map);
	}
	
//	/**
//	 * 客户个人中心/我的微仓/微仓发货 ----生成订单，预请求CHECK限额和购买数量。
//	 */
//	@RequestMapping(value="/checkAmount",produces="application/json;charset=UTF-8")
//	@ResponseBody
//	public Object checkAmount(String mwgIDAndNum, String saID) throws Exception {
//		PageData pd = new PageData();
//		Map<String,Object> map = new HashMap<String,Object>();
//		String result = "";
//		String msg = "";
//		pd = this.getPageData();
//		Session session = Jurisdiction.getSession();
//		String customerID = (String)session.getAttribute("customerID");
//		customerID = Tools.notEmptys(customerID) ? customerID.replace(" ", "") : null;
//		//mwgIDAndNum数据格式：[{"mwgID":"1","sendNum":"1"},{"mwgID":"2","sendNum":"2"}]
//		if (Tools.notEmptys(mwgIDAndNum)) {
//			@SuppressWarnings("unchecked")
//			List<Map<String, String>> orderGoodsList = JSONArray.parseObject(mwgIDAndNum, List.class);
//			Map<String, String> rmap = microWarehouseServiceImpl.checkAmount(orderGoodsList, saID);
//			msg = rmap.get("msg");
//			result = rmap.get("result");
//		}else{
//			msg = "参数为空,请稍后重试!";
//			result = "failed"; 
//			logger.info("CCMWC:微仓发货 --生成订单 "+msg);
//		}
//		map.put("result", result);
//		map.put("msg", msg);
//		return AppUtil.returnObject(pd, map);
//	}
	
	/**
	 * 客户个人中心/我的微仓/微仓发货 ----生成订单，微仓中相应商品数量减少。
	 */
	@RequestMapping(value="/generateOrder",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object generateOrder(String mwgIDAndNum, String saID, String customerRemark) throws Exception {
		PageData pd = new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "";
		String msg = "";
		pd = this.getPageData();
		Session session = Jurisdiction.getSession();
		String customerID = (String)session.getAttribute("customerID");
		customerID = Tools.notEmptys(customerID) ? customerID.replace(" ", "") : null;
		if(Tools.notEmptys(customerID)){
			//mwgIDAndNum数据格式：[{"mwgID":"1","sendNum":"1"},{"mwgID":"2","sendNum":"2"}]
			if (Tools.notEmptys(mwgIDAndNum)) {
				@SuppressWarnings("unchecked")
				List<Map<String, String>> orderGoodsList = JSONArray.parseObject(mwgIDAndNum, List.class);
				try {
					//添加微仓订单及商品
					Map<String, String> resultMap = microWarehouseServiceImpl.addMWOrder(orderGoodsList, saID ,customerID, customerRemark);	
					result = resultMap.get("result");
					msg = resultMap.get("msg");
				}catch (Exception e) {
					msg = "操作失败!请稍后重试!";
					result = "error"; 
					logger.info("CCMWC:微仓发货 ----生成订单"+DateUtil.getSdfTimes() + ",客户:" + customerID + "生成订单异常");
					e.printStackTrace();
				}
			}else{
				msg = "参数错误,请稍后重试!";
				result = "failed"; 
				logger.info("【CCMWC:参数为空,微仓发货 ----生成订单】");
			}
		}else{
			msg = "登陆超时!请重新登陆";
			result = "failed"; 
			logger.info("【CCMWC:登陆超时,微仓发货 ----生成订单登陆超时!】");
		}
		map.put("result", result);
		map.put("msg", msg);
		map.put("pd", pd);
		return AppUtil.returnObject(pd, map);
	}
	
	/**客户个人中心/我的微仓/订单付款  页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goMWOrder")
	public ModelAndView goMWOrder(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String msg = "";
		Session session = Jurisdiction.getSession();
		String customerID = (String)session.getAttribute("customerID");
		customerID = Tools.notEmptys(customerID) ? customerID.replace(" ", "") : null;
		pd.put("customerID", customerID);
		page.setPd(pd);
		page.setPageSize(10);
		Map<String,Object> mwOrderMap = new LinkedHashMap<String,Object>();		
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
		mv.addObject("mwOrderMap", mwOrderMap);
		mv.addObject("pd", pd);
		mv.addObject("page", page);
		mv.addObject("msg", msg);
		mv.addObject("rType", "ddfk");
		mv.setViewName("system/myzone/myMicroWarehouse");
		mv.addObject("menuIndex", 4);
		return mv;
	}
	
	/**
	 * 客户个人中心/我的微仓/订单付款- --删除微仓订单---微仓中相应商品数量增加。
	 */
	@RequestMapping(value="/deleteMWOrder",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object deleteMWOrder(String mwOrderID) throws Exception {
		PageData pd = new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "";
		String msg = "";
		pd = this.getPageData();
		Session session = Jurisdiction.getSession();
		String customerID = (String)session.getAttribute("customerID");
		customerID = Tools.notEmptys(customerID) ? customerID.replace(" ", "") : null;
		if(Tools.notEmptys(customerID)){
			if (Tools.notEmptys(mwOrderID)){
				try {
					microWarehouseServiceImpl.deleteMWOrder(mwOrderID);	//删除微仓订单及商品
					result = "success"; 
				} catch (Exception e) {
					msg = "网络异常!请稍后重试!";
					result = "error"; 
					logger.info("CCMWC:"+DateUtil.getSdfTimes() + ",客户:" + customerID + "删除微仓订单异常");
					throw new Exception();
				}
			}else{
				msg = "参数异常,删除失败!请稍后重试!";
				result = "failed"; 
				logger.info("【CCMWC:参数为空,删除微仓订单参数错误!】");
				throw new Exception(msg);
			}
		}else{
			msg = "登陆超时,请重新登陆!";
			result = "failed"; 
			logger.info("【CCMWC:登陆超时,删除微仓订单!】");
			throw new Exception();
		}
		map.put("result", result);
		map.put("msg", msg);
		map.put("pd", pd);
		return AppUtil.returnObject(pd, map);
	}
	
	/**客户个人中心/我的微仓/订单付款--去结算/尾款支付
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goMWOrderSettle")
	public ModelAndView goMWOrderSettle(String mwOrderID)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String msg = "";
		Session session = Jurisdiction.getSession();
		String customerID = (String)session.getAttribute("customerID");
		String viewName = null;
		if(Tools.notEmptys(customerID)){
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
					mv.addObject("rType", "ddfk");
					mv.addObject("menuIndex", 4);
					viewName = "system/myzone/myMicroWarehouse";
					msg = "订单已提交！请到我的订单中查看！";
				} else {
					mv.addObject("mwOrderGoods", m.get("mwOrderGoods"));
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
