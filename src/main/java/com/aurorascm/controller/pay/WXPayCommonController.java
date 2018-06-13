package com.aurorascm.controller.pay;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aurorascm.controller.BaseController;
import com.aurorascm.service.myzone.OrderService;
import com.aurorascm.util.AppUtil;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.PageData;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;


/** 微信支付控制器
 * @author BYG 2017-9-9
 * @version 1.0
 */
@Controller
@RequestMapping(value="/wxpay")
public class WXPayCommonController extends BaseController {
	@Resource(name = "orderServiceImpl")
	private OrderService orderServiceImpl;

	@RequestMapping(value="/weixinPage", produces="application/json;charset=UTF-8")
    public ModelAndView goWXPayPage(String orderID) throws Exception {
		ModelAndView mv = this.getModelAndView();
		BigDecimal shouldPay = orderServiceImpl.getShouldPayByOID(orderID);
		mv.addObject("shouldPay",shouldPay);
		mv.addObject("orderID",orderID);
		mv.setViewName("system/pay/wxPay");
		return mv;
    }

	/**微信扫码支付模式二（单订单支付）
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value="/payment", produces="application/json;charset=UTF-8")
	public void wxPayment(HttpServletResponse response, String orderID)throws Exception{
		//获取订单应支付金额，以分为单位。
		String shouldPay = this.getShouldPay(orderID);
		//初始化微信支付客户端
		MyWXPayConfig config = new MyWXPayConfig();
        WXPay wxpay = new WXPay(config);
        //设置请求参数
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "北极光供应链订单");//商品简单描述，该字段请按照规范传递.
        data.put("out_trade_no", orderID);//商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
        data.put("device_info", "WEB");//自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
        data.put("fee_type", "CNY");									//标价币种
        data.put("total_fee", shouldPay);	//实际支付金额
//        data.put("total_fee", "1");										//订单总金额，单位为分。1分作测试
        data.put("limit_pay", "no_credit");			//上传此参数no_credit--可限制用户不能使用信用卡支付
        data.put("spbill_create_ip", config.getIP());	
        data.put("notify_url", "https://www.aurorascm.com/wxpay/notify");	//支付成功异步通知地址
        data.put("trade_type", "NATIVE"); // trade_type=NATIVE时（即扫码支付），此参数必传。
        data.put("product_id", orderID);//此参数为二维码中包含的商品ID，商户自行定义。
        data.put("nonce_str", WXPayUtil.generateNonceStr());//添加生成随机数，主要保证签名不可预测。
        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);//发起支付
            System.out.println(resp);
            String code_url = resp.get("code_url"); //获取二维码URL。code_url有效期为2小时，过期后扫码不能再发起支付
            System.out.println(code_url);
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();//根据url生成二维码
            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();// 设置二维码参数
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = multiFormatWriter.encode(code_url, BarcodeFormat.QR_CODE, 300, 300, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, "jpg", response.getOutputStream());//返回二维码
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/**
     * 微信支付结果回调异步通知（只作第三方处理用，不做任何处理）
     * @return
     * @throws Exception 
     */
	@RequestMapping(value="/notify", produces="application/json;charset=UTF-8")
    public void notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//初始化微信支付客户端
		MyWXPayConfig config = new MyWXPayConfig();
        WXPay wxpay = new WXPay(config);
        // 读取回调内容
        InputStream inputStream;
        StringBuffer sb = new StringBuffer();
        inputStream = request.getInputStream();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();
        // 支付结果通知的xml格式数据
        String notifyData = sb.toString(); 
        // 转换成map
        Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyData);
        logger.info(notifyMap);
        //验证签名
        if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {        // 签名正确
            if("SUCCESS".equals(notifyMap.get("result_code"))) {    //交易成功
                logger.info("订单" + notifyMap.get("out_trade_no") + "微信支付成功");
                //不做订单更新，以查询结果为准
            } else {    //交易失败
            	 logger.info("订单" + notifyMap.get("out_trade_no") + "微信支付失败");
            }
        }else {  // 签名错误，如果数据里没有sign字段，也认为是签名错误
        	logger.info("订单" + notifyMap.get("out_trade_no") + "微信支付签名错误");
        }
    }
	
	/**微信支付订单查询
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value="/query", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object query(String orderID)throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> map = new HashMap<String,Object>();
		String result = "";
		String msg = "";
		try {
			int orderState = orderServiceImpl.getOrderStateByOID(orderID);
	    	if (orderState ==1 ) {
	    		MyWXPayConfig config = new MyWXPayConfig();
	    		WXPay wxpay = new WXPay(config);
	    		Map<String, String> data = new HashMap<String, String>();
	    		data.put("out_trade_no", orderID);
	    		Map<String, String> resp = wxpay.orderQuery(data);
	 		    String tradeState = resp.get("trade_state");//未支付：trade_state=NOTPAY,已支付：trade_state=SUCCESS
	 		    if (tradeState == "SUCCESS" || "SUCCESS".equals(tradeState)) {
	 		    	String cashFee = resp.get("cash_fee");//客户支付金额
	 			    String shouldPay = this.getShouldPay(orderID);//获取订单应付金额
//	 			    String shouldPay = "1";
	 				if (cashFee.equals(shouldPay)) {
	 					PageData pduo = new PageData();
	 					pduo.put("orderID", orderID);
	 					pduo.put("payPath", 2);//付款渠道：1支付宝；2微信；3银联
	 					pduo.put("tradeNo", resp.get("transaction_id"));//微信端支付流水号
	 					pduo.put("payTime", DateUtil.getTime());
	 					pduo.put("orderState", 2);//2已支付
	 					BigDecimal payMoney = new BigDecimal(resp.get("cash_fee"));
	 					BigDecimal hundred = new BigDecimal("100");
	 					payMoney = payMoney.divide(hundred);
	 					DecimalFormat df = new DecimalFormat("0.00"); 
	 					pduo.put("payMoney", df.format(payMoney));
	 					this.updateOrderPayInfo(pduo);//更新商品支付信息，更新商品库存
	 					result = "success";
	 					msg = "支付成功";
	 				} else {
	 					PageData pduo = new PageData();
	 					pduo.put("orderID", orderID);
	 					pduo.put("payPath", 2);//付款渠道：1支付宝；2微信；3银联
	 					pduo.put("tradeNo", resp.get("transaction_id"));//微信端支付流水号
	 					pduo.put("payTime", DateUtil.getTime());
	 					pduo.put("orderState", 11);//11实付金额与应付金额不等值
	 					BigDecimal payMoney = new BigDecimal(resp.get("cash_fee"));
	 					BigDecimal hundred = new BigDecimal("100");
	 					payMoney = payMoney.divide(hundred);
	 					DecimalFormat df = new DecimalFormat("0.00"); 
	 					pduo.put("payMoney", df.format(payMoney));
	 					this.updateOrderPayInfo(pduo);
	 					result = "error";
	 					msg = "实际支付金额不等于订单应付金额";
	 				}
	 			}else if(tradeState == "NOTPAY" || "NOTPAY".equals(tradeState)){
	 				result = "nopay";
	 				msg = "未支付";
	 			}else{
	 				result = "error";
	 				msg = "微信支付端异常";
	 			}
			} else {
				result = "error";
 				msg = "超过1小时订单已自动取消";
			}
		} catch (Exception e) {
			result = "error";
			msg = "系统异常";
		}
		map.put("result", result);
		map.put("msg", msg);
		map.put("pd", pd);
		return AppUtil.returnObject(pd, map);
	}
	
	/**下载对账单
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value="/downloadBill", produces="application/json;charset=UTF-8")
	public void downloadBill(String orderIDs)throws Exception{
		MyWXPayConfig config = new MyWXPayConfig();
		WXPay wxpay = new WXPay(config);
		Map<String, String> data = new HashMap<String, String>();
		data.put("out_trade_no", orderIDs);
		try {
		    Map<String, String> resp = wxpay.orderQuery(data);
		    System.out.println(resp);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	/**获取订单应付金额，转换为以分为单位
	 * @param 
	 * @throws Exception
	 */
	public String getShouldPay(String orderID)throws Exception{
		BigDecimal shouldPayment = orderServiceImpl.getShouldPayByOID(orderID);
		BigDecimal hundred = new BigDecimal("100");
		shouldPayment = shouldPayment.multiply(hundred);
		String shouldPay = String.valueOf(shouldPayment.intValue());//转成整数再转成字符串
		return shouldPay;
	}
	
	/**订单支付成功后更新订单信息
	 * @param 
	 * @throws Exception
	 */
	public String updateOrderPayInfo(PageData pd)throws Exception{
		String result = "";
		try {
			orderServiceImpl.updateOrderPayInfo(pd);
			result = "success";
		} catch (Exception e) {
			result = "error";
		}
		return result;
	}
	
	
}
