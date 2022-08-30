package ge.kinder.DAO.DAOimpl;

import ge.kinder.DAO.HobbiesDAO;
import ge.kinder.DAO.ImagesDAO;
import ge.kinder.DAO.PremiumUserDAO;
import ge.kinder.Models.DTO.UserDTO;
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
    private final Connection connection;
    public PremiumUserDAOimpl(Connection connection, HobbiesDAO hobbiesDAO, ImagesDAO imagesDAO) {
        super(connection,hobbiesDAO,imagesDAO);
        this.connection = connection;
    }

    @Override
    public List<UserDTO> getUsers(boolean most_recent, String city, int user_id) {
        List <UserDTO> users = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    ("SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s, %s  FROM %s WHERE %s = ? AND %s != ? AND %s != ?;").formatted(
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
                            User.USER_TABLE,
                            User.USER_CITY,
                            User.USER_USER_ID,
                            User.USER_HIDED
                    )
            );
            stm.setString(1, city);
            stm.setInt(2, user_id);
            stm.setInt(3, 1);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
//                Date birthDate = rs.getDate(3);
//                int age = (int) Math.floor((new Date(System.currentTimeMillis()).getTime()-birthDate.getTime() ) / 3.15576e+10);
//                if(age >= min_age && age <=max_age){
                users.add(new UserDTO(
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
                        rs.getString(10)
                ));
                //}
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return users;
    }

    @Override
    public List<UserDTO> getUsers(int min_age, int max_age, boolean liked, boolean most_recent, String city, int user_id) {
        List <UserDTO> users = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    ("SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s, %s  FROM %s WHERE %s = ? AND %s != ? " +
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

            while (rs.next()) {
//                Date birthDate = rs.getDate(3);
//                int age = (int) Math.floor((new Date(System.currentTimeMillis()).getTime()-birthDate.getTime() ) / 3.15576e+10);
//                if(age >= min_age && age <=max_age){
                users.add(new UserDTO(
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
                        rs.getString(10)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return users;
    }
}