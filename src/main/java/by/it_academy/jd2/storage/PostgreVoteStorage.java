package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.Vote;
import by.it_academy.jd2.dto.Data;
import by.it_academy.jd2.storage.api.IVoteStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreVoteStorage implements IVoteStorage {

    private static final String URL = "jdbc:postgresql://localhost:5433/it_academy";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void add(Vote vote) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "INSERT INTO vote_app.votes (dt_create, artist, genres, about) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(vote.getDtCreate()));
            ps.setString(2, vote.getArtist());

            for (String genre : vote.getGenres()) {
                if (!Data.genresList.contains(genre)) {
                    throw new IllegalArgumentException("Некорректный жанр: " + genre);
                }
            }

            String genresStr = String.join(",", vote.getGenres());
            ps.setString(3, genresStr);

            ps.setString(4, vote.getAbout());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Vote> getAll() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<Vote> votes = new ArrayList<>();
        String sql = "SELECT dt_create, artist, genres, about FROM votes";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vote vote = new Vote();
                vote.setDtCreate(rs.getTimestamp("dt_create").toLocalDateTime());
                vote.setArtist(rs.getString("artist"));

                String genresStr = rs.getString("genres");
                List<String> genres = List.of(genresStr.split(","));

                for (String genre : genres) {
                    if (!Data.genresList.contains(genre)) {
                    }
                }
                vote.setGenres(genres);
                vote.setAbout(rs.getString("about"));
                votes.add(vote);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return votes;
    }
}