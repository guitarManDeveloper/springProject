package com.bigmoney.testproject.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String nowDate(String format) {
		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(nowDate);
	}

}
