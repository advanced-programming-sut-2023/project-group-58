package view.enums;

import model.buildings.BuildingEnum;

public enum GameControllerOut {
    INVALID_NUMBER_INPUT("Number should be from -2 to 2!"),
    NO_FOOD_NO_RATE_CHANGE("You cannot change food rate; not until you provide some food first"),
    SUCCESSFULLY_CHANGED_FOODRATE("Food rate changed successfully"),
    SUCCESSFULLY_CHANGED_TAXRATE("Tax rate changed successfully"),
    NO_GOLD_NO_RATE_CHANGE("You cannot change tax rate; not until you provide some gold first"),
    CANNOT_ADD_UNIT_FROM_HERE("You cannot doThePurchase/train this type of troop in this building"),
    NOT_ENOUGH_GOLD("I'm afraid you cannot afford the cost. Get some gold!"),
    SUCCESSFULLY_CREATED_UNIT("Unit created successfully"),
    NOT_ENOUGH_WEAPON("There is not enough weapon in the storage to add that unit"),
    INVALID_COORDINATES("The coordinates you entered are invalid"),
    NO_BUILDING("There are no buildings in this location"),
    NOT_YOURS("Buildings on this spot aren't yours, I'm afraid"),
    SUCCESSFULLY_SELECTED_BUILDING("Building selected with type: "),
    DROP("You should enter map meu to \"drop\" buildings and units"),
    ;
    private String content;


    GameControllerOut(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public GameControllerOut manipulateSelectBuilding(BuildingEnum name) {
        this.content += name;
        return this;
    }
}
