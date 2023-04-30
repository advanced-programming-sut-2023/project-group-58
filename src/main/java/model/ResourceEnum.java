package model;

public enum ResourceEnum {
    WHEAT("wheat", 0, 0),
    WOOD("wood", 0, 0),
    FLOUR("flour", 0, 0),
    HOPS("hops", 0, 0),
    ALE("ale", 0, 0),
    STONE("stone", 0, 0),
    IRON("iron", 0, 0),
    PITCH("pitch", 0, 0);
    private String name;
    private int buyCost;
    private int sellCost;

    ResourceEnum(String name, int buyCost, int sellCost) {
        this.name = name;
        this.buyCost = buyCost;
        this.sellCost = sellCost;
    }
}
