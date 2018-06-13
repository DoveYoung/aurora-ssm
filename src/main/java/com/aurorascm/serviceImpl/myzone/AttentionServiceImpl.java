package com.aurorascm.serviceImpl.myzone;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.Page;
import com.aurorascm.service.myzone.AttentionService;
import com.aurorascm.util.*;

/**
 * 描述:个人中心--我的关注ServiceImpl
 * 
 * @author SSY 2017/8/16
 * @version 1.0
 */

@Service("attentionServiceImpl")
public class AttentionServiceImpl implements AttentionService {

	@Resource(name = "daoSupport")
	private DAO daoSupport;

	  /**
	   * 根据关注的品牌查询销量最高的上架商品;
	   * @param careBrand
	   */
	@Override
	public Map<String,List<PageData>> getAttention(Page page) throws Exception{
		String customerID = Jurisdiction.getCustomerID();
		String attentionBrand = getAttentionBrand(customerID);
		page.setCurrentPage(page.getCurrentPage()==0?1:page.getCurrentPage());
		page.setPageSize(10);
		String[] brandID = attentionBrand.split(",");
		page.setTotalRecord(brandID.length);
		Map<String,List<PageData>> careBrandGoods = new HashMap<String,List<PageData>>();//存储关注品牌下推荐商品
		for (int i = page.getFromIndex(); i < page.getFromIndex()+10; i++) {
			if (brandID.length<=i) {
				break;
			}
			List<PageData> goodsBybrandID = getGoodsBybrandID(brandID[i]);
			if (goodsBybrandID==null||goodsBybrandID.size()<=0) {
				List<PageData> brandPbByID = getBrandPbByID(brandID[i]);
				goodsBybrandID.addAll(brandPbByID);
			}
			careBrandGoods.put(brandID[i], goodsBybrandID);
		}
		return careBrandGoods;
	}

	/**
	 * 根据品牌id查询品牌信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getBrandPbByID(String brandID) throws Exception{
		return  (List<PageData>) daoSupport.findForList("BrandReadMapper.getBrandPdByID", brandID);
	}
	
	/**
	 * 根据品牌id查询销量最高的4个上架的商品;
	 * @param brandID
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getGoodsBybrandID(String brandID) throws Exception{
		return  (List<PageData>) daoSupport.findForList("GManageReadMapper.getGoodsBybrandID", brandID);
	}
	
	/**
	 * 根据用户id查询关注的拼接品牌id;
	 * @param customerID
	 */
	@Override
	public String getAttentionBrand(String customerID) throws Exception{
		return (String) daoSupport.findForObject("CustomerReadMapper.getAttentionBrand", customerID);
	}
	
	/**
	 * 添加品牌关注
	 * @param brandID ,customerID 
	 */
	@Override
	public int addBrandCare(PageData pd) throws Exception{
		String bCare = (String)daoSupport.findForObject("CustomerReadMapper.getAttentionBrand", pd);
		if (!Tools.notEmptys(bCare)) {//如果是空,
			daoSupport.update("CustomerWriteMapper.addBrandCare", pd);
			daoSupport.update("BrandWriteMapper.addCareNum", pd);
		} else{
			int a = bCare.indexOf(pd.getString("brandID")+",");
			if(a<0){//没有关注
			  daoSupport.update("CustomerWriteMapper.addBrandCare", pd);
			  daoSupport.update("BrandWriteMapper.addCareNum", pd);
			}
		}
		return (int)daoSupport.findForObject("CustomerReadMapper.getBrandCaredNum", pd);
	}
	
	/**
	 * 取消品牌关注
	 * @param brandID ,customerID 
	 */
	public int updateBrandCare(PageData pd) throws Exception{
		String bCare = (String)daoSupport.findForObject("CustomerReadMapper.getAttentionBrand", pd);
		if (!Tools.notEmptys(bCare)) {//如果是空,
			throw new CustomException();
		}
		 int a = bCare.indexOf(pd.getString("brandID")+",");
		 if (a<0) {//没有关注该品牌
			throw new CustomException();
		}
		bCare = bCare.replace(pd.getString("brandID")+",","");
		pd.put("brandCare", bCare);
		daoSupport.update("CustomerWriteMapper.cancelBrandCare", pd);
		daoSupport.update("BrandWriteMapper.decreaseCareNum", pd);
		
		return (int)daoSupport.findForObject("CustomerReadMapper.getBrandCaredNum", pd);
	}
	
	/**
	 * 判断是否已经关注;YES success, NO failed 
	 * @param brandID
	 */
	@Override
	public String getJudgeBrandCared(PageData pd) throws Exception{
		pd.put("customerID", Jurisdiction.getCustomerID());
		String bCare = (String)daoSupport.findForObject("CustomerReadMapper.getAttentionBrand", pd);
		if (!Tools.notEmptys(bCare)) {
			 return "failed";
		}
		int a = bCare.indexOf(pd.getString("brandID")+",");
		  if(a<0){//没有关注
			  return "failed";
		  }else{
			  return "success";
		  }
	}
	
	/**
	 * 判断是否已经关注; 支持批量查询
	 * @param String brandIDs
	 * @return Boolean[] flag
	 * @author SSY 2017-12-29 
	 */
	@Override
	public Boolean[] getJudgeBrandCared(String brandIDs) throws Exception{
		String[] arrayBrandID = brandIDs.split(",");
		Boolean[] hasAttentions = new Boolean[arrayBrandID.length];
		String customerID = Jurisdiction.getCustomerID();
		if (!Tools.notEmptys(customerID)) {
			Arrays.fill(hasAttentions, false);  
			return hasAttentions;
		}
		String attentionBrandID = this.getAttentionBrandID();// 查询关注的拼接品牌id;
		if (!Tools.notEmptys(attentionBrandID)) {
			Arrays.fill(hasAttentions, false);  
			return hasAttentions;
		}
		for (int i = 0; i < arrayBrandID.length; i++) {
			int a = attentionBrandID.indexOf(arrayBrandID[i]+",");
			hasAttentions[i] = a < 0 ? false : true;
		}
		return hasAttentions;
	}	


	/**
	 * 查询用户关注的品牌id
	 * @author SSY 2017-12-29
	 * @param 
	 * @retrun String   attentionBrandID
	 */
	private String getAttentionBrandID() throws Exception{
		String customerID = Jurisdiction.getCustomerID();
		String attentionBrandID =(String) daoSupport.findForObject("CustomerReadMapper.getAttentionBrandID", customerID);//根据用户id查询关注的拼接品牌id;
		return attentionBrandID;
	}


}
