package christmas.domain.calendar.config;

import christmas.exception.DayOfWeekException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public enum DayOfWeekConfig {
    SUNDAY(WeekInfo.WEEKDAY),
    MONDAY(WeekInfo.WEEKDAY),
    TUESDAY(WeekInfo.WEEKDAY),
    WEDNESDAY(WeekInfo.WEEKDAY),
    THURSDAY(WeekInfo.WEEKDAY),
    FRIDAY(WeekInfo.WEEKEND),
    SATURDAY(WeekInfo.WEEKEND);

    private final WeekInfo weekInfo;

    DayOfWeekConfig(WeekInfo weekInfo) {
        this.weekInfo = weekInfo;
    }

    public enum WeekInfo {
        WEEKDAY, WEEKEND;
    }

    public static WeekInfo getWeekInfo(LocalDate calendar) {
        DayOfWeek dayOfWeek = calendar.getDayOfWeek();
        for (DayOfWeekConfig dayOfWeekConfig : DayOfWeekConfig.values()) {
            if (dayOfWeek.name().equals(dayOfWeekConfig.name())) {
                return dayOfWeekConfig.weekInfo;
            }
        }
        throw new DayOfWeekException();
    }
}
