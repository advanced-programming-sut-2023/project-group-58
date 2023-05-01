package model.buildings;

import model.Resource;
import model.ResourceEnum;

public enum BuildingEnum {
    CATHEDRAL(BuildingEnumType.CHURCH,"cathedral", 0, 1000, null, 0, 0),
    PITCH_DITCH(BuildingEnumType.TRAP,"pitch ditch", 0, 0, ResourceEnum.PITCH, 0, 0),
    CAGED_WAR_DOGS(BuildingEnumType.TRAP,"caged war dogs", 0, 100, ResourceEnum.WOOD, 0, 0),
    HOPS_FARMER(BuildingEnumType.RESOURCE_MAKER,"hops farmer", 0, 0, ResourceEnum.WOOD, 1, 0),
    ENGINEERS_GUILD(BuildingEnumType.BUILDING,"engineers guild", 0, 100, ResourceEnum.WOOD, 0, 0),
    INN(BuildingEnumType.BUILDING,"inn", 0, 100, ResourceEnum.WOOD, 1, 0),
    MILL(BuildingEnumType.RESOURCE_MAKER,"mill", 0, 0, ResourceEnum.WOOD, 3, 0),
    PERIMETER_TOWER(BuildingEnumType.TOWER,"perimeter tower", 0, 0, ResourceEnum.STONE, 0, 0),
    DEFENSE_TURRET(BuildingEnumType.TOWER, "defense turret", 0, 0, ResourceEnum.STONE, 0, 0),
    KILLING_PIT(BuildingEnumType.TRAP,"killing pit", 0, 0, ResourceEnum.WOOD, 0, 0),
    SQUARE_TOWER(BuildingEnumType.TOWER, "square tower", 0, 0, ResourceEnum.STONE, 0, 0),
    ROUND_TOWER(BuildingEnumType.TOWER, "round tower", 0, 0, ResourceEnum.STONE, 0, 0),
    SMALL_STONE_GATEHOUSE(BuildingEnumType.GATE, "small stone gatehouse", 0, 0, ResourceEnum.STONE, 0, 0),
    BIG_STONE_GATEHOUSE(BuildingEnumType.GATE, "big stone gatehouse", 0, 0, ResourceEnum.STONE, 0, 0),
    DRAWBRIDGE(BuildingEnumType.GATE, "drawbridge", 0, 0, ResourceEnum.WOOD, 0, 0),
    KEEP(BuildingEnumType.BUILDING, "keep", 0, 0, null, 0, 0),
    LOOKOUT_TOWER(BuildingEnumType.TOWER, "lookout tower", 0, 0, ResourceEnum.STONE, 0, 0),
    CHURCH(BuildingEnumType.CHURCH, "church", 0, 250, null, 0, 0),
    ARMOURY(BuildingEnumType.STORAGE, "armoury", 0, 0, ResourceEnum.WOOD, 0, 0),
    BARRACKS(BuildingEnumType.BUILDING, "barrack", 0, 0, ResourceEnum.STONE, 0, 0),
    HOVEL(BuildingEnumType.BUILDING, "hovel", 0, 0, ResourceEnum.WOOD, 0, 0),
    MERCENARY_POST(BuildingEnumType.BUILDING, "mercenary post", 0, 0, ResourceEnum.WOOD, 0, 0),
    IRON_MINE(BuildingEnumType.RESOURCE_MAKER, "iron mine", 0, 0, ResourceEnum.WOOD, 2, 0),
    STOCKPILE(BuildingEnumType.STORAGE, "stockpile", 0, 0, null, 0, 0),
    WOODCUTTERS(BuildingEnumType.RESOURCE_MAKER, "woodcutter", 0, 0, ResourceEnum.WOOD, 1, 0),
    OX_TETHER(BuildingEnumType.RESOURCE_MAKER, "ox tether", 0, 0, ResourceEnum.WOOD, 1, 0),
    PITCH_RIG(BuildingEnumType.RESOURCE_MAKER, "pitch rig", 0, 0, ResourceEnum.WOOD, 1, 0),
    QUARRY(BuildingEnumType.RESOURCE_MAKER, "quarry", 0, 0, ResourceEnum.WOOD, 3, 0),
    APPLE_ORCHARD(BuildingEnumType.RESOURCE_MAKER, "apple orchard", 0, 0, ResourceEnum.WOOD, 1, 0),
    DIARY_FARM(BuildingEnumType.RESOURCE_MAKER, "diary farmer", 0, 0, ResourceEnum.WOOD, 1, 0),
    WHEAT_FARM(BuildingEnumType.RESOURCE_MAKER, "wheat farm", 0, 0, ResourceEnum.WOOD, 1, 0),
    BAKERY(BuildingEnumType.RESOURCE_MAKER, "bakery", 0, 0, ResourceEnum.WOOD, 1, 0),
    BLACKSMITH(BuildingEnumType.RESOURCE_MAKER, "blacksmith", 0, 100, ResourceEnum.WOOD, 1, 0),
    BREWER(BuildingEnumType.RESOURCE_MAKER , "brewer", 0, 0, ResourceEnum.WOOD, 1, 0),
    GRANARY(BuildingEnumType.STORAGE, "granary", 0, 0 ,ResourceEnum.WOOD, 0, 0),
    STAIR(BuildingEnumType.BUILDING, "stair", 0, 0, ResourceEnum.STONE, 0, 0),
    ARMOURER(BuildingEnumType.RESOURCE_MAKER, "armourer",0, 100, ResourceEnum.WOOD, 1, 0),
    FLETCHER(BuildingEnumType.RESOURCE_MAKER, "fletcher", 0, 100, ResourceEnum.WOOD, 1, 0),
    POLE_TURNER(BuildingEnumType.RESOURCE_MAKER, "pole turner", 0, 100, ResourceEnum.WOOD, 1, 0),
    OIL_SMELTER(BuildingEnumType.RESOURCE_MAKER, "oil smelter", 0, 100, ResourceEnum.IRON, 0, 1),
    WALL(BuildingEnumType.BUILDING, "wall", 0, 0, ResourceEnum.STONE, 0, 0),
    HUNTERS_POST(BuildingEnumType.RESOURCE_MAKER, "hunter post", 0, 0, ResourceEnum.WOOD, 1, 0),
    SIEGE_TENT(BuildingEnumType.BUILDING, "siege tent", 0, 0, null, 0, 1),
    STABLE(BuildingEnumType.BUILDING, "stable", 0, 400, ResourceEnum.WOOD, 0, 0),
    MARKET(BuildingEnumType.BUILDING, "market", 0, 0, ResourceEnum.WOOD, 1, 0),
    ;
    private BuildingEnumType type;
    private String name;
    private int hp;
    private int goldCost;
    private ResourceEnum resource;
    private int resourceCount;
    private int worker;
    private int Engineer;

    BuildingEnum(BuildingEnumType type, String name, int hp, int goldCost, ResourceEnum resource, int resourceCount, int worker, int engineer) {
        this.type = type;
        this.name = name;
        this.hp = hp;
        this.goldCost = goldCost;
        this.resource = resource;
        this.resourceCount = resourceCount;
        this.worker = worker;
        Engineer = engineer;
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

    public BuildingEnumType getType() {
        return type;
    }

    public int getResourceCount() {
        return resourceCount;
    }

    public int getEngineer() {
        return Engineer;
    }
}
