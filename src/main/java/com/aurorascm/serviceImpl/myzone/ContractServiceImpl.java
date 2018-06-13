package com.aurorascm.serviceImpl.myzone;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.ContractManage;
import com.aurorascm.entity.InquiryGoods;
import com.aurorascm.entity.InquiryManage;
import com.aurorascm.entity.Page;
import com.aurorascm.service.myzone.ContractService;
import com.aurorascm.util.CustomException;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/**
 * 描述:合同管理ServiceImpl
 * 
 * @author SSY 2017/8/16
 * @version 1.0
 */

@Service("contractServiceImpl")
public class ContractServiceImpl implements ContractService {

	@Resource(name = "daoSupport")
	private DAO daoSupport;


	/**
	 * @Title: getContractList 
	 * @Description: 查询符合条件的合同列表;
	 * @param   Page page
	 * @return List<ContractManage>  
	 * @author SSY
	 * @date 2018年1月11日 下午1:54:33
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ContractManage> getContractList(Page page) throws Exception {
		return (List<ContractManage>) daoSupport.findForList("ContractReadMapper.getContractList", page);
	}
	
	/**
	 * @Title: getContractNum 
	 * @Description:查询符合条件的合同数量;
	 * @param    Page page
	 * @return int  contractNum
	 * @author SSY
	 * @date 2018年1月11日 下午1:57:20
	 */
	@Override
	public int getContractNum(Page page) throws Exception{
		return (int) daoSupport.findForObject("ContractReadMapper.getContractNum", page);
	}
	
	/**
	 * @Title: getContractStateNum 
	 * @Description: 查询符合合同状态数量;
	 * @param   int[] contractStates
	 * @return int num 
	 * @author SSY
	 * @date 2018年1月11日 下午2:03:12
	 */
	@Override
	public int getContractStateNum(int[] contractStates) throws Exception{
		PageData pd = new PageData();
		pd.put("contractStates", contractStates);
		pd.put("customerID", Jurisdiction.getCustomerID());
		return (int) daoSupport.findForObject("ContractReadMapper.getContractStateNum", pd);
	}
	
	/**
	 * @Title: getValidInquiry 
	 * @Description: 查询询价单;
	 * @param  String inquiryID,Integer inquiryState,String inquiryGoodsID,Integer inquiryGoodsState
	 * @return ContractManage  
	 * @author SSY
	 * @date 2018年1月11日 下午2:14:49
	 */
	private InquiryManage getValidInquiry(String inquiryID,Integer inquiryState,Integer inquiryGoodsState) throws Exception {
		PageData pd = new PageData();
		pd.put("inquiryID", inquiryID);
		pd.put("customerID", Jurisdiction.getCustomerID());
		pd.put("inquiryState", inquiryState);
		pd.put("inquiryGoodsState", inquiryGoodsState);
		return (InquiryManage) daoSupport.findForObject("InquiryReadMapper.getValidInquiry",pd);
	}
	
	/**
	 * @Title: createContract 
	 * @Description: 采购生成合同单;
	 * @param    String inquiryID, String inquiryGoodsIDs, String company, String address
	 * @return int  
	 * @author SSY
	 * @date 2018年1月11日 下午2:10:13
	 */
	public void createContract(String inquiryID, String inquiryGoodsIDs, String company, String address) throws Exception{
		if (!Tools.notEmptys(inquiryGoodsIDs)) {
			throw new CustomException("没有选中商品!");
		}
		InquiryManage inquiry = this.getValidInquiry(inquiryID,2,2);//查询有效询价单,包含有效询价商品;
		if (inquiry==null||inquiry.getInquiryGoodsList().size()==0) {
			throw new CustomException("没有选中商品!");
		}
		String[] inquiryGoodsIDArray = inquiryGoodsIDs.split(",");
		List<InquiryGoods> inquiryGoodsList = inquiry.getInquiryGoodsList();
		String customerID = Jurisdiction.getCustomerID();
		String sdfTime = DateUtil.getSdfTimes();
		ContractManage contractManage = new ContractManage();
		contractManage.setContractID(sdfTime+customerID);
		contractManage.setSalesManager(inquiry.getSalesManager());
		contractManage.setAddress(address);
		contractManage.setCompany(company);
		contractManage.setCreateTime(DateUtil.getTime());
		contractManage.setCustomerEmail(inquiry.getCustomerEmail());
		contractManage.setCustomerID(inquiry.getCustomerID());
		contractManage.setCustomerName(inquiry.getCustomerName());
		contractManage.setCustomerMobile(inquiry.getCustomerMobile());
		contractManage.setInquiryID(inquiry.getInquiryID());
		BigDecimal totalPostage = new BigDecimal("0.00");
		BigDecimal contractMoney = new BigDecimal("0.00");
		for (Iterator iterator = inquiryGoodsList.iterator(); iterator.hasNext();) {
			InquiryGoods inquiryGoods = (InquiryGoods) iterator.next();
			if (Tools.contains(inquiryGoodsIDArray, inquiryGoods.getInquiryGoodsID())==-1) {//如果没有此询价商品;
				iterator.remove();
				continue;
			}
			inquiryGoods.setInquiryID(sdfTime+customerID);//替代合同id
			BigDecimal postage = inquiryGoods.getPostage();
			BigDecimal supplyPrice = inquiryGoods.getSupplyPrice();
			totalPostage = totalPostage.add(postage);
			contractMoney = contractMoney.add(supplyPrice);
		}
		//批量保存合同商品表;
		daoSupport.save("ContractWriteMapper.saveContractGoods", inquiryGoodsList);
		contractMoney = contractMoney.add(totalPostage);
		contractManage.setContractMoney(contractMoney);
		contractManage.setTotalPostage(totalPostage);
		//生成合同表;
		daoSupport.save("ContractWriteMapper.createContract", contractManage); 
	}
	
	/**
	 * @Title: getContractMoney 
	 * @Description: 查询合同应付总金额
	 * @param  String contractID
	 * @return BigDecimal  contractMoney
	 * @author SSY
	 * @date 2018年1月11日 下午4:44:40
	 */
	@Override
	public BigDecimal getContractMoney(String contractID) throws Exception{
		PageData pd = new PageData();
		pd.put("customerID", Jurisdiction.getCustomerID());
		pd.put("contractID", contractID);
		Object findForObject = daoSupport.findForObject("ContractReadMapper.getContractMoney", pd);
		return findForObject!=null?(BigDecimal)findForObject:null;
	}
 
	/**
	 * @Title: updatePayContract 
	 * @Description: 支付成功后，更新合同支付信息
	 * @param   pd:{payPath,tradeNo,payMoney,contractState,contractID}
	 * @return void  
	 * @author SSY
	 * @date 2018年1月11日 下午6:22:18
	 */
	@Override
	public void updatePayContract(PageData pd) throws Exception {
		pd.put("payTime",  DateUtil.getTime());
		daoSupport.update("ContractWriteMapper.updatePayContract", pd);
	}
	
	

	/**
	 * 根据合同id查询合同结算币种是否是人民币；
	 * 是返回true；否则返回false;
	 * @param contractID
	 * @return
	 * @exception
	 */
	@Override
	public Boolean getContractIDCurrencyName(String contractID) throws Exception {
		String currencyName = (String)daoSupport.findForObject("ContractReadMapper.getContractIDCurrencyName", contractID);
		return "人民币".equals(currencyName);
	}
	 
	/**
	 * 根据合同ID，获取合同金额。
	 * @param contractID
	 * @return
	 * @exception
	 */
	@Override
	public BigDecimal getContractMoneyByCID(String contractID) throws Exception {
		return (BigDecimal) daoSupport.findForObject("ContractReadMapper.getContractMoneyByCID", contractID);
	}

	/**
	 * 支付成功后，更新合同支付信息。
	 * @param pd
	 * @return
	 * @exception
	 */
	@Override
	public void updateContractPayInfo(PageData pd) throws Exception {
		pd.put("payTime",  DateUtil.getTime());
		daoSupport.update("ContractWriteMapper.updateContractPayInfo", pd);
	}
	
	/**
	 * ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ 
	 * **************************************我是分割线******************************************* 
	 * ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓
	 */
	 
}
