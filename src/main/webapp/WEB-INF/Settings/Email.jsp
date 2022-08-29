<%@ page import="java.util.regex.Pattern" %>
<%@ page import="ge.kinder.Models.User" %>
<%@ page import="java.sql.Date" %>
<%@ page import="ge.kinder.DAO.DAOimpl.UserDAOimpl" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.08.2022
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Email Settings</title>
</head>

<body>



<form action="Email_Verification" method="post">
    <%
        UserDAOimpl userDao = (UserDAOimpl) request.getServletContext().getAttribute("USERDAO");
        User user = userDao.getUserByMail((String) session.getAttribute("mail"));


    %>
    <div   style="display:block; width:100%;">

        <div style="width: 30%; height: 100%; overflow-y: scroll;  float: left;">
            <button id = "backButton" name="BackFromEmail" type="submit" >Back</button>
            <br/>
            <br/>
EMAIL
<%
    String mail = (String) session.getAttribute("mail");


%>

<br/>
<br/>
<input id = "input" type = "text" name="VERIFICATION_MAIL"  value=<%=mail%>  >
<br/>
<br/>
<output id ="output">Verified Email Address</output>

<script>
    document.getElementById('input').addEventListener('input', function() {
        if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(document.getElementById("input").value)){
            document.getElementById("output").innerHTML ="By verifying a new email,<%=mail%> will no longer be associated with your account" ;
            document.getElementById("b").disabled = false;
        } else {
            document.getElementById("output").innerHTML = "Email invalid.Please try another email address." ;
        }});</script>
<br/>
<br/>
<button id = "b" name="sendCode" type="submit" value="verification" disabled="disabled">Send me a Verification Email</button>
    </div>

    <div style="width:70%; height:100%; ">

        <%
            if (user.getImages().size()>0) {%>
        <img src="images/<%=user.getImages().get(0)%>" alt="photo" width="200px" height="200px">
        <% }%>
        <%--            <%--%>
        <%--            if (user.getImages().size()>1) {%>--%>
        <%--            <img src="images/<%=user.getImages().get(1)%>" alt="photo" width="200px" height="200px">--%>
        <%--            <% }%>--%>
        <%--            <%--%>
        <%--                if (user.getImages().size()>2) {%>--%>
        <%--            <img src="images/<%=user.getImages().get(2)%>" alt="photo" width="200px" height="200px">--%>
        <%--            <% }%>--%>
        <%--            <%--%>
        <%--                if (user.getImages().size()>3) {%>--%>
        <%--            <img src="images/<%=user.getImages().get(3)%>" alt="photo" width="200px" height="200px">--%>
        <%--            <% }%>--%>
        <%--            <%--%>
        <%--                if (user.getImages().size()>4) {%>--%>
        <%--            <img src="images/<%=user.getImages().get(4)%>" alt="photo" width="200px" height="200px">--%>
        <%--            <% }%>--%>
        <%--            <%--%>
        <%--                if (user.getImages().size()>5) {%>--%>
        <%--            <img src="images/<%=user.getImages().get(5)%>" alt="photo" width="200px" height="200px">--%>
        <%--            <% }%>--%>




        <br/>
        <%=user.getFirst_name()   %>

        <%=(int) Math.floor((new Date(System.currentTimeMillis()).getTime()-user.getBirth_date().getTime() ) / 3.15576e+10) %>

        <br/>
        <% if (user.isGenderIsShown()==1) {%> <%=user.getGender()%> <% }%>

    </div>
</div>
</form>
</body>
</html>
