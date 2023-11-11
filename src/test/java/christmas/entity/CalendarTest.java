package christmas.entity;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.config.DecemberSpecialDays2023Config;
import christmas.enums.DayOfWeek;
import christmas.enums.MonthOfYear;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CalendarTest {
    private Calendar calendar;
    private final MonthOfYear MONTH = MonthOfYear.DECEMBER;
    private final DayOfWeek FIRST_DAY = DayOfWeek.FRIDAY;
    private final List<Integer> SPECIAL_DAYS = DecemberSpecialDays2023Config.getSpecialDays();

    @BeforeEach
    void setUp() {
        calendar = new Calendar(MONTH, FIRST_DAY);
        calendar.createCalendarDay();
        calendar.addSpecialDays(SPECIAL_DAYS);
    }

    @DisplayName("주중 날짜면 true를 리턴한다.")
    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 25, 26, 27, 28})
    void isWeekDay_주중_날짜_확인(int day) {
        assertThat(calendar.isWeekDay(day)).isTrue();
    }

    @DisplayName("주말이면 false를 리턴한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 29, 30})
    void isWeekDay_주말_날짜_확인(int day) {
        assertThat(calendar.isWeekDay(day)).isFalse();
    }

    @DisplayName("특별한 날이면 true를 리턴한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void isSpecialDay_특별한_날_확인(int day) {
        assertThat(calendar.isSpecialDay(day)).isTrue();
    }

    @DisplayName("특별한 날이 아니면 false를 리턴한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 16})
    void isSpecialDay_특별한_날_아닌날_확인(int day) {
        assertThat(calendar.isSpecialDay(day)).isFalse();
    }
}