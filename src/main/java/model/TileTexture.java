package model;

public enum TileTexture {

    SMALL_POND(false, false, true),
    SEA(false, false, false),
    FORD(false, false, false),
    IRON(true, true, false),
    EARTH(true, true, true),
    SCRUB(true, false, true),
    THICK_SCRUB(true, false, false),
    OIL(false, false, true),

    BIG_POND(false,false,false),
    RIVER(false,false,false),
    SAND(false,false,true),
    //todo: last three should be added to maps
    ;
    final boolean fertility;
    final boolean constructiblity;
    final boolean walkability;

    TileTexture(boolean fertility, boolean constructiblity, boolean walkability) {
        this.fertility = fertility;
        this.constructiblity = constructiblity;
        this.walkability = walkability;
    }

    public boolean isFertility() {
        return fertility;
    }

    public boolean isConstructiblity() {
        return constructiblity;
    }

    public boolean isWalkability() {
        return walkability;
    }
}
