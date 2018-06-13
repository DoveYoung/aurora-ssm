package com.aurorascm.listener;  
  
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.aurorascm.entity.Result;
import com.aurorascm.redis.RedisUtil;
import com.aurorascm.serviceImpl.CustomerServiceImpl;
import com.aurorascm.serviceImpl.shop.home.HomeServiceImpl;
import com.aurorascm.util.Const;
import com.aurorascm.util.PageData;
import com.aurorascm.util.RedisConst;  

/**
 * session监听,统计网站访客ip和时间
 * @author SSY 2017-11-14
 *
 */
public class MySessionListener implements HttpSessionListener {  
	
//	@Autowired RedisUtil redisUtil;
//	@Autowired CategoryService CategoryServiceImpl;
	
	/* Session创建事件 */  
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
		
		//把类目放入ServletContext中
		HomeServiceImpl HomeServiceImpl =  (HomeServiceImpl) ac.getBean("homeServiceImpl");
		RedisUtil redisUtil =  (RedisUtil) ac.getBean("redisUtil");
		try {
			Object category = redisUtil.get(RedisConst.HOME_CATEGORY);
			if (category != null) {
				Object aCategory = session.getServletContext().getAttribute(RedisConst.HOME_CATEGORY);
				if (aCategory == null) {
					session.getServletContext().setAttribute(RedisConst.HOME_CATEGORY, JSON.toJSON(category));
				}
			} else {
				category = HomeServiceImpl.getAllCategory();
				redisUtil.set(RedisConst.HOME_CATEGORY, category);
				session.getServletContext().setAttribute(RedisConst.HOME_CATEGORY, JSON.toJSON(category));
			}
			
			Object homeSearch = redisUtil.get(RedisConst.HOME_SEARCH);//搜索框关键子
			if (homeSearch==null) {
				Result<String> reslut = HomeServiceImpl.getSearchKeyword(Const.KEYWORD_SEARCH);
				homeSearch = JSON.toJSON(reslut.getResult().split(","));
				redisUtil.set(RedisConst.HOME_SEARCH, homeSearch);
				session.getServletContext().setAttribute(RedisConst.HOME_SEARCH, homeSearch);
			}else{
				Object ahomesearch = session.getServletContext().getAttribute(RedisConst.HOME_SEARCH);
				if (ahomesearch == null) {
					session.getServletContext().setAttribute(RedisConst.HOME_SEARCH, homeSearch);
				}
				session.getServletContext().setAttribute(RedisConst.HOME_SEARCH, homeSearch);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	/* Session失效事件  ———— 用户直接关闭浏览器后状态不改变，通过session失效事件改变用户状态*/  
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		Object customerID = session.getAttribute("customerID");
		PageData pd = new PageData();
		pd.put("customerID", customerID);
		pd.put("customerStatus", 3);			//1.新注册未登陆；2在线；3离线；4禁用
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
		CustomerServiceImpl customerServiceImpl = (CustomerServiceImpl)ac.getBean("customerServiceImpl");
		try {
			customerServiceImpl.updateCustomerStatus(pd);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}  
}  