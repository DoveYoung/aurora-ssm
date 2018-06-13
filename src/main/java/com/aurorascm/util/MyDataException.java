package com.aurorascm.util;

/**
 * 自定义操作异常
 * @author BYG
 * @version 1.0 2017年8月22日
 */
public class MyDataException extends RuntimeException {

	private static final long serialVersionUID = -4088948245377030579L;

	public MyDataException() {
		super();
	}

	public MyDataException(String message) {
		super(message);
	}

	public MyDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyDataException(Throwable cause) {
		super(cause);
	}
	
}
