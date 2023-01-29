<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="parts/header.jsp"/>

    <div class="container">

        <c:choose>
            <c:when test="${!empty requestScope.message}">
                <p style="color:red">${requestScope.message}</p>
            </c:when>
        </c:choose>

        <c:forEach var="game" items="${requestScope.games}">
            <div class="all-classes-container">
                <h3> Game #${game.id} </h3>
                <p> Quest: ${game.quest.name}</p>
                <p> Status: ${game.gameState}</p>
                <c:choose>
                    <c:when test="${game.gameState=='PROGRESS'}">
                        <p> Last Question: ${game.lastRedirectedQuestion.text}</p>
                        <div class="form-group">
                            <div class="col-md-4">
                                <a href="game?quest_id=${game.quest.id}">
                                    <button id="playButton" name="continueGame" class="btn btn-primary">Continue</button>
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