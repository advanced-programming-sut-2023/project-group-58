package model.buildings;

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
        this.hp = type.getOriginalHp();
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

    public void changeHp(int amount) {this.hp += amount;}
    public void resetHp() {this.hp = type.getOriginalHp();}
}