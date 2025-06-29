package by.it_academy.jd2.storage.api;

import by.it_academy.jd2.dto.Vote;

import java.util.List;

public interface IVoteStorage {
    void add(Vote vote) throws ClassNotFoundException;
    List<Vote> getAll() throws ClassNotFoundException;
}
