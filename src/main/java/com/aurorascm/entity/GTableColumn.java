package com.aurorascm.entity;

import java.io.Serializable;

public class GTableColumn implements Serializable{
	
	/** 商品类目属性
	 * @author BYG 2017-7-21
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;
		private int gtcID;					//gtable_column表ID
		private int category2ID;			//二级类目ID
		private String category2;			//二级类目名
		private String columns;				//商品属性名称
		
		public int getGtcID() {
			return gtcID;
		}
		public void setGtcID(int gtcID) {
			this.gtcID = gtcID;
		}
		public int getCategory2ID() {
			return category2ID;
		}
		public void setCategory2ID(int category2id) {
			category2ID = category2id;
		}
		public String getCategory2() {
			return category2;
		}
		public void setCategory2(String category2) {
			this.category2 = category2;
		}
		public String getColumns() {
			return columns;
		}
		public void setColumns(String columns) {
			this.columns = columns;
		}
		@Override
		public String toString() {
			return "GTableColumn [gtcID=" + gtcID + ", category2ID=" + category2ID + ", category2=" + category2
					+ ", columns=" + columns + ", getGtcID()=" + getGtcID() + ", getCategory2ID()=" + getCategory2ID()
					+ ", getCategory2()=" + getCategory2() + ", getColumns()=" + getColumns() + ", getClass()="
					+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}
	
}
