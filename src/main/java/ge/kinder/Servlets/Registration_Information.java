package ge.kinder.Servlets;

import ge.kinder.Models.User;
import ge.kinder.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(name = "Registration_Information", urlPatterns = "/Registration_Information")
public class Registration_Information extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String otp = req.getParameter("REGISTRATION_CODE");
        if(otp == null) {
            req.getRequestDispatcher("/WEB-INF/Confirm_Registration.jsp").forward(req, resp);
            return;
        }else {
            UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
            if(userService.confirmCode((User) req.getSession().getAttribute("user"),otp)) {
                req.getRequestDispatcher("/WEB-INF/Registration_Info.jsp").forward(req,resp);
            } else {
                req.setAttribute("REGISTRATION_ERROR","Wrong code. Try again.");
                req.getRequestDispatcher("/WEB-INF/Confirm_Registration.jsp").forward(req, resp);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
        String otp = req.getParameter("REGISTRATION_CODE");

       if(userService.confirmCode((User) req.getSession().getAttribute("user"),otp)) {
            req.getRequestDispatcher("/WEB-INF/Registration_Info.jsp").forward(req,resp);
        } else {
            req.setAttribute("REGISTRATION_ERROR","Wrong code. Try again.");
            req.getRequestDispatcher("/WEB-INF/Confirm_Registration.jsp").forward(req, resp);

        }


    }
}
