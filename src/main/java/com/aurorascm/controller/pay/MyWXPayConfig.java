package com.aurorascm.controller.pay;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.wxpay.sdk.WXPayConfig;

/* *
 *类名：WxPayConfig
 *功能：微信支付配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class MyWXPayConfig implements WXPayConfig{
	
//	private byte[] certData;
//	
//	public MyWXPayConfig() throws Exception {//撤销、退款申请API中调用需要证书apiclient_cert.p12
//        String certPath = "C://Users/loiikfb/Desktop/北极光/cert(微信支付证书)/apiclient_cert.p12";//本地路径
//        File file = new File(certPath);
//        InputStream certStream = new FileInputStream(file);
//        this.certData = new byte[(int) file.length()];
//        certStream.read(this.certData);
//        certStream.close();
//    }

    public String getAppID() {
        return "wx340aa0a94c7bacc8";//公众账号ID、开发者ID
    }

    public String getMchID() {//商户号
        return "1419268702";
    }

    public String getKey() {
        return "loiitrade0825loiitrade0825123456";//商户平台/账户中心/API安全中设置密钥
    }

//    public InputStream getCertStream() {
//        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
//        return certBis;
//    }
    
	@Override
	public InputStream getCertStream() {
		return null;
	}

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }
	
	/**得到发起支付客户的登陆IP
	 * @return
	 */
    public String getIP() throws Exception {  
		HttpServletRequest request = this.getRequest();
		String ip = "";
		if (request.getHeader("x-forwarded-for") == null) {  
			ip = request.getRemoteAddr();  
	    }else{
	    	ip = request.getHeader("x-forwarded-for");  
	    }
		return ip;
	} 
    
	/**得到request
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}


    
}

