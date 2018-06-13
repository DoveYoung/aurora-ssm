package com.aurorascm.serviceImpl.shop.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.Result;
import com.aurorascm.entity.home.HomeBonded;
import com.aurorascm.entity.home.HomeGoods;
import com.aurorascm.entity.home.HomeSpecial;
import com.aurorascm.entity.home.TimedActivity;
import com.aurorascm.redis.RedisUtil;
import com.aurorascm.service.shop.home.HomeBondedService;
import com.aurorascm.util.Const;
import com.aurorascm.util.RedisConst;

/**
 * @Title: HomeBondedServiceImpl.java 
 * @Package com.aurorascm.serviceImpl.shop.home 
 * @Description: 首页保税仓热卖
 * @author SSY  
 * @date 2018年5月7日 下午4:13:50 
 * @version V1.0
 */
@Service
public class HomeBondedServiceImpl implements HomeBondedService{
	
	@Autowired 
	private DAO daoSupport;
	@Autowired 
	private RedisUtil redisUtil;
	
	
	/**
	 * @Title: getHomeBonded 
	 * @Description: 查询保税仓热卖商品以及专题列表;
	 * @param    
	 * @return Result<HomeBonded>  
	 * @author SSY
	 * @date 2018年5月7日 下午1:42:26
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Result<HomeBonded> getHomeBonded() throws Exception{
		Result<HomeBonded> result = new Result<HomeBonded>();
		HomeBonded homeBonded ;
		Object object = redisUtil.get(RedisConst.HOME_BONDED);
		if (null==object) {
			homeBonded = new HomeBonded();
			List<HomeGoods> bondedGoodsList = (List<HomeGoods>)daoSupport.findForList("HomeBondedReadMapper.getHomeBondedGoods");
			int module = Const.SPECIAL_BONDED_MODULE;
			List<HomeSpecial> bondedSpecialList = (List<HomeSpecial>)daoSupport.findForList("HomeSpecialReadMapper.getHomeSpecialList",module);
			int keywordType = Const.KEYWORD_BONDED;
			String bondedKeyword = (String)daoSupport.findForObject("HomeKeywordReadMapper.getHomeKeyword",keywordType);
			homeBonded.setBondedSpecialList(bondedSpecialList);
			homeBonded.setBondedGoodsList(bondedGoodsList);
			homeBonded.setBondedKeyword(bondedKeyword);
			redisUtil.set(RedisConst.HOME_BONDED, homeBonded);
		}else{
			homeBonded = (HomeBonded)object;
		}
		result.setResult(homeBonded);
		result.setState(Result.STATE_SUCCESS);
		return result;
	}

	
	
	
	
	
	

}
