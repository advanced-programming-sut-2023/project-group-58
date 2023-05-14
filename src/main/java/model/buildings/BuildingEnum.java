package model.buildings;

import model.Resource;
import model.ResourceEnum;

public enum BuildingEnum {
    CATHEDRAL(BuildingEnumType.ForceRecruitment,"cathedral", 0,0, 1000, new Resource(null,0), 0),
    PITCH_DITCH(BuildingEnumType.TRAP,"pitch_ditch", 0,0, 0, new Resource(ResourceEnum.OIL,2), 0),
    CAGED_WAR_DOGS(BuildingEnumType.TRAP,"caged_war_dogs", 0,0, 100, new Resource(ResourceEnum.WOOD, 10), 0),
    HOPS_FARMER(BuildingEnumType.RESOURCE_MAKER,"hops_farmer", 0, 0,0, new Resource(ResourceEnum.WOOD, 15), 1),
    ENGINEERS_GUILD(BuildingEnumType.BUILDING,"engineers_guild", 0, 0,100, new Resource(ResourceEnum.WOOD, 10), 0),
    INN(BuildingEnumType.BUILDING,"inn", 0, 0,100, new Resource(ResourceEnum.WOOD, 20), 1),
    MILL(BuildingEnumType.RESOURCE_MAKER,"mill", 0,0, 0, new Resource(ResourceEnum.WOOD, 20), 3),
    PERIMETER_TOWER(BuildingEnumType.TOWER,"perimeter_tower", 0,0, 0, new Resource(ResourceEnum.STONE, 10), 0),
    DEFENSE_TURRET(BuildingEnumType.TOWER, "defense_turret", 0,0, 0, new Resource(ResourceEnum.STONE, 15), 0),
    KILLING_PIT(BuildingEnumType.TRAP,"killing_pit", 0,0, 0, new Resource(ResourceEnum.WOOD, 6), 0),
    SQUARE_TOWER(BuildingEnumType.TOWER, "square_tower", 0,0, 0, new Resource(ResourceEnum.STONE, 35), 0),
    ROUND_TOWER(BuildingEnumType.TOWER, "round_tower", 0, 0,0, new Resource(ResourceEnum.STONE, 40), 0),
    SMALL_STONE_GATEHOUSE(BuildingEnumType.GATE, "small_stone_gatehouse", 0,0, 0, new Resource(null, 0), 0),
    BIG_STONE_GATEHOUSE(BuildingEnumType.GATE, "big_stone_gatehouse", 0, 0,0, new Resource(ResourceEnum.STONE, 20), 0),
    DRAWBRIDGE(BuildingEnumType.GATE, "drawbridge", 0, 0,0, new Resource(ResourceEnum.WOOD, 10), 0),
    //KEEP(BuildingEnumType.BUILDING, "keep", 0, 0, new Resource(null, 0), 0),
    LOOKOUT_TOWER(BuildingEnumType.TOWER, "lookout_tower", 0,0, 0, new Resource(ResourceEnum.STONE, 10), 0),
    CHURCH(BuildingEnumType.ForceRecruitment, "church", 0,0, 250, new Resource(null, 0), 0),
    ARMOURY(BuildingEnumType.STORAGE, "armoury", 0,0, 0, new Resource(ResourceEnum.WOOD, 5), 0),
    BARRACKS(BuildingEnumType.BUILDING, "barrack", 0,0, 0, new Resource(ResourceEnum.STONE, 15), 0),
    HOVEL(BuildingEnumType.BUILDING, "hovel", 0, 0,0, new Resource(ResourceEnum.WOOD, 6), 0),
    MERCENARY_POST(BuildingEnumType.BUILDING, "mercenary_post", 0,0, 0, new Resource(ResourceEnum.WOOD, 10), 0),
    IRON_MINE(BuildingEnumType.RESOURCE_MAKER, "iron_mine", 0,0, 0, new Resource(ResourceEnum.WOOD, 20), 2),
    STOCKPILE(BuildingEnumType.STORAGE, "stockpile", 0,0, 0, new Resource(null, 0), 0),
    WOODCUTTERS(BuildingEnumType.RESOURCE_MAKER, "woodcutter", 0,0, 0, new Resource(ResourceEnum.WOOD, 3), 1),
    OX_TETHER(BuildingEnumType.RESOURCE_MAKER, "ox_tether", 0,0, 0, new Resource(ResourceEnum.WOOD, 5), 1),
    PITCH_RIG(BuildingEnumType.RESOURCE_MAKER, "pitch_rig", 0,0, 0, new Resource(ResourceEnum.WOOD, 20), 1),
    QUARRY(BuildingEnumType.RESOURCE_MAKER, "quarry", 0,0, 0, new Resource(ResourceEnum.WOOD, 20), 3),
    APPLE_ORCHARD(BuildingEnumType.RESOURCE_MAKER, "apple_orchard", 0,0, 0, new Resource(ResourceEnum.WOOD, 5), 1),
    DIARY_FARM(BuildingEnumType.RESOURCE_MAKER, "diary_farmer", 0,0, 0, new Resource(ResourceEnum.WOOD, 10), 1),
    WHEAT_FARM(BuildingEnumType.RESOURCE_MAKER, "wheat_farm", 0,0, 0, new Resource(ResourceEnum.WOOD, 15), 1),
    BAKERY(BuildingEnumType.RESOURCE_MAKER, "bakery", 0,0, 0, new Resource(ResourceEnum.WOOD, 10), 1),
    BLACKSMITH(BuildingEnumType.RESOURCE_MAKER, "blacksmith", 0,0, 100, new Resource(ResourceEnum.WOOD, 20), 1),
    BREWER(BuildingEnumType.RESOURCE_MAKER , "brewer", 0,0, 0, new Resource(ResourceEnum.WOOD, 10), 1),
    GRANARY(BuildingEnumType.STORAGE, "granary", 0,0, 0 ,new Resource(ResourceEnum.WOOD, 5), 0),
    STAIR(BuildingEnumType.BUILDING, "stair", 0,0, 0, new Resource(ResourceEnum.STONE, 0), 0),//چرا سمگ؟
    ARMOURER(BuildingEnumType.RESOURCE_MAKER, "armourer",0,0, 100, new Resource(ResourceEnum.WOOD, 20), 1),
    FLETCHER(BuildingEnumType.RESOURCE_MAKER, "fletcher", 0, 0,100, new Resource(ResourceEnum.WOOD, 20), 1),
    POLE_TURNER(BuildingEnumType.RESOURCE_MAKER, "pole_turner", 0,0, 100, new Resource(ResourceEnum.WOOD, 10), 1),
    OIL_SMELTER(BuildingEnumType.RESOURCE_MAKER, "oil_smelter", 0, 0,100, new Resource(ResourceEnum.IRON, 10), 1),
    //WALL(BuildingEnumType.BUILDING, "wall", 0, 0, new Resource(ResourceEnum.STONE, 0), 0),
    HUNTERS_POST(BuildingEnumType.RESOURCE_MAKER, "hunter_post", 0,0, 0, new Resource(ResourceEnum.WOOD, 5), 1),
    SIEGE_TENT(BuildingEnumType.BUILDING, "siege_tent", 0,0, 0, new Resource(null, 0), 0),
    STABLE(BuildingEnumType.BUILDING, "stable", 0,0, 400, new Resource(ResourceEnum.WOOD, 20), 0),
    MARKET(BuildingEnumType.BUILDING, "market", 0,0, 0, new Resource(ResourceEnum.WOOD, 5), 1),
    ;
    private BuildingEnumType type;
    private String name;
    private int originalHp;
    private int rate;
    private int goldCost;
    private int worker;
    private Resource resource;

    BuildingEnum(BuildingEnumType type, String name, int hp, int rate, int goldCost, Resource resource, int worker) {
        this.type = type;
        this.name = name;
        this.originalHp = hp;
        this.goldCost = goldCost;
        this.resource = resource;
        this.worker = worker;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public int getOriginalHp() {
        return originalHp;
    }

    public int getGoldCost() {
        return goldCost;
    }


    public int getWorker() {
        return worker;
    }

    public BuildingEnumType getType() {
        return type;
    }

    public Resource getResource() {
        return resource;
    }

    public int getRate() {return rate;}
}
