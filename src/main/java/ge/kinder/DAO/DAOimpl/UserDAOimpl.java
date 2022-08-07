package ge.kinder.DAO.DAOimpl;

import ge.kinder.DAO.UserDAO;
import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOimpl implements UserDAO {

    private final Connection connection;
    private ImagesDAOimpl imagesDAO;
    private HobbiesDAOimpl hobbiesDAO;
    public UserDAOimpl(Connection connection, HobbiesDAOimpl hobbiesDAO, ImagesDAOimpl imagesDAO) {
        this.hobbiesDAO = hobbiesDAO;
        this.imagesDAO = imagesDAO;
        this.connection = connection;
    }

    @Override
    public void addUser(User user) throws SQLException {

        try {
            PreparedStatement stm = connection.prepareStatement(
                    ("INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s," +
                            "%s, %s, %s) VALUES (?,?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?)").formatted(
                                    User.USER_TABLE,
                            User.USER_MAIL,
                    User.USER_FIRST_NAME,
                    User.USER_BIRTH_DATE,
                    User.USER_CITY,
                    User.USER_GENDER ,
                    User.USER_SHOW_GENDER ,
                    User.USER_PREFERENCE ,
                    User.USER_ORIENTATION ,
                    User.USER_BIO ,
                    User.USER_HOROSCOPE ,
                    User.USER_COMPANY,
                    User.USER_JOB,
                    User.USER_SCHOOL ,
                    User.USER_MIN_AGE,
                    User.USER_MAX_AGE ,
                    User.USER_SHOW_ACTIVE ,
                    User.USER_LAST_Session,
                    User.USER_ROLE
                    ) , Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, user.getMail());
            stm.setString(2, user.getFirst_name());
            stm.setDate(3, (Date) user.getBirth_date());
            stm.setString(4, user.getCity());
            stm.setString(5, user.getGender());
            stm.setBoolean(6, user.isGenderIsShown());
            stm.setString(7, user.getGenderPref());
            stm.setString(8,user.getOrientation());
            stm.setString(9,user.getBio());
            stm.setString(10,user.getHoroscope());
            stm.setString(11,user.getCompany());
            stm.setString(12,user.getJob());
            stm.setString(13,user.getSchool());
            stm.setInt(14,user.getMin_age());
            stm.setInt(15,user.getMax_age());
            stm.setBoolean(16, user.isShow_active());
            stm.setDate(17, (Date) user.getLast_session());
            stm.setString(18, user.getRole().toString());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }

    @Override
    public void updateUser(User user) throws SQLException {
        try {
            PreparedStatement stm = connection.prepareStatement(
                    ("UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s=?, %s=?, %s=?," +
                            " %s = ?, %s = ?, %s= ?, %s= ?, %s=?, %s =?, %s = ?, %s =?" +
                            " WHERE %s = ?;").formatted(
                            User.USER_TABLE,
                            User.USER_MAIL,
                            User.USER_FIRST_NAME,
                            User.USER_BIRTH_DATE,
                            User.USER_CITY,
                            User.USER_GENDER ,
                            User.USER_SHOW_GENDER ,
                            User.USER_PREFERENCE ,
                            User.USER_ORIENTATION ,
                            User.USER_BIO ,
                            User.USER_HOROSCOPE ,
                            User.USER_COMPANY,
                            User.USER_JOB,
                            User.USER_SCHOOL ,
                            User.USER_MIN_AGE,
                            User.USER_MAX_AGE,
                            User.USER_REGISTRATION_DATE,
                            User.USER_SHOW_ACTIVE ,
                            User.USER_LAST_Session,
                            User.USER_HIDED,
                            User.USER_ROLE,
                            User.SHOT_TO_LIKED,
                            User.USER_BALANCE,
                            User.USER_USER_ID
                    ) , Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, user.getMail());
            stm.setString(2, user.getFirst_name());
            stm.setDate(3, (Date) user.getBirth_date());
            stm.setString(4, user.getCity());
            stm.setString(5, user.getGender());
            stm.setBoolean(6, user.isGenderIsShown());
            stm.setString(7, user.getGenderPref());
            stm.setString(8,user.getOrientation());
            stm.setString(9,user.getBio());
            stm.setString(10,user.getHoroscope());
            stm.setString(11,user.getCompany());
            stm.setString(12,user.getJob());
            stm.setString(13,user.getSchool());
            stm.setInt(14,user.getMin_age());
            stm.setInt(15,user.getMax_age());
            stm.setDate(16, (Date) user.getRegistration_date());
            stm.setBoolean(17, user.isShow_active());
            stm.setDate(18, (Date) user.getLast_session());
            stm.setBoolean(19, user.isIs_hided());
            stm.setString(20, user.getRole().toString());
            stm.setBoolean(21, user.isShow_to_liked());
            stm.setInt(22, user.getBalance());
            stm.setInt(23,user.getUser_id());
            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }

    @Override
    public boolean deleteUser(User user) throws SQLException {
        // ბაზაში სადაც იუზერი ფორინ ქი არის მანდაც უნდა წაიშალოს და ეგ როგორ ხდება?
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "DELETE FROM %s WHERE %s = ?;".formatted(
                            User.USER_TABLE,
                            User.USER_USER_ID
                    )
            );
            stm.setInt(1, user.getUser_id());
            if (stm.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
        return false;
    }

    @Override
    public boolean userExists(String mail) throws SQLException {
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT 1 FROM %s WHERE %s = ?;".formatted(
                            User.USER_TABLE,
                            User.USER_MAIL
                    )
            );
            stm.setString(1,mail);
            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                if(rs.getInt(1) == 1){
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
        return false;
    }

    @Override
    //აქ დავაბრუნოტ UserDTO
    public UserDTO getUserByMail(String mail) throws SQLException {
        try {

            PreparedStatement stm = connection.prepareStatement(
                    "SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s FROM %s where %s = ?;".formatted(
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
                            User.USER_MAIL
                    ));
            stm.setString(1, mail);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new UserDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getString(5),
                        imagesDAO.getImages(rs.getInt(1)),
                        hobbiesDAO.getHobbies(rs.getInt(1)),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return null;
    }

    @Override
    //აქ დავაბრუნოტ UserDTO
    public List<UserDTO> getUsers(String city, int user_id) throws SQLException {
        //აქ დავაბრუნოტ UserDTO
        List <UserDTO> users = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s  FROM %s WHERE %s = ? AND %s != ?;".formatted(
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
                            User.USER_USER_ID
                    )
            );
            stm.setString(1, city);
            stm.setInt(2, user_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                users.add(new UserDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getString(5),
                        imagesDAO.getImages(rs.getInt(1)),
                        hobbiesDAO.getHobbies(rs.getInt(1)),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)
                        ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
        return users;
    }



    @Override
    public List<UserDTO> getUsers(int min_age, int max_age, String city, int user_id) throws SQLException {
        // როგორ უნდა დავითვალოთ mysql ში ასაკი?
        List <UserDTO> users = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s  FROM %s WHERE %s = ? AND %s < ? AND %s > ? AND %s != ?;".formatted(
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
                            User.USER_BIRTH_DATE,
                            User.USER_BIRTH_DATE,
                            User.USER_USER_ID
                    )
            );
            stm.setString(1, city);
            stm.setInt(2, max_age);
            stm.setInt(3, min_age);
            stm.setInt(4, user_id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                users.add(new UserDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getString(5),
                        imagesDAO.getImages(rs.getInt(1)),
                        hobbiesDAO.getHobbies(rs.getInt(1)),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
        return users;
    }

}
