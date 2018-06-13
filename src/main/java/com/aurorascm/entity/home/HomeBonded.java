package com.aurorascm.entity.home;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: HomeBonded.java 
 * @Package com.aurora.entity.home 
 * @Description: 首页保税仓 
 * @author SSY  
 * @date 2018年5月7日 上午10:06:49 
 * @version V1.0
 */
public class HomeBonded implements Serializable{
 
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 478415110738437864L;
	
	/**
	 * 搜索关键词
	 */
	private String bondedKeyword;
	
	/**
	 * 专题列表
	 */
	private List<HomeSpecial> bondedSpecialList;
	 
	/**
	 * 商品列表
	 */
	private List<HomeGoods> bondedGoodsList;
	
	
	

	public String getBondedKeyword() {
		return bondedKeyword;
	}

	public void setBondedKeyword(String bondedKeyword) {
		this.bondedKeyword = bondedKeyword;
	}

	public List<HomeSpecial> getBondedSpecialList() {
		return bondedSpecialList;
	}

	public void setBondedSpecialList(List<HomeSpecial> bondedSpecialList) {
		this.bondedSpecialList = bondedSpecialList;
	}

	public List<HomeGoods> getBondedGoodsList() {
		return bondedGoodsList;
	}

	public void setBondedGoodsList(List<HomeGoods> bondedGoodsList) {
		this.bondedGoodsList = bondedGoodsList;
	}

	@Override
	public String toString() {
		return "HomeBonded [bondedKeyword=" + bondedKeyword + ", bondedSpecialList=" + bondedSpecialList
				+ ", bondedGoodsList=" + bondedGoodsList + "]";
	}
	 
	
	
	
	
	
}
