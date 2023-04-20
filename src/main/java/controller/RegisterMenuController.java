package controller;

import com.google.gson.*;
import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import view.Commands;
import view.ScanMatch;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterMenuController {

    int numberOfSlogans = 5;
    String userInfoAddress = System.getProperty("user.dir") + "/DataBase/userInfo.json";

    public void createFileWhenNecessary(String address) {
        File myFile = new File(address);
        if (!myFile.exists()) {
            try {
                myFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void setUpUserInfo() throws IOException {

        //file should not be overwritten if it's not empty. first, we check if it exists:
        File userInfo = new File(System.getProperty("user.dir") + "/DataBase/userInfo.json");
        if(!userInfo.exists()) {
            userInfo.createNewFile();}
        else {
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/DataBase/userInfo.json"));
            if (br.readLine() != null) {
                return;
            }
        }

        JSONObject userDetails = new JSONObject();
        userDetails.put("username", "");
        userDetails.put("password", "");
        userDetails.put("nickname", "");
        userDetails.put("email", "");
        userDetails.put("slogan", "");
        userDetails.put("securityQuestion", 0);
        userDetails.put("securityAnswer", "");
        userDetails.put("highScore", -1);

        JSONObject userObject = new JSONObject();
        userObject.put("user", userDetails);

        JSONArray userList = new JSONArray();
        userList.add(userObject);

        FileWriter file = new FileWriter(System.getProperty("user.dir") + "/DataBase/userInfo.json");
        try {
            file.write(userList.toJSONString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            file.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUpSloganDataBase() {
        String allSlogans = "\"It's just a stupid game!\"\nI will rise, from the ashes... your ashes\nhey people!" +
                "I'm a noob and can't even pick a slogan.\nProbably got exams tomorrow, damn\ngame ON";

        File directory = new File(System.getProperty("user.dir") + "/DataBase");
        if (!directory.exists())
            directory.mkdir();
        try {
            FileWriter myWriter = new FileWriter("DataBase/slogans.txt");
            myWriter.write(allSlogans);
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred while creating slogan database.");
            e.printStackTrace();
        }

    }

    public String createUser(String data) throws IOException {

        //extracting inputs:
        //careful: getting double hyphens for email regex.
        //todo: Assuming THERE IS NO DOUBLE QUOTES BETWEEN TWO DOUBLE QUOTES
        //todo: cannot handle: -s "   -u moon " (spaces before -u between "")
        String username = dataExtractor(data, "((?<!\\S)-u\\s+(?<wantedPart>(\"[^\"]*\")|\\S*)(?<!\\s))").trim();
        String password = dataExtractor(data, "((?<!\\S)-p\\s+(?<wantedPart>(\"[^\"]*\")|\\S*)(?<!\\s))").trim();
        String email = dataExtractor(data, "((?<!\\S)--email\\s+(?<wantedPart>(\"[^\"]*\")|\\S*)(?<!\\s))").trim();
        String nickname = dataExtractor(data, "((?<!\\S)-n\\s+(?<wantedPart>(\"[^\"]*\")|\\S*)(?<!\\s))").trim();
        String slogan = dataExtractor(data, "((?<!\\S)-s\\s+(?<wantedPart>(\"[^\"]*\")|\\S*)(?<!\\s))").trim();

        if (!password.trim().equals("random")) {
            if (!getPasswordConfirmation(data)) return "The confirmation is not entered correctly";
        } else {
            password = randomPasswordGenerator();
            System.out.println("Your random password is: " + password + "\nPlease re-enter your password here:");
            int counter = 9;
            while (true) {
                if (!ScanMatch.getScanner().nextLine().trim().equals(password))
                    System.out.println("Wrong. Tries left: " + counter + "\nPlease enter your password: " + password);
                else break;
                counter--;
                if (counter == 0) return "Signup unsuccessful";
            }
        }

        //checking empty fields:
        if (username.length() == 0 || password.length() == 0 || email.length() == 0 ||
                nickname.length() == 0)
            return "Make sure to fill all the essential fields and try again!";
        String regex = "(?<!\")\\s+-s\\s+(?!\")";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        if (matcher.find() && slogan.length() == 0)
            return "You either enter the slogan field, or you don't!";

        //checking username format:
        if (username.matches(".*[\\W+].*"))
            return "Username's format is invalid!";

        //checking to see if this username already exists:
        if (isUsernameOrEmailAlreadyTaken(System.getProperty("user.dir") + "/DataBase/userInfo.json", username, "username")) {
            username = findSomethingSimilar(username);
            System.out.println("This username is already taken. would like to use " + username + " instead?");
            System.out.println("Type y for yes and n for no");
            if (ScanMatch.getScanner().nextLine().equals("n")) return "Registration failed";
        }

        //checking to see if password is weak:
        if (password.length() < 6)
            return "Password is too short!";
        if (!password.matches(".*[A-Z].*"))
            return "Password does not include capital letters!";
        if (!password.matches(".*[a-z].*"))
            return "Password does not include small letters!";
        if (!password.matches(".*[0-9].*"))
            return "Password does not include numbers!";
        if (!password.matches(".*[^a-zA-Z0-9].*"))
            return "Password does not include symbols!";
        //todo: a better name for symbol?


        //check to see if this email is already taken:
        if (isUsernameOrEmailAlreadyTaken(System.getProperty("user.dir") + "/DataBase/userInfo.json", email, "email"))
            return "This email address is already used\nRegistration failed";
        //validating email:
        regex = "^[\\w|.]+@[\\w|.]+\\.[\\w|.]+$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(email);
        if (!matcher.find())
            return "Email format is not valid!";

        //getting the security guestion and answer:
        int questionNumber;
        String answer;
        System.out.println("Pick your security question: 1. What is my father’s name? 2. What" +
                "was my first pet’s name? 3. What is my mother’s last name?\n" +
                "Your response should in form:\n" +
                "question pick -q <question-number> -a <answer> -c <answerconfirm>");
        int counter = 9;
        while (true) {
            if ((matcher = Commands.getMatcher(ScanMatch.getScanner().nextLine(), Commands.SECURITY_QUESTION_PICK)) == null)
                System.out.println("Invalid command. You have " + counter +
                        " chances to enter the order correctly.\n" +
                        "Your response should in form:\n" +
                        "question pick -q <question-number> -a <answer> -c <answerconfirm>");
            else {
                questionNumber = Integer.parseInt(matcher.group("number").trim());
                answer = matcher.group("answerConfirm").trim();
                break;
            }
            counter--;
            if (counter == 0) return "Signup unsuccessful";
        }

        //todo: make sure to add .trim() to all of them.
        //todo: update users at the beginning of the programme.

        //handling random slogan:
        if (slogan.equals("random")) {
            int pickSlogan = (int) (numberOfSlogans * Math.random());
            slogan = Files.readAllLines(Paths.get("/DataBase/slogans.txt")).get(pickSlogan);
            System.out.println("Your slogan is " + slogan);
        }

        try {
            password = encryptPassword(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        //now, we create a user, and save their info for later use.
        User addingUser = new User(username, password, nickname, email, slogan, questionNumber, answer, 0);
        addingUser.addUserToArrayList();

        JSONArray usersList = readFromAJson(System.getProperty("user.dir") + "/DataBase/userInfo.json");
        JSONObject userDetails = new JSONObject();
        userDetails.put("username", username);
        userDetails.put("password", password);
        userDetails.put("nickname", nickname.substring(1, nickname.length()-1));
        userDetails.put("email", email);
        userDetails.put("slogan", slogan);
        userDetails.put("securityQuestion", questionNumber);
        userDetails.put("securityAnswer", answer);
        userDetails.put("highScore", 0);


        JSONObject eachUserAsObject = new JSONObject();
        eachUserAsObject.put("user", userDetails);
        //Add the new onw to the list
        usersList.add(eachUserAsObject);

        //now, we should add the json array to our file.
        FileWriter file = new FileWriter(System.getProperty("user.dir") + "/DataBase/userInfo.json");
        try {
            file.write(usersList.toJSONString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            file.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        file.close();

        return "Registration successful.\nWelcome to the club, mate!";
    }

    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);
        return encoded;
    }


    //Warning: can cause infinite loop:
    private String findSomethingSimilar(String username) {
        String randomStringWeAddEachTime;
        while (true) {
            randomStringWeAddEachTime = username + createRandomString();
            if (!isUsernameOrEmailAlreadyTaken(System.getProperty("user.dir") + "/DataBase/userInfo.json",randomStringWeAddEachTime, "username"))
                return randomStringWeAddEachTime;
        }
    }

    //todo: save the address somewhere.
    public static String createRandomString() {
        Random rand = new Random();
        int numberOfCharacterWeAdd = rand.nextInt(5) + 1;
        String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_";
        StringBuilder random = new StringBuilder();
        Random rnd = new Random();
        while (random.length() < numberOfCharacterWeAdd) {
            int index = (int) (rnd.nextFloat() * randomString.length());
            random.append(randomString.charAt(index));
        }
        return random.toString();
    }

    public String dataExtractor(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        if (!matcher.find()) return "";
        return matcher.group("wantedPart");
    }

    public boolean getPasswordConfirmation(String string) {
        String regex = "(?<!\\S)-p\\s+(?<originalPassword>(\"[^\"]*\")|\\S*)\\s+(?<confirmation>(\"[^\"]*\")|\\S*)(?<!\\s)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        matcher.find();
        String pass = matcher.group("originalPassword").trim();
        String conf = matcher.group("confirmation").trim();
        if (pass.equals(conf)) return true;
        else return false;
    }


    private String randomPasswordGenerator() {
        String lowerCases = "abcdefghijklmnopqrstuvwxyz";
        String upperCases = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String symbols = "\\*\\.\\!\\@\\$\\%\\^\\&\\(\\)\\{\\}\\[\\]\\:\\;\\<\\>\\,\\?\\/\\~\\_\\+\\-\\=\\|";
        StringBuilder randomPassword = new StringBuilder();
        int randomLength = (int) (6 * Math.random()) + 2;

        //first, we need to make sure password we make is valid:
        randomPassword.append(lowerCases.charAt((int) (lowerCases.length() * Math.random())));
        randomPassword.append(upperCases.charAt((int) (upperCases.length() * Math.random())));
        randomPassword.append(symbols.charAt((int) (symbols.length() * Math.random())));
        randomPassword.append(10 * Math.random());

        for (int i = 0; i < randomLength; i++) {
            int rand = (int) (4 * Math.random());
            switch (rand) {
                case 0:
                    randomPassword.append(10 * Math.random());
                    break;
                case 1:
                    rand = (int) (lowerCases.length() * Math.random());
                    randomPassword.append(lowerCases.charAt(rand));
                    break;
                case 2:
                    rand = (int) (upperCases.length() * Math.random());
                    randomPassword.append(upperCases.charAt(rand));
                    break;
                case 3:
                    rand = (int) (symbols.length() * Math.random());
                    randomPassword.append(symbols.charAt(rand));
                    break;
            }
        }
        //todo: random password MIGHT BE repetitive
        return randomPassword.toString();
    }

    public JSONArray readFromAJson(String address) {
        JSONArray userList = new JSONArray();
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(address)) {
            Object obj = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;
            for (Object o : jsonArray)
                userList.add(o);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public boolean isUsernameOrEmailAlreadyTaken(String address, String string, String key) {
        Gson gson = new Gson();
        JsonArray jsonArray = null;
        try {
            jsonArray = gson.fromJson(new FileReader(address), JsonArray.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            if (key.equals("username")) {
                if (jsonObject.get("user").getAsJsonObject().get("username").toString().equals("\"" + string + "\""))
                    return true;
            } else if (key.equals("email")) {
                if (jsonObject.get("user").getAsJsonObject().get("email").toString().equalsIgnoreCase("\"" + string + "\""))
                    return true;
            }
        }
        return false;
    }

}