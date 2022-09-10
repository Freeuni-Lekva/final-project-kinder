package ge.kinder.Servlets;

import com.google.gson.Gson;
import ge.kinder.DAO.DAOimpl.UserDAOimpl;
import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.Role;
import ge.kinder.Models.User;
import ge.kinder.Services.LikesService;
import ge.kinder.Services.MatchesService;
import ge.kinder.Services.SuggestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet(name = "UserInfoServlet", value = "/UserInfoServlet" )
public class UserInfoServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userMail = req.getParameter("userMail");
        PrintWriter out = resp.getWriter();


        UserDAOimpl userDao = (UserDAOimpl) req.getServletContext().getAttribute("USERDAO");
        SuggestionService suggestionService = (SuggestionService) req.getServletContext().getAttribute("SUGGESTION_SERVICE");

        User suser = userDao.getUserByMail(userMail);

        List<String> info = new ArrayList<>();


        int age = (int) Math.floor((new Date(System.currentTimeMillis()).getTime()-suser.getBirth_date().getTime() ) / 3.15576e+10);
        info.add(String.valueOf(age));

        info.add(suser.getJob());
        info.add(suser.getCompany());
        info.add(suser.getCity());
        info.add(suser.getBio());
        info.add(String.valueOf(suser.getIsBanned()));


        String json = (new Gson()).toJson(info);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.write(json);



    }
}
