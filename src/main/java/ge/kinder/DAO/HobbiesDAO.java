package ge.kinder.DAO;

import ge.kinder.Models.Hobby;

import java.sql.SQLException;
import java.util.List;

public interface HobbiesDAO {

    void addHobby(Hobby hobby, int user_id) throws SQLException;

    boolean deleteHobby(Hobby hobby, int user_id) throws SQLException;

    List<Hobby> getHobbies(int user_id) throws SQLException;

}
