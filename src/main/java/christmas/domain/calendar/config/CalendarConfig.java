package christmas.domain.calendar.config;

public enum CalendarConfig {
    Chrismas_2023(2023, 12);

    private final int year;
    private final int month;

    CalendarConfig(int year, int month) {
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
