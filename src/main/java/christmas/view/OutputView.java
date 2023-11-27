package christmas.view;

import christmas.domain.calendar.Calendar;
import christmas.domain.calendar.config.CalendarConfig;

public class OutputView {
    public void writeEvent(Calendar calendar) {
        System.out.println(String.format("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", CalendarConfig.Chrismas_2023.getMonth(),
                calendar.getDateOfVisit()));
    }

    public void
}
