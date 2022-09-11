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
                String orientation = user.getOrientation();
                if(orientation==null) orientation = "None";


            %>
            <button id = "backButton" name="BackFromPref" type="submit" >Back</button>

            <br/>
            <br/>
            <div class="preference_buttons" data-toggle="buttons">

                <br/>
                <br/>

                <label class="btn-pref">
                    <input type="radio" name="orientation"  <%if (orientation.equals("None"))
                    {%> checked="checked"<% }%>  value="None">None
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="orientation"  <%if (orientation.equals("Straight"))
                    {%> checked="checked"<% }%>  value="Straight">Straight
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="orientation"  <%if (orientation.equals("Gay"))
                    {%> checked="checked"<% }%>  value="Gay">Gay
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="orientation"  <%if (orientation.equals("Lesbian"))
                    {%> checked="checked"<% }%>  value="Lesbian">Lesbian
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="orientation"  <%if (orientation.equals("Bisexual"))
                    {%> checked="checked"<% }%>  value="Bisexual">Bisexual
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="orientation"  <%if (orientation.equals("Asexual"))
                    {%> checked="checked"<% }%>  value="Asexual">Asexual
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="orientation"  <%if (orientation.equals("Demisexual"))
                    {%> checked="checked"<% }%>  value="Demisexual">Demisexual
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="orientation"  <%if (orientation.equals("Pansexual"))
                    {%> checked="checked"<% }%>  value="Pansexual">Pansexual
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="orientation"  <%if (orientation.equals("Queer"))
                    {%> checked="checked"<% }%>  value="Queer">Queer
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="orientation"  <%if (orientation.equals("Questioning"))
                    {%> checked="checked"<% }%>  value="Questioning">Questioning
                </label>
                <br/>
                <br/>

            </div>
            <%--            <div id="results_1" class="btn_1" ></div>--%>

            <br/>
            <br/>
            <button id = "changeButton" name="changeOrientation" type="submit"  disabled="disabled">Change Orientation</button>

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

