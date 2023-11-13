package christmas.model;

import christmas.config.BadgeConfig;

public class Badge {
    private final int totalReward;

    public Badge(int totalReward) {
        this.totalReward = totalReward;
    }

    public BadgeConfig assignBadgeLevel() {
        if (totalReward >= BadgeConfig.SANTA.getConditionValue()) {
            return BadgeConfig.SANTA;
        }
        if (totalReward >= BadgeConfig.TREE.getConditionValue()) {
            return BadgeConfig.TREE;
        }
        if (totalReward >= BadgeConfig.STAR.getConditionValue()) {
            return BadgeConfig.STAR;
        }
        return BadgeConfig.DEFAULT;
    }
}
