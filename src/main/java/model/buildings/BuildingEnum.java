package model.buildings;

import model.Resource;
import model.ResourceEnum;

public enum BuildingEnum {
    CATHEDRAL("cathedral", 0, 1000, null, 0),
    PITCH_DITCH("pitch ditch", 0, 0, ResourceEnum.PITCH, 0),
    CAGED_WAR_DOGS("caged war dogs", 0, 100, ResourceEnum.WOOD, 0),
    HOPS_FARMER("hops farmer", 0, 0, ResourceEnum.WOOD, 1),
    ENGINEERS_GUILD("engineers guild", 0, 100, ResourceEnum.WOOD, 0),
    INN("inn", 0, 100, ResourceEnum.WOOD, 1),
    MILL("mill", 0, 0, ResourceEnum.WOOD, 3),
    PERIMETER_TOWER("perimeter tower", 0, 0, ResourceEnum.STONE, 0),
    DEFENSE_TURRET("defense turret", 0, 0, ResourceEnum.STONE, 0),
    KILLING_PIT("killing pit", 0, 0, ResourceEnum.WOOD, 0),
    SQUARE_TOWER("square tower", 0, 0, ResourceEnum.STONE, 0),
    ROUND_TOWER("round tower", 0, 0, ResourceEnum.STONE, 0),
    SMALL_STONE_GATEHOUSE("small stone gatehouse", 0, 0, ResourceEnum.STONE, 0),
    BIG_STONE_GATEHOUSE("big stone gatehouse", 0, 0, ResourceEnum.STONE, 0),
    DRAWBRIDGE("drawbridge", 0, 0, ResourceEnum.WOOD, 0),
    KEEP("keep", 0, 0, null, 0),
    LOOKOUT_TOWER("lookout tower", 0, 0, ResourceEnum.STONE, 0),
    CHURCH("church", 0, 250, null, 0),
    ARMOURY("armoury", 0, 0, ResourceEnum.WOOD, 0),
    BARRACKS("barrack", 0, 0, ResourceEnum.STONE, 0),
    HOVEL("hovel", 0, 0, ResourceEnum.WOOD, 0),
    MERCENARY_POST("mercenary post", 0, 0, ResourceEnum.WOOD, 0),
    IRON_MINE("iron mine", 0, 0, ResourceEnum.WOOD, 2),
    STOCKPILE("stockpile", 0, 0, null, 0),
    WOODCUTTERS("woodcutter", 0, 0, ResourceEnum.WOOD, 1),
    OX_TETHER("ox tether", 0, 0, ResourceEnum.WOOD, 1),
    PITCH_RIG("pitch rig", 0, 0, ResourceEnum.WOOD, 1),
    QUARRY("quarry", 0, 0, ResourceEnum.WOOD, 3),
    APPLE_ORCHARD("apple orchard", 0, 0, ResourceEnum.WOOD, 1),
    DIARY_FARM("diary farmer", 0, 0, ResourceEnum.WOOD, 1),
    WHEAT_FARM("wheat farm", 0, 0, ResourceEnum.WOOD, 1),
    BAKERY("bakery", 0, 0, ResourceEnum.WOOD, 1),
    BLACKSMITH("blacksmith", 0, 100, ResourceEnum.WOOD, 1),
    BREWER("brewer", 0, 0, ResourceEnum.WOOD, 1),
    GRANARY("granary", 0, 0 ,ResourceEnum.WOOD, 0),
    STAIR("stair", 0, 0, ResourceEnum.STONE, 0),
    ARMOURER("armourer",0, 100, ResourceEnum.WOOD, 1),
    FLETCHER("fletcher", 0, 100, ResourceEnum.WOOD, 1),
    POLE_TURNER("pole turner", 0, 100, ResourceEnum.WOOD, 1),
    OIL_SMELTER("oil smelter", 0, 100, ResourceEnum.IRON, 1),
    WALL("wall", 0, 0, ResourceEnum.STONE, 0),
    HUNTERS_POST("hunter post", 0, 0, ResourceEnum.WOOD, 1),
    SIEGE_TENT("siege tent", 0, 0, null, 1),
    STABLE("stable", 0, 400, ResourceEnum.WOOD, 0),
    MARKET("market", 0, 0, ResourceEnum.WOOD, 1),
    ;
    private String name;
    private int hp;
    private int goldCost;
    private ResourceEnum resource;
    private int worker;

    BuildingEnum(String name, int hp, int goldCost, ResourceEnum resource, int worker) {
        this.name = name;
        this.hp = hp;
        this.goldCost = goldCost;
        this.resource = resource;
        this.worker = worker;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public ResourceEnum getResource() {
        return resource;
    }

    public int getWorker() {
        return worker;
    }
}
