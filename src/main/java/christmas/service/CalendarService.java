package christmas.service;

import christmas.domain.calendar.Calendar;
import christmas.dto.DateDto;

public class CalendarService {
    private final DateDto dateDto;

    public CalendarService(DateDto dateDto) {
        this.dateDto = dateDto;
    }

    public void create() {
        Calendar calendar = new Calendar(dateDto.invValue());
    }
}
