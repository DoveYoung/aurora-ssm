package com.aurorascm.controller.myzone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aurorascm.controller.BaseController;
import com.aurorascm.entity.Page;
import com.aurorascm.entity.Result;
import com.aurorascm.service.myzone.AttentionService;
import com.aurorascm.util.AppUtil;
import com.aurorascm.util.CustomException;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/** 个人中心
 * 		---我的关注
 * @author SSY 2017/8/29
 * @version 1.0
 */
@Controller
@RequestMapping(value="/customerAttention")
public class CustomerAttentionController extends BaseController{
	
	@Resource(name="attentionServiceImpl")
	private AttentionService attentionServiceImpl;
	
	/**个人中心 --- 我的关注页面 
	 * @param Page
	 * @return
	 */
	@RequestMapping(value="/attentionList",produces="application/json;charset=UTF-8")
	public ModelAndView goAttentionList(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		String msg = "";
		//获取用户关注品牌的商品,根据销量排序前4个;
		Map<String, List<PageData>> attention = new HashMap<String, List<PageData>>();
		try {
			attention = attentionServiceImpl.getAttention(page);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "网络异常!请稍后重试!";
			logger.info("【CCAC:查询我的关注执行异常！】");
			throw new Exception(msg);
		}
		mv.addObject("page", page);
		mv.addObject("attention", attention);
		mv.setViewName("system/myzone/attentionList");
		mv.addObject("menuIndex", 5);
		return mv;
	}
	
	/**
	 * 品牌关注
	 */
	@RequestMapping(value="/attentionBrand",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> attentionBrand(String brandID)throws Exception{
		Result<Object> result = new Result<Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		if (Tools.notEmptys(brandID)) {
			pd.put("brandID", brandID.replace(" ", ""));
			pd.put("customerID", Jurisdiction.getCustomerID());
			try {
				int careNum = attentionServiceImpl.addBrandCare(pd);
				result.setState(Result.STATE_SUCCESS);
				result.setResult(careNum);
			} catch (Exception e) {
				e.printStackTrace();
				result.setState(Result.STATE_ERROR);
				result.setMsg("关注失败!请稍后重试！");
				logger.info("【CCAC:品牌关注执行异常！】");
			}
		}else{
			result.setState(Result.STATE_ERROR);
			result.setMsg("关注失败!参数错误！");
			logger.info("【CCAC:品牌关注品牌id参数为空！】");
		}
		
		return result;
	}
	
	/**
	 * 取消品牌关注;
	 */
	@RequestMapping(value="/cancelBrandCare",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> cancelBrandCare(String brandID)throws Exception{
		Result<Object> result = new Result<Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		if (Tools.notEmptys(brandID)) {
			pd.put("brandID", brandID.replace(" ", ""));
			pd.put("customerID", Jurisdiction.getCustomerID());
			try {
				int careNum = attentionServiceImpl.updateBrandCare(pd);
				result.setState(Result.STATE_SUCCESS);
				result.setResult(careNum);
			} catch (CustomException ce) {
				result.setState(Result.STATE_ERROR);
				result.setMsg("取关失败!请稍后重试！");
				logger.info("【CCAC:用户没有关注此品牌,取消失败！】");
			}catch (Exception e) {
				result.setState(Result.STATE_ERROR);
				result.setMsg("关注失败!请稍后重试！");
				logger.info("【CCAC:取消品牌关注执行异常！】");
			}
		}else{
			result.setState(Result.STATE_ERROR);
			result.setMsg("关注失败!参数错误！");
			logger.info("【CCAC: 取消品牌关注品牌id参数为空！】");
		}
		return result;
	}
	
//	/**
//	 * 查询该品牌是否已经被关注;
//	 */
//	@RequestMapping(value="/judgeBrandCare",produces="application/json;charset=UTF-8")
//	@ResponseBody
//	public Object judgeBrandCare(String brandID)throws Exception{
//		Map<String, Object> map = new HashMap<String, Object>();
//		PageData pd = new PageData();
//		pd = this.getPageData();
//		String result = "";
//		if (Tools.notEmptys(brandID)) {
//			try {
//				pd.put("brandID", brandID);
//				result = attentionServiceImpl.getJudgeBrandCared(pd);
//			} catch (Exception e) {
//				e.printStackTrace();
//				result = "error";
//				logger.info("CCAC:查询该品牌是否已经被关注系统异常!");
//			}
//		}else{
//			result = "failed";
//			logger.info("CCAC:参数为空!查询该品牌是否已经被关注参数错误!");
//		}
//		map.put("result", result);
//		return map;
//	}
	
	
	/**
	 * @Title: judgeBrandCare 
	 * @Description: 查询该品牌是否已经被关注;
	 * @param    String brandIDs
	 * @return Result<Boolean[]>.hasAttentions
	 * @author SSY
	 * @date 2018年1月18日 下午6:04:37
	 */
	@RequestMapping(value="/judgeBrandCare",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result<Boolean[]> judgeBrandCare(String brandIDs)throws Exception{
		Result<Boolean[]> result = new Result<Boolean[]>();
		if (!Tools.notEmptys(brandIDs)) {
			result.setState(Result.STATE_ERROR);
			result.setMsg("操作失败!参数错误!");
			return result;
		}
		try {
			Boolean[] hasAttentions = attentionServiceImpl.getJudgeBrandCared(brandIDs);
			result.setState(Result.STATE_SUCCESS);
			result.setResult(hasAttentions);
		} catch (Exception e) {
			e.printStackTrace();
			result.setState(Result.STATE_ERROR);
			result.setMsg("网络异常");
			logger.info("ERROR:  "+DateUtil.getTime()+"查询该品牌是否已经被关注系统异常!");
		}
		return result;
	}
	
	/**
	 * 我的关注---查看更多;品牌搜索;
	 */
	
}
