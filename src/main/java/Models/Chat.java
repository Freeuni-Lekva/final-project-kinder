package Models;

import DAO.DAOimpl.MessageDAOimpl;

import java.util.Collections;
import java.util.List;

public class Chat {
    int chat_id;
    User first_user;
    User second_user;

    List<Message> messages;

    public Chat(User first_user, User second_user) {
        this.first_user = first_user;
        this.second_user = second_user;

    }

    public int getChat_id() {
        return chat_id;
    }

    public List<Message> getMessages() {
       return Collections.emptyList(); // to be implemented

        // daaxloebit aseti ragac ikneba
       // MessageDAOimpl  ms = new MessageDAOimpl();
        //List<Message> list= ms.getMessages(chat_id);
        //return list;
    }


    public User getFirst_user() {
        return first_user;
    }

    public User getSecond_user() {
        return second_user;
    }

}
