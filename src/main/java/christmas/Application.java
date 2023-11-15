package christmas;

import christmas.controller.ChristmasController;
import christmas.service.CalendarCreateService;
import christmas.service.DateOfVisitCreateService;
import christmas.service.EventFindService;
import christmas.service.InputConverterService;
import christmas.service.OrderCreateService;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class Application {
    public static void main(String[] args) {
        ChristmasController christmasController = initController();
        christmasController.run();
    }

    private static ChristmasController initController() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        InputConverterService inputConverterService = new InputConverterService();
        CalendarCreateService calendarCreateService = new CalendarCreateService();
        DateOfVisitCreateService dateOfVisitCreateService = new DateOfVisitCreateService();
        OrderCreateService orderCreateService = new OrderCreateService();
        EventFindService eventFindService = new EventFindService();
        return new ChristmasController(inputView, outputView, inputConverterService, calendarCreateService,
                dateOfVisitCreateService, orderCreateService, eventFindService);
    }
}
