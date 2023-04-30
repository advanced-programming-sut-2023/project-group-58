package model.buildings;

public enum BuildingEnum {
    CATHEDRAL(0,0,100,0),
    //PITCH_DITCH(),
    CAGED_WAR_DOGS(0,10,100,0),
    HOPS_FARM(0,15,0,1),
    ENGINEERS_GUILD(0,10,100,0),
    INN(0,20,100,1),
    MILL(0,15,0,1),
    //PERIMETER_TOWER,
    //DEFENSE_TURRET,
    KILLING_PIT(0,6,0,1),
    //SQUARE_TOWER,
    //ROUND_TOWER,
    //SMALL_STONE_GATEHOUSE,
    //BIG_STONE_GATEHOUSE,
    //DRAWBRIDGE,
    //KEEP,
    //LOOKOUT_TOWER,
    //CHURCH,
    ARMORY(0,5,0,0),
    BARRACKS(15,0,0,0),
    HOVEL(0,6,0,0),
    MERCENARY_POST(0,10,0,0),
    IRON_MINE(0,20,0,2),
    STOCKPILE(0,0,0,0),
    WOODCUTTERS(0,3,0,1),
    OX_TETHER(0,5,0,1),
    PITCH_RIG(0,20,0,1),
    QUARRY(0,20,0,3),
    APPLE_ORCHARD(0,5,0,1),
    DAIRY_FARMER(0,10,0,1),
    //MAIN_CASTLE,
    WHEAT_FARMER(0,15,0,1),
    BAKERY(0,10,0,1),
    BLACKSMITH(0,20,100,1),
    BREWER(0,10,0,1),
    GRANARY(0,5,0,0),
    //STAIR,
    //ARMORER,
    FLETCHER(0,20,100,1),
    POLE_TURNER(0,100,10,1),
    //OIL_SMELTER,
    //WAL,
    HUNTERS_POST(0,5,0,1),
    //SIEGE_TENT,
    STABLE(0,20,400,0),
    MARKET(0,5,0,1),
    ;
    private int stoneCost = 0;
    private int woodCost = 0;
    private int goldCost = 0;
    private int workersNeeded = 0;

    BuildingEnum(int stoneCost, int woodCost, int goldCost, int workersNeeded) {
        this.stoneCost = stoneCost;
        this.woodCost = woodCost;
        this.goldCost = goldCost;
        this.workersNeeded = workersNeeded;
    }

    public int getStoneCost() {
        return stoneCost;
    }

    public int getWoodCost() {
        return woodCost;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public int getWorkersNeeded() {
        return workersNeeded;
    }
}
