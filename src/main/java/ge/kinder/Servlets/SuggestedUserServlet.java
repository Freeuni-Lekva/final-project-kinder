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

@WebServlet(name = "SuggestedUserServlet", urlPatterns = "/SuggestedUserServlet")
public class SuggestedUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userMail = req.getParameter("userMail");
        PrintWriter out = resp.getWriter();



        try {
            UserDAOimpl userDao = (UserDAOimpl) req.getServletContext().getAttribute("USERDAO");
            SuggestionService suggestionService = (SuggestionService) req.getServletContext().getAttribute("SUGGESTION_SERVICE");
            LikesService likesService = (LikesService) req.getServletContext().getAttribute("LIKES_SERVICE");
            MatchesService matchesService = (MatchesService) req.getServletContext().getAttribute("MATCHES_SERVICE");
            User user = userDao.getUserByMail(userMail);
            UserDTO suser = suggestionService.getSuggestion(user);

            List<String> info = new ArrayList<>();

            info.add(String.valueOf(suser.getUser_id()));
            info.add(suser.getFirst_name());
            int age = (int) Math.floor((new Date(System.currentTimeMillis()).getTime()-suser.getBirth_date().getTime() ) / 3.15576e+10);
            info.add(String.valueOf(age));
            info.add(suser.getMail());
            info.add(suser.getJob());
            info.add(suser.getCompany());
            info.add(suser.getCity());
            info.add(suser.getBio());


            String json = (new Gson()).toJson(info);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.write(json);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


}

