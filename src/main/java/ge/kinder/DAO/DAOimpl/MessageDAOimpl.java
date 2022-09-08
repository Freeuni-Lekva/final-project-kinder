package ge.kinder.DAO.DAOimpl;

import ge.kinder.DAO.MessageDAO;
import ge.kinder.Database.TableConstants;
import ge.kinder.Models.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOimpl implements MessageDAO {

    private final Connection connection;



    public MessageDAOimpl(Connection connection) {
        this.connection = connection;



    }


    @Override
    public int getChatId(int user_id_1, int user_id_2) throws SQLException{

        try {

            PreparedStatement stm = connection.prepareStatement(
                    "SELECT %s FROM %s WHERE (%s = ? AND %s =?) OR (%s = ? AND %s =?);".formatted(
                            TableConstants.MATCH_CHAT_ID,
                            TableConstants.MATCH_TABLE,
                            TableConstants.MATCH_USER_ID1,
                            TableConstants.MATCH_USER_ID2,
                            TableConstants.MATCH_USER_ID1,
                            TableConstants.MATCH_USER_ID2

                    ));
            stm.setInt(1, user_id_1);
            stm.setInt(2, user_id_2);
            stm.setInt(3, user_id_2);
            stm.setInt(4, user_id_1);
            ResultSet rs = stm.executeQuery();
            rs.next();
            return rs.getInt(1);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addMessage(int chat_id, String msg, int user_id_1) throws SQLException {
        try {

            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO %s (%s, %s,  %s) VALUES (?, ?, ?)".formatted(
                            Message.MESSAGE_TABLE,
                            Message.MESSAGE_CHAT_ID,
                            Message.MESSAGE_MESSAGE_TEXT,

                            Message.MESSAGE_USER_ID
                    ), Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, chat_id);
            stm.setString(2, msg);
            stm.setInt(3, user_id_1);
            stm.executeUpdate();
            connection.commit();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void addMessage(int user_id_1,int user_id_2,String msg) throws SQLException {
        int chat_id = getChatId(user_id_1,user_id_2);


        try {

            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO %s (%s, %s,  %s) VALUES (?, ?, ?)".formatted(
                            Message.MESSAGE_TABLE,
                            Message.MESSAGE_CHAT_ID,
                            Message.MESSAGE_MESSAGE_TEXT,

                            Message.MESSAGE_USER_ID
                    ), Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, chat_id);
            stm.setString(2, msg);
            stm.setInt(3, user_id_1);
            stm.executeUpdate();
            connection.commit();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMessage(int message_id) throws SQLException {

        try {

            PreparedStatement stm = connection.prepareStatement(
                    "DELETE FROM %s WHERE %s = ?".formatted(
                            Message.MESSAGE_TABLE,
                            Message.MESSAGE_MESSAGE_ID
                    ));
            stm.setInt(1, message_id);
            stm.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public List<Message> getMessages(int chat_id) throws SQLException {
        //  int chat_id = getChatId(user_id_1,user_id_2);
        System.out.println("asked chat is " + chat_id);
        List <Message> messages = new ArrayList<>();
        try {

            PreparedStatement stm = connection.prepareStatement(
                    "SELECT * FROM %s WHERE %s = ?;".formatted(
                            Message.MESSAGE_TABLE,
                            Message.MESSAGE_CHAT_ID
                    )
            );
            stm.setInt(1, chat_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                messages.add(new Message(rs.getInt(1),rs.getInt(2),
                        rs.getString(3),rs.getDate(4),rs.getInt(5)));

            }
            System.out.println("messages are  " + messages);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return messages;
    }
}
