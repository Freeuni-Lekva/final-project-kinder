package ge.kinder.Servlets;

import ge.kinder.DAO.DAOimpl.UserDAOimpl;
import ge.kinder.DAO.UserDAO;
import ge.kinder.Models.User;
import ge.kinder.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Settings", urlPatterns = "/Settings")
public class Settings extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (!(req.getSession() != null && req.getSession().getAttribute("user") != null)) {
//            req.getRequestDispatcher("/index.jsp").forward(req, resp);
//        } else {
//            req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
//        }
        req.getRequestDispatcher("/WEB-INF/Settings/Preference.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
        UserDAOimpl userDAOimpl = (UserDAOimpl) req.getServletContext().getAttribute("USERDAO");

        String otp = req.getParameter("VERIFICATION_CODE");
        User user = (User) req.getSession().getAttribute("user");
        String newEmail = (String) req.getSession().getAttribute("newMail");
        System.out.println(newEmail);
        String settings = req.getParameter("settingsButton");
        String verification = req.getParameter("verification");
        String city = req.getParameter("City");
        String preference = req.getParameter("preference");


        if (settings != null) {

            if (settings.equals("Email")) {
                req.getRequestDispatcher("/WEB-INF/Settings/Email.jsp").forward(req, resp);

            } else if (settings.equals("City")) {
                req.getRequestDispatcher("/WEB-INF/Settings/City.jsp").forward(req, resp);

            }else if(settings.equals("Preference")){
                req.getRequestDispatcher("/WEB-INF/Settings/Preference.jsp").forward(req, resp);
            }
        }

        if (verification != null && verification.equals("verificationCode")) {
            if (userService.confirmCode(otp)) {
                req.getSession().setAttribute("mail", newEmail);
                user.setMail(newEmail);
                System.out.println(newEmail);
                userDAOimpl.updateRow(user, User.USER_MAIL, newEmail);
                req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
            } else System.out.println("nope");
        }
        if (city != null) {
            user.setCity(city);
            userDAOimpl.updateRow(user, User.USER_CITY, city);
            req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
        } else {

        }

        if (preference != null) {
            user.setGenderPref(preference);
            userDAOimpl.updateRow(user, User.USER_PREFERENCE, preference);
            req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
        } else {

        }




    }
}



