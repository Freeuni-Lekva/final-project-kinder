<%@ page import="ge.kinder.Models.User" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="java.time.temporal.Temporal" %>
<%@ page import="ge.kinder.Models.DTO.UserDTO" %>
<%@ page import="ge.kinder.DAO.UserDAO" %>
<%@ page import="ge.kinder.DAO.DAOimpl.UserDAOimpl" %>
<%@ page import="ge.kinder.Services.Implementation.SuggestionServiceImpl" %>
<%@ page import="ge.kinder.Services.SuggestionService" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.08.2022
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Messages</title>
</head>
<body>
<form action="ProfilePage" method="post">

    <div   style="display:block; width:100%;">

        <div style="width: 30%; height: 100%; overflow-y: scroll;  float: left;">

        <%
            UserDAOimpl userDao = (UserDAOimpl) request.getServletContext().getAttribute("USERDAO");
            User user = userDao.getUserByMail((String) session.getAttribute("mail"));
        %>

            <button name="mainPageButton" type="submit" value="toSettings">Settings</button>
            <button name="mainPageButton" type="submit" value="matches">Matches</button>
            <button name="mainPageButton" type="submit" value="messages">Messages</button>


        </div>


        <div style="width:70%; height:100%; ">
            <% SuggestionService ss = (SuggestionService) request.getSession().getServletContext().getAttribute("SUGGESTION_SERVICE");
                List<UserDTO> suggestions = ss.getSuggestions(user);
            %>
            '<%= suggestions.toString() %>'
        </div>


    </div>
</form>

</body>
</html>

