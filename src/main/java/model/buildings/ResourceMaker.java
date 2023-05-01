package model.buildings;

import model.Governance;
import model.Resource;

public class ResourceMaker extends Building{
    public ResourceMaker(BuildingEnum type, Governance owner, int direction) {
        super(type, owner, direction);
    }
    public void produce() {

    }
}
