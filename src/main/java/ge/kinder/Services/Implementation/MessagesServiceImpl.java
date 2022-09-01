package ge.kinder.Services.Implementation;

import ge.kinder.DAO.DAOimpl.MessageDAOimpl;
import ge.kinder.DAO.MessageDAO;
import ge.kinder.Models.Message;
import ge.kinder.Services.MessagesService;

import java.sql.SQLException;
import java.util.List;

public class MessagesServiceImpl implements MessagesService {

    private MessageDAO messageDAO;

    public MessagesServiceImpl(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Override
    public void addMessage(int senderUserId, int recieverUserId, String message) {
        try {
            messageDAO.addMessage(senderUserId,recieverUserId,message);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Message> getMessages(int userId1, int userId2) {
        try {
            return messageDAO.getMessages(userId1,userId2);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
