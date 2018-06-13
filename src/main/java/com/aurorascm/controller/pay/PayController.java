package com.aurorascm.controller.pay;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aurorascm.controller.BaseController;
import com.aurorascm.service.myzone.OrderService;
import com.aurorascm.util.PageData;


/** 微信支付控制器
 * @author BYG 2017-9-9
 * @version 1.0
 */
@Controller
@RequestMapping(value="/pay")
public class PayController extends BaseController {
	@Resource(name = "orderServiceImpl")
	private OrderService orderServiceImpl;

	/**支付成功后跳转页面
	 * @param orderID
	 * @return
	 */
	@RequestMapping(value="/goPaySuccess", produces="application/json;charset=UTF-8")
    public ModelAndView goPaySuccess(String orderID) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pdm = orderServiceImpl.getOrderMByOID(orderID);
		mv.addObject("pdm",pdm);
		mv.setViewName("system/order/paySuccess");
		return mv;
    }
	
	/**支付失败后跳转页面
	 * @param orderID
	 * @return
	 */
	@RequestMapping(value="/goPayFailed", produces="application/json;charset=UTF-8")
    public ModelAndView goPayFailed(String orderID) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pdm = orderServiceImpl.getOrderMByOID(orderID);
		mv.addObject("pdm",pdm);
		mv.setViewName("system/order/payFailed");
		return mv;
    }
	
}
