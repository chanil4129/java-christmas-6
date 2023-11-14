package christmas.controller;

import christmas.config.Menu;
import christmas.dto.DateOfVisitDto;
import christmas.dto.OrderDto;
import christmas.exception.DateOfVisitException;
import christmas.exception.OrderException;
import christmas.model.DateOfVisit;
import christmas.model.Order;
import christmas.service.CalendarCreateService;
import christmas.service.ConverterService;
import christmas.service.EventFindService;
import christmas.service.OrderCreateService;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;
import java.util.Map;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ConverterService converterService;
    private final CalendarCreateService calendarCreateService;
    private final EventFindService eventFindService;
    private final OrderCreateService orderCreateService;

    public ChristmasController(InputView inputView, OutputView outputView, ConverterService converterService,
                               CalendarCreateService calendarCreateService, EventFindService eventFindService,
                               OrderCreateService orderCreateService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.converterService = converterService;
        this.calendarCreateService = calendarCreateService;
        this.eventFindService = eventFindService;
        this.orderCreateService = orderCreateService;
    }

    private DateOfVisit findDateOfVisit() {
        while (true) {
            try {
                String userInput = inputView.requestDateOfVisit();
                int date = converterService.convertDtoToInt(new DateOfVisitDto(userInput));
                return new DateOfVisit(date);
            } catch (DateOfVisitException dateOfVisitException) {
            }
        }
    }

    private Order findOrder() {
        while (true) {
            try {
                String userInput = inputView.requestOrderMenuAndNumber();
                Map<Menu, Integer> orders = converterService.convertDtoToOrder(new OrderDto(userInput));
                return new Order(orders);
            } catch (OrderException orderException) {
            }
        }
    }
}
