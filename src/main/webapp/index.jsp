
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
    <title align="center" >Quest JavaRush</title>
    <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>
    <link href="static/main.css" rel="stylesheet">
    <link href="static/bootstrap-5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script>
        function restart() {
            $.ajax({
                url: '/restart',
                type: 'POST',
                contentType: 'application/json;charset=UTF-8',
                async: false,
                success: function () {
                    location.reload();
                }
            });
        }
    </script>
</head>


<body>

<div class="container">

    <c:if test="${quest  == null}">
        <h1>Quest JavaRush</h1>
        <form action="/restart">
            <img src="static/start_img.jpg"><br>
            <label for="fname">Здравствуй :</label><br>
            <input type="text" id="fname" name="fname" value="UserName"><br><br>
            <button type="submit" class="button" onclick="start()">Вступить в игру</button>
        </form>
    </c:if>

    <c:if test="${quest  != null}">
        <h2>${question.getTitle()}</h2>
        <table>
            <tr>
                <th colspan="${question.answers.size()}">${question.getText()}</th>
            </tr>
            <tr>
                <c:forEach items="${question.answers}" var="item">
                    <td> <button class="button answer"  onclick="window.location='/action?clickId=${item.getId()}'">${item.getTitle()}</button>  </td>
                </c:forEach>
            </tr>
        </table>
        <hr>
        <br>
        <button class="button" onclick="restart()">Restart quest</button>
    </c:if>
</div>
<script src="static/bootstrap-5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>