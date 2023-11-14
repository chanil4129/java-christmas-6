package christmas.exception;

import christmas.config.ErrorMessageConfig;

public class OrderException extends IllegalArgumentException {
    private static final String NOT_VALIDATE_ORDER_ERROR_MESSAGE = ErrorMessageConfig.NOT_VALIDATE_ORDER_ERROR_MESSAGE.getErrorMessage();

    public OrderException(String message) {
        super(NOT_VALIDATE_ORDER_ERROR_MESSAGE + message);
    }
}
