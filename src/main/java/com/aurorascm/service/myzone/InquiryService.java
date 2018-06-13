package com.aurorascm.service.myzone;

import java.util.List;

import com.aurorascm.entity.InquiryGoods;
import com.aurorascm.entity.InquiryManage;
import com.aurorascm.entity.Page;
import com.aurorascm.util.PageData;

/** 
 * 询价
 * @author SSY 2018-1-5
 * @version 1.0
 */

public interface InquiryService {
	
	

	/**
	 * 条件分页查询询价单列表;
	 * @param Page page
	 * @return
	 * @throws Exception
	 * @author SSY 2018-1-5
	 */
	public List<InquiryManage> getInquiryList(Page page) throws Exception;
	
	/**
	 * 条件分页查询询价单数量;
	 * @param Page page
	 * @return int num
	 * @throws Exception
	 * @author SSY 2018-1-5
	 */
	public int getInquiryNum(Page page) throws Exception;
	
	/**
	 * 查询符合询价状态的询价单数量;
	 * @param Page page
	 * @return  int num
	 * @throws Exception
	 * @author SSY 2018-1-5
	 */
	public int getInquiryStateNum(int inquiryState) throws Exception;
	
	
	/**
	 * 根据商品id 查询商品基本信息,部分信息;
	 * @param  String goodsID
	 * @return InquiryGoods inquiryGoods
	 * @throws Exception
	 * @author SSY 2017-12-21
	 */
	public InquiryGoods getInquriryGoods(String goodsID) throws Exception;
	
	/**
	 * 获取所有货币符号;
	 * @param
	 * @return 
	 * @author SSY 2017-12-21
	 */
	public List<PageData> getCurrency() throws Exception;

	
	 /**
	  * 根据询价单inquirySID查询询价单的状态;
	  * @param InquiryManage inquiryManage 
	  * @return 
	  * @throws Exception
	  * @author SSY 2018-1-5
	  */
	public void addInquiry(InquiryManage inquiryManage) throws Exception;

	 /**
	  *  根据询价id查询询价单,重新询价;
	  * @param String inquiryID 
	  * @return  InquiryManage inquiry
	  * @throws Exception
	  * @author SSY 2018-1-5
	  */
	public InquiryManage getInquiry(String inquiryID) throws Exception;

	 /**
	  *  根据询价id查询询价单,查询有效询价商品信息;
	  * @param String inquiryID,Integer inquiryState,Integer inquiryGoodsState
	  * @return  InquiryManage inquiry
	  * @throws Exception
	  * @author SSY 2018-1-5
	  */
	public InquiryManage getValidInquiry(String inquiryID,Integer inquiryState,Integer inquiryGoodsState) throws Exception;
	
	


	
	/**
	 * ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ ↑ 
	 * **************************************我是分割线******************************************* 
	 * ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓
	 */
}
