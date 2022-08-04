package ge.kinder.DAO.DAOimpl;

import ge.kinder.DAO.HobbiesDAO;
import ge.kinder.Database.TableConstants;
import ge.kinder.Models.Hobby;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HobbiesDAOimpl implements HobbiesDAO {

    private final Connection connection;

    public HobbiesDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addHobby(Hobby hobby, int user_id) throws SQLException {
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO %s (%s, %s) VALUES (?, ?)".formatted(
                            TableConstants.HOBBIES_TABLE,
                            TableConstants.HOBBY_NAME,
                            TableConstants.HOBBY_USER_ID
                    ), Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, hobby.toString());
            stm.setInt(2, user_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }

    @Override
    public boolean deleteHobby(Hobby hobby, int user_id) throws SQLException {
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "DELETE FROM %s WHERE %s = ? AND %s = ?;".formatted(
                            TableConstants.HOBBIES_TABLE,
                            TableConstants.HOBBY_NAME,
                            TableConstants.HOBBY_USER_ID
                    )
            );
            stm.setString(1, hobby.toString());
            stm.setInt(2, user_id);
            if (stm.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
        return false;
    }

    @Override
    public List<Hobby> getHobbies(int user_id) throws SQLException {
        List <Hobby> hobbies = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT %s FROM %s WHERE %s = ?;".formatted(
                            TableConstants.HOBBY_NAME,
                            TableConstants.HOBBIES_TABLE,
                            TableConstants.HOBBY_USER_ID
                    )
            );
            stm.setInt(1, user_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                hobbies.add(Hobby.valueOf(rs.getString(1)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
        return hobbies;
    }
}
