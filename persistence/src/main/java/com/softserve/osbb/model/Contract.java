package com.softserve.osbb.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Roma on 05.07.2016.
 */
@Entity
@Table(name = "contract")
public class Contract {
    private Integer contractId;
    private Date datestart;
    private Date datefinish;
    private String text;
    private BigDecimal price;
    private String document;
    private Osbb osbbByOsbbId;
    private Provider providerByProviderId;

    @Id
    @Column(name = "contract_id")
    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    @Basic
    @Column(name = "datestart")
    public Date getDatestart() {
        return datestart;
    }

    public void setDatestart(Date datestart) {
        this.datestart = datestart;
    }

    @Basic
    @Column(name = "datefinish")
    public Date getDatefinish() {
        return datefinish;
    }

    public void setDatefinish(Date datefinish) {
        this.datefinish = datefinish;
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

    @Basic
    @Column(name = "document")
    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @ManyToOne
    @JoinColumn(name = "osbb_id", referencedColumnName = "osbb_id")
    public Osbb getOsbbByOsbbId() {
        return osbbByOsbbId;
    }

    public void setOsbbByOsbbId(Osbb osbbByOsbbId) {
        this.osbbByOsbbId = osbbByOsbbId;
    }

    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "provider_id")
    public Provider getProviderByProviderId() {
        return providerByProviderId;
    }

    public void setProviderByProviderId(Provider providerByProviderId) {
        this.providerByProviderId = providerByProviderId;
    }
}
