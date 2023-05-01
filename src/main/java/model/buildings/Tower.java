package model.buildings;

import model.Governance;
import model.User;

public class Tower extends Building{
    private int fireRange;
    private int defendRange;
    public Tower(BuildingEnum type, User owner, int direction) {
        super(type, owner, direction);
    }
}
