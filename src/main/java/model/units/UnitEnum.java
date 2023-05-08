package model.units;

import model.ResourceEnum;

public enum UnitEnum {
    PIKE_MAN("pike man", ResourceEnum.PIKE,0,0,0,0,0,false),
    TUNNELER("tunneler",ResourceEnum.NULL,0,0,0,0,0,false),
    ARCHER_BOW("archer bow",ResourceEnum.BOW,0,0,0,0,0,true),
    KING("king",ResourceEnum.NULL,0,0,0,0,0,false),
    ARCHER("archer",ResourceEnum.BOW,0,0,0,0,0,false),
    SPEAR_MAN("spear man",ResourceEnum.SPEAR,0,0,0,0,0,false),
    SWORDS_MAN("swords man",ResourceEnum.SWORD,0,0,0,0,0,false),
    CROSSBOW_MAN("crossbow_man",ResourceEnum.CROSSBOW,0,0,0,0,0,false),
    MACE_MAN("mace man",ResourceEnum.MACE,0,0,0,0,0,false),
    SOLDIER_ENGINEER("soldier engineer",ResourceEnum.NULL,0,0,0,0,0,false),
    KNIGHT("knight",ResourceEnum.HORSE,0,0,0,0,0,false),
    BLACK_MONK("black monk",ResourceEnum.WOOD,0,0,0,0,0,false),
    HORSE_ARCHER("horse archer",ResourceEnum.HORSEANDBOW,0,0,0,0,0,true),
    ARABIAN_SWORDSMAN("arabian swordsman",ResourceEnum.SWORD,0,0,0,0,0,true),
    LADDER_MAN("ladder man",ResourceEnum.WOOD,0,0,0,0,0,false),
    ASSASSIN("assassin",ResourceEnum.NULL,0,0,0,0,0,true),
    FIRE_THROWER("fire thrower",ResourceEnum.NULL,0,0,0,0,0,true),
    SLAVE("slave",ResourceEnum.NULL,0,0,0,0,0,true),
    ENGINEER("engineer",ResourceEnum.NULL,0,0,0,0,0,false),
    SLINGER("slinger",ResourceEnum.STONE,0,0,0,0,0,true),
    DOGS("dog",ResourceEnum.NULL,0,0,0,0,0,false)
    ;
    private String name;
    private int cost;
    private int damage;
    private int primaryHp;
    private int range;
    private int speed;
    private boolean isArab;
    private ResourceEnum weaponType;

    UnitEnum(String name, ResourceEnum weaponType, int cost, int damage, int primaryHp, int range, int speed, boolean isArab) {
        this.name = name;
        this.cost = cost;
        this.damage = damage;
        this.primaryHp = primaryHp;
        this.range = range;
        this.speed = speed;
        this.isArab = isArab;
        this.weaponType = weaponType;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getDamage() {
        return damage;
    }

    public int getPrimaryHp() {
        return primaryHp;
    }

    public int getRange() {
        return range;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isArab() {
        return isArab;
    }

    public ResourceEnum getWeaponType() {
        return weaponType;
    }
}
