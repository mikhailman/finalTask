<%--
  Created by IntelliJ IDEA.
  User: Michael
  Date: 02.04.2020
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="vm" uri="kefirTag" %>

<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>

<fmt:bundle basename="text" prefix="errorPage.">

    <html lang="${language}">

    <head>
        <title><fmt:message key="title"/></title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

        <style>
            body {
                background-size: cover;
            }

            *[role="form"] {
                max-width: 730px;
                padding: 15px;
                margin: 0 auto;
                border-radius: 0.3em;
                background-color: #eff2be;
            }
        </style>
    </head>
    <body style="padding-top: 40px; padding-bottom: 120px; color: #f2009a" background="images/background.jpg">

    <c:import url="headOfPages.jsp"/>

    <div class="text-center" style="margin-top:30px;margin-right: 20px">
        <h1 class="display-3">Error processing information! </h1>
    </div>


    <div class="container">

        <form action="saveCar.html" role="form" class="needs-validation" novalidate method="post">

            <c:choose>
                <c:when test="${not empty unknownError}">
                    <label>${unknownError}</label>
                </c:when>
                <c:otherwise>
                    <label><fmt:message key="failedRequest"/> ${pageContext.errorData.requestURI}</label>>
                    <br>
                    <label><fmt:message key="statusCode"/> ${pageContext.errorData.statusCode}:</label>
                    <c:if test="${not empty error}"><label><fmt:message key="${error}"/></label></c:if>
                    <label>${pageContext.exception.message}</label>

                </c:otherwise>
            </c:choose>

        </form>
    </div>

    <c:import url="endOfPages.jsp"/>

    </body>
    </html>
</fmt:bundle>

