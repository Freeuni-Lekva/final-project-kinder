package ge.kinder.DAO.DAOimpl;

import ge.kinder.DAO.MatchesDAO;
import ge.kinder.Database.TableConstants;
import ge.kinder.Models.Chat;

import javax.sql.DataSource;
import java.sql.*;

public class MatchesDAOimpl implements MatchesDAO {
    private final Connection connection;
    private  MessageDAOimpl messageDAO;
    public MatchesDAOimpl(Connection connection, MessageDAOimpl messageDAO) {

        this.connection = connection;
        this.messageDAO = messageDAO;
    }
    @Override
    public void addMatch(int user_id_1, int user_id_2) throws SQLException {
        int newChatId = addChat();

        try {

            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?);".formatted(
                            TableConstants.MATCH_TABLE,
                            TableConstants.MATCH_USER_ID1,
                            TableConstants.MATCH_USER_ID2,
                            TableConstants.MATCH_UNMATCH,
                            TableConstants.MATCH_CHAT_ID
                    ), Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, user_id_1);
            stm.setInt(2, user_id_2);
            stm.setInt(3, 0);
            stm.setInt(4,newChatId);
            stm.executeUpdate();

            // ექსეშენები?

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteMatch(int user_id_1, int user_id_2) throws SQLException {
        int chatId = getChatId(user_id_1,user_id_2);

        try {

            PreparedStatement stm = connection.prepareStatement(
                    "DELETE FROM %s WHERE %s = ? AND %s = ?;".formatted(
                            TableConstants.HOBBIES_TABLE,
                            TableConstants.MATCH_USER_ID1,
                            TableConstants.MATCH_USER_ID2
                    )
            );
            stm.setInt(1, user_id_1);
            stm.setInt(2, user_id_2);

            if (stm.executeUpdate() == 1) {
                return chatId;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
        return chatId;
    }

    private int getChatId(int user_id_1, int user_id_2) throws SQLException {

        try {

            PreparedStatement stm = connection.prepareStatement(
                    "SELECT %s FROM %s WHERE %s = ? AND %s =?;".formatted(
                            TableConstants.MATCH_CHAT_ID,
                            TableConstants.MATCH_TABLE,
                            TableConstants.MATCH_USER_ID1,
                            TableConstants.MATCH_USER_ID2

                    ));
            stm.setInt(1, user_id_1);
            stm.setInt(2, user_id_2);
            ResultSet rs = stm.executeQuery();
            rs.next();
            return rs.getInt(1);

            // ექსეშენები?

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }

    @Override
    public int addChat() throws SQLException {

        try {

            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO %s VALUES ();".formatted(
                            Chat.CHAT_TABLE
                    ),Statement.RETURN_GENERATED_KEYS);

            if(stm.executeUpdate() == 1){
                ResultSet rs = stm.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public void deleteChat(int user_id_1, int user_id_2) throws SQLException {

        int chatId = deleteMatch(user_id_1,user_id_2);
        messageDAO.deleteMessages(chatId);

        try {


            PreparedStatement stm = connection.prepareStatement(
                    "DELETE FROM %s WHERE %s = ?;".formatted(
                            Chat.CHAT_TABLE,
                            Chat.CHAT_CHAT_ID
                    ));
            stm.setInt(1,chatId);
            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
