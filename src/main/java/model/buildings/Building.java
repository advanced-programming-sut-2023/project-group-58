package model.buildings;

import model.Governance;

public class Building {
    protected BuildingEnum type;
    protected int hp;
    protected Governance owner;
    protected int direction;

    public Building(BuildingEnum type, Governance owner, int direction) {
        this.type = type;
        this.owner = owner;
        this.direction = direction;
        this.hp = type.getHp();
    }
}