package christmas.model;

import christmas.config.ErrorConfig;
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
    private final String NOT_NULL_ORDER_ERROR_MESSAGE = ErrorConfig.NOT_NULL_ORDER_ERROR_MESSAGE.getSubMessage();
    private final String NOT_ZERO_ORDER_ERROR_MESSAGE = ErrorConfig.NOT_ZERO_ORDER_ERROR_MESSAGE.getSubMessage();
    private final String RANGE_TOTAL_ORDER_ERROR_MESSAGE = ErrorConfig.RANGE_TOTAL_ORDER_ERROR_MESSAGE.getSubMessage();
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
        validateNotNullOrder(orders);
        validateNotZeroOrder(orders);
        validateRangeTotalOrder(orders);
    }

    private void validateNotNullOrder(Map<Menu, Integer> orders) throws OrderException {
        if (orders == null || orders.isEmpty()) {
            throw new OrderException(NOT_NULL_ORDER_ERROR_MESSAGE);
        }
    }

    private void validateNotZeroOrder(Map<Menu, Integer> orders) {
        orders.forEach((key, value) -> {
            if (value == ZERO) {
                throw new OrderException(NOT_ZERO_ORDER_ERROR_MESSAGE);
            }
        });
    }

    private void validateRangeTotalOrder(Map<Menu, Integer> orders) {
        int menuNumber = orders.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        if (menuNumber < MENU_MINIMUM_NUMBER || menuNumber > MENU_MAXIMUM_NUMBER) {
            throw new OrderException(RANGE_TOTAL_ORDER_ERROR_MESSAGE);
        }
    }
}