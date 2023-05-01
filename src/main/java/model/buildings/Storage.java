package model.buildings;

import model.Asset;
import model.Governance;

import java.util.ArrayList;

public class Storage extends Building{
    private ArrayList<Asset> storedGoods = new ArrayList<>();
    private int capacity;

    public Storage(BuildingEnum type, Governance owner, int direction) {
        super(type, owner, direction);
        this.capacity = 50;
    }
}
