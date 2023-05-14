package controller.gameMenuControllers;

import controller.CommonController;
import model.Map;
import model.Resource;
import model.ResourceEnum;
import model.User;
import model.buildings.Building;
import model.buildings.BuildingEnum;
import model.units.Unit;
import model.units.UnitEnum;
import view.enums.GameControllerOut;
import view.enums.ProfisterControllerOut;

import java.util.ArrayList;
import java.util.HashMap;

public class GameController {
    private User CurrentUser;
    private int xCoor;
    private int yCoor;
    private Map selectedMap;
    private Building selectedBuilding;
    private int xOFSelectedBuilding;
    private int yOFSelectedBuilding;
    private int indexOFSelectedBuilding;
    private int xOriginOFSelectedUnit;
    private int yOriginOFSelectedUnit;

    public User getCurrentUser() {
        return CurrentUser;
    }

    public void setCurrentUser(User currentUser) {
        CurrentUser = currentUser;
    }
    public String showPopularityFactors() {
        String ans = "";
        ans += "Food diversity: " + getCurrentUser().getGovernance().getFoodDiversity() + "\n";
        ans += "Food rate:      " + this.CurrentUser.getGovernance().getFoodRate() + "\n";
        ans += "Fear rate       " + this.CurrentUser.getGovernance().getFearRate() + "\n";
        ans += "Tax rate:       " + this.CurrentUser.getGovernance().getTaxRate();
        return ans;
    }

    public String showPopularity() {
        return "This is your popularity: " + this.CurrentUser.getGovernance().getPopularity();
    }

    public GameControllerOut setFoodRate(String rateNumber) {
        if(this.CurrentUser.getGovernance().getResourceAmount(ResourceEnum.APPLE) == 0 &&
                this.CurrentUser.getGovernance().getResourceAmount(ResourceEnum.BREAD) == 0 &&
                this.CurrentUser.getGovernance().getResourceAmount(ResourceEnum.CHEESE) == 0 &&
                this.CurrentUser.getGovernance().getResourceAmount(ResourceEnum.MEAT) == 0)
            return GameControllerOut.NO_FOOD_NO_RATE_CHANGE;
        if(rateNumber == null || rateNumber.length() == 0 || rateNumber.trim().length() == 0)
            return GameControllerOut.INVALID_INPUT_FORMAT;
        int rate = Integer.parseInt(rateNumber.trim());
        switch (rate) {
            case -2:
                this.CurrentUser.getGovernance().changePopularity(-8);
                break;
            case -1:
                this.CurrentUser.getGovernance().changePopularity(-4);
                break;
            case 0:
                break;
            case 1:
                this.CurrentUser.getGovernance().changePopularity(4);
                break;
            case 2:
                this.CurrentUser.getGovernance().changePopularity(8);
                break;
            default:
                return GameControllerOut.INVALID_NUMBER_INPUT;
        }
        this.CurrentUser.getGovernance().changeFoodRate(rate);
        return GameControllerOut.SUCCESSFULLY_CHANGED_FOODRATE;
    }

    public String showFoodRate() {
        return "This is the food rate: " + this.CurrentUser.getGovernance().getFoodRate();
    }

    public GameControllerOut setTaxRate(String rateNumber) {
        if(this.CurrentUser.getGovernance().getGold() <= 0)
            return GameControllerOut.NO_GOLD_NO_RATE_CHANGE;
        int rate = Integer.parseInt(rateNumber.trim());
        switch (rate) {
            case -3:
                this.CurrentUser.getGovernance().changePopularity(7);
                break;
            case -2:
                this.CurrentUser.getGovernance().changePopularity(5);
                break;
            case -1:
                this.CurrentUser.getGovernance().changePopularity(3);
                break;
            case 0:
                this.CurrentUser.getGovernance().changePopularity(1);
                break;
            case 1:
                this.CurrentUser.getGovernance().changePopularity(-2);
                break;
            case 2:
                this.CurrentUser.getGovernance().changePopularity(-4);
                break;
            case 3:
                this.CurrentUser.getGovernance().changePopularity(-6);
                break;
            case 4:
                this.CurrentUser.getGovernance().changePopularity(-8);
                break;
            case 5:
                this.CurrentUser.getGovernance().changePopularity(-12);
                break;
            case 6:
                this.CurrentUser.getGovernance().changePopularity(-16);
                break;
            case 7:
                this.CurrentUser.getGovernance().changePopularity(-20);
                break;
            case 8:
                this.CurrentUser.getGovernance().changePopularity(-24);
                break;
            default:
                return GameControllerOut.INVALID_NUMBER_INPUT;
        }
        this.CurrentUser.getGovernance().changeTaxRate(rate);
        return GameControllerOut.SUCCESSFULLY_CHANGED_TAXRATE;
    }
    public String showTaxRate() {
        return "This is tax rate: " + this.CurrentUser.getGovernance().getTaxRate();
    }

    public boolean extractDataxandy(String data) {
        String x = CommonController.dataExtractor(data, "((?<!\\S)-x\\s+(?<wantedPart>(\\d+))(?<!\\s))");
        String y = CommonController.dataExtractor(data, "((?<!\\S)-y\\s+(?<wantedPart>(\\d+))(?<!\\s))");
        if(x.length() == 0 || y.length() == 0) return false;
        if(x.trim().length() == 0 || y.trim().length() == 0) return false;
        xCoor = Integer.parseInt(CommonController.dataExtractor(data, "((?<!\\S)-x\\s+(?<wantedPart>(\\d+))(?<!\\s))").trim());
        yCoor = Integer.parseInt(CommonController.dataExtractor(data, "((?<!\\S)-y\\s+(?<wantedPart>(\\d+))(?<!\\s))").trim());
        yCoor = selectedMap.getWidth() - 1 - yCoor;
        return true;
    }
    public boolean validateCoordinates(int mapLength, int mapWidth) {
        return xCoor >= 0 && xCoor <= mapWidth - 1 && yCoor >= 0 && yCoor <= mapLength - 1;
    }
    public String selectBuilding(String data) {
        if(!extractDataxandy(data))
            return GameControllerOut.INVALID_INPUT_FORMAT.getContent();
        if(!validateCoordinates(selectedMap.getLength(), selectedMap.getWidth()))
            return GameControllerOut.INVALID_COORDINATES.getContent();
        if(selectedMap.getTile(yCoor,xCoor).getBuildings().size() == 0)
            return GameControllerOut.NO_BUILDING.getContent();
        boolean exist = false;
        for(int i =0; i < selectedMap.getTile(yCoor, xCoor).getBuildings().size(); i++) {
            if(selectedMap.getTile(yCoor, xCoor).getBuildings().get(i)
                    .getOwner().getUsername().equals(this.CurrentUser.getUsername())) {
                exist = true;
                this.selectedBuilding = selectedMap.getTile(yCoor, xCoor).getBuildings().get(i);
                this.xOFSelectedBuilding = xCoor;
                this.yOFSelectedBuilding = yCoor;
                this.indexOFSelectedBuilding = i;
                break;
            }
        }
        xCoor = -1;
        yCoor = -1;
        if(!exist)
            return GameControllerOut.NO_BUILDING.getContent();
        else
            return GameControllerOut.SUCCESSFULLY_SELECTED_BUILDING.manipulateSelectBuilding(selectedBuilding.getType());
    }

    public GameController(User currentUser, Map selectedMap) {
        CurrentUser = currentUser;
        this.selectedMap = selectedMap;
    }

    public void setSelectedMap(Map selectedMap) {
        this.selectedMap = selectedMap;
    }

    public GameControllerOut createUnit(String data) {
        String type = CommonController.dataExtractor(data, "((?<!\\S)-x\\s+(?<wantedPart>(.+))(?<!\\s))");
        String countStr = CommonController.dataExtractor(data, "((?<!\\S)-x\\s+(?<wantedPart>(\\d+))(?<!\\s))");
        if(type.length() == 0 || countStr.length() == 0)
            return GameControllerOut.INVALID_INPUT_FORMAT;
        if(type.trim().length() == 0 || countStr.trim().length() == 0)
            return GameControllerOut.INVALID_INPUT_FORMAT;
        int count = Integer.parseInt(countStr.trim());
        type = type.trim();
        UnitEnum unitType = CommonController.unitTypeSpecifier(type);
        if(unitType == null)
            return GameControllerOut.INVALID_INPUT_FORMAT;
        if(count == 0)
            return GameControllerOut.ZERO;
        if(getCurrentUser().getGovernance().getGold() < unitType.getCost() * count)
            return GameControllerOut.NOT_ENOUGH_GOLD;
        if(!unitType.getWeaponType().equals(ResourceEnum.NULL))
            if(getCurrentUser().getGovernance().getResourceAmount(unitType.getWeaponType()) < count)
                return GameControllerOut.NOT_ENOUGH_WEAPON;
        if(getCurrentUser().getGovernance().getUnemployedPopulation() < count)
            return GameControllerOut.NOT_ENOUGH_PEOPLE;
        if(!checkPlace(unitType))
            return GameControllerOut.WRONG_LOCATION;
        getCurrentUser().getGovernance().changeGold(-1 * unitType.getCost() * count);
        if(!unitType.getWeaponType().equals(ResourceEnum.NULL))
            getCurrentUser().getGovernance().changeResourceAmount(unitType.getWeaponType(),-1 * count);
        Unit addingUnit = new Unit(getCurrentUser(),unitType,count, yOFSelectedBuilding, xOFSelectedBuilding);
        selectedMap.getTile(yOFSelectedBuilding, xOFSelectedBuilding).addUnitToTile(addingUnit);
        return GameControllerOut.SUCCESSFULLY_CREATED_UNIT;
    }

    private boolean checkPlace(UnitEnum unitType) {
        if(unitType.getName().equals("engineer"))
            return selectedBuilding.getType().equals(BuildingEnum.ENGINEERS_GUILD);
        else if(unitType.isArab() && selectedBuilding.getType().equals(BuildingEnum.MERCENARY_POST))
            return true;
        else return !unitType.isArab() && selectedBuilding.getType().equals(BuildingEnum.BARRACKS);
    }

    public GameControllerOut repair() {
        for(int i = -1; i < 2; i++)
            for(int j = -1; j < 2; j++)
                if(selectedMap.getTile(yOFSelectedBuilding + i, xOFSelectedBuilding + j)
                        .areEnemiesHere(getCurrentUser()))
                    return GameControllerOut.ENEMIES_NEAR;
        Resource neededResource = selectedBuilding.getType().getResource();
        if(getCurrentUser().getGovernance().getResourceAmount(neededResource.getType()) <
        neededResource.getAmount())
            return GameControllerOut.NOT_ENOUGH_RESOURCES;
        if(selectedBuilding.getHp() == selectedBuilding.getType().getOriginalHp())
            return GameControllerOut.FULL_HP;
        getCurrentUser().getGovernance().changeResourceAmount(neededResource.getType(), -1 * neededResource.getAmount());
        selectedMap.getTile(yOFSelectedBuilding, xOFSelectedBuilding).getBuildings().get(indexOFSelectedBuilding).resetHp();
        return GameControllerOut.SUCCESSFULLY_REPAIRED;
    }

    public GameControllerOut selectUnit(String data) {
        ArrayList<Integer> xOrigins = new ArrayList<>();
        ArrayList<Integer> yOrigins = new ArrayList<>();
        if(!extractDataxandy(data))
            return GameControllerOut.INVALID_INPUT_FORMAT;
        if(!validateCoordinates(selectedMap.getLength(), selectedMap.getWidth()))
            return GameControllerOut.INVALID_COORDINATES;
        ArrayList<Unit> separate = selectedMap.getTile(yCoor,xCoor).findYourUnits(getCurrentUser());
        if(separate == null)
            return GameControllerOut.NO_UNIT;
        Unit combined = separate.get(0);
        xOrigins.add(combined.getxOrigin());
        yOrigins.add(combined.getyOrigin());
        for(int i = 1; i < separate.size(); i++) {
            combined.addByUnit(separate.get(i));
            xOrigins.add(combined.getxOrigin());
            yOrigins.add(combined.getyOrigin());
        }
        //todo: enhance and simplify addbyunit
        //adding troops which are in a different tile, but are part of the unit:
        for(int i = 0; i < selectedMap.getWidth(); i++)
            for(int j = 0; j < selectedMap.getLength(); j++) {
                ArrayList<Unit> seperatedUnits = selectedMap.getTile(i,j).findYourUnits(getCurrentUser());
                for (Unit unit : seperatedUnits) {
                    if(matchPrimaryCoordinate(xOrigins,yOrigins,unit)) {
                        unit.setxOrigin(xCoor);
                        unit.setyOrigin(yCoor);
                    }
                }
            }
        combined.setxOrigin(xCoor);
        combined.setyOrigin(yCoor);
        xOriginOFSelectedUnit = xCoor;
        yOriginOFSelectedUnit = yCoor;
        selectedMap.getTile(yCoor,xCoor).unifyYourUnits(combined);
        return GameControllerOut.SUCCESSFULLY_SELECTED_UNIT;
    }

    public boolean matchPrimaryCoordinate(ArrayList<Integer> x, ArrayList<Integer> y, Unit unit) {
        for(int i = 0; i < x.size(); i++)
            if(unit.getxOrigin() == x.get(i) && unit.getyOrigin() == y.get(i))
                return true;
        return false;
    }

    public Building getSelectedBuilding() {
        return selectedBuilding;
    }

    public GameControllerOut setFearRate(String rateNumber) {
        if(rateNumber == null || rateNumber.length() == 0 || rateNumber.trim().length() == 0)
            return GameControllerOut.INVALID_INPUT_FORMAT;
        int rate = Integer.parseInt(rateNumber.trim());
        if(rate < -5 || rate > 5)
            return GameControllerOut.INVALID_FEAR_INPUT;
        getCurrentUser().getGovernance().changeFearRate(rate);
        //more fear, less popularity:
        getCurrentUser().getGovernance().changePopularity(rate * -2);
        return GameControllerOut.SUCCESSFULLY_CHANGED_FEAR_RATE;
    }


    public String showFoodList() {
        String ans = "";
        int count = getCurrentUser().getGovernance().getResourceAmount(ResourceEnum.MEAT);
        if(count > 0)
            ans += "Meat   -> " + count + "\n";
        count = getCurrentUser().getGovernance().getResourceAmount(ResourceEnum.BREAD);
        if(count > 0)
            ans += "Bread  -> " + count + "\n";
        count = getCurrentUser().getGovernance().getResourceAmount(ResourceEnum.CHEESE);
        if(count > 0)
            ans += "Cheese -> " + count + "\n";
        count = getCurrentUser().getGovernance().getResourceAmount(ResourceEnum.APPLE);
        if(count > 0)
            ans += "Apple  -> " + count + "\n";
        return ans;
    }

    public GameControllerOut setState(String data) {
        if(!extractDataxandy(data))
            return GameControllerOut.INVALID_INPUT_FORMAT;
        String state = CommonController.dataExtractor(data, "((?<!\\S)-s\\s+(?<wantedPart>\\S+)(?<!\\s))");
        if(state == null || state.length() == 0 || state.trim().length() == 0)
            return GameControllerOut.INVALID_INPUT_FORMAT;
        state = state.trim();
        if(!state.equals("standing") && !state.equals("defensive") && !state.equals("offensive]"));
        if(!this.selectedMap.getTile(yCoor,xCoor).changeState(state,getCurrentUser()))
            return GameControllerOut.NO_UNIT;
        return GameControllerOut.SUCCESSFULLY_CHANGRD_UNIT_STATE;
    }
}
