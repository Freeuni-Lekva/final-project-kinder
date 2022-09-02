package ge.kinder.DAO;

import ge.kinder.Models.Hobby;

import java.sql.SQLException;
import java.util.List;

public interface HobbiesDAO {

    void addHobby(Hobby hobby, int user_id) throws SQLException; // works

    boolean deleteHobby(Hobby hobby, int user_id) throws SQLException; // works

    List<Hobby> getHobbies(int user_id) throws SQLException; //works

}
