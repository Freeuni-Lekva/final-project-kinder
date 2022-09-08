package ge.kinder.DAO.DAOimpl;

import ge.kinder.DAO.HobbiesDAO;
import ge.kinder.DAO.ImagesDAO;
import ge.kinder.DAO.LikesDAO;
import ge.kinder.DAO.PremiumUserDAO;
import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.Role;
import ge.kinder.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PremiumUserDAOimpl extends UserDAOimpl implements PremiumUserDAO {
    private ImagesDAO imagesDAO;
    private HobbiesDAO hobbiesDAO;
    private LikesDAO likesDAO;

    private final Connection connection;
    public PremiumUserDAOimpl(Connection connection, HobbiesDAO hobbiesDAO, ImagesDAO imagesDAO, LikesDAO likesDAO) {
        super(connection,hobbiesDAO,imagesDAO,likesDAO);
        this.connection = connection;
    }
    // ბოლო 24 საათის აქტიურობა
    @Override
    public List<UserDTO> getUsers(boolean most_recent, String city, int user_id) {

        List <UserDTO> users = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    ("SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, TIMESTAMPDIFF(hour,%s,SYSDATE()) " +
                            "FROM %s WHERE %s = ? AND %s != ? AND %s != ? ;").formatted(
                            User.USER_USER_ID,
                            User.USER_FIRST_NAME,
                            User.USER_BIRTH_DATE,
                            User.USER_CITY,
                            User.USER_GENDER,
                            User.USER_BIO,
                            User.USER_HOROSCOPE,
                            User.USER_COMPANY,
                            User.USER_JOB,
                            User.USER_SCHOOL,
                            User.USER_MAIL,
                            User.USER_LAST_Session,
                            User.USER_TABLE,
                            User.USER_CITY,
                            User.USER_USER_ID,
                            User.USER_HIDED
                    )
            );
            stm.setString(1, city);
            stm.setInt(2, user_id);
            stm.setInt(3,1);


            ResultSet rs = stm.executeQuery();
            User curUser = getUserByID(user_id);

            while (rs.next()) {
                if(curUser.getGenderPref().equals(rs.getString(5)) &&
                        (likesDAO.isLiked(user_id, rs.getInt(1)) ||
                                likesDAO.isDisliked(user_id, rs.getInt(1)) ||
                                likesDAO.isDisliked(rs.getInt(1), user_id))
                ){
                    if(!(rs.getString(12).equals(Role.PREMIUM_USER.toString()) &&
                            rs.getInt(13) == 1 &&
                            !likesDAO.isLiked(rs.getInt(1),user_id))){
                        if(!(curUser.getShow_active_people() == 1 && rs.getInt(12) > 24)) {
                            UserDTO suggestedUser = new UserDTO(
                                    rs.getInt(1),
                                    rs.getString(2),
                                    rs.getDate(3),
                                    rs.getString(4),
                                    rs.getString(5),
                                    imagesDAO.getImages(rs.getInt(1)),
                                    hobbiesDAO.getHobbies(rs.getInt(1)),
                                    rs.getString(6),
                                    rs.getString(7),
                                    rs.getString(8),
                                    rs.getString(9),
                                    rs.getString(10),
                                    rs.getString(11)
                            );
                            users.add(suggestedUser);
                        }
                    }
                }
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserDTO> getUsers(int min_age, int max_age, boolean most_recent, String city, int user_id) {
        List <UserDTO> users = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    ("SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s, %s,%s, TIMESTAMPDIFF(hour,%s,SYSDATE())  FROM %s WHERE %s = ? AND %s != ? " +
                            "AND TIMESTAMPDIFF(year,%s,SYSDATE()) >= ? " +
                            "AND TIMESTAMPDIFF(year,%s,SYSDATE()) <= ?" +
                            "AND %s != ?;").formatted(
                            User.USER_USER_ID,
                            User.USER_FIRST_NAME,
                            User.USER_BIRTH_DATE,
                            User.USER_CITY,
                            User.USER_GENDER,
                            User.USER_BIO,
                            User.USER_HOROSCOPE,
                            User.USER_COMPANY,
                            User.USER_JOB,
                            User.USER_SCHOOL,
                            User.USER_MAIL,
                            User.USER_LAST_Session,
                            User.USER_TABLE,
                            User.USER_CITY,
                            User.USER_USER_ID,
                            User.USER_BIRTH_DATE,
                            User.USER_BIRTH_DATE,
                            User.USER_HIDED
                    )
            );
            stm.setString(1, city);
            stm.setInt(2, user_id);
            stm.setInt(3,min_age);
            stm.setInt(4,max_age);
            stm.setInt(5,1);
            ResultSet rs = stm.executeQuery();
            User curUser = getUserByID(user_id);

            while (rs.next()) {
                if(curUser.getGenderPref().equals(rs.getString(5)) &&
                        (likesDAO.isLiked(user_id, rs.getInt(1)) ||
                                likesDAO.isDisliked(user_id, rs.getInt(1)) ||
                                likesDAO.isDisliked(rs.getInt(1), user_id))
                ){
                    if(!(rs.getString(12).equals(Role.PREMIUM_USER.toString()) &&
                            rs.getInt(13) == 1 &&
                            !likesDAO.isLiked(rs.getInt(1),user_id))){
                        if(!(curUser.getShow_active_people() == 1 && rs.getInt(12) > 24)) {
                            UserDTO suggestedUser = new UserDTO(
                                    rs.getInt(1),
                                    rs.getString(2),
                                    rs.getDate(3),
                                    rs.getString(4),
                                    rs.getString(5),
                                    imagesDAO.getImages(rs.getInt(1)),
                                    hobbiesDAO.getHobbies(rs.getInt(1)),
                                    rs.getString(6),
                                    rs.getString(7),
                                    rs.getString(8),
                                    rs.getString(9),
                                    rs.getString(10),
                                    rs.getString(11)
                            );
                            users.add(suggestedUser);
                        }
                    }
                }
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}

