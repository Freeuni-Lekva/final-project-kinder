<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>First page</title>

</head>
<body>

<h1> Tinder  </h1>
<div id="buttons">
    <form action="Registration" method="get">
        <button class="button_class" id="registration">Create Account</button>
    </form>
    </br>
    <form action="Login" method="get">
        <button class="button_class" id="login">Log In</button>
    </form>

<%--   <button id = "register" type="submit" onclick='javascript:window.open("/kinder/Registration","ModalPopUp", "scrollbars=1,resizable=1,height=300,width=450")'>Create Account</button>--%>

<%--    <button id = "login" type="submit" onclick='javascript:window.open("/kinder/Login", "ModalPopUp", "scrollbars=1,resizable=1,height=300,width=450")'>Log In</button>--%>
    <dialog id="dialog">

        <object data="/kinder/Registration"
                width="400"
                height="400"
                type="text/html">
        </object>
    </dialog>

    <button id="show" onclick="dialogShow()">Open Dialog!</button>


    <script>
        function dialogShow() {
            document.getElementById("dialog").showModal();
        };


    </script>


</div>

</body>
</html>