package com.softserve.osbb.controller;

import com.softserve.osbb.model.Vote;
import com.softserve.osbb.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Roman on 13.07.2016.
 */

@CrossOrigin
@RestController
@RequestMapping("/restful")
public class VoteController {

    private static Logger logger = LoggerFactory.getLogger(VoteController.class);

    @Autowired
    private VoteService voteService;

    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    public ResponseEntity<Resource<Vote>> createVote(@RequestBody Vote vote) {
        logger.info("Add vote" + vote);
        vote = voteService.addVote(vote);
        return new ResponseEntity<>(addResourceLinkToVote(vote), HttpStatus.OK);
    }

    @RequestMapping(value = "/vote/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Vote>> getVoteById(@PathVariable("id") Integer id) {
        logger.info("Get vote by id: " + id);
        Vote vote = voteService.getVoteById(id);
        return new ResponseEntity<>(addResourceLinkToVote(vote), HttpStatus.OK);
    }

    @RequestMapping(value = "/vote", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Vote>>> getAllVotes() {
        logger.info("Get all votes.");
        List<Vote> voteList = voteService.getAllVotes();
        final List<Resource<Vote>> resourceVoteList = new ArrayList<>();
        for(Vote v: voteList) {
            resourceVoteList.add(addResourceLinkToVote(v));
        }
        return new ResponseEntity<>(resourceVoteList, HttpStatus.OK);
    }

    @RequestMapping(value = "/vote", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Vote>> updateVote(@RequestBody Vote vote) {
        logger.info("Update vote with id: " + vote.getVoteId());
        Vote updatedVote = voteService.updateVote(vote);
        return new ResponseEntity<>(addResourceLinkToVote(updatedVote), HttpStatus.OK);
    }

    @RequestMapping(value = "/vote/id/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Resource<Vote>> deleteVote(@PathVariable("id") Integer id) {
        logger.info("Delete vote with id: " + id);
        voteService.deleteVote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/vote", method = RequestMethod.DELETE)
    public ResponseEntity<Vote>  deleteAllVotes() {
        logger.info("Delete all votes.");
        voteService.deleteAllVotes();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Resource<Vote> addResourceLinkToVote(Vote vote) {
        if (vote == null) return null;
        Resource<Vote> voteResource = new Resource<>(vote);
        voteResource.add(linkTo(methodOn(VoteController.class)
                .getVoteById(vote.getVoteId()))
                .withSelfRel());
        return voteResource;
    }



}
