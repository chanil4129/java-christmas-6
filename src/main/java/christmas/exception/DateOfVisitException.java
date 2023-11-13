package christmas.exception;

import christmas.config.ErrorMessageConfig;

public class DateOfVisitException extends IllegalArgumentException {
    private static final String NOT_VALID_DATE_OF_VISIT_ERROR_MESSAGE = ErrorMessageConfig.NOT_VALID_DATE_OF_VISIT_ERROR_MESSAGE.getMessage();

    public DateOfVisitException() {
        super(NOT_VALID_DATE_OF_VISIT_ERROR_MESSAGE);
    }
}
