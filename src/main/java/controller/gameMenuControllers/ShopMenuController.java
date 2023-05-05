package controller.gameMenuControllers;

import controller.CommonController;
import model.Resource;
import model.ResourceEnum;
import model.User;
import view.enums.ShopControllerOut;

import java.util.EnumSet;

public class ShopMenuController {
    private final User currentUser;
    private Resource buyProduct;

    public ShopMenuController(User currentUser) {
        this.currentUser = currentUser;
    }

    public Resource getBuyProduct() {return buyProduct;}

    public User getCurrentUser() {
        return currentUser;
    }

    public String showPriceList(User currentUser) {
        String ans = new String();
        for (ResourceEnum value : ResourceEnum.values()) {
            if(!value.getName().equals(""))
                ans += value.getName() + ":\n       buy cost -> " + value.getBuyCost() + "\n        sell cost -> " +
                       value.getSellCost() + "\n        storage amount -> " + currentUser.getGovernance().getResourceAmount(value) + "\n";
        }
        return ans;
    }
    public ShopControllerOut buy(String data, User master) {
        if(CommonController.dataExtractor(data, "((?<!\\S)-i\\s+(?<wantedPart>(\\S+)(?<!\\s))").length() == 0 ||
           CommonController.dataExtractor(data, "((?<!\\S)-a\\s+(?<wantedPart>(\\d+)(?<!\\s))").length() == 0)
            return ShopControllerOut.INVALID_INPUT_FORMAT;

        String item   = CommonController.dataExtractor(data, "((?<!\\S)-x\\s+(?<wantedPart>(\\d+)(?<!\\s))").trim();
        int amount    = Integer.parseInt(CommonController.dataExtractor(data, "((?<!\\S)-y\\s+(?<wantedPart>(\\d+)(?<!\\s))").trim());
        ResourceEnum resourceItem = resourceFinder(item);
        if(resourceItem.equals(ResourceEnum.NULL))
            return ShopControllerOut.INVALID_ITEM;
        if(master.getGovernance().getGold() < resourceItem.getBuyCost() * amount)
            return ShopControllerOut.NOT_ENOUGH_GOLD;

        this.buyProduct = new Resource(resourceItem,amount);
        //todo شرط داشتن فضای کافی چک نشده
        return ShopControllerOut.PROMPT_CONFIRMATION_FOR_PURCHASE;
    }

    public ResourceEnum resourceFinder(String resource) {
        EnumSet<ResourceEnum> resourceEnums = EnumSet.allOf(ResourceEnum.class);
        for (ResourceEnum resourceEnum : resourceEnums) {
            if(resourceEnum.getName().equals(resource))
                return resourceEnum;
        }
        return ResourceEnum.NULL;
    }
    public void sell(String data, User currentUser) {

    }
    public void purchase() {
        currentUser.getGovernance().changeGold(-1 * buyProduct.getType().getBuyCost() * buyProduct.getAmount());
        currentUser.getGovernance().changeResourceAmount(buyProduct.getType(), buyProduct.getAmount());
    }
}
