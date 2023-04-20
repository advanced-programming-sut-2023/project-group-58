package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class LoginMenuController {
    public void exractUserData(String username, String address) {
        //address here is: System.getProperty("user.dir") + "/DataBase/userInfo.json"
        Gson gson = new Gson();
        JsonArray jsonArray = null;
        try {
            jsonArray = gson.fromJson(new FileReader(address), JsonArray.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            if (jsonObject.get("user").getAsJsonObject().get("username").toString().equals("\"" + username + "\"")) {
                String password = jsonObject.get("user").getAsJsonObject().get("password").toString();
            }
        }
    }

    //MUST be used when writing "newUserDetails.put(...)"
    public static String correctDoubleQuotation(String input) {
        if(input.charAt(0) == '"' && input.charAt(input.length()-1) == '"' && input.indexOf(' ') >= 0)
            return input.substring(1,input.length()-1);
        return input;
    }
}