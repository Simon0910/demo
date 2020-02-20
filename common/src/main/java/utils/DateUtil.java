package utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lixu19
 * @Title: DateUtil
 * @Description: 日期工具类
 * @date 2019/12/20 9:59
 */
@Slf4j
public class DateUtil {

    /**
     * 时间毫秒级
     */
    public static final String TIME_WITH_MS_STYLE = "yyyyMMddHHmmssSSS";
    /**
     * 时间秒级
     */
    public static final String TIME_WITH_SECOND_STYLE = "yyyyMMddHHmmss";

    /**
     * 判断是否匹配 提醒日期
     * @param expectedRemindDate
     * @return
     */
    public static boolean matchRemindDate(int expectedRemindDate) {
        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        expectedRemindDate = getExpectedRemindDate(expectedRemindDate, calendar);
        return date == expectedRemindDate;
    }

    /**
     * 获取 期望提醒日期
     * @param expectedRemindDate
     * @param calendar
     * @return
     */
    private static int getExpectedRemindDate(int expectedRemindDate, Calendar calendar) {
        if (expectedRemindDate < 1 || expectedRemindDate > 31) {
            log.error("Date error - expectedRemindDate : {}", expectedRemindDate);
        }
        int maxDateOfMonth = calendar.getActualMaximum(Calendar.DATE);
        if (expectedRemindDate > maxDateOfMonth) {
            expectedRemindDate = maxDateOfMonth;
        }
        return expectedRemindDate;
    }

    /**
     * 获取下次提醒日期
     * @param expectedRemindDate
     * @return
     */
    public static Date getNextRemindDate(int expectedRemindDate) {
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DATE);
        int maxDateOfMonth = calendar.getActualMaximum(Calendar.DATE);

        if (expectedRemindDate > today && expectedRemindDate <= maxDateOfMonth) {
            calendar.set(Calendar.DATE, expectedRemindDate);
        } else {
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DATE, getExpectedRemindDate(expectedRemindDate, calendar));
        }

        return calendar.getTime();
    }

    /**
     * 获取时间格式化字符串
     * @param format
     * @return
     */
    public static String getFormatString(String format) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return dateTimeFormatter.format(localDateTime);
    }

    public static void main(String[] args) {
        log.info("match date:{}", matchRemindDate(2));
        System.out.println(matchRemindDate(2));
        Date nextRemindDate = getNextRemindDate(1);
        log.info("match date:{}", DateTimeUtil.dateToStr(nextRemindDate));
        System.out.println(DateTimeUtil.dateToStr(nextRemindDate));
    }
}
