package ge.kinder.Servlets;

import ge.kinder.DAO.DAOimpl.UserDAOimpl;
import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.Role;
import ge.kinder.Models.Status;
import ge.kinder.Models.User;
import ge.kinder.Services.LikesService;
import ge.kinder.Services.MatchesService;
import ge.kinder.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfilePage", urlPatterns = "/ProfilePage")
public class ProfilePage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        if ((req.getSession() != null && user != null)) {
            if(user.getRole().equals(Role.ADMIN.toString())) {
                req.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(req, resp);
            } else req.getRequestDispatcher("/WEB-INF/MainPage.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
        UserDAOimpl userDAOimpl = (UserDAOimpl) req.getServletContext().getAttribute("USERDAO");
        LikesService likesService = (LikesService) req.getServletContext().getAttribute("LIKES_SERVICE");
        MatchesService matchesService = (MatchesService) req.getServletContext().getAttribute("MATCHES_SERVICE");

        User user = (User) req.getSession().getAttribute("user");

        String mainPageButton = req.getParameter("mainPageButton");

        if (mainPageButton != null) {

            if (mainPageButton.equals("toSettings")) {
                req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);


            } else if(mainPageButton.equals("like")) {
                UserDTO suggestedUser = (UserDTO) req.getSession().getAttribute("suggestedUser");
                likesService.likeUser(user.getUser_id(),suggestedUser.getUser_id(), Status.LIKE);
                if(likesService.isLiked(suggestedUser.getUser_id(),user.getUser_id())) {
                    matchesService.addMatch(user.getUser_id(), suggestedUser.getUser_id());
                    req.setAttribute("MATCH","It`s a match!.");
                }
                req.getRequestDispatcher("/WEB-INF/MainPage.jsp").forward(req, resp);
            } else if(mainPageButton.equals("dislike")) {
                UserDTO suggestedUser = (UserDTO) req.getSession().getAttribute("suggestedUser");
                likesService.dislikeUser(user.getUser_id(),suggestedUser.getUser_id());
                req.getRequestDispatcher("/WEB-INF/MainPage.jsp").forward(req, resp);
            }else if(mainPageButton.equals("superlike")){
                UserDTO suggestedUser = (UserDTO) req.getSession().getAttribute("suggestedUser");
                likesService.likeUser(user.getUser_id(),suggestedUser.getUser_id(), Status.SUPERLIKE);
                if(likesService.isLiked(suggestedUser.getUser_id(),user.getUser_id())) {
                    matchesService.addMatch(user.getUser_id(), suggestedUser.getUser_id());
                    req.setAttribute("MATCH","It`s a match!.");
                }
                req.getRequestDispatcher("/WEB-INF/MainPage.jsp").forward(req, resp);
            }
        }
    }
}