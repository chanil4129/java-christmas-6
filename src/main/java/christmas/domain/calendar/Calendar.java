package christmas.domain.calendar;

import christmas.domain.calendar.config.CalendarConfig;
import christmas.domain.calendar.config.DayOfWeekConfig;
import christmas.domain.calendar.config.DayOfWeekConfig.WeekInfo;
import christmas.domain.calendar.config.SpecialDayConfig;
import christmas.exception.DateOfVisitException;
import java.time.LocalDate;

public class Calendar {
    private final int EVENT_YEAR = CalendarConfig.Chrismas_2023.getYear();
    private final int EVENT_MONTH = CalendarConfig.Chrismas_2023.getMonth();
    private final SpecialDayConfig SPECIAL_DAY_INFO = SpecialDayConfig.DECEMBER_2023;
    private final int FIRST_DAY_OF_MONTH = 1;
    private final int LAST_DAY_OF_MONTH = LocalDate.of(EVENT_YEAR, EVENT_MONTH, FIRST_DAY_OF_MONTH).lengthOfMonth();
    private final LocalDate calendar;
    private final int dateOfVisit;

    public Calendar(int dateOfVisit) {
        validate(dateOfVisit);
        this.calendar = LocalDate.of(EVENT_YEAR, EVENT_MONTH, dateOfVisit);
        this.dateOfVisit = dateOfVisit;
    }

    public int getDateOfVisit() {
        return this.dateOfVisit;
    }

    public boolean isWeekday() {
        return DayOfWeekConfig.getWeekInfo(this.calendar) == WeekInfo.WEEKDAY;
    }

    public boolean isSpecialDay() {
        return SpecialDayConfig.isSpecialDay(SPECIAL_DAY_INFO, this.dateOfVisit);
    }

    private void validate(int dateOfVisit) throws DateOfVisitException {
        validateDateInRange(dateOfVisit);
    }

    private void validateDateInRange(int dateOfVisit) throws DateOfVisitException {
        if (dateOfVisit < FIRST_DAY_OF_MONTH || dateOfVisit > LAST_DAY_OF_MONTH) {
            throw new DateOfVisitException();
        }
    }
}
