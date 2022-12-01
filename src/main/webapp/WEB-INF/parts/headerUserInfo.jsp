<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 27.11.2022
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="d-flex flex-column">
    <div class="p-2 mb-0">
        <c:out value="${userData.getName()}"/><br>
        Games count: <c:out value="${userData.getGameCount()}"/><br>
        Games WIN: <c:out value="${userData.getWinGameCount()}"/><br>
        Games LOSE: <c:out value="${userData.getLostGameCount()}"/>
    </div>
</div>


<div class="d-flex flex-nowrap">
    <div class="order-1 p-2">
    <form method='GET' action='${pageContext.request.contextPath}/logout'>
        <button type="submit" onclick="restart()" class="btn  btn-block  btn-sm btn-danger button50">Log out</button>
    </form>
    </div>
    <div class="order-2 p-2">
    <form method='GET' action='${pageContext.request.contextPath}/login'>
        <button type="submit" onclick="login()" class="btn  btn-block  btn-sm btn-warning button50">Restart Game</button>
    </form>
    </div>
</div>

<hr>