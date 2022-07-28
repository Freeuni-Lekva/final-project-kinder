package DAO;

import Models.User;

public interface MatchesDAO {

    void addMatch(User user_1, User user_2);
    void deleteMatch(User user_1,User user_2);

    void addChat(User user_1,User user_2); // ak unda shemowmdes ogond match xo aris namdvilad
    void deleteChat(User user_1,User user_2);

    // and so on...


}
