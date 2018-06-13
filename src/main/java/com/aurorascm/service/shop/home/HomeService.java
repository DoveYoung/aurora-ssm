package com.aurorascm.service.shop.home;

import java.util.List;

import com.aurorascm.entity.Result;
import com.aurorascm.entity.home.Category;
import com.aurorascm.util.PageData;
import com.aurorascm.util.RedisConst;

/**
 * @Title: HomeService 
 * @Package com.aurorascm.service.shop.home; 
 * @Description:  商城首页维护接口
 * @author BYG
 * @date 2018年5月7日 
 * @version V2.0
 */
public interface HomeService {

	/**
	 * @Title: getAllCategory
	 * @Description: 获取所有目录（一二三级递归嵌套）
	 * @param    
	 * @return  List<Category>
	 * @author BYG
	 * @date 2018年5月7日
	 */
	List<Category> getAllCategory() throws Exception;
	
	/**
	 * @Title: getHotSell
	 * @Description: 获取首页热卖商品信息
	 * @param    
	 * @return  List<PageData>
	 * @author BYG
	 * @date 2018年5月7日
	 */
	List<PageData> getHotSell() throws Exception;
	
	/**
	 * @Title: getLargeCargo
	 * @Description: 获取大货集散商品
	 * @param    
	 * @return  List<PageData>
	 * @author BYG
	 * @date 2018年5月7日
	 */
	List<PageData> getLargeCargo() throws Exception;
	
	/**
	 * @Title: getDirectPost
	 * @Description: 获取海外直邮
	 * @param    
	 * @return  List<PageData>
	 * @author BYG
	 * @date 2018年5月7日
	 */
	List<PageData> getDirectPost() throws Exception;
	
	/**
	 * @Title: getCountriesGoods
	 * @Description: 获取各国好货
	 * @param    
	 * @return  List<PageData>
	 * @author BYG
	 * @date 2018年5月7日
	 */
	List<PageData> getCountriesGoods() throws Exception;
	
	/**
	 * @Title: getTopBrand
	 * @Description: 获取热门品牌
	 * @param    
	 * @return  List<PageData>
	 * @author BYG
	 * @date 2018年5月7日
	 */
	List<PageData> getTopBrand(int pageNumber) throws Exception;
	
	/**
	 * @Title: getTopBrandMaxPage
	 * @Description: 获取热门品牌最大页
	 * @param    
	 * @return  int
	 * @author BYG
	 * @date 2018年5月7日
	 */
	int getTopBrandMaxPage() throws Exception;
	
	/**
	 * @Title: getSearchKeyword 
	 * @Description:查询搜索框热搜词
	 * @param    
	 * @return Result<String>  
	 * @author SSY
	 * @date 2018年5月7日 下午4:15:22
	 */
	Result<String> getSearchKeyword(int keywordType) throws Exception;
}
