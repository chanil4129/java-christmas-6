package christmas.model.calendar;

import christmas.model.calendar.config.DecemberSpecialDays2023;
import christmas.model.calendar.config.EventDayCalendar;
import christmas.model.calendar.constant.DayOfWeek;
import christmas.model.calendar.constant.DayOfWeek.Week;
import java.time.LocalDate;

public class Calendar {
    private final int YEAR = EventDayCalendar.DECEMBER_2023.getYear();
    private final int MONTH = EventDayCalendar.DECEMBER_2023.getMonth();
    private final LocalDate calendar;

    public Calendar(DateOfVisit date) {
        this.calendar = LocalDate.of(YEAR, MONTH, date.getDate());
    }

    public boolean isWeekDay() {
        return DayOfWeek.calculateWeek(this.calendar) == Week.WEEKDAY;
    }

    public boolean isSpecialDay() {
        return DecemberSpecialDays2023.getSpecialDays().contains(this.calendar.getDayOfMonth());
    }
}
