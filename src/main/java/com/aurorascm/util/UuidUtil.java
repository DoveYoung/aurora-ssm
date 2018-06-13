package com.aurorascm.util;

import java.util.UUID;

/** 获得UUID
 * @author BYG 2017-7-21
 * @version 1.0
 */
public class UuidUtil {

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	public static void main(String[] args) {
		System.out.println(get32UUID());
	}
}

