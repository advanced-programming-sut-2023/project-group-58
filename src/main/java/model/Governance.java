package model;

import model.buildings.Building;

import java.util.ArrayList;

public class Governance {
    //todo: everytime a food type is manipulated, popularity should change if updateFoodDiversity changes.
    private static ArrayList<User> empires = new ArrayList<>();
    private int unemployedPopulation;
    private int popularity  = 0;
    private int foodRate    = -2;
    private int foodDiversity=0;
    private int taxRate     = 0;
    private int fearRate    = 0;
    private Resource resource = new Resource();
    private int gold = 0;
    private ArrayList<Building> buildings = new ArrayList<>();

    public void changeUnemployedPopulation(int unemployedPopulation) {this.unemployedPopulation += unemployedPopulation;}

    public int getUnemployedPopulation() {return unemployedPopulation;}
    public ArrayList<Building> getBuildings() {return buildings;}

    public void makeGovernanceNew(){
        empires.clear();
    }
    public static ArrayList<User> getEmpires() {
        return empires;
    }
    public static void setEmpires(ArrayList<User> empires) {Governance.empires = empires;}

    public int getGold() {return gold;}
    public void changeGold(int amount) {this.gold += amount;}

    public int getPopularity() {return popularity;}
    public int getFoodRate() {return foodRate;}

    public int getFoodDiversity() {return foodDiversity;}

    public int getTaxRate() {return taxRate;}

    public int getFearRate() {return fearRate;}

    public void changePopularity(int number) {this.popularity += number;}
    public void changeFoodRate(int number) {this.foodRate += number;}
    public void changeFearRate(int number) {this.fearRate += number;}
    public void changeTaxRate(int number) {this.taxRate += number;}
    public Resource getResource() {return resource;}
}
