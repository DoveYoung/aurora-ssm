package com.aurorascm.serviceImpl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.CartDPMath;
import com.aurorascm.entity.CartFPMath;
import com.aurorascm.entity.CartGoods;
import com.aurorascm.entity.CartOrderGoods;
import com.aurorascm.entity.GoodsCommon;
import com.aurorascm.entity.GoodsManage;
import com.aurorascm.service.BuySettleFCartService;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.MyDataException;
import com.aurorascm.util.MyParamException;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/**
 * 描述:客户登录注册ServiceImpl
 * 创建:BYG 2017/5/25
 * 修改:
 * @version 1.0
 */
@Service("buySettleFCartServiceImpl")
public class BuySettleFCartServiceImpl implements BuySettleFCartService{
	
	@Resource(name = "daoSupport")
	private DAO daoSupport;

	/**购物车【全款】购买结算
	 * @param customerID bCartIDs hCartIDs gCartIDs saID
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> getFSettleFSC(String customerID, String bCartIDs, String hCartIDs, String gCartIDs,
			String saID,int type) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (type == 1) {//立即购买
			PageData newShipAddr = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getNewShipAddr", customerID);
			String saIDNew = newShipAddr.getString("sa_id");
			PageData pd = new PageData();
			pd.put("saIDNew", saIDNew);
			pd.put("customerID", customerID);
			@SuppressWarnings("unchecked")
			List<PageData> shipAddress5 = (List<PageData>)daoSupport.findForList("AreaAddrReadMapper.get5ShipAddressExcludeNew", pd);
			map.put("newShipAddr",newShipAddr);
			map.put("shipAddress5", shipAddress5);
			saID = Tools.notEmptys(saID) ? saID : saIDNew;//如果saID为空，默认最新地址
		} else {//立即采购
			PageData wc1Address = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getWC1Address", customerID);		//获取1号微仓地址
			map.put("wc1Address", wc1Address);
			String customerSaID = wc1Address.getString("sa_id");
			saID = Tools.notEmptys(saID) ? saID : customerSaID;
		}
		PageData shipAddr = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getShipAddr", saID);
		map.put("shipAddr", shipAddr);
		String addressType = shipAddr.getString("address_type");
		String province = shipAddr.getString("province_pin");
		if (!"4".equals(addressType) || province == null || "".equals(province)) {
			province = "weicang";
		}
		DecimalFormat df = new DecimalFormat("0.00"); 
		BigDecimal bMoney = new BigDecimal(0);//保税仓费用
		BigDecimal bPostage = new BigDecimal(0);//保税仓邮费
		BigDecimal hMoney = new BigDecimal(0);//海外费用
		BigDecimal hPostage = new BigDecimal(0);//海外邮费
		BigDecimal gMoney = new BigDecimal(0);//国内现货费用
		BigDecimal gPostage = new BigDecimal(0);//国内现货邮费
		BigDecimal tPostage = new BigDecimal(0);//总邮费
		BigDecimal tMoney = new BigDecimal(0); //不包含邮费
		BigDecimal pMoney = new BigDecimal(0); //包含邮费实际应付
		if (bCartIDs != null) {
			List<CartFPMath> bCartFPMath = this.getCartFSettleG(bCartIDs, province);//计算费用
			if (!bCartFPMath.isEmpty()) {
				for(CartFPMath b:bCartFPMath){
					bMoney = bMoney.add(b.getUMoney());
					bPostage = bPostage.add(b.getUPostage());
					System.out.println(bPostage);
				}
			}
			map.put("bCartFPMath", bCartFPMath);
		}else{
			map.put("bCartFPMath", null);
		}
		if (hCartIDs != null) {
			List<CartFPMath> hCartFPMath = this.getCartFSettleG(hCartIDs, province);
			if (!hCartFPMath.isEmpty()) {
				for(CartFPMath h:hCartFPMath){
					hMoney = hMoney.add(h.getUMoney());
					hPostage = hPostage.add(h.getUPostage() != null ? h.getUPostage() : new BigDecimal(0));
				}
			}
			map.put("hCartFPMath", hCartFPMath);
		}else{
			map.put("hCartFPMath", null);
		}
		if (gCartIDs != null) {
			List<CartFPMath> gCartFPMath = this.getCartFSettleG(gCartIDs, province);
			if (!gCartFPMath.isEmpty()) {
				for(CartFPMath g:gCartFPMath){
					gMoney = gMoney.add(g.getUMoney());
					gPostage = gPostage.add(g.getUPostage() != null ? g.getUPostage() : new BigDecimal(0));
				}
			}
			map.put("gCartFPMath", gCartFPMath);
		}else{
			map.put("gCartFPMath", null);
		}
		tMoney = bMoney.add(hMoney).add(gMoney);
		tPostage = bPostage.add(hPostage).add(gPostage);
		pMoney = tPostage.add(tMoney);
		map.put("tMoney", df.format(tMoney));
		map.put("tPostage", df.format(tPostage));
		map.put("pMoney", df.format(pMoney));
		map.put("bPostage", bPostage);
		map.put("hPostage", hPostage);
		map.put("gPostage", gPostage);
		BigDecimal btMoney = new BigDecimal(0);//保税仓总费用  = 商品总价+邮费
		BigDecimal htMoney = new BigDecimal(0);//海外直邮总费用  = 商品总价+邮费
		btMoney = bMoney.add(bPostage);
		htMoney = hMoney.add(hPostage);
		map.put("btMoney", btMoney);
		map.put("htMoney", htMoney);
		return map;
	}

	/**购物车【全款】提交订单--ajax预请求判断购买金额是否超额
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, String> checkAmountFP(String bCartIDs, String hCartIDs, String gCartIDs, String saID) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String result = "";
		String msg = "";
		if (Tools.notEmptys(saID)) {
			PageData shipAddr = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getShipAddr", saID);
			String addressType = shipAddr.getString("address_type");
			String province = shipAddr.getString("province_pin");
			BigDecimal bMoney = new BigDecimal(0);//保税仓费用
			BigDecimal bPostage = new BigDecimal(0);//保税仓邮费
			BigDecimal hMoney = new BigDecimal(0);//海外费用
			BigDecimal hPostage = new BigDecimal(0);//海外邮费
			if (bCartIDs != null) {
				List<CartFPMath> bCartFPMath = this.getCartFSettleGFC(bCartIDs, province);//计算费用
				if ("4".equals(addressType) && !bCartFPMath.isEmpty()) {
					for(CartFPMath b:bCartFPMath){
						bMoney = bMoney.add(b.getUMoney());
						bPostage = bPostage.add(b.getUPostage());
					}
					bMoney = bMoney.add(bPostage);
				}
			}
			if (hCartIDs != null) {
				List<CartFPMath> hCartFPMath = this.getCartFSettleGFC(hCartIDs, province);
				if ("4".equals(addressType) && !hCartFPMath.isEmpty()) {
					for(CartFPMath h:hCartFPMath){
						hMoney = hMoney.add(h.getUMoney());
						hPostage = hPostage.add(h.getUPostage() != null ? h.getUPostage() : new BigDecimal(0));
					}
					hMoney = hMoney.add(hPostage);
				}
			}
			if (gCartIDs != null) {//国内现货只check购买数量
				this.getCartFSettleGFC(gCartIDs, province);
			}
			BigDecimal twoT = new BigDecimal("2000");//保税仓商品总额不能超过2000元
			BigDecimal TwentyT = new BigDecimal("20000");//海外直邮商品总额不能超过20000元
			if (bMoney.compareTo(twoT) == -1 && hMoney.compareTo(TwentyT) == -1){
				result = "success";
			}else {
				result = "failed";
				if(bMoney.compareTo(twoT) == 1 && hMoney.compareTo(TwentyT) == 1){
					msg = "保税仓商品全款总额不能大于2000.00元！海外直邮商品全款总额不能大于20000.00元";
				}else if(bMoney.compareTo(twoT) == 1 ){
					msg = "保税仓商品全款总额不能大于2000.00元！";
				}else{
					msg = "海外直邮商品全款总额不能大于20000.00元";
				}
			}
		}else{
			result = "failed";
			msg = "收获地址有异常！";
		}
		map.put("result", result);
		map.put("msg", msg);
		return map;
	}
	
	/**购物车【全款】提交订单
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> addFOrderFSC(String customerID, String customerName, String bCartIDs, String hCartIDs,
			String gCartIDs, String saID, String customerRemark) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageData wc1Address = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getWC1Address", customerID);		//获取1号微仓地址
		String customerSaID = wc1Address.getString("sa_id");
		saID = Tools.notEmptys(saID) ? saID : customerSaID;
		PageData shipAddr = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getShipAddr", saID);
		map.put("shipAddr", shipAddr);
		String addressType = shipAddr.getString("address_type");
		String province = shipAddr.getString("province_pin");
		if (!"4".equals(addressType) || province == null || "".equals(province)) {
			province = "weicang";
		}
		DecimalFormat df = new DecimalFormat("0.00"); 
		BigDecimal bMoney = new BigDecimal(0);//保税仓费用
		BigDecimal bPostage = new BigDecimal(0);//保税仓邮费
		BigDecimal bCost = new BigDecimal(0); //保税仓商品成本
		BigDecimal hMoney = new BigDecimal(0);//海外费用
		BigDecimal hPostage = new BigDecimal(0);//海外邮费
		BigDecimal hCost = new BigDecimal(0); //海外商品成本
		BigDecimal gMoney = new BigDecimal(0);//国内现货费用
		BigDecimal gPostage = new BigDecimal(0);//国内现货邮费
		BigDecimal gCost = new BigDecimal(0); //国内现货成本
		BigDecimal tCost = new BigDecimal(0); //订单成本（不含邮费）
		BigDecimal tPostage = new BigDecimal(0);//总邮费
		BigDecimal tMoney = new BigDecimal(0); //不包含邮费
		BigDecimal pMoney = new BigDecimal(0); //包含邮费实际应付
		if (bCartIDs != null) {
			List<CartFPMath> bCartFPMath = this.getCartFSettleG(bCartIDs, province);//计算费用
			if (!bCartFPMath.isEmpty()) {
				for(CartFPMath b:bCartFPMath){
					bMoney = bMoney.add(b.getUMoney());
					bPostage = bPostage.add(b.getUPostage());
					bCost = bCost.add(b.getUCost());
				}
			}
			map.put("bCartFPMath", bCartFPMath);
		}else{
			map.put("bCartFPMath", null);
		}
		if (hCartIDs != null) {
			List<CartFPMath> hCartFPMath = this.getCartFSettleG(hCartIDs, province);
			if (!hCartFPMath.isEmpty()) {
				for(CartFPMath h:hCartFPMath){
					hMoney = hMoney.add(h.getUMoney());
					hPostage = hPostage.add(h.getUPostage() != null ? h.getUPostage() : new BigDecimal(0));
					hCost = hCost.add(h.getUCost());
				}
			}
			map.put("hCartFPMath", hCartFPMath);
		}else{
			map.put("hCartFPMath", null);
		}
		if (gCartIDs != null) {
			List<CartFPMath> gCartFPMath = this.getCartFSettleG(gCartIDs, province);
			if (!gCartFPMath.isEmpty()) {
				for(CartFPMath g:gCartFPMath){
					gMoney = gMoney.add(g.getUMoney());
					gPostage = gPostage.add(g.getUPostage() != null ? g.getUPostage() : new BigDecimal(0));
					gCost = gCost.add(g.getUCost());
				}
			}
			map.put("gCartFPMath", gCartFPMath);
		}else{
			map.put("gCartFPMath", null);
		}
		tMoney = bMoney.add(hMoney).add(gMoney);//商品总额（不包含邮费）
		tPostage = bPostage.add(hPostage).add(gPostage);//商品总邮费
		tCost = bCost.add(hCost).add(gCost);//商品总成本
		pMoney = tPostage.add(tMoney);//交易总额（包含邮费）
		map.put("tMoney", df.format(tMoney));
		map.put("tPostage", df.format(tPostage));
		map.put("pMoney", df.format(pMoney));
		/** 生成订单开始 **/
		PageData orderPD = new PageData();					//用于存储订单信息
		String orderID = DateUtil.getSdfTimes() + customerID;
		String consignee = shipAddr.getString("name");
		String consigneeMobile = shipAddr.getString("mobile");
		String consigneeIDCard = shipAddr.getString("id_card");
		String shipAddress = shipAddr.getString("province") + shipAddr.getString("city") + shipAddr.getString("detail_address");
		String orderType = "";								//订单类型：1微仓；2非微仓
		if ("4".equals(addressType)) {
			orderType = "2";
		}else{
			orderType = "1";
		}
		String cartIDs = "";
		if (bCartIDs != null && hCartIDs != null && gCartIDs != null) {
			cartIDs = bCartIDs + "," + hCartIDs + "," + gCartIDs;
		}else if (bCartIDs != null && hCartIDs != null && gCartIDs == null) {
			cartIDs = bCartIDs + "," + hCartIDs;
		}else if (bCartIDs != null && hCartIDs == null && gCartIDs != null) {
			cartIDs = bCartIDs + "," + gCartIDs;
		}else if (bCartIDs == null && hCartIDs != null && gCartIDs != null) {
			cartIDs = hCartIDs + "," + gCartIDs;
		}else if (bCartIDs != null && hCartIDs == null && gCartIDs == null) {
			cartIDs = bCartIDs;
		}else if (bCartIDs == null && hCartIDs != null && gCartIDs == null) {
			cartIDs = hCartIDs;
		}else if (bCartIDs == null && hCartIDs == null && gCartIDs != null) {
			cartIDs = gCartIDs;
		}						
		List<CartOrderGoods> cartOrderGoods = this.getOrderGoods(cartIDs);
		int salesManager = (int) daoSupport.findForObject("CustomerReadMapper.getSalesManagerByCustomerID", customerID);
		orderPD.put("salesManager", salesManager);//获取客户销售经理
		orderPD.put("cartOrderGoods", cartOrderGoods);
		orderPD.put("orderID", orderID);
		orderPD.put("customerID", customerID);
		orderPD.put("consignee", consignee);
		orderPD.put("consigneeMobile", consigneeMobile);
		orderPD.put("consigneeIDCard", consigneeIDCard);
		orderPD.put("shipAddress", shipAddress);
		orderPD.put("orderType", orderType) ;				//订单类型：1微仓；2非微仓
		orderPD.put("payType", 1);							//付款方式：1全款支付；2定金
		orderPD.put("totalMoney", df.format(pMoney));     	//交易总额
		orderPD.put("shouldPayment", df.format(pMoney)); 	//应支付金额
		orderPD.put("postage", tPostage);
		orderPD.put("cost", tCost);						//订单商品总成本
		orderPD.put("orderTime", DateUtil.getTime());
		orderPD.put("orderState", 1);						//待付款
		orderPD.put("customerRemark", customerRemark);
		orderPD.put("orderLevel", 1);
		orderPD.put("customerName", customerName);
		orderPD.put("payTime", null);
		orderPD.put("payMoney", 0.00);
		daoSupport.save("OrderWriteMapper.addOrder2OM", orderPD);
		daoSupport.save("OrderWriteMapper.addSGOrder2OG", orderPD);//生成订单（单个订单可能包含多个商品）
		//购物车提交订单后删除购物车相应商品
		StringBuilder cartIDsToD = new StringBuilder();
		if (Tools.notEmpty(bCartIDs)){
			cartIDsToD.append(bCartIDs);
		}
		if (Tools.notEmpty(hCartIDs)){
			cartIDsToD.append(",").append(hCartIDs);
		}
        if (Tools.notEmpty(gCartIDs)){
			cartIDsToD.append(",").append(gCartIDs);
		}
		String cartID[] = cartIDsToD.toString().split(",");
		daoSupport.delete("ShopCartWriteMapper.bDeleteCartG", cartID);//购物车提交订单后删除购物车相应商品
		daoSupport.update("AreaAddrWriteMapper.updateShipAddrUsedTimes", saID);//更新收获地址使用次数
		orderPD.put("orderID", orderID);
		map.put("orderID", orderID);
		return map;
	}
	
	/**购物车【定金】购买结算
	 * @param customerID bCartIDs hCartIDs gCartIDs
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> getDSettleFSC(String customerID, String bCartIDs, String hCartIDs, String gCartIDs)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageData wc1Address = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getWC1Address", customerID);//获取1号微仓地址
		DecimalFormat df = new DecimalFormat("0.00"); 
		BigDecimal bFMoney = new BigDecimal(0);//保税区全款费用
		BigDecimal bDMoney = new BigDecimal(0);//保税区定金费用
		BigDecimal hFMoney = new BigDecimal(0);
		BigDecimal hDMoney = new BigDecimal(0);
		BigDecimal gFMoney = new BigDecimal(0);
		BigDecimal gDMoney = new BigDecimal(0);
		BigDecimal tFMoney = new BigDecimal(0);//总的全款费用
		BigDecimal pDMoney = new BigDecimal(0);//总的定金
		if (bCartIDs != null) {
			List<CartDPMath> bCartDPMath = this.getCartDSettleG(bCartIDs);
			if (!bCartDPMath.isEmpty()) {
				for(CartDPMath b:bCartDPMath){
					bFMoney = bFMoney.add(b.getUFMoney());
					bDMoney = bDMoney.add(b.getUDMoney());
				}
			}
			map.put("bCartDPMath", bCartDPMath);
		}else{
			map.put("bCartDPMath", null);
		}
		if (hCartIDs != null) {
			List<CartDPMath> hCartDPMath = this.getCartDSettleG(hCartIDs);
			if (!hCartDPMath.isEmpty()) {
				for(CartDPMath h:hCartDPMath){
					hFMoney = hFMoney.add(h.getUFMoney());
					hDMoney = hDMoney.add(h.getUDMoney());
				}
			}
			map.put("hCartDPMath", hCartDPMath);
		}else{
			map.put("hCartDPMath", null);
		}
		if (gCartIDs != null) {
			List<CartDPMath> gCartDPMath = this.getCartDSettleG(gCartIDs);
			if (!gCartDPMath.isEmpty()) {
				for(CartDPMath g:gCartDPMath){
					gFMoney = gFMoney.add(g.getUFMoney());
					gDMoney = gDMoney.add(g.getUDMoney());
				}
			}
			map.put("gCartDPMath", gCartDPMath);
		}else{
			map.put("gCartDPMath", null);
		}
		tFMoney = bFMoney.add(hFMoney).add(gFMoney);
		pDMoney =  bDMoney.add(hDMoney).add(gDMoney);
		map.put("tFMoney", df.format(tFMoney));
		map.put("pDMoney", df.format(pDMoney));
		map.put("wc1Address", wc1Address);
		return map;
	}
	
	/**购物车【定金】提交订单--ajax预请求判断check购买数量是否在三段、库存范围内
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, String>checkAmountDP(String bCartIDs, String hCartIDs, String gCartIDs)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if (bCartIDs != null) {
			this.getCartDSettleGFD(bCartIDs);//check购买数量是否在三段、库存范围内
		}
		if (hCartIDs != null) {
			this.getCartDSettleGFD(hCartIDs);//check购买数量是否在三段、库存范围内
		}
		if (gCartIDs != null) {
			this.getCartDSettleGFD(gCartIDs);//check购买数量是否在三段、库存范围内
		}
		map.put("result", "success");
		return map;
	}
	
	/**购物车【定金】购买结算页提交订单
	 * @param customerID,customerName,bCartIDs,hCartIDs,gCartIDs,customerRemark
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> addDOrderFSC(String customerID, String customerName, String bCartIDs, String hCartIDs,
			String gCartIDs, String customerRemark) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageData wc1Address = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getWC1Address", customerID);//获取1号微仓地址
		DecimalFormat df = new DecimalFormat("0.00"); 
		BigDecimal bFMoney = new BigDecimal(0);//保税区全款费用
		BigDecimal bDMoney = new BigDecimal(0);//保税区定金费用
		BigDecimal bDCost = new BigDecimal(0);//保税区商品成本
		BigDecimal hFMoney = new BigDecimal(0);
		BigDecimal hDMoney = new BigDecimal(0);
		BigDecimal hDCost = new BigDecimal(0);//海外直邮商品成本
		BigDecimal gFMoney = new BigDecimal(0);
		BigDecimal gDMoney = new BigDecimal(0);
		BigDecimal gDCost = new BigDecimal(0);//国内现货商品成本
		BigDecimal tFMoney = new BigDecimal(0);//总的全款费用
		BigDecimal pDMoney = new BigDecimal(0);//总的定金
		BigDecimal tDCost = new BigDecimal(0);//总的商品成本
		if (bCartIDs != null) {
			List<CartDPMath> bCartDPMath = this.getCartDSettleG(bCartIDs);
			if (!bCartDPMath.isEmpty()) {
				for(CartDPMath b:bCartDPMath){
					bFMoney = bFMoney.add(b.getUFMoney());
					bDMoney = bDMoney.add(b.getUDMoney());
					bDCost = bDCost.add(b.getuDCost());
				}
			}else{//如果查不到,说明参数有误,或者重复提交;
				throw new MyParamException();
			}
			map.put("bCartDPMath", bCartDPMath);
		}else{
			map.put("bCartDPMath", null);
		}
		if (hCartIDs != null) {
			List<CartDPMath> hCartDPMath = this.getCartDSettleG(hCartIDs);
			if (!hCartDPMath.isEmpty()) {
				for(CartDPMath h:hCartDPMath){
					hFMoney = hFMoney.add(h.getUFMoney());
					hDMoney = hDMoney.add(h.getUDMoney());
					hDCost = hDCost.add(h.getuDCost());
				}
			}else{//如果查不到,说明参数有误,或者重复提交;异常页
				throw new MyParamException();
			}
			map.put("hCartDPMath", hCartDPMath);
		}else{
			map.put("hCartDPMath", null);
		}
		if (gCartIDs != null) {
			List<CartDPMath> gCartDPMath = this.getCartDSettleG(gCartIDs);
			if (!gCartDPMath.isEmpty()) {
				for(CartDPMath g:gCartDPMath){
					gFMoney = gFMoney.add(g.getUFMoney());
					gDMoney = gDMoney.add(g.getUDMoney());
					gDCost = gDCost.add(g.getuDCost());
				}
			}else{//如果查不到,说明参数有误,或者重复提交;异常页
				throw new MyParamException();
			}
			map.put("gCartDPMath", gCartDPMath);
		}else{
			map.put("gCartDPMath", null);
		}
		tFMoney = bFMoney.add(hFMoney).add(gFMoney);//交易总额
		pDMoney =  bDMoney.add(hDMoney).add(gDMoney);//支付定金额
		tDCost =  bDCost.add(hDCost).add(gDCost);//支付定金额
		map.put("tFMoney", df.format(tFMoney));
		map.put("pDMoney", df.format(pDMoney));
		map.put("wc1Address", wc1Address);
		//生成订单开始
		PageData orderPD = new PageData();					//用于存储订单信息
		String orderID = DateUtil.getSdfTimes() + customerID;
		String consignee = wc1Address.getString("name");
		String consigneeMobile = wc1Address.getString("mobile");
		String consigneeIDCard = wc1Address.getString("id_card");
		String shipAddress = wc1Address.getString("province") + wc1Address.getString("city") + wc1Address.getString("detail_address");
		String orderType = "1";								//订单类型：1微仓；2非微仓
		String cartIDs = "";
		if (bCartIDs != null && hCartIDs != null && gCartIDs != null) {
			cartIDs = bCartIDs + "," + hCartIDs + "," + gCartIDs;
		}else if (bCartIDs != null && hCartIDs != null && gCartIDs == null) {
			cartIDs = bCartIDs + "," + hCartIDs;
		}else if (bCartIDs != null && hCartIDs == null && gCartIDs != null) {
			cartIDs = bCartIDs + "," + gCartIDs;
		}else if (bCartIDs == null && hCartIDs != null && gCartIDs != null) {
			cartIDs = hCartIDs + "," + gCartIDs;
		}else if (bCartIDs != null && hCartIDs == null && gCartIDs == null) {
			cartIDs = bCartIDs;
		}else if (bCartIDs == null && hCartIDs != null && gCartIDs == null) {
			cartIDs = hCartIDs;
		}else if (bCartIDs == null && hCartIDs == null && gCartIDs != null) {
			cartIDs = gCartIDs;
		}						
		List<CartOrderGoods> orderGoods = this.getOrderGoods(cartIDs);
		int salesManager = (int) daoSupport.findForObject("CustomerReadMapper.getSalesManagerByCustomerID", customerID);
		orderPD.put("salesManager", salesManager);//获取客户销售经理
		orderPD.put("cartOrderGoods", orderGoods);
		orderPD.put("orderID", orderID);
		orderPD.put("customerID", customerID);
		orderPD.put("consignee", consignee);
		orderPD.put("consigneeMobile", consigneeMobile);
		orderPD.put("consigneeIDCard", consigneeIDCard);
		orderPD.put("shipAddress", shipAddress);
		orderPD.put("orderType", orderType);				//订单类型：1微仓；2非微仓;3微仓销售订单
		orderPD.put("payType", 2);							//付款方式：1全款支付；2定金
		orderPD.put("totalMoney", df.format(tFMoney));     	//交易总额
		orderPD.put("shouldPayment", df.format(pDMoney)); 	//应该支付金额
		orderPD.put("cost", tDCost);						//乘以定金后的成本
		orderPD.put("postage", new BigDecimal(0));
		orderPD.put("orderTime", DateUtil.getTime());
		orderPD.put("orderState", 1);						//待付款
		orderPD.put("customerRemark", customerRemark);
		orderPD.put("orderLevel", 1);
		orderPD.put("customerName", customerName);
		orderPD.put("payTime", null);
		orderPD.put("payMoney", 0.00);
		daoSupport.save("OrderWriteMapper.addOrder2OM", orderPD);
		daoSupport.save("OrderWriteMapper.addSGOrder2OG", orderPD);//生成订单（单个订单可能包含多个商品）
		//购物车提交订单后删除购物车相应商品
		StringBuilder cartIDsToD = new StringBuilder();
		if (Tools.notEmpty(bCartIDs)){
			cartIDsToD.append(bCartIDs);
		}
		if (Tools.notEmpty(hCartIDs)){
			cartIDsToD.append(",").append(hCartIDs);
		}
        if (Tools.notEmpty(gCartIDs)){
			cartIDsToD.append(",").append(gCartIDs);
		}
		String cartID[] = cartIDsToD.toString().split(",");
		daoSupport.delete("ShopCartWriteMapper.bDeleteCartG", cartID);//购物车提交订单后删除购物车相应商品
		
		orderPD.put("orderID", orderID);
		map.put("orderID", orderID);
		return map;
	}

	/**根据CartID字符串获取购物车全款购买结算信息---提交订单预提交CHECK，购买数量是否在规定范围内，并小于库存
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<CartFPMath> getCartFSettleGFC(String cartIDs,String province)throws Exception{
		List<CartFPMath> cartFPMath = new ArrayList<>();
		String ArrayCartID[] = cartIDs.split(",");
		@SuppressWarnings("unchecked")
		List<CartGoods> cartFSettleG = (List<CartGoods>)daoSupport.findForList("ShopCartReadMapper.getCartFSettleG", ArrayCartID);
		if (!cartFSettleG.isEmpty()) {
			for(CartGoods cfsg:cartFSettleG){
				GoodsCommon cartUGC = cfsg.getCartGC();
				GoodsManage cartUGM = cfsg.getCartGM();
				int rnum1 = cartUGM.getRnum1();
				int rnum2 = cartUGM.getRnum2();
				int rnum3 = cartUGM.getRnum3();
				int exw = cartUGM.getExw();
				int goodsStock = cartUGM.getGoodsStock();
				int buyNum = cfsg.getBuyNum();
				if (exw == 1 && buyNum > rnum2){
					String goodsName = cartUGC.getGoodsName();
					throw new MyDataException("商品:'" + goodsName+"'购买数量不在规定购买范围");
				}else if(buyNum <= goodsStock && buyNum <= rnum3 && buyNum >= rnum1 ){
					CartFPMath cartUFPMath = new CartFPMath();//购物车商品实体;
					cartUFPMath.setBuyNum(buyNum);
					cartUFPMath.setGoodsStock(goodsStock);
					cartUFPMath.setRnum1(rnum1);
					cartUFPMath.setRnum2(rnum2);
					cartUFPMath.setRnum3(rnum3);
					cartUFPMath.setProvince(province);
					cartUFPMath.setCartID(cfsg.getCartID());
					cartUFPMath.setGoodsID(cfsg.getGoodsID());
					cartUFPMath.setGoodsName(cartUGC.getGoodsName());
					cartUFPMath.setMainMap(cartUGC.getMainMap());
					cartUFPMath.setCategory2ID(cartUGC.getCategory2ID());
					cartUFPMath.setCustomerID(cfsg.getCustomerID());
					cartUFPMath.setShipType(cartUGM.getShipType());
					cartUFPMath.setPostageStyle(cartUGM.getPostageStyle());
					cartUFPMath.setGoodsPrice1(cartUGM.getGoodsPrice1());
					cartUFPMath.setGoodsPrice2(cartUGM.getGoodsPrice2());
					cartUFPMath.setExw(exw);
					cartUFPMath.setWeight(cartUGM.getWeight());
					cartUFPMath.setCostPrice(cartUGM.getCostPrice());
					cartFPMath.add(cartUFPMath);
				}else {
					String goodsName = cartUGC.getGoodsName();
					throw new MyDataException("商品:'" + goodsName+"'购买数量不在规定购买范围，或超出库存！");
				}
			}
		}
		return cartFPMath;
	}
	
	/**根据CartID字符串获取购物车全款购买结算信息
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<CartFPMath> getCartFSettleG(String cartIDs,String province)throws Exception{
		List<CartFPMath> cartFPMath = new ArrayList<>();
		String[] ArrayCartID = cartIDs.split(",");
		@SuppressWarnings("unchecked")
		List<CartGoods> cartFSettleG = (List<CartGoods>)daoSupport.findForList("ShopCartReadMapper.getCartFSettleG", ArrayCartID);
		if (!cartFSettleG.isEmpty()) {
			for(CartGoods cfsg:cartFSettleG){
				CartFPMath cartUFPMath = new CartFPMath();//购物车商品实体;
				GoodsCommon cartUGC = cfsg.getCartGC();
				GoodsManage cartUGM = cfsg.getCartGM();
				cartUFPMath.setBuyNum(cfsg.getBuyNum());
				cartUFPMath.setGoodsStock(cartUGM.getGoodsStock());
				cartUFPMath.setRnum1(cartUGM.getRnum1());
				cartUFPMath.setRnum2(cartUGM.getRnum2());
				cartUFPMath.setRnum3(cartUGM.getRnum3());
				cartUFPMath.setProvince(province);
				cartUFPMath.setCartID(cfsg.getCartID());
				cartUFPMath.setGoodsID(cfsg.getGoodsID());
				cartUFPMath.setGoodsName(cartUGC.getGoodsName());
				cartUFPMath.setMainMap(cartUGC.getMainMap());
				cartUFPMath.setCategory2ID(cartUGC.getCategory2ID());
				cartUFPMath.setCustomerID(cfsg.getCustomerID());
				cartUFPMath.setShipType(cartUGM.getShipType());
				cartUFPMath.setPostageStyle(cartUGM.getPostageStyle());
				cartUFPMath.setGoodsPrice1(cartUGM.getGoodsPrice1());
				cartUFPMath.setGoodsPrice2(cartUGM.getGoodsPrice2());
				cartUFPMath.setWeight(cartUGM.getWeight());
				cartUFPMath.setCostPrice(cartUGM.getCostPrice());
				cartUFPMath.setExw(cartUGM.getExw());
				cartFPMath.add(cartUFPMath);
			}
		}
		return cartFPMath;
	}
	
	/**根据CartID字符串获取购物车【定金】购买结算信息---提交订单预提交CHECK，购买数量是否在规定范围内，并小于库存
	 * @param cartIDs
	 * @return
	 * @throws Exception
	 */
	public List<CartDPMath> getCartDSettleGFD(String cartIDs)throws Exception{
		List<CartDPMath> cartDPMath = new ArrayList<>();
		String ArrayCartID[] = cartIDs.split(",");
		//根据cartID[]获取购物车【定金】结算商品信息
		@SuppressWarnings("unchecked")
		List<CartGoods> cartDSettleG = (List<CartGoods>)daoSupport.findForList("ShopCartReadMapper.getCartDSettleG", ArrayCartID);
		if (!cartDSettleG.isEmpty()) {
			for(CartGoods d:cartDSettleG){
				GoodsManage cartUGM = d.getCartGM();
				int rnum1 = cartUGM.getRnum1();
				int rnum3 = cartUGM.getRnum3();
				int rnum2 = cartUGM.getRnum2();
				int exw = cartUGM.getExw();
				int goodsStock = d.getCartGM().getGoodsStock();
				int buyNum = d.getBuyNum();
				if(exw == 1 && buyNum > rnum2){
					String goodsName = d.getCartGC().getGoodsName();
					throw new MyDataException("商品:'" + goodsName+"'购买数量不在规定购买范围");
				} else if(buyNum <= goodsStock && buyNum <= rnum3 && buyNum >= rnum1 ){
					continue;
				}else {
					String goodsName = d.getCartGC().getGoodsName();
					throw new MyDataException("商品:'" + goodsName+"'购买数量不在规定购买范围，或超出库存！");
				}
			}
		}
		return cartDPMath;
	}
	
	/**根据CartID字符串获取购物车【定金】购买结算信息
	 * @param cartIDs
	 * @return
	 * @throws Exception
	 */
	public List<CartDPMath> getCartDSettleG(String cartIDs)throws Exception{
		List<CartDPMath> cartDPMath = new ArrayList<>();
		String ArrayCartID[] = cartIDs.split(",");
		//根据cartID[]获取购物车【定金】结算商品信息
		@SuppressWarnings("unchecked")
		List<CartGoods> cartDSettleG = (List<CartGoods>)daoSupport.findForList("ShopCartReadMapper.getCartDSettleG", ArrayCartID);
		if (!cartDSettleG.isEmpty()) {
			for(CartGoods d:cartDSettleG){
				CartDPMath cartUDPMath = new CartDPMath();
				GoodsCommon cartUGC = d.getCartGC();
				GoodsManage cartUGM = d.getCartGM();
				cartUDPMath.setCartID(d.getCartID());
				cartUDPMath.setGoodsID(d.getGoodsID());
				cartUDPMath.setGoodsName(cartUGC.getGoodsName());
				cartUDPMath.setMainMap(cartUGC.getMainMap());
				cartUDPMath.setCategory2ID(cartUGC.getCategory2ID());
				cartUDPMath.setCustomerID(d.getCustomerID());
				cartUDPMath.setGoodsStock(cartUGM.getGoodsStock());
				cartUDPMath.setShipType(cartUGM.getShipType());
				cartUDPMath.setDeposit(cartUGM.getDeposit());
				cartUDPMath.setBuyNum(d.getBuyNum());
				cartUDPMath.setGoodsPrice1(cartUGM.getGoodsPrice1());
				cartUDPMath.setGoodsPrice2(cartUGM.getGoodsPrice2());
				cartUDPMath.setRnum1(cartUGM.getRnum1());
				cartUDPMath.setRnum2(cartUGM.getRnum2());
				cartUDPMath.setRnum3(cartUGM.getRnum3());
				cartUDPMath.setExw(cartUGM.getExw());
				cartUDPMath.setCostPrice(cartUGM.getCostPrice());
				cartDPMath.add(cartUDPMath);
			}
		}
		return cartDPMath;
	}
	
	/**根据CartIDs字符串获取订单包含商品信息
	 * @param cartIDs
	 * @return
	 * @throws Exception
	 */
	public List<CartOrderGoods> getOrderGoods(String cartIDs)throws Exception{
		PageData pd = new PageData();
		List<CartOrderGoods> orderGoods = new ArrayList<>();
		String ArrayCartID[] = cartIDs.split(",");
		pd.put("ArrayCartID", ArrayCartID);
		@SuppressWarnings("unchecked")
		List<PageData> orderGoods1 = (List<PageData>)daoSupport.findForList("OrderReadMapper.getOrderGoods", ArrayCartID);
		if(!orderGoods1.isEmpty()){
			for(PageData o : orderGoods1){
				CartOrderGoods orderUGoods = new CartOrderGoods();
				orderUGoods.setGoodsID(o.getString("goods_id"));
				orderUGoods.setGoodsName(o.getString("goods_name"));
				orderUGoods.setGoodsCode(o.getString("goods_code"));
				orderUGoods.setGoodsMap(o.getString("main_map"));
				orderUGoods.setTradeType(Integer.valueOf(o.getString("ship_type")));
				orderUGoods.setDeposit(Integer.valueOf(o.getString("deposit")));
				orderUGoods.setGoodsNum(Integer.valueOf(o.getString("buy_num")));
				orderUGoods.setCostPrice(new BigDecimal(o.getString("cost_price")));
				orderUGoods.setGoodsPrice1(new BigDecimal(o.getString("goods_price1")));
				orderUGoods.setGoodsPrice2(new BigDecimal(o.getString("goods_price2")));
				orderUGoods.setRnum1(Integer.valueOf(o.getString("rnum1")));
				orderUGoods.setRnum2(Integer.valueOf(o.getString("rnum2")));
				orderUGoods.setRnum3(Integer.valueOf(o.getString("rnum3")));
				orderUGoods.setGoodsWeight(new BigDecimal(o.getString("weight")));
				orderUGoods.setPostageStyle(Integer.valueOf(o.getString("postage_style")));
				orderGoods.add(orderUGoods);
			}
		}
		return orderGoods;
	}

}
