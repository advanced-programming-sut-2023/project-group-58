package model;

public enum TileTexture {

    SMALL_POND("small_pond", false, false, false),
    SEA("sea", false, false, false),
    FORD("ford", false, false, true),
    IRON("iron", true, false, true),
    EARTH("earth", true, true, true),
    SCRUB("scrub", true, false, true),
    THICK_SCRUB("thick_scrub", true, false, true),
    OIL("oil", false, false, true),
    BIG_POND("big_pond", false, false, false),
    RIVER("river", false, false, false),
    SAND("sand", false, false, true),
    LAWN("lawn", true, true, true),
    ROCK("rock", false, false, false),
    //todo use the last 5 in the map
    ;
    final boolean fertility;
    final boolean constructiblity;
    final boolean walkability;

    TileTexture(String name, boolean fertility, boolean constructiblity, boolean walkability) {
        this.name = name;
        this.fertility = fertility;
        this.constructiblity = constructiblity;
        this.walkability = walkability;
    }

    String name;

    public boolean isFertility() {
        return fertility;
    }

    public boolean isConstructiblity() {
        return constructiblity;
    }

    public boolean isWalkability() {
        return walkability;
    }

    public String getName() {
        return name;
    }
}
