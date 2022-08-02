package DAO.DAOimpl;

import DAO.MatchesDAO;
import Models.User;

public class MatchesDAOimpl implements MatchesDAO {
    @Override
    public void addMatch(int user_id_1, int user_id_2) {
        //insert into matches
    }

    @Override
    public void deleteMatch(int user_id_1, int user_id_2) {
        //delete from matches
    }

    @Override
    public int addChat(int user_id_1, int user_id_2) {
        //insert into chats
        return 0;
    }

    @Override
    public void deleteChat(int user_id_1, int user_id_2) {
        //delete from chats
    }
}
