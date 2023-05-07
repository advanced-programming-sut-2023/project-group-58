package view.enums;

public enum ShopAndTradeControllerOut {
    INVALID_INPUT_FORMAT("Failed: invalid input format"),
    INVALID_ITEM("There is no such item in the store!"),
    NOT_ENOUGH_GOLD("I'm afraid you cannot afford this product. Get some gold!"),
    CANNOT_AFFORD_TRADE("You don't have enough gold to keep your end of the bargain. No trade!"),
    PROMPT_CONFIRMATION_FOR_PURCHASE("Are you sure you want to doThePurchase "),
    PROMPT_CONFIRMATION_FOR_SELL("Are you sure you want to sell "),
    ABORT_THE_MISSION("Aborting the mission. No problemo!"),
    NOT_ENOUGH_COMMODITY("We don't have that number of commodities in the stock"),
    SUCCESS_FOR_SHOP("permission granted to do the doThePurchase"),
    SUCCESS_FOR_TRADE("Trade done successfully"),
    REQUEST_ADDED("Your request has been successfully added to the list"),
    TRADE_NOT_FOUND("No trade was found with that id"),
    ;
    private String content;

    ShopAndTradeControllerOut(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }


}
