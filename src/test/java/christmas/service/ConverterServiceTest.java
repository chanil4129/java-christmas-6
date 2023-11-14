package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.*;

import christmas.config.ErrorMessageConfig;
import christmas.config.Menu;
import christmas.dto.DateOfVisitDto;
import christmas.dto.OrderDto;
import christmas.exception.DateOfVisitException;
import christmas.exception.OrderException;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ConverterServiceTest {
    private ConverterService converterService;
    private DateOfVisitDto dateOfVisitDto;
    private OrderDto orderDto;

    @BeforeEach
    void setUp() {
        converterService = new ConverterService();
    }

    @DisplayName("방문 날짜 converter")
    @Nested
    class DateOfVisit {
        @DisplayName("정상적인 입력")
        @Test
        void convert_DateOfVisit() {
            dateOfVisitDto = new DateOfVisitDto("3");
            assertDoesNotThrow(() -> converterService.convertDtoToInt(dateOfVisitDto));
        }

        @DisplayName("숫자가 아니면 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {
                "a",
                "3a"
        })
        void converter_DateOfVisit_예외_발생(String input) {
            dateOfVisitDto = new DateOfVisitDto(input);
            assertThatThrownBy(() -> converterService.convertDtoToInt(dateOfVisitDto))
                    .isInstanceOf(DateOfVisitException.class)
                    .hasMessageContaining(ErrorMessageConfig.NOT_VALID_DATE_OF_VISIT_ERROR_MESSAGE.getErrorMessage());
        }
    }

    @DisplayName("주문 converter")
    @Nested
    class Order {
        @DisplayName("단일 메뉴")
        @ParameterizedTest
        @CsvSource({
                "양송이수프-1, MUSHROOM_CREAM_SOUP, 1",
                "타파스-2, TAPAS, 2",
        })
        void convert_order(String input, Menu menu, int expectedCount) {
            orderDto = new OrderDto(input);
            Map<Menu, Integer> result = converterService.convertDtoToOrder(orderDto);
            assertThat(result).containsKey(menu);
            assertThat(result.get(menu)).isEqualTo(expectedCount);
        }

        @DisplayName("여러 메뉴에 대한 정상적인 주문")
        @Test
        void convert_complex_order() {
            String input = "양송이수프-1,타파스-2,제로콜라-2";
            OrderDto orderDto = new OrderDto(input);
            Map<Menu, Integer> result = converterService.convertDtoToOrder(orderDto);

            // 각 메뉴에 대한 검증
            assertThat(result).containsKeys(Menu.MUSHROOM_CREAM_SOUP, Menu.TAPAS, Menu.ZERO_COLA);
            assertThat(result.get(Menu.MUSHROOM_CREAM_SOUP)).isEqualTo(1);
            assertThat(result.get(Menu.TAPAS)).isEqualTo(2);
            assertThat(result.get(Menu.ZERO_COLA)).isEqualTo(2);
        }

        @DisplayName("잘못된 주문 형식이면 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(strings = {
                "",
                "양송이수프1",
                "타파스,2",
                "맛있는거-3",
                "양송이수프-",
                "-3",
                "타파스-3,타파스-4"
        })
        void convert_order_잘못된_형식_예외(String input) {
            OrderDto orderDto = new OrderDto(input);
            assertThatThrownBy(() -> converterService.convertDtoToOrder(orderDto))
                    .isInstanceOf(OrderException.class)
                    .hasMessageContaining(ErrorMessageConfig.NOT_VALIDATE_ORDER_ERROR_MESSAGE.getErrorMessage());
        }
    }
}