package ge.kinder.Services;


import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.User;
import java.util.List;
public interface MatchesService {
    List<UserDTO> getMatches(User user);
}
