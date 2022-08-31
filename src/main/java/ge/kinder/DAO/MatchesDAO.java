package ge.kinder.DAO;

import java.sql.SQLException;

public interface MatchesDAO {

    void addMatch(int user_id_1, int user_id_2) throws SQLException; //works
    void deleteMatch(int user_id_1, int user_id_2) throws SQLException; // works

    //getmatches


}
