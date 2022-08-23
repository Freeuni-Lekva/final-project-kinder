import ge.kinder.DAO.DAOimpl.HobbiesDAOimpl;
import ge.kinder.DAO.DAOimpl.ImagesDAOimpl;
import ge.kinder.DAO.DAOimpl.UserDAOimpl;
import ge.kinder.Database.MyDatabase;
import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.Hobby;
import ge.kinder.Models.Role;
import ge.kinder.Models.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class main {

    public static void main(String[] args) throws SQLException, InterruptedException {
        MyDatabase myDatabase = new MyDatabase();

        Connection connection = myDatabase.getConnection();


        HobbiesDAOimpl hobbiesDAO  = new HobbiesDAOimpl(connection);
        ImagesDAOimpl imagesDAO = new ImagesDAOimpl(connection);
        UserDAOimpl userDAOimpl = new UserDAOimpl(connection,hobbiesDAO,imagesDAO);

        User user_1 = new User();
        user_1.setMail("megi.shashikadze.99@gmail.com");
        user_1.setFirst_name("Megi");
        LocalDate date = LocalDate.of(1999,10,4);
        user_1.setBirth_date(Date.valueOf(date));
        user_1.setCity("Batumi");
        user_1.setGender("Female");
        user_1.setGenderIsShown(0);
        user_1.setGenderPref("Men");
        user_1.setOrientation("Straight");


        User user_2 = new User();
        user_2.setMail("ainau18@freeuni.edu.ge");
        user_2.setFirst_name("Aleksi");
         date = LocalDate.of(2000,10,6);
        user_2.setBirth_date(Date.valueOf(date));
        user_2.setCity("Gori");
        user_2.setGender("Male");
        user_2.setGenderIsShown(0);
        user_2.setGenderPref("Women");
        user_2.setOrientation("Straight");

        userDAOimpl.addUser(user_1);
        userDAOimpl.addUser(user_2);

//        System.out.println(userDAOimpl.userExists(user_1.getMail()));
//        System.out.println(userDAOimpl.userExists(user_2.getMail()));
        userDAOimpl.updateRow(user_1,User.USER_BALANCE,50);
        userDAOimpl.updateRow(user_2,User.USER_CITY,"Tbilisi");
        userDAOimpl.updateRow(user_1,User.USER_BIRTH_DATE, java.sql.Date.valueOf(LocalDate.of(1999,10,3)));
        //System.out.println(userDAOimpl.deleteUser(user_1));

       hobbiesDAO.addHobby(Hobby.FOOTBALL,user_1.getUser_id());
       hobbiesDAO.addHobby(Hobby.CARS,user_1.getUser_id());

        User user = userDAOimpl.getUserByMail(user_1.getMail());
        System.out.println(user.getUser_id());
        System.out.println(user.getBio());
        System.out.println(user.getFirst_name());
        ArrayList<Hobby> h = (ArrayList<Hobby>) user.getHobbies();
        System.out.println(h.get(0));

        hobbiesDAO.deleteHobby(Hobby.FOOTBALL,user_1.getUser_id());
        //userDAOimpl.deleteUser(user);

        imagesDAO.addImage("first",user_1.getUser_id());
        imagesDAO.setImage("first",user_1.getUser_id());
       // TimeUnit.MINUTES.sleep(1);
        imagesDAO.addImage("second",user_1.getUser_id());
        imagesDAO.addImage("third",user_1.getUser_id());
      //  imagesDAO.setImage("second",user_1.getUser_id());
       imagesDAO.deleteImage("first",user_1.getUser_id());
       List<String> images = imagesDAO.getImages(user_1.getUser_id());
       for(String img : images){
           System.out.println(img);
       }

       userDAOimpl.deleteUser(user_1);
//
//

    }
}
