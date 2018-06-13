package com.aurorascm.serviceImpl.myzone;


import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.Page;
import com.aurorascm.service.myzone.AddressService;
import com.aurorascm.util.PageData;

/**
 * 描述:个人中心--我的关注ServiceImpl
 * 
 * @author SSY 2017/8/16
 * @version 1.0
 */

@Service("addressServiceImpl")
public class AddressServiceImpl implements AddressService {

	@Resource(name = "daoSupport")
	private DAO daoSupport;

	/**
	 * 查询个人收货地址总数；
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public int getAddressNum(Page page)throws Exception{
		return (int)daoSupport.findForObject("AreaAddrReadMapper.getAddressNum", page);
	}
	
	/**
	 * 查询个人收获地址总数，除去微仓地址和最新地址
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public int getShipAddrNum(PageData pd)throws Exception{
		return (int)daoSupport.findForObject("AreaAddrReadMapper.getShipAddrNum", pd);
	}
	
	/**
	 * 分页查询个人收货地址
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getAddress(Page page)throws Exception{
		return (List<PageData>)daoSupport.findForList("AreaAddrReadMapper.getAddress", page);
	}
	
	/**
	 * 保存修改之后的收货地址
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public void updateShipAddr(PageData pd)throws Exception{
		daoSupport.update("AreaAddrWriteMapper.updateShipAddr", pd);
	}
	
	
	/**
	 * 删除收货地址;(支持批量操作)
	 * @param  pd(个人收货地址)
	 */
	public void deleteShipAddr(PageData pd)throws Exception{
		daoSupport.delete("AreaAddrWriteMapper.deleteShipAddr", pd);
	}
	
	/**根据CustomerID+页码得到客户4个收获地址，按使用次数由高到低排序。
	 * @param customerID
	 * @param pageNum
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> get5ShipAddress(PageData pd) throws Exception {
		return (List<PageData>)daoSupport.findForList("AreaAddrReadMapper.get4ShipAddress", pd);
	}
	
	/**根据CustomerID和关键词搜索客户收获地址
	 * @param String[] cartID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> likeShipAddr(PageData pd) throws Exception {
		return (List<PageData>)daoSupport.findForList("AreaAddrReadMapper.likeShipAddr", pd);
	}
	
	/**根据CustomerID得到客户使用次数最高的五个收获地址
	 * @param String[] cartID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> get5ShipAddress(String customerID) throws Exception {
		return (List<PageData>)daoSupport.findForList("AreaAddrReadMapper.get5ShipAddress", customerID);
	}

	/**根据CustomerID得到客户最新1个收获地址
	 * @param String[] cartID
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageData getNewShipAddr(String customerID) throws Exception {
		return (PageData)daoSupport.findForObject("AreaAddrReadMapper.getNewShipAddr", customerID);
	}

	/**新增收获地址
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public void addShipAddr(PageData pd) throws Exception {
		daoSupport.save("AreaAddrWriteMapper.addShipAddr", pd);
	}

	/**根据收货地址ID:saID得到客户收获地址
	 * @param String[] cartID
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageData getShipAddr(String saID) throws Exception {
		return (PageData)daoSupport.findForObject("AreaAddrReadMapper.getShipAddr", saID);
	}
	
	/**根据CustomerID 和地址类型得到客户1号微仓地址
	 * @param customerID
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageData getWC1Address(String customerID) throws Exception {
		return (PageData)daoSupport.findForObject("AreaAddrReadMapper.getWC1Address", customerID);
	}
	
	 
}
