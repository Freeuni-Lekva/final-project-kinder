package ge.kinder.Services;

import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.User;

import java.sql.SQLException;
import java.util.List;

public interface SuggestionService {

    List<UserDTO> getSuggestions(User user);
    UserDTO getSuggestion(User user);

}



