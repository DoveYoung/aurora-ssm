package com.aurorascm.controller.myzone;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.aurorascm.controller.BaseController;
import com.aurorascm.entity.ContractManage;
import com.aurorascm.entity.Page;
import com.aurorascm.entity.Result;
import com.aurorascm.util.CustomException;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/**
 * @Title: CustomerContractController.java 
 * @Package com.aurorascm.controller.customer 
 * @Description: 个人中心我的合同
 * @author SSY  
 * @date 2018年1月11日 上午10:15:04 
 * @version V1.0
 */
@Controller
@RequestMapping(value="/customerContract")
public class CustomerContractController extends BaseController{
	
	/**
	 * @Title: goinquiryList 
	 * @Description:  我的合同,查看我的所有合同单,只显示有效
	 * @param    Page page, String keyWord, String beginTime(近一个月29.近三个月89....), Integer contractState
	 * @return   
	 * @author SSY
	 * @date 2018年1月11日 上午10:17:42
	 */
	@RequestMapping(value = "/contractList", produces = "application/json;charset=UTF-8")
	public String goinquiryList(ModelMap modelMap, Page page, String keyWord, String beginTime, Integer contractState) throws Exception {
		PageData pd = new PageData();
		pd.put("beginTimes", Tools.notEmptys(beginTime)?DateUtil.getBeforeDayDate(beginTime.replace(" ", "")):null);
		pd.put("beginTime", beginTime);// 回显使用
		pd.put("keyWord", Tools.notEmptys(keyWord)?keyWord.replace(" ", ""):null);
		pd.put("contractState", contractState);
		pd.put("customerID", Jurisdiction.getCustomerID());
		page.setPd(pd);
		page.setPageSize(10);
		try {
			List<ContractManage> contractList = contractServiceImpl.getContractList(page);
			int totalRecord = contractServiceImpl.getContractNum(page);
			page.setTotalRecord(totalRecord);
			int pendingUploadNum = contractServiceImpl.getContractStateNum(new int[]{1}); // 合同待上传;
			int pendingPayNum = contractServiceImpl.getContractStateNum(new int[]{2}); // 合同待付款;
			int finishNum = contractServiceImpl.getContractStateNum(new int[]{3,4}); // 合同已完成;
			modelMap.put("pendingUploadNum", JSON.toJSON(pendingUploadNum));
			modelMap.put("pendingPayNum", JSON.toJSON(pendingPayNum));
			modelMap.put("finishNum", JSON.toJSON(finishNum));
			modelMap.put("contractList", JSON.toJSON(contractList));
			modelMap.put("pd", JSON.toJSON(pd));
			modelMap.put("page", page);
			modelMap.put("menuIndex", 3);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("ERROR:"+DateUtil.getTime()+"合同单列表查询执行异常!】");
			throw new Exception("网络异常!请稍后重试!");
		}
		return "system/myzone/contractList";
	}
	
	/**
	 * @Title: createContract 
	 * @Description: 询价采购,生成合同采购单;
	 * @param String inquiryID,String inquiryGoodsIDs,String company, String address
	 * @return Object  
	 * @author SSY
	 * @date 2018年1月11日 上午10:48:13
	 */
	@RequestMapping(value="/createContract",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> createContract(String inquiryID,String inquiryGoodsIDs,String company, String address) throws Exception {
		Result<Object> result = new Result<Object>();
		try { 
			contractServiceImpl.createContract(inquiryID,inquiryGoodsIDs, company, address);
			result.setMsg("操作成功!");
			result.setState(Result.STATE_SUCCESS);
		} catch (CustomException ce) {
			result.setMsg(ce.getMessage());
			result.setState(Result.STATE_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("ERROR:"+DateUtil.getTime()+"生成合同采购单执行异常！");
			result.setState(Result.STATE_ERROR);
			result.setMsg("采购失败!重试或联系客服!");
		}
		return result;
	}
	
	/**
	 * @Title: contractPay 
	 * @Description: 合同付款页面
	 * @param  String contractID
	 * @return String  
	 * @author SSY
	 * @date 2018年1月11日 下午1:46:36
	 */
	@RequestMapping(value = "/getcontractPay", produces = "application/json;charset=UTF-8")
	public ModelAndView contractPay(String contractID) throws Exception {
		ModelAndView mv = this.getModelAndView();
		if (contractServiceImpl.getContractIDCurrencyName(contractID)) {//如果是人民币；
			BigDecimal shouldPay = contractServiceImpl.getContractMoneyByCID(contractID);
			mv.addObject("shouldPay",shouldPay);
		}else{
			throw new Exception("除人民币，其他结算币种请联系客服线下付款！");
		}
		mv.addObject("contractID",contractID);
		mv.addObject("pType", "mcp");
		mv.setViewName("system/order/payway");
		return mv;
	}
}
