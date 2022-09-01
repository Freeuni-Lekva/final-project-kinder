package ge.kinder.Services.Implementation;

import ge.kinder.DAO.MatchesDAO;
import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Services.MatchesService;

import java.sql.SQLException;
import java.util.List;

public class MatchesServiceImpl implements MatchesService {

    private  MatchesDAO matchesDAO;

    public MatchesServiceImpl(MatchesDAO matchesDAO) {
        this.matchesDAO = matchesDAO;
    }

    @Override
    public void addMatch(int userId1, int userId2) {
        try {
            matchesDAO.addMatch(userId1,userId2);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserDTO> getMatches(int userId) {
        return matchesDAO.getMatches(userId);
    }

    @Override
    public void deleteMatch(int deleterUserId, int deletedUserId) {
        try {
            matchesDAO.deleteMatch(deleterUserId,deletedUserId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
