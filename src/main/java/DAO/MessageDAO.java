package DAO;

import Models.Chat;
import Models.Message;
import Models.User;

public interface MessageDAO {

    void addMessage(Message message);

    // delete gvkondes?
    void deleteMessage(Message message);

    Message getMessages(Chat chat);




}
