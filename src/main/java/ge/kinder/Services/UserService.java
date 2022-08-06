package ge.kinder.Services;

import ge.kinder.Models.User;

import java.util.Optional;

public interface UserService {



    User registerUser(String mail);

    User loginUser(String user);

    boolean confirmCode(String code);



    Optional<User> getUserByUsername(String username);
}
