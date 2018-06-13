package com.aurorascm.service;

import java.util.Map;


public interface BuySettleFDetailService {
	
	/**详情页【全款】立即购买结算信息
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getFSettleFLB(String customerID, String fGoodsID, String fBuyNum, String saID)throws Exception;

	/**详情页【全款】立即购买结算页面提交订单
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> addOrderFLBFSettle(String customerID, String goodsID, String buyNum, String saID, String customerRemark)throws Exception;

	/**详情页【定金】立即购买结算信息
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getDSettleFLB(String customerID, String dGoodsID, String dBuyNum)throws Exception;
	
	/**详情页立即购买【定金】结算页面提交订单
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> addOrderFLBDSettle(String customerID, String customerName, String goodsID, String buyNum, String customerRemark)throws Exception;

	/**情页立即购买【全款】结算页面提交订单--ajax预请求判断购买金额是否超额+购买数量验证
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> checkAmountF(String goodsID, String buyNum, String saID)throws Exception;
	
	/**情页立即购买【定金】结算页面提交订单--ajax预请求判断购买数量验证
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> checkAmountD(String goodsID, String buyNum)throws Exception;


}
