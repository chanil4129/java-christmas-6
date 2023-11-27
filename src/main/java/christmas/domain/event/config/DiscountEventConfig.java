package christmas.domain.event.config;

public enum DiscountEventConfig {
    D_DAY(0, "크리스마스 디데이 할인"),
    WEEKDAY(2023, "평일 할인"),
    WEEKEND(2023, "주말 할인"),
    SPECIAL(1000, "특별 할인");

    private final int price;
    private final String name;

    DiscountEventConfig(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
