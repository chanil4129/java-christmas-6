package christmas.domain.order;

import christmas.domain.order.config.Menu;
import christmas.domain.order.config.MenuConfig;
import christmas.exception.OrderException;
import java.util.EnumMap;

public class Order {
    private final int ZERO = 0;
    private final EnumMap<Menu, Integer> orders;

    public Order(EnumMap<Menu, Integer> orders) {
        validate(orders);
        this.orders = orders;
    }

    public int calculateTotalFirstCost() {
        return this.orders.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    private void validate(EnumMap<Menu, Integer> orders) {
        validateNotNull(orders);
        validateNotEmpty(orders);
        validateEachOrderNotZero(orders);
        validateTotalOrderInRange(orders);
        validateOnlyDrink(orders);
    }

    private void validateNotNull(EnumMap<Menu, Integer> orders) {
        if (orders == null) {
            throw new OrderException();
        }
    }

    private void validateNotEmpty(EnumMap<Menu, Integer> orders) {
        if (orders.isEmpty()) {
            throw new OrderException();
        }
    }

    private void validateEachOrderNotZero(EnumMap<Menu, Integer> orders) {
        boolean hasZero = orders.values().stream()
                .anyMatch(orderNumber -> orderNumber < ZERO);
        if (hasZero) {
            throw new OrderException();
        }
    }

    private void validateTotalOrderInRange(EnumMap<Menu, Integer> orders) {
        int totalQuantity = orders.values().stream().mapToInt(Integer::intValue).sum();
        if (totalQuantity < MenuConfig.MINIMUM_ORDER_NUMBER.getValue()
                || totalQuantity > MenuConfig.MAXIMUM_ORDER_NUMBER.getValue()) {
            throw new OrderException();
        }
    }

    private void validateOnlyDrink(EnumMap<Menu, Integer> orders) {
        boolean onlyDrinks = orders.keySet().stream().allMatch(menu -> menu.getType() == Menu.MenuType.DRINK);
        if (onlyDrinks) {
            throw new OrderException();
        }
    }
}
