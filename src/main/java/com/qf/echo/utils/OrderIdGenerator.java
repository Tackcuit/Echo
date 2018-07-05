package com.qf.echo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2018/6/26.
 */
public class OrderIdGenerator {
	public static String createOrderId(){
		Date time = Calendar.getInstance().getTime();
		String OrderId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(time);
		return OrderId;


	}
}
