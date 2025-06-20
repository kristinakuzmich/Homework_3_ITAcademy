package by.it_academy.jd2.dto;

import java.util.List;
import java.util.Map;

public class Stats {
    private final Map<String, Integer> artists;
    private final Map<String, Integer> genre;
    private final List<String> abouts;

    public Stats(Map<String, Integer> artists, Map<String, Integer> genre, List<String> abouts) {
        this.artists = artists;
        this.genre = genre;
        this.abouts = abouts;
    }

    public Map<String, Integer> getArtists() {
        return artists;
    }

    public Map<String, Integer> getGenre() {
        return genre;
    }

    public List<String> getAbouts() {
        return abouts;
    }
}
