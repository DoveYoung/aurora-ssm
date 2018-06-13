package com.aurorascm.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.aurorascm.entity.CartDPMath;
import com.aurorascm.entity.CartFPMath;
import com.aurorascm.entity.CartGoods;
import com.aurorascm.entity.GoodsCommon;
import com.aurorascm.entity.GoodsManage;
import com.aurorascm.entity.Result;
import com.aurorascm.service.ShopCartService;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/**
 * 购物车
 * 
 * @author BYG 2017-7-21
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/cartPage")
public class ShopCartController extends BaseController {

	@Resource(name = "shopCartServiceImpl")
	private ShopCartService shopCartServiceImpl;

	/**
	 * @Title: goCartPage 
	 * @Description: 购物车页面
	 * @param    
	 * @return ModelAndView  
	 * @author SSY
	 * @date 2018年5月22日 下午3:36:36
	 */
	@RequestMapping(value = "/goCartPage", produces = "application/json;charset=UTF-8")
	public ModelAndView goCartPage() throws Exception {
		ModelAndView mv = this.getModelAndView();
		String msg = "";
		Session session = Jurisdiction.getSession();
		String customerID = (String) session.getAttribute("customerID");
		if (customerID != null) {
			try { 
				Map<String, Object> map = shopCartServiceImpl.getCartFPGMap(customerID);
				mv.addObject("hLCartFPMath", JSON.toJSON(map.get("hLCartFPMath")));
				mv.addObject("bLCartFPMath", JSON.toJSON(map.get("bLCartFPMath")));
				mv.addObject("gLCartFPMath", JSON.toJSON(map.get("gLCartFPMath")));
				mv.setViewName("system/shopCar/payFull");
			} catch (Exception e) {
				e.printStackTrace();
				msg = "网络异常，请稍后重试！";
				logger.info("CSCC: 购物车执行异常！");
				throw new Exception(msg);
			}
		} else {
			msg = "登陆超时，请重新登陆！";// failed:用户登陆信息验证参数为空！
			logger.info("CSCC: 用户未登陆,customerID为空！");
		}
		mv.addObject("msg", msg);
		return mv;
	}

	/**
	 * @Title: getCartFPGoods 
	 * @Description:  购物车页面全款
	 * @param    
	 * @return ModelAndView  
	 * @author SSY
	 * @date 2018年5月22日 下午3:37:01
	 */
	@RequestMapping(value = "/getCartFPGoods", produces = "application/json;charset=UTF-8")
	public ModelAndView getCartFPGoods() throws Exception {
		ModelAndView mv = this.getModelAndView();
		String msg = "";
		Session session = Jurisdiction.getSession();
		String customerID = (String) session.getAttribute("customerID");
		if (customerID != null) {
			try {
				Map<String, Object> map = shopCartServiceImpl.getCartFPGMap(customerID);
				mv.addObject("hLCartFPMath", JSON.toJSON(map.get("hLCartFPMath")));
				mv.addObject("bLCartFPMath", JSON.toJSON(map.get("bLCartFPMath")));
				mv.addObject("gLCartFPMath", JSON.toJSON(map.get("gLCartFPMath")));
				mv.setViewName("system/shopCar/payFull");
			} catch (Exception e) {
				msg = "网络异常，请稍后重试！";
				logger.info("CSCC: 系统获取购物车全款商品执行异常！");
				throw new Exception(msg);
			}
		} else {
			msg = "登陆超时，请重新登陆！";// failed:用户登陆信息验证参数为空！
			logger.info("CSCC: 用户customerID为空！");
		}
		mv.addObject("msg", msg);
		return mv;
	}

	/**
	 * @Title: getCartDPGoods 
	 * @Description: 购物车页面定金商品
	 * @param    
	 * @return ModelAndView  
	 * @author SSY
	 * @date 2018年5月22日 下午3:37:38
	 */
	@RequestMapping(value = "/getCartDPGoods", produces = "application/json;charset=UTF-8")
	public ModelAndView getCartDPGoods() throws Exception {
		ModelAndView mv = this.getModelAndView();
		String msg = "";
		Session session = Jurisdiction.getSession();
		String customerID = (String) session.getAttribute("customerID");
		try {
			Map<String, Object> map = shopCartServiceImpl.getCartDFGMap(customerID);
			mv.addObject("hLCartDPMath", JSON.toJSON(map.get("hLCartDPMath")));
			mv.addObject("bLCartDPMath", JSON.toJSON(map.get("bLCartDPMath")));
			mv.addObject("gLCartDPMath", JSON.toJSON(map.get("gLCartDPMath")));
			mv.setViewName("system/shopCar/deposit");
		} catch (Exception e) {
			msg = "网络异常，请稍后重试！";
			logger.info("CSCC: 系统获取购物车页面定金商品执行异常！");
			throw new Exception(msg);
		}
		mv.addObject("msg", msg);
		return mv;
	}

	/**
	 * @Title: addGToCart 
	 * @Description:添加商品到购物车
	 * @param    
	 * @return Result<Object>  result.pd
	 * @author SSY
	 * @date 2018年5月22日 下午3:38:22
	 */
	@RequestMapping(value = "/addGToCart", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> addGToCart() throws Exception {
		Result<Object> result = new Result<Object>();
		PageData pd = this.getPageData();
		String goodsID = Tools.notEmptys(pd.getString("goodsID")) ? pd.getString("goodsID").replace(" ", "") : null;
		String buyNum = Tools.notEmptys(pd.getString("buyNum")) ? pd.getString("buyNum").replace(" ", "") : "1";
		String shipType = Tools.notEmptys(pd.getString("shipType")) ? pd.getString("shipType").replace(" ", "") : "1";
		String paymentType = Tools.notEmptys(pd.getString("paymentType")) ? pd.getString("paymentType").replace(" ", "") : null;
		Session session = Jurisdiction.getSession();
		String customerID = (String) session.getAttribute("customerID");
		if (goodsID != null && paymentType != null) {
			try {
				pd.put("customerID", customerID);
				pd.put("goodsID", goodsID);
				pd.put("buyNum", buyNum);
				pd.put("shipType", shipType);
				pd.put("paymentType", paymentType);
				String msg = shopCartServiceImpl.addShopCard(pd);
				result.setState(Result.STATE_SUCCESS);
				result.setMsg(msg);
			} catch (Exception e) {
				e.printStackTrace();
				result.setState(Result.STATE_ERROR);
				result.setMsg("网络异常,请稍后再试!");
				logger.info("【CSCC:购物车添加商品系统异常！】");
			}
		} else {
			result.setState(Result.STATE_ERROR);
			result.setMsg("参数异常,请稍后再试!");
			logger.info("【CSCC:商品goodsID或商品二级类目category2ID为空！】");
		}
		result.setResult(pd);
		return result;
	}

	/**
	 * @Title: deleteCartUG 
	 * @Description: 删除购物车单个商品result.pd
	 * @param    
	 * @return Result<Object>   result.pd
	 * @author SSY
	 * @date 2018年5月22日 下午2:42:22
	 */
	@RequestMapping(value = "/deleteCartUG", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> deleteCartUG() throws Exception {
		Result<Object> result = new Result<Object>();
		PageData pd = this.getPageData();
		String cartID = Tools.notEmptys(pd.getString("cartID")) ? pd.getString("cartID").replace(" ", "") : null;
		if (cartID != null) {
			try {
				pd.put("cartID", cartID);
				shopCartServiceImpl.deleteCartUG(pd);
				result.setState(Result.STATE_SUCCESS);
			} catch (Exception e) {
				result.setState(Result.STATE_ERROR);
				result.setMsg("网络异常，请稍后重试！");
				logger.info("【CSCC:删除购物车单个商品执行异常！】");
			}
		} else {
			result.setState(Result.STATE_ERROR);
			result.setMsg("网络异常，请稍后重试！");
			logger.info("【CSCC:删除购物车单个商品参数异常！】");
		}
		result.setResult(pd);
		return result;
	}

	/**
	 * @Title: bDeleteCartG 
	 * @Description: 批量删除购物车商品
	 * @param    
	 * @return Object  
	 * @author SSY
	 * @date 2018年5月22日 下午2:42:40
	 */
	@RequestMapping(value = "/bDeleteCartG", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> bDeleteCartG() throws Exception {
		Result<Object> result = new Result<Object>();
		PageData pd = this.getPageData();
		String cartIDs = Tools.notEmptys(pd.getString("cartIDs")) ? pd.getString("cartIDs").replace(" ", "") : null;
		if (cartIDs != null) {
			try {
				String ArrayCartID[] = cartIDs.split(",");
				shopCartServiceImpl.bDeleteCartG(ArrayCartID);
				result.setState(Result.STATE_SUCCESS);
			} catch (Exception e) {
				result.setState(Result.STATE_ERROR);
				result.setMsg("网络异常，请稍后重试！");
				logger.info("【CSCC:批量删除购物车商品执行异常！】");
			}
		} else {
			result.setState(Result.STATE_ERROR);
			result.setMsg("网络异常，请稍后重试！");
			logger.info("【CSCC:批量删除购物车商品参数异常！】");
		}
		result.setResult(pd);
		return result;
	}

	/**
	 * @Title: changeCartFPGN 
	 * @Description:购物车全款页面根据cartID变更单商品数量
	 * @param    
	 * @return   result.cartUFPMath 
	 * @author SSY
	 * @date 2018年5月22日 下午2:54:19
	 */
	@RequestMapping(value = "/changeCartFPGN", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Result<Object> changeCartFPGN(String cartID,String buyNum,String goodsID) throws Exception {
		Result<Object> result = new Result<Object>();
		PageData pd  = this.getPageData();
		if (Tools.notEmptys(cartID)&&Tools.notEmptys(goodsID)) {
			try {
				pd.put("cartID", Tools.notEmptys(cartID) ? cartID.replace(" ", "") : null);
				pd.put("buyNum", Tools.notEmptys(buyNum) ? buyNum.replace(" ", "") : "1");
				shopCartServiceImpl.updateCartGN(pd);
				CartGoods cartUG = shopCartServiceImpl.getCartUG(pd);// 根据cartID获取购物车单个商品信息
				if (cartUG != null) {
					CartFPMath cartUFPMath = new CartFPMath();
					cartUFPMath.setCartID(cartUG.getCartID());
					cartUFPMath.setGoodsID(cartUG.getGoodsID());
					GoodsCommon cartUGC = cartUG.getCartGC();
					cartUFPMath.setGoodsName(cartUGC.getGoodsName());
					cartUFPMath.setMainMap(cartUGC.getMainMap());
					cartUFPMath.setCategory2ID(cartUGC.getCategory2ID());
					cartUFPMath.setCustomerID(cartUG.getCustomerID());
					GoodsManage cartUGM = cartUG.getCartGM();
					cartUFPMath.setGoodsStock(cartUGM.getGoodsStock());
					cartUFPMath.setShipType(cartUGM.getShipType());
					cartUFPMath.setPostageStyle(cartUGM.getPostageStyle());
					cartUFPMath.setBuyNum(cartUG.getBuyNum());
					cartUFPMath.setGoodsPrice1(cartUGM.getGoodsPrice1());
					cartUFPMath.setGoodsPrice2(cartUGM.getGoodsPrice2());
					cartUFPMath.setRnum1(cartUGM.getRnum1());
					cartUFPMath.setRnum2(cartUGM.getRnum2());
					cartUFPMath.setRnum3(cartUGM.getRnum3());
					cartUFPMath.setWeight(cartUGM.getWeight());
					cartUFPMath.setCostPrice(cartUGM.getCostPrice());
					cartUFPMath.setExw(cartUGM.getExw());
					int goodsStock = shopCartServiceImpl.getGoodsStock(goodsID.replace(" ", ""));//查询库存;
					cartUFPMath.setGoodsStock(goodsStock);
					result.setResult(cartUFPMath);
					result.setState(Result.STATE_SUCCESS);
				} else {
					result.setState(Result.STATE_ERROR);
					result.setMsg("网络异常，请稍后重试！");
					logger.info("【CSCC:购物车全款单商品更改数量参数异常！】");
				}
			} catch (Exception e) {
				result.setState(Result.STATE_ERROR);
				result.setMsg("网络异常，请稍后重试！");
				logger.info("【CSCC:购物车全款商品更改数量异常！】");
			}
		}
		return result;
	}
	
	/**
	 * @Title: changeCartDPGN 
	 * @Description:购物车定金页面根据cartID变更单商品数量
	 * @param    
	 * @return    result.cartUDPMath  
	 * @author SSY
	 * @date 2018年5月22日 下午3:38:52
	 */
	@RequestMapping(value = "/changeCartDPGN", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object changeCartDPGN(String cartID,String buyNum,String goodsID) throws Exception {
		Result<Object> result = new Result<Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		 
		Session session = Jurisdiction.getSession();
		String customerID = (String) session.getAttribute("customerID");
		pd.put("cartID", Tools.notEmptys(cartID) ? cartID.replace(" ", "") : null);
		pd.put("buyNum", Tools.notEmptys(buyNum) ? buyNum.replace(" ", "") : "1");
		if (cartID != null) {
			try {
				pd.put("customerID", customerID);
				pd.put("cartID", cartID);
				pd.put("buyNum", buyNum);
				shopCartServiceImpl.updateCartGN(pd);
				CartGoods cartUG = shopCartServiceImpl.getCartUG(pd);// 根据cartID获取购物车单个商品信息
				if (cartUG != null) {
					CartDPMath cartUDPMath = new CartDPMath();
					cartUDPMath.setCartID(cartUG.getCartID());
					cartUDPMath.setGoodsID(cartUG.getGoodsID());
					GoodsCommon cartUGC = cartUG.getCartGC();
					cartUDPMath.setGoodsName(cartUGC.getGoodsName());
					cartUDPMath.setMainMap(cartUGC.getMainMap());
					cartUDPMath.setCategory2ID(cartUGC.getCategory2ID());
					cartUDPMath.setCustomerID(cartUG.getCustomerID());
					GoodsManage cartUGM = cartUG.getCartGM();
					cartUDPMath.setGoodsStock(cartUGM.getGoodsStock());
					cartUDPMath.setShipType(cartUGM.getShipType());
					cartUDPMath.setBuyNum(cartUG.getBuyNum());
					cartUDPMath.setGoodsPrice1(cartUGM.getGoodsPrice1());
					cartUDPMath.setGoodsPrice2(cartUGM.getGoodsPrice2());
					cartUDPMath.setRnum1(cartUGM.getRnum1());
					cartUDPMath.setRnum2(cartUGM.getRnum2());
					cartUDPMath.setRnum3(cartUGM.getRnum3());
					cartUDPMath.setExw(cartUGM.getExw());
					cartUDPMath.setDeposit(cartUGM.getDeposit());
					cartUDPMath.setCostPrice(cartUGM.getCostPrice());
					int goodsStock = shopCartServiceImpl.getGoodsStock(goodsID.replace(" ", ""));//查询库存;
					cartUDPMath.setGoodsStock(goodsStock);
					result.setResult(cartUDPMath);
					result.setState(Result.STATE_SUCCESS);
				} else {
					result.setState(Result.STATE_ERROR);
					result.setMsg("网络异常，请稍后重试！");
					logger.info("【CSCC:购物车定金单商品更改数量参数异常！】");
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setState(Result.STATE_ERROR);
				result.setMsg("网络异常，请稍后重试！");
				logger.info("【CSCC:购物车定金单商品更改数量执行异常！】");
			}
		}
		return result;
	}
	
}