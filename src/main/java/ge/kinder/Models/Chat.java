package ge.kinder.Models;

import java.util.Collections;
import java.util.List;

public class Chat {
    public static String CHAT_TABLE = "chat";
    public static String CHAT_CHAT_ID = "Chat_Id";

    public static String USER_1 ="User_id_1";
    public static String USER_2 ="User_id_2";

    private int  chat_id;
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
