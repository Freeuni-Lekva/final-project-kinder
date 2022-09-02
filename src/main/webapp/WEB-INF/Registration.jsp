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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel ="stylesheet" href="Registration.css">
</head>
<body style="background-image: url('https://theme.zdassets.com/theme_assets/302164/8e05540d6f7ea752f80938c848f3ed79b548b959.png');">
<div class="text-center" id="contents">
    <br>
    <br>
    <br>
    <br>
<h1 style="color: aliceblue"> Enter your email</h1>
    <br>
<form action="Registration" method="post" >
    <input type = "text" name="REGISTRATION_MAIL">

    <button id = "submitbutton" type="submit" disabled="disabled" style="color: #fc6880" class="button_class btn btn-light">Continue</button>

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
</div>



</body>
</html>
