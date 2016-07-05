package com.softserve.osbb.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by cavayman on 05.07.2016.
 */
@Entity
@Table(name = "user")
public class UserEntity {
    private Integer userId;
    private String firstname;
    private String lastname;
    private Date birthdate;
    private String email;
    private String phonenumber;
    private String password;
    private String gender;
    private List<VoteEntity> votes;
    private List<ApartmentEntity> appartaments;
    private List<MassegeEntity> messages;
    private List<TicketEntity> ticket;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "birthdate")
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phonenumber")
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    public List<VoteEntity> getVotes() {
        return votes;
    }

    public void setVotes(List<VoteEntity> votes) {
        this.votes = votes;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_appartament", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "appartament_id",
                    nullable = false, updatable = false) })
    public List<ApartmentEntity> getApartments() {
        return appartaments;
    }

    public void setApartments(List<ApartmentEntity> appartaments) {
        this.appartaments = appartaments;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    public List<MassegeEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MassegeEntity> messages) {
        this.messages = messages;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    public List<TicketEntity> getTicket() {
        return ticket;
    }

    public void setTicket(List<TicketEntity> ticket) {
        this.ticket = ticket;
    }

}
