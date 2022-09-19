import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LoginTest {

    private Login login;

    @BeforeEach
    public void setUp(){
        login = new Login();
    }

    @ParameterizedTest
    @CsvSource(value = {"Anna, losen, true" , "Berit, 123456, true", "Kalle, pazzword, false"})

    public void User_Password(String username, String password, boolean expected){
        //When
        boolean result = login.login(username, password);

        //Then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void login_ThrowException(){
        //Given
        String username = "Anna";
        String password = "losen";

        //When, Then
        ArithmeticException err =
                Assertions.assertThrows(ArithmeticException.class,
                        () -> login.login(username, password));

        Assertions.assertEquals("Wrong password.", err.getMessage());

    }

}







