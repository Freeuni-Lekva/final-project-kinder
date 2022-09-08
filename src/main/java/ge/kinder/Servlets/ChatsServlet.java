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


@WebServlet(name = "ChatsServlet", urlPatterns = "/ChatsServlet")
public class ChatsServlet extends HttpServlet {
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
//        System.out.println("user is " + userID);
        List<Integer> matches = matchesService.getMatches(userID);
//        System.out.println("matches " + matches);
        List<Chat> chats = new ArrayList<>();
        for(Integer matchedUserID : matches){
//            System.out.println(matchedUserID);
            User user = null;
            try {
                user = userService.getUserByID(matchedUserID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
//            System.out.println(user);
            Chat chat = new Chat(userID,user.getUser_id());
//            System.out.println("chat " + chat);
            chat.setMessages(messagesService.getMessages(messagesService.getChatId(userID,user.getUser_id())));
            chat.setImage("images/" + user.getImages().get(0));
//            System.out.println(chat.getImage());
            chat.setName(user.getFirst_name());
            chat.setChat_id(messagesService.getChatId(userID,user.getUser_id()));

            chats.add(chat);
//            System.out.println("chat " + chat);
        }
//        System.out.println(chats);

        String json = (new Gson()).toJson(chats);
        out.write(json);

    }
}

