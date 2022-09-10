//package ge.kinder.dao.impl;
//
//import ge.kinder.DAO.DAOimpl.HobbiesDAOimpl;
//import ge.kinder.DAO.DAOimpl.ImagesDAOimpl;
//import ge.kinder.DAO.DAOimpl.LikesDAOimpl;
//import ge.kinder.DAO.DAOimpl.UserDAOimpl;
//import ge.kinder.DAO.HobbiesDAO;
//import ge.kinder.DAO.ImagesDAO;
//import ge.kinder.DAO.LikesDAO;
//import ge.kinder.DAO.UserDAO;
//import ge.kinder.Database.MyDatabase;
//import ge.kinder.Models.Status;
//import ge.kinder.Models.User;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//public class LikesDAOimplTest {
//    private static Connection connection;
//    private static UserDAO userDAO;
//    private static HobbiesDAO hobbiesDAO;
//    private static ImagesDAO imagesDAO;
//    private static LikesDAO likesDAO;
//
//    @BeforeAll
//    static void setUp() throws SQLException {
//        MyDatabase db = new MyDatabase();
//        connection = db.getConnection();
//        hobbiesDAO = new HobbiesDAOimpl(connection);
//        imagesDAO = new ImagesDAOimpl(connection);
//        userDAO = new UserDAOimpl(connection,hobbiesDAO,imagesDAO,likesDAO);
//        likesDAO = new LikesDAOimpl(connection,imagesDAO,hobbiesDAO);
//    }
//    @Test
//    void addLikeAndIsLIkedTest() throws SQLException {
//        //create user1
//        User megi = new User();
//        megi.setMail("megi.shashikadze.99@gmail.com");
//        megi.setFirst_name("Megi");
//        LocalDate date = LocalDate.of(1999,10,4);
//        megi.setBirth_date(Date.valueOf(date));
//        megi.setCity("Batumi");
//        megi.setGender("Female");
//        megi.setGenderIsShown(0);
//        megi.setGenderPref("Men");
//        megi.setOrientation("Straight");
//        //create user2
//        User kakhi = new User();
//        kakhi.setMail("kkura18@freeuni.edu.ge");
//        kakhi.setFirst_name("Kakhi");
//        date = LocalDate.of(2000,6,20);
//        kakhi.setBirth_date(Date.valueOf(date));
//        kakhi.setCity("Gori");
//        kakhi.setGender("Male");
//        kakhi.setGenderIsShown(0);
//        kakhi.setGenderPref("Women");
//        kakhi.setOrientation("Straight");
//        //create user3
//        User lasha = new User();
//        lasha.setMail("lgham@freeuni.edu.ge");
//        lasha.setFirst_name("Lasha");
//        date = LocalDate.of(2000,8,29);
//        lasha.setBirth_date(Date.valueOf(date));
//        lasha.setCity("Gori");
//        lasha.setGender("Male");
//        lasha.setGenderIsShown(0);
//        lasha.setGenderPref("Women");
//        lasha.setOrientation("Straight");
//
//        //add users
//        List<User> users = new ArrayList<>();
//        users.add(megi);
//        users.add(kakhi);
//        users.add(lasha);
//        for (User user : users) {
//            userDAO.addUser(user);
//        }
//        likesDAO.addLike(1,2, Status.LIKE);
//        likesDAO.addLike(2,3,Status.DISLIKE);
//        likesDAO.addLike(3,1,Status.SUPERLIKE);
//        assertTrue(likesDAO.isLiked(1,2));
//        assertFalse(likesDAO.isLiked(2,1));
//        assertFalse(likesDAO.isLiked(2,3));
//        assertFalse(likesDAO.isLiked(3,2));
//        assertTrue(likesDAO.isLiked(3,1));
//
//    }
//    @Test
//    void numberOfLikesTest() throws SQLException {
//        //create user1
//        User megi = new User();
//        megi.setMail("megi.shashikadze.99@gmail.com");
//        megi.setFirst_name("Megi");
//        LocalDate date = LocalDate.of(1999,10,4);
//        megi.setBirth_date(Date.valueOf(date));
//        megi.setCity("Batumi");
//        megi.setGender("Female");
//        megi.setGenderIsShown(0);
//        megi.setGenderPref("Men");
//        megi.setOrientation("Straight");
//        //create user2
//        User kakhi = new User();
//        kakhi.setMail("kkura18@freeuni.edu.ge");
//        kakhi.setFirst_name("Kakhi");
//        date = LocalDate.of(2000,6,20);
//        kakhi.setBirth_date(Date.valueOf(date));
//        kakhi.setCity("Gori");
//        kakhi.setGender("Male");
//        kakhi.setGenderIsShown(0);
//        kakhi.setGenderPref("Women");
//        kakhi.setOrientation("Straight");
//        //create user3
//        User lasha = new User();
//        lasha.setMail("lgham@freeuni.edu.ge");
//        lasha.setFirst_name("Lasha");
//        date = LocalDate.of(2000,8,29);
//        lasha.setBirth_date(Date.valueOf(date));
//        lasha.setCity("Gori");
//        lasha.setGender("Male");
//        lasha.setGenderIsShown(0);
//        lasha.setGenderPref("Women");
//        lasha.setOrientation("Straight");
//
//        //add users
//        List<User> users = new ArrayList<>();
//        users.add(megi);
//        users.add(kakhi);
//        users.add(lasha);
//        for (User user : users) {
//            userDAO.addUser(user);
//        }
//        likesDAO.addLike(1,2,Status.LIKE);
//        likesDAO.addLike(2,3,Status.DISLIKE);
//        likesDAO.addLike(3,1,Status.SUPERLIKE);
//        likesDAO.addLike(3,2,Status.LIKE);
//
//        assertEquals(2,likesDAO.numberOfLikes(2));
//        assertEquals(1,likesDAO.numberOfLikes(1));
//        assertEquals(0,likesDAO.numberOfLikes(3));
//    }
//
//    @Test
//    void deleteLikeTest() throws SQLException {
//        //create user1
//        User megi = new User();
//        megi.setMail("megi.shashikadze.99@gmail.com");
//        megi.setFirst_name("Megi");
//        LocalDate date = LocalDate.of(1999,10,4);
//        megi.setBirth_date(Date.valueOf(date));
//        megi.setCity("Batumi");
//        megi.setGender("Female");
//        megi.setGenderIsShown(0);
//        megi.setGenderPref("Men");
//        megi.setOrientation("Straight");
//        //create user2
//        User kakhi = new User();
//        kakhi.setMail("kkura18@freeuni.edu.ge");
//        kakhi.setFirst_name("Kakhi");
//        date = LocalDate.of(2000,6,20);
//        kakhi.setBirth_date(Date.valueOf(date));
//        kakhi.setCity("Gori");
//        kakhi.setGender("Male");
//        kakhi.setGenderIsShown(0);
//        kakhi.setGenderPref("Women");
//        kakhi.setOrientation("Straight");
//        //create user3
//        User lasha = new User();
//        lasha.setMail("lgham@freeuni.edu.ge");
//        lasha.setFirst_name("Lasha");
//        date = LocalDate.of(2000,8,29);
//        lasha.setBirth_date(Date.valueOf(date));
//        lasha.setCity("Gori");
//        lasha.setGender("Male");
//        lasha.setGenderIsShown(0);
//        lasha.setGenderPref("Women");
//        lasha.setOrientation("Straight");
//
//        //add users
//        List<User> users = new ArrayList<>();
//        users.add(megi);
//        users.add(kakhi);
//        users.add(lasha);
//        for (User user : users) {
//            userDAO.addUser(user);
//        }
//        likesDAO.addLike(1,2,Status.LIKE);
//        likesDAO.addLike(2,3,Status.DISLIKE);
//        likesDAO.addLike(3,1,Status.SUPERLIKE);
//        likesDAO.addLike(3,2,Status.LIKE);
//
//        assertEquals(2,likesDAO.numberOfLikes(2));
//        assertEquals(1,likesDAO.numberOfLikes(1));
//
//        likesDAO.deleteLike(1,2);
//        assertEquals(1,likesDAO.numberOfLikes(2));
//        likesDAO.deleteLike(3,1);
//        assertEquals(0,likesDAO.numberOfLikes(1));
//        likesDAO.addLike(3,1,Status.LIKE);
//        assertEquals(1,likesDAO.numberOfLikes(1));
//
//
//    }
//
//}
//
