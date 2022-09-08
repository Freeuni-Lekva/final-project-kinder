package ge.kinder.Servlets;

import ge.kinder.DAO.DAOimpl.UserDAOimpl;
import ge.kinder.DAO.UserDAO;
import ge.kinder.Models.Role;
import ge.kinder.Models.User;
import ge.kinder.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "BanUser", value = "/BanUser")
public class BanUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
        PrintWriter out = resp.getWriter();
        try{
            User user = (User) req.getSession().getAttribute("user");
            if(!user.getRole().equals(Role.ADMIN.toString())){
                out.print("{\"status\":3}");
                return;
            }
            userService.changeSettings(user, User.IS_BANNED,1);
            out.print("{\"status\":1}");

        } catch (RuntimeException ex) {
            ex.printStackTrace();
            out.print("{\"status\":2}");
        }
    }
}
