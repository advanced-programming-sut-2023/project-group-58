package model;

public class TradeItem {
    private String id;
    private User oneWhoRequests;
    private User oneWhoAnswersTheCall;
    private String Type;
    private int amount;
    private int price;
    private String message;
    private Boolean active;

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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
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
}
