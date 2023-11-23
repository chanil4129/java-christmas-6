package christmas.model.calendar.constant;

import christmas.exception.DayOfWeekException;
import java.time.LocalDate;

public enum DayOfWeek {
    SUNDAY(Week.WEEKDAY),
    MONDAY(Week.WEEKDAY),
    TUESDAY(Week.WEEKDAY),
    WEDNESDAY(Week.WEEKDAY),
    THURSDAY(Week.WEEKDAY),
    FRIDAY(Week.WEEKEND),
    SATURDAY(Week.WEEKEND),
    ;

    private final Week week;

    public enum Week {
        WEEKDAY, WEEKEND
    }

    DayOfWeek(Week week) {
        this.week = week;
    }

    public static final Week calculateWeek(LocalDate date) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.name().equals(date.getDayOfWeek().name())) {
                return day.week;
            }
        }
        throw new DayOfWeekException();
    }
}
