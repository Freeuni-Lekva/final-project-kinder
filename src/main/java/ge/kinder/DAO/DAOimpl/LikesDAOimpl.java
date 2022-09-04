package ge.kinder.DAO.DAOimpl;

import ge.kinder.DAO.LikesDAO;
import ge.kinder.Database.TableConstants;
import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.Status;

import java.sql.*;
import java.util.List;

public class LikesDAOimpl implements LikesDAO {
    private final Connection connection;

    public LikesDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addLike(int user_id_1, int user_id_2, Status status) throws SQLException {
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?);".formatted(
                            TableConstants.LIKE_TABLE,
                            TableConstants.LIKE_USER_ID1,
                            TableConstants.LIKE_USER_ID2,
                            TableConstants.LIKE_STATUS
                    ), Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, user_id_1);
            stm.setInt(2, user_id_2);
            stm.setString(3, status.toString());
            stm.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean isLiked(int user_id_1, int user_id_2) throws SQLException {
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT 1 FROM %s WHERE %s = ? AND %s = ? AND (%s = ? OR %s = ?) ;".formatted(
                            TableConstants.LIKE_TABLE,
                            TableConstants.LIKE_USER_ID1,
                            TableConstants.LIKE_USER_ID2,
                            TableConstants.LIKE_STATUS,
                            TableConstants.LIKE_STATUS
                    )
            );
            stm.setInt(1, user_id_1);
            stm.setInt(2, user_id_2);
            stm.setString(3, Status.LIKE.toString());
            stm.setString(4, Status.SUPERLIKE.toString());
            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                if(rs.getInt(1) == 1){
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    @Override
    public boolean isDisliked(int user_id_1, int user_id_2) throws SQLException {
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT 1 FROM %s WHERE %s = ? AND %s = ? AND %s = ?;".formatted(
                            TableConstants.LIKE_TABLE,
                            TableConstants.LIKE_USER_ID1,
                            TableConstants.LIKE_USER_ID2,
                            TableConstants.LIKE_STATUS
                    )
            );
            stm.setInt(1, user_id_1);
            stm.setInt(2, user_id_2);
            stm.setString(3, Status.DISLIKE.toString());

            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                if(rs.getInt(1) == 1){
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public int numberOfLikes(int user_id_1) throws SQLException {
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT COUNT(*) FROM %s WHERE %s = ? AND (%s = ? OR %s = ?) AND TIMESTAMPDIFF(hour,%s,SYSDATE()) <= 24 ;".formatted(
                            TableConstants.LIKE_TABLE,
                            TableConstants.LIKE_USER_ID2,
                            TableConstants.LIKE_STATUS,
                            TableConstants.LIKE_STATUS,
                            TableConstants.LIKE_DATE
                    )
            );
            stm.setInt(1, user_id_1);
            stm.setString(2,Status.LIKE.toString());
            stm.setString(3,Status.SUPERLIKE.toString());
            ResultSet rs = stm.executeQuery();

            rs.next();
            return rs.getInt(1);


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public boolean deleteLike(int user_id_1, int user_id_2) throws SQLException {

        try {
            PreparedStatement stm = connection.prepareStatement(
                    "DELETE FROM %s WHERE %s = ? AND %s = ?;".formatted(
                            TableConstants.LIKE_TABLE,
                            TableConstants.LIKE_USER_ID1,
                            TableConstants.LIKE_USER_ID2
                    )
            );
            stm.setInt(1, user_id_1);
            stm.setInt(2, user_id_2);

            if (stm.executeUpdate() == 1) {
                connection.commit();
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public List<UserDTO> getLikers(int user_id) {
        return null;
    }
}
