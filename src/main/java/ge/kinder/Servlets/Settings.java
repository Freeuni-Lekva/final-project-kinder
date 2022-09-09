package ge.kinder.Servlets;

import ge.kinder.DAO.DAOimpl.HobbiesDAOimpl;
import ge.kinder.DAO.DAOimpl.ImagesDAOimpl;
import ge.kinder.DAO.DAOimpl.UserDAOimpl;
import ge.kinder.DAO.UserDAO;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "Settings", urlPatterns = "/Settings")
public class Settings extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!(req.getSession() != null && req.getSession().getAttribute("user") != null)) {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            System.out.println("Setting DO GET");
            req.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
        UserDAOimpl userDAOimpl = (UserDAOimpl) req.getServletContext().getAttribute("USERDAO");
        HobbiesDAOimpl hobbiesDAOimpl = (HobbiesDAOimpl) req.getServletContext().getAttribute("HOBBIESDAO");
        ImagesDAOimpl imagesDAOimpl= (ImagesDAOimpl) req.getServletContext().getAttribute("IMAGESDAO");

        String otp = req.getParameter("VERIFICATION_CODE");
        User us = (User) req.getSession().getAttribute("user");
        User user = null;

            user = userDAOimpl.getUserByMail(us.getMail());

        String newEmail = (String) req.getSession().getAttribute("newMail");

        String settings = req.getParameter("settingsButton");
        String verification = req.getParameter("verification");
        String city = req.getParameter("City");
        String preference = req.getParameter("preference");
        String gender = req.getParameter("gender");
        String orientation = req.getParameter("orientation");
        String show = req.getParameter("SHOW");
        String show_me = req.getParameter("SHOW_ME");
        String youSee = req.getParameter("test_1");
        String seesYou = req.getParameter("test");
        String show_activity = req.getParameter("SHOW_STATUS");
        if (settings != null) {
            if(settings.equals("toMainPage") || settings.equals("TinderVersion") ||
                    settings.equals("Email") || settings.equals("City")
                    || settings.equals("Preference") || settings.equals("EditInfo")){
                if(youSee!=null){
                    user.setShow_active_people(Integer.parseInt(youSee));
                    userDAOimpl.updateRow(user,User.SHOW_RECENTLTY_ACTIVE,youSee);
                }

                if(seesYou!=null){
                    user.setShow_to_liked(Integer.parseInt(seesYou));
                    userDAOimpl.updateRow(user,User.SHOT_TO_LIKED,seesYou);
                }
                if(show !=null) {

                    user.setMin_age(Integer.parseInt(req.getParameter("pref_1")));
                    user.setMax_age(Integer.parseInt(req.getParameter("pref_2")));
                    user.setSearchInRange(1);
                    userDAOimpl.updateRow(user, User.USER_MIN_AGE, user.getMin_age());
                    userDAOimpl.updateRow(user, User.USER_MAX_AGE, user.getMax_age());
                    userDAOimpl.updateRow(user,User.AGE_RANGE,1);

                } else {
                    user.setMin_age(18);
                    user.setMax_age(100);
                    user.setSearchInRange(0);
                    userDAOimpl.updateRow(user, User.USER_MIN_AGE, 18);
                    userDAOimpl.updateRow(user, User.USER_MAX_AGE, 100);
                    userDAOimpl.updateRow(user,User.AGE_RANGE,0);

                }
                if(show_me != null) {

                    user.setIs_hided(0);
                    userDAOimpl.updateRow(user, User.USER_HIDED,0);
                }else {
                    user.setIs_hided(1);
                    userDAOimpl.updateRow(user, User.USER_HIDED,1);
                }

                if(show_activity != null) {
                    user.setShowActiveStatus(1);
                    userDAOimpl.updateRow(user, User.USER_SHOW_ACTIVE,1);
                }else {
                    user.setShowActiveStatus(0);
                    userDAOimpl.updateRow(user, User.USER_SHOW_ACTIVE,0);
                }}
        }


        if ( req.getParameter("BackFromCity")!=null || req.getParameter("BackFromPref")!=null || req.getParameter("BackFromPremium")!=null) {
            req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
        }

        if (settings != null) {
            if(settings.equals("TinderVersion")){
                req.getRequestDispatcher("/WEB-INF/Settings/Premium.jsp").forward(req, resp);

            } else if (settings.equals("Email")) {
                req.getRequestDispatcher("/WEB-INF/Settings/Email.jsp").forward(req, resp);

            } else if (settings.equals("City")) {
                req.getRequestDispatcher("/WEB-INF/Settings/City.jsp").forward(req, resp);

            }else if(settings.equals("Preference")){
                req.getRequestDispatcher("/WEB-INF/Settings/Preference.jsp").forward(req, resp);
            } else if(settings.equals("Logout")){
                req.getSession(false).invalidate();
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }else if(settings.equals("toMainPage")){
                System.out.println("Setting TO MAIN PAGE");
                req.getRequestDispatcher("/WEB-INF/MainPage.jsp").forward(req, resp);
            } else if (settings.equals("Delete")){
                req.getSession(false).invalidate();
                req.getRequestDispatcher("/index.jsp").forward(req, resp);

                    userDAOimpl.deleteUser(user);
               
            } else if (settings.equals("EditInfo")){
                req.getRequestDispatcher("/WEB-INF/Settings/Edit.jsp").forward(req,resp);
            } else if(settings.equals("Gender")){
                req.getRequestDispatcher("/WEB-INF/Settings/Gender.jsp").forward(req,resp);
            }else if(settings.equals("Orientation")){
                req.getRequestDispatcher("/WEB-INF/Settings/Orientation.jsp").forward(req,resp);
            }
            else if(settings.equals("Save")){
                if(req.getParameter("BIO")!=null) {
                    user.setBio(req.getParameter("BIO"));
                    userDAOimpl.updateRow(user, User.USER_BIO, req.getParameter("BIO"));
                }
                if(req.getParameter("JOB")!= null ) {
                    user.setJob(req.getParameter("JOB"));
                    userDAOimpl.updateRow(user,User.USER_JOB,req.getParameter("JOB"));

                }
                 if(req.getParameter("COMPANY")!=null) {
                     user.setCompany(req.getParameter("COMPANY"));
                     userDAOimpl.updateRow(user, User.USER_COMPANY, req.getParameter("COMPANY"));
                 }
                if(req.getParameter("SCHOOL")!=null) {
                    user.setSchool(req.getParameter("SCHOOL"));
                    userDAOimpl.updateRow(user, User.USER_SCHOOL, req.getParameter("SCHOOL"));
                }
                List<Hobby> newHobbies = new ArrayList<>();

                if (req.getParameter("SPORT") != null) {
                    newHobbies.add(Hobby.SPORT);
                }
                if (req.getParameter("INSTAGRAM") != null) {
                    newHobbies.add(Hobby.INSTAGRAM);
                }
                if (req.getParameter("PHOTOS") != null) {
                    newHobbies.add(Hobby.PHOTOS);
                }
                if (req.getParameter("CARS") != null) {
                    newHobbies.add(Hobby.CARS);
                }
                if (req.getParameter("FOOTBALL") != null) {
                    newHobbies.add(Hobby.FOOTBALL);
                }

                user.setHobbies(newHobbies);
                List<Hobby> oldHobbies;
                try {
                    oldHobbies = hobbiesDAOimpl.getHobbies(user.getUser_id());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                for(Hobby hobby : oldHobbies){
                    try {
                        hobbiesDAOimpl.deleteHobby(hobby,user.getUser_id());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                for(Hobby hobby : newHobbies){
                    try {
                        hobbiesDAOimpl.addHobby(hobby,user.getUser_id());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                int size = user.getImages().size();
                for(int i=size+1;i<=6;i++){
                if(req.getParameter("PHOTO_"+i)!=""){
                    List<String> photos = user.getImages();
                    photos.add(req.getParameter("PHOTO_"+i));
                    user.setImages(photos);
                    try {
                        imagesDAOimpl.addImage(req.getParameter("PHOTO_"+i),user.getUser_id());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }}



                req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req,resp);
            }else if(settings.equals("BUY")){
                user.setBalance(user.getBalance()-49);
                System.out.println(user.getBalance());
                userDAOimpl.updateRow(user, User.USER_BALANCE,user.getBalance());
                user.setIs_premium(1);
                userDAOimpl.updateRow(user,User.USER_ROLE, String.valueOf(Role.PREMIUM_USER));
              userDAOimpl.updateRow(user, User.PREMIUM,1);
                req.getRequestDispatcher("/WEB-INF/Settings/Premium.jsp").forward(req, resp);
            }
        }

        if (verification != null && verification.equals("verificationCode")) {
            if (userService.confirmCode(user,otp)) {
                req.getSession().setAttribute("mail", newEmail);
                user.setMail(newEmail);
                System.out.println(newEmail);
                userDAOimpl.updateRow(user, User.USER_MAIL, newEmail);
                req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
            } else System.out.println("nope");
        }
        if (city != null) {
            user.setCity(city);
            userDAOimpl.updateRow(user, User.USER_CITY, city);
            req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
        }


        if (preference != null) {
            user.setGenderPref(preference);
            userDAOimpl.updateRow(user, User.USER_PREFERENCE, preference);
            req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req, resp);
        }
        if (gender != null) {
            user.setGender(gender);
            userDAOimpl.updateRow(user, User.USER_GENDER, gender);
            req.getRequestDispatcher("/WEB-INF/Settings/Edit.jsp").forward(req, resp);
        }
        if (orientation!= null) {
            user.setOrientation(orientation);
            userDAOimpl.updateRow(user, User.USER_ORIENTATION,orientation);
            req.getRequestDispatcher("/WEB-INF/Settings/Edit.jsp").forward(req, resp);
        }

    }
}


