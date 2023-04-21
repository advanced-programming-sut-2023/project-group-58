import controller.RegisterMenuController;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

public class TestClass {

    RegisterMenuController registerMenuController = new RegisterMenuController();
    @Test
    public void correctingDoubleQuotes() {
        String testing = new String();
        testing = "\"jhkjhh\"";
        Assertions.assertEquals(registerMenuController.correctDoubleQuotation(testing),"\"jhkjhh\"");
        testing = "";
        Assertions.assertEquals(registerMenuController.correctDoubleQuotation(testing),"");
        testing = "\"jhkj  hh\"";
        Assertions.assertEquals(registerMenuController.correctDoubleQuotation(testing),"jhkj  hh");
        testing = "jhkj  hh";
        Assertions.assertEquals(registerMenuController.correctDoubleQuotation(testing),"jhkj  hh");
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
