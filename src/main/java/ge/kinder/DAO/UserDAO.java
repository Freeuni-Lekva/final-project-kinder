
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

    User getUserByID(int userID) throws SQLException;// works




    UserDTO getUser(String city, int user_id) throws SQLException;

    // აქ უნდა გავითვალისწინოთ, რომ როდესაც ვამატებთ სიაში იუზერს, უნდა შევამოწმოთ :
    // 1) დისლაიქი ხომ არ დამიწერა. თუ დამიწერა, აღარ ვაგდებ სიაში. done
    // 2) თუ პრემიუმია, ხომ არ უნდა, რომ მხოლოდ დალაიქებულებთან გამოჩნდეს.თუ კი, ვამოწმებ, დამალაიქა,თუ არა.
    // 3) დაჰაიდებული ხომ არაა? done
    // 4) წესით ეს სია რომ ამოვიღე და მერე დისლაიქი დამიწერა ამ ტიპმა, სანამ ამ სიაში მაგ ადამიანამდე მივედი, მაშინ უნდა განახლდეს სია. done
    // 5) პრეფერენცია ქალი თუ კაცი.

    List<UserDTO> getUsers(String city, int user_id) throws SQLException;
    //works without taking into account special cases
    UserDTO getUser(int min_age, int max_age, String city, int user_id) throws SQLException;
    List<UserDTO> getUsers(int min_age, int max_age, String city, int user_id) throws SQLException;
    List<User> getBannedUsers() throws SQLException;

    List<User> getAllUsers() throws SQLException;
}