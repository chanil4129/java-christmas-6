package christmas.model;

import christmas.enums.MonthOfYear;
import christmas.exception.DateOfVisitException;

public class DateOfVisit {
    private final int FIRST_DAY = 1;
    private final MonthOfYear month;
    private final int day;

    public DateOfVisit(MonthOfYear month, int day) {
        validate(month, day);
        this.month = month;
        this.day = day;
    }

    private void validate(MonthOfYear month, int day) {
        validateRangeOfMonth(month, day);
    }

    private void validateRangeOfMonth(MonthOfYear month, int day) {
        if (day < FIRST_DAY && day > month.getFinalDate()) {
            throw new DateOfVisitException();
        }
    }
}
