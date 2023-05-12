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
    NOT_ENOUGH_WEAPON("There is not enough weapons in the storage to add that unit\nwE dOn'T hAve ThE neCCessAry AmOunT oF wEapONs"),
    INVALID_COORDINATES("The coordinates you entered are invalid"),
    NO_BUILDING("There are no buildings in this location"),
    NO_UNIT("You don't have any units in this location"),
    NOT_YOURS("Buildings on this spot aren't yours, I'm afraid"),
    SUCCESSFULLY_SELECTED_BUILDING("Building selected with type: "),
    SUCCESSFULLY_SELECTED_UNIT("Unit selected successfully"),
    DROP("You should enter map meu to \"drop\" buildings and units"),
    INVALID_INPUT_FORMAT("Invalid input format"),
    ZERO("What do you want mate? An army of ghosts? Try again with more people!"),
    NOT_ENOUGH_PEOPLE("Not enough people to recruit"),
    WRONG_LOCATION("You are in the wrong location. Go to the relevant building"),
    ENEMIES_NEAR("We're under attack! Cannot repair the building now!"),
    NOT_ENOUGH_RESOURCES("You don't have enough resources"),
    FULL_HP("There is nothing to repair!"),
    SUCCESSFULLY_REPAIRED("Successfully repaired"),
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
