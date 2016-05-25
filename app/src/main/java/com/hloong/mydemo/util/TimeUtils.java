package com.hloong.mydemo.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 时间处理工具类
 * @author zheng
 * */
@SuppressLint("SimpleDateFormat")
public class TimeUtils {
	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    
    public static final SimpleDateFormat DATE_FORMAT_MONTH = new SimpleDateFormat("MM-dd");

    /**
     * long time to string
     * 
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     * 
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }
    /**
     * long time to string, format is {@link #DATE_FORMAT_MONTH}
     * 
     * @param timeInMillis
     * @return
     */
    public static String getTimeMonth(long timeInMillis) {
        return getTime(timeInMillis, DATE_FORMAT_MONTH);
    }

    /**
     * get current time in milliseconds
     * 
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     * 
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     * 
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }

    /**
     * 格式化日期
     * 
     * @param datetime
     * @param format
     * @return
     */
    public static String formatDate(String datetime, String format) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            Date date = formater.parse(datetime);
            return formater.format(date);
        } catch (ParseException e) {
            return datetime;
        }
    }
    /**
     * 
     * @param time
     * @return
     */
    public static long formatLong(String time){
		  Date d2Long = null;
		  try {
			  d2Long = DATE_FORMAT_DATE.parse(time);
			  long mTime = d2Long.getTime();
			  return mTime;
		  } catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
    }
    /**
     * 
     * @param time
     * @return
     */
    public static long formatLongMonth(String time){
        Date d2Long = null;
        try {
            d2Long = DATE_FORMAT_MONTH.parse(time);
            long mTime = d2Long.getTime();
            return mTime;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static long getCurrentTime(){
    	String currentTimeStr = DATE_FORMAT_DATE.format(new Date());// new Date()为获取当前系统时间
    	Date d2Long = null;
		  try {
			  d2Long = DATE_FORMAT_DATE.parse(currentTimeStr);
			  long mTime = d2Long.getTime();
			  return mTime;
		  } catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
    }
    
    /**
     * 系统后一天  格式为：08-15 周日
     * @return
     */
    public static String getTomorrow() {
        SimpleDateFormat dFormat = new SimpleDateFormat("MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_YEAR, 1);
        String week[] = { "周日" ,"周一","周二","周三","周四","周五","周六"};
        return  dFormat.format(calendar.getTime())+week[calendar.get(Calendar.DAY_OF_WEEK)-1];
    }
    /**
     * 系统后2天  格式为：08-16 周一
     * @return
     */
    public static String getAfterTomorrow() {
        SimpleDateFormat dFormat = new SimpleDateFormat("MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_YEAR, 2);
        String week[] = { "周日" ,"周一","周二","周三","周四","周五","周六"};
        return  dFormat.format(calendar.getTime())+week[calendar.get(Calendar.DAY_OF_WEEK)-2];
    }
    /**
     *   格式为：08-16 周一
     * @return
     */
    public static String getLongToday(long time) {
        SimpleDateFormat dFormat = new SimpleDateFormat("MM-dd");
        Date date = new Date(time);
        String week[] = { "周日" ,"周一","周二","周三","周四","周五","周六"};
        return  dFormat.format(time)+week[date.getDay()];
    }
    
    public static String getTimeHourMin(long time) {
        String string = null;
        if (time > 3600) {
            string = time/3600+"小时";
        }else if (time < 60) {
            string = time+"秒钟";
        }else {
            string = time/60 + "分钟";
        }
        
        
        return string;
    }
    
    
    /**
     * 从时间戳转换为字符(转换成yyyy/MM/dd)
     * 
     * @param milliseconds
     * @return
     */
    public static String timeStampToString(String milliseconds) {
        Date dataValue = new Date(0, 0, 1);
        if (StringUtil.isEmpty(milliseconds))
            milliseconds = String.valueOf(dataValue.getTime());
        
        long tempTime = 0;
        milliseconds += "000";
        tempTime=Long.valueOf(milliseconds);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        dataValue = new Date(Long.valueOf(tempTime));
        
        return dateFormat.format(dataValue);
    }
    
    /**
     * 从时间戳转换为字符(转换成yyyy-MM-dd HH:mm:ss)
     * 
     * @param milliseconds
     * @return
     */
    public static String timeStampToyMdHms(String milliseconds) {
        Date dataValue = new Date(0, 0, 1);
        if (StringUtil.isEmpty(milliseconds))
            milliseconds = String.valueOf(dataValue.getTime());
        
        long tempTime = 0;
        milliseconds += "000";
        tempTime=Long.valueOf(milliseconds);

//      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
       
        dataValue = new Date(Long.valueOf(tempTime));
        
        return DEFAULT_DATE_FORMAT.format(dataValue);
    }
}
