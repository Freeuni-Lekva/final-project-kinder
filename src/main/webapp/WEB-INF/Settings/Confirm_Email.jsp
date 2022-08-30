<%@ page import="jdk.swing.interop.SwingInterOpUtils" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 19.08.2022
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Email Verification Page</title>
</head>
<body>
<h1> Enter your code</h1>

<h5> Enter the code sent to mail </h5>

<%
    String Verification_Error = (String) request.getAttribute("LOGIN_ERROR");
    if (Verification_Error!= null) {%>
<div id = "error_text"><%=Verification_Error%></div>
<% }%>

<form action="Settings" method="post" >



    <input type = "text" name="VERIFICATION_CODE">

    <button id = "submitbutton" name = "verification" value="verificationCode" type="submit" disabled="disabled">Continue</button>

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
