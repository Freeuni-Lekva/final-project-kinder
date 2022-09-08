package ge.kinder.Servlets;

import ge.kinder.Services.Implementation.MessagesServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "MessageServlet", urlPatterns = "/MessageServlet")
public class MessageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userID = Integer.parseInt(req.getParameter("userID"));

        int chatID = Integer.parseInt(req.getParameter("chatID"));
        String msg = req.getParameter("msg");
        MessagesServiceImpl messagesService = (MessagesServiceImpl) req.getServletContext().getAttribute("MESSAGES_SERVICE");

//        System.out.println(userID);
//        System.out.println(chatID);
//        System.out.println(msg);

        PrintWriter out = resp.getWriter();
        messagesService.addMessage(chatID, msg, userID);
        out.print("{\"status\":1}");
        return;

    }
}
