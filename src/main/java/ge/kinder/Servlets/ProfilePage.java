package ge.kinder.Servlets;

import ge.kinder.DAO.DAOimpl.UserDAOimpl;
import ge.kinder.Models.User;
import ge.kinder.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfilePage", urlPatterns = "/ProfilePage")
public class ProfilePage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/MainPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
        UserDAOimpl userDAOimpl = (UserDAOimpl) req.getServletContext().getAttribute("USERDAO");

        User user = (User) req.getSession().getAttribute("user");

        String mainPageButton = req.getParameter("mainPageButton");

        if (mainPageButton != null) {

            if (mainPageButton.equals("toSettings")) {
                req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);

            } else if (mainPageButton.equals("matches")) {
                req.getRequestDispatcher("/WEB-INF/MainPage.jsp").forward(req, resp);

            } else if(mainPageButton.equals("messages")){
                req.getRequestDispatcher("/WEB-INF/Messages.jsp").forward(req, resp);
            } else if(mainPageButton.equals("messages")) {
                req.getRequestDispatcher("/WEB-INF/Messages.jsp").forward(req, resp);
            } else if(mainPageButton.equals("messages")) {
                req.getRequestDispatcher("/WEB-INF/Messages.jsp").forward(req, resp);
            }
        }
    }
}
