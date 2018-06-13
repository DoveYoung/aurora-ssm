package com.aurorascm.entity.home;
/**
 * @Title: HomeBanner.java 
 * @Package com.aurora.entity.home 
 * @Description: 首页专题
 * @author SSY  
 * @date 2018年4月23日 下午7:18:48 
 * @version V1.0
 */

import java.io.Serializable;

public class HomeSpecial implements Serializable{

	 
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 7542799889627428317L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * module 1.banner专题;2.保税仓专题;3.大额采购母婴儿童专题;4.美妆护类专题;5.厨卫家居类专题;6.营养保健;7数码家电
	 */
	private Integer module; 
	
	/**
	 *  专题指向地址
	 */
	private String url;
	/**
	 * 专题图
	 */
	private String  specialMap;
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 更新人
	 */
	private String updator;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url+"&specialID="+id;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSpecialMap() {
		return specialMap;
	}
	public void setSpecialMap(String specialMap) {
		this.specialMap = specialMap;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public Integer getModule() {
		return module;
	}
	public void setModule(Integer module) {
		this.module = module;
	}
	@Override
	public String toString() {
		return "HomeSpecial [id=" + id + ", url=" + url + ", specialMap=" + specialMap + ", updateTime=" + updateTime
				+ ", updator=" + updator + "]";
	}
	
}
