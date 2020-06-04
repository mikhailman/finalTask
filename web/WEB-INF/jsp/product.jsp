<%--
  Created by IntelliJ IDEA.
  User: Michael
  Date: 14.05.2020
  Time: 1:11
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

<fmt:bundle basename="text" prefix="">

    <html lang="${language}">
    <jsp:useBean id="product" scope="request" type="by.verishko.kefir.entity.Product"/>

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
        <%--            <%@ include file="headOfPages.jsp" %>--%>
    <div class="text-center" style="margin-top:30px;margin-right: 20px">
        <h1 class="display-3">${product.name}</h1>
        <span style="color: #b30300" id="errorValue"></span>
    </div>


    <div class="container">
        <div class="row col-md-12">
            <div class="col-md-1"></div>
            <div class="col-md-6">
                <br>
                <div class="text-right"><fmt:formatDate value="${product.date_creation}"
                                                        type="date" pattern="dd-MM-yyyy HH:mm"/></div>
                <h6>Name: ${product.name}</h6><br>

                <h6>Description: ${product.description} </h6><br>

                <h6>Price: ${product.price}</h6><br>

            </div>
        </div>
        <br>
    </div>
    </body>
    </html>
</fmt:bundle>