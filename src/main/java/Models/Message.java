package Models;

import java.util.Date;

public class Message {

    User sender;
    // mgoni shegvidzlia es amovigot
    // kaxi ro ambobda nivtze informacia unda iyos kalatashi da ara pirikit
    Chat chat;

    String message_text;
    Date send_date;

    public Message(User sender, Chat chat, String message_text, Date send_date) {
        this.sender = sender;
        this.chat = chat;
        this.message_text = message_text;
        this.send_date = send_date;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public Date getSend_date() {
        return send_date;
    }

    public void setSend_date(Date send_date) {
        this.send_date = send_date;
    }
}
