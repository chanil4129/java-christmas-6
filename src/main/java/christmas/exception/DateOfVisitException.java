package christmas.exception;

public class DateOfVisitException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE =
            ErrorMessage.COMMON_ERROR_MESSAGE.getMessage() + ErrorMessage.DATE_OF_VISIT_ERROR_MESSAGE.getMessage();

    public DateOfVisitException() {
        super(ERROR_MESSAGE);
    }
}
