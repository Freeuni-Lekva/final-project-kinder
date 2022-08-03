package ge.kinder.DAO;

import java.util.List;

public interface HobbiesDAO {

    void addHobbie(String hobbie, int user_id);
    void deleteHobbie(String hobbie, int user_id);

    List<String> getHobbies(int user_id);
}
