package model;

public enum ResourceEnum {
    NULL("",0,0),
    WHEAT("wheat", 0, 0),
    MEAT("meat", 0, 0),
    APPLE("apple", 0, 0),
    CHEESE("cheese", 0, 0),
    WOOD("wood", 0, 0),
    FLOUR("flour", 0, 0),
    HOPS("hops", 0, 0),
    ALE("ale", 0, 0),
    STONE("stone", 0, 0),
    IRON("iron", 0, 0),
    //PITCH("pitch", 0, 0),
    BREAD("bread", 0 , 0),
    BEER("beer", 0 , 0),
    HORSE("horse" , 0 , 0),
    ARMOUR("armour" , 0 , 0),
    OIL("oil",0 ,0 ),
    SWORD("sword",0 ,0 ),
    CROSSBOW("crossbow", 0 , 0),
    SPEAR("spear", 0 , 0),
    PIKE("pike", 0 , 0),
    LEATHER_ARMOR("leather armor", 0 , 0),
    BOW("bow", 0 , 0),
    METAL_ARMOR("metal armor", 0 , 0),
    MACE("mace", 0 , 0),
    HORSEANDBOW("",0 ,0 );
    private String name;
    private int buyCost;
    private int sellCost;

    ResourceEnum(String name, int buyCost, int sellCost) {
        this.name = name;
        this.buyCost = buyCost;
        this.sellCost = sellCost;
    }

    public String getName() {
        return name;
    }

    public int getBuyCost() {
        return buyCost;
    }

    public int getSellCost() {
        return sellCost;
    }
}
