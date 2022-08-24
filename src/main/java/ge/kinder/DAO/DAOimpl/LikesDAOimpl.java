package ge.kinder.DAO.DAOimpl;

import ge.kinder.DAO.LikesDAO;
import ge.kinder.Database.TableConstants;

import java.sql.*;

public class LikesDAOimpl implements LikesDAO {
    private final Connection connection;

    public LikesDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addLike(int user_id_1, int user_id_2, String status) throws SQLException {
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
            stm.setString(3, status);
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
            stm.setString(3, "LIKE");
            stm.setString(4, "SUPERLIKE");
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
    public int numberOfLikes(int user_id_1) throws SQLException {
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT COUNT(*) FROM %s WHERE %s = ?;".formatted(
                            TableConstants.LIKE_TABLE,
                            TableConstants.LIKE_USER_ID2
                    )
            );
            stm.setInt(1, user_id_1);
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
}
