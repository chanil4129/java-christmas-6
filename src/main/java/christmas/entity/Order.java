package christmas.entity;

import christmas.config.GiveawayConfig;
import christmas.config.Menu;
import christmas.config.MenuConfig;
import christmas.exception.OrderException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    private final int ZERO = 0;
    private final int GIVEAWAY_MINIMUM_PRICE = GiveawayConfig.MINIMUM_PRICE.getValue();
    private final int MENU_MINIMUM_NUMBER = MenuConfig.MENU_MINIMUM_NUMBER.getValue();
    private final int MENU_MAXIMUM_NUMBER = MenuConfig.MENU_MAXIMUM_NUMBER.getValue();
    private Map<Menu, Integer> orders;

    public Order(Map<Menu, Integer> orders) {
        validate(orders);
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

    private void validate(Map<Menu, Integer> orders) {
        validateNotNull(orders);
        validateRangeMenu(orders);
    }

    private void validateNotNull(Map<Menu, Integer> orders) {
        if (orders == null || orders.isEmpty()) {
            throw new OrderException();
        }
    }

    private void validateRangeMenu(Map<Menu, Integer> orders) {
        orders.forEach((key, value) -> {
            if (value < MENU_MINIMUM_NUMBER || value > MENU_MAXIMUM_NUMBER) {
                throw new OrderException();
            }
        });
    }
}
