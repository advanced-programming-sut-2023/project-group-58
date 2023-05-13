package model.buildings;

import model.Governance;
import model.User;

public class Trap extends Building{
    private int damage;
    private boolean isVisible;
    public Trap(BuildingEnum type, User owner, int direction) {
        super(type, owner, direction);
        switch (type) {
            case KILLING_PIT:
                damage = 0;
                break;
        }
        isVisible = false;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
