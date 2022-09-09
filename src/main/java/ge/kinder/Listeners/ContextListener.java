package ge.kinder.Listeners;

import ge.kinder.DAO.DAOimpl.*;
import ge.kinder.DAO.HobbiesDAO;
import ge.kinder.DAO.ImagesDAO;
import ge.kinder.DAO.LikesDAO;
import ge.kinder.DAO.UserDAO;
import ge.kinder.Database.MyDatabase;
import ge.kinder.Models.Message;
import ge.kinder.Security.Authentificator;
import ge.kinder.Services.Implementation.*;
import ge.kinder.Services.MessagesService;
import ge.kinder.Services.SuggestionService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        MyDatabase database;
        try {
            database = new MyDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Connection connection =  database.getConnection();
        HobbiesDAOimpl hobbiesDAO  = new HobbiesDAOimpl(connection);
        ImagesDAOimpl imagesDAO = new ImagesDAOimpl(connection);
        LikesDAOimpl likesDAO = new LikesDAOimpl(connection);
        MessageDAOimpl messagesDAO = new MessageDAOimpl(connection);

        UserDAO userDAO = new UserDAOimpl(connection,hobbiesDAO,imagesDAO,likesDAO);
        PremiumUserDAOimpl premiumUserDAOimpl = new PremiumUserDAOimpl(connection,hobbiesDAO,imagesDAO,likesDAO);
        Authentificator authentificator = new Authentificator(new HashMap<>());
        sc.setAttribute("USER_SERVICE",new UserServiceImpl(userDAO,authentificator));
        sc.setAttribute("USERDAO",userDAO);
        sc.setAttribute("IMAGESDAO",imagesDAO);
        sc.setAttribute("HOBBIESDAO",hobbiesDAO);
        sc.setAttribute("SUGGESTION_SERVICE", new SuggestionServiceImpl(userDAO,premiumUserDAOimpl));
        sc.setAttribute("LIKES_SERVICE", new LikesServiceImpl(userDAO,likesDAO));
        sc.setAttribute("MATCHES_SERVICE", new MatchesServiceImpl(new MatchesDAOimpl(connection,messagesDAO,likesDAO)));
        sc.setAttribute("MESSAGED_SERVICE", messagesDAO);
        sc.setAttribute("MESSAGES_SERVICE", new MessagesServiceImpl(messagesDAO));








    }


}
