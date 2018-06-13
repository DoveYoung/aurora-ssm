package com.aurorascm.serviceImpl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.GoodsManage;
import com.aurorascm.service.BuySettleFDetailService;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.MyParamException;
import com.aurorascm.util.PageData;
import com.aurorascm.util.PostageMath;
import com.aurorascm.util.Tools;

/**
 * 描述:客户登录注册ServiceImpl
 * 创建:BYG 2017/5/25
 * 修改:
 * @version 1.0
 */
@Service("buySettleFDetailServiceImpl")
public class BuySettleFDetailServiceImpl implements BuySettleFDetailService{
	
	@Resource(name = "daoSupport")
	private DAO daoSupport;

	/**详情页立即购买【全款】结算
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> getFSettleFLB(String customerID, String fGoodsID, String fBuyNum, String saID) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageData wc1Address = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getWC1Address", customerID);		//获取1号微仓地址
		String customerSaID = wc1Address.getString("sa_id");
		saID = saID !=null ? saID :  customerSaID;
		PageData shipAddr = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getShipAddr", saID);
		String addressType = shipAddr.getString("address_type");
		String province = shipAddr.getString("province_pin");
		if (!"4".equals(addressType) || province == null || "".equals(province)) {
			province = "weiCang";
		}
		PageData pd = new PageData();
		pd.put("goodsID", fGoodsID);
		GoodsManage gManage = (GoodsManage)daoSupport.findForObject("GManageReadMapper.getGMDForLB", pd);
		int rnum1 = gManage.getRnum1();
		int rnum2 = gManage.getRnum2();
		int rnum3 = gManage.getRnum3();
		int exw = gManage.getExw();
		int num = Integer.valueOf(fBuyNum);
		map.put("num",num);
		DecimalFormat df = new DecimalFormat("0.00");
		BigDecimal price1 = gManage.getGoodsPrice1();
		BigDecimal price2 = gManage.getGoodsPrice2();
		BigDecimal goodsPrice = new BigDecimal("0");
		BigDecimal tPayment = new BigDecimal("0");
		BigDecimal payment = new BigDecimal("0");
		if(rnum1 <= num && num <= rnum2){
			BigDecimal n = new BigDecimal(String.valueOf(num));
			goodsPrice = price1;
			payment = n.multiply(price1);//计算商品价格;
			map.put("goodsPrice",df.format(goodsPrice));
			map.put("payment",df.format(payment));
		}else if(exw == 1 && num > rnum2){
			BigDecimal n = new BigDecimal(String.valueOf(num));
			goodsPrice = price1;
			payment = n.multiply(price1);//计算商品价格;
			map.put("goodsPrice",df.format(goodsPrice));
			map.put("payment",df.format(payment));
		}else if(exw == 2 && rnum2 < num && num <= rnum3){
			BigDecimal n = new BigDecimal(String.valueOf(num));
			goodsPrice = price2;
			payment = n.multiply(price2);
			map.put("goodsPrice",df.format(goodsPrice));
			map.put("payment",df.format(payment));
		}
		BigDecimal postage = new BigDecimal("0");
		int postageStyle = gManage.getPostageStyle();
		if(postageStyle == 1 || province == "weiCang"){
			map.put("postage",df.format(postage));  
		}else{
			int shipType = gManage.getShipType();
			BigDecimal uWeight = gManage.getWeight();
			if(shipType == 2){
				postage = PostageMath.getHPosttage(uWeight, num);
				map.put("postage",df.format(postage));
			}else if(shipType == 1 || shipType == 3){
				postage = PostageMath.getBGPosttage(province, uWeight, num);
				map.put("postage",df.format(postage));
			}else{
				throw new MyParamException("【failed:立即购买全款邮寄方式参数有误!】");
			}
		}
		tPayment = payment.add(postage);											//总价格=商品价格+邮费;
		map.put("tPayment",df.format(tPayment));
		@SuppressWarnings("unchecked")
		List<PageData> shipAddress5 = (List<PageData>)daoSupport.findForList("AreaAddrReadMapper.get5ShipAddress", customerID);
		if (shipAddress5.size() > 5) {
			PageData newShipAddr = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getNewShipAddr", customerID);
			map.put("newShipAddr",newShipAddr);
		}
		map.put("wc1Address",wc1Address);
		map.put("shipAddress5",shipAddress5);
		map.put("gManage",gManage);
		return map;
	}

	/**情页立即购买【全款】结算页面提交订单--ajax预请求判断购买金额是否超额+购买数量验证
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, String> checkAmountF(String goodsID, String buyNum, String saID) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String result = "";
		String msg = "";
		if (Tools.notEmptys(saID)) {
			PageData pd = new PageData();
			pd.put("goodsID", goodsID);
			GoodsManage gManage = (GoodsManage)daoSupport.findForObject("GManageReadMapper.getGMDForLB", pd);
			int rnum1 = gManage.getRnum1();
			int rnum2 = gManage.getRnum2();
			int rnum3 = gManage.getRnum3();
			int exw = gManage.getExw();
			int goodsStock = gManage.getGoodsStock();
			int num = Integer.valueOf(buyNum);
			if(num > rnum3 || num < rnum1 ){
				result = "failed";
				msg = "购买数量不在规定购买范围！";
			}else if(num > goodsStock){
				result = "failed";
				msg = "购买数量超出库存！";
			}else if(exw == 1 && num > rnum2){
				result = "failed";
				msg = "购买数量不在规定购买范围！";
			}else {
				PageData shipAddr = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getShipAddr", saID);
				String addressType = shipAddr.getString("address_type");
				String province = shipAddr.getString("province_pin");
				int shipType = gManage.getShipType();
				if ("4".equals(addressType) && shipType != 3) {//直接发货并且贸易方式不是国内现货进行限额判断。
					BigDecimal zero = new BigDecimal("0");
					if (shipType == 1 || shipType == 2) {
						BigDecimal price1 = gManage.getGoodsPrice1();
						BigDecimal price2 = gManage.getGoodsPrice2();
						BigDecimal goodsPrice = zero;
						BigDecimal tPayment = zero;
						BigDecimal payment = zero;
						if(rnum1 <= num && num <= rnum2){
							BigDecimal n = new BigDecimal(String.valueOf(num));
							goodsPrice = price1;
							payment = n.multiply(price1);//计算商品价格;
						}else if(exw == 1 && num > rnum2){
							BigDecimal n = new BigDecimal(String.valueOf(num));
							goodsPrice = price1;
							payment = n.multiply(price1);//计算商品价格;
						}else if(rnum2 < num && num <= rnum3){
							BigDecimal n = new BigDecimal(String.valueOf(num));
							goodsPrice = price2;
							payment = n.multiply(price2);
						}
						BigDecimal postage = zero;
						int postageStyle = gManage.getPostageStyle();
						if(postageStyle == 2){
							BigDecimal uWeight = gManage.getWeight();
							if(shipType == 2){
								postage = PostageMath.getHPosttage(uWeight, num);
							}else if(shipType == 1){
								postage = PostageMath.getBGPosttage(province, uWeight, num);
							}
						}
						tPayment = payment.add(postage);
						if(shipType == 1){
							BigDecimal quota = new BigDecimal("2000");//保税仓商品总额不能超过2000元
							if(tPayment.compareTo(quota) == -1){
								result = "success";
							}else{
								result = "failed";
								msg = "保税仓总额已大于2000元,建议分次购买,或放入北极光微仓!";
							}
						}else if(shipType == 2){
							BigDecimal quota = new BigDecimal("20000");//海外直邮总额不能超过20000元
							if(tPayment.compareTo(quota) == -1){
								result = "success";
							}else{
								result = "failed";
								msg = "海外直邮总额已大于20000元,建议分次购买,或放入北极光微仓!";
							}
						}else{
							result = "failed";
							msg = "贸易方式数据异常！";
						}
					}else{
						result = "success";
					}			
				}else{
					result = "success";
				}
			}
		}else {
			result = "failed";
			msg = "收货地址存在异常";
		}
		map.put("result", result);
		map.put("msg", msg);
		return map;
	}
	/**详情页立即购买【全款】提交订单
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> addOrderFLBFSettle(String customerID, String goodsID, String buyNum, 
												  String saID, String customerRemark) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageData wc1Address = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getWC1Address", customerID);		//获取1号微仓地址
		String customerSaID = wc1Address.getString("sa_id");
		saID = saID !=null ? saID :  customerSaID;
		PageData shipAddr = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getShipAddr", saID);
		String addressType = shipAddr.getString("address_type");
		String province = shipAddr.getString("province_pin");
		if (!"4".equals(addressType) || province == null || "".equals(province)) {
			province = "weiCang";
		}
		PageData pd = new PageData();
		pd.put("goodsID", goodsID);
		GoodsManage gManage = (GoodsManage)daoSupport.findForObject("GManageReadMapper.getGMDForLB", pd);
		int rnum1 = gManage.getRnum1();
		int rnum2 = gManage.getRnum2();
		int rnum3 = gManage.getRnum3();
		int exw = gManage.getExw();
		int num = Integer.valueOf(buyNum);
		map.put("num",num);
		DecimalFormat df = new DecimalFormat("0.00");
		BigDecimal price1 = gManage.getGoodsPrice1();
		BigDecimal price2 = gManage.getGoodsPrice2();
		BigDecimal goodsPrice = new BigDecimal("0");
		BigDecimal tPayment = new BigDecimal("0");
		BigDecimal payment = new BigDecimal("0");
		if(rnum1 <= num && num <= rnum2){
			BigDecimal n = new BigDecimal(String.valueOf(num));
			goodsPrice = price1;
			payment = n.multiply(price1);//计算商品价格;
			map.put("goodsPrice",df.format(goodsPrice));
			map.put("payment",df.format(payment));
		}else if(exw == 1 && num > rnum2){
			BigDecimal n = new BigDecimal(String.valueOf(num));
			goodsPrice = price1;
			payment = n.multiply(price1);//计算商品价格;
		}else if(rnum2 < num && num <= rnum3){
			BigDecimal n = new BigDecimal(String.valueOf(num));
			goodsPrice = price2;
			payment = n.multiply(price2);
			map.put("goodsPrice",df.format(goodsPrice));
			map.put("payment",df.format(payment));
		}
		BigDecimal postage = new BigDecimal("0");
		int postageStyle = gManage.getPostageStyle();
		if(postageStyle == 1 || province == "weiCang"){
			map.put("postage",df.format(postage));  
		}else{
			int shipType = gManage.getShipType();
			BigDecimal uWeight = gManage.getWeight();
			if(shipType == 2){
				postage = PostageMath.getHPosttage(uWeight, num);
				map.put("postage",df.format(postage));
			}else if(shipType == 1 || shipType == 3){
				postage = PostageMath.getBGPosttage(province, uWeight, num);
				map.put("postage",df.format(postage));
			}else{
				throw new MyParamException("立即购买全款邮寄方式参数有误!");
			}
		}
		tPayment = payment.add(postage);											//总价格=商品价格+邮费;
		map.put("tPayment",df.format(tPayment));
		PageData orderPD = new PageData();					//用于存储订单信息
		Session session = Jurisdiction.getSession();
		String customerName = (String)session.getAttribute("customerName");
		String orderID = DateUtil.getSdfTimes() + customerID;
		String goodsName = gManage.getGoodsDetails().getGoodsName();
		String goodsCode = gManage.getGoodsDetails().getGoodsCode();
		String goodsMap = gManage.getGoodsDetails().getMainMap();
		int deposit = gManage.getDeposit();
		int tradeType = gManage.getShipType();
		String consignee = shipAddr.getString("name");
		String consigneeMobile = shipAddr.getString("mobile");
		String consigneeIDCard = shipAddr.getString("id_card");
		String shipAddress = shipAddr.getString("province") + shipAddr.getString("city") + shipAddr.getString("detail_address");
		String orderType = "2";								//1微仓；2非微仓;3微仓销售订单
		if (!"4".equals(addressType)) {
			orderType = "1";
		}
		BigDecimal costPrice = gManage.getCostPrice();//商品单件成本
		BigDecimal cost = costPrice.multiply(new BigDecimal(String.valueOf(num)));//订单成本
		BigDecimal uWeight = gManage.getWeight();
		int salesManager = (int) daoSupport.findForObject("CustomerReadMapper.getSalesManagerByCustomerID", customerID);//获取客户销售经理
		orderPD.put("salesManager", salesManager);
		orderPD.put("orderID", orderID);
		orderPD.put("goodsID", goodsID);
		orderPD.put("goodsName", goodsName);
		orderPD.put("goodsCode", goodsCode);
		orderPD.put("costPrice", costPrice);
		orderPD.put("cost", cost);
		orderPD.put("goodsPrice", goodsPrice);
		orderPD.put("uWeight", uWeight);
		orderPD.put("goodsMap", goodsMap);
		orderPD.put("deposit", deposit);
		orderPD.put("goodsNum", buyNum);
		orderPD.put("tradeType", tradeType);
		orderPD.put("customerID", customerID);
		orderPD.put("consignee", consignee);
		orderPD.put("consigneeMobile", consigneeMobile);
		orderPD.put("consigneeIDCard", consigneeIDCard);
		orderPD.put("shipAddress", shipAddress);
		orderPD.put("orderType", orderType);
		orderPD.put("payType", 1);							//付款方式：1全款支付；2定金
		orderPD.put("totalMoney", df.format(tPayment));     //交易总额
		orderPD.put("shouldPayment", df.format(tPayment));  //应该支付金额
		orderPD.put("postage", postage);
		orderPD.put("postageStyle", postageStyle);
		orderPD.put("orderTime", DateUtil.getTime());
		orderPD.put("orderState", 1);
		orderPD.put("customerRemark", customerRemark);
		orderPD.put("orderLevel", 1);
		orderPD.put("customerName", customerName);
		orderPD.put("payTime", null);
		orderPD.put("payMoney", 0.00);
		daoSupport.save("OrderWriteMapper.addOrder2OM", orderPD);//生成订单（单个订单单个商品）
		daoSupport.save("OrderWriteMapper.addUGOrder2OG", orderPD);
		daoSupport.update("AreaAddrWriteMapper.updateShipAddrUsedTimes", saID);//更新收获地址使用次数
		orderPD.put("orderID", orderID);
		map.put("orderID", orderID);
		map.put("shipAddr",shipAddr);
		map.put("gManage",gManage);
		return map;
	}

	/**详情页【定金】立即购买结算信息
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> getDSettleFLB(String customerID, String dGoodsID, String dBuyNum) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd.put("goodsID", dGoodsID);
		GoodsManage gManage = (GoodsManage)daoSupport.findForObject("GManageReadMapper.getGMDForLB", pd);
		map.put("gManage",gManage);
		int rnum1 = gManage.getRnum1();
		int rnum2 = gManage.getRnum2();
		int rnum3 = gManage.getRnum3();
		int exw = gManage.getExw();
		int deposit = gManage.getDeposit();
		int num = Integer.valueOf(dBuyNum);
		map.put("num",num);
		DecimalFormat df = new DecimalFormat("0.00");
		BigDecimal price1 = gManage.getGoodsPrice1();
		BigDecimal price2 = gManage.getGoodsPrice2();
		BigDecimal goodsPrice = new BigDecimal("0");
		BigDecimal tPayment = new BigDecimal("0");//全款总额
		BigDecimal dPayment = new BigDecimal("0");//定金支付金额
		if(rnum1 <= num && num <= rnum2){
			BigDecimal n = new BigDecimal(String.valueOf(num));
			goodsPrice = price1;
			map.put("goodsPrice",df.format(goodsPrice));
			tPayment = n.multiply(price1);
			BigDecimal hundred = new BigDecimal("100");
			BigDecimal d = new BigDecimal(String.valueOf(deposit));
			dPayment = tPayment.multiply(d).divide(hundred,2,BigDecimal.ROUND_HALF_UP);
			map.put("goodsPrice",df.format(goodsPrice));
			map.put("tPayment",df.format(tPayment));
			map.put("dPayment",df.format(dPayment));
		}else if(exw == 1 && num > rnum2){
			BigDecimal n = new BigDecimal(String.valueOf(num));
			goodsPrice = price1;
			map.put("goodsPrice",df.format(goodsPrice));
			tPayment = n.multiply(price1);
			BigDecimal hundred = new BigDecimal("100");
			BigDecimal d = new BigDecimal(String.valueOf(deposit));
			dPayment = tPayment.multiply(d).divide(hundred,2,BigDecimal.ROUND_HALF_UP);
			map.put("goodsPrice",df.format(goodsPrice));
			map.put("tPayment",df.format(tPayment));
			map.put("dPayment",df.format(dPayment));
		}else if(rnum2 < num && num <= rnum3){
			BigDecimal n = new BigDecimal(String.valueOf(num));
			goodsPrice = price2;
			map.put("goodsPrice",df.format(goodsPrice));
			tPayment = n.multiply(price2);
			BigDecimal hundred = new BigDecimal("100");
			BigDecimal d = new BigDecimal(String.valueOf(deposit));
			dPayment = tPayment.multiply(d).divide(hundred);
			map.put("goodsPrice",df.format(goodsPrice));
			map.put("tPayment",df.format(tPayment));
			map.put("dPayment",df.format(dPayment));
		}
		PageData wc1Address = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getWC1Address", customerID);		//获取1号微仓地址
		map.put("wc1Address",wc1Address);
		return map;
	}

	/**情页立即购买【定金】结算页面提交订单--ajax预请求购买数量验证
	 * @param
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, String> checkAmountD(String goodsID, String buyNum) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String result = "";
		String msg = "";
		PageData pd = new PageData();
		pd.put("goodsID", goodsID);
		GoodsManage gManage = (GoodsManage)daoSupport.findForObject("GManageReadMapper.getGMDForLB", pd);
		int rnum1 = gManage.getRnum1();
		int rnum2 = gManage.getRnum2();
		int rnum3 = gManage.getRnum3();
		int exw = gManage.getExw();
		int goodsStock = gManage.getGoodsStock();
		int num = Integer.valueOf(buyNum);
		if(num > rnum3 || num < rnum1 ){
			result = "failed";
			msg = "购买数量不在规定购买范围！";
		}else if(num > goodsStock){
			result = "failed";
			msg = "购买数量超出库存！";
		}else if(exw == 1 && num > rnum2){
			result = "failed";
			msg = "购买数量不在规定购买范围！";
		}else {
			result = "success";
		}
		map.put("result", result);
		map.put("msg", msg);
		return map;
	}
	
	/**详情页【定金】立即购买提交订单
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> addOrderFLBDSettle(String customerID, String customerName, String goodsID, String buyNum,
													String customerRemark) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd.put("goodsID", goodsID);
		GoodsManage gManage = (GoodsManage)daoSupport.findForObject("GManageReadMapper.getGMDForLB", pd);
		map.put("gManage",gManage);
		int rnum1 = gManage.getRnum1();
		int rnum2 = gManage.getRnum2();
		int rnum3 = gManage.getRnum3();
		int exw = gManage.getExw();
		int deposit = gManage.getDeposit();
		int num = Integer.valueOf(buyNum);
		map.put("num",num);
		DecimalFormat df = new DecimalFormat("0.00");
		BigDecimal price1 = gManage.getGoodsPrice1();
		BigDecimal price2 = gManage.getGoodsPrice2();
		BigDecimal goodsPrice = new BigDecimal("0");
		BigDecimal tPayment = new BigDecimal("0");//全款总额
		BigDecimal dPayment = new BigDecimal("0");//定金支付金额
		if(rnum1 <= num && num <= rnum2){
			BigDecimal n = new BigDecimal(String.valueOf(num));
			goodsPrice = price1;
			map.put("goodsPrice",df.format(goodsPrice));
			tPayment = n.multiply(price1);
			BigDecimal hundred = new BigDecimal("100");
			BigDecimal d = new BigDecimal(String.valueOf(deposit));
			dPayment = tPayment.multiply(d).divide(hundred,2,BigDecimal.ROUND_HALF_UP);
			map.put("goodsPrice",df.format(goodsPrice));
			map.put("tPayment",df.format(tPayment));
			map.put("dPayment",df.format(dPayment));
		}if(exw == 1 && num > rnum2){
			BigDecimal n = new BigDecimal(String.valueOf(num));
			goodsPrice = price1;
			map.put("goodsPrice",df.format(goodsPrice));
			tPayment = n.multiply(price1);
			BigDecimal hundred = new BigDecimal("100");
			BigDecimal d = new BigDecimal(String.valueOf(deposit));
			dPayment = tPayment.multiply(d).divide(hundred,2,BigDecimal.ROUND_HALF_UP);
			map.put("goodsPrice",df.format(goodsPrice));
			map.put("tPayment",df.format(tPayment));
			map.put("dPayment",df.format(dPayment));
		}else if(rnum2 < num && num <= rnum3){
			BigDecimal n = new BigDecimal(String.valueOf(num));
			goodsPrice = price2;
			map.put("goodsPrice",df.format(goodsPrice));
			tPayment = n.multiply(price2);
			BigDecimal hundred = new BigDecimal("100");
			BigDecimal d = new BigDecimal(String.valueOf(deposit));
			dPayment = tPayment.multiply(d).divide(hundred);
			map.put("goodsPrice",df.format(goodsPrice));
			map.put("tPayment",df.format(tPayment));
			map.put("dPayment",df.format(dPayment));
		}
		PageData wc1Address = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getWC1Address", customerID);//获取1号微仓地址
		map.put("wc1Address",wc1Address);
		PageData orderPD = new PageData();					//用于存储订单信息
		String orderID = DateUtil.getSdfTimes() + customerID;
		String goodsName = gManage.getGoodsDetails().getGoodsName();
		String goodsCode = gManage.getGoodsDetails().getGoodsCode();
		String goodsMap = gManage.getGoodsDetails().getMainMap();
		int tradeType = gManage.getShipType();
		String consignee = wc1Address.getString("name");
		String consigneeMobile = wc1Address.getString("mobile");
		String consigneeIDCard = wc1Address.getString("id_card");
		String shipAddress = wc1Address.getString("province") + wc1Address.getString("city") + wc1Address.getString("detail_address");
		String orderType = "1";								//订单类型：1微仓；2非微仓;3微仓销售订单
		BigDecimal costPrice = gManage.getCostPrice();//商品单件成本
		BigDecimal hundred = new BigDecimal("100");
		BigDecimal d = new BigDecimal(String.valueOf(deposit));
		BigDecimal n = new BigDecimal(String.valueOf(num));
		BigDecimal cost = costPrice.multiply(n).multiply(d).divide(hundred);//订单成本
		BigDecimal uWeight = gManage.getWeight();
		int postageStyle = gManage.getPostageStyle();
		int salesManager = (int) daoSupport.findForObject("CustomerReadMapper.getSalesManagerByCustomerID", customerID);//获取客户销售经理
		orderPD.put("salesManager", salesManager);
		orderPD.put("orderID", orderID);
		orderPD.put("goodsID", goodsID);
		orderPD.put("goodsName", goodsName);
		orderPD.put("goodsCode", goodsCode);
		orderPD.put("goodsMap", goodsMap);
		orderPD.put("deposit", deposit);
		orderPD.put("costPrice", costPrice);
		orderPD.put("cost", cost);
		orderPD.put("goodsPrice", goodsPrice);
		orderPD.put("uWeight", uWeight);
		orderPD.put("goodsNum", buyNum);
		orderPD.put("tradeType", tradeType);
		orderPD.put("customerID", customerID);
		orderPD.put("consignee", consignee);
		orderPD.put("consigneeMobile", consigneeMobile);
		orderPD.put("consigneeIDCard", consigneeIDCard);
		orderPD.put("shipAddress", shipAddress);
		orderPD.put("orderType", orderType);
		orderPD.put("postageStyle", postageStyle);
		orderPD.put("payType", 2);							//付款方式：1全款支付；2为定金
		orderPD.put("totalMoney", df.format(tPayment));     //交易总额
		orderPD.put("shouldPayment", df.format(dPayment));  //应该支付金额
		orderPD.put("postage", 0);
		orderPD.put("orderTime", DateUtil.getTime());
		orderPD.put("orderState", 1);
		orderPD.put("customerRemark", customerRemark);
		orderPD.put("orderLevel", 1);
		orderPD.put("customerName", customerName);
		orderPD.put("payTime", null);
		orderPD.put("payMoney", 0.00);
		daoSupport.save("OrderWriteMapper.addOrder2OM", orderPD);			//生成订单（单个订单单个商品）
		daoSupport.save("OrderWriteMapper.addUGOrder2OG", orderPD);		
		map.put("orderID",orderID);
		return map;
	}

}
