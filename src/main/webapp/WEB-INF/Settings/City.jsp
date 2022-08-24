<%@ page import="ge.kinder.Models.User" %>
<%@ page import="java.sql.Date" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 20.08.2022
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>City Settings</title>
</head>
<body>


<form action="Settings" method="post">
    <%
        User user = (User) request.getSession().getAttribute("user");
        String image = user.getImages().get(0);
        System.out.println(image);
        String name = user.getFirst_name();
        String city = user.getCity();
        Date date = (Date) user.getBirth_date();
        String gender = user.getGender();
        int showGender = user.isGenderIsShown();
        System.out.println("gender" + showGender);
        String pref = user.getGenderPref();

    %>
    <div   style="display:block; width:100%;">

        <div style="width: 30%; height: 100%; overflow-y: scroll;  float: left;">
            CURRENT LOCATION
            <%
                String mail = (String) session.getAttribute("mail");


            %>
            <br/>
            <br/>
            <input id = "input" type = "text" name="City"  value=<%=city%>  >
            <br/>
            <br/>
            <output id ="output">My Current Location</output>

            <script>
                document.getElementById('input').addEventListener('input', function() {

                    document.getElementById("output").innerHTML = "By changing city,<%=city%> will no longer be associated with your account";
                    document.getElementById("b").disabled = false;
                } );
            </script>
            <br/>
            <br/>
            <button id = "b" name="sendCode" type="submit" value="verification" disabled="disabled">Change City</button>
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
