package ge.kinder.DAO.DAOimpl;

import ge.kinder.DAO.MessageDAO;
import ge.kinder.Models.Message;
import java.util.List;

public class MessageDAOimpl implements MessageDAO {
    @Override
    public void addMessage(Message message) {
        // insert into messages
    }

    @Override
    public void deleteMessage(Message message) {
        // delete from messages where message.getMessage_id = message_id
    }

    @Override
    public List<Message> getMessages(int chat_id) {
        return null;
    }
}
