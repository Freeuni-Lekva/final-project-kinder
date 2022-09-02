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
import java.sql.SQLException;


@WebServlet(name = "Settings", urlPatterns = "/Settings")
public class Settings extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!(req.getSession() != null && req.getSession().getAttribute("user") != null)) {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
        UserDAOimpl userDAOimpl = (UserDAOimpl) req.getServletContext().getAttribute("USERDAO");

        String otp = req.getParameter("VERIFICATION_CODE");
        User user = (User) req.getSession().getAttribute("user");
        String newEmail = (String) req.getSession().getAttribute("newMail");

        String settings = req.getParameter("settingsButton");
        String verification = req.getParameter("verification");
        String city = req.getParameter("City");
        String preference = req.getParameter("preference");
        String show = req.getParameter("SHOW");
        String show_me = req.getParameter("SHOW_ME");
        String youSee = req.getParameter("test_1");
        String seesYou = req.getParameter("test");
        String show_activity = req.getParameter("SHOW_ACTIVITY");


        if(youSee!=null){
            user.setShow_active_people(Integer.parseInt(youSee));
            userDAOimpl.updateRow(user,User.SHOW_RECENTLTY_ACTIVE,youSee);
        }

        if(seesYou!=null){
            user.setShow_to_liked(Integer.parseInt(seesYou));
            userDAOimpl.updateRow(user,User.SHOT_TO_LIKED,seesYou);
        }
        if(show !=null) {

            user.setMin_age(Integer.parseInt(req.getParameter("pref_1")));
            user.setMax_age(Integer.parseInt(req.getParameter("pref_2")));
            userDAOimpl.updateRow(user, User.USER_MIN_AGE, user.getMin_age());
            userDAOimpl.updateRow(user, User.USER_MAX_AGE, user.getMax_age());


        } else {
            userDAOimpl.updateRow(user, User.USER_MIN_AGE, 18);
            userDAOimpl.updateRow(user, User.USER_MAX_AGE, 100);
        }
        if(show_me !=null) {

            user.setIs_hided(0);
            userDAOimpl.updateRow(user, User.USER_HIDED,0);

        }else {
            user.setIs_hided(1);
            userDAOimpl.updateRow(user, User.USER_HIDED,1);
        }

        if ( req.getParameter("BackFromCity")!=null || req.getParameter("BackFromPref")!=null) {
            req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
        }

        if (settings != null) {

            if (settings.equals("Email")) {
                req.getRequestDispatcher("/WEB-INF/Settings/Email.jsp").forward(req, resp);

            } else if (settings.equals("City")) {
                req.getRequestDispatcher("/WEB-INF/Settings/City.jsp").forward(req, resp);

            }else if(settings.equals("Preference")){
                req.getRequestDispatcher("/WEB-INF/Settings/Preference.jsp").forward(req, resp);
            } else if(settings.equals("Logout")){
                req.getSession(false).invalidate();
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }else if(settings.equals("toMainPage")){
              if(show !=null) {

                  user.setMin_age(Integer.parseInt(req.getParameter("pref_1")));
                  user.setMax_age(Integer.parseInt(req.getParameter("pref_2")));
                  userDAOimpl.updateRow(user, User.USER_MIN_AGE, user.getMin_age());
                  userDAOimpl.updateRow(user, User.USER_MAX_AGE, user.getMax_age());
                  System.out.println(user.getMin_age());
                  System.out.println(user.getMax_age());

              }
                req.getRequestDispatcher("/WEB-INF/MainPage.jsp").forward(req, resp);
            } else if (settings.equals("Delete")){
                req.getSession(false).invalidate();
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
                try {
                    userDAOimpl.deleteUser(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
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
        }


        if (preference != null) {
            user.setGenderPref(preference);
            userDAOimpl.updateRow(user, User.USER_PREFERENCE, preference);
            req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
        }






    }
}


