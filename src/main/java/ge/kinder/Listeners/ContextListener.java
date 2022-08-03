package ge.kinder.Listeners;

import ge.kinder.Database.MyDatabase;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

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
        //UserDAO userDAO = new UserDAOimpl(database.getConnection());


    }


}
