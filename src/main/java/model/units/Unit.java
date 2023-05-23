package model.units;

import model.Point;
import model.User;

import java.util.ArrayList;

public class Unit {
    String state;
    //this is to distinguish between different units in one house,
    //primary location is set when a unit is created, and will be
    //updated everytime select order is used:
    private int xOrigin;
    private int yOrigin;
    private int xDestination = -1;
    private int yDestination = -1;
    private boolean onPatrol = false;
    private Point[] patrolDestinations;
    User master;
    //private HashMap<UnitEnum, ArrayList<Troop>> troops = new HashMap<>();
    private ArrayList<Troop> troops = new ArrayList<>();

    public Unit(User master, UnitEnum type, int count, int yOrigin, int xOrigin) {
        this.master = master;
//        ArrayList<Troop> sameKind = new ArrayList<>();
//        for (int i = 0; i < count; i++)
//            sameKind.add(new Troop(type));
//        this.troops.put(type, sameKind);
        for (int i = 0; i < count; i++) {
            troops.add(new Troop(type));
        }
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        this.state = "standing";
    }

    public void addByTypeAndCount(UnitEnum type, int count) {
        for (int i = 0; i < count; i++) {
            troops.add(new Troop(type));
        }
//        ArrayList<Troop> sameKind = this.troops.get(type);
//        for (int i = 0; i < count; i++)
//            sameKind.add(new Troop(type));
//        this.troops.put(type, sameKind);
    }

//    public void addByTypeAndArrayList(UnitEnum type, ArrayList<Troop> list) {
//        this.troops.putIfAbsent(type, list);
//        for (Troop troop : list) {
//            this.troops.get(type).add(troop);
//        }
//    }

    public void addByUnit(Unit unit) {
        this.troops.addAll(unit.troops);
//        for (Map.Entry<UnitEnum, ArrayList<Troop>> integerEntry : unit.troops.entrySet()) {
//            addByTypeAndArrayList(integerEntry.getKey(), integerEntry.getValue());
//        }
    }

    public String getState() {
        return state;
    }

    public int getxOrigin() {
        return xOrigin;
    }

    public int getyOrigin() {
        return yOrigin;
    }

    public User getMaster() {
        return master;
    }

    public ArrayList<Troop> getTroops() {
        return troops;
    }

    public void takeSteadyDamageForAll(int damage) {
        for (Troop troop : troops) {
            if (!troop.isDead())
                troop.setHp(troop.getHp() - damage);
            if (troop.getHp() <= 0)
                troop.setDead(true);
        }
    }

    public void clearTheDead() {
        ArrayList<Troop> theLiving = new ArrayList<>();
        for (Troop troop : troops) {
            if (!troop.isDead())
                theLiving.add(troop);
        }
        troops = theLiving;
    }

    public void setxOrigin(int xOrigin) {
        this.xOrigin = xOrigin;
    }

    public void setyOrigin(int yOrigin) {
        this.yOrigin = yOrigin;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getxDestination() {
        return xDestination;
    }

    public void setxDestination(int xDestination) {
        this.xDestination = xDestination;
    }

    public int getyDestination() {
        return yDestination;
    }

    public void setyDestination(int yDestination) {
        this.yDestination = yDestination;
    }

    public int getSpeed() {
        int speed;
        int sum = 0;
        int counter = 0;
        for (Troop troop : troops) {
            sum += troop.getType().getSpeed();
            counter++;
        }
        if (counter != 0)
            return Math.round(sum / counter);
        else return 0;
    }

    public boolean isOnPatrol() {
        return onPatrol;
    }

    public void setOnPatrol(boolean onPatrol) {
        this.onPatrol = onPatrol;
    }

    public Point[] getPatrolDestinations() {
        return patrolDestinations;
    }

    public void setPatrolDestinations(Point[] patrolDestinations) {
        this.patrolDestinations = patrolDestinations;
    }
}
