package com.aurorascm.entity.home;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: Category.java 
 * @Package com.aurora.entity.home
 * @Description: 商城首页导航栏类目
 * @author BYG 
 * @date 2018年4月27日 
 * @version 2.0
 */
public class Category implements Serializable{

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -4272702048150121183L;
	/**
	 * 类目id
	 */
	private Integer categoryID;
	/**
	 * 类目名称
	 */
	private String categoryName;
	/**
	 * 类目级别
	 */
	private Integer categoryLevel;
	/**
	 * 类目父级类目id
	 */
	private Integer categoryParentID;
	/**
	 * 位置
	 */
	private Integer locationSort;
	
	/**
	 * 是否标红，1标红；2不标红；默认2
	 */
	private Integer red;
	/**
	 * 子类目
	 */
	private List<Category> subcategory;
	/**
	 * @return the categoryID
	 */
	public Integer getCategoryID() {
		return categoryID;
	}
	/**
	 * @param categoryID the categoryID to set
	 */
	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * @return the categoryLevel
	 */
	public Integer getCategoryLevel() {
		return categoryLevel;
	}
	/**
	 * @param categoryLevel the categoryLevel to set
	 */
	public void setCategoryLevel(Integer categoryLevel) {
		this.categoryLevel = categoryLevel;
	}
	/**
	 * @return the categoryParentID
	 */
	public Integer getCategoryParentID() {
		return categoryParentID;
	}
	/**
	 * @param categoryParentID the categoryParentID to set
	 */
	public void setCategoryParentID(Integer categoryParentID) {
		this.categoryParentID = categoryParentID;
	}
	/**
	 * @return the locationSort
	 */
	public Integer getLocationSort() {
		return locationSort;
	}
	/**
	 * @param locationSort the locationSort to set
	 */
	public void setLocationSort(Integer locationSort) {
		this.locationSort = locationSort;
	}
	/**
	 * @return the red
	 */
	public Integer getRed() {
		return red;
	}
	/**
	 * @param red the red to set
	 */
	public void setRed(Integer red) {
		this.red = red;
	}
	/**
	 * @return the subcategory
	 */
	public List<Category> getSubcategory() {
		return subcategory;
	}
	/**
	 * @param subcategory the subcategory to set
	 */
	public void setSubcategory(List<Category> subcategory) {
		this.subcategory = subcategory;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Category [categoryID=" + categoryID + ", categoryName=" + categoryName + ", categoryLevel="
				+ categoryLevel + ", categoryParentID=" + categoryParentID + ", locationSort=" + locationSort + ", red="
				+ red + ", subcategory=" + subcategory + ", getCategoryID()=" + getCategoryID() + ", getCategoryName()="
				+ getCategoryName() + ", getCategoryLevel()=" + getCategoryLevel() + ", getCategoryParentID()="
				+ getCategoryParentID() + ", getLocationSort()=" + getLocationSort() + ", getRed()=" + getRed()
				+ ", getSubcategory()=" + getSubcategory() + "]";
	}

	
	
	

}
