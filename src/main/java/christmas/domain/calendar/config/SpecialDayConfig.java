package christmas.domain.calendar.config;

import christmas.domain.calendar.valueobject.SpecialDay;
import java.util.Arrays;
import java.util.List;

public enum SpecialDayConfig {
    DECEMBER_2023(Arrays.asList(
            new SpecialDay("chrismas_day", 25),
            new SpecialDay("special_day1", 3),
            new SpecialDay("special_day2", 10),
            new SpecialDay("special_day3", 17),
            new SpecialDay("special_day4", 24),
            new SpecialDay("special_day5", 31)
    ));

    private final List<SpecialDay> specialDays;

    SpecialDayConfig(List<SpecialDay> specialDays) {
        this.specialDays = specialDays;
    }

    public static boolean isSpecialDay(SpecialDayConfig monthAndYear, int date) {
        for (SpecialDay specialDay : SpecialDayConfig.DECEMBER_2023.specialDays) {
            if (date == specialDay.getDate()) {
                return true;
            }
        }
        return false;
    }
}
