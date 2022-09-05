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

    <link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
    <meta charset=utf-8 />
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
            <input type="range" id="pref_1"  <%if(user.getSearchInRange()==0){%>value = "18" <%}else{%>value="<%=user.getMin_age()%>"<%}%>  name="pref_1" min="18" max="100" oninput="this.nextElementSibling.value = this.value">
            <output name="min"></output>
            <br/>
            <br/>
            <label for="pref_2">Max Age Preference</label>
            <input type="range"  id="pref_2" <%if(user.getSearchInRange()==0){ %>value = "100" <%}else{%>value="<%=user.getMax_age()%>" <%}%> name="pref_2" min="18" max="100" oninput="this.nextElementSibling.value = this.value">
            <output name ="max"></output>
            <br/>
            <br/>



            Only show people in this range
            <input type="checkbox" id="age_range" class="checkbox" name ="SHOW" <%if(user.getSearchInRange()==1){ %>checked = "checked" <%}%>  />

            <br/>
            <br/>

            ---------------------------------------

            <div class="btn-group_1" data-toggle="buttons">
                CONTROL WHO YOU SEE Tinder Plus
                <br/>
                <br/>

                <label class="btn btn-primary">
                    <input type="radio" name="test_1" <%if(user.getShow_active_people()==0 || user.getIs_premium()==0) {%> checked="checked"<%}%> value="0">Balanced Recomendations &#x00A; See the most relevant people to you(default)
                </label>
                <br/>
                <br/>
                <label class="btn btn-primary">
                    <input type="radio" name="test_1"<%if(user.getShow_active_people()==1) {%> checked="checked"<%}%> <%if(user.getIs_premium()==0) {%> disabled="disabled"<%}%> value="1">Recently Active &#x00A; See the most recently active people first
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
                    <input type="radio" name="test" <%if(user.getShow_to_liked()==0 || user.getIs_premium()==0) {%> checked="checked"<%}%> value="0">Standard &#x00A; Only be shown to certain types of people &#x00A; for individual recommendations
                </label>
                <br/>
                <br/>
                <label class="btn btn-primary">
                    <input type="radio" name="test" <%if(user.getShow_to_liked()==1) {%> checked="checked"<%}%> <%if(user.getIs_premium()==0) {%> disabled="disabled"<%}%> value="1">Only people I`ve Liked &#x00A; Only people I`ve right swiped will see me
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
            <input type="checkbox" id="show_me" class="checkbox" name = "SHOW_ME" <%if(user.isIs_hided() == 0){%> checked = "checked" <%}%>/>

            <br/>
            <br/>
            Show Activity Status
            <input type="checkbox" id="activity_status" class="checkbox" name ="SHOW_STATUS" <%if(user.getShowActiveStatus() == 1){%> checked = "checked" <%}%> />

            <br/>
            <br/>

            <button name="settingsButton" type="submit" value="Logout">Logout</button>
            <br/>
            <br/>

            <button name="settingsButton" type="submit" value="Delete">Delete Account</button>


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
