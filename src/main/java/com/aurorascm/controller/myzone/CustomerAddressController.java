package com.aurorascm.controller.myzone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aurorascm.controller.BaseController;
import com.aurorascm.entity.Page;
import com.aurorascm.service.AreaAddrService;
import com.aurorascm.service.myzone.AddressService;
import com.aurorascm.util.AppUtil;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/** 个人中心
 * 		---收货地址
 * @author SSY 2017/8/30
 * @version 1.0
 */
@Controller
@RequestMapping(value="/receiverAddress")
public class CustomerAddressController extends BaseController{
	
	@Resource(name="addressServiceImpl")
	private AddressService addressServiceImpl;
	@Resource(name="areaAddrServiceImpl")
	private AreaAddrService areaAddrServiceImpl; 
	/**
	  * 去个人收货地址页面;
	  * @param
	  * 
	  */
	@RequestMapping(value="/addressList",produces="application/json;charset=UTF-8")
	public ModelAndView goAddressList(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String msg = "";
		List<PageData> provinceList = new ArrayList<PageData>();//获取省级地区--新增收货地址使用;
		List<PageData> addressList = new ArrayList<PageData>(); //个人收货地址集合;
		pd.put("customerID", Jurisdiction.getCustomerID());
		page.setPageSize(10);
		page.setPd(pd);
		try {
			int num = addressServiceImpl.getAddressNum(page);
			page.setTotalRecord(num);
			addressList = addressServiceImpl.getAddress(page);
			provinceList = areaAddrServiceImpl.getProvince();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "网络异常!请稍后重试!";
			logger.info("【CCADC:查询个人收货地址执行异常！】");
			throw new Exception(msg);
		}
		mv.addObject("page", page);
		mv.addObject("provinceList", provinceList);//获取省级地区--新增收货地址使用;
		mv.addObject("addressList", addressList);//个人收货地址集合;
		mv.setViewName("system/myzone/addressList");
		mv.addObject("menuIndex", 6);
		return mv;
	}
	  
	/**
	 * 提交订单页面---获取北极光微仓地址+常用四个+最新一个.
	 * @param pageNum 页码
	 * @param mw 需要微仓地址传1；不需要微仓地址传2。
	 */
	@RequestMapping(value="/get3ShipAddr", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object get3ShipAddr(int pageNum, int mw)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		Session session = Jurisdiction.getSession();
		String customerID = (String) session.getAttribute("customerID");
		pd.put("customerID", customerID);
		int start = 0;
		int end = 0;
		if(mw ==1) {
			if (pageNum == 1) {
				start = 0;
				end = 4;
			} else {
				start = (pageNum - 1)*5 - 1;
				end = 5;
			}	
		}else {
			start = (pageNum - 1)*5;
			end = 5;
		}
		pd.put("start", start);
		pd.put("end", end);
		try {
//			if (pageNum == 1 && mw ==1) {
//				PageData wc1Address = addressServiceImpl.getWC1Address(customerID);
//				map.put("wc1Address", wc1Address);
//			} 
			PageData newShipAddr = addressServiceImpl.getNewShipAddr(customerID);
			List<PageData> provinceList = areaAddrServiceImpl.getProvince();
			Object newSaID = newShipAddr.get("sa_id");
			pd.put("newSaID", newSaID);
			int shipAddrNum = addressServiceImpl.getShipAddrNum(pd) + 1;//获取收获地址总数，除去微仓地址和最新地址
			int maxPageNum = (int) Math.ceil(shipAddrNum/5)+1;
			List<PageData> shipAddress5 = addressServiceImpl.get5ShipAddress(pd);
			map.put("newShipAddr", newShipAddr);
			map.put("maxPageNum", maxPageNum);
			map.put("shipAddress5", shipAddress5);
			map.put("provinceList", provinceList);
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			result = "failed";
			msg = "收获地址获取异常！";
			logger.info("error：收获地址获取异常！");
		}
		map.put("result", result);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
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
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		Session session = Jurisdiction.getSession();
		String customerID = (String) session.getAttribute("customerID");
		String name = Tools.notEmptys(pd.getString("name")) ? pd.getString("name").replace(" ", "") : null;
		String mobile = Tools.notEmptys(pd.getString("mobile")) ? pd.getString("mobile").replace(" ", "") : null;
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
				pd.put("province", province);
				pd.put("provincePin", provincePin);
				pd.put("city", city);
				pd.put("area", area);
				pd.put("detailAddr", detailAddr);
				pd.put("IDCard", IDCard);
				pd.put("addressType", 4);
				pd.put("operateTime", DateUtil.getTime());
				addressServiceImpl.addShipAddr(pd);
				PageData newShipAddr = addressServiceImpl.getNewShipAddr(customerID);
				map.put("newShipAddr", newShipAddr);
				result = "success";
			} catch (Exception e) {
				e.printStackTrace();
				result = "error";
				msg = "网络异常,请稍后再试!";
				logger.info("【CAAC:系统异常,新增收获地址执行异常！】");
			}
		} else {
			result = "failed";
			msg = "网络异常,请稍后再试！";
			logger.info("【CAAC:新增收获地址参数不正确！】");
		}
		map.put("result", result);
		map.put("pd", pd);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
	}
	
	
	/**
	 * 保存修改后的收货地址;
	 * @param  name mobile province city area detailAddr IDCard
	 * @return result
	 * @throws Exception
	 */
	@RequestMapping(value="/updateShipAddr", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object updateShipAddr()throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		Session session = Jurisdiction.getSession();
		String customerID =  (String)session.getAttribute("customerID");
		String name = Tools.notEmptys(pd.getString("name")) ? pd.getString("name").replace(" ", "") : null;
		String mobile = Tools.notEmptys(pd.getString("mobile")) ? pd.getString("mobile").replace(" ", "") : null;
		String telephone = Tools.notEmptys(pd.getString("telephone")) ? pd.getString("telephone").replace(" ", "") : null;
		String province =  Tools.notEmptys(pd.getString("province"))  ? pd.getString("province").replace(" ", "") : null;
		String provincePin =  Tools.notEmptys(pd.getString("provincePin")) ? pd.getString("provincePin").replace(" ", "") : null;
		String city =  Tools.notEmptys(pd.getString("city")) ? pd.getString("city").replace(" ", "") : null;
		String area =  Tools.notEmptys(pd.getString("area"))  ? pd.getString("area").replace(" ", "") : null;
		String detailAddr = Tools.notEmptys(pd.getString("detailAddr"))  ? pd.getString("detailAddr").replace(" ", "") : null;
		String IDCard = Tools.notEmptys(pd.getString("IDCard"))  ? pd.getString("IDCard").replace(" ", "") : null;
		String saID = Tools.notEmptys(pd.getString("saID"))  ? pd.getString("saID").replace(" ", "") : null;
		
		if (name!=null&&saID!=null&&provincePin!=null&&mobile!=null&&province!=null&&IDCard!=null&&city!=null&&area!=null&&detailAddr!=null) {
			pd.put("customerID", customerID);
			pd.put("name", name);
			pd.put("mobile", mobile);
			pd.put("telephone", telephone);
			pd.put("province", province);
			pd.put("provincePin", provincePin);
			pd.put("city", city);
			pd.put("saID", saID);
			pd.put("area", area);
			pd.put("detailAddr", detailAddr);
			pd.put("IDCard", IDCard);
			pd.put("operateTime", DateUtil.getTime());
			try{
				addressServiceImpl.updateShipAddr(pd);
				result = "success";
			}catch(Exception e){
				result = "error";
				msg = "网络异常!请稍后重试!";
				logger.info("【CCADC:保存修改后的收获地址执行异常！】");
			}
			
		}else{
			result = "failed";
			msg = "请填写完整的收货地址!";
			logger.info("【CCAC:保存修改的收获地址参数错误！】");
		}
		map.put("result", result);
		map.put("pd", pd);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
	}
	
	/**
	 * 删除收货地址;(支持批量删除,saID逗号分割)
	 * @param saID(个人收货地址id)
	 */
	@RequestMapping(value="/deleteShipAddr", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object deleteShipAddr(String saID)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		String customerID =  Jurisdiction.getCustomerID();
		if (Tools.notEmptys(saID)) {
			pd.put("customerID", customerID);
			pd.put("saIDs", saID.split(","));
			try {
				addressServiceImpl.deleteShipAddr(pd);
				result = "success";
			} catch (Exception e) {
				e.printStackTrace();
				msg = "网络异常!请稍后重试!";
				logger.info("【CCADC:删除个人收货地址执行异常!】");
			}
		}else{
			result = "failed";
			msg = "请选择您要删除的地址!";
			logger.info("【CCADC:删除个人收货地址参数错误!】");
		}
		map.put("result", result);
		map.put("pd", pd);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
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
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		String saID = Tools.notEmptys(pd.getString("saID")) ? pd.getString("saID").replace(" ", "") : null;
		if (saID != null) {
			try {
				PageData uShipAddr = addressServiceImpl.getShipAddr(saID);
				map.put("uShipAddr", uShipAddr);
				result = "success";
			} catch (Exception e) {
				e.printStackTrace();
				result = "error";
				msg = "网络异常,请稍后再试!";
				logger.info("【CAAC:根据地址ID获取详细信息出现异常！】");
			}
		} else {
			result = "failed";
			msg = "网络异常,请稍后再试!";
			logger.info("【CAAC:地址ID参数获取失败!】");
		}
		map.put("result", result);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
	}
	
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
	public Object likeShipAddr() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		Session session = Jurisdiction.getSession();
		String customerID = (String) session.getAttribute("customerID");
		String keyword = pd.getString("keyword") != null && !"".equals(pd.getString("keyword").replace(" ", ""))
				? pd.getString("keyword").replace(" ", "") : null;
		pd.put("customerID", customerID);
		pd.put("keyword", keyword);
		if (customerID != null && keyword != null) {
			try {
				List<PageData> likeShipAddr = addressServiceImpl.likeShipAddr(pd);
				map.put("likeShipAddr", likeShipAddr);
				result = "success";
			} catch (Exception e) {
				result = "error";
				msg = "网络异常!没有搜索到相关地址！";
				logger.info("【CAAC:没有搜索到相关地址系统执行异常!】");
			}
		} else {
			result = "failed";
			msg = "搜索参数有误!";
			logger.info(msg);
		}
		map.put("result", result);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
	}

	
}
