package com.aurorascm.service.shop.home;

import com.aurorascm.entity.Result;
import com.aurorascm.entity.home.HomeBonded;

public interface HomeBondedService {

	/**
	 * @Title: getHomeBonded 
	 * @Description: 查询保税仓热卖商品以及专题列表;
	 * @param    
	 * @return Result<HomeBonded>  
	 * @author SSY
	 * @date 2018年5月7日 下午1:42:26
	 */
	Result<HomeBonded> getHomeBonded() throws Exception;

	 
 
	
	
}
