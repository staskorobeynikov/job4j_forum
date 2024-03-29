<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <title>Forum</title>
</head>
<body>
<div class="container pt-1">
    <div class="row">
        <ul class="nav">
            <li class="nav-item" style="font-weight: bold">
                <a class="nav-link" href="<c:url value="/topics/create"/>">Добавить тему</a>
            </li>
        </ul>
        <ul class="nav">
            <li class="nav-item" style="font-weight: bold">
                <a class="nav-link" href="<c:url value="/out"/>"><c:out value="${user.username}"/> | Выйти из системы</a>
            </li>
        </ul>
        <ul class="nav">
            <li class="nav-item" style="font-weight: bold">
                <a class="nav-link" href="<c:url value="/login"/>">Войти</a>
            </li>
        </ul>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header" style="font-weight: bold; font-size: larger">
                Форум job4j
            </div>
            <div class="card-body">
                <table class="table table-striped, table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Название</th>
                        <th scope="col">Автор</th>
                        <th scope="col">Статус</th>
                        <th scope="col">Количество сообщений</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${topics}" var="topic">
                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath}/posts/post?id=${topic.id}">${topic.name}</a>
                            </td>
                            <td>
                                <c:out value="${topic.author.username}"/>
                            </td>
                            <td>
                                <c:out value="${topic.status.text}"/>
                            </td>
                            <td>
                                <c:out value="${topic.posts.size()}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
