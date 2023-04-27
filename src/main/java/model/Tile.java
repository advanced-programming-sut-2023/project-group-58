package model;

import model.buildings.Building;
import model.units.Troop;
import model.units.UnitEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Tile {

    private boolean hasRock = false;
    private String rockDirection = "#";
    private TileTexture texture = TileTexture.EARTH;
    private ArrayList<Tree> trees = new ArrayList<>();
    private HashMap<UnitEnum , Integer> troopCount = new HashMap<>();
    //assuming a tile can have more than one tree. (since it can have multiple units)
    private ArrayList<Troop> troops = new ArrayList<>();
    //OR we can add units. Can have it either way
    private ArrayList<Building> buildings = new ArrayList<>();

    public ArrayList<Tree> getTrees() {
        return trees;
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

    public boolean hasRock() {return hasRock;}

    public void setHasRock(boolean hasRock) {this.hasRock = hasRock;}

    public String getRockDirection() {return rockDirection;}

    public void setRockDirection(String rockDirection) {this.rockDirection = rockDirection;}

    public char getTileOccupation() {
        if(this.troops.size() > 0)
            return 'S';
        else if(this.buildings.size() > 0)
            return 'B';
        //todo: after adding towers and walls, return W
        else if(trees.size() > 0)
            return 'T';
        else
            return '#';
    }
    public void clear() {
        this.buildings.clear();
        this.trees.clear();
        this.troops.clear();
        this.texture = TileTexture.EARTH;
    }

    public String countTroops() {
            String ans = new String();
            troopCount = new HashMap<>();
            for (Troop troop : this.troops) {
                int addingNumber = troopCount.get(troop.getType());
                troopCount.put(troop.getType(),addingNumber+1);
            }
            Iterator<java.util.Map.Entry<UnitEnum, Integer>> it = troopCount.entrySet().iterator();
            // iterating every set of entry in the HashMap.
            while (it.hasNext()) {
                Map.Entry<UnitEnum , Integer> saving = it.next();
                ans += "Type " + saving.getKey() + " -> " + saving.getValue() + "\n";
            }
            return ans;
    }

    public String showBuildings() {
        String ans = new String();
        for (Building building : this.buildings) {
            ans += building.getType();
        }
        return ans;
    }
}
