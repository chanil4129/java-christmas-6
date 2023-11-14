package christmas.model;

import christmas.enums.MonthOfYear;
import christmas.exception.DateOfVisitException;

public class DateOfVisit {
    private final int FIRST_DAY = 1;
    private final MonthOfYear month = MonthOfYear.DECEMBER;
    private final int day;

    public DateOfVisit(int day) {
        validate(month, day);
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    private void validate(MonthOfYear month, int day) {
        validateRangeOfMonth(month, day);
    }

    private void validateRangeOfMonth(MonthOfYear month, int day) throws DateOfVisitException {
        if (day < FIRST_DAY && day > month.getFinalDate()) {
            throw new DateOfVisitException();
        }
    }
}
