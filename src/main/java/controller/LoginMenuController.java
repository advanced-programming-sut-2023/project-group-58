package controller;

import model.User;
import view.Commands;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.simple.JSONObject;
public class LoginMenuController {

    int numberOfSlogans = 5;
    public void setUpSloganDataBase() {
        String allSlogans = "\"It's just a stupid game!\"\nI will rise, from the ashes... your ashes\nhey people!" +
                "I'm a noob and can't even pick a slogan.\nProbably got exams tomorrow, damn\ngame ON";
        File directory = new File(System.getProperty("user.dir") + "/DataBase");
        if (!directory.exists())
            directory.mkdir();

        try {
            FileWriter myWriter = new FileWriter("/DataBase/slogans.txt");
            myWriter.write(allSlogans);
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred while creating slogan database.");
            e.printStackTrace();
        }

    }

    public String createUser(String data, Scanner scanenr) throws IOException {

        //extracting inputs:
        //careful: getting double hyphens for email regex.
        //todo: Assuming THERE IS NO DOUBLE QUOTES BETWEEN TWO DOUBLE QUOTES
        //todo: cannot handle: -s "   -u moon " (spaces before -u between "")
        String username = dataExtractor(data, "((?<!\\S)-u\\s+(?<wantedPart>(\"[^\"]*\")|\\S*)(?<!\\s))").trim();
        String password = dataExtractor(data, "((?<!\\S)-p\\s+(?<wantedPart>(\"[^\"]*\")|\\S*)(?<!\\s))").trim();
        String email = dataExtractor(data, "((?<!\\S)--email\\s+(?<wantedPart>(\"[^\"]*\")|\\S*)(?<!\\s))").trim();
        String nickname = dataExtractor(data, "((?<!\\S)-n\\s+(?<wantedPart>(\"[^\"]*\")|\\S*)(?<!\\s))").trim();
        String slogan = dataExtractor(data, "((?<!\\S)-s\\s+(?<wantedPart>(\"[^\"]*\")|\\S*)(?<!\\s))").trim();

        String passwordConfirmation = new String();
        if(!password.trim().equals("random"))
            passwordConfirmation = getPasswordConfirmation(data);
        else{
            password = randomPasswordGenerator();
            System.out.println("Your random password is: " + password + "\nPlease re-enter your password here:");
            int counter = 9;
            while(true) {
                if(!scanenr.nextLine().trim().equals(password))
                    System.out.println("Wrong. Tries left: " + counter + "\nPlease enter your password: " + password);
                else break;
                counter--;
                if(counter == 0) return "Signup unsuccessful";
            }
        }

        //checking empty fields:
        if (username.length() == 0 || password.length() == 0 || email.length() == 0 ||
                nickname.length() == 0 || passwordConfirmation.length() == 0)
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
        if(findUserByUserNameOrEmail(username,"username")) {
            username = findSomethingSimilar(username);
            System.out.println("This username is already taken. would like to use " + username + " instead?");
            System.out.println("Type y for yes and n for no");
            if(scanenr.nextLine().equals("n")) return "Registration failed";
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
        if ( !password.matches(".*[^a-zA-Z0-9].*"))
            return "Password does not include symbols!";
        //todo: a better name for symbol?

        //check to see if password and its confirmation are the same:
        if(!password.equals(passwordConfirmation))
            return "Password and the confirmation do not match!";

        //check to see if this email is already taken:
        if(findUserByUserNameOrEmail(email,"email"))
            return "This email address is already used";

        //validating email:
        regex = "^[\\w|.]+@[\\w|.]+\\.[\\w|.]+$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(email);
        if(!matcher.find())
            return "Email format is not valid!";

        //getting the security guestion and answer:
        int questionNumber;
        String answer;
        System.out.println("Pick your security question: 1. What is my father’s name? 2. What" +
                "was my first pet’s name? 3. What is my mother’s last name?");
        while(true) {
            int counter = 9;
            if((matcher = Commands.getMatcher(scanenr.nextLine(), Commands.SECURITY_QUESTION_PICK)) == null)
                System.out.println("Invalid command. You have " + counter +
                        "chances to enter the order correctly.\n" +
                        "Your response should in form:\n" +
                        "question pick -q <question-number> -a <answer> -c <answerconfirm>");
            else {
                System.out.println("Please re-enter your answer:");
                questionNumber = Integer.parseInt(matcher.group("number").trim());
                answer = matcher.group("answerConfirm").trim();
                break;
            }
            counter--;
            if(counter == 0) return "Signup unsuccessful";
        }

        while(true) {
            int counter = 9;
            String probableAnswer = scanenr.nextLine().trim();
            if(!probableAnswer.equals(answer))
                System.out.println("Password recheck failed.\nYou have " + counter + " more chance.");
            else break;
            counter--;
            if(counter == 0) return"Signup unsuccessful";
        }

        //todo: make sure to add .trim() to all of them.
        //todo: update users at the beginning of the programme.

        boolean doWeHaveNewSlogan = true;
        //handling random slogan:
        if(slogan.equals("random")) {
            int pickSlogan = (int) ((int) numberOfSlogans * Math.random());
            slogan = Files.readAllLines(Paths.get("/DataBase/slogans.txt")).get(pickSlogan);
            System.out.println("Your slogan is " + slogan);
        }


        //now, we create a user, and save their info for later use.
        new User(username,password,nickname,email,slogan,questionNumber,answer).addUserToArrayList();
        //write to the file without overwriting:
        try {

            try (FileWriter writer = new FileWriter("userinfo.json")) {
                gson.toJson(staff, writer);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            String addingUser = generateString(username,password,nickname,email,slogan,questionNumber,answer);
            File log = new File(System.getProperty("user.dir") + "/DataBase/userInfo.txt");
            FileWriter fileWriter = new FileWriter(log, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(addingUser + "\n");
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred when storing user data.");
            e.printStackTrace();
        }
        return "Registration successful.\nWelcome to the club, mate!";
    }


    //Warning: can cause infinite loop:
    private String findSomethingSimilar(String username) {
        String randomStringWeAddEachTime;
        while(true) {
            randomStringWeAddEachTime = username + createRandomString();
            if(!findUserByUserNameOrEmail(randomStringWeAddEachTime,"username"))
                return randomStringWeAddEachTime;
        }
    }

    public static String createRandomString() {
        Random rand = new Random();
        int numberOfCharacterWeAdd = rand.nextInt(5)+1;
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
        return matcher.group("wantedPart");
    }

    public String getPasswordConfirmation(String string) {
        String regex = "(?<!\\S)-p\\s+((\"[^\"]*\")|\\S*)\\s+(?<wantedPart>(\"[^\"]*\")|\\S*)(?<!\\s)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        int endOfPassword = matcher.end();
        return string.substring(endOfPassword).trim();
    }

    public boolean findUserByUserNameOrEmail(String username, String type) {
        boolean isItUsed = false;
        try {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/DataBase/userInfo.txt"));
            String line = reader.readLine();
            while (line != null) {
                if(type.equals("username")) if (line.equals("username : " + username)) {
                    isItUsed = true;
                    break;
                }

                if(type.equals("email")) if (line.equals("email : " + username)) {
                    isItUsed = true;
                    break;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isItUsed;
    }

    private String randomPasswordGenerator() {
        String lowerCases = "abcdefghijklmnopqrstuvwxyz";
        String upperCases = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String symbols = "\\*\\.\\!\\@\\$\\%\\^\\&\\(\\)\\{\\}\\[\\]\\:\\;\\<\\>\\,\\?\\/\\~\\_\\+\\-\\=\\|";
        String randomPassword = new String();
        int randomLenght = (int) (6 * Math.random()) + 2;

        //first, we need to make sure password we make is valid:
        randomPassword += String.valueOf(lowerCases.charAt((int) (lowerCases.length() * Math.random())));
        randomPassword += String.valueOf(upperCases.charAt((int) (upperCases.length() * Math.random())));
        randomPassword += String.valueOf(symbols.charAt((int) (symbols.length() * Math.random())));
        randomPassword += String.valueOf(10 * Math.random());

        for (int i = 0; i < randomLenght; i++) {
            int rand = (int) (4 * Math.random());
            switch (rand) {
                case 0:
                    randomPassword += String.valueOf((int) 10 * Math.random());
                    break;
                case 1:
                    rand = (int) (lowerCases.length() * Math.random());
                    randomPassword += String.valueOf(lowerCases.charAt(rand));
                    break;
                case 2:
                    rand = (int) (upperCases.length() * Math.random());
                    randomPassword += String.valueOf(upperCases.charAt(rand));
                    break;
                case 3:
                    rand = (int) (symbols.length() * Math.random());
                    randomPassword += String.valueOf(symbols.charAt(rand));
                    break;
            }
        }

        //todo: random password MIGHT BE repetitive
        return randomPassword;
    }
}
