package com.softserve.osbb;

import com.softserve.osbb.dao.VoteDAO;
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
public class VoteDAOTest {

    private VoteEntity voteEntity;

    @Autowired
    VoteDAO voteDAO;

    @Before
    public void init() {
        voteEntity = new VoteEntity();
        voteEntity.setIventId(2);
        voteEntity.setVoteValue(20);
    }

    @Test
    public void testSave() {
        VoteEntity savedVote= voteDAO.save(voteEntity);
        Assert.assertNotNull(savedVote);
        Assert.assertTrue(voteDAO.exists(savedVote.getVoteId()));
    }

    @Test
    public void testGetVoteById() {
        VoteEntity savedVote = voteDAO.save(voteEntity);
        Assert.assertEquals(savedVote.getVoteId(), voteDAO.getOne(savedVote.getVoteId()).getVoteId());
    }

    @Test
    public void testDeleteByVoteEntity() {
        voteEntity = voteDAO.save(voteEntity);
        voteDAO.delete(voteEntity);
        Assert.assertFalse(voteDAO.exists(voteEntity.getVoteId()));
    }

    @Test
    public void testDeleteByVoteId() {
        voteEntity = voteDAO.save(voteEntity);
        voteDAO.delete(voteEntity.getVoteId());
        Assert.assertFalse(voteDAO.exists(voteEntity.getVoteId()));
    }

    @Test
    public void testDeleteAllVotes(){
        voteDAO.deleteAll();
        assertTrue(voteDAO.findAll().isEmpty());
    }
}
