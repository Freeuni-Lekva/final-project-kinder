<%@ page import="ge.kinder.Models.User" %>
<%@ page import="ge.kinder.DAO.DAOimpl.UserDAOimpl" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Main Page </title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
        #center_image {
            position: relative;
            top: 10%;
            left: 30%;
            width: 40%;
            height: 80%;
        }

        img {
            max-width: 100%;
            max-height: 100%;
        }

        #image {
            position: relative;
            height: 80%;
            width: 100%;
        }

        #bottom_buttons {
            position: absolute;
            height: 8%;
            width: 100%;
            top: 82%;
        }

        #texts_id {
            position: absolute;
            height: 8%;
            top: 72%;
            width: 100%;
        }

        #info_style {
            position: absolute;
            top: 90%;
            height: 10%;
            left: 25%;
            width: 70%;
        }

        #previousButton {
            position: absolute;
            left: 10%;
            width: 20%;
        }

        #nextButton {
            position: absolute;
            left: 70%;
            width: 20%;
        }

        #middleText {
            position: absolute;
            left: 35%;
            width: 30%;
            background-color: white;
        }

        #info {
            position: absolute;
            left: 50%;
            top: 82%;
        }





    </style>

</head>

<body style="background-image: url('https://theme.zdassets.com/theme_assets/302164/8e05540d6f7ea752f80938c848f3ed79b548b959.png')">


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
            const textTag = document.createElement("div");
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
            <button id="likematch" name="mainPageButton" type="button"onclick="seeLikers()" <%if(user.getIs_premium()!=1){%> disabled="disabled" <%}%>value="Likes">See Likes</button>

            <script>
                function seeLikers() {
                    var elem = document.getElementById("likematch");
                    if (elem.innerHTML=="See Likes") elem.innerHTML = "See Matches";
                    else elem.innerHTML = "See Likes";
                    if(state===0){
                        state=1;
                        $("#chatsContainerBody").empty();
                        $('.likesContainerBody').css('display', 'block');
                        $('.chatsContainerBody').css('display', 'none');
                        $.ajax({
                            async: false,
                            type: "POST",
                            url: "LikersServlet",
                            dataType: "json",
                            data: {"userID": userID},
                            success: function (msg) {

                                likersArray = msg;

                                if (likersArray.length != 0) {
                                    $("#likesContainerBody").empty();
                                    for (let i = 0; i < likersArray.length; i++) {

                                        getImagesOfLikers(likersArray[i].mail);
                                        let img="images/"+imagesOfLikers[0];
                                        let name = likersArray[i].first_name;


                                        $("#likesContainerBody").append( " <br/>  <div class=\"currentChatContainer\">\n" +
                                            "            <img width=\"100px\" height=\"100px\" class=\"chatUserIcon\" src=" + img + ">  <br/> " +
                                            "            <span align-items= \"center\" >" + name + "</span>\n" +
                                            "       <br/> </div>");
                                    }
                                }

                            },
                            error: function (msg) {
                                alert("No chats");
                            }
                        });

                    }else {
                        state=0;
                        $("#likesContainerBody").empty();
                        getchats();

                        $('.chatsContainerBody').css('display', 'block');
                        $('.likesContainerBody').css('display', 'none');


                    }
                }

                function getImagesOfLikers(mail){
                    // alert(mail);

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

            </script>

            <style>
                .chatsContainer{
                    width: 98%;
                    /*position: absolute;*/
                    height: calc(100% - 40px);
                    /*// background: #FFFFFF;*/

                    margin-bottom: 8px;
                    top: 0px;
                    left: 2px;
                    display: flex;
                    align-items: center;
                    /*justify-content: center;*/
                    flex-direction: column;

                }
                .currentChatContainer{

                    border: 5px solid #FFFFFF;
                    background: #FFFFFF;

                }
                .openedChatContainer{
                    align-items: center;
                    width: 78%;
                    height: calc(100%);
                    border: 5px solid #FFFFFF;
                    background: #BCC2C1;
                    position: relative;

                }
                .btn-holder {
                    position: absolute;
                    bottom: 5px;
                    right: 40px;
                }
                .returnButton {
                    background-color: transparent;
                    background-repeat: no-repeat;
                    border: none;
                    cursor: pointer;
                    overflow: hidden;
                    outline: none;
                }
                .openedChatHeader{

                    background: #FFFFFF;
                }

                .mine{


                }
                .notmine {

                    position: absolute;

                    right: 10px;
                }



            </style>

            <div class="chatsContainer"> <span  class="chatContainer__Header"></span>

                <div id ="chatsContainerBody" class="chatsContainerBody">

                </div>
                <div id ="likesContainerBody" class="likesContainerBody">

                </div>
                <br/>
                <br/>

                <div class="openedChatContainer">

                    <button class="returnButton" type="button" id="dismissChat" onclick="chatDismiss()" >Return</button>
                    <br/>

                    <div class="openedChatHeader"> <span id="currentOpenChatName"></span>
                        <br/>

                    </div>
                    <div class="openedChatBody">  </div>
                    <div class="openedChatInputContainer">

                        <div  class="btn-holder">
                            <input  class="openedChatInput" placeholder="Send message">
                            <button  type="button" id="sendMessageId" > Send</button>
                        </div>
                    </div>
                </div>

            </div>


        </div>


        <div id ="rightDiv" style="width: 70%; height: 100%; overflow-y: scroll;  float: left;">
            <div id = "center_image">
                <img id="image" src="" alt="photo">

                <div id = "texts_id">
                    <button id = "previousButton" name="mainPageButton" type="button" onclick="getPrevPhoto()" value="prevPhoto">Previous</button>
                    <div id = "middleText">
                        <text id="age" style="text-align: center; text-shadow: 2px 2px 5px hotpink;">hello </text>
                    </div>
                    <button id = "nextButton" name="mainPageButton" type="button" onclick="getNextPhoto()"  value="nextPhoto">Next</button>
                </div>
                <div id ="bottom_buttons">

                    <button id="dislike" name="mainPageButton" type="button" onclick="likeUser(3)" style="width:32.5%" value="dislike">Dislike</button>
                    <button id="superlike" name="mainPageButton" type="button" style="width:32.5%" onclick="likeUser(2) " <%if(user.getIs_premium()!=1){%> disabled="disabled" <%}%> value="superlike">Superlike</button>
                    <button id="like" name="mainPageButton" type="button" style="width:32.5%" onclick="likeUser(1)" value="like">Like</button>

                </div>
                <div id="info_style">
                    <button id= "infoButton_1" name="mainPageButton" type="button" style="width: 35%" onclick="getUserInfo()" value="ShowInfo">Show Info</button>
                    <button id= "infoButton_2" name="mainPageButton" type="button" style="width: 35%"onclick="hideUserInfo()" value="Info">Hide Info</button>
                </div>
            </div>
        </div>

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
            var likersArray
            var imagesOfLikers

            let suggestedUserJob
            let suggestedUserCompany
            let suggestedUserCity
            let suggestedUserBio

            let likeType

            var checkerThreadID = null

            let thread_1

            let displayCurrentChat
            let chatDismiss
            var state=0;


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

                                $("#chatsContainerBody").append( " <br/>  <div onclick=\"displayCurrentChat('" + name + "', '" + chat_id + "')\" class=\"currentChatContainer\">\n" +
                                    "            <img width=\"100px\" height=\"100px\" class=\"chatUserIcon\" src=" + img + ">  <br/> " +
                                    "            <span align-items= \"center\" >" + name + "</span>\n" +
                                    "       <br/> </div>");
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
                            // let currentClass;
                            if (messagesArray[i].user_id !=userID) {

                                const span = document.getElementById("currentOpenChatName");
                                const sender = span.textContent+": ";

                                $('.openedChatBody').append(`<br/> <div class="${"notmine"}">`+sender +messagesArray[i].message_text+`</div>`)
                                // currentClass = "notyou";
                            } else{
                                // else currentClass = "you";

                                let sender = "Me: ";
                                $('.openedChatBody').append(`<br/> <div class="${"mine"}">`+sender +messagesArray[i].message_text+`</div>`)
                            }}
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
                        $('.openedChatBody').append(`<br/> <div class="${"myText"}">` +currentChatMessage.val()+`</div>`);
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
                            $("#age").text(suggestedUserName + ', ' + suggestedUserAge);
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

                for (var i = 0; i < imagesArray.length; i++) {
                    var obj = imagesArray[i];

                    if (obj === curImg) {
                        if (i !== imagesArray.length - 1) {
                            document.getElementById("image").src = 'images/' + imagesArray[i + 1]
                        }
                        if (i == imagesArray.length - 2) document.getElementById("nextButton").disabled = true;
                        if (i == 0) document.getElementById("previousButton").disabled = false;


                    }
                }
            }
        </script>

    </div>
    </div>

</form>

</body>
</html>