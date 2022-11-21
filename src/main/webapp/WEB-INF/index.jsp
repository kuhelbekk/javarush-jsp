<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
    <title align="center">Quest JavaRush</title>
    <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/main.css">

</head>

<body>


<c:if test="${userData  == null}">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="text-center">
                    <h1>Quest JavaRush</h1>
                    <form action="/restart">
                        <img class="rounded mx-auto d-block"
                             src="${pageContext.request.contextPath}/static/start_img.jpg"><br>
                        <label for="fname">Здравствуй, как тебя зовут? </label><br>
                        <input type="text" id="fname" name="fname" value="#username"><br><br>
                        <h6>Давай сыграем в игру?</h6>
                        <button type="submit" class="button" onclick="start()">Давай поиграем</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</c:if>

<c:if test="${userData  != null}">
    <section class="container">
        <div class = "row">
            <div class="col-12" >
                <h2 class="text-center mb-5">${question.getTitle()}</h2>
            </div>
        </div>
        <div class = "row">
            <div class="col-12">
                <h3 class="text-center">${question.getText()}</h3>
            </div>
        </div>


        <c:if test="${question.getImg() != null}">
            <div class = "row">
                <div class="col-12">
                    <img src="${pageContext.request.contextPath}/static/${question.getImg()}" class="rounded mx-auto d-block">
                </div>
            </div>
        </c:if>
        <c:forEach items="${question.answers}" var="item">
            <div class="row mb-2" >
                <div class="col-12" >
                    <form action="${pageContext.request.contextPath}/action" method="post">
                        <input type="hidden" name="clickId" value="${item.getId()}">
                        <button  class="w-100 btn btn-primary btn-lg" type="submit">${item.getTitle()}</button>
                    </form>
                </div>
            </div>
        </c:forEach>

    </section>
    <hr>
    <br>
    <button class="button" onclick="restart()">Restart quest</button>
</c:if>

<script src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js"
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
        crossorigin="anonymous"></script>
</body>
</html>