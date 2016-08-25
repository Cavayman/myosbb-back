package com.softserve.osbb.sheduler;

import com.softserve.osbb.controller.VoteController;
import com.softserve.osbb.model.Vote;
import com.softserve.osbb.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Roman on 22.08.2016.
 */

@Component
public class VoteScheduler {
    private static Logger logger = LoggerFactory.getLogger(VoteController.class);

    @Autowired
    VoteService voteService;

    @Scheduled(fixedRate = 120000)
    public void checkVotingOnDate() {
        logger.info("Vote scheduler: update voting.");
        List<Vote> voteList = voteService.getAllAvailable();
        for(Vote vote: voteList) {
            if(vote.getEndTime() != null) {
                if(LocalDateTime.now().isAfter(vote.getEndTime().toLocalDateTime())){
                    vote.setAvailable(false);
                    voteService.updateVote(vote);
                }
            }
        }
    }
}
