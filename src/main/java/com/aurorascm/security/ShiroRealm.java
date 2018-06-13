package com.aurorascm.security;

import javax.annotation.Resource;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.aurorascm.entity.Customer;
import com.aurorascm.service.myzone.CustomerService;
import com.aurorascm.util.Const;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

/**
 * 登录信息和用户验证信息验证
 * 
 * @author BYG 2017-7-21
 * @version 1.0
 */
public class ShiroRealm extends AuthorizingRealm {

	@Resource(name = "customerServiceImpl")
	private CustomerService customerServiceImpl;

	/*
	 * 登录信息和用户验证信息验证(non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.
	 * apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String customerEmail = (String) token.getPrincipal(); // 得到用户名
		String password = new String((char[]) token.getCredentials()); // 得到密码
		PageData pd = new PageData();
		pd = new PageData(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
		pd.put("customerEmail", customerEmail);
		Customer customer;
		try {
			customer = customerServiceImpl.getCustomerByEmail(pd);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnknownAccountException();//账号不存在
		}  
		if (customer == null) {
			throw new UnknownAccountException();
		} else if (!customer.getCustomerPassword().equals(new SimpleHash("SHA-1", password,customer.getSalt()).toString())) {
			throw new IncorrectCredentialsException();//密码不正确;
		}
		if (customer.getCustomerStatus().equals("4")) { //账户被禁用;
			throw new LockedAccountException();
		}
		//账户验证通过
		Session session = Jurisdiction.getSession();
		session.setAttribute(Const.SESSION_CUSTOMER, customer); // 把用户信息放session中
		String customerID = customer.getCustomerID();
		session.setAttribute("customerID", customerID);
		session.setAttribute("customerEmail", customer.getCustomerEmail());
		String customerName = Tools.notEmptys(customer.getCustomerName()) ? customer.getCustomerName() : customer.getCustomerEmail();
		session.setAttribute("customerName", customerName);
		//查询用户购物车数量;
		int cartNum = 0;
		try {
			cartNum = customerServiceImpl.getCartNum(customerID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		session.setAttribute(Const.SESSION_CUSTOMER_CART_NUM, cartNum);
		return new SimpleAuthenticationInfo(customerEmail, password, getName());

	}

	/*
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法(non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache
	 * .shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		System.out.println("========2");
		return null;
	}

}
