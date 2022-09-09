package ge.kinder.Services.Implementation;

import ge.kinder.DAO.PremiumUserDAO;
import ge.kinder.DAO.UserDAO;
import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.User;
import ge.kinder.Services.SuggestionService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuggestionServiceImpl implements SuggestionService {

    private UserDAO userDAO;
    private PremiumUserDAO premiumUserDAO;

    public SuggestionServiceImpl(UserDAO userDAO, PremiumUserDAO premiumUserDAO) {

        this.userDAO = userDAO;
        this.premiumUserDAO = premiumUserDAO;
    }

    @Override
    public List<UserDTO> getSuggestions(User user) {

        List<UserDTO> users = new ArrayList<>();
        System.out.println(user.getSearchInRange());
        System.out.println(user.getShow_active_people());
        if(user.getSearchInRange()==0 && user.getShow_active_people()==0) users = userDAO.getUsers(user.getCity(),user.getUser_id());
        if(user.getSearchInRange()==1 && user.getShow_active_people()==0) users =userDAO.getUsers(user.getMin_age(), user.getMax_age(), user.getCity(), user.getUser_id());
        if(user.getSearchInRange()==0 && user.getShow_active_people()==1) users = premiumUserDAO.getUsers(true,user.getCity(),user.getUser_id());
        if(user.getSearchInRange()==1 && user.getShow_active_people()==1) users = premiumUserDAO.getUsers(user.getMin_age(),user.getMax_age(),true,user.getCity(),user.getUser_id());

        System.out.println("users");
        return users;

    }


    @Override
    public UserDTO getSuggestion(User user) {
        UserDTO sUser = null;
        try {
//            System.out.println("getSuggestion-->user-->" + user);
            sUser = userDAO.getUser(user.getCity(),user.getUser_id());
//            System.out.println("getSuggestion-->AGE AND CITY-->" + sUser);
//            if (sUser == null) {
//
//                sUser = getUserSuggestionByCity(user);
//                System.out.println("getSuggestion-->CITY-->" + sUser);
//            }
//
//            return sUser;
//        } catch (Exception e){
//            e.printStackTrace();
//            List<UserDTO> l = new ArrayList<>();
//
//            //l.add(new UserDTO(user.getUser_id(),user.getFirst_name()))
        }
        catch (Exception e){
            System.out.println("cant finddddd");
        }
        return sUser;

    }

    private UserDTO getUserSuggestionByAgeAndCity(User user) {

            return userDAO.getUser(user.getMin_age(), user.getMax_age(), user.getCity(), user.getUser_id());

    }
    private UserDTO getUserSuggestionByCity(User user) {

            return userDAO.getUser(user.getCity(), user.getUser_id());

    }
}

