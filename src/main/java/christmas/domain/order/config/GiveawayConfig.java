package christmas.domain.order.config;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public enum GiveawayConfig {
    GIVEAWAY1(Menu.CHAMPAGNE, 1);

    private final Menu menu;
    private final int number;

    GiveawayConfig(Menu menu, int number) {
        this.menu = menu;
        this.number = number;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getNumber() {
        return number;
    }

    public static final Map<Menu, Integer> getGiveaway() {
        EnumMap<Menu, Integer> giveaways = new EnumMap<>(Menu.class);
        for (GiveawayConfig giveaway : GiveawayConfig.values()) {
            giveaways.put(giveaway.menu, giveaway.number);
        }
        return Collections.unmodifiableMap(giveaways);
    }
}
