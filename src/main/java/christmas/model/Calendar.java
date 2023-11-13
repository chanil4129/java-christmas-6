package christmas.model;

import christmas.enums.MonthOfYear;
import christmas.enums.DayOfWeek;
import christmas.enums.Week;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calendar {
    private final int FIRST_DAY = 1;
    private final int WEEK = 7;
    private final MonthOfYear month;
    private final DayOfWeek firstDay;
    private Map<Integer, DayOfWeek> calendarDay;
    private List<Integer> specialDays;

    public Calendar(MonthOfYear month, DayOfWeek firstDay) {
        this.month = month;
        this.firstDay = firstDay;
        this.specialDays = new ArrayList<>();
    }

    public void createCalendarDay() {
        calendarDay = new HashMap<>();
        int finalDate = month.getFinalDate();
        int weekIndex = firstDay.ordinal();

        for (int day = FIRST_DAY; day <= finalDate; day++) {
            calendarDay.put(day, DayOfWeek.values()[weekIndex++ % WEEK]);
        }
    }

    public void addSpecialDays(List<Integer> specialDays) {
        this.specialDays.addAll(specialDays);
    }

    public boolean isWeekDay(int day) {
        if (calendarDay.get(day).getWeek() == Week.WEEKDAY) {
            return true;
        }
        return false;
    }

    public boolean isSpecialDay(int day) {
        if (specialDays.contains(day)) {
            return true;
        }
        return false;
    }
}
