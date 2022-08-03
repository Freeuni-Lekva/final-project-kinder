package ge.kinder.Services;

import ge.kinder.Models.User;

import java.util.Optional;

public interface UserService {

    User registerUser(User user);

    User loginUser(User user);

    boolean confirmUserRegistration(String token);

    Optional<User> getUserByUsername(String username);
}
