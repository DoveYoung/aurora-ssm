package com.aurorascm.controller.shop.home;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.aurorascm.controller.BaseController;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.PageData;

/**
 * @Title: SpecialController.java 
 * @Package com.aurorascm.controller.shop.home 
 * @Description: 专题活动页
 * @author SSY  
 * @date 2018年5月15日 上午10:10:39 
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/special")
public class SpecialController extends BaseController {

	
	
	/**
	 * @Title: getSpecialGoods 
	 * @Description: 专题活动页面
	 * @param     Integer templateID(模板编号), String specialID(专题id)
	 * @return specialBackground  ,specialGoods
	 * @author SSY
	 * @date 2018年5月15日 下午6:11:32
	 */
	@RequestMapping(value = "/product")
	public String getSpecialGoods(ModelMap map, Integer templateID, Integer specialID) throws Exception{
		try {
			List<List<PageData>> specialGoods = specialServiceImpl.getSpecialGoods(templateID,specialID);// 根据专题模块module,specialID查找该专题下的商品
			//查询专题背景图;
			String specialBackground = specialServiceImpl.getSpecialBackground(specialID);
			map.put("specialGoods", JSON.toJSON(specialGoods));
			map.put("specialBackground", JSON.toJSON(specialBackground));
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("【ERROR】"+DateUtil.getTime()+"专题页商品查询执行异常!");
			throw new Exception();
		}
		switch (templateID) {
		case 1:
			return "system/special/special01";
		case 2:
			return "system/special/special02";
		case 3:
			return "system/special/special03";
		case 4:
			return "system/special/special04";
		default:
			return "system/special/special01";
		}
	}
	
	
//	
//	/**
//	 * 专题活动页面
//	 * 
//	 * @return
//	 * @throws Exception
//	 * @param
//	 */
//	@RequestMapping(value = "/getSpecialGoods")
//	public ModelAndView getSpecialGoods(String specialID) throws Exception {
//		ModelAndView mv = this.getModelAndView();
//		specialID = Tools.notEmptys(specialID) ? specialID.replace(" ", "") : "1";
//		String msg = "";
//		try {
//			List<List<PageData>> specialGoods = specialServiceImpl.getSpecialGoods(specialID);// 根据specialID查找该专题下的商品
//			mv.addObject("specialGoods", specialGoods);
//		} catch (Exception e) {
//			e.printStackTrace();
//			msg = "网络异常,请稍后重试!";
//			throw new Exception(msg);
//		}
//		switch (specialID) {
//		case "1":
//			mv.setViewName("system/special/special01");
//			break;
//		case "2":
//			mv.setViewName("system/special/special02");
//			break;
//		case "3":
//			mv.setViewName("system/special/special03");
//			break;
//		case "4":
//			mv.setViewName("system/special/special04");
//			break;
//		default:
//			mv.setViewName("system/special/special01");
//			break;
//		}
//		return mv;
//	}
//	
//	
	
}
