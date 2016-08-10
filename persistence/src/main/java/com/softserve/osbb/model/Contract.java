package com.softserve.osbb.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.softserve.osbb.utils.CustomLocalDateTimeDeserializer;
import com.softserve.osbb.utils.CustomLocalDateTimeSerializer;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * Created by Roma on 05.07.2016.
 * Assigned to Anastasiia on 20.07.2016
 */
@Entity
@Table(name = "contract")
public class Contract {
    private Integer contractId;
    private LocalDate dateStart;
    private LocalDate dateFinish;
    private String text;
    private BigDecimal price;
    private Attachment attachment;
    private Osbb osbb;
    private Provider provider;

    @Id
    @Column(name = "contract_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    @Basic
    @Column(name = "dateStart")
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    public LocalDate getDateStart() {
        return dateStart;
    }

    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    @Basic
    @Column(name = "dateFinish")
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    public LocalDate getDateFinish() {
        return dateFinish;
    }

    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    public void setDateFinish(LocalDate dateFinish) {
        this.dateFinish = dateFinish;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "attachment_id", referencedColumnName = "attachment_id")
    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    @ManyToOne
    @JoinColumn(name = "osbb_id", referencedColumnName = "osbb_id")
    public Osbb getOsbb() {
        return osbb;
    }

    public void setOsbb(Osbb osbb) {
        this.osbb = osbb;
    }

    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "provider_id")
    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
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
