package com.aurorascm.util;

import com.alibaba.druid.pool.DruidDataSourceStatLoggerAdapter;
import com.alibaba.druid.pool.DruidDataSourceStatValue;
import com.alibaba.druid.pool.DruidDataSourceStatLogger ;
public class MyDruidLogger extends DruidDataSourceStatLoggerAdapter implements DruidDataSourceStatLogger {
	public void log(DruidDataSourceStatValue statValue){  
		System.out.println("getConnectCount==="+statValue.getConnectCount());  
		System.out.println("getActiveCount==="+statValue.getActiveCount());
	} 
}
