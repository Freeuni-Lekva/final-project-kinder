<%@ page import="ge.kinder.Models.User" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="java.time.temporal.Temporal" %>
<%@ page import="ge.kinder.Models.DTO.UserDTO" %>
<%@ page import="ge.kinder.DAO.UserDAO" %>
<%@ page import="ge.kinder.DAO.DAOimpl.UserDAOimpl" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.08.2022
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My profile</title>
</head>
<body>
<form action="Settings" method="post">

    <div   style="display:block; width:100%;">

        <div style="width: 30%; height: 100%; overflow-y: scroll;  float: left;">

        <%
            UserDAOimpl userDao = (UserDAOimpl) request.getServletContext().getAttribute("USERDAO");
            User user = userDao.getUserByMail((String) session.getAttribute("mail"));
        %>

            <button name="settingsButton" type="submit" value="toMainPage">Main Page</button>

            <br/>
            <br/>
        ACCOUNT SETTINGS
        <br/>
        <br/>
         <button name="settingsButton" type="submit" value="TinderVersion">Tinder+</button>
        <br/>
        <br/>
         <button name="settingsButton" type="submit" value="Email">Email <%=user.getMail()%></button>
        <br/>
        <br/>

        DISCOVERY SETTINGS
        <br/>
        <br/>

        <button name="settingsButton" type="submit" value="City">City <%=user.getCity()%></button>
        <br/>
        <br/>
         <button name="settingsButton" type="submit" value="Preference">Looking for <%=user.getGenderPref()%></button>
        <br/>
        <br/>

        <label for="pref_1">Min Age Preference</label>
        <input type="range"  value = "18" id="pref_1" name="pref_1" min="18" max="100" oninput="this.nextElementSibling.value = this.value">
            <output name="min"></output>
        <br/>
        <br/>
        <label for="pref_2">Max Age Preference</label>
            <input type="range"  value = "100" id="pref_2" name="pref_2" min="18" max="100" oninput="this.nextElementSibling.value = this.value">
            <output name ="max"></output>
        <br/>
        <br/>



        Only show people in this range
        <input type="checkbox" id="age_range" class="checkbox" name ="SHOW" />

                <br/>
                <br/>

                ---------------------------------------

                <div class="btn-group_1" data-toggle="buttons">
                CONTROL WHO YOU SEE Tinder Plus
                <br/>
                <br/>

                <label class="btn btn-primary">
                        <input type="radio" name="test_1" checked="checked" value="0">Balanced Recomendations &#x00A; See the most relevant people to you(default)
                </label>
                <br/>
                <br/>
                <label class="btn btn-primary">
                        <input type="radio" name="test_1" value="1">Recently Active &#x00A; See the most recently active people first
                </label>
                <br/>
                <br/>

        </div>
        <div id="results_1" class="btn_1" ></div>
        ---------------------------------------

        <div class="btn-group_2" data-toggle="buttons">
                CONTROL WHO SEES YOU Tinder Plus
                <br/>
                <br/>

                <label class="btn btn-primary">
                        <input type="radio" name="test" checked="checked" value="0">Standard &#x00A; Only be shown to certain types of people &#x00A; for individual recommendations
                </label>
                <br/>
                <br/>
                <label class="btn btn-primary">
                        <input type="radio" name="test" value="1">Only people I`ve Liked &#x00A; Only people I`ve right swiped will see me
                </label>


                <br/>
                <br/>

        </div>
        <div id="results_2" class="btn_2" ></div>

                ---------------------------------------


        <script>
                let results_1 = document.getElementById("results_1");

                // Set up a click handler on the parent of the radio buttons so that
                // any clicks to the descendants will bubble up to the parent
                document.querySelector("div.btn-group_1").addEventListener("click", function(evt){
                        // Check if a radio button triggered the event
                        if(evt.target.type === "radio"){
                                // Populate the results area with the value of the clicked element
                                results_1.textContent = evt.target.value;

                        }
                });
                let results_2 = document.getElementById("results_2");


                // Set up a click handler on the parent of the radio buttons so that
                // any clicks to the descendants will bubble up to the parent
                document.querySelector("div.btn-group_2").addEventListener("click", function(evt){
                        // Check if a radio button triggered the event
                        if(evt.target.type === "radio"){
                                // Populate the results area with the value of the clicked element
                                results_2.textContent = evt.target.value;

                        }
                });
        </script>

        <style>
                .btn_1{
                        display: none;
                }
                .btn_2{
                        display: none;
                }

        </style>
                <br/>
                <br/>


        Show me on Tinder
        <input type="checkbox" id="show_me" class="checkbox" name = "SHOW_ME"/>

        <
        <br/>
        <br/>
            Show Activity Status
            <input type="checkbox" id="activity_status" class="checkbox" name ="SHOW_STATUS" />

            <br/>
        <br/>

            <button name="settingsButton" type="submit" value="Logout">Logout</button>
            <br/>
        <br/>

            <button name="settingsButton" type="submit" value="Delete">Delete Account</button>


        </div>

        <div style="width:70%; height:100%; "> <%
            if (user.getImages().size()>0) {%>
            <img src="images/<%=user.getImages().get(0)%>" alt="photo" width="200px" height="200px">
            <% }%>
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



    <br/>
    <%=user.getFirst_name()   %>

    <%=(int) Math.floor((new Date(System.currentTimeMillis()).getTime()-user.getBirth_date().getTime() ) / 3.15576e+10) %>

            <br/>
    <% if (user.isGenderIsShown()==1) {%> <%=user.getGender()%> <% }%>

        </div>
    </div>


</form>

</body>
</html>
