package christmas.enums;

public enum DayOfWeek {
    SUNDAY(Week.WEEKDAY),
    MONDAY(Week.WEEKDAY),
    TUESDAY(Week.WEEKDAY),
    WEDNESDAY(Week.WEEKDAY),
    THURSDAY(Week.WEEKDAY),
    FRIDAY(Week.WEEKEND),
    SATURDAY(Week.WEEKEND),
    ;

    private final Week week;

    DayOfWeek(Week week) {
        this.week = week;
    }

    public Week getWeek() {
        return week;
    }
}
