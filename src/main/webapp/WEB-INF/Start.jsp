<%@ page import="ge.kinder.Models.User" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="java.time.temporal.Temporal" %>
<%@ page import="ge.kinder.Models.DTO.UserDTO" %>
<%@ page import="ge.kinder.DAO.UserDAO" %>
<%@ page import="ge.kinder.DAO.DAOimpl.UserDAOimpl" %>
<%@ page import="ge.kinder.Services.Implementation.SuggestionServiceImpl" %>
<%@ page import="ge.kinder.Services.SuggestionService" %>
<%@ page import="java.util.List" %>
<%@ page import="ge.kinder.Models.Hobby" %><%--
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        img {
            max-width: 100%;
            max-height: 100%;
        }

        #image_place {
            position: relative;
            top: 4%;
            left: 33%;
            width: 34%;
            height: 65%;
        }

        #image_style {
            position: relative;
            height: 100%;
            width: 100%;
        }

        #info_place {
            position: absolute;
            top: 70%;
            height: 20%;
            left: 63%;
            width: 30%;
        }

        #edit_button_style{
            position: absolute;
            top:93%;
            height: 5%;
            left: 58%;
            width: 16%;
        }

        #main_page_button {
            position: relative;
            top: 2%;
            height: 5%;
            width: 40%;
            left: 30%;
        }

        #account_settings {
            position: relative;
            top: 5%;
            left: 5%;
            height: 15%;
            width: 90%;
            border: 2px dashed black;
            border-radius: 10px;
            text-align: center;
        }

        #account_settings_text {
            position: relative;
            top: 3%;
        }

        #account_settings_buttons {
            position: relative;
            top: 13%;
            left: 5%;
            width: 90%;
            height: 10%;
        }

        #discovery_settings {
            position: relative;
            top: 8%;
            left: 5%;
            height: 35%;
            width: 90%;
            border: 2px dashed black;
            border-radius: 10px;
        }

        #discovery_settings_buttons {
            position: relative;
            top: 6%;
            left: 5%;
            width: 90%;
            height: 10%;
        }

        #age_preff {
            position: relative;
            top: 18%;
            left: 5%;
            width: 91%;
        }

        #control_who_you_see {
            position: relative;
            top: 11%;
            height: 35%;
            left: 5%;
            width: 90%;
            border: 2px dashed black;
            border-radius: 10px;
            text-align: center;
        }

        #control_who_you_see_2 {
            position: relative;
            top: 14%;
            height: 35%;
            left: 5%;
            width: 90%;
            border: 2px dashed black;
            border-radius: 10px;
            text-align: center;
        }

        #show_me_on_tinder {
            position: relative;
            top: 17%;
            left: 10%;
            width: 80%;
            height: 5%;
            border: 2px dashed black;
            border-radius: 10px;
            text-align: center;
        }

        #log_out_style {
            position: relative;
            top: 20%;
            width: 90%;
            left: 5%;
        }



    </style>
</head>
<body style="background-image: url('https://theme.zdassets.com/theme_assets/302164/8e05540d6f7ea752f80938c848f3ed79b548b959.png')">
<form action="Settings" method="post">

    <div   style="display:block; width:100%;">

        <div style="width: 30%; height: 100%; overflow-y: scroll;  float: left;">

        <%
            UserDAO userDao = (UserDAOimpl) request.getServletContext().getAttribute("USERDAO");
            User user = userDao.getUserByMail((String) session.getAttribute("mail"));

        %>

            <button id ="main_page_button" name="settingsButton" type="button submit" class="btn btn-light" value="toMainPage">Main Page</button>

            <div id = "account_settings">
                <div id = "account_settings_text" > ACCOUNT SETTINGS </div>
                <div id = "account_settings_buttons">
                    <span>
                         <button name="settingsButton" style="left: 5%; width: 45%;" type="button submit" value="TinderVersion" class="btn btn-light">Tinder+</button>
                    </span>
                    <span>
                         <button name="settingsButton" style="left: 50%; width: 50%;" type="button submit"value="Email" class="btn btn-light">Change Email</button>
                    </span>
                </div>
            </div>
            <div id = "discovery_settings">

                <div style="position: relative; top: 3%; text-align: center">  DISCOVERY SETTINGS </div>
                <div id = "discovery_settings_buttons">
                    <span>
                         <button type="button submit" style="left: 5%; width: 35%;" name="settingsButton" value="City" class="btn btn-light">City: <%=user.getCity()%></button>
                    </span>
                    <span>
                         <button type="button submit" style="left: 40%; width: 60%;" name="settingsButton" value="Preference" class="btn btn-light">Looking for: <%=user.getGenderPref()%></button>
                    </span>
                </div>

                <div id = "age_preff">

                    <label style="color: aliceblue" for="pref_1">Min Age Preference</label>
                    <input type="range"  id="pref_1" name="pref_1" <%if(user.getSearchInRange()==0){%>value = "18" <%}else{%>value="<%=user.getMin_age()%>"<%}%> class="form-label" min="18" max="100" oninput="this.nextElementSibling.value = this.value">
                    <output style="color: aliceblue" name="min"></output>
                    <br/>
                    <br/>

                    <label style="color: aliceblue" for="pref_2">Max Age Preference</label>
                    <input type="range"  id="pref_2" name="pref_2" <%if(user.getSearchInRange()==0){ %>value = "100" <%}else{%>value="<%=user.getMax_age()%>" <%}%> class="form-label" min="18" max="100" oninput="this.nextElementSibling.value = this.value">
                    <output style="color: aliceblue" name ="max"></output>
                    <br/>
                    <br>
                    <span>
                        <p   style="color: aliceblue">Only show people in this range
                        <input type="checkbox" id="age_range" class="checkbox" name ="SHOW" <%if(user.getSearchInRange()==1){ %>checked = "checked" <%}%> />
                        </p>
                    </span>
                </div>
            </div>


            <div id = "control_who_you_see">
                <div class="btn-group_1" data-toggle="buttons">
                        <h4 style="color: aliceblue">CONTROL WHO YOU SEE </h4>
                        <h5 style="color: aliceblue">Tinder+ Only</h5>


                        <label class="btn btn-primary">
                            <input style="color: aliceblue" type="radio" name="test_1" <%if(user.getShow_active_people()==0 || user.getIs_premium()==0) {%> checked="checked"<%}%> value="0">Balanced Recomendations &#x00A; See the most relevant people to you(default)
                        </label>
                        <br/>
                        <br/>
                        <label class="btn btn-primary">
                            <input style="color: aliceblue" type="radio" name="test_1"<%if(user.getShow_active_people()==1) {%> checked="checked"<%}%> <%if(user.getIs_premium()==0) {%> disabled="disabled"<%}%> value="1">Recently Active &#x00A; See the most recently active people first
                        </label>
                        <br/>
                        <br/>

<%--                    <input type="radio" class="btn-check" name="test_1" id="test_1" autocomplete="off" <%if(user.getShow_active_people()==0 || user.getIs_premium()==0) {%> checked="checked"<%}%> value=0">--%>
<%--                    <label class="btn btn-secondary" for="test_1">Balanced Recommendations</label>--%>

<%--                    <input type="radio" class="btn-check" name="test_1" id="test_2" <%if(user.getShow_active_people()==1) {%> checked="checked"<%}%> <%if(user.getIs_premium()==0) {%> disabled="disabled"<%}%> value="1" autocomplete="off">--%>
<%--                    <label class="btn btn-secondary" for="test_2">Radio</label>--%>

                    </div>
                    <div id="results_1" class="btn_1" ></div>

                <br/>
                <br/>
            </div>


            <div id ="control_who_you_see_2">
                <div class="btn-group_2" data-toggle="buttons">
                    <h4 style="color: aliceblue">CONTROL WHO SEES YOU </h4>
                    <h5 style="color: aliceblue">Tinder+ Only</h5>


                    <label class="btn btn-primary">
                        <input style="color: aliceblue" type="radio" name="test" <%if(user.getShow_to_liked()==0 || user.getIs_premium()==0) {%> checked="checked"<%}%> value="0">Standard &#x00A; Only be shown to certain types of people &#x00A; for individual recommendations
                    </label>
                    <br/>
                    <br/>
                    <label class="btn btn-primary">
                        <input style="color: aliceblue" type="radio" name="test" <%if(user.getShow_to_liked()==1) {%> checked="checked"<%}%> <%if(user.getIs_premium()==0) {%> disabled="disabled"<%}%> value="1">Only people I`ve Liked &#x00A; Only people I`ve right swiped will see me
                    </label>


<%--                    <br/>--%>
<%--                    <br/>--%>

<%--                    <input type="radio" class="btn-check" name="test_2" id="test_3" autocomplete="off" <%if(user.getShow_to_liked()==0 || user.getIs_premium()==0) {%> checked="checked"<%}%> value="0">--%>
<%--                    <label class="btn btn-secondary" for="test_3">Standard</label>--%>

<%--                    <input type="radio" class="btn-check" name="test_2" id="test_4" autocomplete="off" <%if(user.getShow_to_liked()==1) {%> checked="checked"<%}%> <%if(user.getIs_premium()==0) {%> disabled="disabled"<%}%> value="1">--%>
<%--                    <label class="btn btn-secondary" for="test_4">Only the people I've liked</label>--%>

                </div>
            </div>
        <div id="results_2" class="btn_2" ></div>
<%--        <div class="btn-group_2" data-toggle="buttons">--%>
<%--            --%>
<%--                <br/>--%>
<%--            <input style="color: aliceblue"type="radio" class="btn-check" value ="0" name="options test" id="option1" autocomplete="off" checked>--%>
<%--            <label class="btn btn-secondary" for="option1">Standard</label>--%>

<%--            <input style="color: aliceblue" type="radio" class="btn-check" name="options test" value="0" id="option2" autocomplete="off">--%>
<%--            <label class="btn btn-secondary" for="option2">Only people I've liked</label>--%>



<%--            <label class="btn btn-primary">--%>
<%--                        <input type="radio" name="test" checked="checked" value="0">Standard &#x00A; Only be shown to certain types of people &#x00A; for individual recommendations--%>
<%--                </label>--%>
<%--                <br/>--%>
<%--                <br/>--%>
<%--                <label class="btn btn-primary">--%>
<%--                        <input type="radio" name="test" value="1">Only people I`ve Liked &#x00A; Only people I`ve right swiped will see me--%>
<%--                </label>--%>




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


        <div id ="show_me_on_tinder">
            <p style="color: aliceblue"> Show me on Tinder
                <input type="checkbox" id="show_me" class="checkbox" name = "SHOW_ME" <%if(user.isIs_hided() == 0){%> checked = "checked" <%}%>/>
            </p>
        </div>
        <div id = "log_out_style">
            <span>
                <button type="button submit" style="width:49%;" name="settingsButton" value="Logout" class="btn btn-light">Log Out</button>
                <button type="button submit" style="left: 50%; width: 49%;" name="settingsButton" value="Delete" class="btn btn-light">Delete Account</button>
            </span>
        </div>
        </div>


        <div style="width:70%; height:100%; float: left">
            <% if (user.getImages().size()>0) {%>
            <div id = "image_place">
                <img id = "image_style" class="card-img" src="images/<%=user.getImages().get(0)%>" alt="No image">
            </div>
            <% }%>
            <div id = "info_place">
                <h5 class="card-title"><%=user.getFirst_name()   %></h5>
                <p class="card-text"><%=(int) Math.floor((new Date(System.currentTimeMillis()).getTime()-user.getBirth_date().getTime() ) / 3.15576e+10) %></p>
                <p class="card-text"><% if (user.isGenderIsShown()==1) {%> <%=user.getGender()%> <% }%></p>
                <p class="card-text"><% for (Hobby hobby : user.getHobbies()) { %>
                    <%=hobby.toString().substring(0,1).toUpperCase() + hobby.toString().substring(1).toLowerCase()%>
                    <%}%></p>
            </div>
            <button id = "edit_button_style"name="settingsButton" type="submit" value="EditInfo">Edit Info</button>


        </div>
<%--            if (user.getImages().size()>0) {%>--%>
<%--            <img src="images/<%=user.getImages().get(0)%>" alt="photo" width="200px" height="200px">--%>
<%--            <% }%>--%>






<%--    <br/>--%>
<%--    <%=user.getFirst_name()   %>--%>

<%--    <%=(int) Math.floor((new Date(System.currentTimeMillis()).getTime()-user.getBirth_date().getTime() ) / 3.15576e+10) %>--%>

<%--            <br/>--%>
<%--    <% if (user.isGenderIsShown()==1) {%> <%=user.getGender()%> <% }%>--%>
<%--            <br/>--%>
<%--    <% for (Hobby hobby : user.getHobbies()) { %>--%>
<%--        <%=hobby.toString()%>--%>
<%--    <%}%>--%>



        <%--//DRAFTS BEFORE LASHA--%>
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

    </div>

</form>

</body>
</html>
