package ge.kinder.DAO.DAOimpl;

import ge.kinder.DAO.PremiumUserDAO;
import ge.kinder.Models.User;

import java.sql.Connection;
import java.util.List;

public class PremiumUserDAOimpl extends UserDAOimpl implements PremiumUserDAO {

    public PremiumUserDAOimpl(Connection connection, HobbiesDAOimpl hobbiesDAO, ImagesDAOimpl imagesDAO) {
        super(connection,hobbiesDAO,imagesDAO);
    }

    @Override
    public List<User> getUsers(boolean most_recent, String city) {
        // აქ ზუსტად ვერ მივხვდი
        return null;
    }

    @Override
    public List<User> getUsers(int min_age, int max_age, boolean liked, boolean most_recent, String city) {
        // აქ ზუსტად ვერ მივხვდი
        return null;
    }
}