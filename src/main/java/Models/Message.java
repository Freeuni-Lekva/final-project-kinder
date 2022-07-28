package Models;

import java.util.Date;

public class Message {

    User sender;
    String message_text;
    Date send_date;

    public Message(User sender, Chat chat, String message_text, Date send_date) {
        this.sender = sender;
        this.message_text = message_text;
        this.send_date = send_date;
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


}
