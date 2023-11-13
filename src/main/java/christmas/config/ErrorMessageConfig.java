package christmas.config;

public enum ErrorConfig {
    COMMON_ERROR_MESSAGE("[ERROR] "),
    NOT_VALIDATE_ORDER_ERROR_MESSAGE("유효하지 않은 주문입니다."),
    NOT_NULL_ORDER_ERROR_MESSAGE("아무것도 주문하지 않았습니다. "),
    NOT_ZERO_ORDER_ERROR_MESSAGE("주문은 1개 이상이어야 합니다. "),
    RANGE_TOTAL_ORDER_ERROR_MESSAGE("총 주문은 1개 이상 20개 이하여야 합니다. ");;

    private final String message;

    ErrorConfig(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ErrorConfig.COMMON_ERROR_MESSAGE.message + message;
    }

    public String getSubMessage() {
        return message;
    }
}
