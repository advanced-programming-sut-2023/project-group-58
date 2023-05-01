package model;

public enum FoodEnum {
    CHEESE("cheese"),
    BREAD("bread"),
    APPLE("apple"),
    MEAT("meat");
    private String name;

    FoodEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
