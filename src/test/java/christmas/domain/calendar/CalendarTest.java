package christmas.domain.calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import christmas.exception.DateOfVisitException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CalendarTest {

    private final String DATE_IN_RANGE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    @DisplayName("평일인지 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {
            3, 4, 5, 6, 7,
            10, 11, 12, 13, 14,
            17, 18, 19, 20, 21,
            24, 25, 26, 27, 28,
            31
    })
    void isWeekday(int day) {
        Calendar calendar = new Calendar(day);

        boolean result = calendar.isWeekday();

        assertThat(result).isTrue();
    }

    @DisplayName("주말인지 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {
            1, 2, 8, 9, 15, 16, 22, 23, 29, 30
    })
    void isWeekend(int day) {
        Calendar calendar = new Calendar(day);

        boolean result = calendar.isWeekday();

        assertThat(result).isFalse();
    }

    @DisplayName("특별한 날인지 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {
            3, 10, 17, 24, 25, 31
    })
    void isSpecialDay(int day) {
        Calendar calendar = new Calendar(day);

        boolean result = calendar.isSpecialDay();

        assertThat(result).isTrue();
    }

    @DisplayName("특별한 날이 아닌지 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {
            1, 2, 29, 30,
    })
    void isNotSpecialDay(int day) {
        Calendar calendar = new Calendar(day);

        boolean result = calendar.isSpecialDay();

        assertThat(result).isFalse();
    }

    @DisplayName("날짜가 범위 안에 있지 않으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {
            -1, 0, 32
    })
    void validateDateInRange(int day) {
        assertThatThrownBy(() -> new Calendar(day))
                .isInstanceOf(DateOfVisitException.class)
                .hasMessageContaining(DATE_IN_RANGE_ERROR_MESSAGE);
    }
}