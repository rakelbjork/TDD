import java.util.HashMap;
import java.util.Map;

public class Login {

    Map<String, String> userMap = new HashMap<>(Map.of(
            "Anna", "losen",
            "Berit", "123456",
            "Kalle", "password"
    ));

    public boolean login(String username, String password) throws WrongPasswordException {
           if (password == "lösen") {
            throw new WrongPasswordException("Wrong password.");
           }

       return userMap
                .get(username)
                .equals(password);
    }
}

