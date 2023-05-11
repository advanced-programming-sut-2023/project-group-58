import controller.RegisterMenuController;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import view.RegisterMenu;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class TestClass {

    RegisterMenuController registerMenuController = new RegisterMenuController();
    @Test
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


    @BeforeEach
    public void settingUp() {
        try {
            registerMenuController.setUpUserInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        registerMenuController.setUpSloganDataBase();
    }
}
