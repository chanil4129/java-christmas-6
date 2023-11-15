package christmas.view.output;

import christmas.config.DiscountConfig;
import christmas.config.Menu;
import christmas.enums.MonthOfYear;
import christmas.model.DateOfVisit;
import christmas.model.Order;
import christmas.model.Reward;
import java.util.Map;

public class OutputView {
    private final int NUMBER_OF_MONTH = MonthOfYear.DECEMBER.getNumberOfMonth();
    private final String START_MESSAGE = "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.";
    private final String BENEFIT_FOREWORD_MESSAGE = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private final String ORDER_MENU_MESSAGE = "<주문 메뉴>";
    private final String TOTAL_ORDER_AMOUNT_MESSAGE = "<할인 전 총주문 금액>";
    private final String GIVEAWAY_MENU_MESSAGE = "<증정 메뉴>";
    private final String BENEFIT_DETAILS_MESSAGE = "<혜택 내역>";
    private final String TOTAL_BENEFIT_AMOUNT_MESSAGE = "<총혜택 금액>";
    private final String EXPECTED_PAYMENT_AFTER_DISCOUNT_MESSAGE = "<할인 후 예상 결제 금액>";
    private final String EVENT_BADGE_MESSAGE = "<%d월 이벤트 배지>";

    public void printStartApplication() {
        System.out.println(String.format(START_MESSAGE, NUMBER_OF_MONTH));
    }

    public void printBenefit(Order order, Reward reward, DateOfVisit dateOfVisit) {
        System.out.println(String.format(BENEFIT_FOREWORD_MESSAGE, NUMBER_OF_MONTH, dateOfVisit.getDay()));
        printOrderMenu(order);
        printTotalOrderAmount(order);
        printGiveawayMenu(reward);
        printBenefitDetatils(reward);
        printTotalBenefitAmount(reward);
        printExpectedPaymentAfterDiscount(reward);
        printEventBadge(reward);
    }

    private void printOrderMenu(Order order) {
        System.out.println(ORDER_MENU_MESSAGE);
        order.getOrders().forEach((menu, menuNumber) -> {
            System.out.println(String.format("%s %d개", menu.getName(), menuNumber));
        });
    }

    private void printTotalOrderAmount(Order order) {
        System.out.println(TOTAL_ORDER_AMOUNT_MESSAGE);
        int totalOrderAmount = order.getFirstCost();
        System.out.println(String.format("%d원", totalOrderAmount));
    }

    private void printGiveawayMenu(Reward reward) {
        System.out.println(GIVEAWAY_MENU_MESSAGE);
        Integer giveawayNumber = reward.getReward().get(DiscountConfig.GIVEAWAY);
        if (giveawayNumber == null) {
            System.out.println("없음");
            return;
        }
        System.out.println(
                String.format("%s %d개", Menu.getGiveaway(), giveawayNumber));
    }

    private void printBenefitDetatils(Reward reward) {
        System.out.println(BENEFIT_DETAILS_MESSAGE);
        Map<DiscountConfig, Integer> rewardContent = reward.getReward();
        if (rewardContent == null || rewardContent.isEmpty()) {
            System.out.println("없음");
            return;
        }
        rewardContent.forEach((discountContent, price) -> {
            System.out.println(String.format("%s: -%d원", discountContent.name(), price));
        });
    }

    private void printTotalBenefitAmount(Reward reward) {
        System.out.println(TOTAL_BENEFIT_AMOUNT_MESSAGE);
        int totalReward = reward.getTotalReward();
        if (totalReward == 0) {
            System.out.println("0원");
            return;
        }
        System.out.println(String.format("-%d원", totalReward));
    }

    private void printExpectedPaymentAfterDiscount(Reward reward) {
        System.out.println(EXPECTED_PAYMENT_AFTER_DISCOUNT_MESSAGE);
        System.out.println(String.format("%d원", reward.getFinalCost()));
    }

    private void printEventBadge(Reward reward) {
        System.out.println(String.format(EVENT_BADGE_MESSAGE, NUMBER_OF_MONTH));
        System.out.println(String.format(reward.getBadge().getLevel().getName()));
    }
}
