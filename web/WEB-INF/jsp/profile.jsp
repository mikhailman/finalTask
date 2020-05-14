<%--
  Created by IntelliJ IDEA.
  User: Michael
  Date: 02.04.2020
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="vm" uri="kefirTag" %>
<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="text">
    <jsp:useBean id="user" scope="request" type="by.verishko.kefir.entity.User"/>

    <html lang="${language}">

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    </head>
    <body style="padding-top: 40px; padding-bottom: 120px; color: #820030" background="images/background.jpg">
    <c:import url="headOfPages.jsp"/>
    <div class="text-center" style="margin-top:30px;margin-right: 20px">
        <h1 class="display-4">Profile</h1>
        <br>
    </div>
    <div class="row col-md-12">
        <div class="col-md-2"></div>
        <div class="col-md-6">
            <br>
            <h5>First name: ${user.name}</h5>
            <br>
            <h5>Last name: ${user.surname}</h5>
            <br>
            <h5>Nickname: ${user.login}</h5>
            <br>
            <h5>Phone : ${user.phone}</h5>
            <br>
            <h5>Email: ${user.email}</h5>
            <br>
            <h5>Email: ${user.date_registration}</h5>
            <br>
            <h5>Email: ${user.role}</h5>
            <br>
        </div>
        <div class="col-md-2"></div>
        <div class="col-md-3">
            <a class="btn btn-outline-primary" style="margin-left: 19%"
               href="${pageContext.request.contextPath}/editProfile.html">Update profile</a>
        </div>
        <div class="btn-group">
            <a class="btn btn-primary"
               href="${pageContext.request.contextPath}/myProducts.html">My products</a>
            <a class="btn btn-primary"
               href="${pageContext.request.contextPath}/shoppingCart.html">Shopping cart</a>
            <a class="btn btn-primary"
               href="${pageContext.request.contextPath}/addAnnouncement.html">Add announcement</a>
        </div>
    </div>

    </body>
    </html>
</fmt:bundle>