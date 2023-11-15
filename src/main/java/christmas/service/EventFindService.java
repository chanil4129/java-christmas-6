package christmas.service;

import christmas.config.DiscountConfig;
import christmas.config.Menu;
import christmas.config.MenuConfig;
import christmas.enums.MenuType;
import christmas.model.Calendar;
import christmas.model.vo.DateOfVisit;
import christmas.model.Order;
import christmas.model.Reward;

public class EventFindService {
    private final int FIRST_DAY = 1;
    private final int DEFAULT_DISCOUNT = DiscountConfig.DEFAULT.getDiscountPrice();
    private final int ACCUMULATE_EVERY_DAY_DISCOUNT = DiscountConfig.ACCUMULATE_EVERY_DAY.getDiscountPrice();
    private final int WEEKDAY_DISCOUNT = DiscountConfig.WEEKDAY.getDiscountPrice();
    private final int WEEKEND_DISCOUNT = DiscountConfig.WEEKEND.getDiscountPrice();
    private final int SPECIAL_DISCOUNT = DiscountConfig.SPECIAL.getDiscountPrice();
    private final int MINIMUM_ORDER_AMOUNT = MenuConfig.MINIMUM_ORDER_AMOUNT.getValue();
    private final int NO_DISCOUNT = 0;

    private Calendar calendar;
    private Order order;
    private DateOfVisit dateOfVisit;

    public void applyEvent(Calendar calendar, Order order, DateOfVisit dateOfVisit) {
        this.calendar = calendar;
        this.order = order;
        this.dateOfVisit = dateOfVisit;
    }

    public Reward calculateReward() {
        Reward reward = new Reward();
        if (order.getFirstCost() >= MINIMUM_ORDER_AMOUNT) {
            applyDiscountEvent(reward);
        }
        reward.calculateFinalCost(order.getFirstCost());
        return reward;
    }

    private void applyDiscountEvent(Reward reward) {
        reward.put(DiscountConfig.DEFAULT, getDDayDiscount());
        reward.put(DiscountConfig.WEEKDAY, getWeekDayDiscount());
        reward.put(DiscountConfig.WEEKEND, getWeekendDiscount());
        reward.put(DiscountConfig.SPECIAL, getSpecialDayDiscount());
        reward.put(DiscountConfig.GIVEAWAY, getGiveawayReward());
    }

    private int getDDayDiscount() {
        int accumulateDiscount = (dateOfVisit.getDay() - FIRST_DAY) * ACCUMULATE_EVERY_DAY_DISCOUNT;
        int defaultDiscount = DEFAULT_DISCOUNT;
        return accumulateDiscount + defaultDiscount;
    }

    private int getWeekDayDiscount() {
        if (!calendar.isWeekDay(dateOfVisit.getDay())) {
            return NO_DISCOUNT;
        }

        // 디저트 메뉴 1개당 WEEKDAY_DISCOUNT 만큼 할인
        return order.getOrders().entrySet().stream()
                .filter(entry -> entry.getKey().getType() == MenuType.DESSERT)
                .mapToInt(entry -> entry.getValue() * WEEKDAY_DISCOUNT)
                .sum();
    }

    private int getWeekendDiscount() {
        if (calendar.isWeekDay(dateOfVisit.getDay())) {
            return NO_DISCOUNT;
        }

        // 메인 메뉴 1개당 WEEKEND_DISCOUNT 만큼 할인
        return order.getOrders().entrySet().stream()
                .filter(entry -> entry.getKey().getType() == MenuType.MAIN)
                .mapToInt(entry -> entry.getValue() * WEEKEND_DISCOUNT)
                .sum();
    }

    private int getSpecialDayDiscount() {
        if (!calendar.isSpecialDay(dateOfVisit.getDay())) {
            return NO_DISCOUNT;
        }

        return SPECIAL_DISCOUNT;
    }

    private int getGiveawayReward() {
        if (!order.receivableGiveaway()) {
            return NO_DISCOUNT;
        }

        return Menu.getGiveaway().getPrice();
    }
}
