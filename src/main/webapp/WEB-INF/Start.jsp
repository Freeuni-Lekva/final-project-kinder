<%@ page import="ge.kinder.Models.User" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.08.2022
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My profile</title>
</head>
<body>
<form action="Profile">

    <div   style="display:block; width:100%;">

        <div style="width: 30%; height: 100%; overflow-y: scroll;  float: left;">

            Account Settings
            <%User user = (User) request.getSession().getAttribute("user");%>

                <p>
                    '<%=user.toString()%>'
                </p>
        </div>

        <div style="width:70%; height:100%; ">
<%--        <%User user = (User) request.getSession().getAttribute("user");--%>
<%--          String image = user.getImages().get(0);--%>
<%--           System.out.println(image);--%>
<%--        %>--%>

<%--            <img height="100" width="100" scr="C:/Users/User/Desktop"+<%=image%> />--%>
<%--            <img  scr="C:/Users/User/Downloads/IMG_20220807_211523.jpg" width="500" height="600"/>--%>

        </div>
    </div>


</form>

</body>
</html>
