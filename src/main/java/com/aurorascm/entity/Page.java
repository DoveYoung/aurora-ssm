package com.aurorascm.entity;

import com.aurorascm.util.PageData;

/**
 * 描述:分页类
 * 创建:BYG 2017/5/25
 * 修改:
 * @version 1.0
 */
public class Page<T> {
	
	private int pageSize;       //每页显示记录数
	private int totalPage;		//总页数
	private int totalRecord;	//总记录数
	private int currentPage;	//当前页
	private int fromIndex;	    //当前记录起始索引
	private String sort;		//排序字段
	private PageData pd = new PageData();
	private T t;
	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public int getTotalPage() {
		if(totalRecord % pageSize == 0){
			totalPage = totalRecord / pageSize;
		} else{
			totalPage = totalRecord / pageSize+1;
		}
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getTotalRecord() {
		return totalRecord;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	
	public int getCurrentPage() {
		if (currentPage == 0) {
			currentPage = 1;
		} 
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		
		this.pageSize = pageSize;
	}
	
	public int getFromIndex() {
		return fromIndex;
	}
	
	public void setFromIndex(int fromIndex) {
		this.fromIndex = fromIndex;
	}
	
	public PageData getPd() {
		return pd;
	}

	public void setPd(PageData pd) {
		this.pd = pd;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "Page [pageSize=" + pageSize + ", totalPage=" + totalPage + ", totalRecord=" + totalRecord
				+ ", currentPage=" + currentPage + ", fromIndex=" + fromIndex + ", sort=" + sort + ", pd=" + pd + ", t="
				+ t + "]";
	}
	
}
