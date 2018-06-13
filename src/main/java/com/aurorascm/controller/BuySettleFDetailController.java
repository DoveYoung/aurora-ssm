package com.aurorascm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aurorascm.service.BuySettleFDetailService;
import com.aurorascm.util.AppUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.MyDataException;
import com.aurorascm.util.MyParamException;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/** 详情页发起购买结算支付
 * @author BYG 2017-7-21
 * @version 1.0
 */
@Controller
@RequestMapping(value="/buySettleFLB")
public class BuySettleFDetailController extends BaseController {
	
	@Resource(name="buySettleFDetailServiceImpl")
	private BuySettleFDetailService buySettleFDetailServiceImpl;
	
	/**详情页立即购买全款结算
	 * @param
	 * @return
	 * @throws Exception
	 * 修改：参数province变成saID
	 */
	@RequestMapping(value="/goFSettleFLB", produces="application/json;charset=UTF-8")
	public ModelAndView goFSettleFLB()throws Exception{
		ModelAndView mv = this.getModelAndView();
		String msg = "";
		PageData pd = new PageData();
		pd = this.getPageData();
		String fGoodsID = Tools.notEmptys(pd.getString("fGoodsID")) ? pd.getString("fGoodsID").replace(" ", "") : null;
		String fBuyNum = Tools.notEmptys(pd.getString("fBuyNum"))  ? pd.getString("fBuyNum").replace(" ", "") : "1";//实现类再判断，最小值为rnum1.
		String saID = Tools.notEmptys(pd.getString("saID"))   ? pd.getString("saID").replace(" ", "") : null;	//邮寄地址ID		
		if(fGoodsID != null){
			Session session = Jurisdiction.getSession();
			String customerID =  (String)session.getAttribute("customerID");
			if(Tools.notEmptys(customerID)){
				try{
					Map<String, Object> map = buySettleFDetailServiceImpl.getFSettleFLB(customerID,fGoodsID,fBuyNum,saID);
					mv.addObject("buyNum",map.get("num"));
					mv.addObject("goodsPrice",map.get("goodsPrice"));
					mv.addObject("payment",map.get("payment"));
					mv.addObject("tPayment",map.get("tPayment"));
					mv.addObject("postage",map.get("postage"));
					mv.addObject("wc1Address", map.get("wc1Address"));
					mv.addObject("shipAddress5", map.get("shipAddress5"));
					mv.addObject("newShipAddr", map.get("newShipAddr"));
					mv.addObject("gManage",map.get("gManage"));
					mv.addObject("sType", "fsflb");
					mv.addObject("pd", pd);
					mv.setViewName("system/order/confirmBuyNow");
				}catch(MyParamException e){
	 				msg = "网络异常，请稍后重试或联系客服！";
	 				logger.info("CBSFDC:"+msg);
					throw new MyParamException(msg);
				}catch(Exception e){
					e.printStackTrace();
					msg = "网络异常，请稍后重试或联系客服！";
					logger.info("CBSFDC:"+msg);
	 				throw new Exception(msg);
				}
			}else{
				msg = "网络异常，请稍后重试或联系客服！";
				logger.info("CBSFDC:"+msg);
 				throw new Exception(msg);
			}
		}else{
			msg = "网络异常，请稍后重试或联系客服！";
			logger.info("CBSFDC:"+msg);
			throw new Exception(msg);
		}
		return mv;
	}
	
	/**详情页立即购买全款结算画面——地址变更结算信息异步更新
	 * @param
	 * @return
	 * @throws Exception
	 * 修改：参数province变成saID
	 */
	@RequestMapping(value="/getFSettleInfoFLB", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getFSettleInfoFLB()throws Exception{
		Map<String, Object> rMap = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		String fGoodsID = Tools.notEmptys(pd.getString("fGoodsID")) ? pd.getString("fGoodsID").replace(" ", "") : null;
		String fBuyNum = Tools.notEmptys(pd.getString("fBuyNum"))  ? pd.getString("fBuyNum").replace(" ", "") : "1";//实现类再判断，最小值为rnum1.
		String saID = Tools.notEmptys(pd.getString("saID"))   ? pd.getString("saID").replace(" ", "") : null;	//邮寄地址ID		
		if(fGoodsID != null){
			Session session = Jurisdiction.getSession();
			String customerID =  (String)session.getAttribute("customerID");
			if(Tools.notEmptys(customerID)){
				try{
					Map<String, Object> map = buySettleFDetailServiceImpl.getFSettleFLB(customerID,fGoodsID,fBuyNum,saID);
					rMap.put("buyNum",map.get("num"));
					rMap.put("goodsPrice",map.get("goodsPrice"));
					rMap.put("payment",map.get("payment"));
					rMap.put("tPayment",map.get("tPayment"));
					rMap.put("postage",map.get("postage"));
					rMap.put("wc1Address", map.get("wc1Address"));
					rMap.put("shipAddress5", map.get("shipAddress5"));
					rMap.put("newShipAddr", map.get("newShipAddr"));
					rMap.put("gManage",map.get("gManage"));
					rMap.put("sType", "fsflb");
					rMap.put("pd", pd);
					result = "success";
				}catch(MyParamException e){
	 				msg = "网络异常，请稍后重试或联系客服！";
	 				result = "failed";
	 				logger.info("CBSFDC:"+msg);
					throw new MyParamException(msg);
				}catch(Exception e){
					msg = "网络异常，请稍后重试或联系客服！";
					result = "error";
					logger.info("CBSFDC:"+msg);
	 				throw new Exception(msg);
				}
			}else{
				msg = "登陆超时，请重新登陆！";
				result = "failed";
				logger.info("CBSFDC:"+msg);
 				throw new Exception(msg);
			}
		}else{
			msg = "网络异常，请稍后重试或联系客服！";
			result = "failed";
			logger.info("CBSFDC:"+msg);
			throw new Exception(msg);
		}
		rMap.put("result", result);
		rMap.put("msg", msg);
		rMap.put("pd", pd);
		return AppUtil.returnObject(pd, rMap);
	}
	
	/**情页立即购买【全款】结算页面提交订单--ajax预请求判断购买金额是否超额+购买数量验证
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/checkAmountF", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object checkAmountF()throws Exception{
		Map<String, Object> rMap = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		Session session = Jurisdiction.getSession();
		String customerID =  (String)session.getAttribute("customerID");
		String goodsID = Tools.notEmptys(pd.getString("goodsID")) ? pd.getString("goodsID").replace(" ", "") : null;
		String buyNum = Tools.notEmptys(pd.getString("buyNum"))  ? pd.getString("buyNum").replace(" ", "") : "1";//实现类再判断，最小值为rnum1.
		String saID = Tools.notEmptys(pd.getString("saID"))   ? pd.getString("saID").replace(" ", "") : null;	//邮寄地址ID		
		if (Tools.notEmptys(customerID)) {
			if(goodsID != null){
				try{
					Map<String, String> map = buySettleFDetailServiceImpl.checkAmountF(goodsID,buyNum,saID);
					msg = map.get("msg");
					result = map.get("result");
				}catch(Exception e){
					result = "failed";
					msg="网络异常，请稍后重试或联系客服！";
					logger.info("CBSFDC:"+msg);
				}
			}else{
				result = "failed";
				msg = "网络异常，请稍后重试或联系客服！";
				logger.info("CBSFDC:"+msg);
			}
		}else{
			result = "failed";
			msg = "登陆超时，请重新登陆！";
			logger.info("CBSFDC:"+msg);
			throw new Exception(msg);
		}
		rMap.put("result", result);
		rMap.put("msg", msg);
		return AppUtil.returnObject(pd, rMap);
	}
	
	
	/**详情页立即购买全款提交订单
	 * @param
	 * @return
	 * @throws Exception
	 * 修改：去掉参数province,增加参数customerRemark
	 */
	@RequestMapping(value="/goFPayFLB", produces="application/json;charset=UTF-8")
	public ModelAndView goFPayFLB()throws Exception{
		ModelAndView mv = this.getModelAndView();
		String msg = "";
		PageData pd = new PageData();
		pd = this.getPageData();
		String goodsID = Tools.notEmptys(pd.getString("goodsID")) ? pd.getString("goodsID").replace(" ", "") : null;
		String buyNum = Tools.notEmptys(pd.getString("buyNum"))  ? pd.getString("buyNum").replace(" ", "") : "1";//实现类再判断，最小值为rnum1.
		String saID = Tools.notEmptys(pd.getString("saID"))   ? pd.getString("saID").replace(" ", "") : null;	//邮寄地址ID		
		String customerRemark = Tools.notEmptys(pd.getString("customerRemark")) ? pd.getString("customerRemark").replace(" ", "") : null;
		if(goodsID != null){
			Session session = Jurisdiction.getSession();
			String customerID =  (String)session.getAttribute("customerID");
			if(Tools.notEmptys(customerID)){
				try{
					Map<String, Object> map = buySettleFDetailServiceImpl.addOrderFLBFSettle(customerID,goodsID,buyNum,saID,customerRemark);
					msg = (String) map.get("msg");
					mv.addObject("buyNum",map.get("num"));
					mv.addObject("orderID",map.get("orderID"));
					mv.addObject("goodsPrice",map.get("goodsPrice"));
					mv.addObject("payment",map.get("payment"));
					mv.addObject("tPayment",map.get("tPayment"));
					mv.addObject("postage",map.get("postage"));
					mv.addObject("shipAddr", map.get("shipAddr"));
					mv.addObject("gManage",map.get("gManage"));
					mv.addObject("pType", "fpflb");
					mv.addObject("pd", pd);
					mv.setViewName("system/order/payway");
				}catch(MyParamException e){
	 				msg = "网络异常，请稍后重试或联系客服！";
	 				logger.info("CBSFDC:"+msg);
					throw new MyParamException(msg);
				}catch(Exception e){
					msg = " 网络异常，请稍后重试或联系客服！";
					logger.info("CBSFDC:"+msg);
					throw new MyParamException(msg);
				}
			}else{
				msg = "登陆超时，请重新登陆！";
				logger.info("CBSFDC:【登陆超时，请重新登陆！】");
				throw new Exception(msg);
			}
		}else{
			msg = "参数错误，请稍后重试！";
			logger.info("CBSFDC:参数错误，请稍后重试！");
			throw new Exception(msg);
		}
		mv.addObject("msg",msg);
		return mv;
	}
	
	/**详情页立即购买【定金】结算
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goDSettleFLB", produces="application/json;charset=UTF-8")
	public ModelAndView goDSettleFLB()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String msg="";
		String dGoodsID = Tools.notEmptys(pd.getString("dGoodsID")) ? pd.getString("dGoodsID").replace(" ", "") : null;
		String dBuyNum = Tools.notEmptys(pd.getString("dBuyNum"))? pd.getString("dBuyNum").replace(" ", "") : "1";
		if(dGoodsID != null){
			Session session = Jurisdiction.getSession();
			String customerID =  (String)session.getAttribute("customerID");
			if(Tools.notEmptys(customerID)){
				try{
					Map<String, Object> map = buySettleFDetailServiceImpl.getDSettleFLB(customerID, dGoodsID, dBuyNum);
					mv.addObject("buyNum",map.get("num"));
					mv.addObject("wc1Address",map.get("wc1Address"));
					mv.addObject("goodsPrice",map.get("goodsPrice"));
					mv.addObject("tPayment",map.get("tPayment"));//全价金额
					mv.addObject("dPayment",map.get("dPayment"));//定金金额
					mv.addObject("gManage",map.get("gManage"));
					mv.addObject("sType", "dsflb");
					mv.addObject("pd", pd);
					mv.setViewName("system/order/confirmBuyNow");
				}catch(MyDataException e){
					msg = "网络异常，请稍后重试或联系客服！";
					logger.info("CBSFDC:"+msg);
	 				throw new MyDataException(msg);
				}catch(Exception e){
					msg = "网络异常，请稍后重试或联系客服！";
					logger.info("CBSFDC:"+msg);
	 				throw new Exception(msg);
				}
			}else{
				msg = "登陆超时，请重新登陆！";
				logger.info("CBSFDC:"+msg);
				throw new Exception(msg);
			}
		}else{
			msg = "参数错误，请稍后重试或联系客服！";
			logger.info("CBSFDC:"+msg);
			throw new Exception(msg);
		}	
		return mv;
	}
	
	/**
	 * 详情页立即购买【定金】结算页面提交订单--ajax预请求购买数量验证
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/checkAmountD", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object checkAmountD()throws Exception{
		Map<String, Object> rMap = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		Session session = Jurisdiction.getSession();
		String customerID =  (String)session.getAttribute("customerID");
		String goodsID = Tools.notEmptys(pd.getString("goodsID")) ? pd.getString("goodsID").replace(" ", "") : null;
		String buyNum = Tools.notEmptys(pd.getString("buyNum"))  ? pd.getString("buyNum").replace(" ", "") : "1";//实现类再判断，最小值为rnum1.
		if (Tools.notEmptys(customerID)) {
			if(goodsID != null){
				try{
					Map<String, String> map = buySettleFDetailServiceImpl.checkAmountD(goodsID,buyNum);
					msg = map.get("msg");
					result = map.get("result");
				}catch(Exception e){
					result = "failed";
					msg="网络异常，请稍后重试或联系客服！";
					logger.info("CBSFDC:详情页购买预提交，系统执行异常！");
				}
			}else{
				result = "failed";
				msg = "参数错误，请稍后重试或联系客服！";
				logger.info("CBSFDC:详情页购买预提交，参数错误！");
			}
		}else{
			result = "failed";
			msg = "参数错误，请稍后重试或联系客服！";
			logger.info("CBSFDC:详情页购买预提交，参数错误！");
		}
		rMap.put("result", result);
		rMap.put("msg", msg);
		return AppUtil.returnObject(pd, rMap);
	}
	
	/**详情页立即购买【定金】结算页面提交订单
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goDPayFLB", produces="application/json;charset=UTF-8")
	public ModelAndView goDPayFLB()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String msg="";
		Session session = Jurisdiction.getSession();
		String customerID =  (String)session.getAttribute("customerID");
		String customerName = (String)session.getAttribute("customerName");
		String goodsID = Tools.notEmptys(pd.getString("goodsID")) ? pd.getString("goodsID").replace(" ", "") : null;
		String buyNum = Tools.notEmptys(pd.getString("buyNum")) ? pd.getString("buyNum").replace(" ", "") : "1";//实现类再具体判断处理。
		String customerRemark = Tools.notEmptys(pd.getString("customerRemark")) ? pd.getString("customerRemark").replace(" ", "") : null;
		if(goodsID != null){
			try{
				Map<String, Object> map = buySettleFDetailServiceImpl.addOrderFLBDSettle(customerID,customerName,goodsID,buyNum,customerRemark);
				mv.addObject("buyNum",map.get("num"));
				mv.addObject("orderID",map.get("orderID"));
				mv.addObject("wc1Address",map.get("wc1Address"));
				mv.addObject("goodsPrice",map.get("goodsPrice"));
				mv.addObject("dPayment",map.get("dPayment"));
				mv.addObject("tPayment",map.get("tPayment"));
				mv.addObject("gManage",map.get("gManage"));
				mv.addObject("pType", "dfflb");
				mv.addObject("pd", pd);
				mv.setViewName("system/order/payway");
			}catch(MyDataException e){
				msg = "网络异常，请稍后重试或联系客服！";
				logger.info("CBSFDC:网络异常，请稍后重试或联系客服！");
 				throw new MyDataException(msg);
			}catch(Exception e){
				msg = "网络异常，请稍后重试或联系客服！";
				logger.info("CBSFDC:定金立刻购买订单提交执行异常！");
 				throw new Exception(msg);
			}
		}else{
			msg = "参数错误，请稍后重试或联系客服！";
			logger.info("【CBSFDC:定金立刻购买订单提交商品ID参数获取失败!】");
			throw new Exception(msg);
		}		
		mv.addObject("msg",msg);
		return mv;
	}
						
}
