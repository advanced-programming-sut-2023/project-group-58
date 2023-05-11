import controller.LoginMenuController;
import controller.RegisterMenuController;
import org.apache.commons.io.IOUtils;
import model.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import view.RegisterMenu;
import view.enums.LoginControllerOut;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.security.NoSuchAlgorithmException;

public class TestClass {
    @BeforeClass
    public static void settingUp() throws IOException {
        LoginMenuController.extractUserData();
        LoginMenuController.setUpStayedLogin();
    }
    @Test
    public void loginUserExist() throws IOException {
        LoginMenuController l = new LoginMenuController("user login -u epo3 -p abC12$");
        Assertions.assertTrue(l.userExist());
        l = new LoginMenuController("user login -u mmmmmm -p efe");
        Assertions.assertEquals(LoginControllerOut.USERNAME_NOT_FOUND, l.checkForLogin());
    }
    @Test
    public void loginUserPasswordMatch() throws IOException {
        LoginMenuController l = new LoginMenuController("user login -u epo3 -p abC12$");
        l.userExist();
        Assertions.assertTrue(l.passwordMatch());
        l = new LoginMenuController("user login -u epo -p wefg");
        l.userExist();
        l.passwordMatch();
        Assertions.assertEquals(LoginControllerOut.PASSWORD_WRONG, l.checkForLogin());


    }
    @Test
    public void checkStayedLogin() throws IOException, NoSuchAlgorithmException {
        LoginMenuController l = new LoginMenuController("user login -u epo3 -p abC12$");
        l.userExist();
        l.saveUserStayed(l.getUser());
        Assertions.assertTrue(RegisterMenu.stayLogin());
    }
    static RegisterMenuController registerMenuController = new RegisterMenuController();
/*    @Test
    @DisplayName("Correcting the input Strings with extra double quotes.")
    public void correctingDoubleQuotes() {
        String testing = new String();
        testing = "\"jhkjhh\"";
//        Assertions.assertThrows(NullPointerException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//
//            }
//        })

        Assertions.assertEquals(registerMenuController.correctDoubleQuotation(testing),"\"jhkjhh\"");
        testing = "";
        Assertions.assertEquals(registerMenuController.correctDoubleQuotation(testing),"");
        testing = "\"jhkj  hh\"";
        Assertions.assertEquals(registerMenuController.correctDoubleQuotation(testing),"jhkj  hh");
        testing = "jhkj  hh";
        Assertions.assertEquals(registerMenuController.correctDoubleQuotation(testing),"jhkj  hh");

    }
    //auto generate equals

    @Test
    public void checkRandomPassword() throws IOException {
        RegisterMenu registerMenu = new RegisterMenu();
        RegisterMenuController registerMenuController1 = new RegisterMenuController();
        //registerMenuController1.validateBeforeCreation("-u username -p password password -email nik_m@yahoo.com -n nik -s nope");
        registerMenu.createUser("-u username -p password password -email nik_m@yahoo.com -n nik -s nope");
        registerMenu.createUser("-u username -p password password -email nik_m@yahoo.com -n nik -s nope");
        registerMenu.createUser("-u username -p password password -email nik_m@yahoo.com -n nik -s nope");
        String xml = new String();
        if ((this.getClass().getResourceAsStream(System.getProperty("user.dir") + "/DataBase/userInfo.json")) != null)
        xml = IOUtils.toString(
                Objects.requireNonNull(this.getClass().getResourceAsStream(System.getProperty("user.dir") + "/DataBase/userInfo.json")),
                StandardCharsets.UTF_8
        );
        else xml ="this is empty";
        System.out.println(xml);
    }

*/


}
