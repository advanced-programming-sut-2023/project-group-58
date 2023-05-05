package model.units;

public class Troop {
    private UnitEnum type;
    private int hp;

    public Troop(UnitEnum type) {
        this.type = type;
        this.hp = type.getPrimaryHp();
    }

    public UnitEnum getType() {
        return type;
    }

    public void setType(UnitEnum type) {
        this.type = type;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
