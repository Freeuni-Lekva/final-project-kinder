<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.08.2022
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
</head>
<body>
<h1> Enter your email</h1>

<form action="Registration" method="post" >
    Mail: <input type = "text" name="REGISTRATION_MAIL">

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
