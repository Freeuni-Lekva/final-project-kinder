package ge.kinder.DAO;

import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO  {

    void addUser(User user) throws SQLException;  // tested

    void updateRow(User user, String rowName, int value); //tested
    void updateRow(User user, String rowName, String value); //tested
    void updateRow(User user, String rowName, java.sql.Date value); //tested

    boolean deleteUser(User user) throws SQLException; // tested without dependencies

    boolean userExists(String mail) throws SQLException; // tested
    UserDTO getUserByMail(String mail) throws SQLException;// tested, but UserDTO should be changed to User object
    // not tested

    // აქ უნდა გავითვალისწინოთ, რომ როდესაც ვამატებთ სიაში იუზერს, უნდა შევამოწმოთ :
    // 1) დისლაიქი ხომ არ დამიწერა. თუ დამიწერა, აღარ ვაგდებ სიაში.
    // 2) თუ პრემიუმია, ხომ არ უნდა, რომ მხოლოდ დალაიქებულებთან გამოჩნდეს.თუ კი, ვამოწმებ, დამალაიქა,თუ არა.
    // 3) დაჰაიდებული ხომ არაა?
    // 4) წესით ეს სია რომ ამოვიღე და მერე დისლაიქი დამიწერა ამ ტიპმა, სანამ ამ სიაში მაგ ადამიანამდე მივედი, მაშინ უნდა განახლდეს სია.
    // მაგაზე უნდა შევთანხმდეთ როგორ ვიზამთ
    List<UserDTO> getUsers(String city, int user_id) throws SQLException; // ეს ჩველებრივი ძებნა //აქ დავაბრუნოტ UserDTO


    List<UserDTO> getUsers(int min_age, int max_age, String city, int user_id) throws SQLException; // ეს პრეფერენციებით //აქ დავაბრუნოტ UserDTO
        // not tested


    // and so on...


}
