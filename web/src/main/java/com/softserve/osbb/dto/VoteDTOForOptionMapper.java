package com.softserve.osbb.dto;

import com.softserve.osbb.model.Vote;

/**
 * Created by Roman on 16.08.2016.
 */
public class VoteDTOForOptionMapper {

    public static VoteDTOForOption mapVoteEntityToDTO(Vote vote) {
        VoteDTOForOption voteDTO = new VoteDTOForOption();
        if(vote != null) {
            voteDTO.setVoteId(vote.getVoteId());
            voteDTO.setDescription(vote.getDescription());
        }
        return voteDTO;
    }
}
