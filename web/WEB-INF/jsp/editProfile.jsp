<%--
  Created by IntelliJ IDEA.
  User: Michael
  Date: 19.05.2020
  Time: 19:20
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
        <title><fmt:message key="title.viewProfile"/></title>
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
            <%--    <div class="row">--%>
        <form action="updateProfile.html" role="form" class="needs-validation" novalidate method="post">
            <h2><fmt:message key="title.editProfile"/></h2>
            <label style="background-color: #B82303; font-size: 25px">${unknownError}</label>
            <div class="form-group row">
                <label for="login" class="col-sm-3 control-label"><fmt:message key="fields.login"/></label>
                <div class="col-sm-9">
                    <input type="text" id="login" name="login" placeholder="<fmt:message key="fields.login"/>"
                           class="form-control" value="${user.login}" required pattern="^[\w-]{1,20}$">

                </div>
                <label style="background-color: #f2f2f2; font-size: 25px">${errorLogin}</label>
            </div>
            <div class="form-group row">
                <label for="firstName" class="col-sm-3 control-label"><fmt:message key="fields.firstName"/> </label>
                <div class="col-sm-9">
                    <input type="text" id="firstName" name="firstName"
                           placeholder="<fmt:message key="fields.firstName"/>" class="form-control"
                           value="${user.name}" required autofocus pattern="^[a-zA-Zа-яА-Я-]{1,255}$">
                </div>
                <label style="background-color: #f2f2f2; font-size: 25px">${errorFirstName}</label>
            </div>
            <div class="form-group row">
                <label for="lastName" class="col-sm-3 control-label"><fmt:message key="fields.lastName"/></label>
                <div class="col-sm-9">
                    <input type="text" id="lastName" name="lastName" placeholder="<fmt:message key="fields.lastName"/>"
                           class="form-control" value="${user.surname}" required autofocus
                           pattern="^[a-zA-Zа-яА-Я-]{1,255}$">
                </div>
                <label style="background-color: #f2f2f2; font-size: 25px">${errorLastName}</label>
            </div>
            <div class="form-group row">
                <label for="email" class="col-sm-3 control-label"><fmt:message key="fields.email"/></label>
                <div class="col-sm-9">
                    <input type="email" id="email" name="email" placeholder="<fmt:message key="fields.email"/>"
                           class="form-control" name="email" value="${user.email}" required autofocus>
                </div>
                <label style="background-color: #f2f2f2; font-size: 25px">${errorEmail}</label>
            </div>
                <%--            <div class="form-group row">--%>
                <%--                <label for="birthDate" class="col-sm-3 control-label"><fmt:message key="fields.dateRegistration"/></label>--%>
                <%--                <div class="col-sm-4">--%>
                <%--                    <input type="date" id="birthDate" name="birthDate" min="${ds:nowMinusYears(100)}" max="${ds:nowMinusYears(18)}" class="form-control" value="${userData.birthday}"--%>
                <%--                           required autofocus>--%>
                <%--                </div>--%>
                <%--                <label style="background-color: B82303; font-size: 25px">${error}</label>--%>
                <%--            </div>--%>
            <div class="form-group row">
                <label for="phoneNumber" class="col-sm-3 control-label"><fmt:message key="fields.phoneNumber"/></label>
                <div class="col-sm-6">
                    <input type="phone" id="phoneNumber" name="phone" placeholder="375XXYYYYYYY"
                           class="form-control" value="${user.phone}" pattern="^[0-9]{7,15}$" required>
                </div>
                <label style="background-color: #f2f2f2; font-size: 25px">${errorPhoneNumber}</label>
            </div>
            <div class="mx-auto-center">
                <button type="submit" class="btn btn-primary"><fmt:message key="button.save"/></button>
            </div>
        </form> <!-- /form -->
            <%--    </div>--%>

        <form action="viewUserProfile.html" role="form" method="post">
            <div class="mx-auto-center">
                <button type="submit" class="btn btn-warning"><fmt:message key="button.cancel"/></button>
            </div>
        </form>
    </div> <!-- ./container -->

    <c:import url="endOfPages.jsp"/>

    <script>
        // Disable form submissions if there are invalid fields
        (function () {
            'use strict';
            window.addEventListener('load', function () {
                // Get the forms we want to add validation styles to
                var forms = document.getElementsByClassName('needs-validation');
                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>

    </body>
    </html>
</fmt:bundle>