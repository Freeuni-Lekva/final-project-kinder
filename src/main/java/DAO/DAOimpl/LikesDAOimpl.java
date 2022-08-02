package DAO.DAOimpl;

import DAO.LikesDAO;

public class LikesDAOimpl implements LikesDAO {


    @Override
    public void addLike(int user_id_1, int user_id_2) {

    }

    @Override
    public void addSuperLike(int user_id_1, int user_id_2) {

    }

    @Override
    public boolean isLiked(int user_id_1, int user_id_2) {
        return false;
    }

    @Override
    public int numberOfLikes(int user_id_1) {
        return 0;
    }

    @Override
    public void deleteLike(int user_id_1, int user_id_2) {

    }
}
