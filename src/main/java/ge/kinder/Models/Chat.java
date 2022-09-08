package ge.kinder.Models;

import java.util.Collections;
import java.util.List;

public class Chat {
    public static String CHAT_TABLE = "chat";
    public static String CHAT_CHAT_ID = "Chat_Id";

    public static String USER_1 = "User_id_1";
    public static String USER_2 = "User_id_2";

    private int chat_id;
    private int first_user;
    private int second_user;

    private List<Message> messages;

    private String img;
    private String name;

    public Chat(int first_user, int second_user) {
        this.first_user = first_user;
        this.second_user = second_user;

    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public int getFirst_user() {
        return first_user;
    }

    public void setFirst_user(int first_user) {
        this.first_user = first_user;
    }

    public int getSecond_user() {
        return second_user;
    }

    public void setSecond_user(int second_user) {
        this.second_user = second_user;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getImage() {
        return img;
    }

    public void setImage(String image) {
        this.img = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

