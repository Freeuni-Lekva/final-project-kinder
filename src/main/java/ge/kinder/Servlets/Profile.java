package ge.kinder.Servlets;


import ge.kinder.DAO.DAOimpl.HobbiesDAOimpl;
import ge.kinder.DAO.DAOimpl.ImagesDAOimpl;
import ge.kinder.DAO.DAOimpl.UserDAOimpl;
import ge.kinder.Models.Hobby;
import ge.kinder.Models.Role;
import ge.kinder.Models.User;
import ge.kinder.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Profile", urlPatterns = "/Profile")
public class Profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ((req.getSession() != null && req.getSession().getAttribute("user") != null)) {
            req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAOimpl userDao = (UserDAOimpl) req.getServletContext().getAttribute("USERDAO");
        ImagesDAOimpl imagesDAOimplDao = (ImagesDAOimpl) req.getServletContext().getAttribute("IMAGESDAO");
        HobbiesDAOimpl hobbiesDAOimpl = (HobbiesDAOimpl) req.getServletContext().getAttribute("HOBBIESDAO");
        UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
        if (req.getParameter("LOGIN_CODE") != null) {
            String otp = req.getParameter("LOGIN_CODE");

            if (userService.confirmCode((User) req.getSession().getAttribute("user"),otp)) {
                User user = (User) req.getSession().getAttribute("user");

                String role = String.valueOf(Role.ADMIN);
                if(user.getRole().equals(role)) {
                    req.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(req, resp);
                } else req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
            } else {
                req.setAttribute("LOGIN_ERROR", "Wrong code. Try again.");
                req.getRequestDispatcher("/WEB-INF/Confirm_Login.jsp").forward(req, resp);

            }}else {

        User user_1 = (User) req.getSession().getAttribute("user");

        try {
            if(!userDao.userExists(user_1.getMail())){
            if(req.getParameter("FIRST_NAME")!=null ) {
                List<String> images = new ArrayList<>();
                for (int i = 1; i <= 6; i++) {
                    if (req.getParameter("PHOTO_" + i) != null) {
                        images.add(req.getParameter("PHOTO_" + i));
                    }
                }
                user_1.setImages(images);

                user_1.setFirst_name(req.getParameter("FIRST_NAME"));
                LocalDate date = LocalDate.of(Integer.parseInt(req.getParameter("YEAR")), Integer.parseInt(req.getParameter("MONTH")), Integer.parseInt(req.getParameter("DAY")));
                user_1.setBirth_date(Date.valueOf(date));
                user_1.setCity(req.getParameter("CITY"));
                user_1.setGender(req.getParameter("GENDER"));
                int show;
                if (req.getParameter("SHOW_GENDER") == null) {
                    show = 0;
                } else show = 1;
                user_1.setGenderIsShown(show);
                user_1.setGenderPref(req.getParameter("SHOW_ME"));

                if (req.getParameter("ORIENTATION") != null) {
                    user_1.setOrientation(req.getParameter("ORIENTATION"));
                }

                List<Hobby> hobbies = new ArrayList<>();

                if (req.getParameter("SPORT") != null) {
                    hobbies.add(Hobby.SPORT);
                }
                if (req.getParameter("INSTAGRAM") != null) {
                    hobbies.add(Hobby.INSTAGRAM);
                }
                if (req.getParameter("PHOTOS") != null) {
                    hobbies.add(Hobby.PHOTOS);
                }
                if (req.getParameter("CARS") != null) {
                    hobbies.add(Hobby.CARS);
                }
                if (req.getParameter("FOOTBALL") != null) {
                    hobbies.add(Hobby.FOOTBALL);
                }

                user_1.setHobbies((ArrayList<Hobby>) hobbies);


                try {
                    userDao.addUser(user_1);
                    imagesDAOimplDao.addImage(req.getParameter("PHOTO_1"), user_1.getUser_id());
                    for (Hobby hobby : hobbies){
                        hobbiesDAOimpl.addHobby(hobby, user_1.getUser_id());
                    }

                } catch (SQLException e) {
                    System.out.println("ups");
                    throw new RuntimeException(e);
                }

                req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
            }
        } else req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }






        }
    }}

