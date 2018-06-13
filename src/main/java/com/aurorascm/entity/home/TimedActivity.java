package com.aurorascm.entity.home;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: TimedActivity.java 
 * @Package com.aurora.entity.home 
 * @Description: 限时抢购活动实体类
 * @author SSY  
 * @date 2018年4月23日 下午7:18:48 
 * @version V1.0
 */
public class TimedActivity implements Serializable{
 
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -4168647406392296889L;
	
	
	/**
	 * 活动id
	 */
	private Integer activityID;
	/**
	 * 活动状态
	 */
	private Integer activityState;
	/**
	 * 开始时间
	 */
	private String beginTime;
	/**
	 * 开始时间
	 */
	private String endTime;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 创建者
	 */
	private String creator;
	/**
	 * 修改时间
	 */
	private String updateTime;
	/**
	 * 修改者
	 */
	private String updator;
	
	/**
	 * 限时抢购活动商品
	 */
	private List<TimedGoods> timedGoodsList;
	
 
	public Integer getActivityID() {
		return activityID;
	}

	public void setActivityID(Integer activityID) {
		this.activityID = activityID;
	}

	public Integer getActivityState() {
		return activityState;
	}

	public void setActivityState(Integer activityState) {
		this.activityState = activityState;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<TimedGoods> getTimedGoodsList() {
		return timedGoodsList;
	}

	public void setTimedGoodsList(List<TimedGoods> timedGoodsList) {
		this.timedGoodsList = timedGoodsList;
	}
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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

	@Override
	public String toString() {
		return "TimedActivity [activityID=" + activityID + ", activityState=" + activityState + ", beginTime="
				+ beginTime + ", endTime=" + endTime + ", timedGoodsList=" + timedGoodsList + "]";
	}
	
	
	
	
	
	
	
}
