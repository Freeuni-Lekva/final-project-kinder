package ge.kinder.DAO;

import ge.kinder.Models.Status;

import java.sql.SQLException;

public interface LikesDAO {

    void addLike(int user_id_1, int user_id_2, Status status) throws SQLException; //works


    boolean isLiked(int user_id_1, int user_id_2) throws SQLException; // works

    int numberOfLikes(int user_id_1) throws SQLException; //works without taking into account last 24 hours
    boolean deleteLike(int user_id_1, int user_id_2) throws SQLException; //works

}
