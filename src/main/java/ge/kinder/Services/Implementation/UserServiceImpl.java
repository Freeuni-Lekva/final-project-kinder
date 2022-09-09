package ge.kinder.Services.Implementation;


         import ge.kinder.DAO.UserDAO;
         import ge.kinder.Exceptions.InvalidMailException;
         import ge.kinder.Exceptions.UserAlreadyExistsException;
         import ge.kinder.Exceptions.UserNotFoundException;
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
         import java.util.List;
         import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private Authentificator authentificator;
    public UserServiceImpl(UserDAO userDAO, Authentificator authentificator) {
        this.authentificator = authentificator;
        this.userDAO = userDAO;
    }

    @Override
    public User registerUser(String mail) throws InvalidMailException, UserAlreadyExistsException {
        if(!patternMatches(mail)){
            throw new InvalidMailException(mail);
        }
        if(!userDAO.userExists(mail)){
            RegistrationMail m = new RegistrationMail(mail,authentificator.generateCode(mail));
            if(MailSender.sendMail(m.getMESSAGE(),m.getSUBJECT(), m.getRECEIVER())){
                User user = new User();
                user.setMail(mail);
                return user;
            } else throw new InvalidMailException(mail);
        }else throw new UserAlreadyExistsException(mail);

    }

    private boolean patternMatches(String mail) {
        String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern)
                .matcher(mail)
                .matches();
    }

    @Override
    public User loginUser(String mail) throws InvalidMailException,UserNotFoundException{

        if(!patternMatches(mail)){
            throw new InvalidMailException(mail);
        }
        if(userDAO.userExists(mail)){
            AuthentificationMail m = new AuthentificationMail(mail,authentificator.generateCode(mail));
             if(MailSender.sendMail(m.getMESSAGE(),m.getSUBJECT(), m.getRECEIVER())){
            User user = userDAO.getUserByMail(mail);
            return user;

             }else throw new InvalidMailException(mail);

        } else throw new UserNotFoundException(mail);

        }



    @Override
    public void verificateUser(User user, String mail) {

        AuthentificationMail m = new AuthentificationMail(mail,authentificator.generateCode(mail));
        if(MailSender.sendMail(m.getMESSAGE(),m.getSUBJECT(), m.getRECEIVER())){
        }else throw new RuntimeException();
    }

    @Override
    public boolean confirmCode(User user, String code) {

       return authentificator.CodeIsCorrect(user.getMail(),code);

    }

    @Override
    public User getUserByID(int userID) throws SQLException {

        return userDAO.getUserByID(userID);

    }

    @Override
    public void changeSettings(User user, String setting, String value) {
        userDAO.updateRow(user,setting,value);
    }
@Override
public void changeSettings(User user, String setting, int value) {
    userDAO.updateRow(user,setting,value);
}
    @Override
    public List<User> getBannedUsers() {

            return userDAO.getBannedUsers();

    }

    @Override
    public List<User> getAllUsers() {

        return userDAO.getAllUsers();
    }
}

