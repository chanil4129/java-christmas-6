package christmas.domain.event.config;

public enum BadgeConfig {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NOTHING("없음", 0);

    private final String level;
    private final int conditionValue;

    BadgeConfig(String level, int conditionValue) {
        this.level = level;
        this.conditionValue = conditionValue;
    }

    public static String calculateLevel(int totalRewardCost) {
        for (BadgeConfig badge : BadgeConfig.values()) {
            if (totalRewardCost >= badge.conditionValue) {
                return badge.level;
            }
        }
        return NOTHING.level;
    }
}
