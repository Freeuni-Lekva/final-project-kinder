package DAO.DAOimpl;

import DAO.ImagesDAO;

import java.util.List;

public class ImagesDAOimpl implements ImagesDAO {

    @Override
    public void addImage(String path,int user_id) {
        //
    }

    @Override
    public void deleteImage(String path,int user_id) {
        // select * from images where path=path and user_id = user_id
    }

    @Override
    public List<String> getImages(int user_id) {
        // select * from images where user_id = user_id
        return null;
    }

    @Override
    public void setImage(String path,int user_id) {

    }
}
