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
            List<UserDTO> users = getUserSuggestionByAgeAndCity(user);
            System.out.println("getSuggestion-->AGE AND CITY-->" + users);
            if (users.isEmpty()) {

                users = getUserSuggestionByCity(user);
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

    private List<UserDTO> getUserSuggestionByAgeAndCity(User user) {
        try {
            return userDAO.getUsers(user.getMin_age(), user.getMax_age(), user.getCity(), user.getUser_id());
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    private List<UserDTO> getUserSuggestionByCity(User user) {
        try {
            return userDAO.getUsers(user.getCity(), user.getUser_id());
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
