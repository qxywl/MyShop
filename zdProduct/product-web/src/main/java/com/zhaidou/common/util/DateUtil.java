package com.zhaidou.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final String FORMAT_STR_END_MONTH = "yyyy-MM";
    public static final String FORMAT_STR_END_DAY = "yyyy-MM-dd";
    public static final String FORMAT_STR_END_HOUR = "yyyy-MM-dd HH";
    public static final String FORMAT_STR_END_MIN = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_STR_END_SEC = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATSTRENDSEC = "yyyyMMddHHmmss";
    public static final String FORMAT_STR_F_MONTH_END_MIN = "MM.dd HH:mm";
    public static final String FORMAT_DSTR_END_DAY = "yyyy.MM.dd";
    public static final String DATE_ERP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    
    /**
	 * 默认ERP日期类型格式
	 */
	private static SimpleDateFormat erpDateFormat = new SimpleDateFormat(DATE_ERP_FORMAT);
    
    public static SimpleDateFormat sdf = new SimpleDateFormat(FORMATSTRENDSEC);

    public static SimpleDateFormat sdfEndOfMin = new SimpleDateFormat(FORMAT_STR_END_MIN);

    public static SimpleDateFormat sdfFormMonthEndOfMin = new SimpleDateFormat(FORMAT_STR_F_MONTH_END_MIN);

    public static SimpleDateFormat sdfEndOfDay = new SimpleDateFormat(FORMAT_STR_END_DAY);

    public static SimpleDateFormat dSdfEndOfDay = new SimpleDateFormat(FORMAT_DSTR_END_DAY);

    public static SimpleDateFormat sdfEndOfSec = new SimpleDateFormat(FORMAT_STR_END_SEC);

    public static SimpleDateFormat sdfStartOfHour = new SimpleDateFormat("HH:mm:ss");

    public static SimpleDateFormat sdfEndOfHour = new SimpleDateFormat(FORMAT_STR_END_HOUR);

    /**
     * yyyyMMddHHmmss
     *
     * @return
     */
    public static String getTimeStr() {
        Calendar c = Calendar.getInstance();
        return sdf.format(c.getTime());
    }

    public static String getWeekStr(int week) {
        String s = null;
        switch (week) {
            case 1:
                s = "周一";
                break;
            case 2:
                s = "周二";
                break;
            case 3:
                s = "周三";
                break;
            case 4:
                s = "周四";
                break;
            case 5:
                s = "周五";
                break;
            case 6:
                s = "周六";
                break;
            case 0:
                s = "周日";
                break;
            case 7:
                s = "周日";
                break;
            default:
                break;
        }
        return s;
    }

    public static Date getEndOfDay(Date day) {
        if (day == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

	public static String formatErpDateTime(Date d) {

		if (d == null)

		{

			return "";

		}

		return erpDateFormat.format(d);

	}
    
    public static String getEndOfMin(Date date) {
        if (date == null) {
            return null;
        }
        return sdfEndOfMin.format(date);
    }

    public static Date getDate(String dateStr) {
        if (dateStr == null || "".equals(dateStr)) {
            return null;
        }
        Date date = null;
        try {
            String formatStr = "";
            if (FORMAT_STR_F_MONTH_END_MIN.equals(dateStr)) {
                formatStr = FORMAT_STR_F_MONTH_END_MIN;
            }
            if (FORMAT_DSTR_END_DAY.equals(dateStr)) {
                formatStr = FORMAT_DSTR_END_DAY;
            }
            if (dateStr.trim().length() == FORMAT_STR_END_MONTH.length()) {
                formatStr = FORMAT_STR_END_MONTH;
            }
            if (dateStr.trim().length() == FORMAT_STR_END_DAY.length()) {
                formatStr = FORMAT_STR_END_DAY;
            }
            if (dateStr.trim().length() == FORMAT_STR_END_HOUR.length()) {
                formatStr = FORMAT_STR_END_HOUR;
            }
            if (dateStr.trim().length() == FORMAT_STR_END_MIN.length()) {
                formatStr = FORMAT_STR_END_MIN;
            }
            if (dateStr.trim().length() == FORMAT_STR_END_SEC.length()) {
                formatStr = FORMAT_STR_END_SEC;
            }
            SimpleDateFormat fmt = new SimpleDateFormat(formatStr);
            date = fmt.parse(dateStr);
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            ca.set(Calendar.HOUR_OF_DAY, 0);
            ca.set(Calendar.MINUTE, 0);
            ca.set(Calendar.SECOND, 0);
            ca.set(Calendar.MILLISECOND, 0);
            date = ca.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getFormatDate(String formatStr) {
        if (formatStr == null || "".equals(formatStr)) {
            return null;
        } else {
            SimpleDateFormat fmt = new SimpleDateFormat(formatStr);
            Date date = null;
            try {
                date = fmt.parse(formatStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }
    }

    
    public static String getFormatDateStr(String formatStr) {
        if (formatStr == null || "".equals(formatStr)) {
            return null;
        } else {
            SimpleDateFormat fmt = new SimpleDateFormat(formatStr);
			Calendar ca = Calendar.getInstance();
            return fmt.format(ca.getTime());
        }
    }

    
    public static Date long2Date(Long dateLong) {
        if (dateLong == null || "".equals(dateLong)) {
            return null;
        } else {
        	Date date = new Date();
        	date.setTime(dateLong);
        	return date;
        }
    }
    
    
    public static void  main(String [] args){
    	Date date = new Date();
    	Long time = date.getTime();
    	
    	Calendar c = Calendar.getInstance();
    	Date date1 = new Date();
    	Long t = Long.valueOf("1427791281230");
    	date1.setTime(t);
    	c.setTime(date1);
    	String strDate = sdfEndOfSec.format(date1);
    	System.out.println(strDate);
    }
}
