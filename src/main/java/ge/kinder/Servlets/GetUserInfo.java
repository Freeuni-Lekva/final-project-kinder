package ge.kinder.Servlets;

import com.google.gson.Gson;
import ge.kinder.Models.Role;
import ge.kinder.Models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "GetUserInfo", value = "/GetUserInfo" )
public class GetUserInfo extends HttpServlet {

    private static final String DEFAULT_IMAGE_MALE = "Content/Images/DEFAULT_THUMB_MALE.jpg";
    private static final String DEFAULT_IMAGE_FEMALE = "Content/Images/DEFAULT_THUMB_FEMALE.jpg";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int user_id = Integer.parseInt(req.getParameter("userID"));
        //User user = (User) req.getSession().getAttribute("user");
        PrintWriter out = resp.getWriter();
        try{
            User user = (User) req.getSession().getAttribute("user");
            if (!user.getRole().equals(Role.ADMIN.toString())) {
                out.print("{\"status\":2}");
                return;
            }

//            FullUserInfo fullInfo = new FullUserInfo();
//            fullInfo.setUsername(user.getUsername());
//            fullInfo.setAdmin(user.isAdmin());
//            fullInfo.setEmail(user.getEmail());
//            fullInfo.setHas_user_profile(user.getHas_user_profile());
//            fullInfo.setName(user.getName());
//            fullInfo.setSurname(user.getSurname());
//            fullInfo.setBanned(user.isBanned());
//            System.out.println(user_id);
//            System.out.println(user.isBanned());
//            if(user.getHas_user_profile()){
//                PersonalUserInfo userInfo = PersonalInfoDAO.getUserInfo(user_id);
//                fullInfo.setAge(userInfo.getAge());
//                fullInfo.setCity(userInfo.getCity());
//                fullInfo.setSex(userInfo.getSex());
//                fullInfo.setPhoneNumber(userInfo.getPhoneNumber());
//                try{
//                    String image = UserImagesDAO.getUserImages(userInfo.getUser_profile_id());
//                    if(image.isEmpty()) image = "null";
//                    fullInfo.setImage(image);
//                }catch (SQLException ex){
//                    ex.printStackTrace();
//                    String image =  DEFAULT_IMAGE_MALE;
//                    fullInfo.setImage(image);
//                }
//            }
            String json = (new Gson()).toJson(user);
            out.write(json);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            out.print("{\"status\":1}");
        }

    }
}
