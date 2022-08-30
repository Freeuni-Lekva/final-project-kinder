package ge.kinder.DAO.DAOimpl;

import ge.kinder.DAO.LikesDAO;
import ge.kinder.DAO.MatchesDAO;
import ge.kinder.DAO.MessageDAO;
import ge.kinder.Database.TableConstants;
import ge.kinder.Models.Chat;

import javax.sql.DataSource;
import java.sql.*;

public class MatchesDAOimpl implements MatchesDAO {
    private final Connection connection;
    private  MessageDAO messageDAO;

    private LikesDAO likesDAO;
    public MatchesDAOimpl(Connection connection, MessageDAO messageDAO, LikesDAO likesDAO) {

        this.connection = connection;
        this.messageDAO = messageDAO;
        this.likesDAO = likesDAO;
    }
    @Override
    public void addMatch(int user_id_1, int user_id_2) throws SQLException {
        if (likesDAO.isLiked(user_id_1, user_id_2) && likesDAO.isLiked(user_id_2, user_id_1)) {
            int newChatId = addChat(user_id_1,user_id_2);

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
                stm.setInt(4, newChatId);
                stm.executeUpdate();
                connection.commit();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void deleteMatch(int user_id_1, int user_id_2) throws SQLException {
        deleteChat(user_id_1,user_id_2);

    }

    private int getChatId(int user_id_1, int user_id_2) throws SQLException{

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
            if(rs.next())
                return rs.getInt(1);
            return 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private int addChat(int user_id_1,int user_id_2) throws SQLException {

        try {

            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO %s (%s,%s) VALUES (?,?);".formatted(
                            Chat.CHAT_TABLE,
                            Chat.USER_1,
                            Chat.USER_2

                    ),Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1,user_id_1);
            stm.setInt(2,user_id_2);
            if(stm.executeUpdate() == 1){
                connection.commit();
                ResultSet rs = stm.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }


    private void deleteChat(int user_id_1, int user_id_2) throws SQLException {

       int chatId = getChatId(user_id_1,user_id_2);


        try {


            PreparedStatement stm = connection.prepareStatement(
                    "DELETE FROM %s WHERE %s = ?;".formatted(
                            Chat.CHAT_TABLE,
                            Chat.CHAT_CHAT_ID
                    ));
            stm.setInt(1,chatId);
            stm.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
