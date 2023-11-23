package christmas.exception;

public class DayOfWeekException extends IllegalStateException {
    private static final String DAY_OF_WEEK_EXCEPTION_MESSAGE = "";

    public DayOfWeekException() {
        super(DAY_OF_WEEK_EXCEPTION_MESSAGE);
    }
}
