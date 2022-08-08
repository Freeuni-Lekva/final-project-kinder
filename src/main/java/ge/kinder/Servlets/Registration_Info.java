package ge.kinder.Servlets;


import ge.kinder.DAO.DAOimpl.UserDAOimpl;
import ge.kinder.Models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Registration_Info", urlPatterns = "/Registration_Info")
public class Registration_Info extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if ((req.getSession() != null && req.getSession().getAttribute("user") != null)) {
//            req.getRequestDispatcher("/WEB-INF/Registration_Info.jsp").forward(req, resp);
//        } else {
//            req.getRequestDispatcher("/index.jsp").forward(req, resp);
//        }


        req.getRequestDispatcher("/WEB-INF/Registration_Info.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
//
//
//         User user_1 = (User) req.getSession().getAttribute("user");
////         List<String> images = new ArrayList<>();
////         images.add(req.getParameter("PHOTO_1"));
////         user.setImages(images);
//
//
//
//        user_1.setFirst_name(req.getParameter("FIRST_NAME"));
//        LocalDate date = LocalDate.of(Integer.parseInt(req.getParameter("YEAR")),
//                Integer.parseInt(req.getParameter("MONTH")), Integer.parseInt(req.getParameter("DAY")));
//        user_1.setBirth_date(Date.valueOf(date));
//        user_1.setCity("Batumi");
//        user_1.setGender(req.getParameter("GENDER"));
//        int show;
//        if(req.getParameter("SHOW_GENDER") == "on") show = 1;else show=0;
//        user_1.setGenderIsShown(show);
//        user_1.setGenderPref(req.getParameter("SHOW_ME"));
//        UserDAOimpl userDao = (UserDAOimpl) req.getServletContext().getAttribute("USERDAO");
//        try {
//            userDao.addUser(user_1);
//        } catch (SQLException e) {
//            System.out.println("ups");
//            throw new RuntimeException(e);
//        }
//
//
//
//
//


    }
}
