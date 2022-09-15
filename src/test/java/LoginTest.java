import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LoginTest {

    @ParameterizedTest
    @CsvSource(value = {"Anna, losen, true" , "Berit, 123456, true", "Kalle, pazzword, false"})

    public void User_Password(String username, String password, boolean expected){
        //Given
        Login login = new Login();

        //When
        boolean result = login.login(username, password);

        //Then
        Assertions.assertEquals(expected, result);
        }

    }

