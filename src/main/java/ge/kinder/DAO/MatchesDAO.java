package ge.kinder.DAO;

import ge.kinder.Models.DTO.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface MatchesDAO {

    void addMatch(int user_id_1, int user_id_2) throws SQLException; //works
    void deleteMatch(int user_id_1, int user_id_2) throws SQLException; // works

    List<UserDTO> getMatches(int user_id);


}
