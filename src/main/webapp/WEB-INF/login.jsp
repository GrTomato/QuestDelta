<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="parts/header.jsp"/>


<div class="container">
    <form class="form-horizontal" action="login" method="post">
        <fieldset>

            <!-- Form Name -->
            <legend>Log In</legend>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userLogin">Login</label>
                <div class="col-md-4">
                    <input id="userLogin" name="login" type="text" placeholder="set login" class="form-control input-md" required="">
                </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userPassword">Password</label>
                <div class="col-md-4">
                    <input id="userPassword" name="password" type="password" placeholder="Your Password" class="form-control input-md" required="">
                </div>
            </div>

            <c:choose>
                <c:when test="${!empty requestScope.message}">
                    <p style="color:red">${requestScope.message}</p>
                </c:when>
            </c:choose>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="submit"></label>
                <div class="col-md-4">
                    <button id="submit" name="loginButton" class="btn btn-success">Login</button>
                </div>
            </div>

        </fieldset>
    </form>
</div>

<jsp:include page="parts/footer.jsp"/>