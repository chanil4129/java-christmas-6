package christmas.dto;

import christmas.exception.DateOfVisitException;

public class DateDto {
    private final String date;

    public DateDto(String date) {
        this.date = date;
    }

    public int invValue() throws DateOfVisitException {
        try {
            return Integer.parseInt(date);
        } catch (NumberFormatException exception) {
            throw new DateOfVisitException();
        }
    }
}
