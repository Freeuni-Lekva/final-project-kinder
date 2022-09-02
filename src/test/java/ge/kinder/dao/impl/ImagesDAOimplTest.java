package ge.kinder.dao.impl;

import ge.kinder.DAO.DAOimpl.HobbiesDAOimpl;
import ge.kinder.DAO.DAOimpl.ImagesDAOimpl;
import ge.kinder.DAO.DAOimpl.UserDAOimpl;
import ge.kinder.DAO.HobbiesDAO;
import ge.kinder.DAO.ImagesDAO;
import ge.kinder.DAO.UserDAO;
import ge.kinder.Database.MyDatabase;
import ge.kinder.Models.Hobby;
import ge.kinder.Models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImagesDAOimplTest {
    private static Connection connection;
    private static UserDAO userDAO;
    private static HobbiesDAO hobbiesDAO;
    private static ImagesDAO imagesDAO;

    @BeforeAll
    static void setUp() throws SQLException {
        MyDatabase db = new MyDatabase();
        connection = db.getConnection();
        hobbiesDAO = new HobbiesDAOimpl(connection);
        imagesDAO = new ImagesDAOimpl(connection);
        userDAO = new UserDAOimpl(connection,hobbiesDAO,imagesDAO);
    }
    @Test
    void addImageest() throws SQLException {
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
        ArrayList<String> images = new ArrayList<>();
        images.add("pirveli");
        images.add("meore");
        images.add("mesame");

        megi.setImages(images);
        kakhi.setImages(images);
        List<User> users = new ArrayList<>();
        users.add(megi);
        users.add(kakhi);
        for (User user : users) {
            userDAO.addUser(user);
            for (int j = 0; j < user.getImages().size(); j++) {
                imagesDAO.addImage(user.getImages().get(j), user.getUser_id());
            }
        }

    }
    @Test
    void getImagesTest() throws SQLException {
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

        ArrayList<String> images = new ArrayList<>();
        images.add("pirveli");
        images.add("meore");
        images.add("mesame");

        megi.setImages(images);
        kakhi.setImages(images);

        List<User> users = new ArrayList<>();
        users.add(megi);
        users.add(kakhi);

        for (User user : users) {
            userDAO.addUser(user);
            for (int j = 0; j < user.getImages().size(); j++) {
                imagesDAO.addImage(user.getImages().get(j), user.getUser_id());
            }
        }

        assertEquals(3,imagesDAO.getImages(1).size());
        assertEquals(3,imagesDAO.getImages(2).size());

        assertEquals("pirveli",imagesDAO.getImages(1).get(0));
        assertEquals("pirveli",imagesDAO.getImages(2).get(0));

        imagesDAO.deleteImage("meotxe",1);
        assertEquals(3,imagesDAO.getImages(1).size());
        imagesDAO.deleteImage("mesame",1);
        assertEquals(2,imagesDAO.getImages(1).size());

        imagesDAO.setImage("meore",1);
        imagesDAO.deleteImage("meore",1);


    }
}
