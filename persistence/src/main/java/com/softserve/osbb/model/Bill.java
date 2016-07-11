package com.softserve.osbb.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nataliia on 10.07.16.
 */

@Entity
@Table(name = "bill")
public class Bill {

    private Integer billId;
    private Date date;
    private Float tariff;
    private Provider provider;
    private Float toPay;
    private Float paid;
    private Apartment apartment;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "tariff")
    public Float getTariff() {
        return tariff;
    }

    public void setTariff(Float tariff) {
        this.tariff = tariff;
    }

    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "provider_id")
    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Basic
    @Column(name = "to_pay")
    public Float getToPay() {
        return toPay;
    }

    public void setToPay(Float toPay) {
        this.toPay = toPay;
    }

    @Basic
    @Column(name = "paid")
    public Float getPaid() {
        return paid;
    }

    public void setPaid(Float paid) {
        this.paid = paid;
    }

    @ManyToOne
    @JoinColumn(name = "apartment_id", referencedColumnName = "apartment_id")
    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
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
