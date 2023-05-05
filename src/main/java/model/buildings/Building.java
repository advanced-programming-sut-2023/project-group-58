package model.buildings;

import model.Governance;
import model.ResourceEnum;
import model.User;

public class Building {
    protected BuildingEnum type;
    protected int hp;
    protected User owner;
    protected int direction;

    public Building(BuildingEnum type, User owner, int direction) {
        this.type = type;
        this.owner = owner;
        this.direction = direction;
        this.hp = type.getHp();
    }

    public BuildingEnum getType() {
        return type;
    }

    public int getHp() {
        return hp;
    }

    public User getOwner() {
        return owner;
    }

    public int getDirection() {
        return direction;
    }
}