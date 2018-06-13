package com.aurorascm.controller.pay;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aurorascm.controller.BaseController;
import com.aurorascm.pay.kuaiqian.Pkipair;
import com.aurorascm.service.myzone.ContractService;
import com.aurorascm.util.PageData;


/** 快钱人民币网关支付控制器（合同订单）
 * @author BYG 2017-9-9
 * @version 1.0
 */
@Controller
@RequestMapping(value="/kqpayContract")
public class KQPayContractController extends BaseController {
	
	@Resource(name = "contractServiceImpl")
	private ContractService contractServiceImpl;
	
	/**快钱人民币网关支付（单合同支付）
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value="/goKQSend", produces="application/json;charset=UTF-8")
    public ModelAndView goKQSend(String contractID,HttpServletRequest request) throws Exception {//orderID为合同IDcontractID
		ModelAndView mv = this.getModelAndView();
		BigDecimal shouldPay = contractServiceImpl.getContractMoneyByCID(contractID);//以元为单位0.00
		BigDecimal hundred = new BigDecimal("100");
		BigDecimal orderAmount = shouldPay.multiply(hundred);//以分为单位
		request.setAttribute("orderAmount", String.valueOf(orderAmount.intValue()));
//		mv.addObject("orderAmount",String.valueOf(orderAmount.intValue()));
		mv.setViewName("system/pay/kqPay/kqSendContract");
		return mv;
    }
	
	
	/**快钱人民币网关支付（单合同支付）
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value="/goKQReceive", produces="application/json;charset=UTF-8")
    public ModelAndView goKQReceive(HttpServletRequest request) throws Exception {
		logger.info("kuaiqian1");
		ModelAndView mv = this.getModelAndView();
		//人民币网关账号，该账号为11位人民币网关商户编号+01,该值与提交时相同。
		String merchantAcctId = request.getParameter("merchantAcctId");
		//网关版本，固定值：v2.0,该值与提交时相同。
		String version = request.getParameter("version");
		//语言种类，1代表中文显示，2代表英文显示。默认为1,该值与提交时相同。
		String language = request.getParameter("language");
		//签名类型,该值为4，代表PKI加密方式,该值与提交时相同。
		String signType = request.getParameter("signType");
		//支付方式，一般为00，代表所有的支付方式。如果是银行直连商户，该值为10,该值与提交时相同。
		String payType = request.getParameter("payType");
		//银行代码，如果payType为00，该值为空；如果payType为10,该值与提交时相同。
		String bankId = request.getParameter("bankId");
		//商户订单号，该值与提交时相同。
		String orderId = request.getParameter("orderId");
		//订单提交时间，格式：yyyyMMddHHmmss，如：20071117020101,该值与提交时相同。
		String orderTime = request.getParameter("orderTime");
		//订单金额，金额以“分”为单位，商户测试以1分测试即可，切勿以大金额测试,该值与支付时相同。
		String orderAmount = request.getParameter("orderAmount");
		// 快钱交易号，商户每一笔交易都会在快钱生成一个交易号。
		String dealId = request.getParameter("dealId");
		//银行交易号 ，快钱交易在银行支付时对应的交易号，如果不是通过银行卡支付，则为空
		String bankDealId = request.getParameter("bankDealId");
		//快钱交易时间，快钱对交易进行处理的时间,格式：yyyyMMddHHmmss，如：20071117020101
		String dealTime = request.getParameter("dealTime");
		//商户实际支付金额 以分为单位。比方10元，提交时金额应为1000。该金额代表商户快钱账户最终收到的金额。
		String payAmount = request.getParameter("payAmount");
		//费用，快钱收取商户的手续费，单位为分。
		String fee = request.getParameter("fee");
		//扩展字段1，该值与提交时相同。
//		String ext1 = request.getParameter("ext1");
		//扩展字段2，该值与提交时相同。
//		String ext2 = request.getParameter("ext2");
		//处理结果， 10支付成功，11 支付失败，00订单申请成功，01 订单申请失败
		String payResult = request.getParameter("payResult");
		//错误代码 ，请参照《人民币网关接口文档》最后部分的详细解释。
		String errCode = request.getParameter("errCode");
		//签名字符串 
		String signMsg = request.getParameter("signMsg");
		String merchantSignMsgVal = "";
		merchantSignMsgVal = appendParam(merchantSignMsgVal,"merchantAcctId", merchantAcctId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "version",version);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "language",language);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "signType",signType);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "payType",payType);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "bankId",bankId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "orderId",orderId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "orderTime",orderTime);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "orderAmount",orderAmount);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "dealId",dealId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "bankDealId",bankDealId);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "dealTime",dealTime);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "payAmount",payAmount);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "fee", fee);
		//merchantSignMsgVal = appendParam(merchantSignMsgVal, "ext1", ext1);
		//merchantSignMsgVal = appendParam(merchantSignMsgVal, "ext2", ext2);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "payResult",payResult);
		merchantSignMsgVal = appendParam(merchantSignMsgVal, "errCode",errCode);
		logger.info(merchantSignMsgVal);
		Pkipair pki = new Pkipair();
		boolean flag = pki.enCodeByCer(merchantSignMsgVal, signMsg);
		int rtnOK =0;//返回给快钱的支付结果通知接收情况值
	  	String rtnUrl="";
		if(flag){
	  		switch(Integer.parseInt(payResult))
	  		{
	  			case 10:
	  					/*此处商户可以做业务逻辑处理*/
	 					PageData pduo = new PageData();
	 					pduo.put("contractID", orderId);
	 					pduo.put("payPath", 3);//付款渠道：1支付宝；2微信；3快钱
	 					pduo.put("contractState", 2);//已付款
	 					pduo.put("tradeNo", dealId);//块钱交易流水号
	 					BigDecimal payMoney = new BigDecimal(orderAmount);
	 					BigDecimal hundred = new BigDecimal("100");
	 					payMoney = payMoney.divide(hundred);
	 					DecimalFormat df = new DecimalFormat("0.00"); 
	 					pduo.put("payMoney", df.format(payMoney));
	 					contractServiceImpl.updateContractPayInfo(pduo);//更新商品支付信息，更新商品库存
	  					rtnOK=1;
	  					//以下是我们快钱设置的show页面，商户需要自己定义该页面。
	  					rtnUrl="https://www.aurorascm.com/kqpayContract/goKQShow?msg=success";
	  					logger.info("kuaiqian=" + String.valueOf(rtnOK) + rtnUrl );
	  					break;
	  			default:
	  					rtnOK=0;
	  					//以下是我们快钱设置的show页面，商户需要自己定义该页面。
	  					rtnUrl="https://www.aurorascm.com/kqpayContract/goKQShow?msg=false";
	  					logger.info("kuaiqian=" + String.valueOf(rtnOK) + rtnUrl );
	  					break;
	  		}
	  	}else{
	  		rtnOK=0;
	  		//以下是我们快钱设置的show页面，商户需要自己定义该页面。
	  		rtnUrl="https://www.aurorascm.com/kqpayContract/goKQShow?msg=error";
	  		logger.info("kuaiqian=" + String.valueOf(rtnOK) + rtnUrl );
	  	}
		mv.addObject("rtnOK", rtnOK);
		mv.addObject("rtnUrl", rtnUrl);
		mv.setViewName("system/pay/kqPay/kqReceive");
		return mv;
    }
	
	/**快钱人民币网关支付（单合同支付成功后显示页面）
	 * @param 
	 * @throws Exception
	 */
	@RequestMapping(value="/goKQShow", produces="application/json;charset=UTF-8")
    public ModelAndView goKQShow(HttpServletRequest request, String msg) throws Exception {
		logger.info("kuaiqian=goKQShow");
		ModelAndView mv = this.getModelAndView();
		String orderId= request.getParameter("orderId").replace(" ", "");
		String orderAmount1=request.getParameter("orderAmount").replace(" ", "");
		BigDecimal orderAmount2 = new BigDecimal(orderAmount1);
		BigDecimal hundred = new BigDecimal("100");
		BigDecimal orderAmount = orderAmount2.divide(hundred);
		String dealId=request.getParameter("dealId").replace(" ", "");
		String orderTime=request.getParameter("orderTime").replace(" ", "");
		String payResult=request.getParameter("payResult").replace(" ", "");
		mv.addObject("orderId",orderId);
		mv.addObject("orderAmount",orderAmount);
		mv.addObject("dealId",dealId);
		mv.addObject("orderTime",orderTime);
		mv.addObject("payResult",payResult);
		mv.addObject("msg",msg);
		logger.info("kuaiqian=goKQShow" + orderId + orderTime + msg);
		logger.info(mv);
		mv.setViewName("system/pay/kqPay/kqShow");
		return mv;
    }
	
	public String appendParam(String returns, String paramId, String paramValue) {
		if (!returns.equals("")) {

			if (!paramValue.equals("")) {
				returns += "&" + paramId + "=" + paramValue;
			}

		} else {

			if (!paramValue.equals("")) {
				returns = paramId + "=" + paramValue;
			}
		}
		return returns;
	}
	
}
