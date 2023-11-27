package christmas.domain.event;

import christmas.domain.calendar.Calendar;
import christmas.domain.event.config.DDayDiscountEventConfig;
import christmas.domain.event.config.DiscountEventConfig;
import christmas.domain.order.Order;
import java.util.EnumMap;

public class DiscountEvent {
    private final Calendar calendar;
    private final Order order;
    private EnumMap<DiscountEventConfig, Integer> discountInfo;

    public DiscountEvent(Calendar calendar, Order order) {
        this.calendar = calendar;
        this.order = order;
    }

    public void calculateDiscount() {
        this.discountInfo = new EnumMap<DiscountEventConfig, Integer>(DiscountEventConfig.class);

        calculateDDayCost();
        calculateWeekdayCost();
        calculateWeekendCost();
        calculateSpecialCost();
    }

    public EnumMap<DiscountEventConfig, Integer> getDiscountInfo() {
        return this.discountInfo;
    }

    public int calculateTotalRewardCost() {
        return this.discountInfo.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void calculateDDayCost() {
        int monthDiscountPrice = DDayDiscountEventConfig.MONTH_DISCOUNT.getPrice();
        int eachDayDiscountPrice =
                this.calendar.getDateOfVisit() * DDayDiscountEventConfig.EACH_DAY_DISCOUNT.getPrice();
        int result = monthDiscountPrice + eachDayDiscountPrice;

        this.discountInfo.put(DiscountEventConfig.D_DAY, result);
    }

    private void calculateWeekdayCost() {
        if (this.calendar.isWeekday()) {
            int result = this.order.getDesertMenuNumber() * DiscountEventConfig.WEEKDAY.getPrice();

            this.discountInfo.put(DiscountEventConfig.WEEKDAY, result);
        }
    }

    private void calculateWeekendCost() {
        if (!this.calendar.isWeekday()) {
            int result = this.order.getMainMenuNumber() * DiscountEventConfig.WEEKEND.getPrice();

            this.discountInfo.put(DiscountEventConfig.WEEKEND, result);
        }
    }

    private void calculateSpecialCost() {
        if (this.calendar.isSpecialDay()) {
            this.discountInfo.put(DiscountEventConfig.SPECIAL, DiscountEventConfig.SPECIAL.getPrice());
        }
    }
}
