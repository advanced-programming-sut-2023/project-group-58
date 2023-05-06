package view.enums;

public enum ShopControllerOut {
    INVALID_INPUT_FORMAT("Failed: invalid input format"),
    INVALID_ITEM("There is no such item in the store!"),
    NOT_ENOUGH_GOLD("I'm afraid you cannot afford this product. Get some gold!"),
    PROMPT_CONFIRMATION_FOR_PURCHASE("Are you sure you want to doThePurchase "),
    PROMPT_CONFIRMATION_FOR_SELL("Are you sure you want to sell "),
    ABORT_THE_MISSION("Aborting the mission. No problemo!"),
    NOT_ENOUGH_COMMODITY("We don't have that number of commodities in the stock"),
    SUCCESS("permission granted to do the doThePurchase"),
    ;
    private String content;

    ShopControllerOut(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }


}
