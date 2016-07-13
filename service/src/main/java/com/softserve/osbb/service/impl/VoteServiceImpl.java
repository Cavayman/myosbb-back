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
    public Vote addVote(Vote vote) {
        return voteRepository.saveAndFlush(vote);
    }

    @Override
    public Vote getVoteById(Integer id) {
        return voteRepository.findOne(id);
    }

    @Override
    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    @Override
    public boolean existsVote(Integer id) {
        return voteRepository.exists(id);
    }

    @Override
    public Vote updateVote(Vote vote) {

        int voteId = vote.getVoteId();

        if(voteRepository.exists(voteId)) {

            Vote updatedVote = voteRepository.getOne(voteId);
            updatedVote.setEvent(vote.getEvent());
            updatedVote.setTime(vote.getTime());
            updatedVote.setUsers(vote.getUsers());
            updatedVote.setVoteValue(vote.getVoteValue());
            return updatedVote;

        } else {

            throw new IllegalArgumentException("Vote with id=" + voteId
                    + " doesn't exist.");
        }
    }

    @Override
    public void deleteVote(Integer id) {
        voteRepository.delete(id);
    }

    @Override
    public void deleteVote(Vote vote) {
        voteRepository.delete(vote);
    }

    @Override
    public void deleteAllVotes() {
        voteRepository.deleteAll();
    }

    @Override
    public long countVotes() {
        return voteRepository.count();
    }
}
