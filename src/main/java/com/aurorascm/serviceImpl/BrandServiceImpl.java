package com.aurorascm.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.Brand;
import com.aurorascm.service.BrandService;
import com.aurorascm.util.PageData;

/**
 * 描述:客户登录注册ServiceImpl
 * 创建:BYG 2017/5/25
 * 修改:
 * @version 1.0
 */
@Service("brandServiceImpl")
public class BrandServiceImpl implements BrandService{
	
	@Resource(name = "daoSupport")
	private DAO daoSupport;

	/**通过关键词判断是否是搜品牌
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public Brand getBrandByKeyword(PageData pd) throws Exception {
		return (Brand) daoSupport.findForObject("BrandReadMapper.getBrandByKeyword", pd);
	}
	
	/**根据品牌ID搜品牌
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public Brand getBrandByID(String brandID) throws Exception {
		return (Brand) daoSupport.findForObject("BrandReadMapper.getBrandByID", brandID);
	}

	/**通过品牌ID获取品牌类目名
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getCategoryIDByBrandID(PageData pd) throws Exception {
		return (List<PageData>)daoSupport.findForList("BrandReadMapper.getCategoryIDByBrandID", pd);
	}

	/**根据品牌类目名得到5个类似品牌
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getBrandByCategory(List<PageData> category) throws Exception {
		return (List<PageData>)daoSupport.findForList("BrandReadMapper.getBrandByCategory", category);
	}

	/**根据商品2级类目ID得到5个类似品牌
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getBrandByCategory2ID(List<Integer> pd) throws Exception {
		return (List<PageData>)daoSupport.findForList("BrandReadMapper.getBrandByCategory2ID", pd);
	}
	
	/**根据品牌ID集合得到品牌类目得到5个类似品牌
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> get5BrandByBrandID(List<String> brandIDs) throws Exception {
		return (List<PageData>)daoSupport.findForList("BrandReadMapper.getBrandCategory", brandIDs);
	}
	
}
