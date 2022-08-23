package ge.kinder.DAO;

import java.sql.SQLException;
import java.util.List;

public interface ImagesDAO {

    void addImage(String path, int user_id) throws SQLException; //works

    boolean deleteImage(String path, int user_id) throws SQLException; //works

    List<String> getImages(int user_id) throws SQLException; //works

    void setImage(String path, int user_id) throws SQLException; // works
}
