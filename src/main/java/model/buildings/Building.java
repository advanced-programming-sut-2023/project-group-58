package model.buildings;

import model.User;

public class Building {
    protected BuildingEnum type;
    protected User master;

    public Building(BuildingEnum type, User master) {
        this.type = type;
        this.master = master;
    }

    public BuildingEnum getType() {
        return type;
    }
}
