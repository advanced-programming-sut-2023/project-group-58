package controller.gameMenuControllers;

import model.Resource;
import model.User;
import view.enums.GameControllerOut;

public class GameController {
    private User CurrentUser;

    public User getCurrentUser() {
        return CurrentUser;
    }

    public void setCurrentUser(User currentUser) {
        CurrentUser = currentUser;
    }
    public String showPopularityFactors() {
        String ans = "";
        ans += "Food diversity: " + updateFoodDiversity(this.CurrentUser.getGovernance().getResource(),this.CurrentUser) + "\n";
        ans += "Food rate:      " + this.CurrentUser.getGovernance().getFoodRate() + "\n";
        ans += "Fear rate       " + this.CurrentUser.getGovernance().getFearRate() + "\n";
        ans += "Tax rate:       " + this.CurrentUser.getGovernance().getTaxRate();
        return ans;
    }

    public String showPopularity() {
        return "This is your popularity: " + this.CurrentUser.getGovernance().getPopularity();
    }

    public GameControllerOut setFoodRate(String rateNumber) {
        if(this.CurrentUser.getGovernance().getResource().getApples() == 0 &&
           this.CurrentUser.getGovernance().getResource().getBread() == 0 &&
           this.CurrentUser.getGovernance().getResource().getCheese() == 0 &&
           this.CurrentUser.getGovernance().getResource().getMeat() == 0)
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
        public int updateFoodDiversity(Resource resource, User owner) {
        int nom = 0;
        if(resource.getMeat() > 0) nom++;
        if(resource.getApples() > 0) nom++;
        if(resource.getBread() > 0) nom++;
        if(resource.getCheese() > 0) nom++;
        return nom;
    }
}
