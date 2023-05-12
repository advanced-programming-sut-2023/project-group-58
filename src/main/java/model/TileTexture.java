package model;

public enum TileTexture {

    SMALL_POND(false, false, false),
    SEA(false, false, false),
    FORD(false, false, true),
    IRON(true, false, true),
    EARTH(true, true, true),
    SCRUB(true, false, true),
    THICK_SCRUB(true, false, true),
    OIL(false, false, true),

    BIG_POND(false,false,false),
    RIVER(false,false,false),
    SAND(false,false,true),
    LAWN(true, true, true),
    ROCK(false,false ,false ),
    //todo use the last 5 in the map
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
