package com.aurorascm.serviceImpl.myzone;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.InquiryGoods;
import com.aurorascm.entity.InquiryManage;
import com.aurorascm.entity.Page;
import com.aurorascm.service.myzone.InquiryService;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;

/**  
 * 询价 
 * @author SSY 2018-1-5
 * @version 1.0
 */

@Service("inquiryServiceImpl")
public class InquiryServiceImpl implements InquiryService{
	@Resource(name = "daoSupport")
	private DAO daoSupport;
	
	
	/**
	 * 条件分页查询询价单列表;
	 * @param Page page
	 * @return
	 * @throws Exception
	 * @author SSY 2018-1-5
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<InquiryManage> getInquiryList(Page page) throws Exception{
		return (List<InquiryManage>)daoSupport.findForList("InquiryReadMapper.getInquiryList",page);
	}
	
	
	/**
	 * 条件分页查询询价单数量;
	 * @param Page page
	 * @return int num
	 * @throws Exception
	 * @author SSY 2018-1-5
	 */
	@Override
	public int getInquiryNum(Page page) throws Exception{
		return (int)daoSupport.findForObject("InquiryReadMapper.getInquiryNum",page);
	}
	
	/**
	 * 查询符合询价状态的询价单数量;
	 * @param Page page
	 * @return  int num
	 * @throws Exception
	 * @author SSY 2018-1-5
	 */
	@Override
	public int getInquiryStateNum(int inquiryState) throws Exception{
		PageData pd = new PageData();
		pd.put("inquiryState", inquiryState);
		pd.put("customerID", Jurisdiction.getCustomerID());
		return (int)daoSupport.findForObject("InquiryReadMapper.getInquiryStateNum",pd);
	}
	
	
	
	/**
	 * 根据商品id 查询商品询价部分信息;
	 * @param  String goodsID
	 * @return   InquiryGoods inquiryGoods
	 * @throws Exception
	 * @author SSY 2017-12-21
	 */
	@Override
	public InquiryGoods getInquriryGoods(String goodsID) throws Exception {
		PageData goods = (PageData)daoSupport.findForObject("GoodsManageReadMapper.getInquriryGoods", goodsID);
		InquiryGoods inquiryGoods = new InquiryGoods();
		inquiryGoods.setGoodsBrand(goods.getString("brand_name"));
		inquiryGoods.setGoodsName(goods.getString("goods_name"));
		inquiryGoods.setGoodsCode(goods.getString("goods_code"));
		inquiryGoods.setGoodsNorm(goods.getString("norm"));
		inquiryGoods.setGoodsMap(goods.getString("main_map"));
		return inquiryGoods;
	}

	
	/**
	 * 获取所有货币符号;
	 * @param
	 * @return 
	 * @author SSY 2018-1-5
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getCurrency() throws Exception{
		return (List<PageData>) daoSupport.findForList("InquiryReadMapper.getCurrency");
	}
	
	 /**
	  * 提交保存询价单
	  * 		判断:--根据询价单的source_id判断,如果source_id为空,当前询价单source_id就是当前父id ()
	  * @param InquiryManage inquiryManage 
	  * @return 
	  * @throws Exception
	  * @author SSY 2018-1-5
	  */
	@Override
	public void addInquiry(InquiryManage inquiryManage) throws Exception{
		List<InquiryGoods> inquiryGoodsList = inquiryManage.getInquiryGoodsList();
		String sdfTime = DateUtil.getSdfTimes();
		String customerID = Jurisdiction.getCustomerID();
		Object salesManager = daoSupport.findForObject("CustomerReadMapper.getSalesManagerByCustomerID", customerID);
		inquiryManage.setInquiryID(customerID+sdfTime);
		inquiryManage.setSalesManager(salesManager!=null?(Integer)salesManager:888);
		inquiryManage.setInquiryTime(DateUtil.getTime());
		inquiryManage.setCustomerID(Integer.valueOf(Jurisdiction.getCustomerID()));
		inquiryManage.setCustomerName(Jurisdiction.getCustomerName());
		if (inquiryManage.getSourceID()==null) {
			inquiryManage.setInquiryTimes(1);
		}else{
			InquiryManage oldInquiry = this.getInquiry(inquiryManage.getSourceID());
			inquiryManage.setInquiryTimes(oldInquiry.getInquiryTimes()+1);
		}
		daoSupport.save("InquiryWriteMapper.addInquriy", inquiryManage);//新增询价单
		//批量新增询价商品表;
		for (InquiryGoods inquiryGoods : inquiryGoodsList) {
			inquiryGoods.setInquiryID(inquiryManage.getInquiryID());
			daoSupport.save("InquiryWriteMapper.addInquriyGoods", inquiryGoods);
		}
	}
	
	 /**
	  *  根据询价id查询询价单,重新询价,只查询部分信息;
	  * @param String inquiryID,Integer inquiryState,Integer inquiryGoodsState
	  * @return  InquiryManage inquiry
	  * @throws Exception
	  * @author SSY 2018-1-5
	  */
	@Override
	public InquiryManage getInquiry(String inquiryID) throws Exception {
		PageData pd = new PageData();
		pd.put("inquiryID", inquiryID);
		pd.put("customerID", Jurisdiction.getCustomerID());
		return (InquiryManage)daoSupport.findForObject("InquiryReadMapper.getInquiry",pd);
	}
	
	 /**
	  *  根据询价id查询询价单,查询有效询价商品信息;
	  * @param String inquiryID,Integer inquiryState,Integer inquiryGoodsState
	  * @return  InquiryManage inquiry
	  * @throws Exception
	  * @author SSY 2018-1-5
	  */
	@Override
	public InquiryManage getValidInquiry(String inquiryID,Integer inquiryState,Integer inquiryGoodsState) throws Exception {
		PageData pd = new PageData();
		pd.put("inquiryID", inquiryID);
		pd.put("customerID", Jurisdiction.getCustomerID());
		pd.put("inquiryGoodsState", inquiryGoodsState);
		pd.put("inquiryState", inquiryState);
		return (InquiryManage)daoSupport.findForObject("InquiryReadMapper.getValidInquiry",pd);
	}
	 

	
	
   
	/**
	 * ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ 
	 * **************************************我是分割线******************************************* 
	 * ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓
	 */
	
}
