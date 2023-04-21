import controller.RegisterMenuController;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
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
