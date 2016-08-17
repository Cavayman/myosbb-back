package com.softserve.osbb.dto;

import com.softserve.osbb.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Roman on 14.08.2016.
 */
public class VoteDTO {
    private Integer voteId;
    private String description;
    private Date time;
    private User user;
    private List<OptionDTO> options;
    private List<Integer> usersId;

    public VoteDTO() {}

    public VoteDTO(Integer voteId, String description, Date time, User user, List<OptionDTO> options) {
        this.voteId = voteId;
        this.description = description;
        this.time = time;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
