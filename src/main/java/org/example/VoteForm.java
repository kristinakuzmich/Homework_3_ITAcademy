package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(urlPatterns = "/voteform")

public class VoteForm extends HttpServlet {

    String[] singers = new String[]{"Анна Асти", "Комната культура", "Кадышева", "Максим", "Red Hot Chili Peppers", "Меладзе", "Eminem", "Adele"};
    String[] genres = new String[]{"Поп", "Рок", "Шансон", "Диско", "Джаз", "Кантри", "Фанк", "Рэп"};

    HashMap<String, Integer> singerMap = new HashMap<>();
    HashMap<String, Integer> genreMap = new HashMap<>();
    HashMap<String, Date> aboutMap = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String singer = req.getParameter("singer");
        String[] genres = req.getParameterValues("genre");
        String about = req.getParameter("about");

        PrintWriter writer = resp.getWriter();

        if (singer == null || genres.length < 3 || about.isEmpty()) {

            writer.write("<div <h1>404</h1>" +
                    "      <span>Oops!</span>" +
                    "      <p>" +
                    "        Кажется, вы не заполнили все поля или выбрали менее 3 жанров. <br> " +
                    "      </p>" +
                    "      <a href='http://localhost:63342/Homework_3_ITAcademy/index.html'>Вернуться на форму голосования</a>" +
                    "  </div>");
        } else {
            Date voteDate = new Date();
            if (singerMap.containsKey(singer)) {
                singerMap.put(singer, singerMap.get(singer) + 1);
            } else {
                singerMap.put(singer, 1);
            }
            for (String genre : genres) {
                if (genreMap.containsKey(genre)) {
                    genreMap.put(genre, genreMap.get(genre) + 1);
                } else {
                    genreMap.put(genre, 1);
                }
            }
            aboutMap.put(about, voteDate);

            writer.write("<div><h1>Результаты голосования</h1>" +
                    "<table border = '0' cellpadding = '7'>" +
                    "<tr><td><b>Исполнители</b></td><td><b>Голоса</b></td></tr>");
            for (Map.Entry<String, Integer> e : mapSorter(singerMap)) {
                writer.write("<tr><td>" + singers[Integer.parseInt(e.getKey())] + "</td><td>" + e.getValue() + "</td></tr>");
            }
            writer.write("<tr><td><b>Жанры</b></td><td><b>Голоса</b></td></tr>");
            for (Map.Entry<String, Integer> e : mapSorter(genreMap)) {
                writer.write("<tr><td>" + this.genres[Integer.parseInt(e.getKey())] + "</td><td>" + e.getValue() + "</td></tr>");
            }
            writer.write("</table>" +
                    "<table border = '0' cellpadding = '7'" +
                    "<tr><td><b>Комментарии:</b></td></tr>");
            for (Map.Entry<String, Date> e : mapSorter(aboutMap)) {
                writer.write("<tr><td>" + e.getKey() + "</td></tr>");
                writer.write("<tr><td><span style='color: gray; font-size: 12px;'>добавлено " + e.getValue() + "</span></td></tr>");
            }
            writer.write("<tr><td></td></tr>" +
                    "<tr><td><a href='http://localhost:63342/Homework_3_ITAcademy/index.html'>Вернуться на форму голосования</a></td></tr>" +
                    "</table>");
        }
    }

    public static <K, V extends Comparable<? super V>> List<Map.Entry<K, V>> mapSorter(Map<K, V> m) {
        List<Map.Entry<K, V>> li = new ArrayList<>(m.entrySet());
        li.sort(Map.Entry.comparingByValue());
        Collections.reverse(li);
        return li;
    }
}