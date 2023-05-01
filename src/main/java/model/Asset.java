package model;

public class Asset {
    private int amount;

    public Asset(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
    public void addAsset(int amount){
        this.amount += amount;
    }
}
