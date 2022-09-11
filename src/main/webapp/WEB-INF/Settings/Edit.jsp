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
    <link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
    <meta charset=utf-8 />
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

                    <label  for="pref_1">Min Age Preference</label>
                    <input type="range"  id="pref_1" name="pref_1" <%if(user.getSearchInRange()==0){%>value = "18" <%}else{%>value="<%=user.getMin_age()%>"<%}%> class="form-label" min="18" max="100" oninput="this.nextElementSibling.value = this.value">
                    <output  name="min"></output>
                    <br/>
                    <br/>

                    <label  for="pref_2">Max Age Preference</label>
                    <input type="range"  id="pref_2" name="pref_2" <%if(user.getSearchInRange()==0){ %>value = "100" <%}else{%>value="<%=user.getMax_age()%>" <%}%> class="form-label" min="18" max="100" oninput="this.nextElementSibling.value = this.value">
                    <output  name ="max"></output>
                    <br/>
                    <br>
                    <span>
                        <p >Only show people in this range
                        <input type="checkbox" id="age_range" class="checkbox" name ="SHOW" <%if(user.getSearchInRange()==1){ %>checked = "checked" <%}%> />
                        </p>
                    </span>
                </div>
            </div>


            <div id = "control_who_you_see">
                <div class="btn-group_1" data-toggle="buttons">
                    <h4 >CONTROL WHO YOU SEE </h4>
                    <h5 >Tinder+ Only</h5>


<%--                    <input type="radio" class="btn-check" name="test_1" id="test_1" autocomplete="off" <%if(user.getShow_active_people()==0 || user.getIs_premium()==0) {%> checked="checked"<%}%> value=0">--%>
<%--                    <label class="btn btn-secondary" for="test_1">Balanced Recommendations</label>--%>

<%--                    <input type="radio" class="btn-check" name="test_1" id="test_2" <%if(user.getShow_active_people()==1) {%> checked="checked"<%}%> <%if(user.getIs_premium()==0) {%> disabled="disabled"<%}%> value="1" autocomplete="off">--%>
<%--                    <label class="btn btn-secondary" for="test_2">Radio</label>--%>

                    <label class="btn btn-primary">
                        <input  type="radio" name="test_1" <%if(user.getShow_active_people()==0 || user.getIs_premium()==0) {%> checked="checked"<%}%> value="0">Balanced Recomendations &#x00A; See the most relevant people to you(default)
                    </label>
                    <br/>
                    <br/>
                    <label class="btn btn-primary">
                        <input  type="radio" name="test_1"<%if(user.getShow_active_people()==1) {%> checked="checked"<%}%> <%if(user.getIs_premium()==0) {%> disabled="disabled"<%}%> value="1">Recently Active &#x00A; See the most recently active people first
                    </label>
                    <br/>
                    <br/>

                </div>
                <div id="results_1" class="btn_1" ></div>

                <br/>
                <br/>
            </div>


            <div id ="control_who_you_see_2">
                <div class="btn-group_2" data-toggle="buttons">
                    <h4 >CONTROL WHO SEES YOU </h4>
                    <h5 >Tinder+ Only</h5>


                    <label class="btn btn-primary">
                        <input  type="radio" name="test" <%if(user.getShow_to_liked()==0 || user.getIs_premium()==0) {%> checked="checked"<%}%> value="0">Standard &#x00A; Only be shown to certain types of people &#x00A; for individual recommendations
                    </label>
                    <br/>
                    <br/>
                    <label class="btn btn-primary">
                        <input  type="radio" name="test" <%if(user.getShow_to_liked()==1) {%> checked="checked"<%}%> <%if(user.getIs_premium()==0) {%> disabled="disabled"<%}%> value="1">Only people I`ve Liked &#x00A; Only people I`ve right swiped will see me
                    </label>

                    <br/>
                    <br/>

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
                <p > Show me on Tinder
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


        <div style="width:70%; height:100%; overflow-y: scroll;  ">


            <% if (user.getImages().size()>0) {%>
            <img src="images/<%=user.getImages().get(0)%>"  alt="photo" width="200px" height="200px" >
            <% }%>

            <%
            if (user.getImages().size()>1) {%>
            <img src="images/<%=user.getImages().get(1)%>"  alt="photo" width="200px" height="200px">
            <% }%>
            <%
                if (user.getImages().size()>2) { %>
            <img src="images/<%=user.getImages().get(2)%>"   alt="photo" width="200px" height="200px">
            <% } else { %>

            <div class="fileUpload btn btn-primary">
            <label class="upload">
                <input type='file' name = "PHOTO_3"  onchange="readURL(this,'#photo_3');" />
                <img id="photo_3"  src=""  />
            </label>
            </div> <% }%>

            <% if (user.getImages().size()>3) {%>
            <img src="images/<%=user.getImages().get(3)%>" alt="photo" width="200px" height="200px">
            <% } else { %>

            <div class="fileUpload btn btn-primary">
                <label class="upload">
                    <input type='file' name = "PHOTO_4"  onchange="readURL(this,'#photo_4');" />
                    <img id="photo_4" src=""  />
                </label>
            </div> <% }%>

            <%   if (user.getImages().size()>4) {%>
            <img src="images/<%=user.getImages().get(4)%>" alt="photo" width="200px" height="200px">
            <% } else { %>

            <div class="fileUpload btn btn-primary">
                <label class="upload">
                    <input type='file' name = "PHOTO_5"  onchange="readURL(this,'#photo_5');" />
                    <img id="photo_5" src=""  />
                </label>
            </div> <% }%>
            <%
                if (user.getImages().size()>5) {%>
            <img src="images/<%=user.getImages().get(5)%>" alt="photo" width="200px" height="200px">
            <% }else { %>

            <div class="fileUpload btn btn-primary">
                <label class="upload">
                    <input type='file' name = "PHOTO_6"  onchange="readURL(this,'#photo_6');" />
                    <img id="photo_6" src=""  />
                </label>
            </div> <% }%>

            <style>input[type='file'] {
                color: rgba(0, 0, 0, 0)
            }</style>

            <script>
                function readURL(input,url) {
                    if (input.files && input.files[0]) {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            $(url)
                                .attr('src', e.target.result)
                                .width(150)
                                .height(200);
                        };
                        reader.readAsDataURL(input.files[0]);
                    }
                }
            </script>

            <br/>
            ABOUT <%=user.getFirst_name().toUpperCase()%>
            <br/>
            <input type = "text"  id = "bio" name="BIO" maxlength="500" <%if(user.getBio()!=null){%> value = "<%=user.getBio()%>"<%}%> />
            <br/>
            <br/>
            PASSIONS


            <%
                List<Hobby> hobbies = user.getHobbies();
            %>
            <div class="multipleSelection">
              <div class="selectBox"
             onclick="showCheckboxes()">
            <select>
                <option>Select hobbies</option>
            </select>
            <div class="overSelect"></div>
          </div>

          <div id="checkBoxes">
            <label for="first">
                <input type="checkbox" id="first" name="SPORT" <%if(hobbies.contains(Hobby.SPORT)){%> checked="checked"<%}%> />
                Sport
            </label>

            <label for="second">
                <input type="checkbox" id="second" name="INSTAGRAM" <%if(hobbies.contains(Hobby.INSTAGRAM)){%> checked="checked"<%}%>/>
                Instagram
            </label>
            <label for="third">
                <input type="checkbox" id="third"name="PHOTOS" <%if(hobbies.contains(Hobby.PHOTOS)){%> checked="checked"<%}%>/>
                Photos
            </label>
            <label for="fourth">
                <input type="checkbox" id="fourth" name="CARS" <%if(hobbies.contains(Hobby.CARS)){%> checked="checked"<%}%>/>
                Cars
            </label>
            <label for="fifth">
                <input type="checkbox" id="fifth"name="FOOTBALL"<%if(hobbies.contains(Hobby.FOOTBALL)){%> checked="checked"<%}%> />
                Football
            </label>
           </div>



        </div>
    <script>
        var show = true;

        function showCheckboxes() {
            var checkboxes =
                document.getElementById("checkBoxes");

            if (show) {
                checkboxes.style.display = "block";
                show = false;
            } else {
                checkboxes.style.display = "none";
                show = true;
            }
        }
    </script>
            <style>


                .multipleSelection {
                    width: 300px;
                    background-color: #BCC2C1;
                    /*position: absolute;*/
                }

                .selectBox {
                    position: relative;
                }

                .selectBox select {
                    width: 100%;
                    font-weight: bold;
                }

                .overSelect {
                    position: absolute;
                    left: 0;
                    right: 0;
                    top: 0;
                    bottom: 0;
                }

                #checkBoxes {
                    display: none;
                    border: 1px #8DF5E4 solid;
                }

                #checkBoxes label {
                    display: block;
                }

                #checkBoxes label:hover {
                    background-color: #4F615E;
                }
            </style>
            <br/>
            <br/>
            JOB TITLE
            <br/>
            <br/>
            <input type = "text"  id = "job" name="JOB" <%if(user.getJob()!=null){%>value ="<%=user.getJob()%>" <%}%>/>
            <br/>
            <br/>

            COMPANY
            <br/>
            <br/>
            <input type = "text"  id = "name" name="COMPANY" <%if(user.getCompany()!=null){%>value ="<%=user.getCompany()%>" <%}%> />
            <br/>
            <br/>
            SCHOOL
            <br/>
            <br/>
            <input type = "text"  id = "name" name="SCHOOL" <%if(user.getSchool()!=null){%>value ="<%=user.getSchool()%>" <%}%>/>
            <br/>
            <br/>
            GENDER
            <br/>
            <br/>
            <button name="settingsButton" type="submit" value="Gender"><%=user.getGender()%></button>
            <br/>
            <br/>

            SEXUAL ORIENTATION
            <br/>
            <br/>
            <%
                String orientation = "";
                if(user.getOrientation()!=null) {
                    orientation = user.getOrientation();

                }else {
                    orientation = "Add sexual orientation";
                }
            %>
            <button name="settingsButton" type="submit" value="Orientation"><%=orientation%></button>
            <br/>
            <br/>
            <button name="settingsButton" type="submit" value="Save">Save</button>

    </div>
    </div>



</form>

</body>
</html>
