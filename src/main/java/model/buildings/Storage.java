package model.buildings;

import model.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

public class Storage extends Building {
    private ArrayList<Asset> storedGoods;
    private int capacity;
    private HashMap<?, Integer> countOfAsset;

    public Storage(BuildingEnum type, User owner, int direction) {
        super(type, owner, direction);
        this.capacity = 600;
        switch (type){
            case STOCKPILE:
                newStockpileResource();
                break;
            case ARMOURY:
                newArmouryWeapon();
                break;
            case GRANARY:
                newGranaryFood();
                break;
        }
    }
    private void newStockpileResource(){
        HashMap<ResourceEnum, Integer> count = new HashMap<>();
        EnumSet<ResourceEnum> resourceEnums = EnumSet.allOf(ResourceEnum.class);
        ArrayList<Asset> list = new ArrayList<>(resourceEnums.size());
        for (ResourceEnum s : resourceEnums){
            list.add(new Resource(s, 0));
            count.put(s, 0);
        }
        storedGoods = list;
        countOfAsset = count;
    }
    private void newArmouryWeapon(){
        HashMap<WeaponEnum, Integer> count = new HashMap<>();
        EnumSet<WeaponEnum> weaponEnums = EnumSet.allOf(WeaponEnum.class);
        ArrayList<Asset> list = new ArrayList<>(weaponEnums.size());
        for (WeaponEnum s : weaponEnums){
            list.add(new Weapon(0, s));
            count.put(s, 0);
        }
        storedGoods = list;
        countOfAsset = count;
    }
    private void newGranaryFood(){
        HashMap<FoodEnum, Integer> count = new HashMap<>();
        EnumSet<FoodEnum> foodEnums = EnumSet.allOf(FoodEnum.class);
        ArrayList<Asset> list = new ArrayList<>(foodEnums.size());
        for (FoodEnum s : foodEnums){
            list.add(new Food(0, s));
            count.put(s, 0);
        }
        storedGoods = list;
        countOfAsset = count;
    }
    public void changeResource(ResourceEnum resourceEnum, int amount){
        HashMap <ResourceEnum, Integer> hash = (HashMap<ResourceEnum, Integer>) countOfAsset;
        int temp = countOfAsset.get(resourceEnum);
        hash.replace(resourceEnum, temp+amount);
        countOfAsset = hash;
        for (Asset s : storedGoods){
            Resource t = (Resource) s;
            if (t.getType().equals(resourceEnum)){
                t.addAsset(amount);
                break;
            }
        }
    }
    public void changeWeapon(WeaponEnum weaponEnum, int amount){
        HashMap<WeaponEnum, Integer> hash = (HashMap<WeaponEnum, Integer>) countOfAsset;
        int temp = countOfAsset.get(weaponEnum);
        hash.replace(weaponEnum, temp+amount);
        countOfAsset = hash;
        for (Asset s : storedGoods){
            Weapon w = (Weapon) s;
            if (w.getType().equals(weaponEnum)){
                w.addAsset(amount);
                break;
            }
        }
    }
    public void changeFood(FoodEnum foodEnum, int amount){
        HashMap<FoodEnum, Integer> hash = (HashMap<FoodEnum, Integer>) countOfAsset;
        int temp = countOfAsset.get(foodEnum);
        hash.replace(foodEnum, temp+amount);
        countOfAsset = hash;
        for (Asset s : storedGoods){
            Food f = (Food) s;
            if (f.getType().equals(foodEnum)){
                f.addAsset(amount);
                break;
            }
        }
    }
    public int getResource(ResourceEnum resourceEnum){
        return countOfAsset.get(resourceEnum);
    }
    public int getWeapon(WeaponEnum weaponEnum){
        return countOfAsset.get(weaponEnum);
    }
    public int getFood(FoodEnum foodEnum){
        return countOfAsset.get(foodEnum);
    }
}
