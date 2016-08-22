package com.softserve.osbb.dto;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Roman on 14.08.2016.
 */
public class VoteDTO {
    private Integer voteId;
    private String description;
    private Boolean available;
    private Timestamp startTime;
    private Timestamp endTime;
    private UserDTO user;
    private List<OptionDTO> options;
    private List<Integer> usersId;

    public VoteDTO() {}

    public VoteDTO(Integer voteId, String description, Timestamp startTime,
                   Timestamp endTime, UserDTO user, List<OptionDTO> options) {
        this.voteId = voteId;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.options = options;
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDTO> options) {
        this.options = options;
    }

    public List<Integer> getUsersId() {
        return usersId;
    }

    public void setUsersId(List<Integer> usersId) {
        this.usersId = usersId;
    }
}
