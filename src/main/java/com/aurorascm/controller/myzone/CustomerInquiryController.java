package com.aurorascm.controller.myzone;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.aurorascm.controller.BaseController;
import com.aurorascm.entity.InquiryGoods;
import com.aurorascm.entity.InquiryManage;
import com.aurorascm.entity.Page;
import com.aurorascm.entity.Result;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/**
 * @Title: CustomerInquiryController.java 
 * @Package com.aurorascm.controller.customer 
 * @Description: 询价
 * @author SSY  
 * @date 2017年12月25日 下午4:26:30 
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/customerInquiry")
public class CustomerInquiryController extends BaseController {

	/**
	 * 跳至我的询价,查看所有询价单
	 * @param Page page,  String keyword(搜索关键字id或者name), Integer inquiryState(1.报价未提交;2报价已提交;3.已失效), String beginTime
	 * @return pendingNum,finishNum,overdueNum,inquiryList,page,menuIndex,
	 * @exception
	 * @author SSY 2018-1-5
	 */
	@RequestMapping(value = "/inquiryList", produces = "application/json;charset=UTF-8")
	public String goinquiryList(ModelMap modelMap, Page page,  String keyWord, Integer inquiryState, String beginTime) throws Exception {
		page.setPageSize(10);
		try {
			PageData pd = new PageData();
			pd.put("keyWord", Tools.notEmptys(keyWord)?keyWord.replace(" ", ""):null);
			pd.put("inquiryState", inquiryState);
			pd.put("beginTimes", Tools.notEmptys(beginTime)?DateUtil.getBeforeDayDate(beginTime.replace(" ", "")):null);
			pd.put("beginTime",beginTime);//回显
			pd.put("customerID", Jurisdiction.getCustomerID());
			page.setPd(pd);
			List<InquiryManage> inquiryList = inquiryServiceImpl.getInquiryList(page);
			page.setTotalRecord(inquiryServiceImpl.getInquiryNum(page));
			int pendingNum = inquiryServiceImpl.getInquiryStateNum(1); //  待报价数量( inquiryState = 1 )
			int finishNum = inquiryServiceImpl.getInquiryStateNum(2); //  询价完成数量(inquiryState = 2)
			int overdueNum = inquiryServiceImpl.getInquiryStateNum(3);//  询价失效 数量(inquiryState = 3)
			modelMap.put("inquiryList", JSON.toJSON(inquiryList));
			modelMap.put("pendingNum", JSON.toJSON(pendingNum));
			modelMap.put("finishNum", JSON.toJSON(finishNum));
			modelMap.put("overdueNum", JSON.toJSON(overdueNum));
			modelMap.put("pd", JSON.toJSON(pd));
			modelMap.put("menuIndex", 2);
			modelMap.put("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("ERROR:"+DateUtil.getTime()+"询价单列表查询执行异常!】");
			throw new Exception("网络异常!请稍后重试!");
		}
		return "system/myzone/inquiryList";
	}

	/**
	 * 查看报价页面,显示已经报价的商品,其余状态商品不显示;
	 * @param String inquiryID
	 * @return inquiry
	 */
	@RequestMapping(value = "/getInquiry", produces = "application/json;charset=UTF-8")
	public String getInquiry(ModelMap modelMap,String inquiryID) throws Exception {
		try {
			// 查看询价详情;
			InquiryManage inquiry = inquiryServiceImpl.getValidInquiry(inquiryID, 2, 2);//已报价,已提交
			modelMap.put("inquiry", JSON.toJSON(inquiry));
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("ERROR:"+DateUtil.getTime()+"询价单查询执行异常!】");
			throw new Exception("网络异常!请稍后重试!");
		}
		return "system/myzone/inquiryDetail";
	}

	/**
	 * 重新询价(已经失效状态的询价单,回显询价单里的商品信息)
	 * @paramString String inquiryID
	 * @return  inquiry ,  currency 
	 * @exception
	 * @author SSY 2018-1-5
	 */
	@RequestMapping(value = "/inquiryAgainPage", produces = "application/json;charset=UTF-8")
	public String inquiryAgain(ModelMap modelMap, String inquiryID) throws Exception {
		try {
			InquiryManage inquiry = inquiryServiceImpl.getInquiry(inquiryID);
//			List<InquiryGoods> inquiryGoodsList = inquiry!=null ? inquiry.getInquiryGoodsList() : new ArrayList<InquiryGoods>();
			List<PageData> currency = inquiryServiceImpl.getCurrency();
			modelMap.put("inquiry", JSON.toJSON(inquiry));
			modelMap.put("currency", JSON.toJSON(currency));
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("ERROR:"+DateUtil.getTime()+"重新询价回显询价单信息执行异常!");
			throw new Exception("操作失败!请重试或联系客服!");
		}
		return "system/inquiryStep/addInquiryPage";
	}
	
	/**
	 * 到询价页面; 
	 * @param    String goodsID(如果是从商品详情页进来必须);
	 * @return   inquiry,currency,menuIndex    
	 * @author SSY 2017-12-21
	 */
	@RequestMapping(value = "/goAddInquiryPage", produces = "application/json;charset=UTF-8")
	public String goAddInquiryPage(ModelMap modelMap, String goodsID) throws Exception {
		List<InquiryGoods> inquiryGoodsList = new ArrayList<InquiryGoods>();
		InquiryGoods inquiryGoods = new InquiryGoods();
		try {
			List<PageData> currency = inquiryServiceImpl.getCurrency();
			if (Tools.notEmptys(goodsID)) {
				inquiryGoods = inquiryServiceImpl.getInquriryGoods(goodsID);// 根据商品id查询;
			}
			inquiryGoodsList.add(inquiryGoods);// 询价---商品回显;
			InquiryManage inquiry = new InquiryManage();
			inquiry.setInquiryGoodsList(inquiryGoodsList);
			modelMap.put("inquiry", JSON.toJSON(inquiry));
			modelMap.put("currency", JSON.toJSON(currency));
			modelMap.put("menuIndex", 2);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("ERROR:"+DateUtil.getTime()+"询价查询商品异常!");
		}
		return "system/inquiryStep/addInquiryPage";
	}

	/**
	 * 保存询价单
	 * @param  inquiryManageJson:{"sourceID":null( 源询价单id不为空的时候,再次询价),"customerMobile":"1518152145","customerEmail":"54861654@qq.com",
	 * 				"inquiryGoodsList":[{'goodsBrand':'ceshi','goodsName':'ceshigoods','goodsMap':'www.baidu.com','goodsNorm':'5个/盒','goodsCode':'241551','buyNum':'52','deliverCountry':'中国','deliverCity':'杭州',
	 * 				'logisticsType':'1'(1海2陆3空),'tradeType':'3'(贸易方式：1CIF;2FCA;3EXW;4.FOB;),'customerRemark':'测试','currencySign':'¥','currencyName':'人民币','goodsMap':'www.baidu.com'}]}
	 * @return result
	 * @author SSY 2017-12-21
	 */
	@RequestMapping(value = "/saveInquiry", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> saveInquiry(String inquiryManageJson) throws Exception {
		Result<Object> result = new Result<Object>();
		try {
			InquiryManage inquiryManage = JSON.parseObject(inquiryManageJson, InquiryManage.class);
			inquiryServiceImpl.addInquiry(inquiryManage);
			result.setState(Result.STATE_SUCCESS);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setState(Result.STATE_ERROR);
			result.setMsg("操作失败!重试或客服!");
			logger.info("ERROR:"+DateUtil.getTime()+"询价提交执行异常!");
		}
		return result;
	}
}
