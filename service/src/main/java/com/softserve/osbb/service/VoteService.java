package com.softserve.osbb.service;


import com.softserve.osbb.model.Vote;

import java.util.List;

/**
 * Created by Roman on 10.07.2016.
 */
public interface VoteService {

    Vote addVote(Vote vote);

    Vote getVoteById(Integer id);

    List<Vote> getAllVotes();

    boolean existsVote(Integer id);

    Vote updateVote(Vote vote);

    void deleteVote(Integer id);

    void deleteVote(Vote vote);

    void deleteAllVotes();

    long countVotes();
}
