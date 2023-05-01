package model.buildings;

import model.Governance;

public class Building {
    private BuildingEnum type;
    private int hp;
    private Governance owner;
    private int direction;

    public Building(BuildingEnum type, Governance owner, int direction) {
        this.type = type;
        this.owner = owner;
        this.direction = direction;
        this.hp = type.getHp();
    }

    public BuildingEnum getType() {
        return type;
    }
}
