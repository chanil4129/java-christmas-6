package christmas.service;

import christmas.domain.order.Order;
import christmas.dto.OrderDto;

public class OrderService {
    private final OrderDto orderDto;

    public OrderService(OrderDto orderDto) {
        this.orderDto = orderDto;
    }

    public Order create() {
        new Order(this.orderDto.getOrderFormat());
    }
}
