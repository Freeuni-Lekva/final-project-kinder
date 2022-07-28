package DAO;

import Models.User;

public interface LikesDAO {

    void addLike(User user_1, User user_2);
    void addSuperLike(User user_1, User user_2);
    boolean isLiked(User user_1,User user_2);
    int numberOfLikes(User user); // num of likes during last 24 hours
    void deleteLike(User user_1, User user_2);


    // and so on...
}
