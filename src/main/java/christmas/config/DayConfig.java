package christmas.config;

import christmas.enums.DayOfWeek;
import christmas.enums.MonthOfYear;
import java.util.List;

public enum DayConfig {
    DECEMBER_2023(MonthOfYear.DECEMBER, DayOfWeek.FRIDAY, DecemberSpecialDays2023Config.getSpecialDays());

    private final MonthOfYear month;
    private final DayOfWeek firstDay;
    private final List<Integer> specialDays;

    DayConfig(MonthOfYear month,DayOfWeek firstDay, List<Integer> specialDays) {
        this.month = month;
        this.firstDay = firstDay;
        this.specialDays = specialDays;
    }

    public MonthOfYear getMonth() {
        return month;
    }

    public DayOfWeek getFirstDay() {
        return firstDay;
    }

    public List<Integer> getSpecialDays() {
        return specialDays;
    }
}
