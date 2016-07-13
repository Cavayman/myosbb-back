package com.softserve.osbb.controller;

import com.softserve.osbb.model.Vote;
import com.softserve.osbb.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by Roman on 13.07.2016.
 */

@RestController
@RequestMapping("/restful")
public class VoteController {

    private static Logger logger = LoggerFactory.getLogger(VoteController.class);

    @Autowired
    private VoteService voteService;

    @RequestMapping(value = "/vote/add", method = RequestMethod.POST)
    public Vote addVote(@RequestBody Vote vote) {
        logger.info("Add vote" + vote);
        return voteService.addVote(vote);
    }

    @RequestMapping(value = "/vote/id={id}", method = RequestMethod.GET)
    public Vote getVote(@PathVariable("id") Integer id) {
        logger.info("Get vote by id: " + id);
        return voteService.getVoteById(id);
    }

    @RequestMapping(value = "/vote/all", method = RequestMethod.GET)
    public List<Vote> getAllVotes() {
        logger.info("Get all votes.");
        return voteService.getAllVotes();
    }

    @RequestMapping(value = "/vote/id={id}", method = RequestMethod.PUT)
    public Vote updateVote(@PathVariable("id") Integer id,
                           @RequestBody Vote vote) {
        logger.info("Update vote with id: " + id);
        return voteService.updateVote(id, vote);
    }

    @RequestMapping(value = "/vote/id={id}", method = RequestMethod.DELETE)
    public void deleteVote(@PathVariable("id") Integer id) {
        logger.info("Delete vote with id: " + id);
        voteService.deleteVote(id);
    }

    @RequestMapping(value = "/vote/all", method = RequestMethod.DELETE)
    public void deleteAllVotes() {
        logger.info("Delete all votes.");
        voteService.deleteAllVotes();
    }

}
