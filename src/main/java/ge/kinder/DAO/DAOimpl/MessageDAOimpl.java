package ge.kinder.DAO.DAOimpl;

import com.mysql.cj.jdbc.ConnectionImpl;
import ge.kinder.DAO.MessageDAO;
import ge.kinder.Database.TableConstants;
import ge.kinder.Models.Hobby;
import ge.kinder.Models.Message;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOimpl implements MessageDAO {

    private final Connection connection;

    public MessageDAOimpl(Connection connection) {
        this.connection = connection;

    }

    public  void deleteMessages(int chatId) throws SQLException {

        try {

            PreparedStatement stm = connection.prepareStatement(
                    "DELETE FROM %s WHERE %s = ?".formatted(
                            Message.MESSAGE_TABLE,
                            Message.MESSAGE_CHAT_ID
                    ));
            stm.setInt(1, chatId);
            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addMessage(Message message) throws SQLException {

        try {

            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)".formatted(
                            Message.MESSAGE_TABLE,
                            Message.MESSAGE_CHAT_ID,
                            Message.MESSAGE_MESSAGE_TEXT,
                            Message.MESSAGE_DATE,
                            Message.MESSAGE_USER_ID
                    ), Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, message.getChat_id());
            stm.setString(2, message.getMessage_text());
            stm.setDate(3, (Date) message.getSend_date());
            stm.setInt(4, message.getUser_id());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMessage(Message message) throws SQLException {

        try {

            PreparedStatement stm = connection.prepareStatement(
                    "DELETE FROM %s WHERE %s = ?".formatted(
                            Message.MESSAGE_TABLE,
                            Message.MESSAGE_MESSAGE_ID
                    ));
            stm.setInt(1, message.getMessage_id());
            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public List<Message> getMessages(int chat_id) throws SQLException {

        List <Message> messages = new ArrayList<>();
        try {

            PreparedStatement stm = connection.prepareStatement(
                    "SELECT * FROM %s WHERE %s = ?;".formatted(
                            Message.MESSAGE_TABLE,
                            Message.MESSAGE_MESSAGE_ID,
                            Message.MESSAGE_CHAT_ID
                    )
            );
            stm.setInt(1, chat_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                messages.add(new Message(rs.getInt(1),rs.getInt(2),
                        rs.getString(3),rs.getDate(4),rs.getInt(5)));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return messages;
    }
}
