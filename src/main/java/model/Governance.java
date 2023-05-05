package model;

import controller.modelFunctions.ResourceMakerFuncs;
import model.buildings.Building;

import java.util.ArrayList;

public class Governance {
    //todo: everytime a food type is manipulated, popularity should change if updateFoodDiversity changes.
    private static ArrayList<User> empires = new ArrayList<>();
    private User master;
    private int unemployedPopulation;
    private int popularity  = 0;
    private int foodRate    = -2;
    private int taxRate     = 0;
    private int fearRate    = 0;
    private int gold = 0;
    private Building granary;
    private Building stockpile;
    private Building armoury;
    private ArrayList<Building> buildings = new ArrayList<>();
    private ArrayList<Resource> resources = new ArrayList<>();

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

    public int getFoodDiversity() {
        int num = 0;
        for (Resource resource : this.resources) {
            if((resource.getType().equals(ResourceEnum.APPLE) || resource.getType().equals(ResourceEnum.BREAD) ||
                resource.getType().equals(ResourceEnum.MEAT)  || resource.getType().equals(ResourceEnum.CHEESE))
                && resource.getAmount() != 0)
                num++;
        }
      return num;
    }

    public int getTaxRate() {return taxRate;}

    public int getFearRate() {return fearRate;}

    public void changePopularity(int number) {this.popularity += number;}
    public void changeFoodRate(int number) {this.foodRate += number;}
    public void changeFearRate(int number) {this.fearRate += number;}
    public void changeTaxRate(int number) {this.taxRate += number;}

    public Building getGranary() {
        return granary;
    }

    public Building getStockpile() {
        return stockpile;
    }

    public Building getArmoury() {
        return armoury;
    }

    public void setGranary(Building granary) {
        this.granary = granary;
    }

    public void setStockpile(Building stockpile) {
        this.stockpile = stockpile;
    }

    public void setArmoury(Building armoury) {
        this.armoury = armoury;
    }

    public ArrayList<Resource> getResources() {return resources;}
    public boolean changeResourceAmount(ResourceEnum type, int amount) {
        return ResourceMakerFuncs.changeOrAddResource(this.resources, type, amount);
    }
    public int getResourceAmount(ResourceEnum type) {
        for (Resource r : this.resources) {
            if(r.getType().equals(type))
                return r.getAmount();
        }
        return 0;
    }
}
