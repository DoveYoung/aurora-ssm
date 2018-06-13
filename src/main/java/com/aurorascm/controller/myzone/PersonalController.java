package com.aurorascm.controller.myzone;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.aurorascm.controller.BaseController;
import com.aurorascm.entity.Customer;

/**
 * @Title: CustomerOrderController.java 
 * @Package com.aurorascm.controller.myzone 
 * @Description: 订单中心 ---我的个人订单
 * @author SSY  
 * @date 2018年5月23日 上午10:11:18 
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/personal")
public class PersonalController extends BaseController {

	/**
	 * @Title: getPersonalInfo 
	 * @Description:  个人中心页面
	 * @param     
	 * @return    customer, menuIndex=4
	 * @author SSY
	 * @date 2018年5月23日 上午10:28:56
	 */
	@RequestMapping
	public String getPersonalInfo(ModelMap map) throws Exception {
		try {
			Customer customer = customerServiceImpl.getPersonalInfo();//查询当前用户信息;
			map.put("customer", JSON.toJSON(customer));
			map.put("menuIndex", 4);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return "system/personal/personalInfo";
	}

}
