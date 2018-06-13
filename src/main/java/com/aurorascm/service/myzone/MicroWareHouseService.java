package com.aurorascm.service.myzone;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.aurorascm.entity.Page;
import com.aurorascm.util.PageData;

public interface MicroWareHouseService {
	
	/**获取客户微仓商品
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getCustomerMWGoods(Page page)throws Exception;
	
	/**客户个人中心/我的微仓  根据条件获取客户微仓商品数量
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public int getCustomerMWGNum(Page page) throws Exception;
	
	/**删除客户微仓中单个商品
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public String deleteMWGoods(PageData pd)throws Exception;
	
	/**客户个人中心/我的微仓 /微仓发货 初始化地址获取
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getShipAddress(String customerID)throws Exception;
	
	/**
	 * 微仓发货页面---请求获取微仓商品
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getCustomerMWG(String customerID) throws Exception;
	
	/**根据mwID获取客户微仓商品
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getGoodsByMwgID(String mwgID)throws Exception;
	
//	/**客户个人中心/我的微仓/微仓发货 生成订单--判断购买金额是否超额+数量是否超过库存
//	 * @param
//	 * @return
//	 * @throws Exception
//	 */
//	public Map<String, String> checkAmount(List<Map<String, String>> orderGoodsList , String saID)throws Exception;
	
	/**客户个人中心/我的微仓/微仓发货 生成订单
	 * @param List<Map<String, String>> orderGoodsList
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> addMWOrder(List<Map<String, String>> orderGoodsList , String saID, String customerID, String customerRemark)throws Exception;

	/**客户个人中心/我的微仓/订单付款--根据客户ID获取客户微仓订单
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getMWOrderID(Page page)throws Exception;
	
	/**客户个人中心/我的微仓/订单付款 --根据客户ID获取客户微仓订单数量
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public int getMWOrderIDNum(Page page) throws Exception;
	
	/**客户个人中心/我的微仓/订单付款--根据客户微仓订单ID获取订单对应的商品
	 * @param mwOrderID
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getGoodsByMWOrderID(String mwOrderID)throws Exception;
	
	/**客户个人中心/我的微仓/订单付款 --删除微仓订单
	 * @param String mwOrderID
	 * @return
	 * @throws Exception
	 */
	public void deleteMWOrder(String mwOrderID)throws Exception;
	
	/**客户个人中心/我的微仓/订单付款 --去结算
	 * @param String mwOrderID
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updateGetPayment(String mwOrderID)throws Exception;
	
	/**根据支付订单ID获取合并订单每个订单尾款之和
	 * @param payOrderID
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getMergeOrderSPByPayOID(String payOrderID) throws Exception;
	
	/**客户个人中心/我的微仓/订单付款 --合并支付成功后客户微仓订单数据转到我的订单
	 * @param String mwOrderID
	 * @return
	 * @throws Exception
	 */
	public void updateTransferMergeOrder(PageData pd)throws Exception;
	
	/**根据支付订单ID得到微仓订单ID
	 * @param payOrderID
	 * @return
	 * @throws Exception
	 */
	public List<String> getMwOrderIDByPayOID(String payOrderID)throws Exception;
	
	/**根据支付订单ID获取支付信息
	 * @param String payOrderID
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getPaymentByPID(String payOrderID)throws Exception;

 

}
