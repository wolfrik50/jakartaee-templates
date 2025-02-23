package io.wulfcodes.plain.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Random;

import static java.time.Month.JANUARY;
import static java.time.Month.FEBRUARY;
import static java.time.Month.MARCH;
import static java.time.Month.APRIL;
import static java.time.Month.MAY;
import static java.time.Month.JUNE;
import static java.time.Month.JULY;
import static java.time.Month.AUGUST;
import static java.time.Month.SEPTEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.NOVEMBER;
import static java.time.Month.DECEMBER;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;

public class DateTimeUtils {
    private static final Random RANDOM = new Random();

    private DateTimeUtils() {}

    public static LocalDateTime generateRandomDateTime() {
        int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2030, 1, 1).toEpochDay();
        long randomDay = minDay + RANDOM.nextInt(maxDay - minDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

        int minSecond = 1;
        int maxSecond = 24 * 60 * 60;
        long randomSecond = RANDOM.nextInt(maxSecond - minSecond);
        LocalTime randomTime = LocalTime.ofSecondOfDay(randomSecond).plusNanos(RANDOM.nextLong());

        return LocalDateTime.of(randomDate, randomTime);
    }

    public static String beautifyDateTime(LocalDateTime instant) {
        String dayName = UPPER_UNDERSCORE.to(UPPER_CAMEL, instant.getDayOfWeek().name());
        String monthName = UPPER_UNDERSCORE.to(UPPER_CAMEL, instant.getMonth().name()).substring(0, 3);

        String dateName = "%02d-%s-%d".formatted(instant.getDayOfMonth(), monthName, instant.getYear());

        String actualDateTime = "[%02d-%02d-%d|%02d:%02d:%02d.%s]".formatted(instant.getDayOfMonth(), instant.getMonthValue(), instant.getYear(),
            instant.getHour(), instant.getMinute(), instant.getSecond(), "%09d".formatted(instant.getNano()).substring(0, 4));

        return String.format("%s %s %s", dayName, dateName, actualDateTime);
    }

    public static boolean compareDay(Byte expectedDay, Integer actualDay) {
        return Byte.valueOf(actualDay.byteValue()).equals(expectedDay);
    }

    public static boolean compareMonth(String expectedMonth, Month actualMonth) {
        return switch (expectedMonth) {
            case "Jan" -> JANUARY.equals(actualMonth);
            case "Feb" -> FEBRUARY.equals(actualMonth);
            case "Mar" -> MARCH.equals(actualMonth);
            case "Apr" -> APRIL.equals(actualMonth);
            case "May" -> MAY.equals(actualMonth);
            case "Jun" -> JUNE.equals(actualMonth);
            case "Jul" -> JULY.equals(actualMonth);
            case "Aug" -> AUGUST.equals(actualMonth);
            case "Sep" -> SEPTEMBER.equals(actualMonth);
            case "Oct" -> OCTOBER.equals(actualMonth);
            case "Nov" -> NOVEMBER.equals(actualMonth);
            case "Dec" -> DECEMBER.equals(actualMonth);
            default -> false;
        };
    }

    public static Boolean compareYear(Short expectedYear, Integer actualYear) {
        return Short.valueOf(actualYear.shortValue()).equals(expectedYear);
    }
}
