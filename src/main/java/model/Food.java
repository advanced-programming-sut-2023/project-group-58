package model;

public class Food extends Asset{
    private FoodEnum type;

    public Food(int amount, FoodEnum type) {
        super(amount);
        this.type = type;
    }

    public FoodEnum getType() {
        return type;
    }

    @Override
    public void changeAsset(int amount) {
        super.changeAsset(amount);
    }
}
