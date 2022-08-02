package DAO.DAOimpl;

import DAO.PremiumUserDAO;
import Models.User;

import java.util.List;

public class PremiumUserDAOimpl extends UserDAOimpl implements PremiumUserDAO {

    @Override
    public List<User> getUsers(boolean liked, boolean most_recent, String city) {
        return null;
    }

    @Override
    public List<User> getUsers(int min_age, int max_age, boolean liked, boolean most_recent, String city) {
        return null;
    }
}