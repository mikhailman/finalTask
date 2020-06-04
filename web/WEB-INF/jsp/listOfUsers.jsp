<%--
  Created by IntelliJ IDEA.
  User: Michael
  Date: 19.05.2020
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="vm" uri="kefirTag" %>

<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="text" prefix="userProfile.">

    <html lang="${language}">
    <head>
        <title><fmt:message key="title.listOfUsers"/></title>
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
    <div class="container" style="background: silver">

        <div class="container" style="background: silver">
            <div role="form">
                <h2><fmt:message key="title.listOfUsers"/></h2>

                <c:forEach var="element" items="${usersList}">

                    <div class="col-sm-12" role="form">

                        <div class="col-sm-4" role="form">
                            <div class="form-group row">
                                <label for="idUser" class="col-sm-3 control-label"><fmt:message
                                        key="fields.idUser"/></label>
                                <div class="col-sm-9">
                                    <input type="text" id="idUser" class="form-control"
                                           value="${element.idUser}" readonly>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-4" role="form">
                            <div class="form-group row">
                                <label for="role" class="col-sm-3 control-label"><fmt:message
                                        key="fields.role"/></label>
                                <div class="col-sm-9">
                                    <input type="text" id="role" class="form-control"
                                           value="${element.role}" readonly>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-4" role="form">
                            <div class="form-group row">
                                <label for="login" class="col-sm-3 control-label"><fmt:message
                                        key="fields.login"/></label>
                                <div class="col-sm-9">
                                    <input type="text" id="login" class="form-control"
                                           value="${element.login}" readonly>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-4" role="form">
                            <div class="form-group row">
                                <label for="password" class="col-sm-3 control-label"><fmt:message
                                        key="fields.password"/></label>
                                <div class="col-sm-9">
                                    <input type="text" id="password" class="form-control"
                                           value="${element.password}" readonly>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-4" role="form">
                            <div class="form-group row">
                                <label for="email" class="col-sm-3 control-label"><fmt:message
                                        key="fields.email"/></label>
                                <div class="col-sm-9">
                                    <input type="text" id="email" class="form-control"
                                           value="${element.email}" readonly>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-4" role="form">
                            <div class="form-group row">
                                <label for="phone" class="col-sm-3 control-label"><fmt:message
                                        key="fields.phoneNumber"/></label>
                                <div class="col-sm-9">
                                    <input type="text" id="phone" class="form-control"
                                           value="${element.phone}" readonly>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-4" role="form">
                            <div class="form-group row">
                                <label for="name" class="col-sm-3 control-label"><fmt:message
                                        key="fields.firstName"/></label>
                                <div class="col-sm-9">
                                    <input type="text" id="name" class="form-control"
                                           value="${element.name}" readonly>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-4" role="form">
                            <div class="form-group row">
                                <label for="surName" class="col-sm-3 control-label"><fmt:message
                                        key="fields.lastName"/></label>
                                <div class="col-sm-9">
                                    <input type="text" id="surName" class="form-control"
                                           value="${element.surname}" readonly>
                                </div>
                            </div>
                        </div>

                    </div>
                </c:forEach>

                    <%--                <div class="col-sm-8">--%>

                    <%--                    <div class="table-responsive">--%>
                    <%--                        <table class="table table-bordered">--%>
                    <%--                            <caption>Users</caption>--%>
                    <%--                            <thead>--%>
                    <%--                            <tr>--%>
                    <%--                                <th id="id">ID</th>--%>
                    <%--                                <th id="login">Login</th>--%>
                    <%--                            </tr>--%>
                    <%--                            </thead>--%>
                    <%--                            <tbody>--%>


                    <%--                            <c:forEach var="element" items="${usersList}">--%>
                    <%--                                <tr>--%>
                    <%--                                    <td><c:out value="${element.id}"/></td>--%>
                    <%--                                    <td><c:out value="${element.login}"/></td>--%>
                    <%--                                </tr>--%>
                    <%--                            </c:forEach>--%>

                    <%--                            </tbody>--%>
                    <%--                        </table>--%>
                    <%--                    </div>--%>
                    <%--                </div>--%>
            </div>

        </div>
    </div>

    <c:import url="endOfPages.jsp"/>

    </body>
    </html>
</fmt:bundle>