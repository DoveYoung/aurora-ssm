package com.aurorascm.service;

import java.util.List;

import com.aurorascm.entity.Brand;
import com.aurorascm.util.PageData;

public interface BrandService {
	
	/**通过关键词判断是否是搜品牌
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Brand getBrandByKeyword(PageData pd)throws Exception;
	
	/**根据品牌ID搜品牌
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Brand getBrandByID(String brandID)throws Exception;
	
	/**通过品牌ID获取品牌类目名
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getCategoryIDByBrandID(PageData pd)throws Exception;
	
	/**根据品牌类目名得到5个类似品牌
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getBrandByCategory(List<PageData> category)throws Exception;
	
	/**根据商品2级类目ID得到5个类似品牌
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> getBrandByCategory2ID(List<Integer> pd)throws Exception;
	
	/**根据品牌ID集合得到品牌类目得到5个类似品牌
	 * @param PageData pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> get5BrandByBrandID(List<String> brandIDs) throws Exception;
	

}
