import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Key;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class LoginTest {

    private Login login;

    @BeforeEach
    public void setUp(){
        login = new Login();
    }

    @ParameterizedTest
    @CsvSource(value = {"Anna, losen, true" , "Berit, 123456, true", "Kalle, pazzword, false"})

    public void User_Password(String username, String password, boolean expected) throws WrongPasswordException {

        //When
        boolean result = login.login(username, password);

        //Then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void wrongPassword_ThrowException(){
        //Given
        String username = "Anna";
        String wrongPassword = "lösen";

        //When
        WrongPasswordException err =
                Assertions.assertThrows(WrongPasswordException.class,
                        () -> login.login(username, wrongPassword));

        //Then
        Assertions.assertEquals("Wrong password.", err.getMessage());
    }

    @Test
    public void JWT(){
        String username = "Berit";
        Key key = Keys.hmacShaKeyFor("SuperMegaDunderJätteHemligaLösenordetSomÄrBraNog".getBytes());

        String token = Jwts.builder()
                .setSubject(username)
                .addClaims(Map.of("Anna", "losen", "Berit", "123456", "Kalle", "password"))
                .signWith(key)
                .compact();

        System.out.println(token);
    }

    @Test
    public void parseJWT(){
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJCZXJpdCIsIkthbGxlIjoicGFzc3dvcmQiLCJBbm5hIjoibG9zZW4iLCJCZXJpdCI6IjEyMzQ1NiJ9.Mn0kjRV8aM-dQtPg4UTkK7RxZLR7T_dT4MKTlIr6xJhYrpQop7x25aCVat_sbFLP";
        Key key = Keys.hmacShaKeyFor("SuperMegaDunderJätteHemligaLösenordetSomÄrBraNog".getBytes());

        String password = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("password", String.class);

        System.out.println(password);
    }
}







