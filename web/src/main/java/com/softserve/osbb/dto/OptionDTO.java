package com.softserve.osbb.dto;

import java.util.List;

/**
 * Created by Roman on 14.08.2016.
 */
public class OptionDTO {
    private Integer optionId;
    private String description;
    private VoteDTOForOption vote;
    private List<UserDTO> users;

    public OptionDTO() {}

    public OptionDTO(Integer optionId, String description, VoteDTOForOption vote, List<UserDTO> users) {
        this.optionId = optionId;
        this.description = description;
        this.vote = vote;
        this.users = users;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VoteDTOForOption getVote() {
        return vote;
    }

    public void setVote(VoteDTOForOption vote) {
        this.vote = vote;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsersDTO(List<UserDTO> users) {
        this.users = users;
    }
}
