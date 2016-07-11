package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Vote;
import com.softserve.osbb.repository.VoteRepository;
import com.softserve.osbb.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Roman on 10.07.2016.
 */
@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteRepository voteRepository;

    @Override
    public Vote addVote(Vote vote) throws Exception {
        return voteRepository.saveAndFlush(vote);
    }

    @Override
    public Vote getVoteById(Integer id) throws Exception {
        return voteRepository.getOne(id);
    }

    @Override
    public List<Vote> getAllVotes() throws Exception {
        return voteRepository.findAll();
    }

    @Override
    public Vote updateVote(Vote vote) throws Exception {

        int voteId = vote.getVoteId();

        if(voteRepository.exists(voteId)) {

            Vote updatedVote = voteRepository.getOne(voteId);
            updatedVote.setEvent(vote.getEvent());
            updatedVote.setTime(vote.getTime());
            updatedVote.setUsers(vote.getUsers());
            updatedVote.setVoteValue(vote.getVoteValue());
            return updatedVote;

        } else {

            throw new Exception("Vote with id=" + voteId
                    + " doesn't exist.");
        }
    }

    @Override
    public void deleteVoteById(Integer id) throws Exception {
        voteRepository.delete(id);
    }

    @Override
    public void deleteAllVotes() throws Exception {
        voteRepository.deleteAll();
    }
}
