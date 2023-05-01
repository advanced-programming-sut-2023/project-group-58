package model.buildings;

import model.Governance;

public class Tower extends Building{
    private int fireRange;
    private int defendRange;
    public Tower(BuildingEnum type, Governance owner, int direction) {
        super(type, owner, direction);
    }
}
