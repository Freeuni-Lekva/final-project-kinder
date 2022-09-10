package ge.kinder.Servlets;

import ge.kinder.DAO.UserDAO;
import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.Role;
import ge.kinder.Models.User;
import ge.kinder.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;


@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       if (!(req.getSession() != null && req.getSession().getAttribute("user") != null)) {
            req.getRequestDispatcher("/WEB-INF/Login.jsp").forward(req, resp);
        } else {
           User user = (User) req.getSession().getAttribute("user");
           //System.out.println(LOGIN);
           if(user.getRole().equals(Role.ADMIN.toString())){

               req.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(req, resp);
           } else {
               req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
           }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");


        if (req.getParameter("LOGIN") != null) {
            req.getRequestDispatcher("/WEB-INF/Login.jsp").forward(req, resp);
        }
        if (req.getParameter("LOGIN_MAIL") != null) {
            String mail = req.getParameter("LOGIN_MAIL");
            try {
                User user = userService.loginUser(mail);
                req.getSession().setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/Confirm_Login.jsp").forward(req, resp);
            } catch (Exception e) {
                System.out.println("user not found");
            }
        }


    }





}
