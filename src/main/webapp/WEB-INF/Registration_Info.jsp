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
<body  style="background-image: url('https://theme.zdassets.com/theme_assets/302164/8e05540d6f7ea752f80938c848f3ed79b548b959.png')">

<form action="Profile" method="post">
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

            City
            <br/>
            <br/>
            <input type = "text"  id = "city" name="CITY" placeholder="City"  class="required" >

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
                <option value="Man">Man</option>
                <option value="Woman">Woman</option>
                <option value="Agender">Agender</option>
                <option value="Androgyne">Androgyne</option>
                <option value="Androgynous">Androgynous</option>
                <option value="Bigender">Bigender</option>
                <option value="Female to Male">Female to Male</option>
                <option value="Ftm">FTM</option>
                <option value="Gender Fluid">Gender Fluid</option>
                <option value="Gender Nonconforming">Gender Nonconforming</option>
                <option value="Gender Questioning">Gender Questitioning</option>
                <option value="Genderqueer">Genderqueer</option>
                <option value="Mtf">MTF</option>
                <option value="Neutrois">Neutrois</option>
                <option value="Non-Binary">Non-Binary</option>
                <option value="Other">Other</option>
                <option value="Pangender">Pangender</option>
                <option value="Trans">Trans</option>
                <option value="Trans Man">Trans Man</option>
                <option value="Trans Person">Trans Person</option>
                <option value="Trans Woman">Trans Woman</option>
                <option value="Transfeminine">Transfeminine</option>
                <option value="Transgender">Transgender</option>
                <option value="Transgender Female">Transgender Female</option>
                <option value="Transgender Male">Transgender Male</option>
                <option value="Transgender Man">Transgender Man</option>
                <option value="Transgender Person">Transgender Person</option>
                <option value="Transgender Woman">Transgender Woman</option>
                <option value="Transmasculine">Transmasculine</option>
                <option value="Two-Spirit">Two-Spirit</option>
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
                <option value="Man">Men</option>
                <option value="Woman">Women</option>
                <option value="Everyone">Everyone</option>
            </select>

            <br/>
            <br/>
        <%

            String mail = (String) session.getAttribute("mail");


        %>


            Email Address
            <input type = "text" name="MAIL" value=<%=mail%> readonly="readonly">
            <br/>
            <br/>

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
                        <input type="checkbox" id="first" name="SPORT" />
                        Sport
                    </label>

                    <label for="second">
                        <input type="checkbox" id="second" name="INSTAGRAM" />
                        Instagram
                    </label>
                    <label for="third">
                        <input type="checkbox" id="third"name="PHOTOS" />
                        Photos
                    </label>
                    <label for="fourth">
                        <input type="checkbox" id="fourth" name="CARS"/>
                      Cars
                    </label>
                    <label for="fifth">
                        <input type="checkbox" id="fifth"name="FOOTBALL" />
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

        Sexual Orientation
        <select name="ORIENTATION">
            <option disabled selected value></option>
            <option value="Straight">Straight</option>
            <option value="Gay">Gay</option>
            <option value="Lesbian">Lesbian</option>
            <option value="Bisexual">Bisexual</option>
            <option value="Asexual">Asexual</option>
            <option value="Demisexual">Demisexual</option>
            <option value="Pansexual">Pansexual</option>
            <option value="Queer">Queer</option>
            <option value="Questioning">Questioning</option>

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
         "SHOW_ME": false,
        "PHOTO_1":false,
        "PHOTO_2":false

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
