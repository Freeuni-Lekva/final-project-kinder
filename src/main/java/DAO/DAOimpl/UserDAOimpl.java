package DAO.DAOimpl;

import DAO.UserDAO;
import Models.User;

import java.util.List;

public class UserDAOimpl implements UserDAO {

    // es methodebi synchronised unda iyos albat
    @Override
    public void addUser(User user) {
        //
        //ბაზაში დამატება
        //insert into users
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public boolean userExists(String mail) {
        //select 1 from users where mail = mail
        //თუ წამოიღო return true;

        return false;
    }

    @Override
    //აქ დავაბრუნოტ UserDTO
    public User getUserByMail(String mail) {
        //select * from user where mail = mail
        return null;
    }

    @Override
    //აქ დავაბრუნოტ UserDTO
    public List<User> getUsers(String city, int user_id) {
        //select * from user from city = city and this.user_id != user_id
        return null;
    }

    @Override
    //აქ დავაბრუნოტ UserDTO
    public List<User> getUsers(boolean liked, boolean most_recent, String city) {
        return null;
    }

    @Override
    //აქ დავაბრუნოტ UserDTO
    public List<User> getUsers(int min_age, int max_age, String city, int user_id) {
        //select * from user where age between min_age and max_age and city = city and user_id != user_id
        return null;
    }

    @Override
    //აქ დავაბრუნოტ UserDTO
    public List<User> getUsers(int min_age, int max_age, boolean liked, boolean most_recent, String city) {
        return null;
    }
}
