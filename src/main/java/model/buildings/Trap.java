package model.buildings;

import model.Governance;

public class Trap extends Building{
    private int damage;
    public Trap(BuildingEnum type, Governance owner, int direction) {
        super(type, owner, direction);
    }
}
