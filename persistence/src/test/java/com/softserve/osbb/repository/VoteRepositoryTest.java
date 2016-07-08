package com.softserve.osbb.repository;

import com.softserve.osbb.PersistenceConfiguration;
import com.softserve.osbb.model.Vote;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertTrue;


/**
 * Created by Roman on 06.07.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
@Transactional
public class VoteRepositoryTest {

    private Vote vote;

    @Autowired
    VoteRepository voteRepository;

    @Before
    public void init() {
        vote = new Vote();
        vote.setEventId(2);
        vote.setVoteValue(20);
    }

    @Test
    public void testSave() {
        Vote savedVote= voteRepository.save(vote);
        Assert.assertNotNull(savedVote);
        Assert.assertTrue(voteRepository.exists(savedVote.getVoteId()));
    }

    @Test
    public void testGetVoteById() {
        Vote savedVote = voteRepository.save(vote);
        Assert.assertEquals(savedVote.getVoteId(), voteRepository.getOne(savedVote.getVoteId()).getVoteId());
    }

    @Test
    public void testDeleteByVote() {
        vote = voteRepository.save(vote);
        voteRepository.delete(vote);
        Assert.assertFalse(voteRepository.exists(vote.getVoteId()));
    }

    @Test
    public void testDeleteByVoteId() {
        vote = voteRepository.save(vote);
        voteRepository.delete(vote.getVoteId());
        Assert.assertFalse(voteRepository.exists(vote.getVoteId()));
    }

    @Test
    public void testDeleteAllVotes(){
        voteRepository.deleteAll();
        assertTrue(voteRepository.findAll().isEmpty());
    }
}
