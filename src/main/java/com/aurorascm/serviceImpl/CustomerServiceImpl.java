package com.aurorascm.serviceImpl;


import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.Customer;
import com.aurorascm.service.myzone.CustomerService;
import com.aurorascm.util.Const;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;

/**
 * 描述:客户登录注册ServiceImpl
 * 创建:BYG 2017/5/25
 * 修改:
 * @version 1.0
 */
@Service("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService{
	
	@Resource(name = "daoSupport")
	private DAO daoSupport;

	/**保存用户注册
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void saveCustomerRegister(PageData pd) throws Exception {
		daoSupport.save("CustomerWriteMapper.saveCustomerRegister", pd);
	}

	/**通过customerEmail获取客户数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public Customer getCustomerByEmail(PageData pd) throws Exception {
		return (Customer)daoSupport.findForObject("CustomerReadMapper.getCustomerByEmail", pd);
	}
	
	/**
	 * 通过customerEmail获取客户数据,返回类型是PageDate
	 * @param customerEmail
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageData getCustomerByEmail(String customerEmail) throws Exception {
		return (PageData)daoSupport.findForObject("CustomerReadMapper.getCustomerPdByEmail", customerEmail);
	}
	
	/**登录判断
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public Customer getCustomerByEmailAndPwd(PageData pd) throws Exception {
		return (Customer)daoSupport.findForObject("CustomerReadMapper.getCustomerByEmailAndPwd", pd);
	}

	/**更新用户状态
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void updateCustomerStatus(PageData pd) throws Exception {
		daoSupport.update("CustomerWriteMapper.updateCustomerStatus", pd);
	}

	/**更新用户IP
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void updateCustomerIP(PageData pd) throws Exception {
		daoSupport.update("CustomerWriteMapper.updateCustomerIP", pd);
	}

	/**重置用户密码
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void resetPW(PageData pd) throws Exception {
		PageData customer = getCustomerByEmail(pd.getString("customerEmail"));
		String salt = customer.getString("salt");
		String password = pd.getString("customerPassword");
		pd.put("customerPassword", new SimpleHash("SHA-1",password,salt).toString());
		updatePW(pd);
	}

	/**
	 * 修改用户密码
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void updatePW(PageData pd) throws Exception {
		daoSupport.update("CustomerWriteMapper.updatePW", pd);
		}
	
	/**
	 * 修改用户个人资料
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public void updateMyInfo(PageData pd) throws Exception {
		daoSupport.update("CustomerWriteMapper.updateMyInfo", pd);
		Session session = Jurisdiction.getSession();
		session.setAttribute(Const.SESSION_CUSTOMER_NAME, pd.getString("name"));
		session.setAttribute(Const.SESSION_CUSTOMER_EMAIL, pd.getString("email"));
	}
	
	/**
	 * 数据统计--用户登陆时间段次数统计;
	 * @throws Exception
	 */
	@Override
	public void addCustomerLoginDistribution() throws Exception {
		String hour = DateUtil.getTwoHour();
		PageData pd = new PageData();
		pd.put("customerID", Jurisdiction.getCustomerID());
		pd.put("hour", hour);
		daoSupport.save("StatisticsCustomerWriteMapper.addCustomerLoginDistribution",pd);
	}
	
	/**
	 * @Title: getCartNum 
	 * @Description:  查询用户购物车数量
	 * @param    
	 * @return int  
	 * @author SSY
	 * @date 2018年5月19日 上午10:51:08
	 */
	public int getCartNum(String customerID) throws Exception{
		int cartNum = (int)daoSupport.findForObject("CustomerReadMapper.getCartNum",customerID);
		return cartNum;
	}
	

	/**
	 * @Title: getPersonalInfo 
	 * @Description: 查询当前登陆的个人中心信息
	 * @param    
	 * @return Customer  
	 * @author SSY
	 * @date 2018年6月1日 上午10:40:33
	 */
	public Customer getPersonalInfo()throws Exception{
		String customerID = Jurisdiction.getCustomerID();
		Customer customer = (Customer)daoSupport.findForObject("CustomerReadMapper.getPersonalInfo",customerID);
		return customer;
	}
	
	
	
	
}
