package ge.kinder.DAO;

import ge.kinder.Models.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageDAO {

    void addMessage(int user_id_1,int user_id_2,String msg) throws SQLException; //works

    List<Message> getMessages(int user_id_1,int user_id_2) throws SQLException; //works


    void deleteMessage(int message_id) throws SQLException; // works








}
