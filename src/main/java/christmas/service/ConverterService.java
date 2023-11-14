package christmas.service;

import christmas.config.ErrorMessageConfig;
import christmas.config.Menu;
import christmas.dto.DateOfVisitDto;
import christmas.dto.OrderDto;
import christmas.exception.DateOfVisitException;
import christmas.exception.OrderException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConverterService {
    private final int MENU_AND_NUMBER = 2;
    private final int MENU_INDEX = 0;
    private final int MENU_NUMBER_INDEX = 1;
    private final int MENU_NUMBER_DEFAULT_IN_MAP = 0;
    private final String COMMA = ",";
    private final String HYPHEN = "-";
    private final String ORDER_FORMAT_ERROR_MESSAGE = ErrorMessageConfig.ORDER_FORMAT_ERROR_MESSAGE.getMessage();
    private final String ORDER_NUMBER_INTEGER_ERROR_MESSAGE = ErrorMessageConfig.ORDER_NUMBER_INTEGER_ERROR_MESSAGE.getMessage();
    private final String ORDER_IN_MENU_ERROR_MESSAGE = ErrorMessageConfig.ORDER_IN_MENU_ERROR_MESSAGE.getMessage();
    private final String ORDER_DUPLICATION_IN_MENU = ErrorMessageConfig.ORDER_DUPLICATION_IN_MENU.getMessage();

    public int convertDtoToInt(DateOfVisitDto dateOfVisitDto) throws DateOfVisitException {
        try {
            String date = dateOfVisitDto.getDate();
            return Integer.parseInt(date);
        } catch (NumberFormatException numberFormatException) {
            throw new DateOfVisitException();
        }
    }

    public Map<Menu, Integer> convertDtoToOrder(OrderDto orderDto) throws OrderException {
        validateOrder(orderDto);
        return parseOrder(orderDto);
    }

    private Map<Menu, Integer> parseOrder(OrderDto orderDto) throws OrderException {
        Map<Menu, Integer> orderMap = new HashMap<>();
        String[] orders = orderDto.getOrder().split(COMMA);
        for (String order : orders) {
            String[] MenuAndNumber = order.split(HYPHEN);

            String menuName = MenuAndNumber[MENU_INDEX];
            int number = Integer.parseInt(MenuAndNumber[MENU_NUMBER_INDEX]);

            Menu menu = getMenuByName(menuName);
            orderMap.put(menu, number);
        }
        return orderMap;
    }

    private Menu getMenuByName(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName)) {
                return menu;
            }
        }
        throw new OrderException(ORDER_IN_MENU_ERROR_MESSAGE);
    }

    private void validateOrder(OrderDto orderDto) throws OrderException {
        String[] orders = orderDto.getOrder().split(COMMA);
        List<String[]> bundleMenuAndNumber = new ArrayList<>();
        for (String order : orders) {
            bundleMenuAndNumber.add(order.split(HYPHEN));
        }
        validateOrderFormat(bundleMenuAndNumber);
        validateOrderNumberInteger(bundleMenuAndNumber);
        validateOrderDuplicationMenu(bundleMenuAndNumber);
    }

    private void validateOrderFormat(List<String[]> bundleMenuAndNumber) throws OrderException {
        for (String[] menuAndNumber : bundleMenuAndNumber) {
            if (menuAndNumber.length != MENU_AND_NUMBER) {
                throw new OrderException(ORDER_FORMAT_ERROR_MESSAGE);
            }
        }
    }

    private void validateOrderNumberInteger(List<String[]> bundleMenuAndNumber) throws OrderException {
        for (String[] menuAndNumber : bundleMenuAndNumber) {
            try {
                Integer.parseInt(menuAndNumber[MENU_NUMBER_INDEX]);
            } catch (NumberFormatException e) {
                throw new OrderException(ORDER_NUMBER_INTEGER_ERROR_MESSAGE);
            }
        }
    }

    private void validateOrderDuplicationMenu(List<String[]> bundleMenuAndNumber) throws OrderException {
        Set<String> seenMenus = new HashSet<>();
        for (String[] menuAndNumber : bundleMenuAndNumber) {
            String menuName = menuAndNumber[MENU_INDEX];
            if (seenMenus.contains(menuName)) {
                throw new OrderException(ORDER_DUPLICATION_IN_MENU);
            }
            seenMenus.add(menuName);
        }
    }
}
