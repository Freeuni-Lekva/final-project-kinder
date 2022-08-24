<%@ page import="ge.kinder.Models.User" %>
<%@ page import="java.sql.Date" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.08.2022
  Time: 0:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Looking for</title>
</head>
<body>
<form action="Settings" method="post">
    <%
        User user = (User) request.getSession().getAttribute("user");
        String image = user.getImages().get(0);
        System.out.println(image);
        String pref = user.getGenderPref();
        System.out.println(pref);
        int showGender = user.isGenderIsShown();





    %>
    <div   style="display:block; width:100%;">

        <div style="width: 30%; height: 100%; overflow-y: scroll;  float: left;">

            <%
                String mail = (String) session.getAttribute("mail");


            %>
            <br/>
            <br/>
            <div class="preference_buttons" data-toggle="buttons">

                <br/>
                <br/>

                <label class="btn-pref">
                    <input type="radio" name="preference"
                    <%if (pref.equals("Men")) {%> checked="checked"<% }%> value="Men">Men
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="preference"  <%if (pref.equals("Women")) {%> checked="checked"<% }%> value="Women">Women
                </label>
                <br/>
                <br/>
                <label class="btn-pref">
                    <input type="radio" name="preference"  <%if (pref.equals("Everyone")) {%> checked="checked"<% }%>  value="Everyone">Everyone
                </label>
                <br/>
                <br/>

            </div>
            <div id="results_1" class="btn_1" ></div>

            <br/>
            <br/>
            <button id = "changeButton" name="changePref" type="submit"  disabled="disabled">Change Preference</button>

            <script>
                let results_1 = document.getElementById("results_1");



                document.querySelector("div.preference_buttons").addEventListener("click", function(evt){
                    // Check if a radio button triggered the event
                    if(evt.target.type === "radio"){
                        // Populate the results area with the value of the clicked element
                        results_1.textContent = "You will see "+ evt.target.value;
                        document.getElementById("changeButton").disabled = false;



                    }
                });

            </script>

        </div>

        <div style="width:70%; height:100%; ">

            <img src="images/<%=user.getImages().get(0)%>" alt="photo" width="400px" height="400px">


            <br/>
            <%=user.getFirst_name()   %>

            <%=(int) Math.floor((new Date(System.currentTimeMillis()).getTime()-user.getBirth_date().getTime() ) / 3.15576e+10) %>

            <br/>
            <% if (showGender==1) {%> <%=user.getGender()%> <% }%>

        </div>
    </div>
</form>
</body>
</html>
