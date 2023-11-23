package christmas.model.calendar.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum DecemberSpecialDays2023 {
    CHRISTMAS(25),
    SPECIAL_DAY_1(3),
    SPECIAL_DAY_2(10),
    SPECIAL_DAY_3(17),
    SPECIAL_DAY_4(24),
    SPECIAL_DAY_5(31);

    private final int day;

    DecemberSpecialDays2023(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public static List<Integer> getSpecialDays() {
        List<Integer> specialDays = new ArrayList<>();
        for (DecemberSpecialDays2023 day : DecemberSpecialDays2023.values()) {
            specialDays.add(day.getDay());
        }
        return Collections.unmodifiableList(specialDays);
    }
}
