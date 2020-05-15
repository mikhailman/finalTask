<%--
  Created by IntelliJ IDEA.
  User: Michael
  Date: 07.05.2020
  Time: 2:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="vm" uri="kefirTag" %>

<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>

<fmt:bundle basename="text" prefix="mainPage.">

    <html lang="${language}">

    <head>
        <title><fmt:message key="title.brand"/></title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body style="padding-top: 40px; padding-bottom: 120px; color: #820030" background="images/background.jpg">

    <c:import url="headOfPages.jsp"/>

    <div class="container" style="background: silver">
        <div class="container" style="background: silver">
            <div class="row" style="position:center">
                <div class="col-sm-1">
                </div>

                <div class="col-sm-10">
                    <div id="carouselExampleFade" class="carousel slide carousel-fade" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active" data-interval="4000">
                                <img src="images/sc1.jpg" class="img-thumbnail" alt="Cinque Terre">
                                <div class="carousel-caption d-none d-md-block">
                                    <h2></h2>
                                </div>
                            </div>
                            <div class="carousel-item" data-interval="4000">
                                <img src="images/sc2.jpg" class="img-thumbnail" alt="Cinque Terre">
                                <div class="carousel-caption d-none d-md-block">
                                    <h2></h2>
                                </div>
                            </div>
                            <div class="carousel-item" data-interval="4000">
                                <img src="images/sc3.jpg" class="img-thumbnail" alt="Cinque Terre">
                                <div class="carousel-caption d-none d-md-block">
                                    <h2></h2>
                                </div>
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleFade" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleFade" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
                <div class="col-sm-1">
                </div>
            </div>

            <div class="container" style="background: silver">
                <form action="listOfProducts.html" method="get">
                    <div class="col-sm-4">
                    </div>
                    <div class="col-sm-12">
                        <button type="submit" class="btn btn-warning btn-lg btn-block"><fmt:message
                                key="button.moreSuggestions"/>
                        </button>
                    </div>
                    <div class="col-sm-4">
                    </div>
                </form>
            </div>
        </div>
    </div>
        <%--    <%@ include file="/WEB-INF/jsp/menu.jsp" %>--%>


    <c:import url="endOfPages.jsp"/>


    </body>
    </html>
</fmt:bundle>