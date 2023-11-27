package christmas.domain;

import christmas.domain.event.DiscountEvent;
import christmas.domain.order.config.Menu;
import java.util.Map;

public class PrintRewardFormat {
    private final DiscountEvent discountEvent;
    private final Map<Menu, Integer> giveaways;
    private final String badgeLevel;

    public PrintRewardFormat() {
        this.discountEvent = null;
        this.giveaways = null;
        this.badgeLevel = null;
    }

    public PrintRewardFormat(DiscountEvent discountEvent, Map<Menu, Integer> giveaways, String badgeLevel) {
        this.discountEvent = discountEvent;
        this.giveaways = giveaways;
        this.badgeLevel = badgeLevel;
    }

    public String getGiveaway() {
        StringBuilder result = new StringBuilder();
        giveaways.forEach((menu, number) -> {
            result.append(menu.getName());
            result.append(String.format(" %d개", number));
        });
        return result.toString();
    }

    public String getDiscountEvent() {
        StringBuilder result = new StringBuilder();
        this.discountEvent.getDiscountInfo().entrySet()
                .forEach((discount, number) -> {
                    result.append(discount.getKey().getName().);
                    result.append(String.format(": -%d원", number));
                });
        return result.toString();
    }

    public String getBadge() {
        return badgeLevel;
    }
}
