package christmas.domain.calendar.valueobject;

public class SpecialDay {
    private final String name;
    private final int date;

    public SpecialDay(String name, int date) {
        this.name = name;
        this.date = date;
    }

    public int getDate() {
        return date;
    }
}
