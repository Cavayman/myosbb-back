package com.softserve.osbb.model;

import javax.persistence.*;

/**
 * Created by cavayman on 05.07.2016.
 */
@Entity
@Table(name = "appartament_user", schema = "public", catalog = "myosbb")
public class AppartamentUserEntity {
    private Integer  appartamentUserEntityId;
    private Integer appartamentId;
    private Integer userId;
    private AppartamentEntity appartamentByAppartamentId;
    private UserEntity userByUserId;


    @Id
    @Column(name="appartament_user_id")
    public Integer getAppartamentUserEntityId() {
        return appartamentUserEntityId;
    }

    public void setAppartamentUserEntityId(Integer appartamentUserEntityId) {
        this.appartamentUserEntityId = appartamentUserEntityId;
    }

    @Basic
    @Column(name = "appartament_id")
    public Integer getAppartamentId() {
        return appartamentId;
    }

    public void setAppartamentId(Integer appartamentId) {
        this.appartamentId = appartamentId;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppartamentUserEntity that = (AppartamentUserEntity) o;

        if (appartamentId != null ? !appartamentId.equals(that.appartamentId) : that.appartamentId != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = appartamentId != null ? appartamentId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "appartament_id", referencedColumnName = "appartament_id")
    public AppartamentEntity getAppartamentByAppartamentId() {
        return appartamentByAppartamentId;
    }

    public void setAppartamentByAppartamentId(AppartamentEntity appartamentByAppartamentId) {
        this.appartamentByAppartamentId = appartamentByAppartamentId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
