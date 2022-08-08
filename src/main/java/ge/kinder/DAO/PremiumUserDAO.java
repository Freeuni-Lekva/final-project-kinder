package ge.kinder.DAO;

import ge.kinder.Models.User;

import java.util.List;

public interface PremiumUserDAO extends UserDAO {

    //anu amas igive metodebi akvs rac user daos + eg ori

    List<User> getUsers(boolean most_recent, String city);
    List<User> getUsers(int min_age, int max_age, boolean liked, boolean most_recent, String city);
}
