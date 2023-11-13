package christmas.config;

public enum MenuConfig {
    MENU_MINIMUM_NUMBER(1),
    MENU_MAXIMUM_NUMBER(20);

    private final int value;

    MenuConfig(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
