package christmas.config;

public enum ErrorConfig {
    COMMON_ERROR_MESSAGE("[ERROR] "),
    NOT_VALIDATE_ORDER_ERROR_MESSAGE("유효하지 않은 주문입니다.");

    private final String message;

    ErrorConfig(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ErrorConfig.COMMON_ERROR_MESSAGE + message;
    }
}
