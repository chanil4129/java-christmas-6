package christmas.model.calendar.config;

public enum EventDayCalendar {
    DECEMBER_2023(2023, 12);

    private final int year;
    private final int month;

    EventDayCalendar(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }
}
