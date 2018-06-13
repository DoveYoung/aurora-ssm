package com.aurorascm.util;

/**
 * 自定义操作异常
 * @author BYG
 * @version 1.0 2017年8月22日
 */
public class MyParamException extends RuntimeException {

	private static final long serialVersionUID = -1169652596159206154L;

	public MyParamException() {
		super();
	}

	public MyParamException(String message) {
		super(message);
	}

	public MyParamException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyParamException(Throwable cause) {
		super(cause);
	}
	
}
