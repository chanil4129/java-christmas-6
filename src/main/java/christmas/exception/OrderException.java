package christmas.exception;

import christmas.config.ErrorConfig;

public class OrderException extends IllegalArgumentException {
    private static final String NOT_VALIDATE_ORDER_ERROR_MESSAGE = ErrorConfig.NOT_VALIDATE_ORDER_ERROR_MESSAGE.getMessage();

    public OrderException() {
        super(NOT_VALIDATE_ORDER_ERROR_MESSAGE);
    }
}
