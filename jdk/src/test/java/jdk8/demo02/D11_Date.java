package jdk8.demo02;

import org.joda.time.DateTimeUtils;
import org.junit.Test;
import utils.DateTimeUtil;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-24 11:24
 */
public class D11_Date {


    // LocalDateTime(本地日期时间)
    @Test
    public void testLocalDateTime() {
        // LocalDateTime 同时表示了时间和日期，相当于前两节内容合并到一个对象上了。
        // LocalDateTime 和 LocalTime还有 LocalDate 一样，都是不可变的。LocalDateTime 提供了一些能访问具体字段的方法。
        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek);      // WEDNESDAY

        Month month = sylvester.getMonth();
        System.out.println(month);          // DECEMBER

        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);    // 1439


        // 只要附加上时区信息，就可以将其转换为一个时间点Instant对象，Instant时间点对象可以很容易的转换为老式的
        Instant instant = sylvester
                .atZone(ZoneId.systemDefault())
                .toInstant();

        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);     // Wed Dec 31 23:59:59 CET 2014

        // 格式化LocalDateTime和格式化时间和日期一样的，除了使用预定义好的格式外，我们也可以自己定义格式：
        DateTimeFormatter formatter =
                DateTimeFormatter
                        .ofPattern("MMM dd, yyyy - HH:mm", Locale.ENGLISH);
        LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
        String string = formatter.format(parsed);
        System.out.println(string);     // Nov 03, 2014 - 07:13
        // 和java.text.NumberFormat不一样的是新版的DateTimeFormatter是不可变的，所以它是线程安全的。 关于时间日期格式的详细信息在这里。
    }

    // LocalDate(本地日期)
    @Test
    public void testLocalDate() {
        LocalDate today = LocalDate.now();//获取现在的日期
        System.out.println("今天的日期: "+today);//2019-03-12
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        System.out.println("明天的日期: "+tomorrow);//2019-03-13
        LocalDate yesterday = tomorrow.minusDays(2);
        System.out.println(tomorrow.getDayOfWeek());
        System.out.println("昨天的日期: "+yesterday);//2019-03-11
        LocalDate independenceDay = LocalDate.of(2019, Month.MARCH, 12);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println("今天是周几:"+dayOfWeek);//TUESDAY


        // ==================
        String str1 = "2014==04==12 01时06分09秒";
        // 根据需要解析的日期、时间字符串定义解析所用的格式器
        DateTimeFormatter fomatter1 = DateTimeFormatter
                .ofPattern("yyyy==MM==dd HH时mm分ss秒");

        LocalDateTime dt1 = LocalDateTime.parse(str1, fomatter1);
        System.out.println(dt1); // 输出 2014-04-12T01:06:09

        String str2 = "2014$$$四月$$$13 20小时";
        DateTimeFormatter fomatter2 = DateTimeFormatter
                .ofPattern("yyy$$$MMM$$$dd HH小时");
        LocalDateTime dt2 = LocalDateTime.parse(str2, fomatter2);
        System.out.println(dt2); // 输出 2014-04-13T20:00


        // ==================
        LocalDateTime rightNow=LocalDateTime.now();
        String date=DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(rightNow);
        System.out.println(date);//2019-03-12T16:26:48.29
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        System.out.println(formatter.format(rightNow));//2019-03-12 16:26:48
    }

    // LocalTime(本地时间)
    @Test
    public void testLocalTime() {
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        zone2 = ZoneId.of("Asia/Shanghai");

        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println(now1.isBefore(now2));  // false

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

        System.out.println(hoursBetween);       // -3
        System.out.println(minutesBetween);     // -239


        // =============
        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);       // 23:59:59
        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedTime(FormatStyle.SHORT)
                        .withLocale(Locale.GERMAN);
                        // .withLocale(Locale.ENGLISH);

        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println(leetTime);   // 13:37
    }

    // Timezones(时区)
    @Test
    public void testTimezones() {
        //输出所有区域标识符
        System.out.println(ZoneId.getAvailableZoneIds());

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        ZoneId zone3 = ZoneId.of("Asia/Shanghai");
        System.out.println(zone1.getRules());// ZoneRules[currentStandardOffset=+01:00]
        System.out.println(zone2.getRules());// ZoneRules[currentStandardOffset=-03:00]
        System.out.println(zone3.getRules());// ZoneRules[currentStandardOffset=-03:00]
    }

    // Clock
    @Test
    public void testClock() {
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);//1552379579043

        Instant instant = clock.instant();
        System.out.println(instant);
        System.out.println(instant.getEpochSecond());
        System.out.println(instant.get(ChronoField.NANO_OF_SECOND));
        System.out.println(instant.get(ChronoField.MICRO_OF_SECOND));
        System.out.println(instant.get(ChronoField.MILLI_OF_SECOND));
        // System.out.println(instant.get(ChronoField.INSTANT_SECONDS));

        Date legacyDate = Date.from(instant); //2019-03-12T08:46:42.588Z
        System.out.println(legacyDate);//Tue Mar 12 16:32:59 CST 2019
        Instant instant2 = legacyDate.toInstant();
        System.out.println(instant == instant2);
        System.out.println(instant2.getEpochSecond());

        System.out.println(DateTimeUtil.dateToStr(legacyDate));//Tue Mar 12 16:32:59 CST 2019
    }
}
