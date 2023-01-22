<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="parts/header.jsp"/>

<div class="container">
  <form class="form-horizontal" action="register" method="post">
    <fieldset>

      <!-- Form Name -->
      <legend>Registration</legend>

      <!-- Text input-->
      <div class="form-group">
        <label class="col-md-4 control-label" for="LoginInput">Login</label>
        <div class="col-md-4">
          <input id="LoginInput" name="login" type="text" placeholder="Type in Login" class="form-control input-md" required="">
        </div>
      </div>

      <!-- Password input-->
      <div class="form-group">
        <label class="col-md-4 control-label" for="PasswordInput">Password</label>
        <div class="col-md-4">
          <input id="PasswordInput" name="password" type="password" placeholder="Type in Password" class="form-control input-md" required="">

        </div>
      </div>

      <!-- Text input-->
      <div class="form-group">
        <label class="col-md-4 control-label" for="EmailInput">Email</label>
        <div class="col-md-4">
          <input id="EmailInput" name="email" type="text" placeholder="Type In Email" class="form-control input-md" required="">
        </div>
      </div>

      <c:choose>
        <c:when test="${!empty requestScope.message}">
          <p style="color:red">${requestScope.message}</p>
        </c:when>
      </c:choose>

      <!-- Button -->
      <div class="form-group">
        <label class="col-md-4 control-label" for="RegisterButton"></label>
        <div class="col-md-4">
          <button id="RegisterButton" name="RegisterButton" class="btn btn-primary">Register</button>
        </div>
      </div>

    </fieldset>
  </form>
</div>

<jsp:include page="parts/footer.jsp"/>