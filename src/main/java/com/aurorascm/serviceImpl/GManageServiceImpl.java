package com.aurorascm.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import com.aurorascm.dao.DAO;
import com.aurorascm.entity.GoodsManage;
import com.aurorascm.entity.home.TimedActivity;
import com.aurorascm.service.GManageService;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;

/**
 * 描述:客户登录注册ServiceImpl
 * 创建:BYG 2017/5/25
 * 修改:
 * @version 1.0
 */
@Service("gManageServiceImpl")
public class GManageServiceImpl implements GManageService{
	
	@Resource(name = "daoSupport")
	private DAO daoSupport;

	/**通过商品ID获取商品管理信息
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getGManage(PageData pd) throws Exception {
		return (List<PageData>)daoSupport.findForList("GManageReadMapper.getGManage", pd);
	}

	/**根据商品id大额采购商品基本信息    (pd)
	 * @param goodsID
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageData getPDGoodsByID(String goodsID) throws Exception {
		return (PageData)daoSupport.findForObject("GManageReadMapper.getPDGoodsByID", goodsID);
	}
	
	/**通过商品ID获取商品管理信息for购买
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public GoodsManage getGMDForLB(PageData pd) throws Exception {
		return (GoodsManage)daoSupport.findForObject("GManageReadMapper.getGMDForLB", pd);
	}

	/**通过商品ID获取商品管理信息和详情（详情页）
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public GoodsManage getGoodsDM(String goodsID) throws Exception {
		GoodsManage goodsManage = (GoodsManage)daoSupport.findForObject("GManageReadMapper.getGoodsDM", goodsID);
		return goodsManage;
	}
	
	/**获取同商品不同邮寄方式的商品详情信息（详情页）
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GoodsManage> getRGoods(PageData pd) throws Exception {
		return (List<GoodsManage>)daoSupport.findForList("GManageReadMapper.getRGoods", pd);
	}

	/**通过商品ID获取商品邮寄方式
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getShipType(String goodsID) throws Exception {
		return (int)daoSupport.findForObject("GManageReadMapper.getShipType", goodsID);
	}
	
	

	/**
	 * 数据统计---商品详情页:
	 * 根据商品信息,统计商品点击次数+1;
	 * @throws Exception
	 */
	@Override
	public void addGoodsClickNum(GoodsManage goodsManage) throws Exception {
		String hour = DateUtil.getTwoHour();
		PageData pd = new PageData();
		pd.put("goodsID", goodsManage.getGoodsID());
		pd.put("month", DateUtil.getMonthMM());
		pd.put("day", DateUtil.getDay());
		pd.put("hour", hour);
		daoSupport.save("StatisticsGoodsWriteMapper.addGoodsDayClickNum", pd);//累计当天该时段该商品访问量;
		daoSupport.save("StatisticsGoodsWriteMapper.addGoodsMonthClickNum", pd);//累计当月该时段该商品访问量;
	}
	
	/**
	 * 数据统计---商品详情页:
	 * 根据商品信息,用户id,统计用户浏览该类目商品次数+1
	 * @throws Exception
	 */
	@Override
	public void addCustomerCategoryClickNum(GoodsManage goodsManage) throws Exception {
		int category2IDI = goodsManage.getGoodsCommon().getCategory1ID();
		String category = "";
		switch(category2IDI){
		case 10000:
			category = "category1_num";
			break;
		case 20000:
			category = "category2_num";
			break;
		case 30000:
			category = "category3_num";
			break;
		case 40000:
			category = "category4_num";
			break;
		case 50000:
			category = "category5_num";
			break;
		case 60000:
			category = "category6_num";
			break;
		case 70000:
			category = "category7_num";
			break;
		default :
			category = "";
			break;
		}
		PageData pd = new PageData();
		pd.put("customerID", Jurisdiction.getCustomerID());
		if (!"".equals(category)) {//如果类目id不存在就不统计;
			pd.put("category", category);
			daoSupport.save("StatisticsCustomerWriteMapper.addCustomerCategoryClickNum", pd);//统计用户各类目访问次数;
		}
	}
	
	
	
	/**
	 * @Title: getActivityGoods 
	 * @Description:  查询该商品的促销价格活动信息;
	 * @param    
	 * @return TimedActivity  
	 * @author SSY
	 * @date 2018年5月15日 上午10:53:39
	 */
	@Override
	public TimedActivity getActivityGoods(String goodsID) throws Exception{
		Object object = daoSupport.findForObject("GManageReadMapper.getActivityGoods",goodsID);
		TimedActivity timedActivity = object!=null?(TimedActivity)object:null;
		return timedActivity;
	}
	
}
