package ge.kinder.Servlets;

import ge.kinder.Models.User;
import ge.kinder.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Email_Verification", urlPatterns = "/Email_Verification")
public class Email_Verification extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
        String email = req.getParameter("VERIFICATION_MAIL");
        req.getSession().setAttribute("newMail",email);
        User user = (User) req.getSession().getAttribute("user");
        try{
            userService.verificateUser(user,email);
            req.getRequestDispatcher("/WEB-INF/Settings/Confirm_Email.jsp").forward(req, resp);
        }catch (Exception e){
            //
        }

    }
}
