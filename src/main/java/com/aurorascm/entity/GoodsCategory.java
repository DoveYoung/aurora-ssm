package com.aurorascm.entity;

import java.io.Serializable;
import java.util.List;

public class GoodsCategory implements Serializable{
	
	/** 商品类目实体类
	 * @author BYG 2017-7-21
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;
		private int categoryID;					 //类目ID
		private String categoryName;			 //类目名称
		private List<GoodsCategory> subCategory; //子类目
		public int getCategoryID() {
			return categoryID;
		}
		public void setCategoryID(int categoryID) {
			this.categoryID = categoryID;
		}
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		public List<GoodsCategory> getSubCategory() {
			return subCategory;
		}
		public void setSubCategory(List<GoodsCategory> subCategory) {
			this.subCategory = subCategory;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		@Override
		public String toString() {
			return "GoodsCategory [categoryID=" + categoryID + ", categoryName=" + categoryName + ", subCategory="
					+ subCategory + ", getCategoryID()=" + getCategoryID() + ", getCategoryName()=" + getCategoryName()
					+ ", getSubCategory()=" + getSubCategory() + ", getClass()=" + getClass() + ", hashCode()="
					+ hashCode() + ", toString()=" + super.toString() + "]";
		}
		
}
