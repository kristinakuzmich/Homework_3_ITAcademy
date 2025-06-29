<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">

    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="template/styles/style_Index.css">
    </head>

    <body>
        <h1>Форма голосования</h1>
        <form action="vote" method="post">

            <h3>Лучший исполнитель:</h3>
                <c:forEach items="${artistsList}" var="item">
                    <p><input type="radio" name="artist" value="${item}" required>${item}</p>
                </c:forEach>

            <h3>Ваши любимые жанры (выберите 3-5):</h3>
                <c:forEach items="${genresList}" var="item">
                    <p><input type="checkbox" name="genre" value="${item}">${item}</p>
                </c:forEach>

            <h3>Расскажите о себе:</h3>
                <textarea name="about" rows="4" cols="50" required></textarea><br><br>

            <input type="submit" value="Отправить">
        </form>

    <% String error = (String) request.getAttribute("error");
        if (error != null) {%>
    <p style="color:red;"><%= error %></p>
    <% } %>
    </body>
</html>