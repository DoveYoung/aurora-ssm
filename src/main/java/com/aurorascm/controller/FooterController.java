package com.aurorascm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/** 搜索页搜索
 * @author BYG 2017-7-21
 * @version 1.0
 */
@Controller
@RequestMapping(value="/footer")
public class FooterController extends BaseController { 
	
	/**页脚--关于我们
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/aboutUs")
	public ModelAndView aboutUs()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/footer/aboutUs");	
		mv.addObject("menuIndex", 8);
		return mv;
	}
	
	/**页脚--法律服务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/legalService")
	public ModelAndView legalService()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/footer/legalService");	
		mv.addObject("menuIndex", 7);
		return mv;
	}
	
	/**页脚--联系我们
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/contactUs")
	public ModelAndView contactUs()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/footer/contactUs");
		mv.addObject("menuIndex", 9);
		return mv;
	}
	
	/**页脚--跨境物流服务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/crossBorder")
	public ModelAndView crossBorder()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/footer/crossBorder");
		mv.addObject("menuIndex", 5);	
		return mv;
	}
	/**页脚--定金模式
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/depositMode")
	public ModelAndView depositMode()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/footer/depositMode");
		mv.addObject("menuIndex", 1);	
		return mv;
	}
	
//	/**页脚--关于我们
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value="/footer")
//	public ModelAndView footer()throws Exception{
//		ModelAndView mv = this.getModelAndView();
//		mv.setViewName("system/footer/footer");	
//		return mv;
//	}
	
	/**页脚--询价
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/inquiry")
	public ModelAndView inquiry()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/footer/inquiry");
		mv.addObject("menuIndex", 2);	
		return mv;
	}
	
	/**页脚--微仓
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/microWarehouse")
	public ModelAndView microWarehouse()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/footer/microWarehouse");	
		mv.addObject("menuIndex", 4);
		return mv;
	}
	
	/**页脚--退款须知
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/noticeOfReturn")
	public ModelAndView noticeOfReturn()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/footer/noticeOfReturn");	
		mv.addObject("menuIndex", 6);
		return mv;
	}
	
	/**页脚--采购合同
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/procurementContract")
	public ModelAndView procurementContract()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/footer/procurementContract");
		mv.addObject("menuIndex", 3);	
		return mv;
	}
	

	/**页脚--采购合同
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/userAgreement")
	public ModelAndView userAgreement()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/footer/userAgreement");
		mv.addObject("menuIndex", 10);	
		return mv;
	}
	
	
	
}
