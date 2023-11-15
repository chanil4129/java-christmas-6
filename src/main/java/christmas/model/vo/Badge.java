package christmas.model.vo;

import christmas.config.BadgeConfig;

public class Badge {
    private final int totalReward;
    private BadgeConfig level;

    public Badge(int totalReward) {
        this.totalReward = totalReward;
    }

    public void assignBadgeLevel() {
        if (totalReward >= BadgeConfig.SANTA.getConditionValue()) {
            level = BadgeConfig.SANTA;
            return;
        }
        if (totalReward >= BadgeConfig.TREE.getConditionValue()) {
            level = BadgeConfig.TREE;
            return;
        }
        if (totalReward >= BadgeConfig.STAR.getConditionValue()) {
            level = BadgeConfig.STAR;
            return;
        }
        level = BadgeConfig.DEFAULT;
    }

    public BadgeConfig getLevel() {
        return level;
    }
}
