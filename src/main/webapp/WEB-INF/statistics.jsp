<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="parts/header.jsp"/>

<div class="container">
  <h3> User Statistics </h3>
</div>
<div class="container">
  <table class="table">
    <thead>
    <tr>
      <th scope="col">Metrics</th>
      <th scope="col">Metric Value</th>
    </tr>
    <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Current Logged In User</td>
      <td>${sessionScope.user}</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Current IP Address</td>
      <td>${pageContext.request.remoteAddr}</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>Total game played</td>
      <td>${requestScope.totalGames}</td>
    </tr>
    <tr>
      <th scope="row">4</th>
      <td>Total Games Finished</td>
      <td>${requestScope.finishedGames}</td>
    </tr>
    </tbody>
  </table>
</div>

<jsp:include page="parts/footer.jsp"/>