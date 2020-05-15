<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="vm" uri="kefirTag" %>

<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>

<fmt:bundle basename="text" prefix="userProfile.">
    <html lang="${language}">

    <head>
        <title><fmt:message key="title.authorisation"/></title>
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

    <div class="container p-3">
        <form action="login.html" method="post">
            <input type="hidden" name="command" value="login"/>
            <div class="form-group">
                <input type="text" class="form-control" name="email" value="" placeholder="Enter Email" required>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="password" value="" placeholder="Enter Password"
                       required></br>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-dark" value="Enter">
            </div>
        </form>
    </div>


        <%--    <div class="container">--%>
        <%--        <div>--%>
        <%--            <div class="row" style="position:center; margin-top: 5%;">--%>
        <%--                <div class="col-sm-3"></div>--%>
        <%--                <div class="col-sm-6" style="background: #f2f2f2; border-radius: 3px">--%>
        <%--                    <form action="authorisation.html" role="form" class="needs-validation" novalidate method="post">--%>
        <%--                        <label style="background-color: B82303; font-size: 25px">${message}</label>--%>
        <%--                        <div class="form-group has-feedback">--%>
        <%--                            <label for="uname" class="control-label col-xs-3" style="font-size: 25px;"><fmt:message--%>
        <%--                                    key="fields.login"/></label>--%>
        <%--                            <input type="text" class="form-control" id="uname"--%>
        <%--                                   placeholder="<fmt:message key="fields.login"/>"--%>
        <%--                                   name="login" value="${email}" required autofocus pattern="^[\w-]{1,20}$">--%>
        <%--                            <div class="invalid-feedback"><fmt:message key="errors.fillOutTheField"/></div>--%>
        <%--                            <label style="background-color: B82303; font-size: 25px">${errorLogin}</label>--%>
        <%--                        </div>--%>

        <%--                        <div class="form-group">--%>
        <%--                            <label for="pwd" style="font-size: 25px"><fmt:message key="fields.password"/></label>--%>
        <%--                            <input type="password" class="form-control" id="pwd"--%>
        <%--                                   placeholder="<fmt:message key="fields.password"/>" name="password" required autofocus--%>
        <%--                                   pattern="^[\w\dа-яА-Я-+%$@!]{1,50}$">--%>
        <%--                            <div class="invalid-feedback"><fmt:message key="errors.fillOutTheField"/></div>--%>
        <%--                            <label style="background-color: B82303; font-size: 25px">${errorPassword}</label>--%>
        <%--                        </div>--%>
        <%--                        <div class="form-group">--%>
        <%--                            <div class="col-sm-9 col-sm-offset-3">--%>
        <%--                                <span class="help-block">*<fmt:message key="fields.requiredFields"/></span>--%>
        <%--                            </div>--%>
        <%--                        </div>--%>
        <%--                        <div class="mx-auto">--%>
        <%--                            <button type="submit" class="btn btn-primary"><fmt:message key="button.logIn"/></button>--%>

        <%--                            <button type="reset" class="btn btn-danger"><fmt:message key="button.reset"/></button>--%>
        <%--                        </div>--%>
        <%--                    </form>--%>
        <%--                </div>--%>
        <%--                <div class="col-sm-3"></div>--%>
        <%--            </div>--%>
        <%--        </div>--%>

        <%--    </div>--%>
    <c:import url="endOfPages.jsp"/>
        <%--    <script>--%>
        <%--        // Disable form submissions if there are invalid fields--%>
        <%--        (function () {--%>
        <%--            'use strict';--%>
        <%--            window.addEventListener('load', function () {--%>
        <%--                // Get the forms we want to add validation styles to--%>
        <%--                var forms = document.getElementsByClassName('needs-validation');--%>
        <%--                // Loop over them and prevent submission--%>
        <%--                var validation = Array.prototype.filter.call(forms, function (form) {--%>
        <%--                    form.addEventListener('submit', function (event) {--%>
        <%--                        if (form.checkValidity() === false) {--%>
        <%--                            event.preventDefault();--%>
        <%--                            event.stopPropagation();--%>
        <%--                        }--%>
        <%--                        form.classList.add('was-validated');--%>
        <%--                    }, false);--%>
        <%--                });--%>
        <%--            }, false);--%>
        <%--        })();--%>

        <%--    </script>--%>
    </body>


</fmt:bundle>