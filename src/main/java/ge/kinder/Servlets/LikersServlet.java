package ge.kinder.Servlets;

import com.google.gson.Gson;
import ge.kinder.DAO.DAOimpl.UserDAOimpl;
import ge.kinder.DAO.MatchesDAO;
import ge.kinder.Models.Chat;
import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.User;
import ge.kinder.Services.Implementation.MessagesServiceImpl;
import ge.kinder.Services.LikesService;
import ge.kinder.Services.MatchesService;
import ge.kinder.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "LikersServlet", urlPatterns = "/LikersServlet")
public class LikersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userID= Integer.parseInt(req.getParameter("userID"));
        UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
        UserDAOimpl userDAOimpl = (UserDAOimpl) req.getServletContext().getAttribute("USERDAO");
        LikesService likesService = (LikesService) req.getServletContext().getAttribute("LIKES_SERVICE");
        MatchesService matchesService = (MatchesService) req.getServletContext().getAttribute("MATCHES_SERVICE");
        MessagesServiceImpl messagesService = (MessagesServiceImpl) req.getServletContext().getAttribute("MESSAGES_SERVICE");
        PrintWriter out = resp.getWriter();

        List<UserDTO> chats= likesService.getLikers(userID);




        String json = (new Gson()).toJson(chats);
        out.write(json);

    }
}

