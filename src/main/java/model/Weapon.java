package model;

public class Weapon extends Asset{
    private WeaponEnum type;

    public Weapon(int amount, WeaponEnum type) {
        super(amount);
        this.type = type;
    }

    public WeaponEnum getType() {
        return type;
    }
    @Override
    public void addAsset(int amount) {
        super.addAsset(amount);
    }
}
