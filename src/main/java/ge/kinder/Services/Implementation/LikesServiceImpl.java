package ge.kinder.Services.Implementation;

import ge.kinder.DAO.LikesDAO;
import ge.kinder.DAO.UserDAO;
import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.Status;
import ge.kinder.Models.User;
import ge.kinder.Services.LikesService;

import java.sql.SQLException;
import java.util.List;

public class LikesServiceImpl implements LikesService {
    private UserDAO userDAO;
    private LikesDAO likesDAO;

    public LikesServiceImpl(UserDAO userDAO, LikesDAO likesDAO) {
        this.userDAO = userDAO;
        this.likesDAO = likesDAO;
    }

    @Override
    public void likeUser(int likerUserId, int likedUserId, Status status) {
        try {
            likesDAO.addLike(likerUserId,likedUserId,status);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dislikeUser(int likerUserId, int dislikedUserId) {
        try {
            likesDAO.addLike(likerUserId,dislikedUserId,Status.DISLIKE);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isLiked( int likerUserId, int likedUserId) {
        try {
            return likesDAO.isLiked(likerUserId,likedUserId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserDTO> getLikers(int likedUserId) {
        return likesDAO.getLikers(likedUserId);
    }

}
