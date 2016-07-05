package com.softserve.osbb.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by cavayman on 05.07.2016.
 */
@Entity
@Table(name = "contract", schema = "public", catalog = "myosbb")
public class ContractEntity {
    private Integer contractId;
    private String datestart;
    private String datefinish;
    private String text;
    private BigDecimal price;
    private String document;
    private OsbbEntity osbbByOsbbId;
    private ProviderEntity providerByProviderId;

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
    public String getDatestart() {
        return datestart;
    }

    public void setDatestart(String datestart) {
        this.datestart = datestart;
    }

    @Basic
    @Column(name = "datefinish")
    public String getDatefinish() {
        return datefinish;
    }

    public void setDatefinish(String datefinish) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractEntity that = (ContractEntity) o;

        if (contractId != null ? !contractId.equals(that.contractId) : that.contractId != null) return false;
        if (datestart != null ? !datestart.equals(that.datestart) : that.datestart != null) return false;
        if (datefinish != null ? !datefinish.equals(that.datefinish) : that.datefinish != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (document != null ? !document.equals(that.document) : that.document != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = contractId != null ? contractId.hashCode() : 0;
        result = 31 * result + (datestart != null ? datestart.hashCode() : 0);
        result = 31 * result + (datefinish != null ? datefinish.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (document != null ? document.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "osbb_id", referencedColumnName = "osbb_id")
    public OsbbEntity getOsbbByOsbbId() {
        return osbbByOsbbId;
    }

    public void setOsbbByOsbbId(OsbbEntity osbbByOsbbId) {
        this.osbbByOsbbId = osbbByOsbbId;
    }

    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "provider_id")
    public ProviderEntity getProviderByProviderId() {
        return providerByProviderId;
    }

    public void setProviderByProviderId(ProviderEntity providerByProviderId) {
        this.providerByProviderId = providerByProviderId;
    }
}
