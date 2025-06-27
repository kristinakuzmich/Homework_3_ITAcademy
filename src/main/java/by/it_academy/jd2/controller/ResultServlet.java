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
import java.io.Writer;

@WebServlet(urlPatterns = "/result")
public class ResultServlet extends HttpServlet {

    private final IVoteService service = new VoteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Stats stats = service.getStats();

        Writer out = resp.getWriter();
        out.write("<html><body>");
        out.write("<h1>Vote</h1>");
        stats.getArtists().forEach((k, v) -> {
            try {
                out.write("<p>" + k + ": " + v + "</p>");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        stats.getGenre().forEach((k, v) -> {
            try {
                out.write("<p>" + k + ": " + v + "</p>");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        stats.getAbouts().forEach((s) -> {
            try {
                out.write("<p>" + s + "</p>");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        out.write("</body></html>");
    }
}
