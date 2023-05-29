package model;

import java.util.function.BiPredicate;

public class TradeItem {
    private String id;
    private User oneWhoRequests;
    private User oneWhoAnswersTheCall;
    private ResourceEnum type;
    private int amount;
    private int price;
    private String message;
    private Boolean active;
    private Boolean isNotified = false;

    public TradeItem(String id, User oneWhoRequests, ResourceEnum type, int amount, int price, String message, Boolean active) {
        this.id = id;
        this.oneWhoRequests = oneWhoRequests;
        this.type = type;
        this.amount = amount;
        this.price = price;
        this.message = message;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOneWhoRequests() {
        return oneWhoRequests;
    }

    public void setOneWhoRequests(User oneWhoRequests) {
        this.oneWhoRequests = oneWhoRequests;
    }

    public User getOneWhoAnswersTheCall() {
        return oneWhoAnswersTheCall;
    }

    public void setOneWhoAnswersTheCall(User oneWhoAnswersTheCall) {
        this.oneWhoAnswersTheCall = oneWhoAnswersTheCall;
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

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setNotified(Boolean notified) {
        isNotified = notified;
    }

    public Boolean getNotified() {
        return isNotified;
    }
}
