package model.buildings;

public class Building {
    private BuildingEnum type;

    public Building(BuildingEnum type) {
        this.type = type;
    }

    public BuildingEnum getType() {
        return type;
    }
}
