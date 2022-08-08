package ge.kinder.Servlets;

import jdk.swing.interop.SwingInterOpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Registration_Info", urlPatterns = "/Registration_Info")
public class Registration_Info extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if ((req.getSession() != null && req.getSession().getAttribute("user") != null)) {
//            req.getRequestDispatcher("/WEB-INF/Registration_Info.jsp").forward(req, resp);
//        } else {
//            req.getRequestDispatcher("/index.jsp").forward(req, resp);
//        }


        req.getRequestDispatcher("/WEB-INF/Registration_Info.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("information Added");
        System.out.println(req.getParameter("FIRST_NAME"));
        System.out.println(req.getParameter("MONTH"));
        System.out.println(req.getParameter("DAY"));
        System.out.println(req.getParameter("YEAR"));
        System.out.println(req.getParameter("SHOW_GENDER"));
        System.out.println(req.getParameter("SHOW_ME"));
        //photos
        // passions
        // orientation




    }
}
