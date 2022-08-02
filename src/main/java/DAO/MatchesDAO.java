package DAO;

import Models.User;

public interface MatchesDAO {

    void addMatch(int user_id_1, int user_id_2);
    void deleteMatch(int user_id_1, int user_id_2);

    int addChat(int user_id_1, int user_id_2); // ak unda shemowmdes ogond match xo aris namdvilad
    void deleteChat(int user_id_1, int user_id_2);

    // and so on...


}
