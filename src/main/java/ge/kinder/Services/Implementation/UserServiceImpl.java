package ge.kinder.Services.Implementation;

import ge.kinder.DAO.UserDAO;
import ge.kinder.Mail.AuthentificationMail;
import ge.kinder.Mail.MailSender;
import ge.kinder.Mail.RegistrationMail;
import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.User;
import ge.kinder.Security.Authentificator;
import ge.kinder.Services.UserService;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private Authentificator authentificator;
    public UserServiceImpl(UserDAO userDAO, Authentificator authentificator) {
        this.authentificator = authentificator;
        this.userDAO = userDAO;
    }

    @Override
    public User registerUser(String mail) throws SQLException {
        if(!patternMatches(mail)){
            // throw exception
        }
        if(!userDAO.
                userExists(mail)){
            RegistrationMail m = new RegistrationMail(mail,authentificator.generateCode(mail));
            if(MailSender.sendMail(m.getMESSAGE(),m.getSUBJECT(), m.getRECEIVER())){
                User user = new User();
                user.setMail(mail);
                //userDAO.addUser(user);
                return user;
        }
        }

        // throw exceptions
        // or user already exists exception or mail class exceptions


        return null;
    }

    private boolean patternMatches(String mail) {
        String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern)
                .matcher(mail)
                .matches();
    }

    @Override
    public User loginUser(String mail) throws SQLException {
//        User user_1 = new User();
//        user_1.setMail("mshas18@freeuni.edu.ge");
//        user_1.setFirst_name("Megi");
//        LocalDate date = LocalDate.of(1999,10,4);
//        user_1.setBirth_date(Date.valueOf(date));
//        user_1.setCity("Batumi");
//        user_1.setGender("Female");
//        user_1.setGenderIsShown(0);
//        user_1.setGenderPref("Men");
//        user_1.setOrientation("Straight");
//
//
//        User user_2 = new User();
//        user_2.setMail("ainau18@freeuni.edu.ge");
//        user_2.setFirst_name("Aleksi");
//         date = LocalDate.of(2000,10,6);
//        user_2.setBirth_date(Date.valueOf(date));
//        user_2.setCity("Gori");
//        user_2.setGender("Male");
//        user_2.setGenderIsShown(0);
//        user_2.setGenderPref("Women");
//        user_2.setOrientation("Straight");
//        ArrayList<String> list = new ArrayList<>();
//        list.add("first");
//        user_1.setImages(list);
//        user_2.setImages(list);
//        System.out.println(user_1.getImages().get(0));
//
//        userDAO.addUser(user_1);
//        userDAO.addUser(user_2);



        if(!patternMatches(mail)){
            // throw exception
        }
        if(userDAO.userExists(mail)){
            AuthentificationMail m = new AuthentificationMail(mail,authentificator.generateCode(mail));
            if(MailSender.sendMail(m.getMESSAGE(),m.getSUBJECT(), m.getRECEIVER())){
                User user = userDAO.getUserByMail(mail);
                return user;
            }}

        // throw exceptions
        // or user doesnt exist exception or mail class exceptions


        return null;
    }

    @Override
    public void verificateUser(User user, String mail) {

            AuthentificationMail m = new AuthentificationMail(mail,authentificator.generateCode(mail));
            if(MailSender.sendMail(m.getMESSAGE(),m.getSUBJECT(), m.getRECEIVER())){


    }else {
                //throw ex
            }
    }

    @Override
    public boolean confirmCode(String code) {

        return authentificator.CodeIsCorrect(code);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public void changeSettings(User user, String setting, String value) {
        userDAO.updateRow(user,setting,value);
    }
}
