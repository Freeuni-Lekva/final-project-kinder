package ge.kinder.Servlets;

import com.google.gson.Gson;
import ge.kinder.DAO.DAOimpl.UserDAOimpl;
import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.Status;
import ge.kinder.Models.User;
import ge.kinder.Services.LikesService;
import ge.kinder.Services.MatchesService;
import ge.kinder.Services.SuggestionService;
import ge.kinder.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

@WebServlet(name = "SuggestedUserImagesServlet", urlPatterns = "/SuggestedUserImagesServlet")
public class SuggestedUserImagesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userMail = req.getParameter("userMail");
        PrintWriter out = resp.getWriter();



      
            UserDAOimpl userDao = (UserDAOimpl) req.getServletContext().getAttribute("USERDAO");
            String suggestedUserMail = (req.getParameter("suggestedUserMail"));


            User user = userDao.getUserByMail(suggestedUserMail);
            List<String> info =user.getImages();




            String json = (new Gson()).toJson(info);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.write(json);





    }


}

