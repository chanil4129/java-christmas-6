package christmas.config;

public enum GiveawayConfig {
    MINIMUM_PRICE(12000),
    NUMBER(1);

    private final int value;

    GiveawayConfig(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
