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
            <input type = "text" name="FIRST_NAME" placeholder="First Name" >

            <br/>
            <br/>
            Birthday
            <br/>
            <br/>
            <input type = "text" name="MONTH" placeholder="MM">
            <input type = "text" name="DAY" placeholder="DD">
            <input type = "text" name="YEAR" placeholder="YYYY">
            <br/>
            <br/>
            Gender
            <select name="GENDER">
                <option disabled selected value></option>
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
            <select name="SHOW_ME">
                <option disabled selected value></option>
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

       </div>

    <div style="width:50%; float: left; display: inline-block;">Profile Photo
        <br/>
        <br/>


        <div class="fileUpload btn btn-primary">
            <label class="upload">
                <input type='file'  onchange="readURL(this,'#photo_1');" />
                <img id="photo_1" src=""  />
            </label>
        </div>
        <br/>
        <div class="fileUpload btn btn-primary">
            <label class="upload">
                <input type='file'  onchange="readURL(this,'#photo_2');" />
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
    <button id = "submitbutton" type="submit" >Continue</button>
</form>




</body>
</html>
