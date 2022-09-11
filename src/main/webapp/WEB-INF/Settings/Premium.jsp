<%@ page import="ge.kinder.Models.User" %>
<%@ page import="ge.kinder.DAO.DAOimpl.UserDAOimpl" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.09.2022
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tinder+</title>
</head>
<body style="background-image: url('https://theme.zdassets.com/theme_assets/302164/8e05540d6f7ea752f80938c848f3ed79b548b959.png')">
<form action="Settings" method="post">
    <%
        UserDAOimpl userDao = (UserDAOimpl) request.getServletContext().getAttribute("USERDAO");
        User user = userDao.getUserByMail((String) session.getAttribute("mail"));

    %>

    <button id = "backButton" name="BackFromPremium" type="submit" >Back</button>
    <br/>
    <br/>
    Get Tinder+
    <br/>
    A First Class Dating Experience
    <br/>
    <br/>

<ul>
    <li>See Who Likes you</li>
    <li>Control who sees you</li>
    <li>Control who you see</li>
    <li>Superlikes</li>
    <li>Passport(Match and chat with people anywhere in Georgia)</li>
</ul>

    Your Balance is <%=user.getBalance()%> GEL.
    <br/>
    <br/>
    <% if(user.getIs_premium()==0){%>
    Price is 49 GEL.
    <br/>
    <%}else {%>
    You already have Premium Account.
    <%}%>
    <br/>
    <br/>

    <button name="settingsButton" type="submit" value="BUY" <% if(user.getIs_premium()==1){%> disabled = "disabled"<%}%>>Buy now</button>
</form>
</body>
</html>
