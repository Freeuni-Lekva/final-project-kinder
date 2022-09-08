package ge.kinder.DAO;

import ge.kinder.Models.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageDAO {

    void addMessage(int chat_id, String msg, int user_id_1) throws SQLException;

    void addMessage(int user_id_1, int user_id_2, String msg) throws SQLException; //works

    List<Message> getMessages(int chat_id) throws SQLException; //works

    int getChatId(int user_id_1, int user_id_2) throws SQLException;


    void deleteMessage(int message_id) throws SQLException; // works








}