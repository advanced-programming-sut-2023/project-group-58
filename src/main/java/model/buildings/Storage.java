package model.buildings;

import model.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

public class Storage extends Building {
    private ArrayList<Asset> storedGoods;
    private int capacity;
    private HashMap<?, Integer> countOfAsset;

    public Storage(BuildingEnum type, Governance owner, int direction) {
        super(type, owner, direction);
        this.capacity = 50;
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
}
