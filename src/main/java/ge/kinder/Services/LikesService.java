package ge.kinder.Services;

import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.User;
import java.util.List;
public interface LikesService {

    void likeUser(User user, int likedUserId);
    void dislikeUser(User user, int dislikedUserId);
    List<UserDTO> getLikers(User user);

}
