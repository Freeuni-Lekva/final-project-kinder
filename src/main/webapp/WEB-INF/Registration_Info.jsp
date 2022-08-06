<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.08.2022
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Info</title>
</head>

<h1> Create Account  </h1>
</br>
</br>
<form action="Registration_Info" method="post">
    First Name
    <br/>
    <br/>
    <input type = "text" name="FIRST_NAME">
    <br/>
    <br/>
    Birthday
    <br/>
    <br/>
    <input type = "text" name="MONTH">
    <input type = "text" name="DAY">
    <input type = "text" name="YEAR">
    <br/>
    <br/>
    Gender
    <select name="GENDER">
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
    <input type="checkbox" id="SHOW_GENDER" />
    <label>Show my gender on my profile </label> <br>
    <br/>
    <br/>
    Show Me
    <select name="SHOW_ME">
        <option value="MEN">Men</option>
        <option value="WOMEN">Women</option>
        <option value="EVERYONE">Everyone</option>
    </select>

    <br/>
    <br/>
    Email Address
    <input type = "text" name="MAIL">
    <br/>
    <br/>

    Profile Photo
    <br/>
    <br/>

    <input type="file"  name="PHOTO_1">
    <br/>
    <input type="file"  name="PHOTO_2">
    <br/>
    <input type="file"  name="PHOTO_3">
    <br/>
    <input type="file"  name="PHOTO_4">
    <br/>
    <input type="file"  name="PHOTO_5">
    <br/>
    <input type="file"  name="PHOTO_6">
    <br/>


    <input type="submit">Submit

</form>
</body>
</html>
