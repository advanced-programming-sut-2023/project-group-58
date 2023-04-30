package model.buildings;

import model.User;

public class Storage extends Building{
    private int capacity;
    private int stored;

    public Storage(BuildingEnum type, User master) {
        super(type, master);
    }
}
