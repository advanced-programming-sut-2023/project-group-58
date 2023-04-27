package model.buildings;

public class Storage extends Building{
    private int capacity;
    private int stored;

    public Storage(BuildingEnum type) {
        super(type);
    }
}
