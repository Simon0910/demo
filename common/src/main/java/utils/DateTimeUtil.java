package utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-11-06 10:48
 */
public class DateTimeUtil {

    /**
     * str->Date
     * Date->str
     */
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date millisToDate(Long timeMillis) {
        DateTime date = new DateTime(timeMillis);
        return date.toDate();
    }

    public static SimpleDateFormat dateFormat(String format) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
        return sDateFormat;
    }

    public static Date strToDate(String dateTimeStr, String formatStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static String dateToStr(Date date, String formatStr) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }

    public static Date strToDate(String dateTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static String dateToStr(Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }


    /**
     * 获取今天的最后时间 23:59:59
     */
    public static Date getLastTimeOfToday() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * 获取今天的开始时间 00:00:00
     *
     * @return
     */
    public static Date getStartTimeOfToday() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }



    final DateTime DISTRIBUTION_TIME_SPLIT_TIME = new DateTime().withTime(15,0,0,0);
    private Date calculateDistributionTimeByOrderCreateTime(Date orderCreateTime){
        DateTime orderCreateDateTime = new DateTime(orderCreateTime);
        return orderCreateDateTime.isAfter(DISTRIBUTION_TIME_SPLIT_TIME) ? wrapDistributionTime(orderCreateDateTime.plusDays(1)) : wrapDistributionTime(orderCreateDateTime.plusDays(2));
    }


    private Date wrapDistributionTime(DateTime distributionTime){
        boolean isSunday = (DateTimeConstants.SUNDAY == distributionTime.getDayOfWeek());
        return isSunday ? distributionTime.plusDays(1).toDate() : distributionTime.toDate() ;
    }


    /**
     * 下次提醒日期
     *
     * @param day
     * @param reminderDay
     * @return
     */
    public static DateTime getNextRemindDay(int day, DateTime reminderDay) {
        // 每月30 14:30:05 号提醒
        day = 33;
        // 假设当前日期是 2020-01-30
        DateTime dateTime = new DateTime(2020, 1, 30, 12, 23, 50, 007);
        // DateTime dateTime = new DateTime(2020, 2, 29, 12, 23, 50, 007);
        System.out.println("当前: \t" + dateTime.toString("yyyy-MM-dd HH:mm:ss:sss"));

        // 下一个月是 2月
        DateTime nextMonth = dateTime.plusMonths(1);
        System.out.println("下个月: \t" + nextMonth.toString("yyyy-MM-dd HH:mm:ss:sss"));

        // DateTime maxDayOfYear = nextMonth.monthOfYear().withMaximumValue();
        // System.out.println(maxDayOfYear.toString("yyyy-MM-dd HH:mm:ss:sss"));
        // 最大日期是 29
        DateTime maxDayOfMonth = nextMonth.dayOfMonth().withMaximumValue();
        // System.out.println(maxDayOfMonth.toString("yyyy-MM-dd HH:mm:ss:sss"));

        // 提醒日期
        if (day > maxDayOfMonth.getDayOfMonth()) {
            day = maxDayOfMonth.getDayOfMonth();
        }

        // update db
        DateTime nextRemid = maxDayOfMonth.withDayOfMonth(day);
        System.out.println("下次提醒: \t" +nextRemid.toString("yyyy-MM-dd HH:mm:ss:sss"));
        return nextRemid;
    }

    public static void main(String[] args) {
        // System.out.println(DateTimeUtil.dateToStr(new Date(), "yyyy-MM-dd"));
       // System.out.println(DateTimeUtil.strToDate("2010-01-01 11:11:11", "yyyy-MM-dd HH:mm:ss"));
       // System.out.println(DateTimeUtil.strToDate("2010-01-01 11:11:11"));
       // System.out.println(DateTimeUtil.millisToDate(1572945129000L));

        // Date now = new Date();
        // 测试获取今天开始的时间，比较与当前时间的大小
        // Date startTimeOfToday = getStartTimeOfToday();
        // System.out.println(dateToStr(startTimeOfToday));
        // Assert.isTrue(now.getTime() > getStartTimeOfToday().getTime(), "现在小于或等于当前时间");

        // 测试获取今天最后的时间，比较与当前时间的大小
        // Date lastTimeOfToday = getLastTimeOfToday();
        // System.out.println(dateToStr(lastTimeOfToday));
        // Assert.isTrue(now.getTime() < getLastTimeOfToday().getTime(), "现在大于或等于当前时间");

        getNextRemindDay(30, null);
    }
}
