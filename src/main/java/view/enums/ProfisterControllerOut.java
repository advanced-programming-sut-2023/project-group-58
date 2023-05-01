package view.enums;

public enum ProfisterControllerOut {
    //profister = profile + register
//ولید جاهای مختلفی استفاده شده. ولی استرینگش لزوما بهش ربطی نداره. فقط یه جا از استرینگش استفاده شده.
    VALID("Your password successfully changed"),
    USERNAME_TAKEN("This username is already taken"),
    USERNAME_INVALID_FORMAT("Username's format is invalid!"),
    SUCCESSFULLY_CHANGED_USERNAME("Username changed to "),
    SUCCESSFULLY_CHANGED_NICKNAME("Nickname changed to "),
    WRONG_PASSWORD("Password is wrong"),
    SHORT_PASSWORD("Password is too short!"),
    NOT_CAPITAL_PASSWORD("Password does not include capital letters!"),
    NOT_SMALL_PASSWORD("Password does not include small letters!"),
    NOT_NUMBERS_PASSWORD("Password does not include numbers!"),
    NOT_SYMBOLS_PASSWORD("Password does not include symbols!"),
    NOT_NEW_PASSWORD("Please enter a new password!"),
    SUCCESSFULLY_ENTERED_PASS_TO_CHANGE_IT("Please enter your new password again"),
    SECOND_CHANCE_FOR_REENTERING_NEWPASSWORD("Please enter your new password again, CORRECTLY!"),
    SECOND_CHANCE_WAISTED("Failed: The confirmation was not entered correctly"),
    EMAIL_TAKEN("This email address is already used"),
    EMAIL_INVALID_FORMAT("Email format is not valid!"),
    SUCCESSFULLY_CHANGED_EMAIL("Your Email changed to "),
    EMPTY_FIELDS("Make sure to fill all the essential fields and try again!"),
    SLOGAN_AND_NO_SLOGAN("You either enter the slogan field, or you don't!"),
    SUGGESTING_USERNAME("This username is already taken. would like to use "),
    SUGGESTING_PASSWORD("Your random password is: "),
    FAILED("Mission failed."),
    INVALID_INPUT_FORMAT("Failed: invalid input format"),
    SUCCESSFULLY_REGISTERED("Registration successful.\nWelcome to the club, mate!"),
    INVALID_NEW_COORDINATES("Mission failed: invalid coordinates after moving"),
    RE_ENTER_PASSWORD("Please re-enter password correctly"),
    NOT_ENOUGH_RESOURCES("I'm afraid you don't have the resources necessary for this building"),
    NOT_A_VALID_PLACE("This is not the spot to put the building. Consider changing the location"),
    SUCCESSFULLY_ADDED_BUILDING("Building added successfully!"),
    UCCESSFULLY_ADDED_UNIT("Unit added successfully!"),
    ;
    private String content;

    ProfisterControllerOut(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public ProfisterControllerOut manipulateTheEnd(String secondHalf) {
        this.content += secondHalf + " successfully";
        return this;
    }

    public ProfisterControllerOut manipulateSuggestedUsername(String username) {
        this.content += username + " instead?\nType y for yes and n for no";
        return this;
    }

    public ProfisterControllerOut manipulateFinalCreationMessage(String slogan) {
        this.content += "\nBy the way, your \"random\" slogan is: " + slogan;
        return this;
    }
}
