package ge.kinder.Services;

import ge.kinder.Models.Message;
import java.util.List;

public interface MessagesService {

    void addMessage(int senderUserId, int recieverUserId, String message);

    List<Message> getMessages(int userId1, int userId2);


}
