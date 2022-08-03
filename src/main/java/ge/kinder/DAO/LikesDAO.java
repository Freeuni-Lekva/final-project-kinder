package ge.kinder.DAO;

public interface LikesDAO {

    void addLike(int user_id_1, int user_id_2);

    void addSuperLike(int user_id_1, int user_id_2);

    boolean isLiked(int user_id_1, int user_id_2);

    int numberOfLikes(int user_id_1); // num of likes during last 24 hours

    void deleteLike(int user_id_1, int user_id_2);


    // and so on...
}
