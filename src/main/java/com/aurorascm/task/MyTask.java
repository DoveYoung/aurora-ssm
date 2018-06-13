package com.aurorascm.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aurorascm.redis.RedisUtil;
import com.aurorascm.util.AppUtil;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.Logger;


/**
 * redis cache 工具类
 *
 * @author BYG 2017-11-13
 * @version 1.0
 */
@Component("myTask")
public class MyTask {

//	@Resource(name="redisUtil")
//	private RedisUtil redisUtil;
//	protected static Logger logger = Logger.getLogger(AppUtil.class);
//		
//	/**
//	 * 10分钟未接订单提醒
//	 */
//	@Scheduled(cron = "0/1 * * * * ?")//秒域必须写0，若写*系统会默认分域为秒域
//    public void missedOrderRemind(){
//		redisUtil.get("shHomeHotWordList");
//		redisUtil.get("shCategory1");
//		redisUtil.get("shCategory23");
//		redisUtil.get("shHotSellList");
//		redisUtil.get("shHotSellCategory1");
//		redisUtil.get("shHomeBHotSell");
//		redisUtil.get("shHomeHHotSell");
//		redisUtil.get("shHomePurchase");
//		redisUtil.get("shLessBuyBrand");
//		redisUtil.get("shPurchaseCategory1");
//		redisUtil.get("shHomeSpecial");
//		redisUtil.get("shHomeHotWordList");
//		int count = 0;
//		count += 1;
//		System.out.println(String.valueOf(count) + "**************************************************");
//    }

}
