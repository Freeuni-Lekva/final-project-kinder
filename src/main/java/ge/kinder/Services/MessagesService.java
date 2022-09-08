package ge.kinder.Services;

import ge.kinder.Models.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessagesService {

    void addMessage(int senderUserId, int recieverUserId, String message);

    void addMessage(int chat_id, String msg, int user_id_1);

    List<Message> getMessages(int chat_id);

    int getChatId(int user_id_1, int user_id_2);
}


