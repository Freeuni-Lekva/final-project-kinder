package ge.kinder.DAO;

import ge.kinder.Models.DTO.UserDTO;

import java.util.List;

public interface PremiumUserDAO extends UserDAO {

    //anu amas igive metodebi akvs rac user daos + eg ori

    List<UserDTO> getUsers(boolean most_recent, String city, int user_id );
    List<UserDTO> getUsers(int min_age, int max_age, boolean liked, boolean most_recent, String city, int user_id);
}
