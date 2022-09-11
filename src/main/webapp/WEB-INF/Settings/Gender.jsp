<%@ page import="ge.kinder.DAO.DAOimpl.UserDAOimpl" %>
<%@ page import="ge.kinder.Models.User" %>
<%@ page import="java.sql.Date" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.09.2022
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gender Settings</title>
</head>
<body  style="background-image: url('https://theme.zdassets.com/theme_assets/302164/8e05540d6f7ea752f80938c848f3ed79b548b959.png')">
<form action="Settings" method="post">
    <%
        UserDAOimpl userDao = (UserDAOimpl) request.getServletContext().getAttribute("USERDAO");
        User user = userDao.getUserByMail((String) session.getAttribute("mail"));
    %>
    <div   style="display:block; width:100%;">

        <div style="width: 30%; height: 100%; overflow-y: scroll;  float: left;">

            <%
                String mail = (String) session.getAttribute("mail");
                String gender = user.getGender();


            %>
            <button id = "backButton" name="BackFromPref" type="submit" >Back</button>

            <br/>
            <br/>
            <div class="preference_buttons" data-toggle="buttons">

                <br/>
                <br/>

                <label class="btn-pref">
                    <input type="radio" name="gender"
                        <%if (gender.equals("Man")) {%> checked="checked"<% }%> value="Man">Man
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Woman")) {%> checked="checked"<% }%> value="Woman">Woman
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Agender")) {%> checked="checked"<% }%>  value="Agender">Agender
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Androgyn")) {%> checked="checked"<% }%>  value="Androgyn">Androgyn
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Androgynous")) {%> checked="checked"<% }%>  value="Androgynous">Androgynous
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Bigender")) {%> checked="checked"<% }%>  value="Bigender">Bigender
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Female to Male")) {%> checked="checked"<% }%>  value="Female to Male">Female to Male
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Ftm")) {%> checked="checked"<% }%>  value="Ftm">Ftm
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Gender Fluid")) {%> checked="checked"<% }%>  value="Gender Fluid">Gender Fluid
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                <input type="radio" name="gender"  <%if (gender.equals("Gender Nonconforming")) {%> checked="checked"<% }%>  value="Gender Nonconforming">Gender Nonconforming
            </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Gender Questioning"))
                    {%> checked="checked"<% }%>  value="Gender Questioning">Gender Questioning
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Genderqueer"))
                    {%> checked="checked"<% }%>  value="Genderqueer">Genderqueer
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Mtf"))
                    {%> checked="checked"<% }%>  value="Mtf">Mtf
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Neutrois"))
                    {%> checked="checked"<% }%>  value="Neutrois">Neutrois
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Non-Binary"))
                    {%> checked="checked"<% }%>  value="Non-Binary">Non-Binary
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Other"))
                    {%> checked="checked"<% }%>  value="Other">Other
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Pangender"))
                    {%> checked="checked"<% }%>  value="Pangender">Pangender
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Trans"))
                    {%> checked="checked"<% }%>  value="Trans">Trans
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Trans Man"))
                    {%> checked="checked"<% }%>  value="Trans Man">Trans Man
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Trans Person"))
                    {%> checked="checked"<% }%>  value="Trans Person">Trans Person
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Trans Woman"))
                    {%> checked="checked"<% }%>  value="Trans Woman">Trans Woman
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Transfeminine"))
                    {%> checked="checked"<% }%>  value="Transfeminine">Transfeminine
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Transgender"))
                    {%> checked="checked"<% }%>  value="Transgender">Transgender
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Transgender Female"))
                    {%> checked="checked"<% }%>  value="Transgender Female">Transgender Female
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Transgender Male"))
                    {%> checked="checked"<% }%>  value="Transgender Male">Transgender Male
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Transgender Man"))
                    {%> checked="checked"<% }%>  value="Transgender Man">Transgender Man
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Transgender Person"))
                    {%> checked="checked"<% }%>  value="Transgender Person">Transgender Person
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Transgender Woman"))
                    {%> checked="checked"<% }%>  value="Transgender Woman">Transgender Woman
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Transmasculine"))
                    {%> checked="checked"<% }%>  value="Transmasculine">Transmasculine
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="gender"  <%if (gender.equals("Two-Spirit"))
                    {%> checked="checked"<% }%>  value="Two-Spirit">Two-Spirit
                </label>
                <br/>
                <br/>

            </div>
<%--            <div id="results_1" class="btn_1" ></div>--%>

            <br/>
            <br/>
            <button id = "changeButton" name="changeGender" type="submit"  disabled="disabled">Change Gender</button>

            <script>
                let results_1 = document.getElementById("results_1");



                document.querySelector("div.preference_buttons").addEventListener("click", function(evt){
                    // Check if a radio button triggered the event
                    if(evt.target.type === "radio"){
                        // Populate the results area with the value of the clicked element
                        // results_1.textContent = "You will see "+ evt.target.value;
                        document.getElementById("changeButton").disabled = false;



                    }
                });

            </script>

        </div>

        <div style="width:70%; height:100%; ">

<%--            <%--%>
<%--                if (user.getImages().size()>0) {%>--%>
<%--            <img src="images/<%=user.getImages().get(0)%>" alt="photo" width="200px" height="200px">--%>
<%--            <% }%>--%>
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




<%--            <br/>--%>
<%--            <%=user.getFirst_name()   %>--%>

<%--            <%=(int) Math.floor((new Date(System.currentTimeMillis()).getTime()-user.getBirth_date().getTime() ) / 3.15576e+10) %>--%>

<%--            <br/>--%>
<%--            <% if (user.isGenderIsShown()==1) {%> <%=user.getGender()%> <% }%>--%>

        </div>
    </div>
</form>
</body>
</html>

