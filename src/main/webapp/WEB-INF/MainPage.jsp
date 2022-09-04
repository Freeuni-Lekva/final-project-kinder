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
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.08.2022
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Build a Full Featured Tinder Like Carousel in Vanilla JavaScript</title>
    <style>
        html,
        body {
            width: 70%;
            height: 100%;
            margin: 0;
            padding: 0;
        }

        #board {
            width: 70%;
            height: 100%;
            position: relative;
            overflow: hidden;
            background-color: rgb(245, 247, 250);
        }

        .card {
            width: 320px;
            height: 320px;
            position: absolute;
            top: 50%;
            left: 50%;
            border-radius: 1%;
            box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.1);
            background-color: white;
            transform: translateX(-50%) translateY(-50%) scale(0.95);
        }
    </style>
</head>

<body>
<form action="ProfilePage" method="post">

    <div   style="display:block; width:100%;">

        <div style="width: 30%; height: 100%; overflow-y: scroll;  float: left;">

            <%
                UserDAOimpl userDao = (UserDAOimpl) request.getServletContext().getAttribute("USERDAO");
                User user = userDao.getUserByMail((String) session.getAttribute("mail"));
            %>

            <button name="mainPageButton" type="submit" value="toSettings">Settings</button>
            <button name="mainPageButton" type="submit" value="matches">Matches</button>
            <button name="mainPageButton" type="submit" value="messages">Messages</button>


        </div>


            <button name="mainPageButton" type="submit" value="nextPhoto">Next</button>
            <button name="mainPageButton" type="submit" value="prevPhoto">Previous</button>
            <button name="mainPageButton" type="submit" value="Info">Info</button>

            <button name="mainPageButton" type="submit" value="dislike">Like</button>
            <button name="mainPageButton" type="submit" value="superlike">Superlike</button>
            <button name="mainPageButton" type="submit" value="like">Dislike</button>

        <div id="board"></div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/hammer.js/2.0.8/hammer.min.js"></script>
        <script>
            /* LikeCarousel (c) 2019 Simone P.M. github.com/simonepm - Licensed MIT */

            class Carousel {

                constructor(element) {

                    this.board = element

                    // add first two cards programmatically
                    this.push()
                    <% System.out.println("FIRST PUSH"); %>
                    this.push()
                    <% System.out.println("SECOND PUSH"); %>
                    this.push()
                    <% System.out.println("THIRD PUSH"); %>
                    // handle gestures
                    this.handle()

                }

                handle() {

                    // list all cards
                    this.cards = this.board.querySelectorAll('.card')

                    // get top card
                    this.topCard = this.cards[this.cards.length - 1]

                    // get next card
                    this.nextCard = this.cards[this.cards.length - 2]

                    // if at least one card is present
                    if (this.cards.length > 0) {

                        // set default top card position and scale
                        this.topCard.style.transform =
                            'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(0deg) scale(1)'

                        // destroy previous Hammer instance, if present
                        if (this.hammer) this.hammer.destroy()

                        // listen for tap and pan gestures on top card
                        this.hammer = new Hammer(this.topCard)
                        this.hammer.add(new Hammer.Tap())
                        this.hammer.add(new Hammer.Pan({
                            position: Hammer.position_ALL,
                            threshold: 0
                        }))

                        // pass events data to custom callbacks
                        this.hammer.on('tap', (e) => {
                            this.onTap(e)
                        })
                        this.hammer.on('pan', (e) => {
                            this.onPan(e)
                        })

                    }

                }

                onTap(e) {

                    // get finger position on top card
                    let propX = (e.center.x - e.target.getBoundingClientRect().left) / e.target.clientWidth

                    // get rotation degrees around Y axis (+/- 15) based on finger position
                    let rotateY = 15 * (propX < 0.05 ? -1 : 1)

                    // enable transform transition
                    this.topCard.style.transition = 'transform 100ms ease-out'

                    // apply rotation around Y axis
                    this.topCard.style.transform =
                        'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(' + rotateY + 'deg) scale(1)'

                    // wait for transition end
                    setTimeout(() => {
                        // reset transform properties
                        this.topCard.style.transform =
                            'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(0deg) scale(1)'
                    }, 100)

                }

                onPan(e) {

                    if (!this.isPanning) {

                        this.isPanning = true

                        // remove transition properties
                        this.topCard.style.transition = null
                        if (this.nextCard) this.nextCard.style.transition = null

                        // get top card coordinates in pixels
                        let style = window.getComputedStyle(this.topCard)
                        let mx = style.transform.match(/^matrix\((.+)\)$/)
                        this.startPosX = mx ? parseFloat(mx[1].split(', ')[4]) : 0
                        this.startPosY = mx ? parseFloat(mx[1].split(', ')[5]) : 0

                        // get top card bounds
                        let bounds = this.topCard.getBoundingClientRect()

                        // get finger position on top card, top (1) or bottom (-1)
                        this.isDraggingFrom =
                            (e.center.y - bounds.top) > this.topCard.clientHeight / 2 ? -1 : 1

                    }

                    // get new coordinates
                    let posX = e.deltaX + this.startPosX
                    let posY = e.deltaY + this.startPosY

                    // get ratio between swiped pixels and the axes
                    let propX = e.deltaX / this.board.clientWidth
                    let propY = e.deltaY / this.board.clientHeight

                    // get swipe direction, left (-1) or right (1)
                    let dirX = e.deltaX < 0 ? -1 : 1

                    // get degrees of rotation, between 0 and +/- 45
                    let deg = this.isDraggingFrom * dirX * Math.abs(propX) * 45

                    // get scale ratio, between .95 and 1
                    let scale = (95 + (5 * Math.abs(propX))) / 100

                    // move and rotate top card
                    this.topCard.style.transform =
                        'translateX(' + posX + 'px) translateY(' + posY + 'px) rotate(' + deg + 'deg) rotateY(0deg) scale(1)'

                    // scale up next card
                    if (this.nextCard) this.nextCard.style.transform =
                        'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(0deg) scale(' + scale + ')'

                    if (e.isFinal) {

                        this.isPanning = false

                        let successful = false

                        // set back transition properties
                        this.topCard.style.transition = 'transform 200ms ease-out'
                        if (this.nextCard) this.nextCard.style.transition = 'transform 100ms linear'

                        // check threshold and movement direction
                        if (propX > 0.25 && e.direction == Hammer.DIRECTION_RIGHT) {

                            successful = true
                            // get right border position
                            posX = this.board.clientWidth

                        } else if (propX < -0.25 && e.direction == Hammer.DIRECTION_LEFT) {

                            successful = true
                            // get left border position
                            posX = -(this.board.clientWidth + this.topCard.clientWidth)

                        } else if (propY < -0.25 && e.direction == Hammer.DIRECTION_UP) {

                            successful = true
                            // get top border position
                            posY = -(this.board.clientHeight + this.topCard.clientHeight)

                        }

                        if (successful) {

                            // throw card in the chosen direction
                            this.topCard.style.transform =
                                'translateX(' + posX + 'px) translateY(' + posY + 'px) rotate(' + deg + 'deg)'

                            // wait transition end
                            setTimeout(() => {
                                // remove swiped card
                                this.board.removeChild(this.topCard)
                                // add new card
                                <% System.out.println("FROM ONPAN");%>
                                this.push()
                                <% System.out.println("TO ONPAN"); %>

                                // handle gestures on new top card
                                this.handle()
                            }, 200)

                        } else {

                            // reset cards position and size
                            this.topCard.style.transform =
                                'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(0deg) scale(1)'
                            if (this.nextCard) this.nextCard.style.transform =
                                'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(0deg) scale(0.95)'

                        }

                    }

                }

                push() {
                    <% System.out.println("PUSH"); %>
                    <% SuggestionService ss = (SuggestionService) request.getSession().getServletContext().getAttribute("SUGGESTION_SERVICE");
                UserDTO suggestion = ss.getSuggestion(user);
                    System.out.println("SUGGESTED USER --> " + suggestion);
                    String img = "";
                    if (suggestion.getImages().size()>0) {
                        img = suggestion.getImages().get(0);
                      System.out.println("IMAGE --> "+ img);} %>
                    <%--                        <img src="images/<%=userDTO.getImages().get(0)%>" alt="photo" width="200px" height="200px">--%>
                    <%--                        <%=userDTO.getFirst_name()   %>--%>
                    <%--                        <%=userDTO.getCity()   %>--%>
                    <%--                        <%=(int) Math.floor((new Date(System.currentTimeMillis()).getTime()-userDTO.getBirth_date().getTime() ) / 3.15576e+10) %>--%>

<%--                    <% }%>--%>

                    let card = document.createElement('div')

                    card.classList.add('card')

                    card.style.backgroundImage =
<%--                    <%! String img() {--%>
<%--                        SuggestionService ss = (SuggestionService) request.getSession().getServletContext().getAttribute("SUGGESTION_SERVICE");--%>
<%--                UserDTO suggestion = ss.getSuggestion(user);--%>
<%--                    System.out.println("SUGGESTED USER --> " + suggestion);--%>
<%--                    String img = "";--%>
<%--                    if (suggestion.getImages().size()>0) {--%>
<%--                        img = suggestion.getImages().get(0);}--%>
<%--                    return img;--%>
<%--                      System.out.println("IMAGE --> "+ img);}--%>
<%--                       %>--%>

                    <% System.out.println("URL"); %>

                    this.board.insertBefore(card, this.board.firstChild)

                }

            }

            let board = document.querySelector('#board')

            let carousel = new Carousel(board)
        </script>

    </div>
    </div>

</form>

</body>
</html>

