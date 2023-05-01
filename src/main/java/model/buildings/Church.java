package model.buildings;

import model.Governance;
import model.User;

public class Church extends Building{
    public Church(BuildingEnum type, User owner, int direction){
        super(type, owner, direction);
    }
}
