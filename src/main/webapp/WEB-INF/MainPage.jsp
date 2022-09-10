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
    <title>Main Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body style="background-image: url('https://theme.zdassets.com/theme_assets/302164/8e05540d6f7ea752f80938c848f3ed79b548b959.png')">
<form action="ProfilePage" method="post">

    <div   style="display:block; width:100%; overflow-y: hidden">

        <div style="width: 30%; height: 100%; overflow-y: scroll;  float: left;">

            <%
                UserDAOimpl userDao = (UserDAOimpl) request.getServletContext().getAttribute("USERDAO");
                User user = userDao.getUserByMail((String) session.getAttribute("mail"));
            %>
            <button name="mainPageButton" type="button submit" class="btn btn-light" value="toSettings">Settings</button>
            <button name="mainPageButton" type="button submit" class="btn btn-light" value="matches">Matches</button>
            <button name="mainPageButton" type="button submit" class="btn btn-light" value="messages">Messages</button>
<%--            <button name="mainPageButton" type="submit" value="toSettings">Settings</button>--%>
<%--            <button name="mainPageButton" type="submit" value="matches">Matches</button>--%>
<%--            <button name="mainPageButton" type="submit" value="messages">Messages</button>--%>


        </div>


        <div style="width:70%; height:100%;">

            <% SuggestionService ss = (SuggestionService) request.getSession().getServletContext().getAttribute("SUGGESTION_SERVICE");
                List<UserDTO> suggestions = ss.getSuggestions(user);
                for (UserDTO userDTO : suggestions){

                    if (userDTO.getImages().size()>0) {%>
                        <img src="images/<%=userDTO.getImages().get(0)%>" alt="photo" width="200px" height="200px">
                        <%=userDTO.getFirst_name()   %>
                        <%=userDTO.getCity()   %>
                        <%=(int) Math.floor((new Date(System.currentTimeMillis()).getTime()-userDTO.getBirth_date().getTime() ) / 3.15576e+10) %>

            <% }}%>

            <button name="mainPageButton" type="button submit" class="btn btn-light" value="nextPhoto">Next</button>
            <button name="mainPageButton" type="button submit" class="btn btn-light" value="prevPhoto">Previous</button>
            <button name="mainPageButton" type="button submit" class="btn btn-light" value="Info">Info</button>

            <button name="mainPageButton" type="button submit" class="btn btn-light" value="dislike">Like</button>
            <button name="mainPageButton" type="button submit" class="btn btn-light" value="superlike">Superlike</button>
            <button name="mainPageButton" type="button submit" class="btn btn-light" value="like">Dislike</button>

<%--            <button name="mainPageButton" type="submit" value="nextPhoto">Next</button>--%>
<%--            <button name="mainPageButton" type="submit" value="prevPhoto">Previous</button>--%>
<%--            <button name="mainPageButton" type="submit" value="Info">Info</button>--%>

<%--            <button name="mainPageButton" type="submit" value="dislike">Like</button>--%>
<%--            <button name="mainPageButton" type="submit" value="superlike">Superlike</button>--%>
<%--            <button name="mainPageButton" type="submit" value="like">Dislike</button>--%>

        </div>



    </div>

</form>

</body>
</html>

