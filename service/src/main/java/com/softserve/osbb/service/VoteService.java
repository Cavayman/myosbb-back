package com.softserve.osbb.service;


import com.softserve.osbb.model.Vote;

import java.util.List;

/**
 * Created by Roman on 10.07.2016.
 */
public interface VoteService {

    Vote addVote(Vote vote) throws Exception;

    Vote getVoteById(Integer id)  throws Exception;

    List<Vote> getAllVotes()  throws Exception;

    Vote updateVote(Vote vote)  throws Exception;

    void deleteVoteById(Integer id)  throws Exception;

    void deleteAllVotes()  throws Exception;
}
