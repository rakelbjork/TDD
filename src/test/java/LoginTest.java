import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Key;
import java.util.List;
import java.util.Map;


@ExtendWith(MockitoExtension.class)
public class LoginTest {

    @Mock
    UserRepo userRepo;

    private Login login;

    @BeforeEach
    public void setUp(){
        login = new Login();
    }

    @ParameterizedTest
    @CsvSource(value = {"Anna, losen, QW5uYQ==" , "Berit, 123456, QmVyaXQ="})

    public void User_Password(String username, String password, String expected) throws WrongPasswordException {

        //When
        String result = login.login(username, password);

        //Then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void role(){
        //Given
        UserService userService = new UserService(userRepo);
        Mockito.when(userRepo.findAll()).thenReturn(List.of(
                new User("Anna", "ADMIN"),
                new User("Berit", "TEACHER"),
                new User("Kalle", "STUDENT")
        ));
        List <User> expectedList = List.of(
                new User("Anna", "ADMIN"),
                new User("Berit", "TEACHER"),
                new User("Kalle", "STUDENT")
        );

        //When
        List<User> userList = userService.role();

        //Then
        Assertions.assertIterableEquals(expectedList, userList);

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

        String token = login.tokenBuilder();

        //Then
        Assertions.assertEquals();
                /* Jwts.builder()
                .setSubject(username)
                .addClaims(Map.of("Anna", "ADMIN", "Berit", "TEACHER", "Kalle", "STUDENT"))
                .signWith(key)
                .compact();
*/
    }

    @Test
    public void parseJWT(){
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJCZXJpdCIsIkthbGxlIjoicGFzc3dvcmQiLCJBbm5hIjoibG9zZW4iLCJCZXJpdCI6IjEyMzQ1NiJ9.Mn0kjRV8aM-dQtPg4UTkK7RxZLR7T_dT4MKTlIr6xJhYrpQop7x25aCVat_sbFLP";
        Key key = Keys.hmacShaKeyFor("SuperMegaDunderJätteHemligaLösenordetSomÄrBraNog".getBytes());

        String roll = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("roll", String.class);

        System.out.println(roll);
    }
}







