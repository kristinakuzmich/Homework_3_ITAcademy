package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.Data;
import by.it_academy.jd2.dto.Vote;
import by.it_academy.jd2.service.VoteService;
import by.it_academy.jd2.service.api.IVoteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

@WebServlet(urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {
    private final IVoteService service = VoteService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("artistsList", Data.artistsList);
        req.setAttribute("genresList", Data.genresList);
        req.getRequestDispatcher("/template/vote.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String[] artists = req.getParameterValues("artist");
        String[] genres = req.getParameterValues("genre");
        String about = req.getParameter("about");

        if (genres == null || genres.length < 3 || genres.length > 5) {
            req.setAttribute("error", "Пожалуйста, выберите от 3 до 5 жанров");
            req.setAttribute("artistsList", Data.artistsList);
            req.setAttribute("genresList", Data.genresList);
            req.getRequestDispatcher("/template/vote.jsp").include(req, resp);
            return;
        }

        Vote vote = new Vote();
        vote.setDtCreate(LocalDateTime.now());
        vote.setArtist(artists[0]);
        vote.setGenres(Arrays.asList(genres));
        vote.setAbout(about);
        service.add(vote);

        req.getRequestDispatcher("/template/results.jsp").forward(req, resp);
    }
}