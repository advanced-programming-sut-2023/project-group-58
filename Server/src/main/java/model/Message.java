package model;

import org.json.JSONObject;

public class Message {
    public String sender;
    public String text;
    public boolean Seen;

    public Message(String sender, String text)
    {
        this.sender = sender;
        this.text = text;
        Seen = false;
    }

    public JSONObject toJson()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sender", sender);
        jsonObject.put("text", text);
        jsonObject.put("Seen", Seen);
        return jsonObject;
    }

    public static Message fromJson(JSONObject jsonObject)
    {
        Message message =  new Message(jsonObject.get("sender").toString(), jsonObject.get("text").toString());
        message.setSeen((boolean) jsonObject.get("Seen"));
        return message;
    }

    public String getSender() {
        return sender;
    }


    public String getText() {
        return text;
    }

    public boolean isSeen() {
        return Seen;
    }

    public void setSeen(boolean seen) {
        Seen = seen;
    }
}
