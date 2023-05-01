package view.enums;

public enum GameControllerOut {
    INVALID_NUMBER_INPUT("Number should be from -2 to 2!"),
    NO_FOOD_NO_RATE_CHANGE("You cannot change food rate; not until you provide some food first"),
    SUCCESSFULLY_CHANGED_FOODRATE("Food rate changed successfully"),
    SUCCESSFULLY_CHANGED_TAXRATE("Tax rate changed successfully"),
    NO_GOLD_NO_RATE_CHANGE("You cannot change tax rate; not until you provide some gold first"),
    ;
    private String content;


    GameControllerOut(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }


}
