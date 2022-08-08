package ge.kinder.Services;

import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserService {



    User registerUser(String mail) throws SQLException;

    UserDTO loginUser(String user) throws SQLException;

    boolean confirmCode(String code);



    Optional<User> getUserByUsername(String username);
}
