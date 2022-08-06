package ge.kinder.Services.Implementation;

import ge.kinder.DAO.UserDAO;
import ge.kinder.Mail.AuthentificationMail;
import ge.kinder.Mail.MailSender;
import ge.kinder.Mail.RegistrationMail;
import ge.kinder.Models.User;
import ge.kinder.Security.Authentificator;
import ge.kinder.Services.UserService;

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
    public User registerUser(String mail) {
        if(!patternMatches(mail)){
            // throw exception
        }
        if(!userDAO.userExists(mail)){
            RegistrationMail m = new RegistrationMail(mail,authentificator.generateCode(mail));
            if(MailSender.sendMail(m.getMESSAGE(),m.getSUBJECT(), m.getRECEIVER())){
                User user = new User();
                user.setMail(mail);
                userDAO.addUser(user);
                return user;
        }}

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
    public User loginUser(String mail) {
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
        // or user doesnt exsit exception or mail class exceptions


        return null;
    }

    @Override
    public boolean confirmCode(String code) {

        return authentificator.CodeIsCorrect(code);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return Optional.empty();
    }
}
