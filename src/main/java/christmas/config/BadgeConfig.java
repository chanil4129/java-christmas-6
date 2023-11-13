package christmas.config;

public enum BadgeConfig {
    STAR(5000),
    TREE(10000),
    SANTA(20000),
    DEFAULT(0);

    private final int conditionValue;

    BadgeConfig(int conditionValue) {
        this.conditionValue = conditionValue;
    }

    public int getConditionValue() {
        return conditionValue;
    }
}
