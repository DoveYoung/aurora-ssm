package com.aurorascm.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/** 异常处理
 * @author BYG 2017-7-21
 * @version 1.0
 */
public class MyExceptionResolver implements HandlerExceptionResolver{

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// TODO Auto-generated method stub
		System.out.println("==============异常开始=============");
		ex.printStackTrace();
		System.out.println("==============异常结束=============");
		ModelAndView mv = new ModelAndView("error");
		String message = ex.getMessage();
		message = Tools.notEmptys(message)?message.replaceAll("\n", "<br/>"):message;
		mv.addObject("exception",message);
		return mv;
	}

}
