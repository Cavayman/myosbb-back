package com.softserve.osbb.dto;

import com.softserve.osbb.model.User;

/**
 * Created by nazar.dovhyy on 03.08.2016.
 */
public class HousePageDTO {

    private Integer houseId;
    private String city;
    private String street;
    private String zipCode;
    private User head;

    public HousePageDTO(String city, String street, String zipCode, User head) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.head = head;
    }

    public HousePageDTO() {

    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public User getHead() {
        return head;
    }

    public void setHead(User head) {
        this.head = head;
    }
}
