package com.softserve.osbb.controller;

import com.softserve.osbb.dto.VoteDTO;
import com.softserve.osbb.dto.VoteDTOMapper;
import com.softserve.osbb.model.Option;
import com.softserve.osbb.model.User;
import com.softserve.osbb.model.Vote;
import com.softserve.osbb.service.OptionService;
import com.softserve.osbb.service.UserService;
import com.softserve.osbb.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private UserService userService;

    @Autowired
    private OptionService optionService;

    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    public ResponseEntity<Resource<Vote>> createVote(@RequestBody Vote vote) {
        logger.info("Add vote: " + vote);
        System.out.println("vote.user: " + vote.getUser());
        int usrId = vote.getUser()!=null? vote.getUser().getUserId(): 3;
        User user = userService.findOne(usrId);
        user.getVotes().add(vote);
        vote.setUser(user);
        vote = voteService.addVote(vote);
        userService.save(user);
        for(Option option: vote.getOptions()) {
            option.setVote(vote);
            optionService.addOption(option);
        }
        return new ResponseEntity<>(addResourceLinkToVote(vote), HttpStatus.OK);
    }

    //  **********************************************************************
        private  Timestamp getStartTime() {
            ZoneId kievZone = ZoneId.of("Europe/Kiev");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ZonedDateTime minskTime = ZonedDateTime.of(LocalDateTime.now(), kievZone);

            return Timestamp.valueOf(minskTime.toLocalDateTime().format(dtf));
        }

        private Timestamp getEndTime() {
            ZoneId kievZone = ZoneId.of("Europe/Kiev");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ZonedDateTime minskTime = ZonedDateTime.of(LocalDateTime.now().plusHours(8), kievZone);
            return Timestamp.valueOf(minskTime.toLocalDateTime().format(dtf));
        }
    //  **********************************************************************

    @RequestMapping(value = "/vote/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Vote>> getVoteById(@PathVariable("id") Integer id) {
        logger.info("Get vote by id: " + id);
        Vote vote = voteService.getVoteById(id);
        return new ResponseEntity<>(addResourceLinkToVote(vote), HttpStatus.OK);
    }

    @RequestMapping(value = "/vote", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<VoteDTO>>> getAllVotes() {
        logger.info("Get all votes.");
        List<Vote> voteList = voteService.getAllVotesByDateOfCreation();
        //List<Vote> voteList = voteService.getAllVotes();
        List<VoteDTO> voteDTOList = new ArrayList<>();
        for(Vote vote: voteList) {
            voteDTOList.add(VoteDTOMapper.mapVoteEntityToDTO(vote));
        }
        final List<Resource<VoteDTO>> resourceVoteList = new ArrayList<>();
        for(VoteDTO v: voteDTOList) {
            resourceVoteList.add(addResourceLinkToVote(v));
        }
        return new ResponseEntity<>(resourceVoteList, HttpStatus.OK);
    }
/*
    @RequestMapping(value = "/vote", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<VoteDTO>>> getAllVotes() {
        logger.info("Get all votes.");
        List<Vote> voteList = voteService.getAllVotes();
        List<VoteDTO> voteDTOList = new ArrayList<>();
        for(Vote vote: voteList) {
            voteDTOList.add(VoteDTOMapper.mapVoteEntityToDTO(vote));
        }
        final List<Resource<VoteDTO>> resourceVoteList = new ArrayList<>();
        for(VoteDTO v: voteDTOList) {
            resourceVoteList.add(addResourceLinkToVote(v));
        }
        return new ResponseEntity<>(resourceVoteList, HttpStatus.OK);
    }*/

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
    private Resource<VoteDTO> addResourceLinkToVote(VoteDTO vote) {
        if (vote == null) return null;
        Resource<VoteDTO> voteResource = new Resource<>(vote);
        voteResource.add(linkTo(methodOn(VoteController.class)
                .getVoteById(vote.getVoteId()))
                .withSelfRel());
        return voteResource;
    }

}
