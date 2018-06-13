package com.aurorascm.controller.myzone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.aurorascm.controller.BaseController;
import com.aurorascm.entity.OrderManage;
import com.aurorascm.entity.Page;
import com.aurorascm.service.myzone.OrderService;
import com.aurorascm.util.Const;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Tools;

/**
 * @Title: CustomerOrderController.java 
 * @Package com.aurorascm.controller.myzone 
 * @Description: 订单中心 ---我的微仓销售订单
 * @author SSY  
 * @date 2018年5月23日   下午16:11:18 
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/personal/sellOrder")
public class SellOrderController extends BaseController {

	@Autowired
	private OrderService orderServiceImpl;

	
	/**
	 * @Title: getSellOrderList 
	 * @Description: TODO 
	 * @param    Page<OrderManage> page, String searchWord, String beginTimeFront(一个月前29···), String orderStateFront1(逗号拼接字符串),String orderStateFront2
	 * @return   page  ,回显page.t.searchWord,page.t.beginTimeFront,page.t.orderStateFront,sellOrderList订单列表, trsNum待收货,doneNum已完成
	 * @author SSY
	 * @date 2018年5月23日  下午16:11:18 
	 */
	@RequestMapping
	public String getSellOrderList(ModelMap map, Page<OrderManage> page, String searchWord, String beginTimeFront, String orderStateFront1,String orderStateFront2) throws Exception {
		try {
			List<OrderManage> sellOrderList = orderServiceImpl.getSellOrderList(page,searchWord, beginTimeFront, orderStateFront1, orderStateFront2);
			int trsNum = orderServiceImpl.getOrderNum(Const.SELL_ORDER_TYPE,"2,6,7");//待收货;
			int doneNum = orderServiceImpl.getOrderNum(Const.SELL_ORDER_TYPE,"8");//已完成;
			map.put("trsNum", JSON.toJSON(trsNum));
			map.put("doneNum", JSON.toJSON(doneNum));
			map.put("sellOrderList", JSON.toJSON(sellOrderList));
			map.put("page", JSON.toJSON(page));
			map.put("menuIndex", 3);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return "system/personal/sellOrder";
	}
 
	 
 
}
