<%@ page import="ge.kinder.Models.User" %>
<%@ page import="ge.kinder.DAO.DAOimpl.UserDAOimpl" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Main Page </title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>

<body>


<form action="ProfilePage" method="post">

    <%
        UserDAOimpl userDao = (UserDAOimpl) request.getServletContext().getAttribute("USERDAO");
        User user = userDao.getUserByMail((String) session.getAttribute("mail"));

    %>
    <script>

        function getPrevPhoto() {

            var curImg = document.getElementById("image").src.substring(36);


            for (var i=0; i<imagesArray.length; i++){
                var obj = imagesArray[i];

                if (obj === curImg){
                    if(i !== 0)document.getElementById("image").src = 'images/' + imagesArray[i-1]
                    if(i==imagesArray.length-1) document.getElementById("nextButton").disabled = false;
                    if(i==1) document.getElementById("previousButton").disabled = true;
                }
            }
        }

    </script>


    <script>
        function getUserInfo(){

            document.getElementById("infoButton_1").disabled = true;
            document.getElementById("infoButton_2").disabled = false;
            const textTag = document.createElement("text");
            textTag.setAttribute("id","info");




            let job = suggestedUserJob+"\r\n";
            let company = suggestedUserCompany+"\r\n";
            let city=suggestedUserCity + "\r\n";
            let bio =suggestedUserBio + "\r\n";





            textTag.appendChild(document.createElement("br"));
            textTag.appendChild(document.createTextNode(city));
            textTag.appendChild(document.createElement("br"));
            textTag.appendChild(document.createTextNode(bio));
            textTag.appendChild(document.createElement("br"));
            textTag.appendChild(document.createTextNode(job));
            textTag.appendChild(document.createElement("br"));
            textTag.appendChild(document.createTextNode(company));
            textTag.appendChild(document.createElement("br"));

            for (var i=0; i<hobbiesArray.length; i++){
                let hobby = hobbiesArray[i] + "   ";
                textTag.appendChild(document.createTextNode(hobby));
            }

            const parentEl = document.getElementById("rightDiv");
            parentEl.appendChild(textTag);
        }
    </script>

    <script>
        function hideUserInfo(){
            document.getElementById("infoButton_1").disabled = false;
            document.getElementById("infoButton_2").disabled = true;

            const textTag = document.getElementById("info");
            const parentEl = document.getElementById("rightDiv");
            parentEl.removeChild(textTag);
        }
    </script>
    <div   style="display:block; width:100%;">

        <div id="leftDiv" style="width: 30%; height: 100%; overflow-y: scroll;  float: left;">


            <button name="mainPageButton" type="submit" value="toSettings">Settings</button>


            <div class="chatsContainer"> <span  class="chatContainer__Header">Your Matches</span>

                <div id ="chatsContainerBody" class="chatsContainerBody">

                </div>

                <div class="openedChatContainer">
                    <button type="button" id="dismissChat" onclick="chatDismiss()" >Return</button>

                    <div class="openedChatHeader"> <span id="currentOpenChatName"></span>

                    </div>
                    <div class="openedChatBody">  </div>
                    <div class="openedChatInputContainer"> <input  class="openedChatInput" placeholder="Send message">
                        <button type="button" id="sendMessageId" > Send</button>

                    </div>
                </div>

            </div>


        </div>


        <div id ="rightDiv" style="width: 70%; height: 100%; overflow-y: scroll;  float: left;">



            <button id = "nextButton" name="mainPageButton" type="button" onclick="getNextPhoto()"  value="nextPhoto">Next</button>
            <button id = "previousButton" name="mainPageButton" type="button" onclick="getPrevPhoto()"  value="prevPhoto">Previous</button>
            <br/>
            <br/>
            <button id= "infoButton_1" name="mainPageButton" type="button" onclick="getUserInfo()" value="ShowInfo">Show Info</button>
            <button id= "infoButton_2" name="mainPageButton" type="button" onclick="hideUserInfo()" value="Info">Hide Info</button>
            <br/>
            <br/>
            <button id="like" name="mainPageButton" type="button" onclick="likeUser(1)" value="like">Like</button>

            <button id="superlike" name="mainPageButton" type="button" onclick="likeUser(2)" <%if(user.getIs_premium()!=1){%> disabled="disabled" <%}%> value="superlike">Superlike</button>
            <button id="dislike" name="mainPageButton" type="button" onclick="likeUser(3)" value="dislike">Dislike</button>
            <br/>
            <br/>
            <br/>




            <img id="image" src="" alt="photo" width="200px" height="200px">
            <br/>
            <br/>
            <text id="name">hello </text>
            <br/>
            <br/>
            <text id="age">hello </text>




            <script>

                let suggestedUserName
                let suggestedUserID
                let suggestedUserAge
                let suggestedUserMail
                let suggestedUserImage

                var imagesArray
                var hobbiesArray
                var chatsArray
                var messagesArray

                let suggestedUserJob
                let suggestedUserCompany
                let suggestedUserCity
                let suggestedUserBio

                let likeType

                var checkerThreadID = null

                let thread_1

                let displayCurrentChat
                let chatDismiss


                $('.openedChatContainer').css('display', 'none');
                let currentChatMessage = $('.openedChatInput');

                setInterval(getchats,1000)

                let userID = "<%=user.getUser_id()%>"

                getUser();
                getImages();
                getHobbies();
                getchats();


                function likeUser(status){
                    likeType = status
                    $.ajax({
                        async: false,
                        type: "POST",
                        url: "LikesServlet",
                        dataType: "json",
                        data: {"user": userID, "suggestedUser": suggestedUserID,"type": likeType},
                        success: function (msg) {
                            const currMsg = JSON.stringify(msg);
                            let response = JSON.parse(currMsg);
                            let state = response.status;
                            if (state == 2) {
                                getUser();
                                getImages();
                                getHobbies();
                            }else if(state==1) {
                                getchats();
                                getUser();
                                getImages();
                                getHobbies();

                            }else {
                                alert("Unexpected error");
                            }
                        },
                        error: function (msg) {
                            alert("Error");
                        }
                    });

                }

                function getchats(){
                    $.ajax({
                        async: false,
                        type: "POST",
                        url: "ChatsServlet",
                        dataType: "json",
                        data: {"userID": userID},
                        success: function (msg) {

                            chatsArray = msg;

                            if (chatsArray.length != 0) {
                                $("#chatsContainerBody").empty();
                                for(let i=0; i<chatsArray.length; i++)
                                {
                                    let img = chatsArray[i].img;
                                    let name = chatsArray[i].name;
                                    let chat_id = chatsArray[i].chat_id;

                                    $("#chatsContainerBody").append( "<div onclick=\"displayCurrentChat('" + name + "', '" + chat_id + "')\" class=\"currentChatContainer\">\n" +
                                        "            <img width=\"100px\" height=\"100px\" class=\"chatUserIcon\" src=" + img + ">\r\n" +
                                        "            <span>" + name + "</span>\n" +
                                        "        </div>");
                                }
                            }

                        },
                        error: function (msg) {
                            alert("No chats");
                        }
                    });
                }
                thread_1 = function(chatRoom){

                    let oldMessages = 0;
                    getMessages(chatRoom);
                    if(messagesArray != null){
                        // alert("mesijebi araa charieli")
                        if(messagesArray.length > oldMessages) {
                            //   alert("nolze metia")
                            oldMessages = messagesArray.length;
                            $('.openedChatBody').empty();
                            for (let i = 0; i < messagesArray.length; i++) {
                                let currentClass;
                                if (messagesArray[i].user_id !=userID) currentClass = "yourLoversText";
                                else currentClass = "myText";

                                $('.openedChatBody').append(`<div class="${currentClass}">`+messagesArray[i].message_text+`</div>`)
                            }
                        }
                    }
                };

                function getMessages(chat_room_id){
                    $.ajax({
                        async: false,
                        type: "POST",
                        url: "MessagesServlet",
                        dataType: "json",
                        data: {"chatID": chat_room_id },
                        success: function (msg) {
                            messagesArray = msg;


                        },
                        error: function (msg) {
                            alert("No messages");
                        }
                    });
                }


                chatDismiss = function(){
                    clearInterval(checkerThreadID);
                    location.reload();
                    //
                    // $('.openedChatContainer').css('display', 'none');
                    // $('.chatsContainerBody').css('display', 'block');
                    //
                    // $('.openedChatBody').empty();

                }

                displayCurrentChat = function (currentName, chatRoom){
                    checkerThreadID = setInterval(function() {thread_1(chatRoom)}, 10);
                    $('#currentOpenChatName').text(currentName);
                    $('.openedChatContainer').css('display', 'block');
                    $('.chatsContainerBody').css('display', 'none');
                    //alert("chatroom 1 "+chatRoom);

                    $('#sendMessageId').click(function (){

                        if(currentChatMessage.val() !== ""){
                          //  alert("chatroom 3 "+chatRoom);
                            sendMessage(chatRoom, currentChatMessage.val());
                            //alert("chatroom 31 "+chatRoom);
                            $('.openedChatBody').append(`<div class="${"myText"}">` +currentChatMessage.val()+`</div>`);
                            currentChatMessage.val("") ;
                        }
                    })

                }


                function sendMessage(chatRoom, msg){
                    //alert("chatroom 32 "+chatRoom);
                    let userID = "<%=user.getUser_id()%>"
                    $.ajax({
                        async: false,
                        type: "POST",
                        url: "MessageServlet",
                        dataType: "json",
                        data: {"chatID": chatRoom, "msg": msg, "userID": userID},
                        success: function (msg) {
                            let response = msg;

                        },
                        error: function (msg) {
                            alert("Couldn't send message");
                            console.log(msg);
                        }
                    });
                }


                function getImages() {
                    if (suggestedUserID != null) {
                        $.ajax({
                            async: false,
                            type: "POST",
                            url: "SuggestedUserImagesServlet",
                            dataType: "json",
                            data: {"suggestedUserMail": suggestedUserMail},
                            success: function (msg) {
                                try {
                                    let response = JSON.parse(msg).status;
                                    if (response === 0) {
                                        suggestedUserImage = null;
                                    }
                                } catch (e) {
                                    imagesArray = msg
                                    suggestedUserImage = msg[0];
                                    $("#image").attr("src", "images/"+suggestedUserImage);


                                }
                            },
                            error: function (msg) {
                                suggestedUserImage = null;

                            }
                        });
                    }



                }


                function getHobbies(){
                    if (suggestedUserID != null) {
                        $.ajax({
                            async: false,
                            type: "POST",
                            url: "SuggestedUserHobbiesServlet",
                            dataType: "json",
                            data: {"suggestedUserMail": suggestedUserMail},
                            success: function (msg) {
                                try {
                                    let response = JSON.parse(msg).status;
                                    if (response === 0) {

                                    }
                                } catch (e) {
                                    hobbiesArray = msg


                                }
                            },
                            error: function (msg) {

                            }
                        });
                    }

                }

                function getUser(){
                    let userMail = "<%=user.getMail()%>"
                    $.ajax({
                        async: false,
                        type: "POST",
                        url: "SuggestedUserServlet",
                        data: {"userMail": userMail},
                        success: function (msg) {
                            $("#previousButton").attr('disabled','true');
                            $("#nextButton").removeAttr("disabled");
                            try {
                                let response = JSON.parse(msg).status;
                                if (response === 0) {
                                    suggestedUserID = null;
                                    $("#name").text("No more suggestions");
                                    $("#age").css('display','none');
                                    $("#image").attr("src", "images/blankphoto.jpg");
                                    document.getElementById("infoButton_1").disabled = true;
                                    document.getElementById("infoButton_2").disabled = true;
                                    document.getElementById("previousButton").disabled = true;
                                    document.getElementById("nextButton").disabled = true;
                                    document.getElementById("like").disabled = true;
                                    document.getElementById("superlike").disabled = true;
                                    document.getElementById("dislike").disabled = true;

                                }
                            } catch (e) {

                                suggestedUserID = msg[0];
                                suggestedUserName = msg[1];
                                $("#name").text(suggestedUserName);
                                $("#age").css('display','block');
                                suggestedUserAge = msg[2];
                                $("#age").text(suggestedUserAge);
                                suggestedUserMail = msg[3];
                                suggestedUserJob =msg[4];
                                suggestedUserCompany = msg[5];
                                suggestedUserCity = msg[6];
                                suggestedUserBio = msg[7];
                                document.getElementById("infoButton_1").disabled = false;
                                document.getElementById("nextButton").disabled = false;
                                document.getElementById("like").disabled = false;
                                document.getElementById("dislike").disabled = false;






                            }
                        },
                        error: function (msg) {
                            alert("no more suggestions");
                        }
                    });



                }
            </script>

            <script>
                function getNextPhoto() {

                    var curImg = document.getElementById("image").src.substring(36);

                    for (var i=0; i<imagesArray.length; i++){
                        var obj = imagesArray[i];

                        if (obj === curImg){
                            if(i !== imagesArray.length-1){
                                document.getElementById("image").src = 'images/' + imagesArray[i+1]
                            }
                            if(i==imagesArray.length-2) document.getElementById("nextButton").disabled = true;
                            if(i==0) document.getElementById("previousButton").disabled = false;


                        }
                    }
                }
            </script>








        </div>
    </div>

</form>

</body>
</html>



