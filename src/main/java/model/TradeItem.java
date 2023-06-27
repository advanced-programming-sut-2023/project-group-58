package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TradeItem {

    private String lastDateUpdate;
    private String id;
    private User oneWhoRequests;
    private User oneWhoAnswersTheCall;
    private ResourceEnum type;
    private int amount;
    private int price;
    private String message;
    private Boolean active;
    private Boolean seenRequester = false;
    private Boolean seenAccepter = false;

    private void updateDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd at HH:mm: ");
        lastDateUpdate = now.format(formatter);
    }

    public TradeItem(String id, User oneWhoRequests, ResourceEnum type, int amount, int price, String message, Boolean active) {
        this.id = id;
        this.oneWhoRequests = oneWhoRequests;
        this.type = type;
        this.amount = amount;
        this.price = price;
        this.message = message;
        this.active = active;
        updateDate();
    }

    public String getId() {
        return id;
    }

    public User getOneWhoRequests() {
        return oneWhoRequests;
    }

    public User getOneWhoAnswersTheCall() {
        return oneWhoAnswersTheCall;
    }

    public void setOneWhoAnswersTheCall(User oneWhoAnswersTheCall) {
        this.oneWhoAnswersTheCall = oneWhoAnswersTheCall;
        updateDate();
    }

    public String getTypeName() {
        return this.type.getName();
    }

    public ResourceEnum getType() {
        return this.type;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        updateDate();
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
        updateDate();
    }

    public void setSeenRequester(Boolean notified) {
        seenRequester = notified;
    }

    public Boolean getSeenRequester() {
        return seenRequester;
    }

    public Boolean getSeenAccepter() {
        return seenAccepter;
    }

    public void setSeenAccepter(Boolean seenAccepter) {
        this.seenAccepter = seenAccepter;
    }

    public String getLastDateUpdate() {
        return lastDateUpdate;
    }
}
