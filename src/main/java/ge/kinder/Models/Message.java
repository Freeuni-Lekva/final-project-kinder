package ge.kinder.Models;

import java.util.Date;

public class Message {
    public static final String MESSAGE_TABLE = "Messages";
    public static final String MESSAGE_CHAT_ID = "Chat_Id";
    public static final String MESSAGE_MESSAGE_ID = "Message_Id";
    public static final String MESSAGE_MESSAGE_TEXT = "Message_Text";
    public static final String MESSAGE_DATE = "Message_Date";
    public static final String MESSAGE_USER_ID= "User_Id";

    //აქაც UserDTO
    private User sender;
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    private String message_text;
    private Date send_date;
    private int chat_id;

    private int message_id;

    public Message( int chat_id, int message_id, String message_text, Date send_date, int user_id) {
        this.user_id = user_id;
        this.message_text = message_text;
        this.send_date = send_date;
        this.chat_id = chat_id;
        this.message_id = message_id;
    }

    public Message(User sender, Chat chat, String message_text, Date send_date) {


    }

    public User getSender() {
        return sender;
    }

    public String getMessage_text() {
        return message_text;
    }

    public Date getSend_date() {
        return send_date;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }
}
