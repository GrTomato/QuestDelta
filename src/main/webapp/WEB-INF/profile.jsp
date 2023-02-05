<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="parts/header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col">
            <h3>Current Profile Data</h3>
            <div class="col"> Email: ${sessionScope.user.email} </div>
            <div class="col"> Login: ${sessionScope.user.login} </div>
            <div class="col"> Role: ${sessionScope.user.role} </div>
        </div>
        <div class="col">
            <form class="form-horizontal" action="profile" method="post">
                <fieldset>
                    <!-- Form Name -->
                    <h3>Edit Profile Data</h3>
                    <!-- Email input-->
                    <div class="form-group">
                        <label class="col control-label" for="userEmail">Email</label>
                        <div class="col">
                            <input id="userEmail" name="user_email" type="text" placeholder="Set Email" class="form-control input-md" required>
                        </div>
                    </div>
                    <!-- Password input-->
                    <div class="form-group">
                        <label class="col control-label" for="userPassword">Password</label>
                        <div class="col">
                            <input id="userPassword" name="user_password" type="password" placeholder="Print New Password"
                                   class="form-control input-md" required>
                        </div>
                    </div>
                    <!-- Password input-->
                    <div class="form-group">
                        <label class="col control-label" for="userPassword">Password</label>
                        <div class="col">
                            <input id="userPasswordRepeat" name="user_password_repeat" type="password" placeholder="Repeat New Password"
                                class="form-control input-md" required>
                        </div>
                    </div>
                    <!-- Select Basic -->
                    <div class="form-group">
                        <label class="col control-label" for="userRole">Role</label>
                        <div class="col">
                            <select id="userRole" name="user_role" class="form-control">
                                <c:forEach items="${applicationScope.roles}" var="role">
                                    <option value="${role}" ${role==requestScope.user.role?"selected":""}>
                                            ${role}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <c:choose>
                        <c:when test="${!empty requestScope.message}">
                            <p style="color:red">${requestScope.message}</p>
                        </c:when>
                    </c:choose>

                    <!-- Button -->
                    <div class=" form-group">
                        <label class="col control-label" for="submit"></label>
                        <div class="col">
                            <button id="submit" name="update"
                                    class="btn btn-success">Update</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>

<jsp:include page="parts/footer.jsp"/>