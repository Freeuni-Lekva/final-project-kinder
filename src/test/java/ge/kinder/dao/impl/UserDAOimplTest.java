//
//
//package ge.kinder.dao.impl;
//import ge.kinder.DAO.DAOimpl.HobbiesDAOimpl;
//import ge.kinder.DAO.DAOimpl.ImagesDAOimpl;
//import ge.kinder.DAO.DAOimpl.LikesDAOimpl;
//import ge.kinder.DAO.DAOimpl.UserDAOimpl;
//import ge.kinder.DAO.HobbiesDAO;
//import ge.kinder.DAO.ImagesDAO;
//import ge.kinder.DAO.LikesDAO;
//import ge.kinder.DAO.UserDAO;
//import ge.kinder.Database.MyDatabase;
//import ge.kinder.Models.User;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class UserDAOimplTest {
//    private static Connection connection;
//    private static UserDAO userDAO;
//
//    @BeforeAll
//    static void setUp() throws SQLException {
//        MyDatabase db = new MyDatabase();
//        connection = db.getConnection();
//        HobbiesDAO hobbiesDAO = new HobbiesDAOimpl(connection);
//        ImagesDAO imagesDAO = new ImagesDAOimpl(connection);
//        LikesDAO likesDAO = new LikesDAOimpl(connection);
//        userDAO = new UserDAOimpl(connection,hobbiesDAO,imagesDAO,likesDAO);
//    }
//    @Test
//    void addUserTest() throws SQLException {
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
//        //add users
//        userDAO.addUser(megi);
//        userDAO.addUser(kakhi);
//    }
//    @Test
//    void userExistsTest() throws SQLException {
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
//        //add users
//        userDAO.addUser(megi);
//        userDAO.addUser(kakhi);
//        assertTrue(userDAO.userExists("megi.shashikadze.99@gmail.com"));
//        assertTrue(userDAO.userExists("kkura18@freeuni.edu.ge"));
//        assertFalse(userDAO.userExists("megi.shashikadze.99"));
//        assertFalse(userDAO.userExists("kkura18"));
//    }
//    @Test
//    void getUserByMailTest() throws SQLException {
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
//        //add users
//        userDAO.addUser(megi);
//        userDAO.addUser(kakhi);
//
//        assertEquals("Megi",userDAO.getUserByMail("megi.shashikadze.99@gmail.com").getFirst_name());
//        assertEquals("Kakhi",userDAO.getUserByMail("kkura18@freeuni.edu.ge").getFirst_name());
//
//    }
//    @Test
//    void getUsersCityTest() throws SQLException {
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
//        //add users
//        userDAO.addUser(megi);
//        userDAO.addUser(kakhi);
//        userDAO.addUser(lasha);
//
//        assertEquals(1,userDAO.getUsers("Gori",3).size());
//        assertEquals("Kakhi",userDAO.getUsers("Gori",3).get(0).getFirst_name());
//        assertEquals("Kakhi",userDAO.getUsers("Gori",3).get(0).getFirst_name());
//        assertEquals(0,userDAO.getUsers("Batumi",1).size());
//
//    }
//    @Test
//    void getUsersAgeTest() throws SQLException {
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
//        //add users
//        userDAO.addUser(megi);
//        userDAO.addUser(kakhi);
//        userDAO.addUser(lasha);
//
//        //Name Check
//        assertEquals("Megi",userDAO.getUsers(21,24,"Batumi",3).get(0).getFirst_name());
//        assertEquals("Kakhi",userDAO.getUsers(21,24,"Gori",3).get(0).getFirst_name());
//        userDAO.updateRow(megi, User.USER_CITY,"Gori");
//        //City Check
//        System.out.println(userDAO.getUsers(21,24,"Gori",3));
//        assertEquals(2,userDAO.getUsers(21,24,"Gori",3).size());
//        userDAO.updateRow(lasha, User.USER_CITY,"Xashuri");
//        //Id and age check
//        assertEquals(0,userDAO.getUsers(21,24,"Xashuri",3).size());
//        assertEquals(0,userDAO.getUsers(22,24,"Xashuri",2).size());
//        assertEquals(1,userDAO.getUsers(21,24,"Xashuri",2).size());
//
//    }
//
//}
