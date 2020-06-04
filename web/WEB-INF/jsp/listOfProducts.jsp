<%--
  Created by IntelliJ IDEA.
  User: Michael
  Date: 25.05.2020
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="vm" uri="kefirTag" %>

<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="text" prefix="products.">

    <html lang="${language}">
    <head>
        <title><fmt:message key="title.listOfProducts"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <style>
            body {
                background-size: cover;
            }

            *[role="form"] {
                max-width: 730px;
                padding: 15px;
                margin: 0 auto;
                border-radius: 0.3em;
                background-color: #f2f2f2;
            }

            *[role="form"] h2 {
                font-family: 'Open Sans', sans-serif;
                font-size: 40px;
                font-weight: 600;
                color: #000000;
                margin-top: 2%;
                margin-bottom: 2%;
                text-align: center;
                text-transform: uppercase;
                letter-spacing: 4px;
            }

        </style>
    </head>
    <body style="padding-top: 40px; padding-bottom: 120px; color: #820030" background="images/background.jpg">
    <c:import url="headOfPages.jsp"/>

    <div class="container">
        <div role="form">
            <h2><fmt:message key="title.listOfProducts"/></h2>

                <%--            <div class="row" style="position:center">--%>

                <%--            <c:choose>--%>
                <%--                <c:when test="${ sessionScope.authorizedUser == null or sessionScope.aithorizedUser.role == USER }">--%>

            <c:forEach var="element" items="${products}">

                <div class="col-sm-12" role="form">

                    <div class="col-sm-4" role="form">
                        <div class="form-group row">
                            <label for="name" class="col-sm-3 control-label"><fmt:message
                                    key="fields.name"/></label>
                            <div class="col-sm-9">
                                <input type="text" id="name" class="form-control"
                                       value="${element.name}" readonly>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-4" role="form">
                        <div class="form-group row">
                            <label for="description" class="col-sm-3 control-label"><fmt:message
                                    key="fields.description"/></label>
                            <div class="col-sm-9">
                                <input type="text" id="description" class="form-control"
                                       value="${element.description}" readonly>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-4" role="form">
                        <div class="form-group row">
                            <label for="price" class="col-sm-3 control-label"><fmt:message
                                    key="fields.price"/></label>
                            <div class="col-sm-9">
                                <input type="text" id="price" class="form-control"
                                       value="${element.price}" readonly>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-4" role="form">
                        <div class="form-group row">
                            <label for="date_creation" class="col-sm-3 control-label"><fmt:message
                                    key="fields.date_creation"/></label>
                            <div class="col-sm-9">
                                <input type="text" id="date_creation" class="form-control"
                                       value="${element.date_creation}" readonly>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
                <%--                </c:when>--%>
                <%--                <c:when test="${ sessionScope.authorizedUser.role == USER }">--%>

                <%--                </c:when>--%>

                <%--            </c:choose>--%>
        </div>
    </div>
        <%--        </div>--%>
    <c:import url="endOfPages.jsp"/>

    </body>
    </html>
</fmt:bundle>