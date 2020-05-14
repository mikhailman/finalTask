<%--
  Created by IntelliJ IDEA.
  User: Michael
  Date: 07.05.2020
  Time: 2:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="vm" uri="kefirTag" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header id="header" style="height: 50px"/>

<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>

<fmt:bundle basename="text">

    <html lang="${language}">
    <div class="row">
        <nav class="navbar navbar-expand-sm bg-dark justify-content-center navbar-dark fixed-top">
            <lu class="navbar-brand">

                <form action="main.html" method="get">
                    <a href="main.html">
                        <img src="images/logo1.jpg" alt="Logo" style="width:40px; position:absolute; left:150px" ;>
                    </a>
                </form>
                <lo class="nav-item">
                    <div class="mx-auto" style="width: 150px; left:200px">
                        <h4 style="color:#118fff;"><fmt:message key="mainPage.title.brand"/>
                            <small><i><em><fmt:message key="mainPage.title.tagline"/></em></i></small></h4>
                    </div>
                </lo>
                <lo class="nav-item"></lo>
            </lu>
            <div class="dropdown" style="position:absolute; top:15px; left:300px; height: 30px;">
                <button class="btn btn-outline-warning btn-sm dropdown-toggle" type="button" id="dropdownMenu2"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="mainPage.button.menu"/>
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                    <form action="main.html" method="get">
                        <button class="dropdown-item" type="submit"><fmt:message
                                key="mainPage.button.home"/></button>
                    </form>
                    <c:choose>
                        <c:when test="${authorizedUser.role.id == 2}">
                            <form action="addTrip.html" method="post">
                                <button class="dropdown-item" type="submit"><fmt:message
                                        key="mainPage.button.addProduct"/></button>
                            </form>
                        </c:when>
                        <c:when test="${authorizedUser.role.id == 1}">
                            <form action="listOfUsers.html" method="post">
                                <button class="dropdown-item" type="submit"><fmt:message
                                        key="mainPage.button.allUsers"/></button>
                            </form>
                        </c:when>
                    </c:choose>
                </div>
            </div>

            <c:set var="errorPage" value="/kefir/WEB-INF/jsp/error.jsp"/>


            <c:if test="${pageContext.request.requestURI ne errorPage}">
                <form method="post">
                        <%--                    <div class="form-group">--%>
                        <%--                        <select class="form-control" onchange="this.form.submit()" name="local"--%>
                        <%--                                style=" background-color: #353535; color: #118fff; border-color: #118fff; width: 65px; height: 30px; font-size: 10px; position: absolute; top:15px; left: 380px;"--%>
                        <%--                                id="exampleFormControlSelect1">--%>
                        <%--                            <option value="ru-RU" <c:if test="${language eq 'ru-RU' }">selected</c:if>>RU</option>--%>
                        <%--                            <option value="en-EN" <c:if test="${language eq 'en-EN' }">selected</c:if>>EN</option>--%>

                        <%--                        </select>--%>

                    <ul class="navbar-nav mr-sm-4 ">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="lang" data-toggle="dropdown">
                                <fmt:message key="language"/>
                            </a>
                            <ul class="dropdown-menu">
                                <a class="dropdown-item" href="?locale=en"><fmt:message key="english"/> </a>
                                <a class="dropdown-item" href="?locale=ru_RU"><fmt:message key="russian"/></a>
                            </ul>
                        </li>
                    </ul>

                        <%--                    </div>--%>
                </form>
            </c:if>
            <c:set var="role" value="${authorizedUser.role.id!=2 and authorizedUser!=null}"/>
            <c:choose>
                <c:when test="${role}">
                    <div class="btn-group" style="position:absolute; top:15px; right:200px;">
                        <form action="profile.html" method="post">
                            <button type="submit" class="btn btn-outline-warning btn-sm">
                                <fmt:message key="mainPage.button.viewAccount"/> ${authorizedUser.getLogin()}
                            </button>
                        </form>
                        <form action="logout.html" method="post">
                            <button type="submit" class="btn btn-outline-warning btn-sm">
                                <fmt:message key="mainPage.button.logOut"/>
                            </button>
                        </form>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="btn-group" style="position:absolute; top:15px; right:200px;">
                        <form action="login.html" method="post">
                            <button type="submit" class="btn btn-outline-warning btn-sm">
                                <fmt:message key="mainPage.button.login"/>
                            </button>
                        </form>
                        <form action="registrationPage.html" method="post">
                            <button type="submit" class="btn btn-outline-warning btn-sm">
                                <fmt:message key="mainPage.button.registration"/>
                            </button>
                        </form>
                    </div>
                </c:otherwise>
            </c:choose>
        </nav>
    </div>
    </html>
</fmt:bundle>
</header>