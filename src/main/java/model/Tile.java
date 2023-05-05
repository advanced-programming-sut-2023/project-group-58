package model;

import model.buildings.Building;
import model.units.Unit;
import model.units.UnitEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tile {
    private String rockDirection = "#";
    private TileTexture texture = TileTexture.EARTH;
    private ArrayList<Tree> trees = new ArrayList<>();
    private HashMap<User , ArrayList<Unit>> playersUnits = new HashMap<>();
    //assuming a tile can have more than one tree. (since it can have multiple units)
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


    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public String getRockDirection() {return rockDirection;}

    public void setRockDirection(String rockDirection) {this.rockDirection = rockDirection;}

    public char getTileOccupation() {
        if(!this.playersUnits.isEmpty())
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
        this.playersUnits.clear();
        this.texture = TileTexture.EARTH;
    }

//    public String countTroops() {
//            String ans = new String();
//            troopCount = new HashMap<>();
//            for (Troop troop : this.troops) {
//                int addingNumber = troopCount.get(troop.getType());
//                troopCount.put(troop.getType(),addingNumber+1);
//            }
//            Iterator<java.util.Map.Entry<UnitEnum, Integer>> it = troopCount.entrySet().iterator();
//            // iterating every set of entry in the HashMap.
//            while (it.hasNext()) {
//                Map.Entry<UnitEnum , Integer> saving = it.next();
//                ans += "Type " + saving.getKey() + " -> " + saving.getValue() + "\n";
//            }
//            return ans;
//    }

    public String showBuildings() {
        String ans = new String();
        for (Building building : this.buildings) {
            ans += building.getType();
        }
        return ans;
    }
    public ArrayList<Unit> findYourUnits(User master) {
        for (Map.Entry<User, ArrayList<Unit>> arrayListEntry : this.playersUnits.entrySet()) {
            if(arrayListEntry.getKey().getUsername().equals(master.getUsername()))
                return this.playersUnits.get(master);
        }
        return null;
    }
}
