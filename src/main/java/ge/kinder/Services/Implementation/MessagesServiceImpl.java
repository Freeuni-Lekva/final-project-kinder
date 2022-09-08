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
    public void addMessage(int chat_id, String msg, int user_id_1) {
        try {
            messageDAO.addMessage( chat_id,  msg,  user_id_1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Message> getMessages(int chat_id) {
        try {
            return messageDAO.getMessages(chat_id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getChatId(int user_id_1, int user_id_2) {
        try {
            return messageDAO.getChatId(user_id_1,user_id_2);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

