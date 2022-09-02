package ge.kinder.Services;

import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.Status;
import ge.kinder.Models.User;
import java.util.List;
public interface LikesService {

    void likeUser(int likerUserId, int likedUserId, Status status);
    void dislikeUser(int likerUserId, int dislikedUserId);
    boolean isLiked( int likerUserId, int likedUserId);
    List<UserDTO> getLikers(int likedUserId);

}
