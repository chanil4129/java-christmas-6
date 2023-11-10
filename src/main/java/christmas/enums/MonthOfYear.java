package christmas.enums;

public enum MonthOfYear {
    DECEMBER(31);

    private final int finalDate;

    MonthOfYear(int finalDate) {
        this.finalDate = finalDate;
    }

    public int getFinalDate() {
        return finalDate;
    }
}
