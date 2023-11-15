package christmas.model;

import christmas.config.DiscountConfig;
import christmas.model.vo.Badge;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Reward {
    private Map<DiscountConfig, Integer> reward;
    private int finalCost;

    public Reward() {
        this.reward = new HashMap<>();
    }

    public void put(DiscountConfig discount, int price) {
        reward.put(discount, price);
    }

    public int getTotalReward() {
        return reward.entrySet().stream()
                .mapToInt((entry) -> entry.getValue())
                .sum();
    }

    public Map<DiscountConfig, Integer> getReward() {
        return Collections.unmodifiableMap(reward);
    }

    public Badge getBadge() {
        Badge result = new Badge(getTotalReward());
        result.assignBadgeLevel();
        return result;
    }

    public void calculateFinalCost(int firstCost) {
        finalCost = firstCost - getTotalReward();
    }

    public int getFinalCost() {
        return finalCost;
    }
}
