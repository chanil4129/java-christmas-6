package christmas.exception;

public class OrderException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE =
            ErrorMessage.COMMON_ERROR_MESSAGE.getMessage() + ErrorMessage.ORDER_ERROR_MESSAGE.getMessage();

    public OrderException() {
        super(ERROR_MESSAGE);
    }
}
