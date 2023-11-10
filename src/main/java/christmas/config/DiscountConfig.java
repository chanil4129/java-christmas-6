package christmas.config;

public enum DiscountConfig {
    DEFAULT(1000),
    ACCUMULATE_EVERY_DAY(100),
    WEEKDAY(2023),
    WEEKEND(2023),
    SPECIAL(1000),
    ;

    private final int discountPrice;

    DiscountConfig(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }
}
