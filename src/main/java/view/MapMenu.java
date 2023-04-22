package view;

import java.util.regex.Matcher;

public class MapMenu {
    public void run(){
        while (true){
            String command = ScanMatch.getScanner().nextLine();
            Matcher matcher;
            if (command.equals("back")){
                //todo: has not connect ed it to game menu yet.
                System.out.println("Your are in the game menu");
                return;
            }
            //else if(comma)
        }
    }
}
