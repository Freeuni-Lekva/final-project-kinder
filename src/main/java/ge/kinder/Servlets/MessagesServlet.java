package ge.kinder.Servlets;

import com.google.gson.Gson;
import ge.kinder.Models.Message;
import ge.kinder.Services.Implementation.MessagesServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "MessagesServlet", urlPatterns = "/MessagesServlet")
public class MessagesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        int chatID =Integer.parseInt(req.getParameter("chatID"));

        MessagesServiceImpl messagesService = (MessagesServiceImpl) req.getServletContext().getAttribute("MESSAGES_SERVICE");

        PrintWriter out = resp.getWriter();
        System.out.println("chat_id" + chatID);
        List<Message> messageList = messagesService.getMessages(chatID);
        System.out.println(messageList);
        String json = (new Gson()).toJson(messageList);
        out.write(json);




    }

}

