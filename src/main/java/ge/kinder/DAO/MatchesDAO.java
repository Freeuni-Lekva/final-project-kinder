package ge.kinder.DAO;

import java.sql.SQLException;

public interface MatchesDAO {

    void addMatch(int user_id_1, int user_id_2) throws SQLException;
    int deleteMatch(int user_id_1, int user_id_2) throws SQLException;

    int addChat() throws SQLException; // ak unda shemowmdes ogond match xo aris namdvilad
    void deleteChat(int user_id_1, int user_id_2) throws SQLException;

    // and so on...


}
