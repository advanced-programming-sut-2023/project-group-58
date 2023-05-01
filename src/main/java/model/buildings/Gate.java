package model.buildings;

import model.Governance;
import model.User;

public class Gate extends Building{
    public Gate(BuildingEnum type, User owner, int direction) {
        super(type, owner, direction);
    }
}
