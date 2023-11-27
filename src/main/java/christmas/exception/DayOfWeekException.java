package christmas.exception;

public class DayOfWeekException extends IllegalStateException {
    private static final String ERROR_MESSAGE =
            ErrorMessage.COMMON_ERROR_MESSAGE.getMessage() + ErrorMessage.DAY_OF_WEEK_ERROR_MESSAGE;

    public DayOfWeekException() {
        super(ERROR_MESSAGE);
    }
}
