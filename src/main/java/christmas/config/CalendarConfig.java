package christmas.config;

public enum CalendarConfig {
    DECEMBER(31);

    private final int final_date;

    CalendarConfig(int final_date) {
        this.final_date = final_date;
    }

    public int getFinal_date() {
        return final_date;
    }
}
