package com.aurorascm.service;

import java.util.List;
import java.util.Map;

import com.aurorascm.entity.CartGoods;
import com.aurorascm.entity.Result;
import com.aurorascm.util.PageData;

public interface ShopCartService {
	
	/**新增商品到购物车
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public void addGToCart(PageData pd)throws Exception;
	
	/**删除购物车单个商品
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public void deleteCartUG(PageData pd)throws Exception;
	
	/**批量删除购物车商品
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public void bDeleteCartG(String[] cartID)throws Exception;
	
	/**根据用户ID/贸易方式/支付方式获取购物车中某贸易方式下的商品
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<CartGoods> getCartGoods(PageData pd)throws Exception;
	
	/**获取购物车页面全款商品数据
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getCartFPGMap(String customerID)throws Exception;
	
	/**获取购物车页面定金商品数据
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getCartDFGMap(String customerID)throws Exception;
	
	/**根据用户ID和商品ID获取购物车商品
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public CartGoods findCGoods(PageData pd)throws Exception;
	
	/**
	 * 添加商品到购物车,需先判断购物车中是否已经有该商品;
	 */
	public String addShopCard(PageData pd) throws Exception;
	
	/**购物车全款页面根据cartID变更单商品数量
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public void updateCartGN(PageData pd)throws Exception;
	
	/**根据cartID获取购物车单个商品信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */   
	public CartGoods getCartUG(PageData pd)throws Exception;
	
//	/**根据CartID[]获取购物车【全款】购买结算商品
//	 * @param String[] cartID
//	 * @return
//	 * @throws Exception
//	 */
//	public List<CartGoods> getCartFSettleG(String[] cartID)throws Exception;
	
//	/**根据CartID[]获取购物车【定金】购买结算商品
//	 * @param String[] cartID
//	 * @return
//	 * @throws Exception
//	 */
//	public List<CartGoods> getCartDSettleG(String[] cartID)throws Exception;
	
	/**
	 * @Title: buyAgain 
	 * @Description: 订单中心--再次购买加入购物车
	 * @param    
	 * @return Result<Object>  
	 * @author SSY
	 * @date 2018年5月24日 下午3:20:41
	 */
	public Result<Object> buyAgain(String orderID) throws Exception;
	
	/**
	 * 根据商品id查询商品库存goodsStock
	 */
	public int getGoodsStock(String goodsID) throws Exception;
 
	
	
}
