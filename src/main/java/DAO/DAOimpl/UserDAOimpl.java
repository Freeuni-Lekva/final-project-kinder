package DAO.DAOimpl;

import DAO.UserDAO;
import Models.User;

import java.util.List;

public class UserDAOimpl implements UserDAO {

    // es methodebi synchronised unda iyos albat
    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public boolean userExists(String mail) {
        return false;
    }

    @Override
    public User getUserByMail(String mail) {
        return null;
    }

    @Override
    public List<User> getUsers(String city) {
        return null;
    }

    @Override
    public List<User> getUsers(boolean liked, boolean most_recent, String city) {
        return null;
    }

    @Override
    public List<User> getUsers(int min_age, int max_age, String city) {
        return null;
    }

    @Override
    public List<User> getUsers(int min_age, int max_age, boolean liked, boolean most_recent, String city) {
        return null;
    }
}
