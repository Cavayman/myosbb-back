package com.softserve.osbb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.softserve.osbb.model.enums.BillStatus;
import com.softserve.osbb.utils.CustomLocalDateDeserializer;
import com.softserve.osbb.utils.CustomLocalDateSerializer;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by cavayman on 10.07.16.
 */

@Entity
@Table(name = "bill")
public class Bill implements Serializable {

    private Integer billId;
    private LocalDate date;
    private Float tariff;
    private Provider provider;
    private Float toPay;
    private Float paid;
    private Apartment apartment;
    private BillStatus billStatus;

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
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @Column(name = "date")
    public LocalDate getDate() {
        return date;
    }

    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    public void setDate(LocalDate date) {
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JoinColumn(name = "apartment_id", referencedColumnName = "apartment_id")
    public Apartment getApartment() {
        return apartment == null ? new Apartment() : apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "varchar(255) default 'NOT_PAID'")
    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", date=" + date +
                ", tariff=" + tariff +
                ", toPay=" + toPay +
                ", paid=" + paid +
                ", billStatus=" + billStatus +
                '}';
    }
}
