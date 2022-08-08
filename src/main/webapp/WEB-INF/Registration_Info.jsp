<%@ page import="ge.kinder.Models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Info</title>
    <link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
    <meta charset=utf-8 />
</head>
<body>
<form action="Registration_Info" method="post">
    <h1> Create Account  </h1>
<div style="display:block; width:100%;">
    <div style="width:50%; float: left; display: inline-block;">

        </br>
        </br>

            First Name
            <br/>
            <br/>
            <input type = "text"  id = "name" name="FIRST_NAME" placeholder="First Name"  class="required" >

            <br/>
            <br/>
            Birthday
            <br/>
            <br/>
            <input type = "text" id = "month" name="MONTH" placeholder="MM"  class="required">
            <input type = "text" id =  "day" name="DAY" placeholder="DD">
            <input type = "text" id = "year" name="YEAR" placeholder="YYYY">
            <br/>
            <br/>
            Gender
            <select name="GENDER" id = "gender">
                <option value="nothing"></option>
                <option value="MAN">Man</option>
                <option value="WOMAN">Woman</option>
                <option value="AGENDER">Agender</option>
                <option value="ANDROGYNE">Androgyne</option>
                <option value="ANDROGYNOUS">Androgynous</option>
                <option value="BIGENDER">Bigender</option>
                <option value="FEMALE_TO_MALE">Female to Male</option>
                <option value="FTM">FTM</option>
                <option value="GENDER_FLUID">Gender Fluid</option>
                <option value="GENDER_NONCONFORMING">Gender Nonconforming</option>
                <option value="GENDER_QUESTIONING">Gender Questitioning</option>
                <option value="GENDERQUEER">Genderqueer</option>
                <option value="MTF">MTF</option>
                <option value="NEUTROIS">Neutrois</option>
                <option value="NON-BINARY">Non-Binary</option>
                <option value="OTHER">Other</option>
                <option value="PANGENDER">Pangender</option>
                <option value="TRANS">Trans</option>
                <option value="TRANS_MAN">Trans Man</option>
                <option value="TRANS_PERSON">Trans Person</option>
                <option value="TRANS_WOMAN">Trans Woman</option>
                <option value="TRANSFEMININE">Transfeminine</option>
                <option value="TRANSGENDER">Transgender</option>
                <option value="TRASNGENDER_FEMALE">Transgender Female</option>
                <option value="TRASNGENDER_MALE">Transgender Male</option>
                <option value="TRASNGENDER_MAN">Transgender Man</option>
                <option value="TRASNGENDER_PERSON">Transgender Person</option>
                <option value="TRASNGENDER_WOMAN">Transgender Woman</option>
                <option value="TRASNSMASCULINE">Transmasculine</option>
                <option value="TWO-SPIRIT">Two-Spirit</option>
            </select>
            <br/>
            <br/>
            <input type="checkbox" name="SHOW_GENDER" />
            <label>Show my gender on my profile </label> <br>
            <br/>
            <br/>
            Show Me
            <select name="SHOW_ME" id = "show">
                <option value=""></option>
                <option value="MEN">Men</option>
                <option value="WOMEN">Women</option>
                <option value="EVERYONE">Everyone</option>
            </select>

            <br/>
            <br/>
        <%

            String mail = (String) session.getAttribute("mail");
            System.out.println(mail);

        %>


            Email Address
            <input type = "text" name="MAIL" value=<%=mail%> readonly="readonly">
            <br/>
            <br/>

        Passions
        <select name="PASSIONS">
            <option disabled selected value></option>
            <option value="STRAIGHT">Straight</option>
            <option value="GAY">Gay</option>
            <option value="LESBIAN">Lesbian</option>


        </select>
        <br/>
        <br/>

        Sexual Orientation
        <select name="ORIENTATION">
            <option disabled selected value></option>
            <option value="YOGA">Yoga</option>
            <option value="MEDITATION">Meditation</option>
            <option value="MANGA">Manga</option>
            <option value="WALKING">Walking</option>

        </select>
        <br/>

       </div>

    <div style="width:50%; float: left; display: inline-block;">Profile Photo
        <br/>
        <br/>


        <div class="fileUpload btn btn-primary">
            <label class="upload">
                <input type='file' name = "PHOTO_1"  onchange="readURL(this,'#photo_1');" />
                <img id="photo_1" src=""  />
            </label>
        </div>
        <br/>
        <div class="fileUpload btn btn-primary">
            <label class="upload">
                <input type='file' name = "PHOTO_2" onchange="readURL(this,'#photo_2');" />
                <img id="photo_2" src=""  />
            </label>
        </div>
        <br/>
        <div class="fileUpload btn btn-primary">
            <label class="upload">
                <input type='file'  onchange="readURL(this,'#photo_3');" />
                <img id="photo_3" src=""  />
            </label>
        </div>
        <br/>
        <div class="fileUpload btn btn-primary">
            <label class="upload">
                <input type='file'  onchange="readURL(this,'#photo_4');" />
                <img id="photo_4" src=""  />
            </label>
        </div>
        <br/>
        <div class="fileUpload btn btn-primary">
            <label class="upload">
                <input type='file'  onchange="readURL(this,'#photo_5');" />
                <img id="photo_5" src=""  />
            </label>
        </div>
        <br/>
        <div class="fileUpload btn btn-primary">
            <label class="upload">
                <input type='file'  onchange="readURL(this,'#photo_6');" />
                <img id="photo_6" src=""  />
            </label>
        </div>
        <br/>


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




    </div>
</div>



    <button id = "submitbutton" type="submit" disabled="disabled">Continue</button>


</form>




</body>

<script>
    let inputs = document.querySelectorAll('input');
    let buttonSend = document.getElementById('submitbutton');
    let selects = document.querySelectorAll('select');




    let inputValidator = {
        "FIRST_NAME": false,
        "MONTH": false,
        "DAY": false,
        "YEAR": false,
        "GENDER": false,
        "SHOW_GENDER": false,
         "SHOW_ME": false
        // "PHOTO_1": false,
        // "PHOTO_2": false,
    }


        selects.forEach((select) => {
            select.addEventListener('input', () => {
                let name = event.target.getAttribute('name');


                if (event.target.value === "nothing") {
                    inputValidator[name] = false;
                } else {
                    inputValidator[name] = true;
                };
                let allTrue = Object.keys(inputValidator).every((item) => {
                    return inputValidator[item] === true
                });

                if (allTrue) {
                    buttonSend.disabled = false;
                } else {
                    buttonSend.disabled = true;
                }


            })
        })



    inputs.forEach((input) => {
        input.addEventListener('input', () => {
            let name = event.target.getAttribute('name');

            if (event.target.value.length > 0) {
                inputValidator[name] = true;
            } else {
                inputValidator[name] = false;
            };


            let allTrue = Object.keys(inputValidator).every((item) => {
                return inputValidator[item] === true
            });

            if (allTrue) {
                buttonSend.disabled = false;
            } else {
                buttonSend.disabled = true;
            }
        })
    })
</script>
</html>
