<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>First page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body style="background-image: url('https://theme.zdassets.com/theme_assets/302164/8e05540d6f7ea752f80938c848f3ed79b548b959.png');">
<br>
<br>
<br>

<h1 class="text-center" style="color: aliceblue"> Welcome to Kinder  </h1>
<br>
<br>
<br>
<div class="text-center" id="buttons" >

    <form action="Login" method="get">
        <%--        <button class="button_class" id="login">Log In</button>--%>
        <button class="button_class btn btn-outline-light" style="color: aliceblue" id="login">Log in</button>

    </form>

    <br>
    <br>
    <form action="Registration" method="get">
<%--        <button class="button_class" id="registration">Create Account</button>--%>
        <p style="color: aliceblue">Not a member? <button style="color: #fc6880" class="button_class btn btn-light" id="registration">Register</button></p>
    </form>



<%--   <button id = "register" type="submit" onclick='javascript:window.open("/kinder/Registration","ModalPopUp", "scrollbars=1,resizable=1,height=300,width=450")'>Create Account</button>--%>

<%--    <button id = "login" type="submit" onclick='javascript:window.open("/kinder/Login", "ModalPopUp", "scrollbars=1,resizable=1,height=300,width=450")'>Log In</button>--%>
<%--    <dialog id="dialog">--%>

<%--        <object data="/kinder/Registration"--%>
<%--                width="400"--%>
<%--                height="400"--%>
<%--                type="text/html">--%>
<%--        </object>--%>
<%--    </dialog>--%>

<%--    <button id="show" onclick="dialogShow()">Open Dialog!</button>--%>


<%--    <script>--%>
<%--        function dialogShow() {--%>
<%--            document.getElementById("dialog").showModal();--%>
<%--        };--%>


<%--    </script>--%>



    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</div>

</body>
</html>