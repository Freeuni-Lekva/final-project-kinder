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
<%@ page import="ge.kinder.Models.Hobby" %>
<%@ page import="ge.kinder.Models.Role" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.08.2022
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script></head>

<form action="Settings" method="post">

    <%
        UserDAOimpl userDao = (UserDAOimpl) request.getServletContext().getAttribute("USERDAO");
        User user = userDao.getUserByMail((String) session.getAttribute("mail"));

    %>
<script>
    let suggestedUserName
    let suggestedUserID
    let suggestedUserAge
    let suggestedUserMail
    let suggestedUserImage
    let userIsBanned
    let activeMail

    var imagesArray
    var chatsArray
    var likersArray
    var imagesOfLikers

    let suggestedUserJob
    let suggestedUserCompany
    let suggestedUserCity
    let suggestedUserBio

    let displayCurrentChat
    // setInterval(getchats,1000)

    let userID = "<%=user.getUser_id()%>"



</script>

    <div   style="display:block; width:100%;">

        <div id="leftDiv" style="width: 30%; height: 100%; overflow-y: scroll;  float: left;">

            <button name="settingsButton" type="submit" value="Logout">Logout</button>
            <br/>
            <br/>

            <button id="banUnban" name="mainPageButton" type="button"onclick="seeUsers()" value="Users">See All Users</button>





            <div class="chatsContainer"> <span  class="chatContainer__Header"></span>

                <div id ="chatsContainerBody" class="chatsContainerBody">

                </div>
                <div id ="likesContainerBody" class="likesContainerBody">

                </div>



            </div>


        </div>
        <script>

            getBannedUsers();
            function seeUsers() {
                var elem = document.getElementById("banUnban");
                if (elem.innerHTML=="See All Users") elem.innerHTML = "See Banned Users";
                else elem.innerHTML = "See All Users";

                if(state===0){
                    state=1;

                    $("#chatsContainerBody").empty();

                    $('.likesContainerBody').css('display', 'block');

                    $('.chatsContainerBody').css('display', 'none');
                    //alert(state);

                    $.ajax({
                        async: false,
                        type: "POST",
                        url: "AllUsersServlet",
                        dataType: "json",

                        success: function (msg) {
                           // alert(msg);
                            likersArray = msg;

                            if (likersArray.length != 0) {
                                $("#likesContainerBody").empty();
                                for (let i = 0; i < likersArray.length; i++) {
                                    let mail = likersArray[i].mail;
                                    getImagesOfLikers(mail);
                                    let img="images/"+imagesOfLikers[0];
                                    let name = likersArray[i].first_name;
                                    let id = likersArray[i].user_id;

                                    $("#likesContainerBody").append("<div  onclick=\"displayCurrentChat('" + id+ "', '" + name + "', '" + mail + "', '" + img + "')\"class=\"currentChatContainer\">\n" +
                                        "            <img width=\"100px\" height=\"100px\" class=\"chatUserIcon\" src=" + img + ">\r\n" +
                                        "            <span>" + name + "</span>\n" +
                                        "        </div>");
                                }
                            }

                        },
                        error: function (msg) {
                            alert("No Users");
                        }
                    });

                }else {
                    state=0;
                    $("#likesContainerBody").empty();
                    getBannedUsers();
                    $('.chatsContainerBody').css('display', 'block');
                    $('.likesContainerBody').css('display', 'none');


                }
            }

            var state=0;
            function getImagesOfLikers(mail){


                $.ajax({
                    async: false,
                    type: "POST",
                    url: "SuggestedUserImagesServlet",
                    dataType: "json",
                    data: {"suggestedUserMail": mail},
                    success: function (msg) {
                        try {
                            let response = JSON.parse(msg).status;
                            if (response === 0) {

                            }
                        } catch (e) {
                            imagesOfLikers = msg



                        }
                    },
                    error: function (msg) {


                    }
                });



            }
            function getBannedUsers(){
                $.ajax({
                    async: false,
                    type: "POST",
                    url: "BannedUsersServlet",
                    dataType: "json",
                    data: {"userID": userID},
                    success: function (msg) {

                        chatsArray = msg;
                        $("#likesContainerBody").empty();
                        $('.chatsContainerBody').css('display', 'block');
                        $('.likesContainerBody').css('display', 'none');

                        if (chatsArray.length != 0) {
                            $("#chatsContainerBody").empty();
                            for(let i=0; i<chatsArray.length; i++)
                            {
                                getImagesOfLikers(chatsArray[i].mail);
                                let img="images/"+imagesOfLikers[0];
                                let name = chatsArray[i].first_name;
                                $("#chatsContainerBody").append( "<div  class=\"currentChatContainer\">\n" +
                                    "            <img width=\"100px\" height=\"100px\" class=\"chatUserIcon\" src=" + img + ">\r\n" +
                                    "            <span>" + name + "</span>\n" +
                                    "        </div>");
                            }

                        }

                    },
                    error: function (msg) {
                        alert("No Banned Users");
                    }
                });
            }

        </script>


        <div id ="rightDiv" style="width: 70%; height: 100%; overflow-y: scroll;  float: left;">

            <div class="openedChatContainer">
                <div class="openedChatHeader"> <span id="currentOpenChatName"></span></div>
                <div id="divToAdd"class="openedChatBody"> </div>
                <button type="button" id="banId" > Ban</button>
                <button type="button" id="unbanId" > Unban</button>
                <div  class="openedChatInputContainer">


                </div>
            </div>

            <script>

                function banUser(mail) {

                    $.ajax({
                        async: false,
                        type: "POST",
                        url: "BanUserServlet",
                        dataType: "json",
                        data: {"userMail": mail},
                        success: function (msg) {
                            const currMsg = JSON.stringify(msg);
                            let response = JSON.parse(currMsg);
                            let state = response.status;
                            if (state == 1) {


                            } else {
                                alert("Unexpected error");
                            }
                        },
                        error: function (msg) {
                            alert("Error");
                        }
                    });
                }



                function unbanUser(mail){

                    $.ajax({
                        async: false,
                        type: "POST",
                        url: "UnbanUserServlet",
                        dataType: "json",
                        data: {"userMail": mail},
                        success: function (msg) {
                            const currMsg = JSON.stringify(msg);
                            let response = JSON.parse(currMsg);
                            let state = response.status;
                            if (state == 1) {


                            } else {
                                alert("Unexpected error");
                            }
                        },
                        error: function (msg) {
                            alert("Error");
                        }
                    });

                }
                let userToShow;


                displayCurrentChat = function (user_id,name,mail,img){
                    activeMail=mail;

                    $('.openedChatBody').empty();
                    $('.openedChatContainer').css('display', 'block');

                    var imageTag = document.createElement("img");
                    imageTag.src =img;
                    imageTag.width = "500";
                    imageTag.height="500";

                    const parentEl = document.getElementById("divToAdd");
                    parentEl.appendChild(imageTag);

                    $('.openedChatBody').append(`<div class="${"name"}">` +name+`</div>`);

                    getUserInfo(mail,img);

                   // $("#banId").removeAttr("disabled");
                    //$("#unbanId").removeAttr("disabled");
                    if(userIsBanned==="1") {
                        $("#unbanId").removeAttr("disabled");
                         $("#banId").attr('disabled','true');

                    }else {

                         $("#unbanId").attr('disabled','true');
                         $("#banId").removeAttr("disabled");
                    }




                }
                $('#banId').click(function (){

                    banUser(activeMail);
                    $("#unbanId").removeAttr("disabled");
                    $("#banId").attr('disabled','true');


                })
                $('#unbanId').click(function (){

                    unbanUser(activeMail);
                    $("#unbanId").attr('disabled','true');
                    $("#banId").removeAttr("disabled");


                })

                function getUserInfo(mail,img){

                    getUser(mail);
                    const textTag = document.createElement("text");
                    textTag.setAttribute("id","info");

                    let age = suggestedUserAge+"\r\n";
                    let job = suggestedUserJob+"\r\n";
                    let company = suggestedUserCompany+"\r\n";
                    let city=suggestedUserCity + "\r\n";
                    let bio =suggestedUserBio + "\r\n";


                    textTag.appendChild(document.createElement("br"));
                    textTag.appendChild(document.createTextNode(age));
                    textTag.appendChild(document.createElement("br"));
                    textTag.appendChild(document.createElement("br"));
                    textTag.appendChild(document.createTextNode(city));
                    textTag.appendChild(document.createElement("br"));
                    textTag.appendChild(document.createTextNode(bio));
                    textTag.appendChild(document.createElement("br"));
                    textTag.appendChild(document.createTextNode(job));
                    textTag.appendChild(document.createElement("br"));
                    textTag.appendChild(document.createTextNode(company));
                    textTag.appendChild(document.createElement("br"));

                    const parentEl = document.getElementById("divToAdd");
                    parentEl.appendChild(textTag);
                }



                function getUser(mail){

                    $.ajax({
                        async: false,
                        type: "POST",
                        url: "UserInfoServlet",
                        data: {"userMail": mail},
                        success: function (msg) {
                            // $("#previousButton").attr('disabled','true');
                            // $("#nextButton").removeAttr("disabled");
                            try {
                                let response = JSON.parse(msg).status;
                                if (response === 0) {
                                }
                            } catch (e) {
                                suggestedUserAge = msg[0];
                                suggestedUserJob =msg[1];
                                suggestedUserCompany = msg[2];
                                suggestedUserCity = msg[3];
                                suggestedUserBio = msg[4];
                                userIsBanned = msg[5];

                            }
                        },
                        error: function (msg) {
                            alert("no info");
                        }
                    });

                }




            </script>

        </div>
    </div>

</form>

</body>
</html>