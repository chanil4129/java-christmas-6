package christmas.exception;

public enum ErrorMessage {
    COMMON_ERROR_MESSAGE("[ERROR] "),
    DATE_OF_VISIT_ERROR_MESSAGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    ORDER_ERROR_MESSAGE("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    DAY_OF_WEEK_ERROR_MESSAGE("해당 날짜의 요일을 찾을 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
