package ge.kinder.Servlets;

import ge.kinder.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login_Confirmation", urlPatterns = "/Login_Confirmation")
public class Login_Confirmation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String otp = req.getParameter("LOGIN_CODE");
        if(otp == null) {
            req.getRequestDispatcher("/WEB-INF/Confirm_Login.jsp").forward(req, resp);
            return;
        }else {
            UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
            if(userService.confirmCode(otp)) {
                req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req,resp);
            } else {
                req.setAttribute("LOGIN_ERROR","Wrong code. Try again.");
                req.getRequestDispatcher("/WEB-INF/Confirm_Login.jsp").forward(req, resp);

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
        String otp = req.getParameter("LOGIN_CODE");

        if(userService.confirmCode(otp)) {
            req.getRequestDispatcher("/WEB-INF/Start.jsp").forward(req,resp);
        } else {

            req.setAttribute("LOGIN_ERROR","Wrong code. Try again.");
            req.getRequestDispatcher("/WEB-INF/Confirm_Login.jsp").forward(req, resp);

        }
    }
}
