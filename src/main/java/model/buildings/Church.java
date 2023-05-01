package model.buildings;

import model.Governance;

public class Church extends Building{
    public Church(BuildingEnum type, Governance owner, int direction){
        super(type, owner, direction);
    }
}
