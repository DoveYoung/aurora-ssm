package com.aurorascm.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.aurorascm.entity.Customer;

/** session管理
 * @author BYG 2017-7-21
 * @version 1.0
 */
public class Jurisdiction {


	
	/**获取当前登录的用户customer
	 * @return
	 */
	public static Customer getCustomer(){
		return (Customer) getSession().getAttribute(Const.SESSION_CUSTOMER);
	}
	
	
	
	/**获取当前登录的用户id
	 * @return
	 */
	public static String getCustomerID(){
		Object attribute = getSession().getAttribute(Const.SESSION_CUSTOMER_ID);
		return attribute!=null?attribute.toString().replace(" ", ""):null;
	}
	/**获取当前登录的用户邮箱
	 * @return
	 */
	public static String getCustomerEmail(){
		Object attribute = getSession().getAttribute(Const.SESSION_CUSTOMER_EMAIL);
		return attribute!=null?attribute.toString().replace(" ", ""):null;
	}
	
	/**获取当前登录的用户名
	 * @return
	 */
	public static String getCustomerName(){
		Object attribute = getSession().getAttribute(Const.SESSION_CUSTOMER_NAME);
		return attribute!=null?attribute.toString().replace(" ", ""):null;
	}
	
	/**shiro管理的session
	 * @return
	 */
	public static Session getSession(){ 
		return SecurityUtils.getSubject().getSession();
	}
}
