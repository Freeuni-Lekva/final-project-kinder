<%@ page import="ge.kinder.Models.User" %>
<%@ page import="java.sql.Date" %>
<%@ page import="ge.kinder.DAO.DAOimpl.UserDAOimpl" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.08.2022
  Time: 0:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Looking for</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body style="background-image: url('https://theme.zdassets.com/theme_assets/302164/8e05540d6f7ea752f80938c848f3ed79b548b959.png')">
<form action="Settings" method="post">
    <%
        UserDAOimpl userDao = (UserDAOimpl) request.getServletContext().getAttribute("USERDAO");
        User user = userDao.getUserByMail((String) session.getAttribute("mail"));
    %>
    <div   style="display:block; width:100%;">

        <div style="width: 30%; height: 100%; overflow-y: scroll;  float: left;">

            <%
                String mail = (String) session.getAttribute("mail");

            %>
<%--            <button id = "backButton" name="BackFromPref" type="submit" >Back</button>--%>
            <button type="button submit" id = "backButton" class="btn btn-light">Back</button>


            <br/>
            <br/>
            <div class="preference_buttons" data-toggle="buttons" style="color: aliceblue">

                <br/>
                <br/>

                <label class="btn-pref">
                    <input type="radio" name="preference"
                    <%if (user.getGenderPref().equals("Man")) {%> checked="checked"<% }%> value="Men" >Men
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="preference"  <%if (user.getGenderPref().equals("Woman")) {%> checked="checked"<% }%> value="Woman"> Women
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="preference"  <%if (user.getGenderPref().equals("Everyone")) {%> checked="checked"<% }%>  value="Everyone">Everyone
                </label>
                <br/>
                <br/>

            </div>
            <div id="results_1" class="btn_1" ></div>

            <br/>
            <br/>
<%--            <button id = "changeButton" name="changePref" type="submit"  disabled="disabled">Change Preference</button>--%>
            <button type="button submit" id = "changeButton" class="btn btn-light" disabled = "disabled">Change Preference</button>


            <script>
                let results_1 = document.getElementById("results_1");



                document.querySelector("div.preference_buttons").addEventListener("click", function(evt){
                    // Check if a radio button triggered the event
                    if(evt.target.type === "radio"){
                        // Populate the results area with the value of the clicked element
                        results_1.textContent = "You will see "+ evt.target.value;
                        document.getElementById("changeButton").disabled = false;
                    }
                });

            </script>

        </div>

        <div style="width:70%; height:100%; ">

            <div class="card bg-dark text-white">
                <img  class="card-img" src="images/<%=user.getImages().get(0)%>" alt="No image">
                <div class="card-img-overlay">
                    <h5 class="card-title"><%=user.getFirst_name()   %></h5>
                    <p class="card-text"><%=(int) Math.floor((new Date(System.currentTimeMillis()).getTime()-user.getBirth_date().getTime() ) / 3.15576e+10) %></p>
                    <p class="card-text"><% if (user.isGenderIsShown()==1) {%> <%=user.getGender()%> <% }%></p>
                </div>
            </div>

<%--            <%--%>
<%--                if (user.getImages().size()>0) {%>--%>
<%--            <img src="images/<%=user.getImages().get(0)%>" alt="photo" width="200px" height="200px">--%>
<%--            <% }%>--%>
            <%--            <%--%>
            <%--            if (user.getImages().size()>1) {%>--%>
            <%--            <img src="images/<%=user.getImages().get(1)%>" alt="photo" width="200px" height="200px">--%>
            <%--            <% }%>--%>
            <%--            <%--%>
            <%--                if (user.getImages().size()>2) {%>--%>
            <%--            <img src="images/<%=user.getImages().get(2)%>" alt="photo" width="200px" height="200px">--%>
            <%--            <% }%>--%>
            <%--            <%--%>
            <%--                if (user.getImages().size()>3) {%>--%>
            <%--            <img src="images/<%=user.getImages().get(3)%>" alt="photo" width="200px" height="200px">--%>
            <%--            <% }%>--%>
            <%--            <%--%>
            <%--                if (user.getImages().size()>4) {%>--%>
            <%--            <img src="images/<%=user.getImages().get(4)%>" alt="photo" width="200px" height="200px">--%>
            <%--            <% }%>--%>
            <%--            <%--%>
            <%--                if (user.getImages().size()>5) {%>--%>
            <%--            <img src="images/<%=user.getImages().get(5)%>" alt="photo" width="200px" height="200px">--%>
            <%--            <% }%>--%>




<%--            <br/>--%>
<%--            <%=user.getFirst_name()   %>--%>

<%--            <%=(int) Math.floor((new Date(System.currentTimeMillis()).getTime()-user.getBirth_date().getTime() ) / 3.15576e+10) %>--%>

<%--            <br/>--%>
<%--            <% if (user.isGenderIsShown()==1) {%> <%=user.getGender()%> <% }%>--%>

        </div>
    </div>
</form>
</body>
</html>
