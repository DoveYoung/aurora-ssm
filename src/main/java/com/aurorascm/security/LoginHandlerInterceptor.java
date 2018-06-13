package com.aurorascm.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.aurorascm.util.Const;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.Tools;

/** 登录过滤，权限验证
 * @author BYG 2017-7-21
 * @version 1.0
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//登陆拦截
		String path = request.getServletPath();
		if(path.matches(Const.INTERCEPTOR_PATH)){ 
			String customerID = (String) Jurisdiction.getSession().getAttribute("customerID");
			if(!Tools.notEmpty(customerID)){
				//登陆过滤
				String goURL = request.getServletPath();//获取用户想要去的地址
				String param = request.getQueryString();//获得地址中携带的参数
				if(param != null){
					goURL = goURL + "?" + param;//重新拼接地址+参数
				}
				HttpSession session =  request.getSession();
				session.setAttribute("goURL", goURL);
				response.sendRedirect(request.getContextPath() + Const.LOGIN);//重定向改变路径
				return false;
			}
		} 
			return true;
	}
	
}
