package DAO;

import Models.User;

import java.util.List;

public interface UserDAO  {

    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);

    boolean userExists(String mail);
    User getUserByMail(String mail);


    // აქ უნდა გავითვალისწინოთ, რომ როდესაც ვამატებთ სიაში იუზერს, უნდა შევამოწმოთ :
    // 1) დისლაიქი ხომ არ დამიწერა. თუ დამიწერა, აღარ ვაგდებ სიაში.
    // 2) თუ პრემიუმია, ხომ არ უნდა, რომ მხოლოდ დალაიქებულებთან გამოჩნდეს.თუ კი, ვამოწმებ, დამალაიქა,თუ არა.
    // 3) დაჰაიდებული ხომ არაა?
    // 4) წესით ეს სია რომ ამოვიღე და მერე დისლაიქი დამიწერა ამ ტიპმა, სანამ ამ სიაში მაგ ადამიანამდე მივედი, მაშინ უნდა განახლდეს სია.
    // მაგაზე უნდა შევთანხმდეთ როგორ ვიზამთ
    List<User> getUsers(String city); // ეს ჩველებრივი ძებნა
    List<User> getUsers(boolean liked, boolean most_recent,String city); // ეს პრემიუმისთვისაა. შეუძლია ასევე ესენი მიუთითოს.
    List<User> getUsers(int min_age, int max_age,String city); // ეს პრეფერენციებით
    List<User> getUsers(int min_age, int max_age,boolean liked,boolean most_recent,String city); // ეს პრეფერენციებით პრემიუმისათვის


    // and so on...


}
