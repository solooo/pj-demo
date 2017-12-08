package net.solooo.demo.other;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * Description:
 * Author:Eric
 * Date:2017/12/8
 */
public class DateTimeTest {

    @Test
    public void instatTest() {
        System.out.println("LocalDate.now() = " + LocalDate.now());
        System.out.println("LocalTime.now() = " + LocalTime.now());
        System.out.println("LocalDateTime.now() = " + LocalDateTime.now());
        System.out.println("Instant.now() = " + Instant.ofEpochMilli(System.currentTimeMillis()));
        System.out.println("ZonedDateTime.now() = " + ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        System.out.println("Date parse 20170101 = " + LocalDate.parse("20170101", DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println("Date parse 2017/01/01 = " + LocalDate.parse("2017/01/01", DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        System.out.println("Date to string = " + LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println("Date to string = " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒")));

        Period period = Period.between(LocalDate.now(), LocalDate.of(2018, 1,7));
        System.out.println(String.format("相差时间：%s 年 %s 月 %s 天" , period.getYears(), period.getMonths(), period.getDays()));

        Duration duration = Duration.between(LocalDateTime.now(), LocalDateTime.of(2018, 1, 7, 0, 0, 0));
        System.out.println("相差天数：" + duration.toDays());
        System.out.println("相差分钟数：" + duration.toMinutes());

        System.out.println(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
    }
}
