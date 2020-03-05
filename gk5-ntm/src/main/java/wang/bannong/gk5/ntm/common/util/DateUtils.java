package wang.bannong.gk5.ntm.common.util;

import org.apache.commons.collections4.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
    public static final String           DAY_START   = " 00:00:00";
    public static final String           DAY_END     = " 23:59:59";
    public static final String           MONTH_START = "-01 00:00:00";
    public static final SimpleDateFormat YMDHMS      = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat YMD         = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat YM          = new SimpleDateFormat("yyyy-MM");
    public static final SimpleDateFormat Y           = new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat M           = new SimpleDateFormat("MM");
    public static final SimpleDateFormat YYYYMMDD    = new SimpleDateFormat("yyyyMMdd");

    public static final SimpleDateFormat YMD_APP = new SimpleDateFormat("yyyy/MM/dd");

    public static String getStartQuarterTime(Calendar cal) throws NullPointerException {
        if (cal == null) {
            throw new NullPointerException("the param cal is null");
        }
        int month = cal.get(Calendar.MONTH);
        String result = null;
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                result = cal.get(Calendar.YEAR) + "-01-01 00:00:00";
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                result = cal.get(Calendar.YEAR) + "-04-01 00:00:00";
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                result = cal.get(Calendar.YEAR) + "-07-01 00:00:00";
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                result = cal.get(Calendar.YEAR) + "-10-01 00:00:00";
                break;
        }
        return result;
    }

    public static String getEndQuarterTime(Calendar cal) throws NullPointerException {
        if (cal == null) {
            throw new NullPointerException("the param cal is null");
        }
        int month = cal.get(Calendar.MONTH);
        String result = null;
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                result = cal.get(Calendar.YEAR) + "-03-31 23:59:59";
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                result = cal.get(Calendar.YEAR) + "-07-31 23:59:59";
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                result = cal.get(Calendar.YEAR) + "-10-31 23:59:59";
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                result = cal.get(Calendar.YEAR) + "-12-31 23:59:59";
                break;
        }
        return result;
    }

    public static String getMonthStartTime(Calendar cal, int month) {
        return getMonthStartDate(cal, month) + DAY_START;
    }

    public static String getMonthStartDate(Calendar cal, int month) {
        cal.add(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return YMD.format(cal.getTime());
    }

    public static String addMonth(Calendar cal, int month) {
        cal.add(Calendar.MONTH, month);
        return YMD.format(cal.getTime());
    }

    public static String getMonthEndDate(Calendar cal, int month) {
        cal.add(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return YMD.format(cal.getTime());
    }


    public static String getMonthEndTime(Calendar cal, int month) {
        return getMonthEndDate(cal, month) + DAY_END;
    }


    public static String getYearStartTime(Date date) {
        return Y.format(date) + "-01-01" + DAY_START;
    }

    public static String transCapNumber(int num) {
        if (num == 1) {
            return "一";
        } else if (num == 2) {
            return "二";
        } else if (num == 3) {
            return "三";
        } else if (num == 4) {
            return "四";
        } else if (num == 5) {
            return "五";
        }
        return num + "";
    }


    public static List<Date> getBeforDaysStartTime(int beforDays, Calendar cal) {
        List<String> dateStrs = getBeforDaysStartTimeStr(beforDays, cal);
        if (CollectionUtils.isEmpty(dateStrs)) {
            return null;
        }
        List<Date> dates = new ArrayList<>();
        try {
            for (String date : dateStrs) {
                dates.add(YMDHMS.parse(date));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dates;
    }


    public static List<String> getBeforDaysStartTimeStr(int beforDays, Calendar cal) {
        if (beforDays < 0) {
            return null;
        }
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < beforDays; i++) {
            dates.add(DateUtils.YMD.format(cal.getTime()) + DateUtils.DAY_START);
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        return dates;
    }


    public static List<Date> getBeforDaysEndTime(int beforDays, Calendar cal) {
        List<String> dateStrs = getBeforDaysEndTimeStr(beforDays, cal);
        if (CollectionUtils.isEmpty(dateStrs)) {
            return null;
        }
        List<Date> dates = new ArrayList<>();
        try {
            for (String date : dateStrs) {
                dates.add(YMDHMS.parse(date));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dates;
    }


    public static List<String> getBeforDaysEndTimeStr(int beforDays, Calendar cal) {
        if (beforDays < 0) {
            return null;
        }
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < beforDays; i++) {
            dates.add(DateUtils.YMD.format(cal.getTime()) + DateUtils.DAY_END);
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        return dates;
    }


    public static List<String> getBeforDayDate(int beforDays, Calendar cal) {
        if (beforDays < 0) {
            return null;
        }
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < beforDays; i++) {
            dates.add(DateUtils.YMD.format(cal.getTime()));
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        return dates;
    }


    public static List<String> getBeforMonthDateStr(Calendar cal, int months) {
        List<String> monthLists = new ArrayList<>();
        while (months > 0) {
            cal.add(Calendar.MONTH, -1);
            monthLists.add(YM.format(cal.getTime()));
            months--;
        }
        return monthLists;
    }


    public static List<String> getBeforMonthsStartTimeStr(Calendar cal, int months) {
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < months; i++) {
            cal.add(Calendar.MONTH, -1);
            dates.add(DateUtils.YM.format(cal.getTime()) + DateUtils.MONTH_START);
        }
        return dates;
    }


    public static List<Date> getBeforMonthsStartTime(Calendar cal, int months) {
        List<String> dates = getBeforMonthsStartTimeStr(cal, months);
        List<Date> resultDates = new ArrayList<>(dates.size());
        try {
            for (String date : dates) {
                resultDates.add(YMDHMS.parse(date));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDates;
    }


    public static List<String> getBeforMonthsEndTimeStr(Calendar cal, int months) {
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < months; i++) {
            cal.add(Calendar.MONTH, -1);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            dates.add(DateUtils.YMD.format(cal.getTime()) + DateUtils.DAY_END);
        }
        return dates;
    }


    public static List<Date> getBeforMonthsEndTime(Calendar cal, int months) {
        List<String> dates = getBeforMonthsEndTimeStr(cal, months);
        List<Date> resultDates = new ArrayList<>(dates.size());
        try {
            for (String date : dates) {
                resultDates.add(YMDHMS.parse(date));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDates;
    }


    public static String getDayStartTimeStr(Calendar cal, int days) {
        cal.add(Calendar.DAY_OF_MONTH, days);
        return YMD.format(cal.getTime()) + DAY_START;
    }

    public static Date getDayStartTime(Calendar cal, int days) throws Exception {
        return YMDHMS.parse(getDayStartTimeStr(cal, days));
    }

    public static String getDayEndTimeStr(Calendar cal, int days) {
        cal.add(Calendar.DAY_OF_MONTH, days);
        return YMD.format(cal.getTime()) + DAY_END;
    }

    public static Date getDayEndTime(Calendar cal, int days) throws Exception {
        return YMDHMS.parse(getDayEndTimeStr(cal, days));
    }


    public static void main(String[] args) {
        System.out.println(getDayStartTimeStr(Calendar.getInstance(), -30));
    }

    public static Date beforeTime(Date time, int months, int beforeType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        calendar.add(beforeType, -months);
        return calendar.getTime();
    }

    public static Date daysBeforeTime(Date time, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(5, -days);
        return calendar.getTime();
    }

}
