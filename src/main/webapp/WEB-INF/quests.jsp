<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="parts/header.jsp"/>

<div class="container">
    <h1>Quests Available:</h1>
    <hr>
    <c:forEach var="quest" items="${requestScope.quests}">
        <div class="all-classes-container">
            <h3> ${quest.name} </h3>
            <p>Description: ${quest.description}</p>
            <p>Created By: ${quest.author}</p>
            <c:choose>
                <c:when test="${sessionScope.user.role!='VISITOR'}">
                    <div class="form-group">
                        <div class="col-md-4">
                            <a href="game?quest_id=${quest.id}">
                                <button id="playButton" name="starGame" class="btn btn-primary">Play!</button>
                            </a>
                        </div>
                    </div>
                </c:when>
            </c:choose>
            <hr>
        </div>
    </c:forEach>
</div>

<jsp:include page="parts/footer.jsp"/>