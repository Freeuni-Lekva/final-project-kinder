package ge.kinder.Servlets;

import ge.kinder.DAO.DAOimpl.UserDAOimpl;
import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.Status;
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
@WebServlet(name = "LikesServlet", urlPatterns = "/LikesServlet")
public class LikesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        LikesService likesService = (LikesService) req.getServletContext().getAttribute("LIKES_SERVICE");
        MatchesService matchesService = (MatchesService) req.getServletContext().getAttribute("MATCHES_SERVICE");

        PrintWriter out = resp.getWriter();

        int userID = Integer.valueOf(req.getParameter("user"));
        int suggestedUserID =Integer.valueOf(req.getParameter("suggestedUser"));
        int likeType =Integer.valueOf(req.getParameter("type"));

        String status = (likeType==1) ? "LIKE" : ((likeType==2) ? "SUPERLIKE" : "DISLIKE");

        if(status == "LIKE" || status == "SUPERLIKE") {
            likesService.likeUser(userID,suggestedUserID, Status.valueOf(status));
            if(likesService.isLiked(suggestedUserID,userID)) {
                matchesService.addMatch(userID, suggestedUserID);
                out.print("{\"status\":1}");
            } else out.print("{\"status\":2}");
        } else if(status == "DISLIKE"){
            likesService.dislikeUser(userID,suggestedUserID);
            out.print("{\"status\":2}");
        }

    }
}

