package by.it_academy.jd2.controller;

import by.it_academy.jd2.dto.Stats;
import by.it_academy.jd2.service.VoteService;
import by.it_academy.jd2.service.api.IVoteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/result")
public class ResultServlet extends HttpServlet {

    private final IVoteService service = new VoteService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Stats stats = null;
        try {
            stats = service.getStats();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("artists", stats.getArtists());
        req.setAttribute("genres", stats.getGenre());
        req.setAttribute("about", stats.getAbouts().toArray());

        req.getRequestDispatcher("/results.jsp").forward(req, resp);
    }
}