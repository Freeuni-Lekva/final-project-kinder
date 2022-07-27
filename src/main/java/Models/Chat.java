package Models;

import java.util.List;

public class Chat {

    User first_user;
    User second_user;

    // eg ar vici tu unda ak
    List<Message> messages;

    public Chat(User first_user, User second_user) {
        this.first_user = first_user;
        this.second_user = second_user;
    }

    public User getFirst_user() {
        return first_user;
    }

    public void setFirst_user(User first_user) {
        this.first_user = first_user;
    }

    public User getSecond_user() {
        return second_user;
    }

    public void setSecond_user(User second_user) {
        this.second_user = second_user;
    }
}
