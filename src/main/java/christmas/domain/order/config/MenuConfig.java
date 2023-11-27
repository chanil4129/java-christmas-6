package christmas.domain.order.config;

public enum MenuConfig {
    MINIMUM_ORDER_NUMBER(1),
    MAXIMUM_ORDER_NUMBER(20);

    private final int value;

    MenuConfig(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
