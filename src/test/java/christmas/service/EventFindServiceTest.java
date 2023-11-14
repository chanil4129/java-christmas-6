package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.config.BadgeConfig;
import christmas.config.DecemberSpecialDays2023Config;
import christmas.config.Menu;
import christmas.enums.DayOfWeek;
import christmas.enums.MonthOfYear;
import christmas.model.Badge;
import christmas.model.Calendar;
import christmas.model.DateOfVisit;
import christmas.model.Order;
import christmas.model.Reward;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventFindServiceTest {
    private EventFindService eventFindService;

    @BeforeEach
    void setUp() {
        CalendarCreateService calendarCreateService = new CalendarCreateService();
        calendarCreateService.createCalendar();
        Calendar calendar = calendarCreateService.getCalendar();
        Map<Menu, Integer> menuOrder = new HashMap<>();
        menuOrder.put(Menu.T_BONE_STEAK, 1);
        menuOrder.put(Menu.BARBECUE_RIBS, 1);
        menuOrder.put(Menu.CHOCOLATE_CAKE, 2);
        menuOrder.put(Menu.ZERO_COLA, 1);
        Order order = new Order(menuOrder);
        DateOfVisit dateOfVisit = new DateOfVisit(3);
        eventFindService = new EventFindService(calendar, order, dateOfVisit);
    }

    @DisplayName("보상이 얼마인지 확인한다.")
    @Test
    public void getReward() {
        Reward reward = eventFindService.getReward();

        assertThat(reward.getTotalReward()).isEqualTo(31246);
    }

    @DisplayName("배지 등급을 확인한다.")
    @Test
    public void getBadge() {
        Badge badge = eventFindService.getBadge();

        assertThat(badge.getLevel()).isEqualTo(BadgeConfig.SANTA);
    }
}