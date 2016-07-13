package com.softserve.osbb.controller;

import com.softserve.osbb.model.Vote;
import com.softserve.osbb.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Roman on 13.07.2016.
 */

@RestController
@RequestMapping("/restful")
public class VoteController {

    @Autowired
    VoteService voteService;

    @RequestMapping(value = "/vote/add", method = RequestMethod.POST)
    public Vote addVote(@RequestBody Vote vote) {
        return voteService.addVote(vote);
    }

    @RequestMapping(value = "/vote/id={id}", method = RequestMethod.GET)
    public Vote getVote(@PathVariable("id") Integer id) {
        return voteService.getVoteById(id);
    }

    @RequestMapping(value = "/vote/all", method = RequestMethod.GET)
    public List<Vote> getAllVotes() {
        return voteService.getAllVotes();
    }

    @RequestMapping(value = "/vote/{id}", method = RequestMethod.PUT)
    public Vote updateVote(@PathVariable("id") Integer id,
                           @RequestBody Vote vote) {
        return voteService.updateVote(vote);
    }

    @RequestMapping(value = "/vote/{id}", method = RequestMethod.DELETE)
    public void deleteVote(@PathVariable("id") Integer id) {
        voteService.deleteVote(id);
    }

    @RequestMapping(value = "/vote/all", method = RequestMethod.DELETE)
    public void deleteAllVotes() {
        voteService.deleteAllVotes();
    }

}
