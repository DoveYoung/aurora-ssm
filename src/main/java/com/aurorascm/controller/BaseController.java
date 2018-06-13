package com.aurorascm.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.aurorascm.entity.Page;
import com.aurorascm.service.SpecialService;
import com.aurorascm.service.StatisticsService;
import com.aurorascm.service.myzone.ContractService;
import com.aurorascm.service.myzone.CustomerService;
import com.aurorascm.service.myzone.InquiryService;
import com.aurorascm.service.shop.home.HomeBondedService;
import com.aurorascm.service.shop.home.HomeFloorService;
import com.aurorascm.service.shop.home.HomeSpecialService;
import com.aurorascm.service.shop.home.LimitedTimeService;
import com.aurorascm.service.supply.intention.SupplyIntentionService;
import com.aurorascm.util.PageData;
import com.aurorascm.util.UuidUtil;

/**
 * 描述:BaseController
 * 创建: BYG 2017/5/24
 * 修改:
 * @version 1.0
 */
public class BaseController {
	@Resource(name = "statisticsServiceImpl")
	protected StatisticsService statisticsServiceImpl;
	@Resource(name="inquiryServiceImpl")
	protected InquiryService inquiryServiceImpl;
	@Resource(name="contractServiceImpl")
	protected ContractService contractServiceImpl;
	
	protected Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected HomeSpecialService homeSpecialServiceImpl;
	@Autowired
	protected LimitedTimeService limitedTimeServiceImpl;
	@Autowired
	protected HomeBondedService homeBondedServiceImpl;
	@Autowired
	protected HomeFloorService homeFloorServiceImpl;
	@Autowired 
	protected SpecialService specialServiceImpl;
	@Autowired
	protected SupplyIntentionService supplyIntentionServiceImpl;
	@Autowired
	protected CustomerService customerServiceImpl;
	/**new PageData对象
	 * @return
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}
	
	/**得到ModelAndView
	 * @return
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**得到request
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**得到32位uuid
	 *@return
	 */
	public String get32UUID(){
		return UuidUtil.get32UUID();
	}
	
	/**得到分页列表信息
	 * @return
	 */
	public Page getPage(){
		return new Page();
	}
	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}
	
}
