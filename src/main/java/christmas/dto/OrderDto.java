package christmas.dto;

import christmas.domain.order.config.Menu;
import java.util.EnumMap;

public class OrderDto {
    private final String orders;

    public OrderDto(String orders) {
        this.orders = orders;
    }

    public EnumMap<Menu, Integer> getOrderFormat() {
        
    }
}
