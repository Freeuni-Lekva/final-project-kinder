import ge.kinder.DAO.DAOimpl.*;
import ge.kinder.DAO.MatchesDAO;
import ge.kinder.DAO.MessageDAO;
import ge.kinder.Database.MyDatabase;
import ge.kinder.Models.*;
import ge.kinder.Models.DTO.UserDTO;

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
        user_1.setCity("Tbilisi");
        user_1.setGender("Female");
        user_1.setGenderIsShown(0);
        user_1.setGenderPref("Men");
        user_1.setOrientation("Straight");
        user_1.setMin_age(15);
        user_1.setMax_age(46);

        User user_3 = new User();
        user_3.setMail("megze.99@gmail.com");
        user_3.setFirst_name("Sofia");

        user_3.setBirth_date(Date.valueOf(date));
        user_3.setCity("Tbilisi");
        user_3.setGender("Female");
        user_3.setGenderIsShown(0);
        user_3.setGenderPref("Men");
        user_3.setOrientation("Straight");


        User user_2 = new User();
        user_2.setMail("ainau18@freeuni.edu.ge");
        user_2.setFirst_name("Aleksi");
         date = LocalDate.of(1975,10,6);
        user_2.setBirth_date(Date.valueOf(date));
        user_2.setCity("Tbilisi");
        user_2.setGender("Male");
        user_2.setGenderIsShown(0);
        user_2.setGenderPref("Women");
        user_2.setOrientation("Straight");

        userDAOimpl.addUser(user_1);
        userDAOimpl.addUser(user_2);
        userDAOimpl.addUser(user_3);


        LikesDAOimpl likesDAOimpl = new LikesDAOimpl(connection);
        likesDAOimpl.addLike(user_1.getUser_id(),user_2.getUser_id(), String.valueOf(Status.LIKE));
        likesDAOimpl.addLike(user_2.getUser_id(),user_1.getUser_id(), String.valueOf(Status.LIKE));
        likesDAOimpl.addLike(user_1.getUser_id(),user_3.getUser_id(), String.valueOf(Status.LIKE));
        likesDAOimpl.addLike(user_3.getUser_id(),user_1.getUser_id(), String.valueOf(Status.LIKE));

//        System.out.println(likesDAOimpl.isLiked(user_1.getUser_id(),user_2.getUser_id()));
//        likesDAOimpl.deleteLike(user_1.getUser_id(),user_2.getUser_id());
//        likesDAOimpl.addLike(user_1.getUser_id(),user_2.getUser_id(), String.valueOf(Status.LIKE));
//        System.out.println(likesDAOimpl.isLiked(user_1.getUser_id(),user_2.getUser_id()));
//        likesDAOimpl.addLike(user_3.getUser_id(),user_2.getUser_id(), String.valueOf(Status.LIKE));
//        likesDAOimpl.deleteLike(user_1.getUser_id(),user_2.getUser_id());
//        System.out.println(likesDAOimpl.numberOfLikes(user_2.getUser_id()));

////        System.out.println(userDAOimpl.userExists(user_1.getMail()));
////        System.out.println(userDAOimpl.userExists(user_2.getMail()));
//        userDAOimpl.updateRow(user_1,User.USER_BALANCE,50);
//        userDAOimpl.updateRow(user_2,User.USER_CITY,"Tbilisi");
//        userDAOimpl.updateRow(user_1,User.USER_BIRTH_DATE, java.sql.Date.valueOf(LocalDate.of(1999,10,3)));
//        //System.out.println(userDAOimpl.deleteUser(user_1));

//       hobbiesDAO.addHobby(Hobby.FOOTBALL,user_1.getUser_id());
//       hobbiesDAO.addHobby(Hobby.CARS,user_1.getUser_id());
//
//        User user = userDAOimpl.getUserByMail(user_1.getMail());
////        System.out.println(user.getUser_id());
//        System.out.println(user.getBio());
//        System.out.println(user.getFirst_name());
//        ArrayList<Hobby> h = (ArrayList<Hobby>) user.getHobbies();
//        System.out.println(h.get(0));
//
//        hobbiesDAO.deleteHobby(Hobby.FOOTBALL,user_1.getUser_id());
        //userDAOimpl.deleteUser(user);

//        imagesDAO.addImage("first",user_1.getUser_id());
//        imagesDAO.setImage("first",user_1.getUser_id());
//       // TimeUnit.MINUTES.sleep(1);
//        imagesDAO.addImage("second",user_1.getUser_id());
//        imagesDAO.addImage("third",user_1.getUser_id());
//      //  imagesDAO.setImage("second",user_1.getUser_id());
//       imagesDAO.deleteImage("first",user_1.getUser_id());
//       List<String> images = imagesDAO.getImages(user_1.getUser_id());
//       for(String img : images){
//           System.out.println(img);
//       }

       //userDAOimpl.deleteUser(user_1);

//            List<UserDTO> list = userDAOimpl.getUsers("Tbilisi",user_1.getUser_id());
//       for(UserDTO u : list){
//           System.out.println(u.getFirst_name());
//
//       }
//        List<UserDTO> list_1 = userDAOimpl.getUsers(user_1.getMin_age(),user_1.getMax_age(),"Tbilisi",user_1.getUser_id());
//        for(UserDTO u : list_1){
//            System.out.println("ki " + u.getFirst_name());
//
//        }

        MessageDAOimpl messageDAOimpl = new MessageDAOimpl(connection);
        MatchesDAOimpl matchesDAOimpl = new MatchesDAOimpl(connection,messageDAOimpl,likesDAOimpl);
        matchesDAOimpl.addMatch(user_1.getUser_id(),user_2.getUser_id());
        matchesDAOimpl.addMatch(user_1.getUser_id(),user_3.getUser_id());
        //matchesDAOimpl.deleteMatch(user_1.getUser_id(),user_2.getUser_id());


       messageDAOimpl.addMessage(user_1.getUser_id(),user_2.getUser_id(),"hello");
        messageDAOimpl.addMessage(user_2.getUser_id(),user_1.getUser_id(),"debilo");

        messageDAOimpl.addMessage(user_1.getUser_id(),user_3.getUser_id(),"h");
        messageDAOimpl.addMessage(user_3.getUser_id(),user_1.getUser_id(),"he");
        messageDAOimpl.addMessage(user_1.getUser_id(),user_3.getUser_id(),"hel");
        messageDAOimpl.deleteMessage(5);
        List<Message> messages = messageDAOimpl.getMessages(user_1.getUser_id(),user_3.getUser_id());
        for(Message m : messages){
            System.out.println(m.getMessage_text());
            System.out.println(m.getChat_id());
            System.out.println(m.getUser_id());
            System.out.println(m.getSend_date());
            System.out.println(m.getMessage_id());
        }

        userDAOimpl.deleteUser(user_1);

    }

}
