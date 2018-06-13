package com.aurorascm.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/** 阿里云短信服务
 * @author BYG 2017-7-21
 * @version 1.0
 */
public class AliyunSMS{
	
	/**
	 * 获取IAcsClient对象
	 * @param templateCode 		模板码
	 * @param mobile			手机号
	 * @param templateParam		验证码参数
	 * @return
	 * @throws ClientException
	 */
	public static void sendSMS(String templateCode , String mobile , String templateParam) throws ClientException {
	    // 初始化client对象
	    IAcsClient client = initClient();
	    // 短信模板请求对象
	    SendSmsRequest request = null;
	    // 根据短信模板code来获取不同的短信模板请求对象
	    request = getSMS(templateCode, mobile , templateParam);
	    // 发送短信
	    SendSmsResponse response = client.getAcsResponse(request);
	    // 打印短信的消息
	    System.out.println("_____________发送SMS_72780019短信收到的响应信息_______________");
	    System.out.println("请求的ID:" + response.getRequestId());
	    System.out.println("请求的状态码:" + response.getCode());
	    System.out.println("请求的状态码描述:" + response.getMessage());
	    System.out.println("请求的回执ID:" + response.getBizId());
	}
	
	/**
	 * 获取IAcsClient对象
	 * @return
	 * @throws ClientException
	 */
	private static IAcsClient initClient() throws ClientException {
	    System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
	    System.setProperty("sun.net.client.defaultReadTimeout", "10000");
	    // 初始化ascClient需要的几个参数
	    final String product = "Dysmsapi";// 短信API产品名称
	    final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名
	    // 秘钥key和secret
	    final String appkey = "LTAIYDfcv68Gw1Ep";
	    final String appSecret = "H0JdDZ3eUVPZMfyRa0X9zXQEY5WgCI";
	    // 初始化ascClient,暂时不支持多region
	    IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", appkey, appSecret);
	    DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
	    IAcsClient acsClient = new DefaultAcsClient(profile);
	    return acsClient;
	}

	/**
	 * 获取SMS_72780019短信模板对应的请求
	 * @param String mobile , String code
	 * @return
	 */
	private static SendSmsRequest getSMS(String templateCode ,String mobile , String code) {
	     //组装请求对象
	     SendSmsRequest request = new SendSmsRequest();
	     //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为20个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
	     request.setPhoneNumbers(mobile);
	     //必填:短信签名-可在短信控制台中找到
	     request.setSignName("北极光供应链");
	     //必填:短信模板-可在短信控制台中找到
	     request.setTemplateCode(templateCode);
	     //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
	     //request.setTemplateParam("{\"code\":\"code\"}");
	     request.setTemplateParam("{\"code\":\""+code+"\"}");
	     //request.setSmsParamString("{\"code\":\""+code+"\",\"product\":\""+product+"\"}");
		return request;
	 }	
}
