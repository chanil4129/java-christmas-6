package christmas.controller;

import christmas.config.Menu;
import christmas.dto.DateOfVisitDto;
import christmas.dto.OrderDto;
import christmas.exception.DateOfVisitException;
import christmas.exception.OrderException;
import christmas.model.vo.DateOfVisit;
import christmas.model.Order;
import christmas.model.Reward;
import christmas.service.CalendarCreateService;
import christmas.service.DateOfVisitCreateService;
import christmas.service.InputConverterService;
import christmas.service.EventFindService;
import christmas.service.OrderCreateService;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;
import java.util.Map;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;
    private final InputConverterService inputConverterService;
    private final CalendarCreateService calendarCreateService;
    private final DateOfVisitCreateService dateOfVisitCreateService;
    private final OrderCreateService orderCreateService;
    private final EventFindService eventFindService;

    public ChristmasController(InputView inputView, OutputView outputView, InputConverterService inputConverterService,
                               CalendarCreateService calendarCreateService,
                               DateOfVisitCreateService dateOfVisitCreateService, OrderCreateService orderCreateService,
                               EventFindService eventFindService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputConverterService = inputConverterService;
        this.calendarCreateService = calendarCreateService;
        this.dateOfVisitCreateService = dateOfVisitCreateService;
        this.orderCreateService = orderCreateService;
        this.eventFindService = eventFindService;
    }

    public void run() {
        outputView.printStartApplication();

        DateOfVisit dateOfVisit = findDateOfVisit();
        Order order = findOrder();

        calendarCreateService.createCalendar();
        eventFindService.applyEvent(calendarCreateService.getCalendar(), order, dateOfVisit);
        Reward reward = eventFindService.calculateReward();

        outputView.printBenefit(order, reward, dateOfVisit);
    }

    private DateOfVisit findDateOfVisit() {
        while (true) {
            try {
                String userInput = inputView.requestDateOfVisit();
                int date = inputConverterService.convertDtoToInt(new DateOfVisitDto(userInput));
                dateOfVisitCreateService.initDate(date);
                return dateOfVisitCreateService.createDateOfVisit();
            } catch (DateOfVisitException dateOfVisitException) {
                System.out.println(dateOfVisitException.getMessage());
            }
        }
    }

    private Order findOrder() {
        while (true) {
            try {
                String userInput = inputView.requestOrderMenuAndNumber();
                Map<Menu, Integer> orders = inputConverterService.convertDtoToOrder(new OrderDto(userInput));
                return orderCreateService.createOrder(orders);
            } catch (OrderException orderException) {
                System.out.println(orderException.getMessage());
            }
        }
    }
}
