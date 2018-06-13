package com.aurorascm.service;

import java.util.List;

import com.aurorascm.util.PageData;

public interface AreaAddrService {
	
	/**商品详情页获取省份
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getProvince()throws Exception;
	
	/**根据CustomerID和关键词搜索客户收获地址
	 * @param String[] cartID
	 * @return
	 * @throws Exception
	 */
	public List<PageData> likeShipAddr(PageData pd)throws Exception;
	
	/**根据CustomerID得到客户使用次数最高的五个收获地址
	 * @param customerID
	 * @return
	 * @throws Exception
	 */
	public List<PageData> get5ShipAddress(String customerID)throws Exception;
		
	/**根据CustomerID得到客户最新1个收获地址
	 * @param customerID
	 * @return
	 * @throws Exception
	 */
	public PageData getNewShipAddr(String customerID)throws Exception;
	
	/**根据CustomerID 和地址类型得到客户1号微仓地址
	 * @param customerID
	 * @return
	 * @throws Exception
	 */
	public PageData getWC1Address(String customerID)throws Exception;
	
	/**根据收货地址ID:saID得到客户收获地址
	 * @param String[] cartID
	 * @return
	 * @throws Exception
	 */
	public PageData getShipAddr(String saID)throws Exception;
		
	/**新增收获地址
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public void addShipAddr(PageData pd)throws Exception;
	
	/**修改收获地址
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public void updateShipAddr(PageData pd)throws Exception;
	
	/**根据父级区域ID搜索相应地区
	 * @param String areaID
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getUArea(String areaID)throws Exception;


	

}
