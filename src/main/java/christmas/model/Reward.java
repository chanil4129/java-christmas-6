package christmas.model;

import christmas.config.DiscountConfig;
import java.util.HashMap;
import java.util.Map;

public class Reward {
    private Map<DiscountConfig, Integer> reward;

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
}
