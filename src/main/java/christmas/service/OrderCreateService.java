package christmas.service;

import christmas.config.Menu;
import christmas.model.Order;
import java.util.Map;

public class OrderCreateService {

    public OrderCreateService() {
    }

    public Order createOrder(Map<Menu, Integer> order) {
        return new Order(order);
    }
}
