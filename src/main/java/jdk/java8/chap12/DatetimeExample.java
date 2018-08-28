package jdk.java8.chap12;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.*;
import java.util.Locale;

import static java.time.temporal.TemporalAdjusters.nextOrSame;

public class DatetimeExample {

    public static void main(String[] args) {
        useLocalDate();
        useTemporalAdjuster();
        useDateFormatter();
    }

    public static void useLocalDate() {
        System.out.println("using LocalDate");
        //LocalDate localDate = LocalDate.of(2018, 2, 12);
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int length = localDate.lengthOfMonth();
        boolean leap = localDate.isLeapYear();

        System.out.println(localDate);
        System.out.println(year + " " + dayOfWeek.getValue() + " " + length);
        System.out.println(leap);

        int year1 = localDate.get(ChronoField.YEAR);
        System.out.println(year1);


        LocalTime localTime = LocalTime.of(9, 53, 16);
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();
        System.out.println(localTime);

        LocalDate localDate1 = LocalDate.parse("2018-02-12");
        LocalTime localTime1 = LocalTime.parse("09:56:23");

        LocalDateTime dateTime = LocalDateTime.of(2018, 2, 12, 10, 04, 34);
        LocalDateTime dateTime1 = LocalDateTime.of(localDate, localTime);
        LocalDateTime dateTime2 = localDate.atTime(localTime);
        LocalDateTime dateTime3 = localTime.atDate(localDate);
        System.out.println(dateTime);

        LocalDate localDate2 = dateTime.toLocalDate();
        LocalTime localTime2 = dateTime.toLocalTime();


        Instant instant = Instant.ofEpochSecond(3);
        Instant instant1 = Instant.now();
        System.out.println(instant);

        Duration duration = Duration.between(instant1, instant);
        Duration duration1 = Duration.between(localTime2, localTime1);
        Duration duration2 = Duration.between(dateTime2, dateTime1);
        System.out.println(duration);

        Period tenDays = Period.between(LocalDate.of(2018, 01, 1), LocalDate.of(2018, 01, 11));
        System.out.println(tenDays);

        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);
        Period tenDays2 = Period.ofDays(10);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
    }

    public static void useTemporalAdjuster() {
        System.out.println("using TemporalAdjusters");
        LocalDate date = LocalDate.of(2018, 2, 12);
        date = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(date);
        date = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(date);

        date = date.with(new NextWorkingDay());
        System.out.println(date);
        date = date.with(nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date);
        date = date.with(new NextWorkingDay());
        System.out.println(date);

        date = date.with(nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date);
        date = date.with(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        System.out.println(date);
    }

    private static class NextWorkingDay implements TemporalAdjuster {

        @Override
        public Temporal adjustInto(Temporal temporal) {
            DayOfWeek day = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayAdd = 1;
            if (day == DayOfWeek.FRIDAY) {
                dayAdd = 3;
            }
            if (day == DayOfWeek.SATURDAY) {
                dayAdd = 2;
            }
            return temporal.plus(dayAdd, ChronoUnit.DAYS);
        }
    }

    public static void useDateFormatter() {
        System.out.println("using DateFormatter");
        LocalDate localDate = LocalDate.of(2018, 02, 12);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);

        System.out.println(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(localDate.format(formatter));
        System.out.println(localDate.format(italianFormatter));

        DateTimeFormatter complexFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);
        System.out.println(localDate.format(complexFormatter));
    }
}
