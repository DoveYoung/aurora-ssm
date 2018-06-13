package com.aurorascm.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.service.SpecialService;
import com.aurorascm.util.Const;
import com.aurorascm.util.PageData;

/**
 * 描述:专题活动页面ServiceImpl
 * 创建:SSY 2017-10-26
 * 修改:
 * @version 1.0
 */
@Service("specialServiceImpl")
public class SpecialServiceImpl implements SpecialService{
	
	@Resource(name = "daoSupport")
	private DAO daoSupport;

	/**
	 * 根据specialID查找该专题下的商品
	 * @param   Integer specialID, String templateID
	 * @return   templateID
	 * @throws Exception
	 */
	@Override
	public List<List<PageData>> getSpecialGoods(Integer templateID, Integer specialID) throws Exception{
		List<List<PageData>> specialGoods = new ArrayList<List<PageData>>();
		switch (templateID) {
		case 1:
			specialGoods = getFirstSpecialGoods(specialID);
			break;
		case 2:
			specialGoods = getSecondSpecialGoods(specialID);
			break;
		case 3:
			specialGoods = getThirdSpecialGoods(specialID);
			break;
		case 4:
			specialGoods = getFourthSpecialGoods(specialID);
			break;
		default:
			specialGoods = getFourthSpecialGoods(specialID);
			break;
		}
		return specialGoods;
	}
	
	/**
	 * @Title: getSpecialMap 
	 * @Description:  查询专题背景图
	 * @param    Integer specialID
	 * @return String  
	 * @author SSY
	 * @date 2018年5月16日 上午9:21:43
	 */
	@Override
	public String getSpecialBackground(Integer specialID) throws Exception{
		Object object = daoSupport.findForObject("HomeSpecialReadMapper.getSpecialBackground",specialID);
		return object!=null?object.toString():null;
	}
	
	
	
	/**
	 * 获取第1个活动专题商品;
	 */
	@SuppressWarnings("unchecked")
	private List<List<PageData>> getFirstSpecialGoods(Integer specialID) throws Exception{
		List<List<PageData>> specialGoods = new ArrayList<List<PageData>>();
		List<PageData> firstModuleGoods = (List<PageData>)daoSupport.findForList("HomeSpecialReadMapper.getFirstSpecialGoods",specialID);
		specialGoods.add(firstModuleGoods);
		return specialGoods;
	}
	
	/**
	 * 获取第2个活动专题商品;
	 */
	@SuppressWarnings("unchecked")
	private List<List<PageData>> getSecondSpecialGoods(Integer specialID) throws Exception{
		PageData pd = new PageData();
		pd.put("specialID", specialID);
		pd.put("module", "10000");
		List<List<PageData>> specialGoods = new ArrayList<List<PageData>>();
		List<PageData> firstModuleGoods = (List<PageData>)daoSupport.findForList("HomeSpecialReadMapper.getSecondSpecialGoods",pd);
		specialGoods.add(firstModuleGoods);
		pd.put("module", "20000");
		List<PageData> secondModuleGoods = (List<PageData>)daoSupport.findForList("HomeSpecialReadMapper.getSecondSpecialGoods",pd);
		specialGoods.add(secondModuleGoods);
		pd.put("module", "30000");
		List<PageData> thirdModuleGoods = (List<PageData>)daoSupport.findForList("HomeSpecialReadMapper.getSecondSpecialGoods",pd);
		specialGoods.add(thirdModuleGoods);
		pd.put("module", "40000");
		List<PageData> fourthModuleGoods = (List<PageData>)daoSupport.findForList("HomeSpecialReadMapper.getSecondSpecialGoods",pd);
		specialGoods.add(fourthModuleGoods);
		pd.put("module", "50000");
		List<PageData> fifthModuleGoods = (List<PageData>)daoSupport.findForList("HomeSpecialReadMapper.getSecondSpecialGoods",pd);
		specialGoods.add(fifthModuleGoods);
		return specialGoods;
	}
	
	
	/**
	 * 获取第3个活动专题商品;
	 */
	@SuppressWarnings("unchecked")
	private List<List<PageData>> getThirdSpecialGoods(Integer specialID) throws Exception{
		PageData pd = new PageData();
		pd.put("specialID", specialID);
		pd.put("module", "1");
		List<List<PageData>> specialGoods = new ArrayList<List<PageData>>();
		List<PageData> firstModuleGoods = (List<PageData>)daoSupport.findForList("HomeSpecialReadMapper.getThirdSpecialGoods",pd);
		specialGoods.add(firstModuleGoods);
		pd.put("module", "2");
		List<PageData> secondModuleGoods = (List<PageData>)daoSupport.findForList("HomeSpecialReadMapper.getThirdSpecialGoods",pd);
		specialGoods.add(secondModuleGoods);
		pd.put("module", "3");
		List<PageData> thirdModuleGoods = (List<PageData>)daoSupport.findForList("HomeSpecialReadMapper.getThirdSpecialGoods",pd);
		specialGoods.add(thirdModuleGoods);
		return specialGoods;
	}
	
	/**
	 * 获取第四个活动专题商品;
	 */
	@SuppressWarnings("unchecked")
	private List<List<PageData>> getFourthSpecialGoods(Integer specialID) throws Exception{
		PageData pd = new PageData();
		pd.put("specialID", specialID);
		pd.put("module", "1");
		List<List<PageData>> specialGoods = new ArrayList<List<PageData>>();
		List<PageData> firstModuleGoods = (List<PageData>)daoSupport.findForList("HomeSpecialReadMapper.getFourthSpecialGoods",pd);
		specialGoods.add(firstModuleGoods);
		pd.put("module", "2");
		List<PageData> secondModuleGoods = (List<PageData>)daoSupport.findForList("HomeSpecialReadMapper.getFourthSpecialGoods",pd);
		specialGoods.add(secondModuleGoods);
		return specialGoods;
	}
	
}
