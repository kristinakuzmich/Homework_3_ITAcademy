package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.Vote;
import by.it_academy.jd2.storage.api.IVoteStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreVoteStorage implements IVoteStorage {

    private static final String URL = "jdbc:postgresql://localhost:5433/it_academy";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    @Override
    public void add(Vote vote) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement("""
                         INSERT INTO vote_app.votes(dt_create, artist, genres, about)
                         	VALUES (?, ?, ?, ?);
                     """);
        ) {
            ps.setTimestamp(1, Timestamp.valueOf(vote.getDtCreate()));
            ps.setString(2, vote.getArtist());
            ps.setString(3, String.join(",", vote.getGenres()));
            ps.setString(4, vote.getAbout());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Vote> getAll() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        List<Vote> votes = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement("""
                         SELECT dt_create, artist, genres, about FROM vote_app.votes
                     """);) {
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {

                    Vote vote = new Vote();
                    vote.setDtCreate(rs.getTimestamp("dt_create").toLocalDateTime());
                    vote.setArtist(rs.getString("artist"));

                    String genresStr = rs.getString("genres");
                    List<String> genres = List.of(genresStr.split(","));
                    vote.setGenres(genres);
                    vote.setAbout(rs.getString("about"));
                    votes.add(vote);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return votes;
    }
}
