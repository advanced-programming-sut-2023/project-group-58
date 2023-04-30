package model;

public enum WeaponEnum {
    CROSSBOW("crossbow"),
    SPEAR("spear"),
    PIKE("pike"),
    SWORD("sword"),
    LEATHER_ARMOR("leather armor"),
    BOW("bow"),
    METAL_ARMOR("metal armor"),
    MACE("mace"),
    OIL("oi");
    private String name;

    WeaponEnum(String name) {
        this.name = name;
    }
}
