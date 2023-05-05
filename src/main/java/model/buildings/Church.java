package model.buildings;

import model.Governance;
import model.User;

public class Church extends Building{
    boolean isCathedral;
    public Church(BuildingEnum type, User owner, int direction, boolean isCathedral){
        super(type, owner, direction);
        this.isCathedral = isCathedral;
        owner.getGovernance().changePopularity(2);
    }
}
