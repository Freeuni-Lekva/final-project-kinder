package ge.kinder.Servlets;

import ge.kinder.Exceptions.InvalidMailException;
import ge.kinder.Exceptions.UserAlreadyExistsException;
import ge.kinder.Models.Role;
import ge.kinder.Models.User;
import ge.kinder.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Registration", urlPatterns = "/Registration")
public class Registration extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        if ((req.getSession() != null && user != null)) {
            if(user.getRole().equals(Role.ADMIN.toString())) {
                req.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(req, resp);
            } else req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/Registration.jsp").forward(req, resp);
        }


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");

        if (req.getParameter("CREATEACCOUNT") != null) {
            req.getRequestDispatcher("/WEB-INF/Registration.jsp").forward(req, resp);
        }
        if (req.getParameter("REGISTRATION_MAIL") != null) {
            String mail = req.getParameter("REGISTRATION_MAIL");
            try {
                User user = userService.registerUser(mail);
                req.getSession().setAttribute("user", user);

                req.getRequestDispatcher("/WEB-INF/Confirm_Registration.jsp").forward(req, resp);
            } catch (InvalidMailException | UserAlreadyExistsException e) {
                req.setAttribute("REGISTRATION_ERROR",e.getMessage());
                req.getRequestDispatcher("/WEB-INF/Registration.jsp").forward(req, resp);
            }
        }
    }
    }


