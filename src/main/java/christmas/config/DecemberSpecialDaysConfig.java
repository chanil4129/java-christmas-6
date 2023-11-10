package christmas.config;

public enum DecemberSpecialDaysConfig {
    CHRISTMAS(25),
    SPCIEAL_DAY_1(3),
    SPCIEAL_DAY_2(10),
    SPCIEAL_DAY_3(17),
    SPCIEAL_DAY_4(24),
    SPCIEAL_DAY_5(31),
    ;

    private final int day;

    DecemberSpecialDaysConfig(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}
