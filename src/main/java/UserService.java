import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> role() {
        return userRepo.findAll()
                .stream()
                /*.sorted()*/
                .toList();
    }
}
