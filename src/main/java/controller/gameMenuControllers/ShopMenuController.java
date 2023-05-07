package controller.gameMenuControllers;

import controller.CommonController;
import model.Resource;
import model.ResourceEnum;
import model.User;
import view.enums.ShopControllerOut;

import java.util.EnumSet;

public class ShopMenuController {
    private final User currentUser;
    private Resource merchandise;

    public ShopMenuController(User currentUser) {
        this.currentUser = currentUser;
    }

    public Resource getMerchandise() {return merchandise;}

    public User getCurrentUser() {
        return currentUser;
    }

    public String showPriceList(User currentUser) {
        String ans = new String();
        for (ResourceEnum value : ResourceEnum.values()) {
            if(!value.getName().equals(""))
                ans += value.getName() + ":\n       doThePurchase cost -> " + value.getBuyCost() + "\n        sell cost -> " +
                       value.getSellCost() + "\n        storage amount -> " + currentUser.getGovernance().getResourceAmount(value) + "\n";
        }
        return ans;
    }
    //doThePurchase and sell must be called immediately after extract item and amount
    public ShopControllerOut buy(String data, User master) {
        ShopControllerOut out = extractItemAndAmount(data);
        if(!out.equals(ShopControllerOut.SUCCESS))
            return out;
        if(this.merchandise.getType().equals(ResourceEnum.NULL))
            return ShopControllerOut.INVALID_ITEM;
        if(master.getGovernance().getGold() < this.merchandise.getType().getBuyCost() * this.merchandise.getAmount())
            return ShopControllerOut.NOT_ENOUGH_GOLD;

        //todo شرط داشتن فضای کافی چک نشده
        return ShopControllerOut.PROMPT_CONFIRMATION_FOR_PURCHASE;
    }

    public ShopControllerOut sell(String data, User master) {
        ShopControllerOut out = extractItemAndAmount(data);
        if(!out.equals(ShopControllerOut.SUCCESS))
            return out;
        if(this.merchandise.getType().equals(ResourceEnum.NULL))
            return ShopControllerOut.INVALID_ITEM;
        if(master.getGovernance().getResourceAmount(this.merchandise.getType()) < merchandise.getAmount())
            return ShopControllerOut.NOT_ENOUGH_COMMODITY;
        return ShopControllerOut.PROMPT_CONFIRMATION_FOR_SELL;
    }

    public ShopControllerOut extractItemAndAmount(String data) {
        if(CommonController.dataExtractor(data, "((?<!\\S)-i\\s+(?<wantedPart>([^-]+)(?<!\\s))").length() == 0 ||
           CommonController.dataExtractor(data, "((?<!\\S)-a\\s+(?<wantedPart>(\\d+)(?<!\\s))").length() == 0)
            return ShopControllerOut.INVALID_INPUT_FORMAT;
        String item   = CommonController.dataExtractor(data, "((?<!\\S)-i\\s+(?<wantedPart>([^-]+)(?<!\\s))").trim();
        int amount    = Integer.parseInt(CommonController.dataExtractor(data, "((?<!\\S)-a\\s+(?<wantedPart>(\\d+)(?<!\\s))").trim());
        ResourceEnum resourceItem = resourceFinder(item);
        this.merchandise = new Resource(resourceItem,amount);
        return ShopControllerOut.SUCCESS;
    }

    public ResourceEnum resourceFinder(String resource) {
        EnumSet<ResourceEnum> resourceEnums = EnumSet.allOf(ResourceEnum.class);
        for (ResourceEnum resourceEnum : resourceEnums) {
            if(resourceEnum.getName().equals(resource))
                return resourceEnum;
        }
        return ResourceEnum.NULL;
    }
    public void purchase() {
        currentUser.getGovernance().changeGold(-1 * merchandise.getType().getBuyCost() * merchandise.getAmount());
        currentUser.getGovernance().changeResourceAmount(merchandise.getType(), merchandise.getAmount());
    }

    public void retail() {
        currentUser.getGovernance().changeGold(merchandise.getType().getSellCost() * merchandise.getAmount());
        currentUser.getGovernance().changeResourceAmount(merchandise.getType(),-1 * merchandise.getAmount());
    }
}
