package DAO;

import Models.User;

import java.util.List;

public interface UserDAO  {

    void addUser(User user);

    // shemdegebshi shegvidzlia an user pirdapir an user_id gadavcet
    void updateUser(User user);
    void deleteUser(User user);
    void hideUser(User user);
    void unhideUser(User user);
    List<User> getAllUsers();


    // an maili and id is mixedvit da useris classhi davamatot id
    User getUserByMail(String mail);
    User getUserById(int id);


    // da sxva metodebi...


}
