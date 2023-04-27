package model.units;

import model.User;

public class Troop extends Unit{
    private final UnitEnum type;
    private final User master;

    public Troop(UnitEnum type, User master) {
        this.type   = type;
        this.master = master;
    }
    public UnitEnum getType() {
        return type;
    }

    public User getMaster() {
        return master;
    }
}
