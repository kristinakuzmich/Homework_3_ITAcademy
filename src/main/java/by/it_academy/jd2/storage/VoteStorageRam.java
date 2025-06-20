package by.it_academy.jd2.storage;

import by.it_academy.jd2.dto.Vote;
import by.it_academy.jd2.storage.api.IVoteStorage;

import java.util.ArrayList;
import java.util.List;

public class VoteStorageRam implements IVoteStorage {
    private final List<Vote> votes = new ArrayList<>();

    @Override
    public void add(Vote vote) {
        this.votes.add(vote);
    }

    @Override
    public List<Vote> getAll() {
        return this.votes;
    }
}
