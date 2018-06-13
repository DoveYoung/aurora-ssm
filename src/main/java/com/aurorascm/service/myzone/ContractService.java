package com.aurorascm.service.myzone;

import java.math.BigDecimal;
import java.util.List;

import com.aurorascm.entity.ContractManage;
import com.aurorascm.entity.Page;
import com.aurorascm.util.PageData;

public interface ContractService {
	
	/**
	 * @Title: getContractList 
	 * @Description: 
	 * @param   Page page
	 * @return List<ContractManage>  
	 * @author SSY
	 * @date 2018年1月11日 下午1:54:33
	 */
	public List<ContractManage> getContractList(Page page)throws Exception;

	/**
	 * @Title: getContractNum 
	 * @Description:查询符合条件的合同数量;
	 * @param    Page page
	 * @return int  contractNum
	 * @author SSY
	 * @date 2018年1月11日 下午1:57:20
	 */
	public int getContractNum(Page page) throws Exception;

	/**
	 * @Title: getContractStateNum 
	 * @Description: 查询符合合同状态数量;
	 * @param   int[] contractStates
	 * @return int num 
	 * @author SSY
	 * @date 2018年1月11日 下午2:03:12
	 */
	public int getContractStateNum(int[] contractStates) throws Exception;
	

	/**
	 * @Title: createContract 
	 * @Description: 采购生成合同单;
	 * @param   String inquiryID, String inquiryGoodsIDs, String company, String address
	 * @return int  
	 * @author SSY
	 * @date 2018年1月11日 下午2:10:13
	 */
	public void createContract(String inquiryID, String inquiryGoodsIDs, String company, String address) throws Exception;

	/**
	 * @Title: getContractMoney 
	 * @Description: 查询合同应付总金额
	 * @param  String contractID
	 * @return BigDecimal  contractMoney
	 * @author SSY
	 * @date 2018年1月11日 下午4:44:40
	 */
	public BigDecimal getContractMoney(String contractID) throws Exception;

	/**
	 * @Title: updatePayContract 
	 * @Description: 支付成功后，更新合同支付信息
	 * @param   pd:{payPath,tradeNo,payMoney,contractState,contractID}
	 * @return void  
	 * @author SSY
	 * @date 2018年1月11日 下午6:22:18
	 */
	public void updatePayContract(PageData pd) throws Exception;
	
	/**
	 * 根据合同id查询合同结算币种是否是人民币；
	 * 是返回true；否则返回false;
	 * @param contractID
	 * @return
	 * @exception
	 */
	public Boolean getContractIDCurrencyName(String contractID) throws Exception;
	
	/**
	 * 根据合同ID，获取合同金额。
	 * @param contractID
	 * @return
	 * @exception
	 */
	public BigDecimal getContractMoneyByCID(String contractID) throws Exception;
	/**
	 * 支付成功后，更新合同支付信息。
	 * @param pd
	 * @return
	 * @exception
	 */
	public void updateContractPayInfo(PageData pd) throws Exception;
	
	
	  
	 

}
