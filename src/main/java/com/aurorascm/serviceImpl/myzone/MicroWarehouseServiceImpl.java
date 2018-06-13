package com.aurorascm.serviceImpl.myzone;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.Page;
import com.aurorascm.service.myzone.MicroWareHouseService;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.MyDataException;
import com.aurorascm.util.MyParamException;
import com.aurorascm.util.PageData;
import com.aurorascm.util.PostageMath;
import com.aurorascm.util.Tools;

/**
 * 描述:客户个人中心/我的微仓ServiceImpl
 * 
 * @author SSY 2017/8/16
 * @version 1.0
 */

@Service("microWarehouseServiceImpl")
public class MicroWarehouseServiceImpl implements MicroWareHouseService {
	
	@Resource(name = "daoSupport")
	private DAO daoSupport;

	
	/**
	 *获取客户微仓商品
	 * @param pd
	 * @return
	 * @exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getCustomerMWGoods(Page page) throws Exception {
		return (List<PageData>) daoSupport.findForList("MicroWarehouseReadMapper.getCustomerMWGoods", page);
	}

	/**客户个人中心/我的微仓  根据条件获取客户微仓商品数量
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getCustomerMWGNum(Page page) throws Exception {
		return (int) daoSupport.findForObject("MicroWarehouseReadMapper.getCustomerMWGNum", page);
	}

	/**删除客户微仓中单个商品
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public String deleteMWGoods(PageData pd) throws Exception {
		int goodsNum = (int)daoSupport.findForObject("MicroWarehouseReadMapper.getGoodsNum", pd);
		if(goodsNum > 0){//微仓中商品数量不为零不能删除
			return "failed1";
		}else{
			int orsderGoodsNum = (int)daoSupport.findForObject("MicroWarehouseReadMapper.getOrderGoodsNum", pd);
			if (orsderGoodsNum > 0) {//微仓订单中包含此商品不能删除，不然删除微仓订单，微仓中相应商品数据无法恢复。(2017.12.28/BYG/修复)
				return "failed2";
			} else {
				try{
					daoSupport.delete("MicroWarehouseWriteMapper.deleteMWGoods", pd);
					return "success";
				} catch (Exception e) {
					return "error";
				}
			}
		}
	}

	/**客户个人中心/我的微仓 /微仓发货 初始化地址获取
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> getShipAddress(String customerID) throws Exception {
		Map<String, Object> m = new HashMap<>();
	 	//获取1号微仓地址
		PageData wc1Address = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getWC1Address", customerID);
		//获取最常用的5个地址
		@SuppressWarnings("unchecked")
		List<PageData> shipAddress5 = (List<PageData>)daoSupport.findForList("AreaAddrReadMapper.get5ShipAddress", customerID);
		//获取最新的1个地址
		if (shipAddress5.size() > 5) {
		PageData newShipAddr = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getNewShipAddr", customerID);
		m.put("newShipAddr", newShipAddr);
		}
		m.put("wc1Address", wc1Address);
		m.put("shipAddress5", shipAddress5);
		return m;
	}
	
	/**
	 * 微仓发货页面---请求获取微仓商品
	 * @param customerID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getCustomerMWG(String customerID) throws Exception {
		return (List<PageData>) daoSupport.findForList("MicroWarehouseReadMapper.getCustomerMWG", customerID);
	}
	
	/**根据mwID获取客户微仓商品
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageData getGoodsByMwgID(String mwgID) throws Exception {
		return (PageData)daoSupport.findForObject("MicroWarehouseReadMapper.getGoodsByMwgID", mwgID);
	}

	/**客户个人中心/我的微仓/微仓发货 ----生成订单，微仓中相应商品数量减少。
	 * @param List<Map<String, String>> orderGoodsList
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, String> addMWOrder(List<Map<String, String>> orderGoodsList, String saID, String customerID, String customerRemark) throws Exception {
		Map<String, String> map = this.checkAmount(orderGoodsList, saID);
		String checkResult = map.get("result");
		if (!checkResult.equals("success")) {
			return map;
		} else {
			String mwOrderID = DateUtil.getSdfTimes() + customerID;
			BigDecimal tPostage = new BigDecimal(0);		//总邮费
			PageData shipAddr = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getShipAddr", saID);
			PageData pdo = new PageData();
			String shipAddress = shipAddr.get("province").toString() + shipAddr.get("city")+shipAddr.get("area")+shipAddr.get("detail_address");
			pdo.put("mwOrderID", mwOrderID);
			pdo.put("customerID", customerID);
			pdo.put("consignee", shipAddr.get("name"));
			pdo.put("shipAddress", shipAddress);
			pdo.put("consigneeMobile", shipAddr.get("mobile"));
			pdo.put("consigneeIDCard", shipAddr.get("id_card"));
			pdo.put("orderTime", DateUtil.getTime());
			pdo.put("customerRemark", customerRemark);
			daoSupport.save("MicroWarehouseWriteMapper.saveMWOrder", pdo);//保存微仓订单,邮费
			String provincePin = (String) shipAddr.get("province_pin");
			for (Object object : orderGoodsList) {
				@SuppressWarnings("unchecked")
				Map<String, String> goods= (Map<String, String>)object;
				String mwgID = goods.get("mwgID").trim();
				int sendNum = Integer.valueOf(goods.get("sendNum").trim());
				PageData uGoods = (PageData)daoSupport.findForObject("MicroWarehouseReadMapper.getGoodsByMwgID", mwgID);
				int goodsNum = (int)uGoods.get("goods_num");
				BigDecimal goodsPrice = new BigDecimal(uGoods.get("goods_price").toString());
				BigDecimal uWeight = new BigDecimal(uGoods.get("goods_weight").toString());
				int deposit = (int) uGoods.get("deposit");
				int payType = (int)uGoods.get("pay_type");
				BigDecimal advancePay = new BigDecimal(0);  //预付款
				BigDecimal finalPay = new BigDecimal(0); 	//尾款
				if(payType == 1){
					advancePay = goodsPrice.multiply(new BigDecimal(sendNum));
				}else{
					BigDecimal mSendNum = new BigDecimal(String.valueOf(sendNum));
					BigDecimal mDeposit = new BigDecimal(deposit);
					BigDecimal hundred = new BigDecimal("100");
					advancePay = goodsPrice.multiply(mSendNum).multiply(mDeposit).divide(hundred,2,BigDecimal.ROUND_HALF_UP);
					finalPay = goodsPrice.multiply(mSendNum).subtract(advancePay);
				}
				int tradeType = (int)uGoods.get("trade_type");
				int postageStyle = (int)uGoods.get("postage_style");
				BigDecimal postage = new BigDecimal(0); 	//邮费
				if (postageStyle == 2) {
					if(tradeType == 1 || tradeType == 3){
						postage = PostageMath.getBGPosttage(provincePin, uWeight, sendNum);
					}else if(tradeType == 2){
						postage = PostageMath.getHPosttage(uWeight, sendNum);
					}
				}
				tPostage = tPostage.add(postage);
				PageData pd = new PageData();
				pd.put("mwOrderID", mwOrderID);
				pd.put("mwgID", mwgID);
				pd.put("sendNum", sendNum);
				DecimalFormat df = new DecimalFormat("0.00"); 
				pd.put("advancePay",  df.format(advancePay));
				pd.put("finalPay",  df.format(finalPay));
				daoSupport.save("MicroWarehouseWriteMapper.saveMWOrderGoods", pd);//保存微仓订单商品
				int afterGoodsNum = goodsNum - sendNum;
				pd.put("afterGoodsNum", afterGoodsNum);
				daoSupport.update("MicroWarehouseWriteMapper.updateMWGoodsNum", pd);//更新客户微仓商品数量
			}
			pdo.put("tPostage", tPostage);
			daoSupport.update("MicroWarehouseWriteMapper.updateMWOPostage", pdo);//保存微仓订单的邮费;
			daoSupport.update("AreaAddrWriteMapper.updateShipAddrUsedTimes", saID);//更新收获地址使用次数
		}
		return map;
	}

	/**客户个人中心/我的微仓/订单付款--根据客户ID获取客户微仓订单
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getMWOrderID(Page page) throws Exception {
		return (List<PageData>) daoSupport.findForList("MicroWarehouseReadMapper.getMWOrderID", page);
	}

	/**客户个人中心/我的微仓/订单付款 --根据客户ID获取客户微仓订单数量
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getMWOrderIDNum(Page page) throws Exception {
		return (int) daoSupport.findForObject("MicroWarehouseReadMapper.getMWOrderIDNum", page);
	}

	/**客户个人中心/我的微仓/订单付款--根据客户微仓订单ID获取订单对应的商品
	 * @param mwOrderID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getGoodsByMWOrderID(String mwOrderID) throws Exception {
		return (List<PageData>) daoSupport.findForList("MicroWarehouseReadMapper.getGoodsByMWOrderID", mwOrderID);
	}

	/**客户个人中心/我的微仓/订单付款 --删除微仓订单----微仓中相应商品数量增加。
	 * @param String mwOrderID
	 * @return
	 * @throws Exception
	 */
	@Override
	public void deleteMWOrder(String mwOrderID) throws Exception {
		String ArrayMWOrderID[] = mwOrderID.split(",");
		//获取多个微仓订单包含商品信息
		@SuppressWarnings("unchecked")
		List<PageData> mwOrderGoods = (List<PageData>) daoSupport.findForList("MicroWarehouseReadMapper.getMwOrderGoods", ArrayMWOrderID);
		for(PageData mo : mwOrderGoods){
			int mwgID = (int) mo.get("mwg_id");
			PageData mwGoods = (PageData)daoSupport.findForObject("MicroWarehouseReadMapper.getGoodsByMwgID", String.valueOf(mwgID));
			int goodsNum = (int) mwGoods.get("goods_num");
			int sendNum = (int) mo.get("send_num");
			int afterGoodsNum = goodsNum + sendNum;
			PageData pd = new PageData();
			pd.put("afterGoodsNum", afterGoodsNum);
			pd.put("mwgID", mwgID);
			daoSupport.update("MicroWarehouseWriteMapper.updateMWGoodsNum", pd);
		}
		daoSupport.delete("MicroWarehouseWriteMapper.deleteMWOrder", ArrayMWOrderID);
	}

	/**客户个人中心/我的微仓/订单付款 --去结算
	 * @param String mwOrderID
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> updateGetPayment(String mwOrderID) throws Exception {
		Map<String, Object> m = new HashMap<>();
		Session session = Jurisdiction.getSession();
		String customerID =  (String)session.getAttribute("customerID");
		String ArrayMWOrderID[] = mwOrderID.split(",");
		BigDecimal tFinalPay = new BigDecimal(0);		//尾款总和
		BigDecimal tPostage = new BigDecimal(0);		//邮费总和
		int result = 1;//1支付金额为零，不需要跳转支付页面；2支付金额不为零，需要跳转到支付页面。
		@SuppressWarnings("unchecked")
		List<PageData> mwOrderGoods = (List<PageData>) daoSupport.findForList("MicroWarehouseReadMapper.getMwOrderGoods", ArrayMWOrderID);
		for(PageData mog : mwOrderGoods){
			BigDecimal finalPay = (BigDecimal) mog.get("final_pay");
			tFinalPay = tFinalPay.add(finalPay);
		}
		@SuppressWarnings("unchecked")
		List<PageData> mwOrder = (List<PageData>) daoSupport.findForList("MicroWarehouseReadMapper.getMwOrder", ArrayMWOrderID);
		for(PageData mo : mwOrder){
			BigDecimal postage = (BigDecimal) mo.get("postage");
			tPostage = tPostage.add(postage);
		}
		BigDecimal payment = tFinalPay.add(tPostage);			//支付金额
		BigDecimal  zero = new BigDecimal(0);
		int salesManager = (int) daoSupport.findForObject("CustomerReadMapper.getSalesManagerByCustomerID", customerID);//获取客户销售经理
		if (zero.compareTo(payment) == 0) {					//支付金额为零，不需要跳转支付页面，微仓订单更新到普通订单。
			for(PageData mo : mwOrder){
				PageData pdm = new PageData();
				pdm.put("orderLevel", 2);//1北极光订单；2北极光客户订单
				pdm.put("customerID", mo.get("customer_id"));
				pdm.put("customerName", mo.get("customer_name"));
				pdm.put("consignee", mo.get("consignee"));
				pdm.put("shipAddress", mo.get("ship_address"));
				pdm.put("consigneeMobile", mo.get("consignee_mobile"));
				pdm.put("consigneeIDCard", mo.get("consignee_id_card"));
				pdm.put("orderType", 2);//订单类型：1微仓；2非微仓
				pdm.put("payType", 1);//付款方式：1全款支付；2定金
				String orderID = (String) mo.get("mw_order_id");
				pdm.put("orderID", orderID);
				//订单成本计算
				BigDecimal cost = new BigDecimal("0");
				@SuppressWarnings("unchecked")
				List<PageData> orderGoods = (List<PageData>) daoSupport.findForList("MicroWarehouseReadMapper.getMwOrderGoodsForCost", orderID);
				for(PageData og : orderGoods){
					BigDecimal costPrice = (BigDecimal) og.get("cost_price");
					BigDecimal sendNum = new BigDecimal(String.valueOf(og.get("send_num")));
					BigDecimal gCost = new BigDecimal("0");
					gCost = costPrice.multiply(sendNum);
					cost = cost.add(gCost);
				}
				pdm.put("cost", cost);
				//订单金额计算
				BigDecimal postage = (BigDecimal) mo.get("postage");
				pdm.put("postage", postage);
				BigDecimal finalPay = (BigDecimal) daoSupport.findForObject("MicroWarehouseReadMapper.getOrderFPByOID", orderID);
				BigDecimal uoPayment = finalPay.add(postage);//单订单支付金额
				pdm.put("payMoney", uoPayment);       //实际支付金额
				pdm.put("shouldPayment", uoPayment);  //应该支付金额
				pdm.put("totalMoney", uoPayment);	  //总金额金额
				pdm.put("payTime", DateUtil.getTime());
				pdm.put("orderTime", mo.get("order_time"));
				pdm.put("orderState", 2);//2已支付
				pdm.put("customerRemark", mo.get("customer_remark"));
				pdm.put("salesManager", salesManager);
				daoSupport.save("OrderWriteMapper.addMWOrder2OM", pdm);
			}
			@SuppressWarnings("unchecked")
			List<PageData> mwOrderGoodsDetails = (List<PageData>) daoSupport.findForList("MicroWarehouseReadMapper.getMWOrderGDByOID", ArrayMWOrderID);
			for(PageData mog : mwOrderGoodsDetails){
				PageData pdg = new PageData();
				pdg.put("orderID", mog.get("mw_order_id"));
				pdg.put("goodsID", mog.get("goods_id"));
				pdg.put("goodsName", mog.get("goods_name"));
				pdg.put("goodsCode", mog.get("goods_code"));
				pdg.put("goodsNum", mog.get("send_num"));
				pdg.put("costPrice", mog.get("cost_price"));
				pdg.put("goodsPrice", mog.get("goods_price"));
				pdg.put("deposit", mog.get("deposit"));
				pdg.put("goodsMap", mog.get("goods_map"));
				pdg.put("uWeight", mog.get("goods_weight"));
				pdg.put("payType", mog.get("pay_type"));
				pdg.put("tradeType", mog.get("trade_type"));
				pdg.put("postageStyle", mog.get("postage_style"));
				daoSupport.save("OrderWriteMapper.addUGOrder2OG", pdg);
				// @author SSY 更新用户已经发出的微仓周转时间
				pdg.put("customerID", mog.get("customer_id"));
				pdg.put("turnoverDays", mog.get("turnoverDays"));
				daoSupport.update("StatisticsCustomerWriteMapper.updateMWTurnoverTime", pdg);
			}
			daoSupport.delete("MicroWarehouseWriteMapper.deleteMWOrder", ArrayMWOrderID);
		}else {													//支付金额不为零，需要跳转到支付页面
			result = 2;
			//生成支付用订单合并ID，并更新到每个订单。
			String payOrderID = DateUtil.getSdfTimes() + customerID;
			PageData pdp = new PageData();
			pdp.put("payOrderID", payOrderID);
			pdp.put("ArrayMWOrderID", ArrayMWOrderID);
			daoSupport.update("MicroWarehouseWriteMapper.updateMWPayOrderID", pdp);
			m.put("payOrderID", payOrderID);
			m.put("mwOrderGoods", mwOrderGoods);
			m.put("mwOrder", mwOrder);
			m.put("payment", payment);
		}
		m.put("result", result);
		return m;
	}

	/**客户个人中心/我的微仓/订单付款 --支付成功后客户微仓订单数据转到我的订单，并删除微仓中相应的订单。
	 * @param String mwOrderID
	 * @return
	 * @throws Exception
	 */

	@Override
	public void updateTransferMergeOrder(PageData pd) throws Exception {
			String payOrderID = pd.getString("payOrderID");
			String tradeNo = pd.getString("tradeNo");
			String orderState = pd.getString("orderState");
			String payPath = pd.getString("payPath");
			Session session = Jurisdiction.getSession();
			String customerID =  (String)session.getAttribute("customerID");
			@SuppressWarnings("unchecked")
			List<PageData> mwOrder = (List<PageData>) daoSupport.findForList("MicroWarehouseReadMapper.getMwOrderMByPID", payOrderID);
			for(PageData mo : mwOrder){
				PageData pdm = new PageData();
				pdm.put("outTradeNo", payOrderID);
				pdm.put("tradeNo", tradeNo);
				pdm.put("payPath", payPath);//付款渠道：1支付宝；2微信；3银联
				pdm.put("orderLevel", 2);//1北极光订单；2北极光客户订单
				pdm.put("customerID", mo.get("customer_id"));
				pdm.put("customerName", mo.get("customer_name"));
				pdm.put("consignee", mo.get("consignee"));
				pdm.put("shipAddress", mo.get("ship_address"));
				pdm.put("consigneeMobile", mo.get("consignee_mobile"));
				pdm.put("consigneeIDCard", mo.get("consignee_id_card"));
				pdm.put("orderType", 3);//1微仓；2非微仓;3微仓销售订单
				pdm.put("payType", 1);//付款方式：1全款支付；2定金
				String orderID = (String) mo.get("mw_order_id");
				pdm.put("orderID", orderID);
				//订单成本计算
				BigDecimal cost = new BigDecimal("0");
				@SuppressWarnings("unchecked")
				List<PageData> orderGoods = (List<PageData>) daoSupport.findForList("MicroWarehouseReadMapper.getMwOrderGoodsForCost", orderID);
				int salesManager = (int) daoSupport.findForObject("CustomerReadMapper.getSalesManagerByCustomerID", customerID);//获取客户销售经理
				for(PageData og : orderGoods){
					BigDecimal costPrice = (BigDecimal) og.get("cost_price");
					int payType = (int) og.get("pay_type");
					BigDecimal sendNum = new BigDecimal(String.valueOf(og.get("send_num")));
					BigDecimal gCost = new BigDecimal("0");
					if (payType == 2) {//定金成本第一次支付时已算定金占比部分，二次算剩余成本；
						BigDecimal hundred = new BigDecimal("100");
						BigDecimal deposit = new BigDecimal(String.valueOf(og.get("deposit")));//非BigDecimal类型的不能强制转换成BigDecimal
						BigDecimal ds = hundred.subtract(deposit);
						gCost = costPrice.multiply(sendNum).multiply(ds).divide(hundred);
					}else{//全款
						gCost = costPrice.multiply(sendNum);
					}
					cost = cost.add(gCost);
				}
				pdm.put("cost", cost);
				//订单金额计算
				BigDecimal postage = (BigDecimal) mo.get("postage");
				pdm.put("postage", postage);
				BigDecimal finalPay = (BigDecimal) daoSupport.findForObject("MicroWarehouseReadMapper.getOrderFPByOID", orderID);
				BigDecimal payment = finalPay.add(postage);
				pdm.put("payMoney", payment);
				pdm.put("shouldPayment", payment);  //应该支付金额
				pdm.put("totalMoney", payment);
				pdm.put("payTime", DateUtil.getTime());
				pdm.put("orderTime", mo.get("order_time"));
				pdm.put("orderState", orderState);//2已支付
				pdm.put("customerRemark", mo.get("customer_remark"));
				pdm.put("salesManager", mo.get("salesManager"));
				daoSupport.save("OrderWriteMapper.addMWOrder2OM", pdm);
			}
			@SuppressWarnings("unchecked")
			List<PageData> mwOrderGoods = (List<PageData>) daoSupport.findForList("MicroWarehouseReadMapper.getMWOrderGDByPID", payOrderID);
			for(PageData mog : mwOrderGoods){
				PageData pdg = new PageData();
				pdg.put("orderID", mog.get("mw_order_id"));
				pdg.put("goodsID", mog.get("goods_id"));
				pdg.put("goodsName", mog.get("goods_name"));
				pdg.put("goodsCode", mog.get("goods_code"));
				pdg.put("goodsNum", mog.get("send_num"));
				pdg.put("costPrice", mog.get("cost_price"));
				pdg.put("goodsPrice", mog.get("goods_price"));
				pdg.put("deposit", mog.get("deposit"));
				pdg.put("goodsMap", mog.get("goods_map"));
				pdg.put("uWeight", mog.get("goods_weight"));
				pdg.put("payType", mog.get("pay_type"));
				pdg.put("postageStyle", mog.get("postage_style"));
				pdg.put("tradeType", mog.get("trade_type"));
				daoSupport.save("OrderWriteMapper.addUGOrder2OG", pdg);
				// @author SSY 更新用户已经发出的微仓周转时间
				pdg.put("customerID", mog.get("customer_id"));
				pdg.put("turnoverDays", mog.get("turnoverDays"));
				daoSupport.update("StatisticsCustomerWriteMapper.updateMWTurnoverTime", pdg);
			}
			daoSupport.delete("MicroWarehouseWriteMapper.deleteMWOrderAfterPay", payOrderID);
	}
	

	/**根据支付订单ID获取合并订单每个订单尾款之和
	 * @param payOrderID
	 * @return
	 * @throws Exception
	 */
	@Override
	public BigDecimal getMergeOrderSPByPayOID(String payOrderID) throws Exception {
		return (BigDecimal) daoSupport.findForObject("MicroWarehouseReadMapper.getMergeOrderSPByPayOID", payOrderID);
	}

	/**根据支付订单ID得到微仓订单ID
	 * @param payOrderID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getMwOrderIDByPayOID(String payOrderID) throws Exception {
		return (List<String>) daoSupport.findForList("MicroWarehouseReadMapper.getMwOrderIDByPayOID", payOrderID);
	}

	/**根据支付订单ID获取支付信息
	 * @param String payOrderID
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> getPaymentByPID(String payOrderID) throws Exception {
		BigDecimal shouldPay = (BigDecimal) daoSupport.findForObject("MicroWarehouseReadMapper.getMergeOrderSPByPayOID", payOrderID);
		@SuppressWarnings("unchecked")
		List<PageData> mwOrderGoods = (List<PageData>) daoSupport.findForList("MicroWarehouseReadMapper.getMWOrderByPID", payOrderID);
		Map<String, Object> m = new HashMap<>();
		m.put("shouldPay", shouldPay);
		m.put("mwOrderGoods", mwOrderGoods);
		return m;
	}

	
	/**客户个人中心/我的微仓/微仓发货 生成订单--判断购买金额是否超额+数量是否超过库存
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> checkAmount(List<Map<String, String>> orderGoodsList, String saID) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String result = "";
		String msg = "";
		PageData shipAddr = (PageData)daoSupport.findForObject("AreaAddrReadMapper.getShipAddr", saID);
		String provincePin = (String) shipAddr.get("province_pin");
		BigDecimal bMoney = new BigDecimal(0);//保税仓商品应付金额
		BigDecimal hMoney = new BigDecimal(0);//海外直邮商品应付金额
		for (Object object : orderGoodsList) {
			@SuppressWarnings("unchecked")
			Map<String, String> goods= (Map<String, String>)object;
			String mwgID = goods.get("mwgID").trim();
			if (Tools.notEmptys(mwgID) && Tools.notEmptys(goods.get("sendNum")) && Integer.valueOf(goods.get("sendNum").trim()) > 0){
				PageData uGoods = (PageData)daoSupport.findForObject("MicroWarehouseReadMapper.getGoodsByMwgIDFC", mwgID);
				int goodsNum = (int)uGoods.get("goods_num");
				int sendNum = Integer.valueOf(goods.get("sendNum").trim());
				if (goodsNum >= sendNum) {
					int tradeType = (int)uGoods.get("trade_type");
					if (tradeType != 3) {
						BigDecimal bdSendNum = new BigDecimal(goods.get("sendNum").trim());
						BigDecimal goodsPrice = new BigDecimal(uGoods.get("goods_price").toString());
						BigDecimal uWeight = new BigDecimal(uGoods.get("goods_weight").toString());
						BigDecimal money = goodsPrice.multiply(bdSendNum);
						int postageStyle = (int)uGoods.get("postage_style");
						if (postageStyle == 1) {//包邮
							if (tradeType == 1) {
								bMoney = bMoney.add(money);
							}else if (tradeType == 2) {
								hMoney = hMoney.add(money);
							}
						}else {
							if (tradeType == 1) {
								BigDecimal postage = PostageMath.getBGPosttage(provincePin, uWeight, sendNum);
								bMoney = bMoney.add(money).add(postage);
							}else if (tradeType == 2) {
								BigDecimal postage = PostageMath.getHPosttage(uWeight, sendNum);
								hMoney = hMoney.add(money).add(postage);
							}
						}
					}
					BigDecimal twoT = new BigDecimal(2000);//保税仓商品总额不能超过2000元
					BigDecimal TwentyT = new BigDecimal("20000");//海外直邮商品总额不能超过20000元
					if (bMoney.compareTo(twoT) == -1 && hMoney.compareTo(TwentyT) == -1){
						result = "success";
					}else {
						if(bMoney.compareTo(twoT) == 1 && hMoney.compareTo(TwentyT) == 1){
							msg = "保税仓商品全款总额不能大于2000.00元！海外直邮商品全款总额不能大于20000.00元";
						}else if(bMoney.compareTo(twoT) == 1 ){
							msg = "保税仓商品全款总额不能大于2000.00元！";
						}else{
							msg = "海外直邮商品全款总额不能大于20000.00元";
						}
						result = "failed";
					}
				} else {
					msg = "发货数量超出微仓可用数量！";
					result = "failed";
				}
			}else {
				msg = "参数有误！";
				result = "failed";
			}
		}
		map.put("result", result);
		map.put("msg", msg);
		return map;
	}

	
}
