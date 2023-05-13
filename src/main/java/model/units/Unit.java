package model.units;

import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Unit {
    String state;
    //this is to distinguish between different units in one house,
    //primary location is set when a unit is created, and will be
    //updated everytime select order is used:
    String primaryLocation;
    User master;
    private HashMap<UnitEnum, ArrayList<Troop>> troops = new HashMap<>();

    public Unit(User master, UnitEnum type, int count, String primaryLocation) {
        this.master = master;
        ArrayList<Troop> sameKind = new ArrayList<>();
        for(int i = 0; i < count; i++)
            sameKind.add(new Troop(type));
        this.troops.put(type, sameKind);
    }

    public void addByTypeAndCount(UnitEnum type, int count) {
        ArrayList<Troop> sameKind = this.troops.get(type);
        for(int i = 0; i < count; i++)
            sameKind.add(new Troop(type));
        this.troops.put(type, sameKind);
    }

    public void addByTypeAndArrayList(UnitEnum type, ArrayList<Troop> list) {
        this.troops.putIfAbsent(type, list);
        for (Troop troop : list) {
            this.troops.get(type).add(troop);
        }
    }

    public void addByUnit(Unit unit) {
        for (Map.Entry<UnitEnum, ArrayList<Troop>> integerEntry : unit.troops.entrySet()) {
            addByTypeAndArrayList(integerEntry.getKey(),integerEntry.getValue());
        }
    }

    public void takeDamage(int damage) {
        //todo
    }
}
