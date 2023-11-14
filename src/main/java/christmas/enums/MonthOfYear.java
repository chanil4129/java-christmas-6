package christmas.enums;

public enum MonthOfYear {
    DECEMBER(31, 12);

    private final int finalDate;
    private final int month;

    MonthOfYear(int finalDate, int month) {
        this.finalDate = finalDate;
        this.month = month;
    }

    public int getFinalDate() {
        return finalDate;
    }

    public int getMonth() {
        return month;
    }
}
