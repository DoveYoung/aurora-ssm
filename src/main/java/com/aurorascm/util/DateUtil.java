package com.aurorascm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * 日期处理
 * 
 * @author BYG 2017-7-21
 * @version 1.0
 */
public class DateUtil {

	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private final static SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");
	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 获取yyyyMMddHHmmss格式
	 * 
	 * @return
	 */
	public static String getSdfTimes() {
		return sdfTimes.format(new Date());
	}

	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}

	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays() {
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	 * 获取yyyy-MM格式
	 * @return
	 */
	public static String getMonthMM() {
		return sdfMonth.format(new Date());
	}
	
	/**
	 * 获取当前月MM
	 * @return
	 */
	public static int getMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}
	
	/**
	 * 获取当前小时
	 * @return
	 */
	public static int getHour() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		return hour;
	}
	
	/**
	 * 获取当天日期DD;day_of_month
	 * @return
	 */
	public static int getDayDD() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}
	
	/**
	 * @Title: compareDate
	 * @Description: (日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws @author
	 *             fh
	 */
	public static boolean compareDate(String s, String e) {
		if (fomatDate(s) == null || fomatDate(e) == null) {
			return false;
		}
		return fomatDate(s).getTime() >= fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// long aa=0;
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24))
					/ 365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}

	/**
	 * <li>功能描述：时间相减得到天数
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 * @author Administrator
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate = null;
		java.util.Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		// System.out.println("相隔的天数="+day);

		return day;
	}

	/**
	 * 得到n天之后的日期
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();
		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);
		return dateStr;
	}
	
	/**
	 * 得到n天之前的日期
	 * 
	 * @param days
	 * @return
	 */
	public static String getBeforeDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, -daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();
		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);
		return dateStr;
	}

	/**
	 * 得到n天之后是周几
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);
		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);
		return dateStr;
	}

	/**
	 *  当前时间第n个月;负数是往前,返回的是月份YYYY-MM ;
	 */
	@Test
// public void getDayBefore() {
	public static String getBeforeMonth(int months) {
		Calendar c = Calendar.getInstance();
		 c.setTime(new Date());
	        c.add(Calendar.MONTH, months);
	        Date m = c.getTime();
	        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM");
	        String mon = sdf.format(m);
	        System.out.println("过去一个月："+mon);
		return mon;
	}
	
	/**
	 * 获取当前时间时辰,按照12个时间段,12个时辰;
	 */
	public static String getTwoHour(){
		String hour = "";
		switch(DateUtil.getHour()){//访问时间段
		case 0: case 1:
			hour = "hour1";
			break;
		case 2: case 3:
			hour = "hour2";
			break;
		case 4: case 5:
			hour = "hour3";
			break;
		case 6: case 7:
			hour = "hour4";
			break;
		case 8: case 9:
			hour = "hour5";
			break;
		case 10: case 11:
			hour = "hour6";
			break;
		case 12: case 13:
			hour = "hour7";
			break;
		case 14: case 15:
			hour = "hour8";
			break;
		case 16: case 17:
			hour = "hour9";
			break;
		case 18: case 19:
			hour = "hour10";
			break;
		case 20: case 21:
			hour = "hour11";
			break;
		case 22: case 23:
			hour = "hour12";
			break;
		} 
		return hour;
	}
	
	
}
