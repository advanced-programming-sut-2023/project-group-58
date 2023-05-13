package model.buildings;

import model.Governance;
import model.User;

public class Gate extends Building{
    private boolean isOpen = true;
    public Gate(BuildingEnum type, User owner, int direction) {
        super(type, owner, direction);
    }
    public void changeGateStatus(boolean state){
        isOpen = state;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
