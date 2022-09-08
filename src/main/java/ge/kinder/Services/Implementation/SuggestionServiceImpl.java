package ge.kinder.Services.Implementation;

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

    public SuggestionServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<UserDTO> getSuggestions(User user) {
        try {
            System.out.println("getSuggestion-->user-->" + user);
            List<UserDTO> users = getUserSuggestionsByAgeAndCity(user);
            System.out.println("getSuggestion-->AGE AND CITY-->" + users);
            if (users.isEmpty()) {

                users = getUserSuggestionsByCity(user);
                System.out.println("getSuggestion-->CITY-->" + users);
            }

            return users;
        } catch (Exception e){
            e.printStackTrace();
            List<UserDTO> l = new ArrayList<>();

            //l.add(new UserDTO(user.getUser_id(),user.getFirst_name()))
        }
        return new ArrayList<>();
    }

    private List<UserDTO> getUserSuggestionsByAgeAndCity(User user) {
        try {
            return userDAO.getUsers(user.getMin_age(), user.getMax_age(), user.getCity(), user.getUser_id());
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    private List<UserDTO> getUserSuggestionsByCity(User user) {
        try {
            return userDAO.getUsers(user.getCity(), user.getUser_id());
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
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
        try {
            return userDAO.getUser(user.getMin_age(), user.getMax_age(), user.getCity(), user.getUser_id());
        } catch (SQLException e) {
            e.printStackTrace();
            return new UserDTO();
        }
    }
    private UserDTO getUserSuggestionByCity(User user) {
        try {
            return userDAO.getUser(user.getCity(), user.getUser_id());
        } catch (SQLException e) {
            e.printStackTrace();
            return new UserDTO();
        }
    }
}

