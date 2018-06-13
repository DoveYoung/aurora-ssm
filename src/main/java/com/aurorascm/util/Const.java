package com.aurorascm.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.springframework.context.ApplicationContext;

/** 转换
 * @author BYG 2017-7-21
 * @version 1.0
 */
public class Const {
	public static final String SESSION_VERIFICATION_CODE = "sessionVerificationCode";	//验证码
	public static final String SESSION_CUSTOMER = "sessionCustomer";					//session用的用户
	public static final String SESSION_CUSTOMER_EMAIL = "customerEmail";					//session用的邮箱
	public static final String SESSION_CUSTOMER_ID = "customerID";					//session用的用户id
	public static final String SESSION_CUSTOMER_PASSWORD = "customerPassword";		
	public static final String SESSION_CUSTOMER_NAME = "customerName";					//用户名
	/**
	 * 用户Session购物车商品数量
	 */
	public static final String SESSION_CUSTOMER_CART_NUM = "customerCartNum";
	
	public static final String TRUE = "T";
	public static final String FALSE = "F";
	public static final String LOGIN = "/registerLogin/goLoginPage";				    //登录地址
	public static final String EMAIL = "admin/config/EMAIL.txt";		//邮箱服务器配置路径
	public static final String WEBSOCKET = "admin/config/WEBSOCKET.txt";//WEBSOCKET配置路径
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/";	//图片上传路径
	public static final String FILEPATHFILE = "uploadFiles/file/";		//文件上传路径
	public static final String FILEPATHTWODIMENSIONCODE = "uploadFiles/twoDimensionCode/"; //二维码存放路径
	public static final String INTERCEPTOR_PATH = ".*/((customerAttention)|(receiverAddress)|(customerContract)|(customerInfo)|"
			+ "(customerInquiry)|(customerMW)|(personal)|(cartPage)|(buySettleFCart)|(buySettleFLB)|(login)|(websocket)).*";	//对匹配该值的访问路径拦截（正则）
	//public static final String INTERCEPTOR_PATH = ".*/((buySettleFLB/goFSettleFLB)|(cartPage/goCartPage)).*";	//不对匹配该值的访问路径拦截（正则）
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
	
	/**
	 * APP Constants
	 */
	//app注册接口_请求协议参数)
	public static final String[] APP_REGISTERED_PARAM_ARRAY = new String[]{"countries","uname","passwd","title","full_name","company_name","countries_code","area_code","telephone","mobile"};
	public static final String[] APP_REGISTERED_VALUE_ARRAY = new String[]{"国籍","邮箱帐号","密码","称谓","名称","公司名称","国家编号","区号","电话","手机号"};
	
	//app根据用户名获取会员信息接口_请求协议中的参数
	public static final String[] APP_GETAPPUSER_PARAM_ARRAY = new String[]{"USERNAME"};
	public static final String[] APP_GETAPPUSER_VALUE_ARRAY = new String[]{"用户名"};
	
	/**
	 * 1.banner专题;2.保税仓专题;10000.类目1---大额采购母婴儿童专题;20000.类目2---美妆护类专题;30000.类目3---厨卫家居类专题;40000.类目4---营养保健;50000. 类目5--数码家电
	 */
	/**
	 * 专题归属模块 1.banner专题;
	 */
	public static final int SPECIAL_BANNER_MODULE = 1;  
	/**
	 * 专题归属模块 2.保税仓专题;
	 */
	public static final int SPECIAL_BONDED_MODULE = 2;  
	/**
	 * 专题归属模块 10000.类目1---大额采购母婴儿童专题
	 */
	public static final int SPECIAL_CATEGORY1_MODULE = 10000;  
	/**
	 * 专题归属模块 20000.类目2---美妆护类专题;
	 */
	public static final int SPECIAL_CATEGORY2_MODULE = 20000;  
	/**
	 * 专题归属模块 30000.类目3---厨卫家居类专题
	 */
	public static final int SPECIAL_CATEGORY3_MODULE = 30000;  
	/**
	 * 专题归属模块 40000.类目4---营养保健类专题
	 */
	public static final int SPECIAL_CATEGORY4_MODULE = 40000;  
	/**
	 * 专题归属模块 50000.类目5--数码家电类专题
	 */
	public static final int SPECIAL_CATEGORY5_MODULE = 50000;  
	
	/**
	 * 首页关键词类型1.搜索框搜索关键词; 2.其他关键词; 3.保税仓关键词; 10000.类目1--母婴儿童类目商品关键词;20000.类目2---美妆护肤 30000.类目3---厨卫家居类专题;40000.类目4---营养保健;50000. 类目5--数码家电
	 */
	/**
	 *  首页关键词类型1.搜索框搜索关键词;
	 */
	public static final int KEYWORD_SEARCH = 1;
	/**
	 *  首页关键词类型2.其他关键词
	 */
	public static final int KEYWORD_OVERSEA = 2;
	/**
	 *  首页关键词类型3.保税仓关键词;
	 */
	public static final int KEYWORD_BONDED = 3;
	/**
	 *  首页关键词类型4. 类目1--母婴儿童类目商品关键词
	 */
	public static final int KEYWORD_CATEGORY1 = 10000;
	/**
	 *  首页关键词类型5.类目2---美妆护肤关键词
	 */
	public static final int KEYWORD_CATEGORY2 = 20000;
	/**
	 *  首页关键词类型 6.类目3---厨卫家居类关键词;
	 */
	public static final int KEYWORD_CATEGORY3 = 30000;
	/**
	 *  首页关键词类型7.类目4---营养保健关键词;
	 */
	public static final int KEYWORD_CATEGORY4 = 40000;
	/**
	 *  首页关键词类型8. 类目5--数码家电关键词;
	 */
	public static final int KEYWORD_CATEGORY5 = 50000;
	
//	/**
//	 *  所有首页展示一级类目常量;
//	 */
//	public static final int[] HOME_FLOOR_CATEGORYIDS = {Const.SPECIAL_CATEGORY1_MODULE,Const.SPECIAL_CATEGORY2_MODULE,Const.SPECIAL_CATEGORY3_MODULE,Const.SPECIAL_CATEGORY4_MODULE,Const.SPECIAL_CATEGORY5_MODULE};
//	
	/**
	 *  首页品类展示一级类目常量字符串;mybatis 查询常量使用
	 */
	public static final String CATEGORYID_FLOORS = "10000,20000,30000,40000,50000";
	
	
	/**
	 * 活动价格商品标记
	 */
	public static final int ACTIVITY_GOODS = 1;
	
	/**
	 * @author BYG 2018.4.27
	 * 格式化金额相关数据用（格式：0.00）
	 * 调用方法：Const.DF.format(param);
	 */
	public static final DecimalFormat DF = new DecimalFormat("0.00");
	
	/**
	 * 税率
	 */
	public static final BigDecimal TAX_RATE = new BigDecimal("0.119");
	
	/**
	 * 订单每页数量
	 */
	public static final int PAGE_SIZE_ORDER = 10;
	/**
	 * 订单类型    2.个人订单; 
	 */
	public static final int MY_ORDER_TYPE = 2;
	
	/**
	 * 订单类型   1.微仓采购订单 
	 */
	public static final int PURCHASE_ORDER_TYPE = 1;
	/**
	 * 订单类型  3.微仓销售订单
	 */
	public static final int SELL_ORDER_TYPE = 3;
	
	/**
	 * 贸易方式类型编码:保税仓发货1
	 */
	public static final int BONDED_TRADE_TYPE = 1;
	
	/**
	 * 贸易方式类型编码:海外直邮2
	 */
	public static final int DIRECT_TRADE_TYPE = 2;
	
	/**
	 * 贸易方式类型编码:一般贸易3
	 */
	public static final int GENERAL_TRADE_TYPE = 3;
	
}
