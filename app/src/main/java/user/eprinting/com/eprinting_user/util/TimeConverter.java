package user.eprinting.com.eprinting_user.util;

/**
 * @author AKBAR <dicky.akbar@dwidasa.com>
 */

public class TimeConverter {
    public static long convertToMinute(int minute) {
        return minute * 60 * 1000;
    }

    public static long convertToSecond(int second) {
        return second * 1000;
    }

    public static long convertToHour(int hour) {
        return hour * (60 * 60 * 1000);
    }
}
