package model.units;

import model.User;

public class NotTroop extends Unit{
    public NotTroop(User master, UnitEnum type, int count, String primaryLocation) {
        super(master, type, count, primaryLocation);
    }
}
