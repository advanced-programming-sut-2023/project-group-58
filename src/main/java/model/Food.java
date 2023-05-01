package model;

public class Food extends Asset{
    private FoodEnum type;

    public Food(int amount, FoodEnum type) {
        super(amount);
        this.type = type;
    }
}
