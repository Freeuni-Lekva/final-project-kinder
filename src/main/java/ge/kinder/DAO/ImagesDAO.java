package ge.kinder.DAO;

import java.util.List;

public interface ImagesDAO {

    void addImage(String path, int user_id);

    void deleteImage(String path, int user_id);

    List<String> getImages(int user_id);

    void setImage(String path, int user_id);
}
