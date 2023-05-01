package model.buildings;

import model.Governance;
import model.User;

public class Trap extends Building{
    private int damage;
    public Trap(BuildingEnum type, User owner, int direction) {
        super(type, owner, direction);
    }
}
