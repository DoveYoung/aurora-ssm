package com.aurorascm.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.CartDPMath;
import com.aurorascm.entity.CartFPMath;
import com.aurorascm.entity.CartGoods;
import com.aurorascm.entity.GoodsCommon;
import com.aurorascm.entity.GoodsManage;
import com.aurorascm.entity.OrderGoods;
import com.aurorascm.entity.OrderManage;
import com.aurorascm.entity.Result;
import com.aurorascm.service.ShopCartService;
import com.aurorascm.util.Const;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/**
 * 描述:客户登录注册ServiceImpl
 * 创建:BYG 2017/5/25
 * 修改:
 * @version 1.0
 */
@Service("shopCartServiceImpl")
public class ShopCartServiceImpl implements ShopCartService{
	
	@Resource(name = "daoSupport")
	private DAO daoSupport;

	/**新增商品到购物车
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public void addGToCart(PageData pd) throws Exception {
		pd.put("time", DateUtil.getTime());
		daoSupport.save("ShopCartWriteMapper.addGToCart", pd);
	}

	/**根据用户ID/贸易方式/支付方式获取购物车中某贸易方式下的商品
	 * @param pd  String customerID, int paymentType, int shipType
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CartGoods> getCartGoods(PageData pd) throws Exception {
		return (List<CartGoods>)daoSupport.findForList("ShopCartReadMapper.getCartGoods", pd);
	}

	/**获取购物车页面全款商品数据
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String,Object> getCartFPGMap(String customerID)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		//海外直邮
		pd.put("customerID", customerID);
		pd.put("paymentType", 1);
		pd.put("shipType", 2);
		List<CartGoods> hCartGoods = getCartGoods(pd);
		List<CartFPMath> hLCartFPMath = new ArrayList<>();
		if (!hCartGoods.isEmpty()){
			for(CartGoods hcg:hCartGoods){
				CartFPMath hCartFPMath = new CartFPMath();
				hCartFPMath.setCartID(hcg.getCartID());
				hCartFPMath.setGoodsID(hcg.getGoodsID());
				GoodsCommon hCartGC = hcg.getCartGC();
				hCartFPMath.setGoodsName(hCartGC.getGoodsName());
				hCartFPMath.setMainMap(hCartGC.getMainMap());
				hCartFPMath.setCategory2ID(hCartGC.getCategory2ID());
				hCartFPMath.setCustomerID(hcg.getCustomerID());
				GoodsManage hCartGM = hcg.getCartGM();
				hCartFPMath.setGoodsStock(hCartGM.getGoodsStock());
				hCartFPMath.setGoodsState(hCartGM.getGoodsState());
				hCartFPMath.setShipType(hCartGM.getShipType());
				hCartFPMath.setPostageStyle(hCartGM.getPostageStyle());
				hCartFPMath.setBuyNum(hcg.getBuyNum());
				hCartFPMath.setGoodsPrice1(hCartGM.getGoodsPrice1());
				hCartFPMath.setGoodsPrice2(hCartGM.getGoodsPrice2());
				hCartFPMath.setRnum1(hCartGM.getRnum1());
				hCartFPMath.setRnum2(hCartGM.getRnum2());
				hCartFPMath.setRnum3(hCartGM.getRnum3());
				hCartFPMath.setExw(hCartGM.getExw());
				hCartFPMath.setWeight(hcg.getCartGM().getWeight());
				hCartFPMath.setCostPrice(hCartGM.getCostPrice());
				hLCartFPMath.add(hCartFPMath);
			}
		}
		//保税仓直邮
		pd.put("shipType", 1);
		List<CartGoods> bCartGoods = getCartGoods(pd);
		List<CartFPMath> bLCartFPMath = new ArrayList<>();
		if (!bCartGoods.isEmpty()) {
			for(CartGoods bcg:bCartGoods){
				CartFPMath bCartFPMath = new CartFPMath();
				bCartFPMath.setCartID(bcg.getCartID());
				bCartFPMath.setGoodsID(bcg.getGoodsID());
				GoodsCommon bCartGC = bcg.getCartGC();
				bCartFPMath.setGoodsName(bCartGC.getGoodsName());
				bCartFPMath.setMainMap(bCartGC.getMainMap());
				bCartFPMath.setCategory2ID(bCartGC.getCategory2ID());
				bCartFPMath.setCustomerID(bcg.getCustomerID());
				GoodsManage bCartGM = bcg.getCartGM();
				int goodsStock = bCartGM.getGoodsStock();
				bCartFPMath.setGoodsStock(goodsStock);
				bCartFPMath.setGoodsState(bcg.getCartGM().getGoodsState());
				bCartFPMath.setShipType(bCartGM.getShipType());
				bCartFPMath.setPostageStyle(bCartGM.getPostageStyle());
				bCartFPMath.setBuyNum(bcg.getBuyNum());
				bCartFPMath.setGoodsPrice1(bCartGM.getGoodsPrice1());
				bCartFPMath.setGoodsPrice2(bCartGM.getGoodsPrice2());
				bCartFPMath.setRnum1(bCartGM.getRnum1());
				bCartFPMath.setRnum2(bCartGM.getRnum2());
				bCartFPMath.setRnum3(bCartGM.getRnum3());
				bCartFPMath.setExw(bCartGM.getExw());
				bCartFPMath.setWeight(bCartGM.getWeight());
				bCartFPMath.setCostPrice(bCartGM.getCostPrice());
				bLCartFPMath.add(bCartFPMath);
			}
		}
		//国内现货
		pd.put("shipType", 3);
		List<CartGoods> gCartGoods = getCartGoods(pd);
		List<CartFPMath> gLCartFPMath = new ArrayList<>();
		if (!gCartGoods.isEmpty()) {
			for(CartGoods gcg:gCartGoods){
				CartFPMath gCartFPMath = new CartFPMath();
				gCartFPMath.setCartID(gcg.getCartID());
				gCartFPMath.setGoodsID(gcg.getGoodsID());
				GoodsCommon gCartGC = gcg.getCartGC();
				gCartFPMath.setGoodsName(gCartGC.getGoodsName());
				gCartFPMath.setMainMap(gCartGC.getMainMap());
				gCartFPMath.setCategory2ID(gCartGC.getCategory2ID());
				gCartFPMath.setCustomerID(gcg.getCustomerID());
				GoodsManage gCartGM = gcg.getCartGM();
				gCartFPMath.setGoodsStock(gCartGM.getGoodsStock());
				gCartFPMath.setGoodsState(gCartGM.getGoodsState());
				gCartFPMath.setShipType(gCartGM.getShipType());
				gCartFPMath.setPostageStyle(gCartGM.getPostageStyle());
				gCartFPMath.setBuyNum(gcg.getBuyNum());
				gCartFPMath.setGoodsPrice1(gCartGM.getGoodsPrice1());
				gCartFPMath.setGoodsPrice2(gCartGM.getGoodsPrice2());
				gCartFPMath.setRnum1(gCartGM.getRnum1());
				gCartFPMath.setRnum2(gCartGM.getRnum2());
				gCartFPMath.setRnum3(gCartGM.getRnum3());
				gCartFPMath.setExw(gCartGM.getExw());
				gCartFPMath.setWeight(gCartGM.getWeight());
				gCartFPMath.setCostPrice(gCartGM.getCostPrice());
				gLCartFPMath.add(gCartFPMath); 
			}
		}
		map.put("hLCartFPMath",hLCartFPMath);
		map.put("bLCartFPMath",bLCartFPMath);
		map.put("gLCartFPMath",gLCartFPMath);
		return map;
	}

	
	/**获取购物车页面定金商品数据
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String,Object> getCartDFGMap(String customerID)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd.put("customerID", customerID);
		pd.put("paymentType", 2);
		pd.put("shipType", 1);
		//保税仓直邮
		List<CartGoods> bCartGoods = getCartGoods(pd);
		List<CartDPMath> bLCartDPMath = new ArrayList<>();
		if (!bCartGoods.isEmpty()){
			for(CartGoods bcg:bCartGoods){
				CartDPMath bCartDPMath = new CartDPMath();
				bCartDPMath.setCartID(bcg.getCartID());
				bCartDPMath.setGoodsID(bcg.getGoodsID());
				GoodsCommon bCartGC = bcg.getCartGC();
				bCartDPMath.setGoodsName(bCartGC.getGoodsName());
				bCartDPMath.setMainMap(bCartGC.getMainMap());
				bCartDPMath.setCategory2ID(bCartGC.getCategory2ID());
				bCartDPMath.setCustomerID(bcg.getCustomerID());
				GoodsManage bCartGM = bcg.getCartGM();
				bCartDPMath.setGoodsStock(bCartGM.getGoodsStock());
				bCartDPMath.setGoodsState(bCartGM.getGoodsState());
				bCartDPMath.setShipType(bCartGM.getShipType());
				bCartDPMath.setBuyNum(bcg.getBuyNum());
				bCartDPMath.setGoodsPrice1(bCartGM.getGoodsPrice1());
				bCartDPMath.setGoodsPrice2(bCartGM.getGoodsPrice2());
				bCartDPMath.setRnum1(bCartGM.getRnum1());
				bCartDPMath.setRnum2(bCartGM.getRnum2());
				bCartDPMath.setRnum3(bCartGM.getRnum3());
				bCartDPMath.setExw(bCartGM.getExw());
				bCartDPMath.setDeposit(bCartGM.getDeposit());
				bLCartDPMath.add(bCartDPMath);
			}
		}
		//海外直邮
		pd.put("shipType", 2);
		List<CartGoods> hCartGoods = getCartGoods(pd);
		List<CartDPMath> hLCartDPMath = new ArrayList<>();
		if (!hCartGoods.isEmpty()){
			for(CartGoods hcg:hCartGoods){
				CartDPMath hCartDPMath = new CartDPMath();
				hCartDPMath.setCartID(hcg.getCartID());
				hCartDPMath.setGoodsID(hcg.getGoodsID());
				GoodsCommon hCartGC = hcg.getCartGC();
				hCartDPMath.setGoodsName(hCartGC.getGoodsName());
				hCartDPMath.setMainMap(hCartGC.getMainMap());
				hCartDPMath.setCategory2ID(hCartGC.getCategory2ID());
				hCartDPMath.setCustomerID(hcg.getCustomerID());
				GoodsManage hCartGM = hcg.getCartGM();
				hCartDPMath.setGoodsStock(hCartGM.getGoodsStock());
				hCartDPMath.setGoodsState(hCartGM.getGoodsState());
				hCartDPMath.setShipType(hCartGM.getShipType());
				hCartDPMath.setBuyNum(hcg.getBuyNum());
				hCartDPMath.setGoodsPrice1(hCartGM.getGoodsPrice1());
				hCartDPMath.setGoodsPrice2(hCartGM.getGoodsPrice2());
				hCartDPMath.setRnum1(hCartGM.getRnum1());
				hCartDPMath.setRnum2(hCartGM.getRnum2());
				hCartDPMath.setRnum3(hCartGM.getRnum3());
				hCartDPMath.setExw(hCartGM.getExw());
				hCartDPMath.setDeposit(hCartGM.getDeposit());
				hLCartDPMath.add(hCartDPMath);
			}
		}
		//国内现货
		pd.put("shipType", 3);
		List<CartGoods> gCartGoods = getCartGoods(pd);
		List<CartDPMath> gLCartDPMath = new ArrayList<>();
		if (!gCartGoods.isEmpty()){
			for(CartGoods gcg:gCartGoods){
				CartDPMath gCartDPMath = new CartDPMath();
				gCartDPMath.setCartID(gcg.getCartID());
				gCartDPMath.setGoodsID(gcg.getGoodsID());
				GoodsCommon gCartGC = gcg.getCartGC();
				gCartDPMath.setGoodsName(gCartGC.getGoodsName());
				gCartDPMath.setMainMap(gCartGC.getMainMap());
				gCartDPMath.setCategory2ID(gCartGC.getCategory2ID());
				gCartDPMath.setCustomerID(gcg.getCustomerID());
				GoodsManage gCartGM = gcg.getCartGM();
				gCartDPMath.setGoodsStock(gCartGM.getGoodsStock());
				gCartDPMath.setGoodsState(gCartGM.getGoodsState());
				gCartDPMath.setShipType(gCartGM.getShipType());
				gCartDPMath.setBuyNum(gcg.getBuyNum());
				gCartDPMath.setGoodsPrice1(gCartGM.getGoodsPrice1());
				gCartDPMath.setGoodsPrice2(gCartGM.getGoodsPrice2());
				gCartDPMath.setRnum1(gCartGM.getRnum1());
				gCartDPMath.setRnum2(gCartGM.getRnum2());
				gCartDPMath.setRnum3(gCartGM.getRnum3());
				gCartDPMath.setExw(gCartGM.getExw());
				gCartDPMath.setDeposit(gCartGM.getDeposit());
				gLCartDPMath.add(gCartDPMath);
			}
		}
		map.put("hLCartDPMath",hLCartDPMath);
		map.put("bLCartDPMath",bLCartDPMath);
		map.put("gLCartDPMath",gLCartDPMath);
		return map;
	}
	
	/**购物车全款页面根据cartID变更单商品数量--判断是否在购买范围内
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public void updateCartGN(PageData pd) throws Exception {
		//查询该商品的阶段价格;
		PageData goods = (PageData)daoSupport.findForObject("ShopCartReadMapper.findGoodsPrice", pd.getString("goodsID"));
		 int rnum3 = Integer.valueOf(goods.getString("rnum3"));
		 int rnum2 = Integer.valueOf(goods.getString("rnum2"));
		 int rnum1 = Integer.valueOf(goods.getString("rnum1"));
		 int buyNum = Integer.valueOf(pd.getString("buyNum"));
		 int exw = Integer.valueOf(goods.getString("exw"));
		 int stock = Integer.valueOf(goods.getString("goods_stock"));
		 if (stock<=buyNum) {
				buyNum = stock;
			}
			
		if (exw == 1) {//price2为exw价格
			if(buyNum > rnum2){// 则最大购买量为rnum2
				buyNum = rnum2;
			}else if (buyNum <= rnum1) {
				buyNum = rnum1;
			} 
		}
		if (exw == 2) {
			if (rnum3<buyNum) {
				buyNum = rnum3;
			}else if(buyNum<rnum1){
				buyNum = rnum1;
			} 
		}
		pd.put("buyNum", buyNum);
		pd.put("time", DateUtil.getTime());
		daoSupport.update("ShopCartWriteMapper.updateCartGN", pd);
	}

	/**根据cartID获取购物车单个商品信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public CartGoods getCartUG(PageData pd) throws Exception {
		return (CartGoods)daoSupport.findForObject("ShopCartReadMapper.getCartUG", pd);
	}

	/**删除购物车单个商品
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public void deleteCartUG(PageData pd) throws Exception {
		daoSupport.delete("ShopCartWriteMapper.deleteCartUG", pd);
		//查询用户购物车数量;
		Session session = Jurisdiction.getSession();
		String customerID = Jurisdiction.getCustomerID();
		int cartNewNum = 0;
		try {
			cartNewNum = (int)daoSupport.findForObject("CustomerReadMapper.getCartNum",customerID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute(Const.SESSION_CUSTOMER_CART_NUM, cartNewNum);
	}

	/**批量删除购物车商品
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public void bDeleteCartG(String[] cartID) throws Exception {
		daoSupport.delete("ShopCartWriteMapper.bDeleteCartG", cartID);
		//查询用户购物车数量;
		Session session = Jurisdiction.getSession();
		String customerID = Jurisdiction.getCustomerID();
		int cartNewNum = 0;
		try {
			cartNewNum = (int)daoSupport.findForObject("CustomerReadMapper.getCartNum",customerID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute(Const.SESSION_CUSTOMER_CART_NUM, cartNewNum);
	}
	
	/**根据用户ID和商品ID获取购物车商品
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public CartGoods findCGoods(PageData pd) throws Exception {
		return (CartGoods)daoSupport.findForObject("ShopCartReadMapper.findCGoods", pd);
	}
	
	
	/**
	 * 添加商品到购物车,需先判断购物车中是否已经有该商品;
	 * -----------------再判断数量是否已经超出2段最大值;
	 * -----------------再判断购买数量对应的价格段;
	 */
	@Override
	public String addShopCard(PageData pd) throws Exception {
		String msg = "";
		CartGoods cartGoods = findCGoods(pd);
		//查询该商品的阶段价格;
		PageData goods = (PageData)daoSupport.findForObject("ShopCartReadMapper.findGoodsPrice", pd.getString("goodsID"));
		 int rnum3 = Integer.valueOf(goods.getString("rnum3"));
		 int rnum2 = Integer.valueOf(goods.getString("rnum2"));
		 int rnum1 = Integer.valueOf(goods.getString("rnum1"));
		 int buyNum = Integer.valueOf(pd.getString("buyNum"));
		 int exw = Integer.valueOf(goods.getString("exw"));
		//如果购物车中已经存在该商品;
		if (cartGoods != null) {
			int bumNum0 = cartGoods.getBuyNum();
			int bumNum1 = bumNum0 + buyNum;
			if (rnum3<bumNum1) {
				bumNum1 = rnum3;
				msg = "已达最大购买数量";
			}else if(exw == 1 && bumNum1 > rnum2){//price2为exw价格，则最大购买量为rnum2
				bumNum1 = rnum2;
				msg = "已达最大购买数量";
			}else if(bumNum1<rnum1){
				bumNum1 = rnum1;
				msg = "购买数量不到起订量，则系统默认购买量为起订量。";
			}else{
				msg = "添加成功";
			}
			int cartID = cartGoods.getCartID();
			pd.put("buyNum", bumNum1);
			pd.put("cartID", cartID);
			pd.put("time", DateUtil.getTime());
			daoSupport.update("ShopCartWriteMapper.updateCartGN", pd);
		}else {
			if(exw == 1 && buyNum > rnum2){//price2为exw价格，则最大购买量为rnum2
				buyNum = rnum2;
				msg = "购买数量大于最大购买量，则系统默认购买量为最大购买量。";
			}else if (rnum3<buyNum) {
				buyNum = rnum3;
				msg = "购买数量大于最大购买量，则系统默认购买量为最大购买量。";
			}else if(buyNum<rnum1){
				buyNum = rnum1;
				msg = "购买数量不到起订量，系统默认购买量为起订量。";
			}else{
				msg = "添加成功";
			}
			pd.put("buyNum", buyNum);
			addGToCart(pd);
		}
		
		//查询用户购物车数量;
		Session session = Jurisdiction.getSession();
		String customerID = Jurisdiction.getCustomerID();
		int cartNewNum = 0;
		try {
			cartNewNum = (int)daoSupport.findForObject("CustomerReadMapper.getCartNum",customerID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute(Const.SESSION_CUSTOMER_CART_NUM, cartNewNum);
		return msg;
	}
	
	/**
	 * 根据订单id和用户id再次购买,循环加入购物车;
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public Result<Object> buyAgain(String orderID) throws Exception {
		Result<Object> result = new Result<Object>();
		if (Tools.isEmpty(orderID)) {
			result.setMsg("参数错误！  ");
			result.setState(Result.STATE_ERROR);
			return result;
		}
		Integer customerID = Integer.valueOf(Jurisdiction.getCustomerID());
		OrderManage orderManage = new OrderManage();
		orderManage.setOrderID(orderID.replace(" ", ""));
		orderManage.setCustomerID(customerID);
		orderManage = (OrderManage) daoSupport.findForObject("OrderReadMapper.getOrder", orderManage);
		List<OrderGoods> orderGoodsList = orderManage.getOrderGoods();
		
		for (Iterator iterator = orderGoodsList.iterator(); iterator.hasNext();) {
			OrderGoods orderGoods = (OrderGoods) iterator.next();
			PageData pd = new PageData();
			pd.put("customerID", customerID);
			pd.put("goodsID", orderGoods.getGoodsID());
			pd.put("paymentType", orderGoods.getPayType());
			pd.put("shipType", orderGoods.getTradeType());
			pd.put("buyNum", orderGoods.getGoodsNum());
			addShopCard(pd);
		}
		result.setState(Result.STATE_SUCCESS);
		return result;
	}
	
	/**
	 * 根据商品id查询商品库存goodsStock
	 */
	@Override
	public int getGoodsStock(String goodsID) throws Exception {
		return (int)daoSupport.findForObject("ShopCartReadMapper.getGoodsStock", goodsID);
	}
	
}
