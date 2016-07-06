package com.softserve.osbb.repository;

import com.softserve.osbb.OsbbApplicationRunner;
import com.softserve.osbb.model.VoteEntity;
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
@SpringApplicationConfiguration(classes = OsbbApplicationRunner.class)
@Transactional
public class VoteRepositoryTest {

    private VoteEntity voteEntity;

    @Autowired
    VoteRepository voteRepository;

    @Before
    public void init() {
        voteEntity = new VoteEntity();
        voteEntity.setIventId(2);
        voteEntity.setVoteValue(20);
    }

    @Test
    public void testSave() {
        VoteEntity savedVote= voteRepository.save(voteEntity);
        Assert.assertNotNull(savedVote);
        Assert.assertTrue(voteRepository.exists(savedVote.getVoteId()));
    }

    @Test
    public void testGetVoteById() {
        VoteEntity savedVote = voteRepository.save(voteEntity);
        Assert.assertEquals(savedVote.getVoteId(), voteRepository.getOne(savedVote.getVoteId()).getVoteId());
    }

    @Test
    public void testDeleteByVoteEntity() {
        voteEntity = voteRepository.save(voteEntity);
        voteRepository.delete(voteEntity);
        Assert.assertFalse(voteRepository.exists(voteEntity.getVoteId()));
    }

    @Test
    public void testDeleteByVoteId() {
        voteEntity = voteRepository.save(voteEntity);
        voteRepository.delete(voteEntity.getVoteId());
        Assert.assertFalse(voteRepository.exists(voteEntity.getVoteId()));
    }

    @Test
    public void testDeleteAllVotes(){
        voteRepository.deleteAll();
        assertTrue(voteRepository.findAll().isEmpty());
    }
}
