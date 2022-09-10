package ge.kinder.Servlets;

import com.google.gson.Gson;
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
import java.util.List;

@WebServlet(name = "BannedUsersServlet", value = "/BannedUsersServlet")
public class BannedUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hi");
        UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
        PrintWriter out = resp.getWriter();

        List<User> users = userService.getBannedUsers();
        System.out.println("list of banned users " + users);
        String json = (new Gson()).toJson(users);
        out.write(json);
    }
}
