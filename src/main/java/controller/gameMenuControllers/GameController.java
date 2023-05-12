package controller.gameMenuControllers;

import controller.CommonController;
import model.Map;
import model.ResourceEnum;
import model.User;
import model.buildings.Building;
import view.enums.GameControllerOut;

public class GameController {
    private User CurrentUser;
    private int xCoor;
    private int yCoor;
    private Map selectedMap;
    private Building selectedBuilding;

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
            //todo: assigning them one by one is stupid. use math. for god's sake.
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
    public GameControllerOut selectBuilding(String data) {
        extractDataxandy(data);
        if(!validateCoordinates(selectedMap.getLength(), selectedMap.getWidth()))
            return GameControllerOut.INVALID_COORDINATES;
        if(selectedMap.getTile(yCoor,xCoor).getBuildings().size() == 0)
            return GameControllerOut.NO_BUILDING;
        boolean exist = false;
        for (Building building : selectedMap.getTile(yCoor, xCoor).getBuildings()) {
            if(building.getOwner().getUsername().equals(this.CurrentUser.getUsername())) {
                exist = true;
                this.selectedBuilding = building;
                break;
            }
        }
        if(!exist)
            return GameControllerOut.NO_BUILDING;
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
        String countstr = CommonController.dataExtractor(data, "((?<!\\S)-x\\s+(?<wantedPart>(\\d+))(?<!\\s))");
        if()
        int count;
        return null;
    }
}
