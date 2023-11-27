package christmas.domain.event.config;

public enum DiscountEventConfig {
    D_DAY(0),
    WEEKDAY(2023),
    WEEKEND(2023),
    SPECIAL(1000);

    private final int price;

    DiscountEventConfig(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
