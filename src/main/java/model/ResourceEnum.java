package model;

import model.units.ResourceType;

public enum ResourceEnum {
    NULL("",0,0, null),
    WHEAT("wheat", 0, 0, ResourceType.NORMAL),
    MEAT("meat", 10, 5, ResourceType.FOOD),
    APPLE("apple", 0, 0, ResourceType.FOOD),
    CHEESE("cheese", 0, 0, ResourceType.FOOD),
    WOOD("wood", 0, 0, ResourceType.NORMAL),
    FLOUR("flour", 0, 0, ResourceType.NORMAL),
    HOPS("hops", 0, 0, ResourceType.NORMAL),
    ALE("ale", 0, 0, ResourceType.NORMAL),
    STONE("stone", 0, 0, ResourceType.NORMAL),
    IRON("iron", 0, 0, ResourceType.NORMAL),
    //PITCH("pitch", 0, 0),
    BREAD("bread", 0 , 0, ResourceType.FOOD),
    BEER("beer", 0 , 0, ResourceType.NORMAL),
    HORSE("horse" , 0 , 0, ResourceType.NORMAL),
    ARMOUR("armour" , 0 , 0, ResourceType.WEAPON),
    OIL("oil",0 ,0 , ResourceType.NORMAL),
    SWORD("sword",0 ,0 , ResourceType.WEAPON),
    CROSSBOW("crossbow", 0 , 0, ResourceType.WEAPON),
    SPEAR("spear", 0 , 0, ResourceType.WEAPON),
    PIKE("pike", 0 , 0, ResourceType.WEAPON),
    LEATHER_ARMOR("leather armor", 0 , 0, ResourceType.WEAPON),
    BOW("bow", 0 , 0, ResourceType.WEAPON),
    METAL_ARMOR("metal armor", 0 , 0, ResourceType.WEAPON),
    MACE("mace", 0 , 0, ResourceType.WEAPON),
    HORSEANDBOW("",0 ,0 , ResourceType.WEAPON);
    private String name;
    private int buyCost;
    private int sellCost;
    private ResourceType resourceType;

    ResourceEnum(String name, int buyCost, int sellCost, ResourceType resourceType) {
        this.name = name;
        this.buyCost = buyCost;
        this.sellCost = sellCost;
        this.resourceType = resourceType;
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

    public ResourceType getResourceType() {
        return resourceType;
    }
}
