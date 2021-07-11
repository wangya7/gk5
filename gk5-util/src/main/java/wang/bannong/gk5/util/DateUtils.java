package wang.bannong.gk5.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by bn. on 2018/8/28 下午10:46
 */
public final class DateUtils {

    public static final String YMD1 = "yyyy:MM:dd";
    public static final String YMD2 = "yyyy/MM/dd";
    public static final String YMD3 = "yyyy-MM-dd";
    public static final String YMD4 = "yyyy年MM月dd日";
    public static final String YMD5 = "yyyyMMdd";

    public static final String YM1 = "yyyy:MM";
    public static final String YM2 = "yyyy/MM";
    public static final String YM3 = "yyyy-MM";
    public static final String YM4 = "yyyy年MM月";
    public static final String YM5 = "yyyyMM";

    public static final String YMDHMS1 = "yyyy:MM:dd HH:mm:ss";
    public static final String YMDHMS2 = "yyyy/MM/dd HH:mm:ss";
    public static final String YMDHMS3 = "yyyy-MM-dd HH:mm:ss";
    public static final String YMDHMS4 = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String YMDHMS5 = "yyyyMMddHHmmss";

    private static final long HALF_MINUTE = 30000L;
    private static final long ONE_MINUTE  = 60000L;
    private static final long ONE_HOUR    = 60 * ONE_MINUTE;
    private static final long ONE_DAY     = 24 * ONE_HOUR;
    private static final long ONE_MONTH   = 30 * ONE_DAY;
    private static final long ONE_YEAR    = 365 * ONE_DAY;


    // ################################### 转换

    public static String format(Date date) {
        return format(YMDHMS1, date);
    }

    public static String format(String dateFormatStr, Date date) {
        DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        return dateFormat.format(date);
    }

    public static Date parse(String dateStr) {
        return parse(YMDHMS1, dateStr);
    }

    public static Date parse(String dateFormatStr, String dateStr) {
        DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
           throw new RuntimeException("convert string to date exception");
        }
    }


    // ################################### 当天时间转换

    /**
     * 返回day当天的开始时间
     *
     * @param day 天数
     * @return time 一天的开始时间
     */
    public static Date beginTimeOfDay(Date day) {
        /**
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
         */

        String s = format(YMD3, day) + " 00:00:00";
        return parse(s);
    }

    /**
     * 返回day当天的结束时间
     *
     * @param day 天数
     * @return time 一天的结束时间
     */
    public static Date endTimeOfDay(Date day) {
        /**
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
         */

        String s = format(YMD3, day) + " 23:59:59";
        return parse(s);
    }


    // ################################### 当月时间转换

    public static Date yesterdayNow(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 获取两天天数差 【按照时间算】
     * 举例
     * <p>2018-10-07 21:09:09   2018-10-07 22:19:09  得到  1</p>
     * <p>2018-10-07 21:09:09   2018-10-08 09:09:09  得到  1</p>
     * <p>2018-10-07 21:09:09   2018-10-09 09:09:09  得到  2</p>
     * <p>2018-10-07 21:09:09   2018-10-08 23:09:09  得到  2</p>
     * <p>2018-10-07 21:09:09   2018-10-12 13:09:09  得到  5</p>
     * <p>2018-10-07 21:09:09   2018-10-18 23:09:09  得到  12</p>
     * <p>2018-10-07 21:09:09   2018-10-01 09:09:09  得到  -1</p>
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return -1标识异常
     */
    public static long intervalDaysByTime(Date beginDate, Date endDate) {
        Calendar fromDate = Calendar.getInstance();
        fromDate.setTime(beginDate);
        long fromDateLong = fromDate.getTimeInMillis();
        Calendar toDate = Calendar.getInstance();
        toDate.setTime(endDate);
        long toDateLong = toDate.getTimeInMillis();
        if (fromDateLong > toDateLong) {
            return -1;
        }
        long dis = toDateLong - fromDateLong;
        return 0 == dis % ONE_DAY ? dis / ONE_DAY : (dis / ONE_DAY + 1);
    }

    /**
     * 获取两天天数差 【按照日期算】
     * <p>2018-10-07 21:09:09   2018-10-07 22:19:09  得到  0</p>
     * <p>2018-10-07 21:09:09   2018-10-08 09:09:09  得到  1</p>
     * <p>2018-10-07 21:09:09   2018-10-09 09:09:09  得到  2</p>
     * <p>2018-10-07 21:09:09   2018-10-08 23:09:09  得到  1</p>
     * <p>2018-10-07 21:09:09   2018-10-12 13:09:09  得到  5</p>
     * <p>2018-10-07 21:09:09   2018-10-18 23:09:09  得到  11</p>
     * <p>2018-10-07 21:09:09   2018-10-01 09:09:09  得到  -1</p>
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return -1标识异常
     */
    public static long intervalDaysByDate(Date beginDate, Date endDate) {
        return intervalDaysByTime(beginTimeOfDay(beginDate), beginTimeOfDay(endDate));
    }


    /**
     * time的day天前的时间
     * <p>2018-10-18 12:23:12   2   得到   2018-10-16 12:23:12</p>
     * <p>2018-10-18 12:23:12   7   得到   2018-10-11 12:23:12</p>
     * <p>2018-10-18 12:23:12  -2   得到   2018-10-20 12:23:12</p>
     * <p>2018-10-18 12:23:12   0   得到   2018-10-18 12:23:12</p>
     *
     * @param time 参考时间
     * @param days 基于参考时间的天数差
     * @return
     */
    public static Date daysBeforeTime(Date time, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DAY_OF_MONTH, -days);
        return calendar.getTime();
    }

    public static Date daysAfterTime(Date time, int days) {
        return daysBeforeTime(time, -days);
    }



    public static void main(String... args) {
        System.out.println(format(beginTimeOfDay(new Date())));
        System.out.println(format(YM3, endTimeOfDay(new Date())));
    }
}
