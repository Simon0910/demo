package utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


 /*

    1、将字符串转换为时间
DateTimeFormatter forPattern = DateTimeFormat.forPattern(“yyyy-MM-dd”);
DateTime dateTime=forPattern.parseDateTime(“2018-01-01”);
format = DateTimeFormat.forPattern(“yyyy年MM月dd日 HH:mm:ss”);
dateTime=forPattern.parseDateTime(“2018年01月01日 23:25:35”);
2、将时间转换为字符串
DateTime dateTime=new DateTime();
String dateString = dateTime.toString(“yyyyMMdd”);
DateTimeFormatter forPattern = DateTimeFormat.forPattern(“yyyy年MM月dd日 HH:mm:ss”);
dateString=forPattern.print(dateTime);
3、得到任何日期所在周的第一天与最后一天
DateTime dateTime=new DateTime();
// 一周的开始日期
String monday= dateTime.dayOfWeek().withMinimumValue().toString(“yyyyMMdd”);
// 一周的结束日期
String sunday= dateTime.dayOfWeek().withMaximumValue().toString(“yyyyMMdd”);
4、得到任何日期所在月的第一天与最后一天
DateTime dateTime=new DateTime();
String first= dateTime.dayOfMonth().withMinimumValue().toString(“yyyyMMdd”);
String last= dateTime.dayOfMonth().withMaximumValue().toString(“yyyyMMdd”);

5、得到任何日期所在年的第一天与最后一天
DateTime dateTime=new DateTime();
String first= dateTime.dayOfYear().withMinimumValue().toString(“yyyyMMdd”);
String last= dateTime.dayOfYear().withMaximumValue().toString(“yyyyMMdd”);
6、加减年、月、周、天、时、分、秒、毫秒
DateTime dateTime=new DateTime();
//加一年
dateTime.plusYears(1);
//加一月
dateTime.plusMonths(1);
//加一周
dateTime.plusWeeks(1);
//加一天
dateTime.plusDays(1);
//加一小时
dateTime.plusHours(1);
dateTime.plusMinutes(1);
dateTime.plusSeconds(1);
dateTime.plusMillis(1);

//减去一年
dateTime.minusYears(1);
//减去加一月
dateTime.minusMonths(1);
……

7、得到上一周的星期一与星期天、下一周的星期一与星期天
DateTime dateTime=new DateTime();
DateTime previousMonday= dateTime.dayOfWeek().withMinimumValue().minusWeeks(1);
DateTime previousSunday= dateTime.dayOfWeek().withMaximumValue().minusWeeks(1);

DateTime nextMonday= dateTime.dayOfWeek().withMinimumValue().plusWeeks(1);
DateTime nextSunday= dateTime.dayOfWeek().withMaximumValue().plusWeeks(1);

8、得到上一个月的第一天与最后一天、下一个有的第一天与最后一天
DateTime dateTime=new DateTime();
String previousFirstDay= dateTime.dayOfYear().withMinimumValue().minusMonths(1).toString(“yyyyMMdd”);
String previousLastDay= dateTime.dayOfYear().withMaximumValue().minusMonths(1).toString(“yyyyMMdd”);

String nextFirstDay= dateTime.dayOfYear().withMinimumValue().plusMonths(1).toString(“yyyyMMdd”);
String nextLastDay= dateTime.dayOfYear().withMaximumValue().plusMonths(1).toString(“yyyyMMdd”);

9、计算两个日期相差多少天、相差多少月、相差多少年
DateTime nextFirstDay= dateTime.dayOfYear().withMinimumValue().plusMonths(1);
DateTime nextLastDay= dateTime.dayOfYear().withMaximumValue().plusMonths(1);
int days = Days.daysBetween(nextFirstDay, nextLastDay).getDays();
int months = Months.monthsBetween(nextFirstDay, nextLastDay).getMonths();
int years =Years.yearsBetween(nextFirstDay, nextLastDay).getYears();

10、计算某个月份的第一个星期二
DateTime now =new DateTime();
String date=now.monthOfYear()
.setCopy(10) // 10月
.dayOfMonth()
.withMinimumValue() // 10月第一天
.plusDays(6) // 加6天
.dayOfWeek()
.setCopy(2) //第一个星期二
.toString(“yyyy-MM-dd”);

11、判断闰年、闰月
DateTime now =new DateTime();
boolean leapYear = now.year().isLeap();
boolean leapMonth = now.monthOfYear().isLeap();

12、取得一天的开始时间和结束时间
DateTime now = new DateTime();
String formatter=“yyyy-MM-dd HH:mm:ss”；
String startTime = now.withTimeAtStartOfDay().toString(formatter);
String endTime = now.millisOfDay().withMaximumValue().toString(formatter);
————————————————
版权声明：本文为CSDN博主「weixin_37364740」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/weixin_37364740/java/article/details/83783873
     */


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


    final DateTime DISTRIBUTION_TIME_SPLIT_TIME = new DateTime().withTime(15, 0, 0, 0);

    private Date calculateDistributionTimeByOrderCreateTime(Date orderCreateTime) {
        DateTime orderCreateDateTime = new DateTime(orderCreateTime);
        return orderCreateDateTime.isAfter(DISTRIBUTION_TIME_SPLIT_TIME) ? wrapDistributionTime(orderCreateDateTime.plusDays(1)) : wrapDistributionTime(orderCreateDateTime.plusDays(2));
    }


    private Date wrapDistributionTime(DateTime distributionTime) {
        boolean isSunday = (DateTimeConstants.SUNDAY == distributionTime.getDayOfWeek());
        return isSunday ? distributionTime.plusDays(1).toDate() : distributionTime.toDate();
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
        System.out.println("下次提醒: \t" + nextRemid.toString("yyyy-MM-dd HH:mm:ss:sss"));
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
