package ge.kinder.Services.Implementation;

import ge.kinder.Models.User;
import ge.kinder.Services.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Override
    public User registerUser(User user) {
        return null;
    }

    @Override
    public User loginUser(User user) {
        return null;
    }

    @Override
    public boolean confirmUserRegistration(String token) {
        return false;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return Optional.empty();
    }
}
