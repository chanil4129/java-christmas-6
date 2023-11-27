package christmas.domain.event.config;

public enum EventConfig {
    EVENT_APPLY(10000),
    GIVEAWAY_APPLY(120000);

    private final int conditionValue;

    EventConfig(int conditionValue) {
        this.conditionValue = conditionValue;
    }

    public int getConditionValue() {
        return conditionValue;
    }
}
