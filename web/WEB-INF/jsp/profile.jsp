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

<fmt:bundle basename="text" prefix="userProfile.">

    <html lang="${language}">

    <jsp:useBean id="user" scope="request" type="by.verishko.kefir.entity.User"/>

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
        <form action="editProfile.html" role="form" method="post">
            <h2><fmt:message key="title.viewProfile"/></h2>
            <label style="background-color: #B82303; font-size: 25px">${unknownError}</label>

            <div class="form-group row">
                <label for="login" class="col-sm-3 control-label"><fmt:message key="fields.login"/></label>
                <div class="col-sm-9">
                    <input type="text" id="login" class="form-control"
                           value="${user.login}" readonly>
                </div>
            </div>
            <div class="form-group row">
                <label for="firstName" class="col-sm-3 control-label"><fmt:message key="fields.firstName"/> </label>
                <div class="col-sm-9">
                    <input type="text" id="firstName" class="form-control"
                           value="${user.name}" readonly>
                </div>
            </div>
            <div class="form-group row">
                <label for="lastName" class="col-sm-3 control-label"><fmt:message key="fields.lastName"/></label>
                <div class="col-sm-9">
                    <input type="text" id="lastName" class="form-control"
                           value="${user.surname}" readonly>
                </div>
            </div>
            <div class="form-group row">
                <label for="email" class="col-sm-3 control-label"><fmt:message key="fields.email"/></label>
                <div class="col-sm-9">
                    <input type="email" id="email" class="form-control"
                           value="${user.email}" readonly>
                </div>
            </div>
                <%--            <div class="form-group row">--%>
                <%--                <label for="createDate" class="col-sm-3 control-label"><fmt:message--%>
                <%--                        key="fields.dateRegistration"/></label>--%>
                <%--                <div class="col-sm-4">--%>
                <%--                    <input type="date" id="createDate" class="form-control" value="${user.date_registration}"--%>
                <%--                           readonly>--%>
                <%--                </div>--%>
                <%--            </div>--%>
            <div class="form-group row">
                <label for="phone" class="col-sm-3 control-label"><fmt:message key="fields.phoneNumber"/></label>
                <div class="col-sm-6">
                    <input type="phone" id="phone"
                           class="form-control" value="${user.phone}" readonly>
                </div>
            </div>
            <div class="mx-auto-center">
                <button type="submit" class="btn btn-primary"><fmt:message key="button.editProfile"/></button>
            </div>
        </form>
        <form action="changePassword.html" role="form" method="post">
            <div class="mx-auto-center">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalChangePassword">
                    <fmt:message key="button.changePassword"/></button>
            </div>

            <!-- The Modal -->
            <div class="modal" id="modalChangePassword">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h2 class="modal-title"><fmt:message key="title.modalChangePassword"/></h2>
                            <button type="button" class="close" data-dismiss="modal">×</button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <div class="form-group row">
                                <label for="oldPassword" class="col-sm-3 control-label"><fmt:message
                                        key="fields.oldPassword"/>:*</label>
                                <div class="col-sm-9">
                                    <input type="password" id="oldPassword" name="oldPassword"
                                           placeholder="<fmt:message key="fields.oldPassword"/>" class="form-control"
                                           required autofocus pattern="^[\w\dа-яА-Я-+%$@!]{1,50}$">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="newPassword" class="col-sm-3 control-label"><fmt:message
                                        key="fields.newPassword"/>:*</label>
                                <div class="col-sm-9">
                                    <input type="password" id="newPassword" name="newPassword"
                                           placeholder="<fmt:message key="fields.newPassword"/>" class="form-control"
                                           required autofocus pattern="^[\w\dа-яА-Я-+%$@!]{1,50}$">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="confirmNewPassword" class="col-sm-3 control-label"><fmt:message
                                        key="fields.confirmNewPassword"/>:*</label>
                                <div class="col-sm-9">
                                    <input type="password" id="confirmNewPassword" name="confirmNewPassword"
                                           placeholder="<fmt:message key="fields.confirmNewPassword"/>"
                                           class="form-control"
                                           required autofocus pattern="^[\w\dа-яА-Я-+%$@!]{1,50}$">
                                </div>
                            </div>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-danger"><fmt:message
                                    key="button.changePassword"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <form action="deleteProfile.html" role="form" method="post">
            <div class="mx-auto-center">
                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modalDelProfile">
                    <fmt:message
                            key="button.deleteProfile"/></button>
            </div>
            <!-- The Modal -->
            <div class="modal" id="modalDelProfile">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h2 class="modal-title"><fmt:message key="title.modalDeleteProfile"/></h2>
                            <button type="button" class="close" data-dismiss="modal">×</button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <h5><fmt:message key="fields.modalWarning"/></h5>
                            <div class="form-group row">
                                <label for="password" class="col-sm-3 control-label"><fmt:message
                                        key="fields.password"/>*</label>
                                <div class="col-sm-9">
                                    <input type="password" id="password" name="password"
                                           placeholder="<fmt:message key="fields.password"/>" class="form-control"
                                           required autofocus pattern="^[\w-]{1,20}$">
                                </div>
                            </div>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-danger"><fmt:message
                                    key="button.deleteProfile"/></button>
                        </div>

                    </div>
                </div>
            </div>
        </form>
    </div> <!-- ./container -->
    <c:import url="endOfPages.jsp"/>

    </body>
    </html>
</fmt:bundle>