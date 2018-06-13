package com.aurorascm.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aurorascm.util.AliyunOSSClientUtil;
import com.aurorascm.util.AppUtil;
import com.aurorascm.util.DateUtil;
import com.aurorascm.util.PageData;
import com.aurorascm.util.Tools;

@Controller
@RequestMapping(value = "/upload")
public class UploadController extends BaseController {

	String menuUrl = "upload.do"; // 菜单地址(权限用)

	/**
	 * 单图片上传
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadImage")
	@ResponseBody
	public Object uploadImage(HttpServletRequest request, HttpServletResponse response, MultipartFile file)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		if (file.isEmpty()) {
			logger.info("【CUC:未选择文件!】");
			result = "failed";
			msg = "参数错误,请稍后重试!";
		} else {
			try {
				String fileName = DateUtil.getSdfTimes() + file.getOriginalFilename();
				result = AliyunOSSClientUtil.uploadPicture2OSS(file, fileName); // result =  "success" 或  "error"
				String url = "https://aurora-picture.oss-cn-hangzhou.aliyuncs.com/" + fileName; // 文件存储地址
				map.put("url", url);
			} catch (Exception e) {
				result = "error";
				msg = "图片上传失败!请稍后重试!";
				logger.info("【CUC:图片上传系统异常!】");
			}
		}
		map.put("result", result);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
	}

	/**
	 * 单图片删除（图片路径未保存数据库）
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteImage")
	@ResponseBody
	public Object deleteImage(String url) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		if (url.isEmpty()) {
			logger.info("【CUC:未选择删除图片!】");
			result = "failed";
			msg = "请选择要删除的图片!";
		} else {
			try {
				String fileName = url.split(".com/")[1];
				AliyunOSSClientUtil.deletePicture(fileName);
				result = "success";
			} catch (Exception e) {
				result = "error";
				msg = "图片删除失败!请稍后再试!";
				logger.info("【CUC:图片删除系统执行异常!】");
			}
		}
		map.put("msg", msg);
		map.put("result", result);
		return AppUtil.returnObject(pd, map);
	}

	/**
	 * 单文件上传
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadfile")
	@ResponseBody
	public Object uploadFile(HttpServletRequest request, HttpServletResponse response, MultipartFile file)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		if (file.isEmpty()) {
			logger.info("【CUC:未选择文件!】");
			result = "failed";
			msg = "请选择文件!";
		} else {
			try {
				String fileName = DateUtil.getSdfTimes() + file.getOriginalFilename();
				result = AliyunOSSClientUtil.uploadFile2OSS(file, fileName); // result = "success" 或 "error"
				String url = "https://aurora-file.oss-cn-hangzhou.aliyuncs.com/" + fileName; // 文件存储地址
				map.put("url", url);
				result = "success";
			} catch (Exception e) {
				result = "error";
				msg = "文件上传失败!请稍后再试!";
				logger.info("【CUC:文件上传系统执行异常!】");
			}
		}
		map.put("result", result);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
	}

	/**
	 * 单文件下载
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/downloadfile")
	@ResponseBody
	public Object uploadFile(String url, File file) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "";
		String msg = "";
		if (Tools.notEmptys(url)) {
			logger.info("【CUC:无效的url!】");
			result = "failed";
			msg = "无效的url!请稍后再试!";
		} else {
			try {
				String fileName = url.substring(url.lastIndexOf("/") + 1);
				result = AliyunOSSClientUtil.getObjectFile(fileName, file); // result = "success" 或 "error"
			} catch (Exception e) {
				result = "error";
				msg = "文件下载失败!请稍后再试!";
				logger.info("【CUC:文件下载系统执行异常!】");
			}
		}
		map.put("url", url);
		map.put("result", result);
		map.put("msg", msg);
		return AppUtil.returnObject(pd, map);
	}

}
