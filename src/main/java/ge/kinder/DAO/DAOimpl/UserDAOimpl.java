package ge.kinder.DAO.DAOimpl;

import ge.kinder.DAO.HobbiesDAO;
import ge.kinder.DAO.ImagesDAO;
import ge.kinder.DAO.LikesDAO;
import ge.kinder.DAO.UserDAO;
import ge.kinder.Models.DTO.UserDTO;
import ge.kinder.Models.Role;
import ge.kinder.Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOimpl implements UserDAO {

    private final Connection connection;
    private ImagesDAO imagesDAO;
    private HobbiesDAO hobbiesDAO;
    private LikesDAO likesDAO    ;
    public UserDAOimpl(Connection connection, HobbiesDAO hobbiesDAO, ImagesDAO imagesDAO, LikesDAO likesDAO) {
        this.hobbiesDAO = hobbiesDAO;
        this.imagesDAO = imagesDAO;
        this.connection = connection;
        this.likesDAO = likesDAO;
    }

    @Override
    public void addUser(User user) throws SQLException {

        try {

            PreparedStatement stm = connection.prepareStatement(("INSERT INTO kinder_base.user " +
                    "( %s, %s, %s, %s, %s, %s, %s, %s) " +
                    "VALUES (?, ?, ?, ?,?, ?, ?, ?)")
                    .formatted(User.USER_MAIL,
                    User.USER_FIRST_NAME,
                    User.USER_BIRTH_DATE,
                    User.USER_CITY,
                    User.USER_GENDER ,
                    User.USER_SHOW_GENDER ,
                    User.USER_PREFERENCE ,
                    User.USER_ORIENTATION),Statement.RETURN_GENERATED_KEYS);

            stm.setString(1, user.getMail());
            stm.setString(2, user.getFirst_name());
            stm.setDate(3,  user.getBirth_date());
            stm.setString(4, user.getCity());
            stm.setString(5, user.getGender());
            stm.setInt(6, user.isGenderIsShown());
            stm.setString(7, user.getGenderPref());
            stm.setString(8,user.getOrientation());

            if (stm.executeUpdate() == 1) {
                ResultSet rs = stm.getGeneratedKeys();
                rs.next();
                user.setUser_id(rs.getInt(1));
            }
            connection.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }


    @Override
    public void updateRow(User user, String rowName, int value) {
        int user_id = user.getUser_id();

        try {
            PreparedStatement stm = connection.prepareStatement(
                    "UPDATE kinder_base.user SET %s = ? WHERE %s = ?;".formatted(
                            rowName,
                            User.USER_USER_ID));
            stm.setInt(1, value);
            stm.setInt(2, user_id);
            stm.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        @Override
    public void updateRow(User user, String rowName, String value) {
            int user_id = user.getUser_id();

            try {
                PreparedStatement stm = connection.prepareStatement(
                        "UPDATE kinder_base.user SET %s = ? WHERE %s = ?;".formatted(
                                rowName,
                                User.USER_USER_ID));
                stm.setString(1, value);
                stm.setInt(2, user_id);
                stm.executeUpdate();
                connection.commit();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }

    @Override
    public void updateRow(User user, String rowName, Date value) {
        int user_id = user.getUser_id();

        try {
            PreparedStatement stm = connection.prepareStatement(
                    "UPDATE kinder_base.user SET %s = ? WHERE %s = ?;".formatted(
                            rowName,
                            User.USER_USER_ID));
            stm.setDate(1, value);
            stm.setInt(2, user_id);
            stm.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    public boolean deleteUser(User user) throws SQLException {

        try {
            PreparedStatement stm = connection.prepareStatement(
                    "DELETE FROM %s WHERE %s = ?;".formatted(
                            User.USER_TABLE,
                            User.USER_USER_ID
                    )
            );
            stm.setInt(1, user.getUser_id());
            if (stm.executeUpdate() == 1) {
                connection.commit();
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean userExists(String mail) throws SQLException {
        // tested
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

        }
        return false;
    }

    @Override
    //აქ დავაბრუნოტ UserDTO
    public User getUserByMail(String mail) throws SQLException {

        try {

            PreparedStatement stm = connection.prepareStatement(
                    ("SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s,%s,%s,%s" +
                            ", %s, %s, %s, %s, %s, %s, %s, %s FROM %s where %s = ?;").formatted(
                            User.USER_USER_ID,
                            User.USER_MAIL,
                            User.USER_FIRST_NAME,
                            User.USER_BIRTH_DATE,
                            User.USER_CITY,
                            User.USER_GENDER,
                            User.USER_SHOW_GENDER,
                            User.USER_PREFERENCE,
                            User.USER_ORIENTATION,
                            User.USER_BIO,
                            User.USER_HOROSCOPE,
                            User.USER_COMPANY,
                            User.USER_JOB,
                            User.USER_SCHOOL,
                            User.USER_MIN_AGE,
                            User.USER_MAX_AGE,
                            User.USER_REGISTRATION_DATE,
                            User.USER_SHOW_ACTIVE,
                            User.USER_LAST_Session,
                            User.USER_HIDED,
                            User.USER_ROLE,
                            User.SHOT_TO_LIKED,
                            User.USER_BALANCE,
                            User.SHOW_RECENTLTY_ACTIVE,
                            User.PREMIUM,
                            User.AGE_RANGE,
                            User.USER_TABLE,
                            User.USER_MAIL
                    ));
            stm.setString(1, mail);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getString(21),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(8),
                        rs.getInt(7),
                        imagesDAO.getImages(rs.getInt(1)),
                        hobbiesDAO.getHobbies(rs.getInt(1)),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getInt(15),
                        rs.getInt(16),
                        rs.getInt(18),
                        rs.getDate(19),
                        rs.getInt(22),
                        rs.getInt(20),
                        rs.getDate(17),
                        rs.getInt(23),
                        rs.getInt(24),rs.getInt(25),rs.getInt(26)

                );
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    public UserDTO getUser(String city, int user_id) throws SQLException {
        System.out.println("CITY --> " + city + " USER_ID --> " + user_id);
        List<UserDTO> users = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    ("SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s, %s  " +
                            "FROM %s WHERE %s = ? AND %s != ? AND %s != ?;").formatted(
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
            System.out.println(stm.toString());

            ResultSet rs = stm.executeQuery();
            //System.out.println("out-->"+ rs.getInt(1));
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                if (!(likesDAO.isLiked(user_id, rs.getInt(1)) ||
                        likesDAO.isDisliked(user_id, rs.getInt(1)) ||
                        likesDAO.isDisliked(rs.getInt(1), user_id)))
                    return new UserDTO(
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
                    );

            }

            System.out.println(users);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<UserDTO> getUsers(String city, int user_id) throws SQLException {
        System.out.println("CITY --> " +city + " USER_ID --> " + user_id );
        List <UserDTO> users = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    ("SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s, %s  " +
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
                            User.USER_TABLE,
                            User.USER_CITY,
                            User.USER_USER_ID,
                            User.USER_HIDED
                    )
            );
            stm.setString(1, city);
            stm.setInt(2, user_id);
            stm.setInt(3,1);
            System.out.println(stm.toString());

            ResultSet rs = stm.executeQuery();
            //System.out.println("out-->"+ rs.getInt(1));
            while (rs.next()) {
                System.out.println(rs.getInt(1));
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

            System.out.println(users);
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //return users;
    }

    public UserDTO getUser(int min_age, int max_age, String city, int user_id) throws SQLException {
        System.out.println("CITY --> " + city + " USER_ID --> " + user_id);
        try {
            PreparedStatement stm = connection.prepareStatement(
                    ("SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s, %s  FROM %s WHERE %s = ? AND %s != ? " +
                            "AND TIMESTAMPDIFF(year,%s,SYSDATE()) >= ? " +
                            "AND TIMESTAMPDIFF(year,%s,SYSDATE()) <= ? " +
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

            //System.out.println("out-->"+ rs.getInt(1));
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                if ((likesDAO.isLiked(user_id, rs.getInt(1)) ||
                        likesDAO.isDisliked(user_id, rs.getInt(1)) ||
                        likesDAO.isDisliked(rs.getInt(1), user_id)))
                    return new UserDTO(
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
                    );
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<UserDTO> getUsers(int min_age, int max_age, String city, int user_id) throws SQLException {

        List <UserDTO> users = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    ("SELECT %s, %s, %s, %s, %s, %s, %s, %s, %s, %s  FROM %s WHERE %s = ? AND %s != ? " +
                            "AND TIMESTAMPDIFF(year,%s,SYSDATE()) >= ? " +
                            "AND TIMESTAMPDIFF(year,%s,SYSDATE()) <= ? " +
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
              //}
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return users;
    }

}
