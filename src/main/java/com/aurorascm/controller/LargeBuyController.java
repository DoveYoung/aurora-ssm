package com.aurorascm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aurorascm.entity.Page;
import com.aurorascm.service.HomePageService;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/**
 * 描述:大额采购页面
 * 创建:SSY 2017-10-27
 * 修改:
 * @version 1.0
 */
@Controller
@RequestMapping(value="/largeBuyPage")
public class LargeBuyController extends BaseController { 

	@Resource(name="homePageServiceImpl")
	private HomePageService homePageServiceImpl;
	
	
	/**
	 * 大额采购页面
	 * @return
	 * @throws Exception
	 * @param page  category1ID, brandID
	 */
	@RequestMapping(value = "/largeBuyList")
	public ModelAndView getSpecialGoods(Page page,String category1ID,String brandID) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		String msg = "";
		pd.put("category1ID", Tools.notEmptys(category1ID)?category1ID.replace(" ", ""):null);
		pd.put("brandID", Tools.notEmptys(brandID)?brandID.replace(" ", ""):null);
		page.setPd(pd);
		page.setPageSize(15);
		try {
			int totalRecord = homePageServiceImpl.getLargeBuyNum(page);//条件查询大额采购商品数量
			page.setTotalRecord(totalRecord);
			List<PageData> largeBuyGoods = homePageServiceImpl.getLargeBuyList(page);//  
			mv.addObject("largeBuyGoods", largeBuyGoods);
			List<PageData> brandLargeBuy = homePageServiceImpl.getBrandLargeBuy(page);//
			mv.addObject("brandLargeBuy", brandLargeBuy);
			List<PageData> category1 = homePageServiceImpl.getCategory1();
			mv.addObject("category1", category1);
			mv.setViewName("system/largeBuy/largeBuyMore");
		} catch (Exception e) {
			e.printStackTrace();
			msg = "网络异常,请稍后再试!";
			logger.info("【CLBC:大额采购页面跳转失败,系统异常!】");
			throw new Exception(msg);
		}
		mv.addObject("pd", pd);
		return mv;
	}
}
	 