package ge.kinder.DAO;

import java.sql.SQLException;

public interface LikesDAO {

    void addLike(int user_id_1, int user_id_2, String status) throws SQLException;


    boolean isLiked(int user_id_1, int user_id_2) throws SQLException;

    int numberOfLikes(int user_id_1) throws SQLException; // num of likes during last 24 hours

    boolean deleteLike(int user_id_1, int user_id_2) throws SQLException;


    // and so on...
}
