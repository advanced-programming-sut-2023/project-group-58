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
    ;
    final boolean fertility;
    final boolean constructiblity;
    final boolean walkability;

    TileTexture(boolean fertility, boolean constructiblity, boolean walkability) {
        this.fertility = fertility;
        this.constructiblity = constructiblity;
        this.walkability = walkability;
    }
}
