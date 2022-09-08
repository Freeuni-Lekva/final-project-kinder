package ge.kinder.Services;

import ge.kinder.Models.DTO.UserDTO;
        import ge.kinder.Models.User;

        import java.sql.SQLException;
        import java.util.Optional;
        import java.util.List;

public interface UserService {



    User registerUser(String mail) throws SQLException;

    User loginUser(String user) throws SQLException;

    void verificateUser(User user,String mail);

    boolean confirmCode(String code);

    User getUserByID(int userID) throws SQLException;

    void changeSettings(User user,String setting,String value);
    void changeSettings(User user,String setting,int value);
    List<User> getBannedUsers();
    List<User> getAllUsers();
}


