package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.Stats;
import by.it_academy.jd2.dto.Vote;
import by.it_academy.jd2.service.api.IVoteService;
import by.it_academy.jd2.storage.PostgreVoteStorage;
import by.it_academy.jd2.storage.api.IVoteStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteService implements IVoteService {

    private static final IVoteStorage storage = new PostgreVoteStorage();
    private static VoteService instance = null;

    @Override
    public void add(Vote vote) {
        //VALIDATION
        this.storage.add(vote);
    }

    public VoteService() {
    }
    public static VoteService getInstance() {
        if (instance == null) {
            instance = new VoteService();
        }
        return instance;
    }

    @Override
    public Stats getStats() {
        Map<String, Integer> artistStats = new HashMap<>();
        Map<String, Integer> genreStats = new HashMap<>();
        List<String> abouts = new ArrayList<>();

        List<Vote> all = storage.getAll();

        for (Vote vote : all) {
            artistStats.compute(vote.getArtist(), (k, v) ->
                    v == null ? 1 : v + 1);

            for (String genre : vote.getGenres()) {
                genreStats.compute(genre, (k, v) ->
                        v == null ? 1 : v + 1);
            }


            abouts.add(vote.getDtCreate() + ": " + vote.getAbout());
        }

        return new Stats(artistStats, genreStats, abouts);
    }
}
