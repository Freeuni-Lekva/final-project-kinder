package ge.kinder.Listeners;

import ge.kinder.DAO.DAOimpl.UserDAOimpl;
import ge.kinder.DAO.UserDAO;
import ge.kinder.Database.MyDatabase;
import ge.kinder.Security.Authentificator;
import ge.kinder.Services.Implementation.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;
import java.util.HashMap;

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
        UserDAO userDAO = new UserDAOimpl(database.getConnection());
        Authentificator authentificator = new Authentificator(new HashMap<>());
        sc.setAttribute("USER_SERVICE",new UserServiceImpl(userDAO,authentificator));







    }


}
