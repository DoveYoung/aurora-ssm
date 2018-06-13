package com.aurorascm.service;

import java.util.Map;

public interface BuySettleFCartService {
	
	
	/**购物车【全款】购买结算
	 * @param customerID bCartIDs hCartIDs gCartIDs saID
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getFSettleFSC(String customerID, String bCartIDs, String hCartIDs, String gCartIDs, String saID,int type)throws Exception;

	/**购物车【全款】购买结算页提交订单
	 * @param customerID,customerName,bCartIDs,hCartIDs,gCartIDs,saID,customerRemark
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> addFOrderFSC(String customerID, String customerName, String bCartIDs, String hCartIDs, 
												String gCartIDs, String saID, String customerRemark)throws Exception;

	/**购物车【定金】购买结算
	 * @param customerID bCartIDs hCartIDs gCartIDs
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getDSettleFSC(String customerID, String bCartIDs, String hCartIDs, String gCartIDs)throws Exception;

	/**购物车【定金】购买结算页提交订单
	 * @param customerID,customerName,bCartIDs,hCartIDs,gCartIDs,customerRemark
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> addDOrderFSC(String customerID, String customerName, String bCartIDs, String hCartIDs, 
												String gCartIDs, String customerRemark)throws Exception;

	/**购物车【全款】提交订单--ajax预请求判断购买金额是否超额
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> checkAmountFP(String bCartIDs, String hCartIDs, String gCartIDs, String saID)throws Exception;
	
	/**购物车【定金】提交订单--ajax预请求判断check购买数量是否在三段、库存范围内checkAmountDP
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> checkAmountDP(String bCartIDs, String hCartIDs, String gCartIDs)throws Exception;

}
