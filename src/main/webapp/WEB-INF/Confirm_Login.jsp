<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.08.2022
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Confirm Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body class="text-center" style="background-image: url('https://theme.zdassets.com/theme_assets/302164/8e05540d6f7ea752f80938c848f3ed79b548b959.png')">
<br>
<br>
<br>
<br>
<br>
<h3 style="color: aliceblue"> We sent a confirmation code to your mail</h3>
<br>

<%
    String Login_Error = (String) request.getAttribute("LOGIN_ERROR");
    if (Login_Error!= null) {%>
<div id = "error_text"><%=Login_Error%></div>
<% }%>

<form action="Profile" method="post" >

    <%
        String mail = request.getParameter("LOGIN_MAIL");
        session.setAttribute("mail",mail);

    %>

<%--    <div class="input-group mb-3" >--%>
<%--        <input name="LOGIN_CODE" type="text" class="form-control" placeholder="Enter your code here..." aria-label="Enter your code here..." aria-describedby="inputGroup-sizing-sm" >--%>
<%--        <button class="btn btn-outline-secondary button_class btn-light" type="button submit" disabled="disabled" id="submitbutton" style="color: #fc6880">Continue</button>--%>
<%--    </div>--%>
    <div class="input-group mb-3" style="padding-left: 560px; width: 865px"  >
        <input name="LOGIN_MODE" type="text" class="form-control"
               placeholder="Enter your code here..." aria-label="Enter your code here..."
               aria-describedby="inputGroup-sizing-sm" >

        <button class="btn btn-outline-secondary button_class btn-light"
                type="button submit" disabled="disabled" id="submitbutton"
                style="color: #fc6880">Continue</button>
    </div>


    <script type="text/javascript" language="javascript">
        let txt = document.querySelectorAll('[type="text"]');
        for (let i = 0; i < txt.length; i++) {
            txt[i].oninput = () => {
                if (!(txt[0].value == '')) {
                    submitbutton.removeAttribute('disabled')
                }
            }
        }
    </script>
</form>
</body>
</html>
