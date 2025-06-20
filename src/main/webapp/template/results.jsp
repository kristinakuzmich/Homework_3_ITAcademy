<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="by.it_academy.jd2.dto.Vote" %>
<%@ page import="by.it_academy.jd2.service.VoteService" %>
<%@ page import="by.it_academy.jd2.service.api.IVoteService" %>
<%@ page import="by.it_academy.jd2.dto.Stats" %>
<%@ page import="java.util.*" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<% IVoteService service = new VoteService();
Stats stats = service.getStats();%>

<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="UTF-8" />
        <title>Результаты голосования</title>
        <link rel="stylesheet" type="text/css" href="template/styles/style_Results.css">
    </head>
    <body>
        <h1>Результаты голосования</h1>

        <section>
            <h2>Лучшие исполнители (по количеству голосов)</h2>
                <% for (Map.Entry<String, Integer> artist : stats.getArtists().entrySet()) {
                    out.write("<p>" + artist.getKey() + ": " + artist.getValue() + "</p>");} %>
        </section>

        <section>
            <h2>Любимые жанры (по количеству голосов)</h2>
                <% for (Map.Entry<String, Integer> genre : stats.getGenre().entrySet()) {
                    out.write("<p>" + genre.getKey() + ": " + genre.getValue() + "</p>");} %>
        </section>

        <section>
            <h2>Информация о себе</h2>
                    <% for (String about : stats.getAbouts()) {
                        out.write("<p>" + about + "</p>");} %>
        </section>
    </body>
</html>