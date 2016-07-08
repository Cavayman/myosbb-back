package com.softserve.osbb.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by cavayman on 05.07.2016.
 */
@Entity
@Table(name = "provider")
public class Provider implements Comparable{
    private Integer providerId;
    private String name;
    private String description;
    private String logourl;
    private Collection<Contract> contractsByProviderId;

    public Provider() {
    }

    public Provider(String name) {
        this.name = name;
    }

    public Provider(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    @Basic
    @Column(name = "logourl")
    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "providerByProviderId")
    public Collection<Contract> getContractsByProviderId() {
        return contractsByProviderId;
    }

    public void setContractsByProviderId(Collection<Contract> contractsByProviderId) {
        this.contractsByProviderId = contractsByProviderId;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }


    //natural order by provider's names
    @Override
    public int compareTo(Object o) {
        return name.compareTo(((Provider) o).getName());
    }
}
