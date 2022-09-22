import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Login {

    Map<String, String> userMap = new HashMap<>(Map.of(
            "Anna", "losen",
            "Berit", "123456",
            "Kalle", "password"
    ));

    public String login(String username, String password) throws WrongPasswordException {

       if (userMap.get(username).equals(password)) {
           return new String (Base64.getEncoder().encode(username.getBytes()));
        } else {
           throw new WrongPasswordException("Wrong password.");
       }
    }

    public String tokenBuilder() {
        return "";
    }
}

