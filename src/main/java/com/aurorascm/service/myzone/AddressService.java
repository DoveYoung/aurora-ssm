package com.aurorascm.service.myzone;

import java.util.List;

import com.aurorascm.entity.Page;
import com.aurorascm.util.PageData;

public interface AddressService {
	
	
	/**
	 * 查询个人收货地址总数；
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public int getAddressNum(Page page)throws Exception;
	
	/**
	 * 查询个人收获地址总数，除去微仓地址和最新地址
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public int getShipAddrNum(PageData pd)throws Exception;
	
	/**
	 * 分页查询个人收货地址
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getAddress(Page page)throws Exception;
 
	/**
	 * 保存修改之后的收货地址
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public void updateShipAddr(PageData pd)throws Exception;
	
	/**
	 * 删除收货地址;
	 * @param  pd(个人收货地址)
	 */
	public void deleteShipAddr(PageData pd)throws Exception;
	
	/**根据CustomerID+页码得到客户5个收获地址，按使用次数由高到低排序。
	 * @param customerID
	 * @param pageNum
	 * @return
	 * @throws Exception
	 */
	public List<PageData> get5ShipAddress(PageData pd)throws Exception;
	
	
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
	
	
}
