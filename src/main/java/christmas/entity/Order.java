package christmas.entity;

import christmas.config.GiveawayConfig;
import christmas.config.MenuConfig;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    private final int ZERO = 0;
    private final int GIVEAWAY_MINIMUM_PRICE = GiveawayConfig.MINIMUM_PRICE.getValue();
    private Map<MenuConfig, Integer> orders;

    public Order(Map<MenuConfig, Integer> orders) {
        // TODO: 2023-11-11 유효한 주문인지 확인 - 최대 메뉴 20개까지
        this.orders = orders;
    }

    public int getFirstCost() {
        AtomicInteger totalCost = new AtomicInteger(ZERO);
        orders.forEach((key, value) -> {
            int menuCost = key.getPrice() * value;
            totalCost.set(totalCost.get() + key.getPrice() * value);
        });
        return totalCost.get();
    }

    public boolean receivableGiveaway() {
        if (getFirstCost() > GIVEAWAY_MINIMUM_PRICE) {
            return true;
        }
        return false;
    }
}
