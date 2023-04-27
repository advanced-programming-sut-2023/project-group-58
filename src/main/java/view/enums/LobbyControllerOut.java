package view.enums;

public enum LobbyControllerOut {
    USERNAME_NOT_FOUND("Username not found"),
    PLAYER_ALREADY_IN("This user already joined lobby"),
    SUCCESSFULLY_JOINED("User joined lobby"),
    REMOVING_YOURSELF("You cannot remove yourself, because your are owner of lobby"),
    SUCCESSFULLY_REMOVED_USER(" removed successfully"),
    NO_SUCH_USER_IN_LOBBY("There isn't any user with entered username in the lobby"),
    ;
    private String content;

    LobbyControllerOut(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public LobbyControllerOut manipulateRemovingFormat(String firstHalf) {
        String secondHalf = this.content;
        this.content = firstHalf + secondHalf;
        return this;
    }
}