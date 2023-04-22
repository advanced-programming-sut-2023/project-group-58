package model;

import java.util.ArrayList;

public class Tile {
    private int x = 200;
    private int y = 200;
    private TileTexture texture = TileTexture.EARTH;
    private ArrayList<Troop> troops = new ArrayList<>();
    //OR we can add units. Can have it either way
    private ArrayList<Building> buildings = new ArrayList<>();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TileTexture getTexture() {
        return texture;
    }

    public void setTexture(TileTexture texture) {
        this.texture = texture;
    }

    public ArrayList<Troop> getTroops() {
        return troops;
    }

    public void setTroops(ArrayList<Troop> troops) {
        this.troops = troops;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }
}
