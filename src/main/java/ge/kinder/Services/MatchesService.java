package ge.kinder.Services;


import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.User;
import java.util.List;
public interface MatchesService {

    void addMatch(int userId1, int userId2);
    List<Integer> getMatches(int userId);
    void deleteMatch(int deleterUserId, int deletedUserId);


}



