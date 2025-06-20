package by.it_academy.jd2.service.api;

import by.it_academy.jd2.dto.Stats;
import by.it_academy.jd2.dto.Vote;

public interface IVoteService {
    void add(Vote vote);
    Stats getStats();
}
