package com.aurorascm.util;  
  
import java.io.ByteArrayInputStream;  
import java.io.File;  
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;  
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;  
import com.aliyun.oss.model.ObjectMetadata;  
import com.aliyun.oss.model.PutObjectResult;  
  
/** 
 * @class:AliyunOSSClientUtil 
 * @descript:java使用阿里云OSS存储对象上传图片 
 * @date:2017年7月3日  
 * @author BYG 
 */  
public class AliyunOSSClientUtil {  
    private static Logger logger = Logger.getLogger(AliyunOSSClientUtil.class);     	//log日志  
    private static String endpoint  = "oss-cn-hangzhou.aliyuncs.com";      				//阿里云API的内或外网域名  
    private static String accessKeyID  = "LTAIYDfcv68Gw1Ep";      						//阿里云API的密钥Access Key ID  
    private static String accessKeySecret = "H0JdDZ3eUVPZMfyRa0X9zXQEY5WgCI";     		//阿里云API的密钥Access Key Secret  
    
 	/** 
     * 获取阿里云OSS客户端对象 
     * @return ossClient 
     */  
    public static  OSSClient getOSSClient(){  
        return new OSSClient(endpoint ,accessKeyID, accessKeySecret);  
    }  
  
    /** 
     * 上传图片至OSS "aurora-picture"
     * @param ossClient  oss连接 
     * @param file 上传文件（文件全路径如：D:\\image\\cake.jpg） 
     * @param bucketName  存储空间 
     * @param folder 模拟文件夹名 如"qj_nanjing/" 
     * @return String 返回的唯一MD5数字签名 
     * */  
    public static  String uploadPicture2OSS(MultipartFile file , String fileName) {
        String result = null;
        OSSClient ossClient = getOSSClient();
        String bucketName = "aurora-picture";
        try {  
            InputStream is = file.getInputStream();                 						 //以输入流的形式上传文件  
            Long fileSize = file.getSize();            										 //文件大小  
            ObjectMetadata metadata = new ObjectMetadata();            						 //创建上传Object的Metadata 
            metadata.setContentLength(is.available());           							 //上传的文件的长度  
            metadata.setCacheControl("no-cache");           								 //指定该Object被下载时的网页的缓存行为  
            metadata.setHeader("Pragma", "no-cache");            							 //指定该Object下设置Header  
            metadata.setContentEncoding("utf-8");            								 //指定该Object被下载时的内容编码格式  
            metadata.setContentType(getContentType(fileName));
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte."); //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称)
            PutObjectResult putResult = ossClient.putObject(bucketName, fileName, is, metadata);        //上传文件   (上传文件流的形式)
            String resultStr = putResult.getETag();           										 	//解析结果  （返回的唯一MD5数字签名 ）
            result = "success";
        } catch (Exception e) {
        	result = "error";
            e.printStackTrace();  
             logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return result;  
    }  
    
    //根据对象名从OSS指定的Bucket中导出OSSObject。请求结果OSSObject实例。使用完之后需要手动关闭其中的ObjectContent释放请求连接。
    public static  OSSObject getObjectPicture(String key) {
    	OSSClient ossClient = getOSSClient();
    	//阿里云API的bucket名称  
        String bucketName = "aurora-picture"; 
    	OSSObject ossImage = ossClient.getObject(bucketName, key);
		return ossImage;
    }
    
    /** 
    * 上传文件至OSS "aurora-file"
    * @param ossClient  oss连接 
    * @param file 上传文件（文件全路径如：D:\\image\\cake.jpg） 
    * @param bucketName  存储空间 
    * @param folder 模拟文件夹名 如"qj_nanjing/" 
    * @return String 返回的唯一MD5数字签名 
    * */  
   public static  String uploadFile2OSS(MultipartFile file , String fileName) {
       String result = null;
       OSSClient ossClient = getOSSClient();
       String bucketName = "aurora-file";
       try {  
           InputStream is = file.getInputStream();                 						 	 //以输入流的形式上传文件  
           Long fileSize = file.getSize();            										 //文件大小  
           ObjectMetadata metadata = new ObjectMetadata();            						 //创建上传Object的Metadata 
           metadata.setContentLength(is.available());           							 //上传的文件的长度  
           metadata.setCacheControl("no-cache");           								 	 //指定该Object被下载时的网页的缓存行为  
           metadata.setHeader("Pragma", "no-cache");            							 //指定该Object下设置Header  
           metadata.setContentEncoding("utf-8");            								 //指定该Object被下载时的内容编码格式  
           metadata.setContentType(getContentType(fileName));
           metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");  //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称)
           PutObjectResult putResult = ossClient.putObject(bucketName, fileName, is, metadata);         //上传文件   (上传文件流的形式)
           String resultStr = putResult.getETag();           										 	//解析结果（返回的唯一MD5数字签名 ）  
           result = "success";
       } catch (Exception e) {
       	result = "error";
           e.printStackTrace();  
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
       }
       return result;  
   }
    
   //根据对象名从OSS指定的Bucket中导出OSSObject。请求结果OSSObject实例。使用完之后需要手动关闭其中的ObjectContent释放请求连接。
	public static  String getObjectFile(String key, File file ) {
		OSSClient ossClient = getOSSClient();
		String bucketName = "aurora-file";    			 //阿里云API的bucket名称  
		String result = "";
		try {
			ossClient.getObject(new GetObjectRequest(bucketName, key), file);
			ossClient.shutdown();                        // 关闭client
			result = "success";
		} catch (Exception e) {
			result = "error";
		}                      
		return result;
	}
    
    /**   
     * 根据key删除OSS服务器上的图片   
     * @param ossClient  oss连接 
     * @param bucketName  存储空间  
     * @param folder  模拟文件夹名 如"qj_nanjing/" 
     * @param key Bucket下的文件的路径名+文件名 如："upload/cake.jpg" 
     */      
    public static void deletePicture(String key){
        OSSClient ossClient = getOSSClient();
        String bucketName = "aurora-picture"; 
        ossClient.deleteObject(bucketName, key);  
        logger.info("删除" + bucketName + "下的文件" + key + "成功");    
    }   
        
    /** 
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType 
     * @param fileName 文件名 
     * @return 文件的contentType 
     */  
    public static  String getContentType(String fileName){  
        //文件的后缀名  
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));  
        if(".bmp".equalsIgnoreCase(fileExtension)) {  
            return "image/bmp";  
        }  
        if(".gif".equalsIgnoreCase(fileExtension)) {  
            return "image/gif";  
        }  
        if(".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension)  || ".png".equalsIgnoreCase(fileExtension) ) {  
            return "image/jpeg";  
        }  
        if(".html".equalsIgnoreCase(fileExtension)) {  
            return "text/html";  
        }  
        if(".txt".equalsIgnoreCase(fileExtension)) {  
            return "text/plain";  
        }  
        if(".vsd".equalsIgnoreCase(fileExtension)) {  
            return "application/vnd.visio";  
        }  
        if(".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {  
            return "application/vnd.ms-powerpoint";  
        }  
        if(".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {  
            return "application/msword";  
        }  
        if(".xml".equalsIgnoreCase(fileExtension)) {  
            return "text/xml";  
        }
        if(".pdf".equalsIgnoreCase(fileExtension)) {  
            return "application/pdf";  
        } 
        //默认返回类型  
        return "image/jpeg";  
    }  
    
    /** 
     * 创建存储空间 
     * @param ossClient      OSS连接 
     * @param bucketName 存储空间 
     * @return 
     */  
    public  static String createBucketName(OSSClient ossClient,String bucketName){  
        //存储空间  
        final String bucketNames=bucketName;  
        if(!ossClient.doesBucketExist(bucketName)){  
            //创建存储空间  
            Bucket bucket=ossClient.createBucket(bucketName);  
            logger.info("创建存储空间成功");  
            return bucket.getName();  
        }  
        return bucketNames;  
    }  
      
    /** 
     * 删除存储空间buckName 
     * @param ossClient  oss对象 
     * @param bucketName  存储空间 
     */  
    public static  void deleteBucket(OSSClient ossClient, String bucketName){    
        ossClient.deleteBucket(bucketName);     
        logger.info("删除" + bucketName + "Bucket成功");    
    }    
      
    /** 
     * 创建模拟文件夹 
     * @param ossClient oss连接 
     * @param bucketName 存储空间 
     * @param folder   模拟文件夹名如"qj_nanjing/" 
     * @return  文件夹名 
     */  
    public  static String createFolder(OSSClient ossClient,String bucketName,String folder){  
        //文件夹名   
        final String keySuffixWithSlash = folder;
        //判断文件夹是否存在，不存在则创建  
        if(!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)){  
            //创建文件夹  
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));  
            logger.info("创建文件夹成功");  
            //得到文件夹名  
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);  
            String fileDir=object.getKey();  
            return fileDir; 
        }
        return keySuffixWithSlash;  
    }  
        
    //生成一个用HTTP GET方法访问OSSObject的URL。
    public static URL generatePresignedUrl(String key){
    	// 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        OSSClient ossClient = getOSSClient();
        //阿里云API的bucket名称  
        String bucketName = "aurora-picture"; 
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
		return url;
    }
}  