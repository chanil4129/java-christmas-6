package christmas.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import christmas.config.ErrorConfig;
import christmas.config.Menu;
import christmas.exception.OrderException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderTest {
    private Map<Menu, Integer> orders;

    @BeforeEach
    void setUp() {
        orders = new HashMap<>();
    }

    @DisplayName("유효성 검증 예외 발생 확인")
    @Nested
    class 유효성_검증_예외_발생 {
        @DisplayName("주문이_null이면_예외발생한다.")
        @Test
        void validate_주문이_null이면_예외발생() {
            Map<Menu, Integer> nullOrder = null;

            throwOrderException(nullOrder);
        }

        @DisplayName("주문이_비어있으면_예외발생한다.")
        @Test
        void validate_주문이_비어있으면_예외발생() {
            throwOrderException(orders);
        }

        @DisplayName("주문 개수는 1개 이상 20개 이하가 아니면 예외가 발생한다.")
        @ParameterizedTest
        @CsvSource({
                "MUSHROOM_CREAM_SOUP, 0, TAPAS, 5",
                "TAPAS, 1, CAESAR_SALAD, -1",
                "T_BONE_STEAK, 10, SEAFOOD_PASTA, 21",
                "BARBECUE_RIBS, 100, ICE_CREAM, 2"
        })
        void validate_주문_개수_예외발생(Menu menu1, int quantity1, Menu menu2, int quantity2) {
            Map<Menu, Integer> orders = new HashMap<>();
            orders.put(menu1, quantity1);
            orders.put(menu2, quantity2);

            throwOrderException(orders);
        }

        private static void throwOrderException(Map<Menu, Integer> orders) {
            assertThatThrownBy(() -> new Order(orders))
                    .isInstanceOf(OrderException.class)
                    .hasMessageContaining(ErrorConfig.NOT_VALIDATE_ORDER_ERROR_MESSAGE.getMessage());
        }
    }

    @DisplayName("method 기능 확인")
    @Nested
    class method {
        private Order validOrder;

        @BeforeEach
        void setValidOrder() {
            orders.put(Menu.MUSHROOM_CREAM_SOUP, 2);
            orders.put(Menu.T_BONE_STEAK, 1);

            validOrder = new Order(orders);
        }

        @Test
        @DisplayName("주문 총액 계산")
        void testCalculateTotalCost() {
            int expectedCost = Menu.MUSHROOM_CREAM_SOUP.getPrice() * 2 + Menu.T_BONE_STEAK.getPrice();
            assertThat(validOrder.getFirstCost()).isEqualTo(expectedCost);
        }

        @Test
        @DisplayName("경품 수령 가능 여부")
        void testReceivableGiveaway() {
            assertThat(validOrder.receivableGiveaway()).isTrue();
        }
    }
}