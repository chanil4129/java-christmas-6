package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.config.Menu;
import christmas.exception.OrderException;
import java.util.EnumMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderTest {
    private final String ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    @DisplayName("양송이 수프 2개와 초콜릿 케익 1개의 원가는 27000이다.")
    @Test
    void calculateTotalFirstCost() {
        EnumMap<Menu, Integer> orders = new EnumMap<Menu, Integer>(Menu.class);
        orders.put(Menu.MUSHROOM_SOUP, 2);
        orders.put(Menu.CHOCOLATE_CAKE, 1);
        Order order = new Order(orders);

        int result = order.calculateTotalFirstCost();

        assertThat(result).isEqualTo(27000);
    }

    @DisplayName("주문이 null이면 예외가 발생한다.")
    @Test
    void validateOrderNotNull() {
        Assertions.assertThatThrownBy(() -> new Order(null))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("주문이 비어있으면 예외가 발생한다.")
    @Test
    void validateOrderNotEmpty() {
        EnumMap<Menu, Integer> orders = new EnumMap<Menu, Integer>(Menu.class);

        Assertions.assertThatThrownBy(() -> new Order(orders))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("주문의 각 항목 수량이 0 이하이면 예외가 발생한다.")
    @Test
    void validateEachOrderNotZero() {
        EnumMap<Menu, Integer> orders = new EnumMap<Menu, Integer>(Menu.class);
        orders.put(Menu.MUSHROOM_SOUP, 0);

        Assertions.assertThatThrownBy(() -> new Order(orders))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("총 주문 수량이 범위 내에 있지 않으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 21})
    void validateTotalOrderInRange(int totalQuantity) {
        EnumMap<Menu, Integer> orders = new EnumMap<>(Menu.class);
        orders.put(Menu.MUSHROOM_SOUP, totalQuantity);

        Assertions.assertThatThrownBy(() -> new Order(orders))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }

    @DisplayName("주문이 오직 음료만 포함되면 예외가 발생한다.")
    @Test
    void validateOnlyDrink() {
        EnumMap<Menu, Integer> orders = new EnumMap<>(Menu.class);
        orders.put(Menu.ZERO_COLA, 2);
        orders.put(Menu.RED_WINE, 3);

        Assertions.assertThatThrownBy(() -> new Order(orders))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining(ERROR_MESSAGE);
    }
}