<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.08.2022
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Confirm Page</title>


</head>
<body>
<h1> Enter your code</h1>

<h5> Enter the code sent to mail </h5>

<%
    String Registration_Error = (String) request.getAttribute("REGISTRATION_ERROR");
    if (Registration_Error!= null) {%>
<div id = "error_text"><%=Registration_Error%></div>
<% }%>

<form action="Registration_Confirmation" method="post" >

 <input type = "text" name="REGISTRATION_CODE">
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
