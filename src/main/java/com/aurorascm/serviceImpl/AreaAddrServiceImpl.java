package com.aurorascm.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.service.AreaAddrService;
import com.aurorascm.util.PageData;

/**
 * 描述:客户登录注册ServiceImpl
 * 创建:BYG 2017/5/25
 * 修改:
 * @version 1.0
 */
@Service("areaAddrServiceImpl")
public class AreaAddrServiceImpl implements AreaAddrService{
	
	@Resource(name = "daoSupport")
	private DAO daoSupport;

	/**商品详情页获取省份
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getProvince() throws Exception {
		return (List<PageData>)daoSupport.findForList("AreaAddrReadMapper.getProvince");
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
	
	/**修改收获地址
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public void updateShipAddr(PageData pd) throws Exception {
		daoSupport.save("AreaAddrWriteMapper.updateShipAddr", pd);
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

	/**根据父级区域ID搜索相应地区
	 * @param String areaID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getUArea(String areaID) throws Exception {
		return (List<PageData>)daoSupport.findForList("AreaAddrReadMapper.getUArea", areaID);
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
