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

    <html lang="${language}">
    <jsp:useBean id="user" scope="request" type="by.verishko.kefir.entity.User"/>

    <head>
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

        <%--    <div class="text-center" style="margin-top:30px;margin-right: 20px">--%>
        <%--        <h1 class="display-4">Profile</h1>--%>
        <%--        <br>--%>
        <%--    </div>--%>
        <%--    <div class="row col-md-12">--%>
        <%--        <div class="col-md-2"></div>--%>
        <%--        <div class="col-md-6">--%>
        <%--            <br>--%>
        <%--            <h5>First name: ${user.name}</h5>--%>
        <%--            <br>--%>
        <%--            <h5>Last name: ${user.surname}</h5>--%>
        <%--            <br>--%>
        <%--            <h5>Nickname: ${user.login}</h5>--%>
        <%--            <br>--%>
        <%--            <h5>Phone : ${user.phone}</h5>--%>
        <%--            <br>--%>
        <%--            <h5>Email: ${user.email}</h5>--%>
        <%--            <br>--%>
        <%--            <h5>Email: ${user.date_registration}</h5>--%>
        <%--            <br>--%>
        <%--            <h5>Email: ${user.role}</h5>--%>
        <%--            <br>--%>
        <%--        </div>--%>
        <%--        <div class="col-md-2"></div>--%>
        <%--        <div class="col-md-3">--%>
        <%--            <a class="btn btn-outline-primary" style="margin-left: 19%"--%>
        <%--               href="${pageContext.request.contextPath}/editProfile.html">Update profile</a>--%>
        <%--        </div>--%>
        <%--        <div class="btn-group">--%>
        <%--            <a class="btn btn-primary"--%>
        <%--               href="${pageContext.request.contextPath}/myProducts.html">My products</a>--%>
        <%--        </div>--%>
        <%--    </div>--%>


    <div class="container">
            <%--    <div class="row">--%>
        <form action="editProfile.html" role="form" method="post">
            <h2><fmt:message key="titel.viewProfile"/></h2>
            <label style="background-color: #B82303; font-size: 25px">${unknownError}</label>

            <div class="form-group row">
                <label for="login" class="col-sm-3 control-label"><fmt:message key="fields.login"/></label>
                <div class="col-sm-9">
                    <input type="text" id="login" class="form-control"
                           value="${userData.login}" readonly>
                </div>
            </div>
            <div class="form-group row">
                <label for="firstName" class="col-sm-3 control-label"><fmt:message key="fields.firstName"/> </label>
                <div class="col-sm-9">
                    <input type="text" id="firstName" class="form-control"
                           value="${userData.name}" readonly>
                </div>
            </div>
            <div class="form-group row">
                <label for="lastName" class="col-sm-3 control-label"><fmt:message key="fields.lastName"/></label>
                <div class="col-sm-9">
                    <input type="text" id="lastName" class="form-control"
                           value="${userData.surname}" readonly>
                </div>
            </div>
            <div class="form-group row">
                <label for="email" class="col-sm-3 control-label"><fmt:message key="fields.email"/></label>
                <div class="col-sm-9">
                    <input type="email" id="email" class="form-control"
                           value="${userData.email}" readonly>
                </div>
            </div>
            <div class="form-group row">
                <label for="birthDate" class="col-sm-3 control-label"><fmt:message key="fields.dateOfBirth"/></label>
                <div class="col-sm-4">
                    <input type="date" id="birthDate" class="form-control" value="${userData.birthday}"
                           readonly>
                </div>
            </div>
            <div class="form-group row">
                <label for="phoneNumber" class="col-sm-3 control-label"><fmt:message key="fields.phoneNumber"/></label>
                <div class="col-sm-6">
                    <input type="phoneNumber" id="phoneNumber"
                           class="form-control" value="${userData.phoneNumber}" readonly>
                </div>
            </div>
            <div class="form-group row">
                <label for="country" class="col-sm-3 control-label"><fmt:message key="fields.country"/></label>
                <div class="col-sm-6">
                    <input type="country" id="country"
                           class="form-control" value="${userData.country}" readonly>
                </div>
            </div>
            <div class="form-group row">
                <label for="passportNumber" class="col-sm-3 control-label"><fmt:message
                        key="fields.passportNumber"/></label>
                <div class="col-sm-6">
                    <input type="passportNumber" id="passportNumber"
                           class="form-control" value="${userData.passportNumber}" readonly>
                </div>
            </div>
            <div class="form-group row">
                <label for="passportDate" class="col-sm-3 control-label"><fmt:message
                        key="fields.passportDateOfIssue"/></label>
                <div class="col-sm-4">
                    <input type="date" id="passportDate" class="form-control"
                           value="${userData.passportDateOfIssue}" readonly>
                </div>
            </div>
            <div class="form-group row">
                <label for="sex" class="col-sm-3 control-label"><fmt:message key="fields.sex"/></label>
                <div class="col-sm-4">
                    <input type="text" id="sex" class="form-control"
                           value="${userData.gender}" readonly>
                </div>
            </div>
            <div class="mx-auto-center">
                <button type="submit" class="btn btn-primary"><fmt:message key="button.editProfile"/></button>
            </div>
        </form> <!-- /form -->
            <%--    </div>--%>

        <c:choose>
            <c:when test="${ empty userData.car}">
                <form action="addCar.html" role="form" method="post">
                    <div class="mx-auto-center">
                        <button type="submit" class="btn btn-primary"><fmt:message key="button.addCar"/></button>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <form action="editCar.html" role="form" method="post">
                    <div class="form-group row">
                        <label for="brand" class="col-sm-3 control-label"><fmt:message key="fields.carBrand"/></label>
                        <div class="col-sm-9">
                            <input type="text" id="brand" class="form-control"
                                   value="${userData.car.brand}" readonly>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="model" class="col-sm-3 control-label"><fmt:message key="fields.carModel"/> </label>
                        <div class="col-sm-9">
                            <input type="text" id="model" class="form-control"
                                   value="${userData.car.model}" readonly>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="yearOfProduce" class="col-sm-3 control-label"><fmt:message
                                key="fields.carYearOfProduce"/></label>
                        <div class="col-sm-9">
                            <input type="yearOfProduce" id="yearOfProduce" class="form-control"
                                   value="${userData.car.yearOfProduce}" readonly>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="airConditioner" class="col-sm-3 control-label"><fmt:message
                                key="fields.carClimate"/></label>
                        <div class="col-sm-9">
                            <input type="airConditioner" id="airConditioner" class="form-control"
                                   value="${userData.car.airConditioner}" readonly>
                        </div>
                    </div>
                    <div class="mx-auto-center">
                        <button type="submit" class="btn btn-primary"><fmt:message key="button.editCar"/></button>
                    </div>
                </form>
                <form action="deleteCar.html" role="form" method="post">
                    <div class="mx-auto-center">
                        <button type="submit" class="btn btn-warning"><fmt:message key="button.deleteCar"/></button>
                    </div>
                </form>
            </c:otherwise>
        </c:choose>
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
                            <h2 class="modal-title"><fmt:message key="titel.modalChangePassword"/></h2>
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
                                <label for="comfirmNewPassword" class="col-sm-3 control-label"><fmt:message
                                        key="fields.comfirmNewPassword"/>:*</label>
                                <div class="col-sm-9">
                                    <input type="password" id="comfirmNewPassword" name="comfirmNewPassword"
                                           placeholder="<fmt:message key="fields.comfirmNewPassword"/>"
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

        <form action="deleteUser.html" role="form" method="post">
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
                            <h2 class="modal-title"><fmt:message key="titel.modalDeleteProfile"/></h2>
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
    </div>
    </div> <!-- ./container -->
    <c:import url="endOfPages.jsp"/>

    </body>
    </html>
</fmt:bundle>