package ge.kinder.Servlets;

import ge.kinder.DAO.DAOimpl.UserDAOimpl;
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

@WebServlet(name = "UnbanUserServlet", value = "/UnbanUserServlet")
public class UnbanUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
        String userMail = req.getParameter("userMail");
        PrintWriter out = resp.getWriter();


        UserDAOimpl userDao = (UserDAOimpl) req.getServletContext().getAttribute("USERDAO");

        User user = userDao.getUserByMail(userMail);
        user.setIsBanned(0);
        userService.changeSettings(user, User.IS_BANNED, 0);
        out.print("{\"status\":1}");
    }
}
