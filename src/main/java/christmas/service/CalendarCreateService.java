package christmas.service;

import christmas.config.DayConfig;
import christmas.model.Calendar;

public class CalendarCreateService {
    private final Calendar calendar;
    private final DayConfig DAYCONFIG = DayConfig.DECEMBER_2023;

    public CalendarCreateService(Calendar calendar) {
        this.calendar = new Calendar(DAYCONFIG.getMonth(), DAYCONFIG.getFirstDay());
    }

    public void createCalendar() {
        calendar.createCalendarDay();
        calendar.addSpecialDays(DAYCONFIG.getSpecialDays());
    }
}
