package ge.kinder.DAO.DAOimpl;

import ge.kinder.DAO.ImagesDAO;
import ge.kinder.Database.TableConstants;
import ge.kinder.Models.Hobby;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImagesDAOimpl implements ImagesDAO {
    private final Connection connection;

    public ImagesDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addImage(String path,int user_id) throws SQLException {
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?);".formatted(
                            TableConstants.IMAGES_TABLE,
                            TableConstants.IMAGE_USER_ID,
                            TableConstants.IMAGE_URL,
                            TableConstants.IMAGE_IS_PROFILE
                    ), Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, user_id);
            stm.setString(2, path);
            stm.setInt(3, 0);
            stm.executeUpdate();
            connection.commit();
            // ექსეფშენების ამბავი
        } catch (SQLException e) {
            System.out.println("ver mivxvdi");
            throw new RuntimeException(e);

        }
    }

    @Override
    public boolean deleteImage(String path, int user_id) throws SQLException {
        boolean isProfile  = false;
        if (isProfile(path,user_id)){
            isProfile=true;
        }

        try {
            PreparedStatement stm = connection.prepareStatement(
                    "DELETE FROM %s WHERE %s = ? AND %s = ?;".formatted(
                            TableConstants.IMAGES_TABLE,
                            TableConstants.IMAGE_URL,
                            TableConstants.IMAGE_USER_ID
                    )
            );
            stm.setString(1, path);
            stm.setInt(2, user_id);
            if (stm.executeUpdate() == 1) {
                connection.commit();
                setImage(minDateImage(user_id),user_id);
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private String minDateImage(int user_id) throws SQLException {
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT %s FROM %s WHERE %s = (SELECT min(%s) FROM %s WHERE %s = ?);".formatted(
                            TableConstants.IMAGE_URL,
                            TableConstants.IMAGES_TABLE,
                            TableConstants.IMAGE_DATE,
                            TableConstants.IMAGE_DATE,
                            TableConstants.IMAGES_TABLE,
                            TableConstants.IMAGE_USER_ID
                    )
            );
            stm.setInt(1, user_id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    private boolean isProfile(String path, int user_id) throws SQLException {
        System.out.println(path+ " " + user_id);
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT %s FROM %s WHERE %s = ? AND %s = ?;".formatted(
                            TableConstants.IMAGE_IS_PROFILE,
                            TableConstants.IMAGES_TABLE,
                            TableConstants.IMAGE_URL,
                            TableConstants.IMAGE_USER_ID
                    )
            );
            stm.setString(1, path);
            stm.setInt(2, user_id);
            ResultSet rs = stm.executeQuery();
            if(rs.next())
                return rs.getInt(1) == 1;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getImages(int user_id) throws SQLException {
        List <String> images = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT %s FROM %s WHERE %s = ?;".formatted(
                            TableConstants.IMAGE_URL,
                            TableConstants.IMAGES_TABLE,
                            TableConstants.IMAGE_USER_ID
                    )
            );
            stm.setInt(1, user_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                images.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return images;
    }

    @Override
    public void setImage(String path, int user_id) throws SQLException {
        changeCurrentProfile(user_id);
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "UPDATE %s SET %s = ? WHERE %s = ? AND %s = ?;".formatted(
                            TableConstants.IMAGES_TABLE,
                            TableConstants.IMAGE_IS_PROFILE,
                            TableConstants.IMAGE_URL,
                            TableConstants.IMAGE_USER_ID
                    )
            );
            stm.setInt(1, 1);
            stm.setString(2, path);
            stm.setInt(3, user_id);
            stm.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void changeCurrentProfile(int user_id) throws SQLException {
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "UPDATE %s SET %s = ? WHERE %s = ? AND %s = ?;".formatted(
                            TableConstants.IMAGES_TABLE,
                            TableConstants.IMAGE_IS_PROFILE,
                            TableConstants.IMAGE_IS_PROFILE,
                            TableConstants.IMAGE_USER_ID
                    )
            );
            stm.setInt(1, 0);
            stm.setInt(2, 1);
            stm.setInt(3, user_id);
            stm.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
