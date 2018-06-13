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
 * @Description: 订单中心 ---我的微仓采购订单
 * @author SSY  
 * @date 2018年5月23日   下午16:11:18 
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/personal/purchaseOrder")
public class PurchaseOrderController extends BaseController {

	@Autowired
	private OrderService orderServiceImpl;

	
	/**
	 * @Title: getPurchaseOrderList 
	 * @Description: TODO 
	 * @param    Page<OrderManage> page, String searchWord, String beginTimeFront(一个月前29···), String orderStateFront1(逗号拼接字符串),String orderStateFront2
	 * @return   page  ,回显page.t.searchWord,page.t.beginTimeFront,page.t.orderStateFront,purchaseOrderList订单列表,obligationNum代付款,doneNum已完成
	 * @author SSY
	 * @date 2018年5月23日  下午16:11:18 
	 */
	@RequestMapping
	public String getPurchaseOrderList(ModelMap map, Page<OrderManage> page, String searchWord, String beginTimeFront,  String orderStateFront1,String orderStateFront2) throws Exception {
		try {
			List<OrderManage> purchaseOrderList = orderServiceImpl.getPurchaseOrderList(page,searchWord, beginTimeFront, orderStateFront1, orderStateFront2);
			int obligationNum = orderServiceImpl.getOrderNum(Const.PURCHASE_ORDER_TYPE,"1");//待付款状态1
			int doneNum = orderServiceImpl.getOrderNum(Const.PURCHASE_ORDER_TYPE,"8");//已完成;
			map.put("obligationNum", JSON.toJSON(obligationNum));
			map.put("doneNum", JSON.toJSON(doneNum));
			map.put("purchaseOrderList", JSON.toJSON(purchaseOrderList));
			map.put("page", JSON.toJSON(page));
			map.put("menuIndex", 2);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return "system/personal/purchaseOrder";
	}
 
	 
 
}
