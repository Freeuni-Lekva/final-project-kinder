package ge.kinder.DAO;

import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO  {

    void addUser(User user) throws SQLException;  // works

    void updateRow(User user, String rowName, int value); //works
    void updateRow(User user, String rowName, String value); //works
    void updateRow(User user, String rowName, java.sql.Date value); //works

    boolean deleteUser(User user) throws SQLException; // works

    boolean userExists(String mail) throws SQLException; // works
    User getUserByMail(String mail) throws SQLException;// works






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
