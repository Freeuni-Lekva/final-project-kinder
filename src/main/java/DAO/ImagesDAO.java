package DAO;

import java.util.List;

public interface ImagesDAO {

    void addImage(String path);
    void deleteImage(String path);
    List<String> getImages();
    void setImage(String path);
}
