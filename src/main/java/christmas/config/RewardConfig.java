package christmas.config;

public enum RewardConfig {
    CHRISTMAS_D_Day_DISCOUNT("크리스마스 디데이 할인: "),
    WEEKDAY_DISCOUNT("평일 할인: "),
    WEEKEND_DISCOUNT("주말 할인: "),
    SPECIAL_DISCOUNT("특별 할인: "),
    GIVEAWAY_EVENT("증정 이벤트: "),
    NONE_REWARD("없음");

    private final String message;

    RewardConfig(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
