package model;

import model.buildings.Building;
import model.buildings.Gate;
import model.buildings.Trap;
import model.units.Unit;
import model.units.UnitEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tile {
    private int x;
    private int y;
    private Tile parent;
    private boolean hasTrap;
    private int gCost;
    private int hCost;
    private String rockDirection = "#";
    private TileTexture texture = TileTexture.EARTH;
    private ArrayList<Tree> trees = new ArrayList<>();
    private HashMap<String, ArrayList<Unit>> playersUnits = new HashMap<>();
    //assuming a tile can have more than one tree. (since it can have multiple units)
    private ArrayList<Building> buildings = new ArrayList<>();

    public boolean isHasTrap() {
        return hasTrap;
    }

    public void setHasTrap(boolean hasTrap) {
        this.hasTrap = hasTrap;
    }

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

    public String getRockDirection() {
        return rockDirection;
    }

    public void setRockDirection(String rockDirection) {
        this.rockDirection = rockDirection;
    }

    public char getTileOccupation() {
        if (!this.playersUnits.isEmpty())
            return 'S';
        else if (this.buildings.size() > 0)
            return 'B';
            //todo: after adding towers and walls, return W
        else if (trees.size() > 0)
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
//                int addingNumber = troopCount.get(troop.getTypeName());
//                troopCount.put(troop.getTypeName(),addingNumber+1);
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
        String ans = "\nBuilding(s) here:";
        for (Building building : this.buildings) {
            ans += "\n" + building.getType().getName();
        }
        return ans;
    }

    public ArrayList<Unit> findYourUnits(User master) {
        for (Map.Entry<String, ArrayList<Unit>> arrayListEntry : this.playersUnits.entrySet()) {
            if (arrayListEntry.getKey().equals(master.getUsername()))
                return arrayListEntry.getValue();
        }
        return null;
    }

    public boolean changeState(String state, User master) {
        boolean exist = false;
        for (Map.Entry<String, ArrayList<Unit>> arrayListEntry : this.playersUnits.entrySet())
            if (arrayListEntry.getKey().equals(master.getUsername())) {
                exist = true;
                for (Unit unit : arrayListEntry.getValue()) {
                    unit.setState(state);
                }
            }
        return exist;
    }

    public void unifyYourUnits(Unit unit) {
        ArrayList<Unit> replacement = new ArrayList<>();
        replacement.add(unit);
        for (Map.Entry<String, ArrayList<Unit>> arrayListEntry : this.playersUnits.entrySet()) {
            if (arrayListEntry.getKey().equals(unit.getMaster().getUsername()))
                arrayListEntry.setValue(replacement);
        }
    }

    public void addUnitToTile(Unit unit) {
        this.playersUnits.get(unit.getMaster().getUsername()).add(unit);
    }

    public boolean areEnemiesHere(User current) {
        for (Map.Entry<String, ArrayList<Unit>> arrayListEntry : this.playersUnits.entrySet()) {
            if (!arrayListEntry.getKey().equals(current.getUsername()))
                return true;
        }
        return false;
    }

    public void workingTrap() {
        //todo
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Tile getParent() {
        return parent;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setParent(Tile parent) {
        this.parent = parent;
    }

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getFCost() {
        return gCost + hCost;
    }

    public int getgCost() {
        return gCost;
    }

    public int gethCost() {
        return hCost;
    }

    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    public void sethCost(int hCost) {
        this.hCost = hCost;
    }

    public float getPrice() {
        if (!texture.isWalkability() || existTree()) return 0.0f;
        if (!checkPossibleBuilding()) return 0.0f;
        return 1.0f;
    }

    public boolean existTree() {
        if (trees != null) return false;
        return true;
    }

    public boolean checkPossibleBuilding() {
        for (Building building : buildings) {
            if (building instanceof Gate) {
                if (!((Gate) building).isOpen()) return false;
            } else if (building instanceof Trap) {
                if (((Trap) building).isVisible()) return false;
            } else return false;
        }
        return true;
    }

    public HashMap<String, ArrayList<Unit>> getPlayersUnits() {
        return playersUnits;
    }
}
