<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>JSP Quests</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
            <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
            </a>

            <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/quests" class="nav-link px-2 link-secondary">Quests</a></li>
                <c:choose>
                    <c:when test="${sessionScope.user.role!='VISITOR'}">
                    <li><a href="/games" class="nav-link px-2 link-dark">Games</a></li>
                    <li><a href="/stats" class="nav-link px-2 link-dark">Statistics</a></li>
                    <li><a href="/profile" class="nav-link px-2 link-dark">Profile</a></li>
                    </c:when>
                </c:choose>
            </ul>

            <c:choose>
                <c:when test="${sessionScope.user.role!='VISITOR'}">
                    <div class="col-md-3 text-end">
                        Current user: ${sessionScope.user.login}

                        <a href="logout">
                            <button type="button" class="btn btn-outline-primary me-2">Log Out</button>
                        </a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="col-md-3 text-end">
                        <a href="login">
                            <button type="button" class="btn btn-outline-primary me-2">Login</button>
                        </a>
                        <a href="register">
                            <button type="button" class="btn btn-primary">Sign-up</button>
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>
        </header>
    </div>

