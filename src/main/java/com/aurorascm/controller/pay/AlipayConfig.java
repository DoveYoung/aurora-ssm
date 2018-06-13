package com.aurorascm.controller.pay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-10-16
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号2017101284523191/2017101209269071
	public static String app_id = "2017101209269071";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCkTn/vutARCdWZpMwwwszt/ndeie7iAsct5lLFbzJG8Zc+RjvmlnWqjj3rPb6rFPR5tdvoduepNA6sj+8P7KqT6bhReflajVjPHQI4phBcF8YyIVxZj/ehwADKeLVTWDtycBbbdxUpVyeY4Ip/z/gbUkqQDQUgTT5j69RrOLIXbG1fHnZdVGEwn4ggti+hnjGM2ArF+/kCLTN1TxLx2hfVje7a0atFImR769SDZQYXXCqXR8tOQmVkSKVakHnA9RYxZ6zDfInyJTbcBBhclDVghq09pM0l/dFJ/PFFjSlBr69eZe8GvegUjWyJF2bbgf9J6Ga4riawADD2SVkypbzxAgMBAAECggEBAII4aPMx5xep6K1lzCNVMJ/rDuZRgY+EziGVqsIpl/pcHRdRCMZ/NvCe3XokrInhCMWxmYGLQ5150at4Q+smEic6lEeW2UaoQducTRdinhKvEPjIMe4VyRhcGQRfWK6efjb7mVIE9jOWjJ6AT6Ruyl+0/71dZFOuhKUi0m7MN3GRyITf+EuVlVS4QCzvU9z9QLnVa3Y0q9FaWKPKcf4f8lLzW4udoPACp8/VC6Jg1NLgoEzykQSZdd9k+jduccpRsoYTtRa5XViMam0SCsKJdPEbzR9jcEIXOFuJVIAeKOOQCxhCjJPpGKqAkBZzezZrUWj5BH5OOHmoXnGfxRaSUgECgYEA635/YMt3Sb9Jc0qAbhe7KvvLb/Li9OYev172S48eeP6FkXs80HlGYy++EbIYpRoSXi/1dMh1sdg1FA9DJlPeh9HQcWIvoHXUWMX+Pp8j0aHvGRnLlYqKLT+ydfcuD3QwtX/elaN1R9hAtm78KpuAfDJnjUWN0xcWOK25kaCgiyECgYEAsp0heKr0wmvzhzrxz3SjhWpS73iuPcKXJ7J6DGYmFRqYsEJI21cbLTv4FKBfxGq448hJ9t8EGimr3spf6m1K7xMzPkQioO+fjwwohQcyC2Z2viQmvLBEn75chTmwAoGazNHZV+MijdV4wYWJ6Qep5qE5FzMM0KXqon15ivc+R9ECgYANy3AsvF51fHYSAdg6GrvFDdDh8ulmjSBZ93cX//85+TbbyctLLv93ifFpWfRln3xZ9hc7yw5R56dcUHSPhzQu4pSCP80oah6RQK/e/TV87y5xWvh4/w+PDP8oN92D3DuKNj5Up6mUFs/bn+63lDpXjCBSvzNKnTyY/1wycqVU4QKBgF4ttARoP/5z5Uicr/vvSHmEgo56V4WBISpxVSFMiy/9gh0OFlRxLYdAY6KcnWJ3011nsnhVR1h0OIeUtdZqImTE/vQKAShSsRNAGe1qydxw/pNrhHFhkA2a1jX5IPFxXSo6TBunTAT1VmLUAs+4lsFgqTWuWUd69KkQ+BFqJJDBAoGBAIHnx28oIn2obn2vtXf1fj5zAx02KyI6oypeXYffdV+K1GmH1eLZ79KsjeuYbpoYLo7glGHK7fa29Lal2jSb3Y4mWelSD9PkLLl4mgvw16Fx+/SwbB1cLGlQQw4PKmf0B3hY7p6QP1B7/vzD2MH7xwbkHMalsApAdZ/vK4DzfxAU";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAobWZ0WCeSBIMUWjrleCF/8ovWVGN0msbLYkMsZoDV1ee/ahZus10xLN9mu7bStTBESZPruj6onKTvEWROdG1mbzOG33wRzVVlqRxnpqPohJkT8OH/B5znoPFnmRsur5MeHMqtbGHg0BkKiG+VcP20RVFj56Bu1krEjaMhAVgZigp3CEP4RR0K5179LemOFpeJPuUpu5CRwhvut7FQxd7VQZOupBIgqPlvdavodctrR/btK1XXQBM5XPt1FiopZmq7Yf1oByJF3fGYLh9RGDBNnLp2SPoE8KJWF8AyKe172DmesJnqtugVBuQUbsiFrHTEu+/dAkeKheBRE8T9vbZwwIDAQAB";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 参数返回格式，只支持json
	static String FORMAT = "json";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	// 日志存放路径
	//public static String log_path = "C:\\";
	
	public static AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, app_id, merchant_private_key, FORMAT, 
			charset, alipay_public_key, sign_type);


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

//    /** 
//     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
//     * @param sWord 要写入日志里的文本内容
//     */
//    public static void logResult(String sWord) {
//        FileWriter writer = null;
//        try {
//            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
//            writer.write(sWord);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (writer != null) {
//                try {
//                    writer.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
	
}

