package christmas.domain.event.config;

public enum DDayDiscountEventConfig {
    MONTH_DISCOUNT(1000),
    EACH_DAY_DISCOUNT(100);

    private final int price;

    DDayDiscountEventConfig(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
