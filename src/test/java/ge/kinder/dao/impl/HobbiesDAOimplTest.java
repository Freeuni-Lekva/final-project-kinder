package ge.kinder.dao.impl;

import ge.kinder.DAO.DAOimpl.HobbiesDAOimpl;
import ge.kinder.DAO.DAOimpl.ImagesDAOimpl;
import ge.kinder.DAO.DAOimpl.LikesDAOimpl;
import ge.kinder.DAO.DAOimpl.UserDAOimpl;
import ge.kinder.DAO.HobbiesDAO;
import ge.kinder.DAO.ImagesDAO;
import ge.kinder.DAO.LikesDAO;
import ge.kinder.DAO.UserDAO;
import ge.kinder.Database.MyDatabase;
import ge.kinder.Models.Hobby;
import ge.kinder.Models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class HobbiesDAOimplTest {
    private static Connection connection;
    private static UserDAO userDAO;
    private static  HobbiesDAO hobbiesDAO;
    private static ImagesDAO imagesDAO;
    private static LikesDAO likesDAO;

    @BeforeAll
    static void setUp() throws SQLException {
        MyDatabase db = new MyDatabase();
        connection = db.getConnection();
        hobbiesDAO = new HobbiesDAOimpl(connection);
        imagesDAO = new ImagesDAOimpl(connection);
        likesDAO = new LikesDAOimpl(connection);
        userDAO = new UserDAOimpl(connection,hobbiesDAO,imagesDAO,likesDAO);
    }
    @Test
    void addHobbyTest() throws SQLException {
        //create user1
        User megi = new User();
        megi.setMail("megi.shashikadze.99@gmail.com");
        megi.setFirst_name("Megi");
        LocalDate date = LocalDate.of(1999,10,4);
        megi.setBirth_date(Date.valueOf(date));
        megi.setCity("Batumi");
        megi.setGender("Female");
        megi.setGenderIsShown(0);
        megi.setGenderPref("Men");
        megi.setOrientation("Straight");
        //create user2
        User kakhi = new User();
        kakhi.setMail("kkura18@freeuni.edu.ge");
        kakhi.setFirst_name("Kakhi");
        date = LocalDate.of(2000,6,20);
        kakhi.setBirth_date(Date.valueOf(date));
        kakhi.setCity("Gori");
        kakhi.setGender("Male");
        kakhi.setGenderIsShown(0);
        kakhi.setGenderPref("Women");
        kakhi.setOrientation("Straight");
        //add users
        ArrayList<Hobby> hobbies = new ArrayList<>();
        hobbies.add(Hobby.valueOf("SPORT"));
        hobbies.add(Hobby.valueOf("CARS"));
        megi.setHobbies(hobbies);
        kakhi.setHobbies(hobbies);
        userDAO.addUser(megi);
        userDAO.addUser(kakhi);

        hobbiesDAO.addHobby(Hobby.CARS,1);
        hobbiesDAO.addHobby(Hobby.SPORT,1);
        hobbiesDAO.addHobby(Hobby.CARS,2);
        hobbiesDAO.addHobby(Hobby.SPORT,2);

    }
    @Test
    void getHobbiesTest() throws SQLException {
        //create user1
        User megi = new User();
        megi.setMail("megi.shashikadze.99@gmail.com");
        megi.setFirst_name("Megi");
        LocalDate date = LocalDate.of(1999,10,4);
        megi.setBirth_date(Date.valueOf(date));
        megi.setCity("Batumi");
        megi.setGender("Female");
        megi.setGenderIsShown(0);
        megi.setGenderPref("Men");
        megi.setOrientation("Straight");
        //create user2
        User kakhi = new User();
        kakhi.setMail("kkura18@freeuni.edu.ge");
        kakhi.setFirst_name("Kakhi");
        date = LocalDate.of(2000,6,20);
        kakhi.setBirth_date(Date.valueOf(date));
        kakhi.setCity("Gori");
        kakhi.setGender("Male");
        kakhi.setGenderIsShown(0);
        kakhi.setGenderPref("Women");
        kakhi.setOrientation("Straight");
        //add users
        ArrayList<Hobby> hobbies = new ArrayList<>();
        hobbies.add(Hobby.valueOf("SPORT"));
        hobbies.add(Hobby.valueOf("CARS"));
        megi.setHobbies(hobbies);
        kakhi.setHobbies(hobbies);
        userDAO.addUser(megi);
        userDAO.addUser(kakhi);

        hobbiesDAO.addHobby(Hobby.CARS,1);
        hobbiesDAO.addHobby(Hobby.SPORT,1);
        hobbiesDAO.addHobby(Hobby.CARS,2);
        hobbiesDAO.addHobby(Hobby.SPORT,2);

        assertEquals(2,hobbiesDAO.getHobbies(1).size());
        assertEquals(2,hobbiesDAO.getHobbies(2).size());
        assertEquals(0,hobbiesDAO.getHobbies(3).size());
        assertEquals(Hobby.valueOf("CARS"),hobbiesDAO.getHobbies(1).get(0));
        assertEquals(Hobby.valueOf("CARS"),hobbiesDAO.getHobbies(2).get(0));

        hobbiesDAO.deleteHobby(Hobby.INSTAGRAM,1);
        assertEquals(2,hobbiesDAO.getHobbies(1).size());
        hobbiesDAO.deleteHobby(Hobby.CARS,1);
        assertEquals(1,hobbiesDAO.getHobbies(1).size());


    }
}
