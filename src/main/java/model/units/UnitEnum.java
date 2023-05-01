package model.units;

public enum UnitEnum {
    PIKE_MAN("pike man"),
    TUNNELER("tunneler"),
    ARCHER_BOW("archer bow"),
    KING("king"),
    ARCHER("archer"),
    SPEAR_MAN("spear man"),
    SWORDS_MAN("swords man"),
    CROSSBOW_MAN("crossbow_man"),
    MACE_MAN("mace man"),
    SOLDIER_ENGINEER("soldier engineer"),
    KNIGHT("knight"),
    BLACK_MONK("black monk"),
    HORSE_ARCHER("horse archer"),
    ARABIAN_SWORDSMAN("arabian swordsman"),
    LADDER_MAN("ladder man"),
    ASSASSIN("assassin"),
    FIRE_THROWER("fire thrower"),
    SLAVE("slave"),
    ENGINEER("engineer"),
    SLINGER("slinger"),
    ;
    private String name;

    UnitEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
