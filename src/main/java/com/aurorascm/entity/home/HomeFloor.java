package com.aurorascm.entity.home;

import java.io.Serializable;
/**
 * @Title: HomeFloor.java 
 * @Package com.aurorascm.entity.home 
 * @Description:  首页Floor
 * @author SSY  
 * @date 2018年5月7日 下午4:45:55 
 * @version V1.0
 */
import java.util.List;
public class HomeFloor implements Serializable{
 
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 568485484845645496L;
	
	private String keyword;

	private Integer category1ID;
	
	private String categoryName;
	
	private List<HomeFloorBrand> floorBrandList;
	
	private List<HomeFloorGoods> floorGoodsList;

	private List<HomeSpecial> specialList;
	
	
	
	
	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getKeyword() {
		return keyword;
	}

	
	public List<HomeSpecial> getSpecialList() {
		return specialList;
	}


	public void setSpecialList(List<HomeSpecial> specialList) {
		this.specialList = specialList;
	}


	public Integer getCategory1ID() {
		return category1ID;
	}

	public void setCategory1ID(Integer category1id) {
		category1ID = category1id;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<HomeFloorBrand> getFloorBrandList() {
		return floorBrandList;
	}

	public void setFloorBrandList(List<HomeFloorBrand> floorBrandList) {
		this.floorBrandList = floorBrandList;
	}

	public List<HomeFloorGoods> getFloorGoodsList() {
		return floorGoodsList;
	}

	public void setFloorGoodsList(List<HomeFloorGoods> floorGoodsList) {
		this.floorGoodsList = floorGoodsList;
	}

	@Override
	public String toString() {
		return "HomeFloor [keyword=" + keyword + ", floorBrandList=" + floorBrandList + ", floorGoodsList="
				+ floorGoodsList + "]";
	}
	
	
	
	
}
