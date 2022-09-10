//import ge.kinder.DAO.DAOimpl.*;
//import ge.kinder.DAO.MatchesDAO;
//import ge.kinder.DAO.MessageDAO;
//import ge.kinder.Database.MyDatabase;
//import ge.kinder.Models.*;
//import ge.kinder.Models.DTO.UserDTO;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.SQLException;
import ge.kinder.DAO.DAOimpl.*;
import ge.kinder.DAO.LikesDAO;
import ge.kinder.Database.MyDatabase;
import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.User;
import ge.kinder.Security.Authentificator;
import ge.kinder.Services.Implementation.SuggestionServiceImpl;
import ge.kinder.Services.Implementation.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class main {

    public static void main(String[] args) throws SQLException, InterruptedException, SQLException {
        MyDatabase myDatabase = new MyDatabase();

        Connection connection = myDatabase.getConnection();


        HobbiesDAOimpl hobbiesDAO  = new HobbiesDAOimpl(connection);
        ImagesDAOimpl imagesDAO = new ImagesDAOimpl(connection);
        LikesDAOimpl likesDAO = new LikesDAOimpl(connection,imagesDAO,hobbiesDAO);
        UserDAOimpl userDAOimpl = new UserDAOimpl(connection,hobbiesDAO,imagesDAO,likesDAO);
        UserServiceImpl userService = new UserServiceImpl(userDAOimpl,new Authentificator(new HashMap<>()));
        System.out.println(likesDAO.isLiked(1,2));
        System.out.println(likesDAO.isDisliked(1,2));
//        SuggestionServiceImpl suggestionService = new SuggestionServiceImpl(userDAOimpl,new PremiumUserDAOimpl());
//
//        User user = userDAOimpl.getUserByMail("kakhikurasbediani@gmail.com");
//        List<UserDTO> users = suggestionService.getSuggestions(user);
//        //List<UserDTO> users = userDAOimpl.getUsers(user.getMin_age(),user.getMax_age(),user.getCity(),user.getUser_id());
//        System.out.println("users " + users);
//}
//
//
//}}
    }}
