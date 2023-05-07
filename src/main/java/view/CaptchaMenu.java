package view;

import model.Captcha;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CaptchaMenu {
    public boolean run(){
        while (true){
            Captcha captcha = new Captcha();
            BufferedImage image = captcha.getCaptcha();
            for (int y = 0; y < 15; y++) {
                StringBuilder sb = new StringBuilder();
                for (int x = 0; x < captcha.getWidth(); x++) {

                    sb.append(image.getRGB(x, y) == -16777216 ? "." : "*");

                }
                if (sb.toString().trim().isEmpty()) {
                    continue;
                }
                System.out.println(sb);
            };
            System.out.println("please enter the number :");
            String command = ScanMatch.getScanner().nextLine();
            if (command.matches("back")) return false;
            if (command.matches(captcha.getNumber())) {
                System.out.println("correct");
                return true;
            }
            else System.out.println("input number is invalid!");
        }
    }
}
