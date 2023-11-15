package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;
import christmas.enums.MonthOfYear;

public class InputView {
    private final int MONTH = MonthOfYear.DECEMBER.getNumberOfMonth();
    private final String DATE_OF_VISIT_REQUEST_MESSAGE = "%d월 중 식당 예상 방문 날짜는 언제인가요?";
    private final String ORDER_MENU_AND_NUMBER_REQUEST_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public String requestDateOfVisit() {
        System.out.println(String.format(DATE_OF_VISIT_REQUEST_MESSAGE, MONTH));
        return userInput();
    }

    public String requestOrderMenuAndNumber() {
        System.out.println(ORDER_MENU_AND_NUMBER_REQUEST_MESSAGE);
        return userInput();
    }

    private static String userInput() {
        return Console.readLine().trim();
    }
}
