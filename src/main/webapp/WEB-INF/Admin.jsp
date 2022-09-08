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
<%@ page import="java.util.List" %>
<%@ page import="ge.kinder.Models.Hobby" %>
<%@ page import="ge.kinder.Models.Role" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.08.2022
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="CSS bin/Admin.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        if(session == null){
            request.getSession();
            response.sendRedirect("index.jsp");
        }
        try{
            UserDAOimpl userDao = (UserDAOimpl) request.getServletContext().getAttribute("USERDAO");
            User user = userDao.getUserByMail((String) session.getAttribute("mail"));
            if(!user.getRole().equals(Role.ADMIN.toString())){
                response.sendRedirect("index.jsp");
            }
        }catch (SQLException ex){
            response.sendRedirect("index.jsp");
            ex.printStackTrace();
        }
    %>
</head>
<body>
<div class="bannedUsersContainer">
    <span class="bannedUsersContainer__Header">Banned Users</span>
    <div class="bannedUserBody"></div>
</div>

<div class="userBoxContainer">
    <div class="userContainer">
        <div class="userContainerHeader" id = "userName" val = "">
                <span id="currentUserMail">
                </span>
            <span id="currentUserStatus">
                </span>
        </div>
        <div class="userImageandInfoContainer">
            <img  class="userContainerImage" src="Content/Images/DEFAULT_UNI_PROFILE.jpg">
            <div class="userInfoBox">
                <span id="currentUserAge" class="userInfoValue"></span>
                <span id="currentUserGender" class="userInfoValue"></span>
                <span id="currentUserCity" class="userInfoValue"></span>
                <span id="currentUserName" class="userInfoValue"></span>
                <span id="currentUserEmail" class="userInfoValue"></span>
            </div>
        </div>
        <button  class="userBanButton">
            Ban User
        </button>
        <button  class="userUnbanButton">
            Unban User
        </button>
        <div  class="userIsAdminDisplay">
            User is Admin
        </div>
    </div>
</div>
<!--<button  id = "BanButton" style="background: #2AFE14" class="userToggleButton">
   <i style="color: white" class="fas fa-check fa-2x"></i>
   </button>
-->

<div class="navMainContainer">
    <span class="navWelcome"></span>
    <div class="searchForUserContainer">
        <input class="searchForUserInput" placeholder="Search for User">
        <i id="searchForUserId" style="color: white; cursor: pointer" class="fas fa-search fa-2x"></i>

    </div>
    <div class="navSignOut">
        <span>Log out</span>
        <form action="LogoutServlet" method="post">
            <button class="signOutButton" type="submit" >
                <i style="color: white"   class="fas fa-sign-out-alt"></i>
            </button>
        </form>
    </div>
</div>
</body>
</html>
