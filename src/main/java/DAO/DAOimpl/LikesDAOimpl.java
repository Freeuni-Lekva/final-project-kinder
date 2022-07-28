package DAO.DAOimpl;

import DAO.LikesDAO;
import Models.User;

public class LikesDAOimpl implements LikesDAO {


    @Override
    public void addLike(User user_1, User user_2) {

    }

    @Override
    public void addSuperLike(User user_1, User user_2) {

    }

    @Override
    public boolean isLiked(User user_1, User user_2) {
        return false;
    }

    @Override
    public int numberOfLikes(User user) {
        return 0;
    }

    @Override
    public void deleteLike(User user_1, User user_2) {

    }
}
