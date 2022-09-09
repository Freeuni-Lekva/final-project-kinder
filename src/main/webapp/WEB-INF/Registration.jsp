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
    <link rel ="stylesheet" href="/CSS%20bin/Registration.css">
</head>
<body style="background-image: url('https://theme.zdassets.com/theme_assets/302164/8e05540d6f7ea752f80938c848f3ed79b548b959.png');">
<div class="text-center" id="contents">
    <br>
    <br>
    <br>
    <br>
<h1 style="color: aliceblue"> Enter your email</h1>
    <br>
    <%
        String Registration_Error = (String) request.getAttribute("REGISTRATION_ERROR");
        if (Registration_Error!= null) {%>
    <div id = "error_text" style="color: #FFFFFF"><%=Registration_Error%></div>
    <% }%>
    <br>
<form action="Registration" method="post" >
<%--    <div class="input-group mb-3" >--%>
<%--        <input name="REGISTRATION_MAIL" type="text" class="form-control" placeholder="yourmail@example.xyz" aria-label="yourmail@example.xyz" aria-describedby="inputGroup-sizing-sm" >--%>
<%--        <button class="btn btn-outline-secondary button_class btn-light" type="button submit" disabled="disabled" id="submitbutton" style="color: #fc6880">Continue</button>--%>
<%--    </div>--%>
    <div class="input-group mb-3" style="padding-left: 560px; width: 865px"  >
        <input name="REGISTRATION_MAIL" type="text" class="form-control"
               placeholder="yourmail@example.xyz" aria-label="yourmail@example.xyz"
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
</div>



</body>
</html>
