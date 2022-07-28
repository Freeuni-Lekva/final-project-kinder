package DAO;

import java.util.List;

public interface HobbiesDAO {

    void addHobbie(String hobbie);
    void deleteHobbie(String hobbie);
    List<String> getHobbies();

}
