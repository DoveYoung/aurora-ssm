package com.aurorascm.entity;

import java.io.Serializable;

public class SearchKeyword implements Serializable{
	
	/** 搜索关键词
	 * @author BYG 2017-7-21
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;
		private int sKeywordID;				//搜索关键词ID
		private String sKeyword;			//搜索关键词
		private int sKeywordNum;			//搜索次数
		
		public int getsKeywordID() {
			return sKeywordID;
		}
		
		public void setsKeywordID(int sKeywordID) {
			this.sKeywordID = sKeywordID;
		}
		public String getsKeyword() {
			return sKeyword;
		}
		public void setsKeyword(String sKeyword) {
			this.sKeyword = sKeyword;
		}
		public int getsKeywordNum() {
			return sKeywordNum;
		}
		public void setsKeywordNum(int sKeywordNum) {
			this.sKeywordNum = sKeywordNum;
		}
		
		@Override
		public String toString() {
			return "GoodsSearchKeyword [sKeywordID=" + sKeywordID + ", sKeyword=" + sKeyword + ", sKeywordNum="
					+ sKeywordNum + ", getsKeywordID()=" + getsKeywordID() + ", getsKeyword()=" + getsKeyword()
					+ ", getsKeywordNum()=" + getsKeywordNum() + ", getClass()=" + getClass() + ", hashCode()="
					+ hashCode() + ", toString()=" + super.toString() + "]";
		}
		

		
}
