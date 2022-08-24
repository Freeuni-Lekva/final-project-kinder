<%@ page import="java.util.regex.Pattern" %>
<%@ page import="ge.kinder.Models.User" %>
<%@ page import="java.sql.Date" %><%--
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
        User user = (User) request.getSession().getAttribute("user");
        String image = user.getImages().get(0);
        System.out.println(image);

        int showGender = user.isGenderIsShown();
        System.out.println("gender" + showGender);

    %>
    <div   style="display:block; width:100%;">

        <div style="width: 30%; height: 100%; overflow-y: scroll;  float: left;">
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

        <img src="images/<%=user.getImages().get(0)%>" alt="photo" width="400px" height="400px">


        <br/>
        <%=user.getFirst_name()   %>

        <%=(int) Math.floor((new Date(System.currentTimeMillis()).getTime()-user.getBirth_date().getTime() ) / 3.15576e+10) %>

        <br/>
        <% if (showGender==1) {%> <%=user.getGender()%> <% }%>

    </div>
</div>
</form>
</body>
</html>
