package DAO;

import Models.Chat;
import Models.Message;
import Models.User;

import java.util.List;

public interface MessageDAO {

    void addMessage(Message message);

    // delete gvkondes?
    void deleteMessage(Message message);

    List<Message> getMessages(int chat_id);

    // and so on...




}
