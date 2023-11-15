package christmas.config;

public enum BadgeConfig {
    STAR(5000, "별"),
    TREE(10000, "트리"),
    SANTA(20000, "산타"),
    DEFAULT(0, "없음");

    private final int conditionValue;
    private final String name;

    BadgeConfig(int conditionValue, String name) {
        this.conditionValue = conditionValue;
        this.name = name;
    }

    public int getConditionValue() {
        return conditionValue;
    }

    public String getName() {
        return name;
    }
}
