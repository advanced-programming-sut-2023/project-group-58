package model.buildings;

import model.Governance;

public class Gate extends Building{
    public Gate(BuildingEnum type, Governance owner, int direction) {
        super(type, owner, direction);
    }
}
