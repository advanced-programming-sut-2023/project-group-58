package model;

import java.util.ArrayList;

public class Governance {
    //todo: everytime a food type is manipulated, popularity should change if updateFoodDiversity changes.
    private static ArrayList<User> empires = new ArrayList<>();
    private int popularity  = 0;
    private int meat        = 0;
    private int cheese      = 0;
    private int bread       = 0;
    private int apples      = 0;
    private int foodRate    = -2;
    private int foodDiversity=0;
    private int taxRate     = 0;
    private int fearRate    = 0;
    public void updateFoodDiversity() {
        int nom = 0;
        if(this.meat > 0) nom++;
        if(this.apples > 0) nom++;
        if(this.bread > 0) nom++;
        if(this.cheese > 0) nom++;
        this.foodDiversity = nom;
    }
    private int gold = 0;
    public void makeGovernanceNew(){
        empires.clear();
    }
    public static ArrayList<User> getEmpires() {
        return empires;
    }
    public static void setEmpires(ArrayList<User> empires) {Governance.empires = empires;}

    public int getGold() {return gold;}
    public void purchase(int amount) {this.gold -= amount;}

    public int getPopularity() {return popularity;}

    public int getMeat() {return meat;}

    public int getCheese() {return cheese;}

    public int getBread() {return bread;}

    public int getApples() {return apples;}

    public int getFoodRate() {return foodRate;}

    public int getFoodDiversity() {return foodDiversity;}

    public int getTaxRate() {return taxRate;}

    public int getFearRate() {return fearRate;}

    public void changePopularity(int number) {this.popularity += number;}
    public void changeFoodRate(int number) {this.foodRate += number;}
    public void changeFearRate(int number) {this.fearRate += number;}
    public void changeTaxRate(int number) {this.taxRate += number;}

}
