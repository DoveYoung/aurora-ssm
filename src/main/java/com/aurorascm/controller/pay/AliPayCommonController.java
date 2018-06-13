package com.aurorascm.controller.pay;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.aurorascm.controller.BaseController;
import com.aurorascm.service.myzone.OrderService;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.PageData;

/** 支付控制器
 * @author BYG 2017-9-9
 * @version 1.0
 */
@Controller
@RequestMapping(value="/alipay")
public class AliPayCommonController extends BaseController {
	@Resource(name = "orderServiceImpl")
	private OrderService orderServiceImpl;

	/**
	 * 跳转到支付宝支付页面
	 * @author BYG
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value="/payment", produces="application/json;charset=UTF-8")
	@ResponseBody
	public void Alipayment(HttpServletRequest request, HttpServletResponse response,
			String orderID)throws Exception{
		BigDecimal shouldPayment = orderServiceImpl.getShouldPayByOID(orderID);
		AlipayClient alipayClient = AlipayConfig.alipayClient;		//获得初始化的AlipayClient
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();		//设置请求参数
		// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		String notify_url = "https://www.aurorascm.com/alipay/notifyUrl";
		// 页面跳转同步通知页面路径 需https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		String return_url = "https://www.aurorascm.com/alipay/returnUrl";
		alipayRequest.setReturnUrl(return_url);		//设置回跳和通知地址
		alipayRequest.setNotifyUrl(notify_url);
		String out_trade_no = orderID;	//商户订单号，商户网站订单系统中唯一订单号，必填
		String total_amount = shouldPayment.toString();		//本次交易支付的订单金额，单位为人民币（元），精确到小数点后2位
//		String total_amount = "0.01";		//本次交易支付的订单金额，单位为人民币（元），精确到小数点后2位
		String subject = "北极光订单";		//订单名称，必填
		String body = "北极光供应链订单商品";			//商品描述，可空
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//支付页面表单
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		response.setContentType("text/html;charset=" + AlipayConfig.charset);
		response.getWriter().write(result);//直接将完整的表单html输出到页面
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**支付宝支付后【同步】通知结果（主要是给客户反馈）
	 * @author BYG
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value="/returnUrl", produces="application/json;charset=UTF-8")
    public ModelAndView returnUrl(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, UnsupportedEncodingException{
        ModelAndView mv = this.getModelAndView();
        String payResult = "";
        //获取支付宝get过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
    	Map<String,String[]> requestParams = request.getParameterMap();
    	for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
    		String name = (String) iter.next();
    		String[] values = (String[]) requestParams.get(name);
    		String valueStr = "";
    		for (int i = 0; i < values.length; i++) {
    			valueStr = (i == values.length - 1) ? valueStr + values[i]
    					: valueStr + values[i] + ",";
    		}
    		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");//乱码解决，这段代码在出现乱码时使用
    		params.put(name, valueStr);
    	}
    	//调用SDK验证签签名
    	boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
    	if(signVerified) {
    		String out_trade_no = request.getParameter("out_trade_no");//商户订单号
    		String trade_no = request.getParameter("trade_no");//支付宝交易号
    		String total_amount = request.getParameter("total_amount");//付款金额（以元为单位）
    		payResult = "1";
        	mv.addObject("out_trade_no",out_trade_no);
    		mv.addObject("trade_no",trade_no);
    		mv.addObject("total_amount",total_amount);
    	}else {
    		payResult = "2";
    	}
    	mv.addObject("payResult",payResult);
		mv.setViewName("system/pay/alipay/returnUrl");
        return mv;
    }
	
	/**支付宝支付后【异步】通知结果（主要是给服务器做业务处理，支付结果以异步通知为准）
	 * @author BYG
	 * @param 
	 * @throws IOException 
	 * @throws Exception
	 */
	@RequestMapping(value="/notifyUrl", produces="application/json;charset=UTF-8")
	@ResponseBody
    public void notifyUrl(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, IOException{
		//ModelAndView mv = this.getModelAndView();
		//String result = "";
		Map<String,String> params = new HashMap<String,String>();
    	Map<String,String[]> requestParams = request.getParameterMap();
    	for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
    		String name = (String) iter.next();
    		String[] values = (String[]) requestParams.get(name);
    		String valueStr = "";
    		for (int i = 0; i < values.length; i++) {
    			valueStr = (i == values.length - 1) ? valueStr + values[i]
    					: valueStr + values[i] + ",";
    		}
    		//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");//乱码解决，这段代码在出现乱码时使用
    		params.put(name, valueStr);
    	}
    	logger.info("np");
    	logger.info(params);
    	//调用SDK验证签签名
    	boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
    	logger.info(signVerified);
    	if(signVerified) {
    		String out_trade_no = request.getParameter("out_trade_no"); //商户订单号(“out_trade_no”)
    		String trade_no = request.getParameter("trade_no");   		//支付宝交易号
    		String total_amount = request.getParameter("total_amount"); //付款金额
    		String trade_status = request.getParameter("trade_status"); //交易状态
    		logger.info("ns");
        	logger.info(out_trade_no);
        	logger.info(trade_status);
    		if(trade_status.equals("TRADE_SUCCESS")){
    			PageData pduo = new PageData();
				pduo.put("orderID", out_trade_no);
				pduo.put("payPath", 1);//付款渠道：1支付宝；2微信；3银联
				pduo.put("tradeNo", trade_no);//支付宝支付流水号
				pduo.put("payTime", DateUtil.getTime());
				pduo.put("orderState", 2);//2已支付
				pduo.put("payMoney", total_amount);
				try {
					orderServiceImpl.updateOrderPayInfo(pduo);//更新商品支付信息，更新商品库存
				} catch (Exception e) {
					String msg = "pay-订单：" + out_trade_no + "支付成功后订单信息更新失败";
					logger.info(msg);
					e.printStackTrace();
				}
				logger.info("success");
				response.getWriter().println("success");
				response.getWriter().flush();
				response.getWriter().close();
    		}else{
    			response.getWriter().println("fail");
    			response.getWriter().flush();
    			response.getWriter().close();
    		}
    	}else {
    		response.getWriter().println("fail");
    		response.getWriter().flush();
    		response.getWriter().close();
    	}
    }

	/**支付宝支付状态--trade_status
	 * WAIT_BUYER_PAY：交易创建，等待买家付款；
	 * TRADE_CLOSED	未付款交易超时关闭，或支付完成后全额退款 
	 * TRADE_SUCCESS	交易支付成功
	 * TRADE_FINISHED	交易结束，不可退款
	 */
		
}
