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
    <title>Login Confrim Page</title>
</head>
<body>


<h1> Enter your code</h1>

<h5> Enter the code sent to mail </h5>

<%
    String Login_Error = (String) request.getAttribute("LOGIN_ERROR");
    if (Login_Error!= null) {%>
<div id = "error_text"><%=Login_Error%></div>
<% }%>

<form action="Login_Confirmation" method="post" >



    <input type = "text" name="LOGIN_CODE">

    <button id = "submitbutton" type="submit" disabled="disabled">Continue</button>

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
