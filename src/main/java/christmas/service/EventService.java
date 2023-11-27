package christmas.service;

import christmas.domain.PrintRewardFormat;
import christmas.domain.calendar.Calendar;
import christmas.domain.event.DiscountEvent;
import christmas.domain.event.config.BadgeConfig;
import christmas.domain.event.config.EventConfig;
import christmas.domain.order.Order;
import christmas.domain.order.config.GiveawayConfig;
import christmas.domain.order.config.Menu;
import java.util.Map;

public class EventService {
    private final Calendar calendar;
    private final Order order;

    public EventService(Calendar calendar, Order order) {
        this.calendar = calendar;
        this.order = order;
    }

    public PrintRewardFormat calculateEvent() {
        if (isValidateEvent()) {
            DiscountEvent discountEvent = new DiscountEvent(this.calendar, this.order);
            Map<Menu, Integer> giveaways = getGiveaway();
            String level = BadgeConfig.calculateLevel(discountEvent.calculateTotalRewardCost());
            return new PrintRewardFormat(discountEvent, giveaways, level);
        }
        return new PrintRewardFormat();
    }

    public boolean isValidateEvent() {
        return this.order.calculateTotalFirstCost() >= EventConfig.EVENT_APPLY.getConditionValue();
    }

    private Map<Menu, Integer> getGiveaway() {
        if (this.order.calculateTotalFirstCost() >= EventConfig.GIVEAWAY_APPLY.getConditionValue()) {
            return GiveawayConfig.getGiveaway();
        }
        return null;
    }
}
