package com.aurorascm.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/** 邮费计算
 * @author BYG 2017-7-21
 * @version 1.0
 */
public class PostageMath{
	
	
	/**
	 * @author BYG 2018.4.27
	 * 获取海外直邮邮费---货币单位人民币
	 */
	public static BigDecimal getHPosttage1(BigDecimal uWeight, int num) {
		BigDecimal hPosttage;
		Double weight = uWeight.doubleValue()*num;
		Double exchangeRate = 8.00;//欧元兑换人民币汇率
		if (weight <= 0.5) {
			Double y = 2.86 * exchangeRate;
			hPosttage = new BigDecimal(y);
		} else {
			Double y = (2.86 + 1.24 * ((weight-0.5) / 0.5)) * exchangeRate;
			hPosttage = new BigDecimal(y);
		}
		Const.DF.format(hPosttage);//格式化为0.00格式
		return hPosttage;
	}
	
	/**获取海外直邮邮费
	 * @return
	 */
	public static BigDecimal getHPosttage(BigDecimal uWeight, int num) {// weight.compareTo(BigDecimal.valueOf(1.00)): 结果是-1 小于;0 等于;1 大于
		BigDecimal n = new BigDecimal(String.valueOf(num));
		BigDecimal hPosttage = new BigDecimal("0");
		BigDecimal bdWeight = n.multiply(uWeight);
		Double weight = bdWeight.doubleValue();
		if(weight <= 1){//小于等于1
			hPosttage = new BigDecimal("56.00");
		}else if(weight > 1 && weight <= 2){
			hPosttage = new BigDecimal("96.00");
		}else if(weight > 2 && weight <= 3){
			hPosttage = new BigDecimal("144.00");
		}else if(weight > 3 && weight <= 4){
			hPosttage = new BigDecimal("172.00");
		}else if(weight > 4 && weight <= 5){
			hPosttage = new BigDecimal("200.00");
		}else if(weight > 5 && weight <= 6){
			hPosttage = new BigDecimal("228.00");
		}else if(weight > 6 && weight <= 7){
			hPosttage = new BigDecimal("256.00");
		}else if(weight > 7 && weight <= 8){
			hPosttage = new BigDecimal("284.00");
		}else if(weight > 8 && weight <= 9){
			hPosttage = new BigDecimal("312.00");
		}else if(weight > 9 && weight <= 10){
			hPosttage = new BigDecimal("340.00");
		}else if(weight > 10 && weight <= 11){
			hPosttage = new BigDecimal("368.00");
		}else if(weight > 11 && weight <= 12){
			hPosttage = new BigDecimal("396.00");
		}else if(weight > 12 && weight <= 13){
			hPosttage = new BigDecimal("424.00");
		}else if(weight > 13 && weight <= 14){
			hPosttage = new BigDecimal("452.00");
		}else if(weight > 14 && weight <= 15){
			hPosttage = new BigDecimal("480.00");
		}else if(weight > 15 && weight <= 16){
			hPosttage = new BigDecimal("508.00");
		}else if(weight > 16 && weight <= 17){
			hPosttage = new BigDecimal("536.00");
		}else if(weight > 17 && weight <= 18){
			hPosttage = new BigDecimal("564.00");
		}else if(weight > 18 && weight <= 19){
			hPosttage = new BigDecimal("592.00");
		}else if(weight > 19 && weight <= 20){
			hPosttage = new BigDecimal("620.00");
		}else if(weight > 20){
			hPosttage = new BigDecimal("88888.00");
		}
		return hPosttage;
	}
	
	
	/**获取保税仓/国内直邮邮费
	 * @return
	 */
	public static BigDecimal getBGPosttage(String province, BigDecimal uWeight, int num) {
		BigDecimal bgPosttage = new BigDecimal("0");
		BigDecimal n = new BigDecimal(String.valueOf(num));
		BigDecimal weight = n.multiply(uWeight);
		// anhui 7/1; neimeng 12/7 内蒙; xinjiang 18/14 新疆; xizang 20/15 西藏
		String[] s51 = {"jiangSu", "zheJiang","shangHai"};//江苏 浙江 上海
		String[] s75 = {"guangDong", "fuJian","shanDong","jiangXi","beiJing"};//广东 福建 山东 江西 北京 
		String[] s85 = {"huNan", "huBei","heNan","heBei","tianJin"}; //湖南 湖北 河南 河北 天津 
		String[] s86 = {"haiNan", "guiZhou","siChuan","chongQing"};//海南 贵州 四川 重庆
		//shanXi山西 shanXI陕西 广西 吉林 辽宁 黑龙江 云南 宁夏 青海 甘肃
		String[] s106 = {"shanXi", "shanXI","guangXi","jiLin","liaoNing","heiLongJiang", "yunNan","ningXia", "qingHai","ganSu"};		
		List<String> s51l = Arrays.asList(s51);
		List<String> s75l = Arrays.asList(s75);
		List<String> s85l = Arrays.asList(s85);
		List<String> s86l = Arrays.asList(s86);
		List<String> s106l = Arrays.asList(s106);
		BigDecimal one = new BigDecimal("1");
		BigDecimal five = new BigDecimal("5");
		BigDecimal six = new BigDecimal("6");
		BigDecimal seven = new BigDecimal("7");
		BigDecimal eight = new BigDecimal("8");
		BigDecimal ten = new BigDecimal("10");
		BigDecimal twelve = new BigDecimal("12");
		BigDecimal fourteen = new BigDecimal("14");
		BigDecimal fifteen = new BigDecimal("15");
		BigDecimal eighteen = new BigDecimal("18");
		BigDecimal twenty = new BigDecimal("20");
		BigDecimal w1 = weight.subtract(one);
		if (weight.compareTo(one) == -1){
			w1 = one;
		}
		if(province.equals("anHui")){
			bgPosttage = w1.add(seven);				//7 + weight -1;
			if (w1.compareTo(one) == 0){
				bgPosttage = seven;
			}
		}else if(province.equals("neiMengGu")){
			bgPosttage = w1.multiply(seven).add(twelve);//12 + (weight -1)*7;
			if (w1.compareTo(one) == 0){
				bgPosttage = twelve;
			}
		}else if(province.equals("xinJiang")){
			bgPosttage = w1.multiply(fourteen).add(eighteen);//18 + (weight -1)*14;
			if (w1.compareTo(one) == 0){
				bgPosttage = eighteen;
			}
		}else if(province.equals("xiZang")){
			bgPosttage = w1.multiply(fifteen).add(twenty);//20 + (weight -1)*15;
			if (w1.compareTo(one) == 0){
				bgPosttage = twenty;
			}
		}else if(s51l.contains(province)){
			bgPosttage = w1.add(five);					//5 + weight -1;
			if (w1.compareTo(one) == 0){
				bgPosttage = five;
			}
		}else if(s75l.contains(province)){
			bgPosttage = w1.multiply(five).add(seven);//7 + (weight -1)*5;
			if (w1.compareTo(one) == 0){
				bgPosttage = seven;
			}
		}else if(s85l.contains(province)){
			bgPosttage = w1.multiply(five).add(eight);// 8 + (weight -1)*5;
			if (w1.compareTo(one) == 0){
				bgPosttage = eight;
			}
		}else if(s86l.contains(province)){
			bgPosttage = w1.multiply(six).add(eight);//8 + (weight -1)*6;
			if (w1.compareTo(one) == 0){
				bgPosttage = eight;
			}
		}else if(s106l.contains(province)){
			bgPosttage = w1.multiply(six).add(ten);//10 + (weight -1)*6;
			if (w1.compareTo(one) == 0){
				bgPosttage = ten;
			}
		}
		return bgPosttage;
	}
		
}
