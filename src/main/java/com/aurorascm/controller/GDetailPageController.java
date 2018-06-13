package com.aurorascm.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.aurorascm.entity.Brand;
import com.aurorascm.entity.GoodsManage;
import com.aurorascm.entity.home.TimedActivity;
import com.aurorascm.service.AreaAddrService;
import com.aurorascm.service.BrandService;
import com.aurorascm.service.DataService;
import com.aurorascm.service.GManageService;
import com.aurorascm.service.GoodsService;
import com.aurorascm.service.HomePageService;
import com.aurorascm.util.AppUtil;
import com.aurorascm.util.Const;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Jurisdiction;
import com.aurorascm.util.PageData;
import com.aurorascm.util.PostageMath;
import com.aurorascm.util.Tools;

/** 商品详情页
 * @author BYG 2017-7-21
 * @version 1.0
 */
@Controller
@RequestMapping(value="/detailPage")
public class GDetailPageController extends BaseController {
	
	@Resource(name="homePageServiceImpl")
	private HomePageService homePageServiceImpl;
	@Resource(name="goodsServiceImpl")
	private GoodsService goodsServiceImpl;
	@Resource(name="brandServiceImpl")
	private BrandService brandServiceImpl;
	@Resource(name="gManageServiceImpl")
	private GManageService gManageServiceImpl;
	@Resource(name="dataServiceImpl")
	private DataService dataServiceImpl;
	@Resource(name="areaAddrServiceImpl")
	private AreaAddrService areaAddrServiceImpl;
		
	
	/**
	 * @Title: getGoods 
	 * @Description: 商详页面(new)
	 * @param    String goodsID
	 * @return    likeGoods,goodsDM(注意:如果goodsDM.isActivity == 1表示活动商品,goodsDM.originalPrice1,originalPrice2表示原价,
	 * 							goodsDM.timedActivity中存有该商品的活动时间),sgIDMap,property,map6,advertiseMap,goodsState,province,brand,pd
	 * @author SSY
	 * @date 2018年5月15日 上午9:34:02
	 */
	@RequestMapping(value="/goGoodsdetail", produces="application/json;charset=UTF-8")
	public String getGoods(ModelMap map, String goodsID,HttpServletResponse response,HttpServletRequest request)throws Exception{
		if (Tools.isEmpty(goodsID)) {
			throw new Exception("没有此商品!");
		}
		PageData pd = this.getPageData();
		pd.put("goodsID", goodsID);
		try{
			GoodsManage goodsDM = gManageServiceImpl.getGoodsDM(goodsID);
			if (null==goodsDM) {//转发至一个不存在的路径,让其404
				request.getRequestDispatcher("/xxx/xxx").forward(request,response);
			}
			if (goodsDM.getIsActivity()==Const.ACTIVITY_GOODS) {//如果是活动中商品,查询活动结束时间
				TimedActivity timedActivity = gManageServiceImpl.getActivityGoods(goodsID);
				goodsDM.setTimedActivity(timedActivity);
			}
			int goodsState = goodsDM.getGoodsState();
			int shipType0 = goodsDM.getShipType();
			Map<Integer, String> sgIDMap = new HashMap<>();      //key为商品及关联商品贸易方式，value为商品及关联商品ID
			sgIDMap.put(shipType0, goodsID);
			String relate1 = goodsDM.getRelate1GID();
			if (Tools.notEmptys(relate1)) {
				int r1ShipType = gManageServiceImpl.getShipType(relate1);
				sgIDMap.put(r1ShipType, relate1);
			}
			String relate2 = goodsDM.getRelate2GID();
			if (Tools.notEmptys(relate2)) {
				int r2ShipType = gManageServiceImpl.getShipType(relate2);
				sgIDMap.put(r2ShipType, relate2);
			}
			int category2IDI = goodsDM.getGoodsCommon().getCategory2ID();
			String category2ID = String.valueOf(category2IDI);
			if(Tools.notEmptys(category2ID)){
				pd.put("category2ID", category2ID);
				List<PageData>	likeGoods = goodsServiceImpl.getLikeGoods(pd);	//根据二级类目ID得到相关推荐商品
				map.put("likeGoods",likeGoods);
			}
			int brandID = goodsDM.getGoodsCommon().getBrandID();
			Brand brand =  brandServiceImpl.getBrandByID(String.valueOf(brandID));							//获取当前商品品牌信息
			String property1 = goodsDM.getGoodsDetails().getProperty();
			List<String> property = new ArrayList<String>();
			if (Tools.notEmpty(property1)) {
				String property2[] = property1.split(",");
				property = new ArrayList<String>(Arrays.asList(property2));			//商品属性
			}
			String map61 = goodsDM.getGoodsDetails().getMap6();
			List<String> map6 = new ArrayList<String>();
			if (Tools.notEmpty(map61)) {
				String map62[] = map61.split(",");
				map6 = new ArrayList<String>(Arrays.asList(map62));             					//商品六面图
			}
			String advertiseMap1 = goodsDM.getGoodsDetails().getAdvertiseMap();
			List<String> advertiseMap = new ArrayList<String>();
			if (Tools.notEmpty(advertiseMap1)) {
				String advertiseMap2[] = advertiseMap1.split(",");
				advertiseMap = new ArrayList<String>(Arrays.asList(advertiseMap2));             	//商品广告图
			}
			List<PageData> province = areaAddrServiceImpl.getProvince();									//获取省份
			map.put("sgIDMap",sgIDMap);											//邮寄方式对应的商品id
			map.put("property",property);											//商品私有属性
			map.put("map6",map6);													//商品六面图
			map.put("advertiseMap",advertiseMap);									//商品广告图
			map.put("goodsDM",JSON.toJSON(goodsDM));
			map.put("goodsState",goodsState);
			map.put("province",province);
			map.put("brand",brand);
			map.put("pd",pd);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("【ERROR】"+DateUtil.getTime()+"商详查询商品执行异常!");
			throw new Exception("没有此商品!");
		}
		return "system/product/product";
	}
	
//	/**带数据跳转到商品详情页
//	 * @param goodsID
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value="/goodsDetail", produces="application/json;charset=UTF-8")
//	public ModelAndView goGoodsdetail()throws Exception{
//		ModelAndView mv = this.getModelAndView();
//		PageData pd = new PageData();
//		pd = this.getPageData();
//		String msg = "";
//		String goodsID = Tools.notEmptys(pd.getString("goodsID")) ? pd.getString("goodsID").replace(" ", "") : null;
//		if(goodsID != null){
//			pd.put("goodsID", goodsID);
//			try{
//				GoodsManage goodsDM = gManageServiceImpl.getGoodsDM(goodsID);
//				int goodsState = goodsDM.getGoodsState();
//				int shipType0 = goodsDM.getShipType();
//				Map<Integer, String> sgIDMap = new HashMap<>();      //key为商品及关联商品贸易方式，value为商品及关联商品ID
//				sgIDMap.put(shipType0, goodsID);
//				String relate1 = goodsDM.getRelate1GID();
//				if (Tools.notEmptys(relate1)) {
//					int r1ShipType = gManageServiceImpl.getShipType(relate1);
//					sgIDMap.put(r1ShipType, relate1);
//				}
//				String relate2 = goodsDM.getRelate2GID();
//				if (Tools.notEmptys(relate2)) {
//					int r2ShipType = gManageServiceImpl.getShipType(relate2);
//					sgIDMap.put(r2ShipType, relate2);
//				}
//				int category2IDI = goodsDM.getGoodsCommon().getCategory2ID();
//				String category2ID = String.valueOf(category2IDI);
//				if(Tools.notEmptys(category2ID)){
//					pd.put("category2ID", category2ID);
//					List<PageData>	likeGoods = goodsServiceImpl.getLikeGoods(pd);	//根据二级类目ID得到相关推荐商品
//					mv.addObject("likeGoods",likeGoods);
//				}
//				int brandID = goodsDM.getGoodsCommon().getBrandID();
//				Brand brand =  brandServiceImpl.getBrandByID(String.valueOf(brandID));							//获取当前商品品牌信息
//				String property1 = goodsDM.getGoodsDetails().getProperty();
//				List<String> property = new ArrayList<String>();
//				if (Tools.notEmpty(property1)) {
//					String property2[] = property1.split(",");
//					property = new ArrayList<String>(Arrays.asList(property2));			//商品属性
//				}
//				String map61 = goodsDM.getGoodsDetails().getMap6();
//				List<String> map6 = new ArrayList<String>();
//				if (Tools.notEmpty(map61)) {
//					String map62[] = map61.split(",");
//					map6 = new ArrayList<String>(Arrays.asList(map62));             					//商品六面图
//				}
//				String advertiseMap1 = goodsDM.getGoodsDetails().getAdvertiseMap();
//				List<String> advertiseMap = new ArrayList<String>();
//				if (Tools.notEmpty(advertiseMap1)) {
//					String advertiseMap2[] = advertiseMap1.split(",");
//					advertiseMap = new ArrayList<String>(Arrays.asList(advertiseMap2));             	//商品广告图
//				}
//				List<PageData> province = areaAddrServiceImpl.getProvince();									//获取省份
//				mv.addObject("sgIDMap",sgIDMap);											//邮寄方式对应的商品id
//				mv.addObject("property",property);											//商品私有属性
//				mv.addObject("map6",map6);													//商品六面图
//				mv.addObject("advertiseMap",advertiseMap);									//商品广告图
//				mv.addObject("goodsDM",goodsDM);
//				mv.addObject("goodsState",goodsState);
//				mv.addObject("province",province);
//				mv.addObject("brand",brand);
//				mv.addObject("pd",pd);
//				mv.setViewName("system/product/product");
//			}catch(Exception e){
//				e.printStackTrace();
//				msg = "网络异常，请稍后重试或联系客服！";
//				logger.info("CGDPC:"+msg);
//				throw new Exception(msg);
//			}
//		}else{
//			msg = "没有此商品！";
//			logger.info("CGDPC:参数错误，商品详情页商品id为null");
//			throw new Exception(msg);
//		}
//		mv.addObject("msg",msg);
//		return mv;
//	}
//	
	/**详情页获取行业数据
	 * @param String province, BigDecimal uWeight, int num
	 * @return BigDecimal bgPosttage
	 * @throws Exception
	 */
	@RequestMapping(value="/getIndustryData", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getIndustryData(String goodsID)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<PageData> jpriceData0 = dataServiceImpl.getJPriceData(goodsID);							//获取四个行业数据
		List<PageData> tpriceData0 = dataServiceImpl.getTPriceData(goodsID);
		List<PageData> tStoreData0 = dataServiceImpl.getTStoreData(goodsID);
		List<PageData> apriceData0 = dataServiceImpl.getAPriceData(goodsID);
		String jpriceData = null;
		String tpriceData = null;
		String apriceData = null;
		String tStoreData = null;
		for(PageData j : jpriceData0){
			jpriceData = j.getString("jprice1") + ',' + j.getString("jprice2") + ',' + j.getString("jprice3") + ',' + j.getString("jprice4")
							 + ',' + j.getString("jprice5") + ',' + j.getString("jprice6") + ',' + j.getString("jprice7")+ ',' + j.getString("jprice8")
							 + ',' + j.getString("jprice9") + ',' + j.getString("jprice10") + ',' + j.getString("jprice11")+ ',' + j.getString("jprice12");
		}
		for(PageData t : tpriceData0){
			tpriceData = t.getString("tprice1") + ',' + t.getString("tprice2") + ',' + t.getString("tprice3") + ',' + t.getString("tprice4")
							 + ',' + t.getString("tprice5") + ',' + t.getString("tprice6") + ',' + t.getString("tprice7")+ ',' + t.getString("tprice8")
							 + ',' + t.getString("tprice9") + ',' + t.getString("tprice10") + ',' + t.getString("tprice11")+ ',' + t.getString("tprice12");
		}
		for(PageData a : apriceData0){
			apriceData = a.getString("aprice1") + ',' + a.getString("aprice2") + ',' + a.getString("aprice3") + ',' + a.getString("aprice4")
							 + ',' + a.getString("aprice5") + ',' + a.getString("aprice6") + ',' + a.getString("aprice7")+ ',' + a.getString("aprice8")
							 + ',' + a.getString("aprice9") + ',' + a.getString("aprice10") + ',' + a.getString("aprice11")+ ',' + a.getString("aprice12");
		}
		for(PageData ts : tStoreData0){
			tStoreData = ts.getString("tsnum1") + ',' + ts.getString("tsnum2") + ',' + ts.getString("tsnum3") + ',' + ts.getString("tsnum4")
							 + ',' + ts.getString("tsnum5") + ',' + ts.getString("tsnum6") + ',' + ts.getString("tsnum7")+ ',' + ts.getString("tsnum8")
							 + ',' + ts.getString("tsnum9") + ',' + ts.getString("tsnum10") + ',' + ts.getString("tsnum11")+ ',' + ts.getString("tsnum12");
		}
		map.put("jpriceData",jpriceData);
		map.put("tpriceData",tpriceData);
		map.put("tStoreData",tStoreData);
		map.put("apriceData",apriceData);
		return AppUtil.returnObject(pd, map);	
	}
	
	/**保税仓、国内现货邮费计算
	 * @param String province, BigDecimal uWeight, int num
	 * @return BigDecimal bgPosttage
	 * @throws Exception
	 */
	@RequestMapping(value="/getPosttage", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getPosttage()throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String province = pd.getString("province")!= null && !"".equals(pd.getString("province").replace(" ", "") ) ? pd.getString("province").replace(" ", "") : null;
		String weight = pd.getString("weight")!= null && !"".equals(pd.getString("weight").replace(" ", "") ) ? pd.getString("weight").replace(" ", "") : null;
		String buyNum = pd.getString("buyNum")!= null && !"".equals(pd.getString("buyNum").replace(" ", "") ) ? pd.getString("buyNum").replace(" ", "") : null;
		String shipType = pd.getString("shipType") != null && !"".equals(pd.getString("shipType").replace(" ", "") ) ? pd.getString("shipType").replace(" ", "") : null;
		BigDecimal uWeight = new BigDecimal(weight);
		BigDecimal posttage = new BigDecimal(0);
		int num = Integer.valueOf(buyNum.replace(" ", ""));
		int shipType1 = Integer.valueOf(shipType.replace(" ", ""));
		if (shipType1 == 1 ||shipType1 == 3) {
			posttage = PostageMath.getBGPosttage(province, uWeight, num);
		}else if(shipType1 == 2){
			posttage = PostageMath.getHPosttage(uWeight, num);
		}
		map.put("posttage", posttage);
		map.put("pd", pd);
		System.out.println();
		return AppUtil.returnObject(pd, map);	
	}
	
	
	/**
	 * 数据统计 --- 商品详情页 --统计用户访问该类目的次数,统计该商品该时间段被浏览的次数;
	 * @param String goodsID
	 */
	@RequestMapping(value="/statisticGoodsDetail", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object statisticGoodsDetail(String goodsID) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			GoodsManage goodsManage = gManageServiceImpl.getGoodsDM(goodsID);
			//数据统计--用户浏览该类目商品次数+1
			if (null!=goodsManage&&null!=Jurisdiction.getCustomerID()) {
				gManageServiceImpl.addCustomerCategoryClickNum(goodsManage);
			}
			//数据统计--商品浏览次数+1
			if (null!=goodsManage){
				gManageServiceImpl.addGoodsClickNum(goodsManage);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("CGDPC:商品详情页--数据统计--系统执行异常!");
		}
		map.put("result", "success");
		return map;
	}
	
	
}
