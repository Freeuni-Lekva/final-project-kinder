package ge.kinder.Servlets;

import ge.kinder.Models.User;
import ge.kinder.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Registration_Confirmation", urlPatterns = "/Registration_Confirmation")
public class Registration_Confirmation extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!(req.getSession() != null && req.getSession().getAttribute("user") != null)) {
            req.getRequestDispatcher("/WEB-INF/Registration.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
        String mail = req.getParameter("REGISTRATION_MAIL");
            try {
              User user = userService.registerUser(mail);

                req.getSession().setAttribute("user", user);

                req.getRequestDispatcher("/WEB-INF/Confirm_Registration.jsp").forward(req, resp);
            } catch (Exception e) {

                //throw exceptions
            }
        }
    }


