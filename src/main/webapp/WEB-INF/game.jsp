<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="parts/header.jsp"/>

<div class="container">
    <h1>${requestScope.game.quest.name}</h1>
    <p>
        ${requestScope.game.lastRedirectedQuestion.text}
    </p>

    <c:choose>
        <c:when test="${!empty requestScope.game.lastRedirectedQuestion.answers}">
            <div class="container">
                <form class="form-horizontal" action="game?id=${requestScope.game.id}" method="post">
                    <fieldset>
                        <!-- Form Name -->
                        <legend>Select your Answer</legend>
                        <!-- Multiple Radios -->
                        <div class="form-group">
                            <div class="col-md-4">
                                <c:forEach var="answer" items="${requestScope.game.lastRedirectedQuestion.answers}">
                                    <div class="radio">
                                        <label for="answers-${answer.id}">
                                            <input type="radio" name="selectedAnswerNextQuestion" id="answers-${answer.id}" value="${answer.nextQuestion.id}">
                                                ${answer.text}
                                        </label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                        <!-- Button -->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="selectButton"></label>
                            <div class="col-md-4">
                                <button id="selectButton" name="selectButton" class="btn btn-primary">Select</button>
                            </div>
                        </div>

                    </fieldset>
                </form>
            </div>
        </c:when>
    </c:choose>
    <hr>

    <a href="game?quest_id=${requestScope.game.quest.id}&instruct=restart">
        <button id="restartButton" name="restart" class="btn btn-primary">Restart Quest</button>
    </a>
</div>

<jsp:include page="parts/footer.jsp"/>