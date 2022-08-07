package ge.kinder.DAO;

import ge.kinder.Models.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageDAO {
    void deleteMessages(int chatId) throws SQLException;
    void addMessage(Message message) throws SQLException;

    // delete gvkondes?
    void deleteMessage(Message message) throws SQLException;

    List<Message> getMessages(int chat_id) throws SQLException;

    // and so on...




}
