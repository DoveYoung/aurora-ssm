package com.aurorascm.service.myzone;

import com.aurorascm.entity.Customer;
import com.aurorascm.util.PageData;

public interface CustomerService {
	
	/**保存用户注册
	 * @param pd
	 * @throws Exception
	 */
	public void saveCustomerRegister(PageData pd)throws Exception;
	
	/**
	 * 通过customerEmail获取客户数据,返回类型是Customer
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Customer getCustomerByEmail(PageData pd)throws Exception;
	
	/**
	 * 通过customerEmail获取客户数据,返回类型是PageDate
	 * @param customerEmail
	 * @return
	 * @throws Exception
	 */
	public PageData getCustomerByEmail(String customerEmail) throws Exception;
	
	/**登录判断
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Customer getCustomerByEmailAndPwd(PageData pd)throws Exception;
	
	/**更新用户状态
	 * @param pd
	 * @throws Exception
	 */
	public void updateCustomerStatus(PageData pd)throws Exception;
	
	/**重置用户密码
	 * @param pd
	 * @throws Exception
	 */
	public void resetPW(PageData pd)throws Exception;
	
	/**更新用户IP
	 * @param pd
	 * @throws Exception
	 */
	public void updateCustomerIP(PageData pd)throws Exception;

	/**
	 * 修改用户个人资料
	 * @param pd
	 */
	public void updateMyInfo(PageData pd)throws Exception;
	
	/**
	 * 修改用户密码
	 */
	public void updatePW(PageData pd) throws Exception;

	/**
	 * 数据统计--用户登陆时间段次数统计(该时间段日统计以及累计统计);
	 * @throws Exception
	 */
	public void addCustomerLoginDistribution() throws Exception;

	/**
	 * @Title: getCartNum 
	 * @Description:  查询用户购物车数量
	 * @param    
	 * @return int  
	 * @author SSY
	 * @date 2018年5月19日 上午10:51:08
	 */
	public int getCartNum(String customerID) throws Exception;

	/**
	 * @Title: getPersonalInfo 
	 * @Description: 查询当前登陆的个人中心信息
	 * @param    
	 * @return Customer  
	 * @author SSY
	 * @date 2018年6月1日 上午10:40:33
	 */
	public Customer getPersonalInfo()throws Exception;
	
}
