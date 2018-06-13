package com.aurorascm.controller.myzone;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/** 客户个人中心/微仓库存
 * @author BYG 2018-5-23
 * @version 2.0
 */
@Controller
@RequestMapping(value="/mwStock")
public class MWStockController extends BaseController {
	
	@Resource(name="microWarehouseServiceImpl")
	private MicroWareHouseService microWarehouseServiceImpl;
	@Resource(name="areaAddrServiceImpl")
	private AreaAddrService areaAddrServiceImpl;
	
	/**跳转到微仓库存页面
	 * @param Page page,  String keyword, int time(如果时间没选择默认0)
	 * @return customerMWGoods
	 * @throws Exception
	 */
	@RequestMapping(value="/goMWStock")
	public ModelAndView goWCStock(Page page,  int time, String keyword)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String msg = "";
		keyword = Tools.notEmptys(keyword) ? keyword.replace(" ", "") : null;
		String beginTime = null;
		String endTime = null;
		if (time != 0) {
			beginTime = DateUtil.getBeforeDayDate(String.valueOf(time * 30));
			endTime = DateUtil.getTime();
		}
		String customerID = Jurisdiction.getCustomerID();
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
		mv.addObject("customerMWGoods", JSON.toJSON(customerMWGoods));
		mv.addObject("pd", pd);
		mv.addObject("page", page);
		mv.addObject("msg", msg);
		mv.setViewName("system/myzone/wchw");
		mv.addObject("menuIndex", 8);
		return mv;
	}
	
	/**
	 * 客户个人中心/微仓库存 -- 删除商品
	 * @param String mwgID
	 * @author BYG 2018.5.23
	 */
	@RequestMapping(value="/deleteMWGoods",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> deleteMWGoods(String mwgID) throws Exception {
		Result<Object> result = new Result<Object>();
		PageData pd = this.getPageData();
		String msg = "";
		String state = "";
		pd.put("mwgID", mwgID);
		String customerID = Jurisdiction.getCustomerID();
		if(Tools.notEmptys(customerID)){
			pd.put("customerID", customerID);
			state = microWarehouseServiceImpl.deleteMWGoods(pd);			//根据条件删除客户微仓商品
			if("failed1".equals(state)){                   //客户微仓中此商品数量>0
				state = "failed";
				msg = "删除失败!该商品微仓中数量不为0!";
				logger.info("客户微仓中此商品数量>0】");
			}else if("failed2".equals(state)){
				state = "failed";
				msg = "删除失败!微仓订单中包含此商品!";
				logger.info("微仓订单中包含此商品!】");
			}else if("error".equals(state)){
				msg = "删除失败!请稍后重试!";
				logger.info("客户微仓中删除商品执行异常】");
			}else{
				logger.info(DateUtil.getSdfTimes() + ",客户:" + customerID + "删除微仓商品:" + mwgID);
			}
		}else{
			msg = "登陆超时,请重新登陆!";
			state = "failed"; 
			logger.info("【CCMWC:微仓删除商品--登陆超时】");
		}
		result.setMsg(msg);
		result.setState(state);
		result.setResult(pd);
		return result;
	}
}
