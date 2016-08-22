package com.softserve.osbb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * Created by cavayman on 05.07.2016.
 */
@Entity
@Table(name = "users")

public class User  implements Serializable {

    private Integer userId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private String phoneNumber;
    private String password;
    private String gender;

    private Boolean activated;
    private Role role;
    private Apartment apartment;
    private Collection<Vote> votes=new ArrayList<Vote>();
    private Collection<Apartment> apartments=new ArrayList<Apartment>();
    private Collection<Message> messages=new ArrayList<Message>();
    private Collection<Ticket> assigned=new ArrayList<Ticket>();
    private Collection<Ticket> tickets=new ArrayList<Ticket>();
    private Collection<Option> options=new ArrayList<Option>();
    private Collection<Report> reports=new ArrayList<Report>();
    public boolean isOwner;

    public User(User user) {
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.birthDate = user.getBirthDate();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.password = user.getPassword();
        this.gender = user.getGender();
    }


    public User() {
    }

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
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "birthDate")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
    @Column(name = "phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    @Basic
    @Column(name = "password")
    public String getPassword() {
        return this.password;
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

    @Column(name="activated")
    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    public Collection<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Collection<Vote> votes) {
        this.votes = votes;
    }


    @Basic
    @Column(name="isOwner")
    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "apartment_id",referencedColumnName = "apartment_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
     public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment appartament) {
        this.apartment = appartament;
    }



    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",cascade = CascadeType.REMOVE)
    public Collection<Message> getMessages() {

        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    public Collection<Report> getReports() {
        return reports;
    }

    public void setReports(Collection<Report> reports) {
        this.reports = reports;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assigned")
    @JsonIgnore
    public Collection<Ticket> getAssigned() {
        return assigned;
    }

    public void setAssigned(Collection<Ticket> assigned) {
        this.assigned = assigned;
    }



    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    @JoinTable(name="user_option",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName="user_id"),
            inverseJoinColumns = @JoinColumn(name="option_id", referencedColumnName="option_id")
    )
    public Collection<Option> getOptions() {
        return options;
    }

    public void setOptions(Collection<Option> options) {
        this.options = options;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
